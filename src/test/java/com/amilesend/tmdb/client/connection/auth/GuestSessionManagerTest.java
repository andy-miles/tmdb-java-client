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
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GuestSessionManagerTest {
    @Mock
    private AuthenticationApi mockAuthApi;

    private GuestSessionManager managerUnderTest;

    @BeforeEach
    public void setUp() {
        managerUnderTest = GuestSessionManager.builder()
                .authApi(mockAuthApi)
                .build();
    }

    ///////////////////////
    // registerNewSession
    ///////////////////////

    @SneakyThrows
    @Test
    public void registerNewSession_withSuccessAndNoException_shouldSetGuestSessionId() {
        final String expected = "Guest Session ID value";
        final CreateGuestSessionResponse response = CreateGuestSessionResponse.builder()
                .guestSessionId(expected)
                .expiresAt(LocalDateTime.now().plus(1, ChronoUnit.HOURS))
                .success(true)
                .build();
        when(mockAuthApi.createGuestSession()).thenReturn(response);

        final String actual = managerUnderTest.registerNewSession();

        assertEquals(expected, actual);
    }

    @Test
    public void registerNewSession_withNonSuccess_shouldThrowException() {
        final CreateGuestSessionResponse response = CreateGuestSessionResponse.builder()
                .success(false)
                .build();
        when(mockAuthApi.createGuestSession()).thenReturn(response);

        final Throwable actual = assertThrows(SessionException.class,
                () -> managerUnderTest.registerNewSession());

        assertEquals("User denied the authentication request", actual.getMessage());
    }

    @Test
    public void registerNewSession_withResponseExceptionThrown_shouldThrowException() {
        doThrow(new ResponseException("Exception")).when(mockAuthApi).createGuestSession();

        final Throwable thrown = assertThrows(SessionException.class,
                () -> managerUnderTest.registerNewSession());

        assertAll(
                () -> assertEquals("Error creating guest session: Exception", thrown.getMessage()),
                () -> assertInstanceOf(ResponseException.class, thrown.getCause()));
    }

    //////////////////
    // deleteSession
    //////////////////

    @SneakyThrows
    @Test
    public void deleteSession_withGuestSessionIdAndNoException_shouldClearGuestSessionId() {
        managerUnderTest.getGuestSessionIdReference().set("Expected Guest Session ID");
        final DeleteSessionResponse response = DeleteSessionResponse.builder().success(true).build();
        when(mockAuthApi.deleteSession(any(DeleteSessionRequest.class))).thenReturn(response);

        managerUnderTest.deleteSession();

        assertNull(managerUnderTest.getGuestSessionId());
    }

    @Test
    public void deleteSession_withGuestSessionIdAndNonSuccess_shouldThrowException() {
        managerUnderTest.getGuestSessionIdReference().set("Expected Guest Session ID");
        final DeleteSessionResponse response = DeleteSessionResponse.builder().success(false).build();
        when(mockAuthApi.deleteSession(any(DeleteSessionRequest.class))).thenReturn(response);

        assertThrows(SessionException.class, () -> managerUnderTest.deleteSession());
    }

    @Test
    public void deleteSession_withResponseException_shouldThrowException() {
        managerUnderTest.getGuestSessionIdReference().set("Expected Guest Session ID");
        when(mockAuthApi.deleteSession(any(DeleteSessionRequest.class))).thenThrow(new ResponseException("Exception"));

        final Throwable thrown = assertThrows(SessionException.class, () -> managerUnderTest.deleteSession());

        assertAll(
                () -> assertEquals("Error deleting guest session: Exception", thrown.getMessage()),
                () -> assertInstanceOf(ResponseException.class, thrown.getCause()));
    }

    @SneakyThrows
    @Test
    public void deleteSession_withNoExistingSession_shouldDoNothing() {
        managerUnderTest.deleteSession();
        verifyNoInteractions(mockAuthApi);
    }
}
