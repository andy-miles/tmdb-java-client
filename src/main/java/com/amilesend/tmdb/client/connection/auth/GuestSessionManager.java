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

import com.amilesend.tmdb.client.api.AuthenticationApi;
import com.amilesend.tmdb.client.connection.ResponseException;
import com.amilesend.tmdb.client.model.auth.CreateGuestSessionResponse;
import com.amilesend.tmdb.client.model.auth.DeleteSessionRequest;
import com.amilesend.tmdb.client.model.auth.DeleteSessionResponse;
import com.google.common.annotations.VisibleForTesting;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class GuestSessionManager {
    private final AuthenticationApi authApi;
    private final AtomicReference<String> guestSessionId = new AtomicReference<>();

    @Builder
    private GuestSessionManager(
            @NonNull final AuthenticationApi authApi,
            final String guestSessionId) {
        this.authApi = authApi;
        this.guestSessionId.set(guestSessionId);
    }

    public String getGuestSessionId() {
        return guestSessionId.get();
    }

    @VisibleForTesting
    AtomicReference<String> getGuestSessionIdReference() {
        return guestSessionId;
    }

    /**
     * Authenticates with TMDB to allow for user grant approvals to access account information.
     *
     * @return the guest session identifier
     * @throws SessionException if an error occurred while creating the session
     */
    public String registerNewSession() throws SessionException {
        try {
            final CreateGuestSessionResponse response = authApi.createGuestSession();
            if (!response.getSuccess()) {
                throw new SessionException("User denied the authentication request");
            }

            guestSessionId.set(response.getGuestSessionId());
            return guestSessionId.get();
        } catch (final ResponseException ex) {
            throw new SessionException("Error creating guest session: " + ex.getMessage(), ex);
        }
    }

    /**
     * Deletes the created guest session.
     *
     * @throws SessionException if an error occurred while deleting the session
     */
    public void deleteSession() throws SessionException {
        final String sessionIdValue = guestSessionId.get();
        if (StringUtils.isBlank(sessionIdValue)) {
            return;
        }

        try {
            final DeleteSessionResponse response =
                    authApi.deleteSession(DeleteSessionRequest.builder().sessionId(sessionIdValue).build());
            if (!response.getSuccess()) {
                throw new SessionException("Failed to delete guest session");
            }

            guestSessionId.set(null);
        } catch (final ResponseException ex) {
            throw new SessionException("Error deleting guest session: " + ex.getMessage(), ex);
        }
    }
}
