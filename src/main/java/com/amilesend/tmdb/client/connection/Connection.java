/*
 * tmdb-java-client - A client to access the TMDB API
 * Copyright © 2024 Andy Miles (andy.miles@amilesend.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.amilesend.tmdb.client.connection;

import com.amilesend.tmdb.client.parse.GsonFactory;
import com.amilesend.tmdb.client.parse.parser.GsonParser;
import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.IOException;

import static com.google.common.net.HttpHeaders.ACCEPT;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.google.common.net.HttpHeaders.USER_AGENT;
import static com.google.common.net.MediaType.JSON_UTF_8;

/** Wraps a {@link OkHttpClient} that manages parsing responses to corresponding POJO types. */
@Slf4j
public class Connection {
    public static final String JSON_CONTENT_TYPE = JSON_UTF_8.toString();
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse(JSON_CONTENT_TYPE);
    public static final String DEFAULT_BASE_URL = "https://api.themoviedb.org/3";

    private static final String THROTTLED_RETRY_AFTER_HEADER = "Retry-After";
    private static final String USER_AGENT_VALUE = "TMDBJavaClient/1.0";
    private static final Long DEFAULT_RETRY_AFTER_SECONDS = Long.valueOf(10L);
    private static final int THROTTLED_RESPONSE_CODE = 429;
    private static final int MAX_BASE_URL_STR_LENGTH = 256;

    private final OkHttpClient httpClient;
    /** The configured GSON instance used for marshalling request and responses to/from JSON. */
    @Getter
    private final Gson gson;
    /** The base URL for the Graph API. */
    @Getter
    private final String baseUrl;
    private final String readAccessToken;

    /**
     * Creates a new {@code Connection}.
     *
     * @param httpClient the configured HTTP client
     * @param gsonFactory the factory used to vend configured GSON instances for request/reply serialization
     * @param baseUrl the base URL to use for Graph API invocations
     */
    @Builder
    @VisibleForTesting
    Connection(
            @NonNull final OkHttpClient httpClient,
            @NonNull final GsonFactory gsonFactory,
            final String baseUrl,
            final String readAccessToken) {
        Validate.notBlank(readAccessToken, "readAccessToken must not be blank");

        this.httpClient = httpClient;
        this.gson = gsonFactory.newInstanceForConnection();
        this.baseUrl = StringUtils.isNotBlank(baseUrl) && baseUrl.length() < MAX_BASE_URL_STR_LENGTH
                ? baseUrl
                : DEFAULT_BASE_URL;
        this.readAccessToken = "Bearer " + readAccessToken;
    }

    /**
     * Creates a new {@code Connection} that is configured with the default settings for the given
     * read access token.
     *
     * @param readAccessToken the read access token
     * @return the connection
     */
    public static Connection newDefaultInstance(final String readAccessToken) {
        return new Connection(
                new OkHttpClient.Builder().build(),
                GsonFactory.getInstance(),
                DEFAULT_BASE_URL,
                readAccessToken);
    }

    /**
     * Creates a new {@link Request.Builder} with pre-configured headers for request that expect a JSON-formatted
     * response body.
     *
     * @return the request builder
     */
    public Request.Builder newRequestBuilder() {
        return new Request.Builder()
                .addHeader(AUTHORIZATION, readAccessToken)
                .addHeader(USER_AGENT, USER_AGENT_VALUE)
                .addHeader(ACCEPT, JSON_CONTENT_TYPE);
    }

    /**
     * Executes the given {@link Request} and parses the JSON-formatted response with given {@link GsonParser}.
     *
     * @param request the request
     * @param parser the parser to decode the response body
     * @return the response as a POJO resource type
     * @param <T> the POJO resource type
     * @throws ConnectionException if an error occurred during the transaction
     */
    public <T> T execute(@NonNull final Request request, @NonNull final GsonParser<T> parser)
            throws ConnectionException {
        try {
            try (final Response response = httpClient.newCall(request).execute()) {
                if (log.isDebugEnabled()) {
                    log.debug("Received response: {}", response);
                }
                validateResponse(response);

                return parser.parse(getGson(), response.body().byteStream());
            }
        } catch (final IOException ex) {
            throw new RequestException("Unable to execute request: " + ex.getMessage(), ex);
        } catch (final JsonParseException ex) {
            throw new ResponseParseException("Error parsing response: " + ex.getMessage(), ex);
        }
    }

    private static void validateResponse(final Response response) {
        final int code = response.code();
        if (code == THROTTLED_RESPONSE_CODE) {
            final Long retryAfterSeconds = extractRetryAfterHeaderValue(response);
            final String msg = retryAfterSeconds != null
                    ? "Request throttled. Retry after " + retryAfterSeconds + " seconds"
                    : "Request throttled";
            throw new ThrottledException(msg, retryAfterSeconds);
        }

        final boolean isRequestError = String.valueOf(code).startsWith("4");
        if (isRequestError) {
            throw new RequestException(new StringBuilder("Error with request (")
                    .append(code)
                    .append("): ")
                    .append(response)
                    .toString());
        } else if (!response.isSuccessful()) {
            throw new ResponseException(new StringBuilder("Unsuccessful response (")
                    .append(code)
                    .append("): ")
                    .append(response)
                    .toString());
        }
    }

    private static Long extractRetryAfterHeaderValue(final Response response) {
        final String retryAfterHeaderValue = response.header(THROTTLED_RETRY_AFTER_HEADER);
        return StringUtils.isNotBlank(retryAfterHeaderValue)
                ? Long.valueOf(retryAfterHeaderValue)
                : DEFAULT_RETRY_AFTER_SECONDS;
    }
}
