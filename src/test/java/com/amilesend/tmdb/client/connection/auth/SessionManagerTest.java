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
package com.amilesend.tmdb.client.connection.auth;

import com.amilesend.client.connection.ResponseException;
import com.amilesend.tmdb.client.api.AuthenticationApi;
import com.amilesend.tmdb.client.model.auth.CreateRequestTokenResponse;
import com.amilesend.tmdb.client.model.auth.CreateSessionRequest;
import com.amilesend.tmdb.client.model.auth.CreateSessionResponse;
import com.amilesend.tmdb.client.model.auth.DeleteSessionRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionManagerTest {
    @Mock
    private AuthenticationApi mockAuthApi;
    @Mock
    private RequestTokenGrantReceiver mockGrantReceiver;

    private SessionManager managerUnderTest;

    @BeforeEach
    public void setUp() {
        managerUnderTest = SessionManager.builder()
                .authApi(mockAuthApi)
                .config(RequestTokenGrantReceiver.Config.builder()
                        .callbackPath("/Callback")
                        .build())
                .build();
    }

    @Test
    public void ctor_withInvalidParameters_shouldThrowException() {
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> SessionManager.builder()
                        .authApi(null)
                        .config(RequestTokenGrantReceiver.Config.builder()
                                .callbackPath("/Callback")
                                .build())
                        .build()),
                () -> assertThrows(NullPointerException.class, () -> SessionManager.builder()
                        .authApi(mockAuthApi)
                        .config(null)
                        .build()));
    }

    ///////////////////////
    // registerNewSession
    ///////////////////////

    @Test
    @SneakyThrows
    public void registerNewSession_withApproval_shouldReturnSessionId() {
        final String expected = "SessionIdValue";
        when(mockAuthApi.createRequestToken())
                .thenReturn(CreateRequestTokenResponse.builder()
                        .requestToken("RequestTokenValue")
                        .build());
        when(mockAuthApi.createSession(any(CreateSessionRequest.class)))
                .thenReturn(CreateSessionResponse.builder()
                        .sessionId(expected)
                        .build());
        when(mockGrantReceiver.waitForApprovalStatus()).thenReturn(true);
        final RequestTokenGrantReceiver.RequestTokenGrantReceiverBuilder mockBuilder = setUpMockBuilder();

        try (final MockedStatic<RequestTokenGrantReceiver> grantReceiverMockedStatic =
                     mockStatic(RequestTokenGrantReceiver.class)) {
            grantReceiverMockedStatic.when(RequestTokenGrantReceiver::builder).thenReturn(mockBuilder);

            final String actual = managerUnderTest.registerNewSession();

            assertAll(
                    () -> assertEquals(expected, actual),
                    () -> assertEquals(expected, managerUnderTest.getSessionId().get()));
        }
    }

    @Test
    @SneakyThrows
    public void registerNewSession_withDenial_shouldThrowException() {
        when(mockAuthApi.createRequestToken())
                .thenReturn(CreateRequestTokenResponse.builder()
                        .requestToken("RequestTokenValue")
                        .build());
        when(mockGrantReceiver.waitForApprovalStatus()).thenReturn(false); // <--
        final RequestTokenGrantReceiver.RequestTokenGrantReceiverBuilder mockBuilder = setUpMockBuilder();

        try (final MockedStatic<RequestTokenGrantReceiver> grantReceiverMockedStatic =
                     mockStatic(RequestTokenGrantReceiver.class)) {
            grantReceiverMockedStatic.when(RequestTokenGrantReceiver::builder).thenReturn(mockBuilder);

            assertThrows(SessionException.class, () -> managerUnderTest.registerNewSession());
        }
    }

    @SneakyThrows
    private RequestTokenGrantReceiver.RequestTokenGrantReceiverBuilder setUpMockBuilder() {
        final RequestTokenGrantReceiver.RequestTokenGrantReceiverBuilder mockBuilder =
                mock(RequestTokenGrantReceiver.RequestTokenGrantReceiverBuilder.class);
        when(mockBuilder.config(any(RequestTokenGrantReceiver.Config.class))).thenReturn(mockBuilder);
        when(mockBuilder.build()).thenReturn(mockGrantReceiver);
        when(mockGrantReceiver.start()).thenReturn(mockGrantReceiver);

        return mockBuilder;
    }

    //////////////////
    // deleteSession
    //////////////////

    @Test
    @SneakyThrows
    public void deleteSession_withBlankSessionId_shouldNotDeleteSession() {
        managerUnderTest.deleteSession();
        verifyNoInteractions(mockAuthApi);
    }

    @Test
    @SneakyThrows
    public void deleteSession_withSessionId_shouldDeleteSession() {
        final String expected = "SessionIdValue";
        managerUnderTest.getSessionId().set(expected);

        managerUnderTest.deleteSession();

        final ArgumentCaptor<DeleteSessionRequest> requestCaptor = ArgumentCaptor.forClass(DeleteSessionRequest.class);
        verify(mockAuthApi).deleteSession(requestCaptor.capture());
        final DeleteSessionRequest request = requestCaptor.getValue();
        assertEquals(expected, request.getSessionId());
    }

    @Test
    @SneakyThrows
    public void deleteSession_withResponseException_shouldThrowException() {
        managerUnderTest.getSessionId().set("SessionIdValue");
        doThrow(new ResponseException("Exception")).when(mockAuthApi).deleteSession(any(DeleteSessionRequest.class));

        final Throwable thrown = assertThrows(SessionException.class, () -> managerUnderTest.deleteSession());
        assertInstanceOf(ResponseException.class, thrown.getCause());
    }

    ////////////////////
    // newRequestToken
    ////////////////////

    @Test
    @SneakyThrows
    public void newRequestToken_withResponseException_shouldThrowException() {
        doThrow(new ResponseException("Exception")).when(mockAuthApi).createRequestToken();

        final Throwable thrown = assertThrows(SessionException.class, () -> managerUnderTest.newRequestToken());
        assertInstanceOf(ResponseException.class, thrown.getCause());
    }

    /////////////////
    // newSessionId
    /////////////////

    @Test
    @SneakyThrows
    public void newSessionId_withResponseException_shouldThrowException() {
        doThrow(new ResponseException("Exception")).when(mockAuthApi).createSession(any(CreateSessionRequest.class));

        final Throwable thrown = assertThrows(
                SessionException.class,
                () -> managerUnderTest.newSessionId("RequestTokenValue"));
        assertInstanceOf(ResponseException.class, thrown.getCause());

    }
}
