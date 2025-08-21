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
import com.amilesend.client.util.StringUtils;
import com.amilesend.tmdb.client.model.auth.CreateGuestSessionResponse;
import com.amilesend.tmdb.client.model.auth.CreateRequestTokenResponse;
import com.amilesend.tmdb.client.model.auth.CreateSessionRequest;
import com.amilesend.tmdb.client.model.auth.CreateSessionResponse;
import com.amilesend.tmdb.client.model.auth.DeleteSessionRequest;
import com.amilesend.tmdb.client.model.auth.DeleteSessionResponse;
import com.amilesend.tmdb.client.model.auth.ValidateKeyResponse;
import lombok.NonNull;

/** TMDB Authentication API. */
public class AuthenticationApi extends ApiBase {
    private static final String API_PATH = "/authentication";

    /**
     * Creates a new {@code AuthenticationApi} object.
     *
     * @param connection the underlying connection
     */
    public AuthenticationApi(final Connection connection) {
        super(connection);
    }

    /**
     * Creates a new guest session to allow for limited access to a user's account.
     * More information: <a href="https://developer.themoviedb.org/reference/authentication-create-guest-session">
     * https://developer.themoviedb.org/reference/authentication-create-guest-session</a>
     *
     * @return the response containing the guest session identifier.
     * @see CreateGuestSessionResponse
     */
    public CreateGuestSessionResponse createGuestSession() {
        return executeGet(getApiPath("/guest_session/new"), CreateGuestSessionResponse.class);
    }

    /**
     * Creates a new request token to validate the TMDB user login.
     *
     * @return the response containing the request token
     * @see CreateRequestTokenResponse
     */
    public CreateRequestTokenResponse createRequestToken() {
        return executeGet(getApiPath("/token/new"), CreateRequestTokenResponse.class);
    }

    /**
     * Creates a new user session that grants access to write user data.
     *
     * @param request the request containing the request token
     * @return the response containing the secret session identifier
     * @see CreateSessionRequest
     * @see CreateSessionResponse
     */
    public CreateSessionResponse createSession(@NonNull final CreateSessionRequest request) {
        return executePost(getApiPath("/session/new"), request, CreateSessionResponse.class);
    }

    /**
     * Deletes a session.
     *
     * @param request the request containing the session identifier
     * @return the response
     * @see DeleteSessionRequest
     * @see DeleteSessionResponse
     */
    public DeleteSessionResponse deleteSession(@NonNull final DeleteSessionRequest request) {
        return executeDelete(getApiPath("/session"), request, DeleteSessionResponse.class);
    }

    /**
     * Validates the application's access permissions (i.e., the read access token).
     *
     * @return the response
     * @see ValidateKeyResponse
     */
    public ValidateKeyResponse validateKey() {
        return executeGet(StringUtils.EMPTY, ValidateKeyResponse.class);
    }

    private static String getApiPath(final String subApiPath) {
        return API_PATH + subApiPath;
    }
}
