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
package com.amilesend.tmdb.client.connection.auth;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.io.CharStreams;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Callback to handle approval or denial responses upon requesting a token used to create a user session.
 *
 * @see SessionManager
 */
@Slf4j
public class RequestTokenGrantCallback implements HttpHandler {
    private static final String LANDING_HTML_RESOURCE = "/SuccessLanding.html";
    private static final String FAILURE_HTML_RESOURCE = "/FailureLanding.html";
    private static final String LANDING_HTML_FALLBACK = new StringBuilder("<html>")
            .append("<head><title>Session Grant Received</title></head>")
            .append("<body>")
            .append("Received response. You may now close this window.")
            .append("</body>")
            .append("</html>\n")
            .toString();

    private final String callbackPath;
    private final String successLandingPageUrl;
    private final String failureLandingPageUrl;
    /** To block until receiving an authorization response or stop() is called. */
    private final Semaphore waitUnlessSignaled = new Semaphore(0 /* initially zero permit */);

    /**
     * The URL to proceed with session registration. When not {@code null}, this implies that the request token
     * grant was approved.
     */
    @VisibleForTesting // The Setter
    @Setter(value = AccessLevel.PACKAGE)
    @Getter
    private Boolean isApproved;

    @Builder
    public RequestTokenGrantCallback(
            final String callbackPath,
            final String successLandingPageUrl,
            final String failureLandingPageUrl) {
        Validate.notBlank(callbackPath, "callbackPath must not be blank");

        this.callbackPath = callbackPath;
        this.successLandingPageUrl = successLandingPageUrl;
        this.failureLandingPageUrl = failureLandingPageUrl;
    }

    @Override
    public void handle(@NonNull final HttpExchange httpExchange) throws IOException {
        final String requestUriPath = Optional.ofNullable(httpExchange.getRequestURI())
                .map(URI::getPath)
                .orElse(StringUtils.EMPTY);
        try {
            if (!callbackPath.equals(requestUriPath)) {
                return;
            }

            final Map<String, String> params = queryToMap(httpExchange.getRequestURI().getQuery());
            final boolean isApprovedResponse = Optional.ofNullable(params.get("approved"))
                    .map(Boolean::parseBoolean)
                    .orElse(false);
            final boolean isDeniedResponse = Optional.ofNullable(params.get("denied"))
                    .map(Boolean::parseBoolean)
                    .orElse(false);
            isApproved = isApprovedResponse && !isDeniedResponse;

            final Headers respHeaders = httpExchange.getResponseHeaders();
            if (isApprovedResponse && StringUtils.isNotBlank(successLandingPageUrl)) {
                respHeaders.add("Location", successLandingPageUrl);
                httpExchange.sendResponseHeaders(HTTP_MOVED_TEMP, -1);
            } else if (isDeniedResponse && StringUtils.isNotBlank(failureLandingPageUrl)) {
                respHeaders.add("Location", failureLandingPageUrl);
                httpExchange.sendResponseHeaders(HTTP_MOVED_TEMP, -1);
            } else {
                final String resourcePath = isApprovedResponse ? LANDING_HTML_RESOURCE : FAILURE_HTML_RESOURCE;
                writeLandingHtml(resourcePath, httpExchange, respHeaders);
            }
        } finally {
            httpExchange.close();
            releaseLock();
        }
    }

    /**
     * Blocks until the server receives a request token auth grant approval, or when the lock is explicitly
     * released (e.g., shutdown).
     *
     * @return the authorization callback url used to request a new session.
     * @throws SessionException if no response was ever received from TMDB
     */
    public boolean waitForApprovalStatus() throws SessionException {
        waitUnlessSignaled.acquireUninterruptibly();

        if (isApproved == null) {
            throw new SessionException("No auth callback received");
        }

        return isApproved;
    }

    /** Releases the lock. */
    public void releaseLock() {
        waitUnlessSignaled.release();
    }

    private static Map<String, String> queryToMap(final String query) {
        if (StringUtils.isBlank(query)) {
            return Collections.emptyMap();
        }

        return Arrays.stream(query.split("&"))
                .filter(StringUtils::isNotBlank)
                .map(kv -> {
                    if (!kv.contains("=")){
                        return Pair.of(kv, StringUtils.EMPTY);
                    }

                    final String pair[] = kv.split("=");
                    if (pair.length != 2 && pair.length > 0) {
                        return Pair.of(pair[0], StringUtils.EMPTY);
                    }

                    return Pair.of(pair[0], pair[1]);
                })
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    private static void writeLandingHtml(
            final String resourcePath,
            final HttpExchange exchange,
            final Headers headers) throws IOException {
        try (final OutputStream os = exchange.getResponseBody();
             final OutputStreamWriter doc = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            exchange.sendResponseHeaders(HTTP_OK, 0);
            headers.add("ContentType", "text/html");
            doc.write(getLandingHtml(resourcePath));
            doc.flush();
        }
    }

    private static String getLandingHtml(final String resourcePath) {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader
                (RequestTokenGrantCallback.class.getResourceAsStream(resourcePath), StandardCharsets.UTF_8))) {
            return CharStreams.toString(reader);
        } catch (final IOException ex) {
            log.warn("Error trying to load resource: {}", resourcePath);
            return LANDING_HTML_FALLBACK;
        }
    }
}
