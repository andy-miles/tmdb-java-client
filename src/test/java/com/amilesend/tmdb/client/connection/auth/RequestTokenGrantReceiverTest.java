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

import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestTokenGrantReceiverTest {
    private static final int PORT = 9001;

    @Mock
    private HttpServer mockServer;
    @Mock
    private RequestTokenGrantCallback mockCallback;
    private MockedStatic<RequestTokenGrantCallback> callbackMockedStatic;
    private RequestTokenGrantReceiver receiverUnderTest;

    @BeforeEach
    public void setUp() {
        callbackMockedStatic = mockStatic(RequestTokenGrantCallback.class);
        final RequestTokenGrantCallback.RequestTokenGrantCallbackBuilder mockBuilder = mockCallbackBuilder();
        callbackMockedStatic.when(RequestTokenGrantCallback::builder).thenReturn(mockBuilder);

        final RequestTokenGrantReceiver.Config config = RequestTokenGrantReceiver.Config.builder()
                .callbackPath("/CallbackPath")
                .port(PORT)
                .build();

        receiverUnderTest = spy(RequestTokenGrantReceiver.builder()
                .config(config)
                .build());
    }

    private RequestTokenGrantCallback.RequestTokenGrantCallbackBuilder mockCallbackBuilder() {
        final RequestTokenGrantCallback.RequestTokenGrantCallbackBuilder mockBuilder =
                mock(RequestTokenGrantCallback.RequestTokenGrantCallbackBuilder.class);
        when(mockBuilder.callbackPath(anyString())).thenReturn(mockBuilder);
        when(mockBuilder.successLandingPageUrl(any())).thenReturn(mockBuilder);
        when(mockBuilder.build()).thenReturn(mockCallback);
        return mockBuilder;
    }

    @AfterEach
    public void cleanUp() {
        callbackMockedStatic.close();
    }

    //////////
    // start
    //////////

    @SneakyThrows
    @Test
    public void start_withValidConfig_shouldStartServer() {
        try (final MockedStatic<HttpServer> mockServerStatic = mockStatic(HttpServer.class)) {
            mockServerStatic.when(() -> HttpServer.create(any(InetSocketAddress.class), anyInt()))
                    .thenReturn(mockServer);

            receiverUnderTest.start();

            verify(mockServer).start();
        }
    }

    @SneakyThrows
    @Test
    public void start_withMultipleInvocations_shouldOnlyStartServiceOnce() {
        try (final MockedStatic<HttpServer> mockServerStatic = mockStatic(HttpServer.class)) {
            mockServerStatic.when(() -> HttpServer.create(any(InetSocketAddress.class), anyInt()))
                    .thenReturn(mockServer);

            receiverUnderTest.start();
            receiverUnderTest.start();

            verify(mockServer).start();
        }
    }

    @SneakyThrows
    @Test
    public void start_withIOException_shouldThrowException() {
        try (final MockedStatic<HttpServer> mockServerStatic = mockStatic(HttpServer.class)) {
            mockServerStatic.when(() -> HttpServer.create(any(InetSocketAddress.class), anyInt()))
                    .thenReturn(mockServer);
            doThrow(new IllegalStateException("Exception")).when(mockServer).start();

            assertThrows(SessionException.class, () -> receiverUnderTest.start());
        }
    }

    /////////
    // stop
    /////////

    @SneakyThrows
    @Test
    public void stop_withStartedServer_shouldStopServer() {
        try (final MockedStatic<HttpServer> mockServerStatic = mockStatic(HttpServer.class)) {
            mockServerStatic.when(() -> HttpServer.create(any(InetSocketAddress.class), anyInt()))
                    .thenReturn(mockServer);
            receiverUnderTest.start();

            receiverUnderTest.stop();

            verify(mockServer).stop(anyInt());
        }
    }

    @SneakyThrows
    @Test
    public void stop_withNoServerStarted_shouldNotStopServer() {
        try (final MockedStatic<HttpServer> mockServerStatic = mockStatic(HttpServer.class)) {
            mockServerStatic.when(() -> HttpServer.create(any(InetSocketAddress.class), anyInt()))
                    .thenReturn(mockServer);

            receiverUnderTest.stop();

            verify(mockServer, never()).stop(anyInt());
        }
    }

    @SneakyThrows
    @Test
    public void stop_withExceptionThrown_shouldThrowException() {
        try (final MockedStatic<HttpServer> mockServerStatic = mockStatic(HttpServer.class)) {
            mockServerStatic.when(() -> HttpServer.create(any(InetSocketAddress.class), anyInt()))
                    .thenReturn(mockServer);
            doThrow(new IllegalStateException("Exception")).when(mockServer).stop(anyInt());
            receiverUnderTest.start();

            assertThrows(SessionException.class, () -> receiverUnderTest.stop());
        }
    }

    //////////
    // close
    //////////

    @SneakyThrows
    @Test
    public void close_withNoException_shouldInvokeStop() {
        doNothing().when(receiverUnderTest).stop();

        receiverUnderTest.close();

        verify(receiverUnderTest).stop();
    }

    @SneakyThrows
    @Test
    public void close_whenExceptionThrown_shouldThrowException() {
        doThrow(new SessionException("Exception")).when(receiverUnderTest).stop();

        assertThrows(SessionException.class, () -> receiverUnderTest.close());
    }

    //////////////////////////
    // waitForApprovalStatus
    //////////////////////////

    @SneakyThrows
    @Test
    public void waitForApprovalStatus_withApprovalSet_shouldReturnTrue() {
        when(mockCallback.waitForApprovalStatus()).thenReturn(Boolean.TRUE);
        assertTrue(receiverUnderTest.waitForApprovalStatus());
    }

    @SneakyThrows
    @Test
    public void waitForCode_withSessionException_shouldThrowException() {
        when(mockCallback.waitForApprovalStatus()).thenThrow(new SessionException("Exception"));
        assertThrows(SessionException.class, () -> receiverUnderTest.waitForApprovalStatus());
    }
}
