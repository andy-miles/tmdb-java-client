/*
 * tmdb-java-client - A client to access the TMDB API
 * Copyright © 2024-2025 Andy Miles (andy.miles@amilesend.com)
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
package com.amilesend.tmdb.client.api;

import com.amilesend.client.connection.Connection;
import com.amilesend.client.parse.parser.BasicParser;
import com.amilesend.client.util.Validate;
import com.amilesend.tmdb.client.model.BodyBasedRequest;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import com.amilesend.tmdb.client.parse.GsonFactory;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.amilesend.client.connection.Connection.JSON_MEDIA_TYPE;

/**
 * The API Base class used to simplify the construction of API URLs for the {@link Connection}.
 *
 * @see Connection
 */
@RequiredArgsConstructor
public abstract class ApiBase {
    /** The connection that wraps the underlying HTTP client. */
    @NonNull
    @Getter
    private final Connection<GsonFactory> connection;

    /**
     * Executes a GET request for the given URL path and expected response type class.
     *
     * @param apiPath the full path URL
     * @param responseType the expected response type class
     * @return the deserialized response
     * @param <T> the response type
     */
    protected <T> T executeGet(final String apiPath, final Class<T> responseType) {
        return connection.execute(
                connection.newRequestBuilder()
                        .url(new StringBuilder(connection.getBaseUrl())
                                .append(apiPath)
                                .toString())
                        .build(),
                new BasicParser<>(responseType));
    }

    /**
     * Executes a GET request for the given URL path, request, and expected response type class.
     *
     * @param apiPath te full path URL
     * @param request the request
     * @param responseType the response type class
     * @return the deserialized response
     * @param <T> the response type
     */
    protected <T> T executeGet(
            final String apiPath,
            final QueryParameterBasedRequest request,
            final Class<T> responseType) {
        final HttpUrl.Builder urlBuilder = HttpUrl.parse(
                new StringBuilder(connection.getBaseUrl())
                        .append(apiPath)
                        .toString())
                .newBuilder();
        final HttpUrl url = request.populateQueryParameters(urlBuilder).build();

        return connection.execute(
                connection.newRequestBuilder()
                        .url(url)
                        .build(),
                new BasicParser<>(responseType));
    }

    /**
     * Executes a POST request for the given URL path, request, and expected response type class.
     *
     * @param apiPath te full path URL
     * @param request the request
     * @param responseType the response type class
     * @return the deserialized response
     * @param <T> the response type
     */
    protected <T> T executePost(
            final String apiPath,
            final QueryParameterBasedRequest request,
            final Class<T> responseType) {
        Validate.isTrue(request instanceof BodyBasedRequest, "Request must implement BodyBasedRequest");

        final String urlPath = new StringBuilder(connection.getBaseUrl())
                .append(apiPath)
                .toString();
        final HttpUrl.Builder urlBuilder = HttpUrl.parse(urlPath).newBuilder();
        final Request httpRequest = connection.newRequestBuilder()
                .url(request.populateQueryParameters(urlBuilder).build())
                .post(RequestBody.create(
                        connection.getGsonFactory().getInstance(connection).toJson(request),
                        JSON_MEDIA_TYPE))
                .build();
        return connection.execute(httpRequest, new BasicParser<>(responseType));
    }

    /**
     * Executes a DELETE request for the given URL path, request, and expected response type class.
     *
     * @param apiPath te full path URL
     * @param request the request
     * @param responseType the response type class
     * @return the deserialized response
     * @param <T> the response type
     */
    protected <T> T executeDelete(
            final String apiPath,
            final QueryParameterBasedRequest request,
            final Class<T> responseType) {
        final String urlPath = new StringBuilder(connection.getBaseUrl())
                .append(apiPath)
                .toString();
        final HttpUrl.Builder urlBuilder = HttpUrl.parse(urlPath).newBuilder();
        final HttpUrl httpUrl = request.populateQueryParameters(urlBuilder).build();

        final Request.Builder requestBuilder =  connection.newRequestBuilder().url(httpUrl);
        if (request instanceof BodyBasedRequest) {
            requestBuilder.delete(RequestBody.create(
                    connection.getGsonFactory().getInstance(connection).toJson(request),
                    JSON_MEDIA_TYPE));
        } else {
            requestBuilder.delete();
        }

        return connection.execute(requestBuilder.build(), new BasicParser<>(responseType));
    }
}
