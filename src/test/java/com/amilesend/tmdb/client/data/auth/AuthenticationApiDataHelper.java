/*
 * tmdb-java-client - A client to access the TMDB API
 * Copyright © 2024-2026 Andy Miles (andy.miles@amilesend.com)
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
package com.amilesend.tmdb.client.data.auth;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.auth.CreateGuestSessionResponse;
import com.amilesend.tmdb.client.model.auth.CreateRequestTokenResponse;
import com.amilesend.tmdb.client.model.auth.CreateSessionResponse;
import com.amilesend.tmdb.client.model.auth.DeleteSessionResponse;
import com.amilesend.tmdb.client.model.auth.ValidateKeyResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

import static com.amilesend.tmdb.client.FunctionalTestBase.SUCCESS_STATUS_CODE;

@UtilityClass
public class AuthenticationApiDataHelper {
    public static CreateGuestSessionResponse newCreateGuestSessionResponse() {
        return CreateGuestSessionResponse.builder()
                .guestSessionId("GuestSessionIdValue")
                .expiresAt(LocalDateTime.of(2030, 1, 15, 12, 0, 0))
                .success(true)
                .build();
    }

    public static CreateRequestTokenResponse newCreateRequestTokenResponse() {
        return CreateRequestTokenResponse.builder()
                .expiresAt(LocalDateTime.of(2030, 1, 15, 12, 0, 0))
                .requestToken("RequestTokenValue")
                .success(true)
                .build();
    }

    public static CreateSessionResponse newCreateSessionResponse() {
        return CreateSessionResponse.builder()
                .sessionId("SessionIdValue")
                .success(true)
                .build();
    }

    public static DeleteSessionResponse newDeleteSessionResponse() {
        return DeleteSessionResponse.builder()
                .success(true)
                .build();
    }

    public static ValidateKeyResponse newValidateKeyResponse() {
        return ValidateKeyResponse.builder()
                .statusCode(SUCCESS_STATUS_CODE)
                .statusMessage("Verified")
                .success(true)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/AuthenticationApi/";
        public static final SerializedResource CREATE_GUEST_SESSION_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "CreateGuestSessionResponse.json");
        public static final SerializedResource CREATE_REQUEST_TOKEN_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "CreateRequestTokenResponse.json");
        public static final SerializedResource CREATE_SESSION_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "CreateSessionResponse.json");
        public static final SerializedResource DELETE_SESSION_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "DeleteSessionResponse.json");
        public static final SerializedResource VALIDATE_KEY_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "ValidateKeyResponse.json");
    }
}
