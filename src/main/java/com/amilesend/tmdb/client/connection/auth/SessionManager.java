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

import com.amilesend.client.connection.ResponseException;
import com.amilesend.client.util.StringUtils;
import com.amilesend.client.util.Validate;
import com.amilesend.client.util.VisibleForTesting;
import com.amilesend.tmdb.client.api.AuthenticationApi;
import com.amilesend.tmdb.client.model.auth.CreateSessionRequest;
import com.amilesend.tmdb.client.model.auth.DeleteSessionRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import static com.amilesend.tmdb.client.connection.auth.RequestTokenGrantReceiver.browse;

/** Creates and stores a user session in-memory. */
@Slf4j
public class SessionManager {
    private final AuthenticationApi authApi;
    private final RequestTokenGrantReceiver.Config config;
    @VisibleForTesting
    @Getter(AccessLevel.PACKAGE)
    private final AtomicReference<String> sessionId = new AtomicReference<>();

    @Builder
    private SessionManager(
            @NonNull final AuthenticationApi authApi,
            @NonNull final RequestTokenGrantReceiver.Config config,
            final String sessionId) {
        this.authApi = authApi;
        this.config = config;
        this.sessionId.set(sessionId);
    }

    /**
     * Authenticates with TMDB to allow for user grant approvals to access account information.
     *
     * @return the session identifier
     * @throws SessionException if an error occurred while creating the session
     */
    public String registerNewSession() throws SessionException {
        final String requestToken = newRequestToken();

        try (final RequestTokenGrantReceiver receiver =
                     RequestTokenGrantReceiver.builder()
                             .config(config)
                             .build()
                             .start()) {
            browse(toBrowseUrl(requestToken));
            final boolean status = receiver.waitForApprovalStatus();
            if (!status) {
                throw new SessionException("User denied the authentication request");
            }

            sessionId.set(newSessionId(requestToken));
            return sessionId.get();
        }
    }

    /**
     * Deletes the created session.
     *
     * @throws SessionException if an error occurred while deleting the session
     */
    public void deleteSession() throws SessionException {
        final String sessionIdValue = sessionId.get();
        if (StringUtils.isBlank(sessionIdValue)) {
            return;
        }

        try {
            authApi.deleteSession(DeleteSessionRequest.builder().sessionId(sessionIdValue).build());
            sessionId.set(null);
        } catch (final ResponseException ex) {
            throw new SessionException("Error deleting session: " + ex.getMessage(), ex);
        }
    }

    @VisibleForTesting
    String newRequestToken() throws SessionException {
        log.debug("Requesting new token...");
        try {
            return authApi.createRequestToken().getRequestToken();
        } catch (final ResponseException ex) {
            throw new SessionException("Error creating new request token: " + ex.getMessage(), ex);
        }
    }

    @VisibleForTesting
    String newSessionId(final String requestToken) throws SessionException {
        log.debug("Request sessionId with RequestToken {}", requestToken);
        try {
            return authApi.createSession(CreateSessionRequest
                    .builder()
                    .requestToken(requestToken).build())
                    .getSessionId();
        } catch (final ResponseException ex) {
            throw new SessionException("Error creating new session: " + ex.getMessage(), ex);
        }
    }

    private String toBrowseUrl(final String requestToken) {
        Validate.notBlank(requestToken, "requestToken must not be blank");

        return new StringBuilder("https://www.themoviedb.org/authenticate/")
                .append(URLEncoder.encode(requestToken, StandardCharsets.UTF_8))
                .append("?redirect_to=")
                .append(config.getRedirectUrl())
                .toString();
    }
}
