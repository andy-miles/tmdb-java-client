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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.auth.CreateGuestSessionResponse;
import com.amilesend.tmdb.client.model.auth.CreateRequestTokenResponse;
import com.amilesend.tmdb.client.model.auth.CreateSessionRequest;
import com.amilesend.tmdb.client.model.auth.CreateSessionResponse;
import com.amilesend.tmdb.client.model.auth.DeleteSessionRequest;
import com.amilesend.tmdb.client.model.auth.DeleteSessionResponse;
import com.amilesend.tmdb.client.model.auth.ValidateKeyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.Responses.CREATE_GUEST_SESSION_RESPONSE;
import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.Responses.CREATE_REQUEST_TOKEN_RESPONSE;
import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.Responses.CREATE_SESSION_RESPONSE;
import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.Responses.DELETE_SESSION_RESPONSE;
import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.Responses.VALIDATE_KEY_RESPONSE;
import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.newCreateGuestSessionResponse;
import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.newCreateRequestTokenResponse;
import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.newCreateSessionResponse;
import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.newDeleteSessionResponse;
import static com.amilesend.tmdb.client.data.auth.AuthenticationApiDataHelper.newValidateKeyResponse;
import static org.junit.Assert.assertEquals;

public class AuthenticationApiFunctionalTest extends FunctionalTestBase {
    private AuthenticationApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getAuthenticationApi();
    }

    @Test
    public void createGuestSession_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, CREATE_GUEST_SESSION_RESPONSE);
        final CreateGuestSessionResponse expected = newCreateGuestSessionResponse();

        final CreateGuestSessionResponse actual =  apiUnderTest.createGuestSession();

        assertEquals(expected, actual);
    }

    @Test
    public void createRequestToken_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, CREATE_REQUEST_TOKEN_RESPONSE);
        final CreateRequestTokenResponse expected = newCreateRequestTokenResponse();

        final CreateRequestTokenResponse actual = apiUnderTest.createRequestToken();

        assertEquals(expected, actual);
    }

    @Test
    public void createSession_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, CREATE_SESSION_RESPONSE);
        final CreateSessionResponse expected = newCreateSessionResponse();

        final CreateSessionResponse actual = apiUnderTest.createSession(
                CreateSessionRequest.builder()
                        .requestToken("RequestTokenValue")
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void deleteSession_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, DELETE_SESSION_RESPONSE);
        final DeleteSessionResponse expected = newDeleteSessionResponse();

        final DeleteSessionResponse actual = apiUnderTest.deleteSession(
                DeleteSessionRequest.builder()
                        .sessionId("SessionIdValue")
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void validateKey_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, VALIDATE_KEY_RESPONSE);
        final ValidateKeyResponse expected = newValidateKeyResponse();

        final ValidateKeyResponse actual = apiUnderTest.validateKey();

        assertEquals(expected, actual);
    }
}
