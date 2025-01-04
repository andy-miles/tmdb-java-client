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
import com.sun.net.httpserver.HttpServer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
public class RequestTokenGrantReceiver implements AutoCloseable {
    private static final int DEFAULT_SYSTEM_BACKLOG = 0;
    private static final int MAX_PROTOCOL_STR_LENGTH = 10;

    private final Config config;

    @VisibleForTesting
    @Getter(value = AccessLevel.PACKAGE)
    private final RequestTokenGrantCallback callback;
    private HttpServer server;

    @Builder
    private RequestTokenGrantReceiver(@NonNull final Config config) {
        this.config = config;
        this.callback = RequestTokenGrantCallback.builder()
                .callbackPath(config.getCallbackPath())
                .successLandingPageUrl(config.getSuccessLandingPageUrl())
                .build();
    }

    /**
     * Closes the HTTP server resource.
     *
     * @throws SessionException if an error occurred while closing the HTTP server resource
     */
    @Override
    public void close() throws SessionException {
        stop();
    }

    /**
     * Open a browser at the given URL using {@link Desktop} if available, or alternatively output the
     * URL to {@link System#out} for command-line applications.
     *
     * @param url URL to browse
     */
    public static void browse(final String url) {
        Validate.notBlank(url, "url must not be blank");
        log.info("Opening browser to: {}", url);

        // Ask user to open in their browser using copy-paste
        System.out.println("Please open the following address in your browser: " + url);
        try {
            if (!Desktop.isDesktopSupported()) {
                return;
            }

            final Desktop desktop = Desktop.getDesktop();
            if (!desktop.isSupported(Desktop.Action.BROWSE)) {
                return;
            }

            System.out.println("Attempting to open that address in the default browser now...");
            desktop.browse(URI.create(url));
        } catch (IOException | InternalError ex) {
            log.warn("Unable to open browser", ex);
        }
    }

    /**
     * Starts the HTTP server to handle OAuth callbacks.
     *
     * @return this OAuthReceiver instance
     * @throws SessionException if an error occurred while starting the HTTP server
     */
    public <T> T start() throws SessionException {
        if (server != null) {
            return (T) this;
        }

        try {
            server = HttpServer.create(new InetSocketAddress(config.getPort()), DEFAULT_SYSTEM_BACKLOG);
            server.createContext(config.getCallbackPath(), callback);
            server.setExecutor(null);
            server.start();
            return (T) this;
        } catch (final Exception ex) {
            throw new SessionException("Error starting OAuthReceiver", ex);
        }
    }

    /**
     * Stops the running HTTP server.
     *
     * @throws SessionException if an error occurred while stopping the server
     */
    public void stop() throws SessionException {
        callback.releaseLock();
        if (server != null) {
            try {
                server.stop(0);
            } catch (final Exception ex) {
                throw new SessionException("Error stopping SessionManager", ex);
            }
            server = null;
        }
    }

    /**
     * Blocks until the server receives a request token grant approval, or when the server is stopped by
     * {@link #stop()}.
     *
     * @return authorization code if login succeeds; may return {@code null} if the server is stopped
     *     by {@link #close()}
     * @throws SessionException if an error occurred
     */
    public boolean waitForApprovalStatus() throws SessionException {
        return callback.waitForApprovalStatus();
    }

    @Data
    public static class Config {
        /** Max allowed port range for listening for the OAuth redirect. */
        @VisibleForTesting
        static final int MAX_PORT_RANGE = 65535;

        private static final String DEFAULT_PROTOCOL_PREFIX = "http://";
        private static final String DEFAULT_HOST = "localhost";
        private static final int DEFAULT_RECEIVER_PORT = 8895;
        private static final String DEFAULT_CALLBACK_PATH = "/Approved";

        private final String protocolPrefix;
        private final String host;
        private final String callbackPath;
        private final int port;
        private final String successLandingPageUrl;

        @Builder
        public Config(
                final String protocolPrefix,
                final String host,
                final String callbackPath,
                final Integer port,
                final String successLandingPageUrl) {
            this.protocolPrefix =
                    StringUtils.isNotBlank(protocolPrefix) && protocolPrefix.length() < MAX_PROTOCOL_STR_LENGTH
                            ? protocolPrefix
                            : DEFAULT_PROTOCOL_PREFIX;
            this.host = StringUtils.isNotBlank(host) ? URLEncoder.encode(host, StandardCharsets.UTF_8) : DEFAULT_HOST;
            this.callbackPath = StringUtils.isNotBlank(callbackPath)
                    ? URLEncoder.encode(callbackPath, StandardCharsets.UTF_8) : DEFAULT_CALLBACK_PATH;
            this.port = Optional.ofNullable(port).orElse(DEFAULT_RECEIVER_PORT);
            validatePort(this.port);
            this.successLandingPageUrl = successLandingPageUrl;
        }

        /**
         * Gets the redirect URL where the callback handler is listening on.
         *
         * @return the redirect URL
         */
        public String getRedirectUrl() {
            return new StringBuilder(protocolPrefix)
                    .append(host)
                    .append(":")
                    .append(port)
                    .append(callbackPath)
                    .toString();
        }

        private static void validatePort(final int port) {
            Validate.isTrue(port > 0 && port <= MAX_PORT_RANGE,
                    "Invalid port. Must be between 0 and " + MAX_PORT_RANGE);
        }
    }
}
