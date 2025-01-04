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

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.net.URI;

import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RequestTokenGrantCallbackTest {
    private static final String CALLBACK_PATH = "/CallbackPath";

    private RequestTokenGrantCallback callbackUnderTest = RequestTokenGrantCallback.builder()
            .callbackPath(CALLBACK_PATH)
            .build();

    @SneakyThrows
    @Test
    public void handle_withApprovalReturned_shouldSetFlag() {
        final HttpExchange mockHttpExchange = newMockHttpExchangeFor("approved=true", CALLBACK_PATH);

        callbackUnderTest.handle(mockHttpExchange);

        assertTrue(callbackUnderTest.waitForApprovalStatus());
    }

    @SneakyThrows
    @Test
    public void handle_withDenialReturned_shouldSetFlag() {
        final HttpExchange mockHttpExchange = newMockHttpExchangeFor("denied=true", CALLBACK_PATH);

        callbackUnderTest.handle(mockHttpExchange);

        assertFalse(callbackUnderTest.waitForApprovalStatus());
    }

    @SneakyThrows
    @Test
    public void handle_withNoStatusReturned_shouldSetFlag() {
        final HttpExchange mockHttpExchange = newMockHttpExchangeFor(null, CALLBACK_PATH);

        callbackUnderTest.handle(mockHttpExchange);

        assertFalse(callbackUnderTest.waitForApprovalStatus());
    }

    @SneakyThrows
    @Test
    public void handle_withApprovalAndSuccessLandingPageUrl_shouldSetResponseHeaders() {
        callbackUnderTest = RequestTokenGrantCallback.builder()
                .callbackPath(CALLBACK_PATH)
                .successLandingPageUrl("SuccessUrl")
                .build();
        final Headers mockHeaders = mock(Headers.class);
        final HttpExchange mockHttpExchange =
                newMockHttpExchangeFor("approved=true", CALLBACK_PATH, mockHeaders);

        callbackUnderTest.handle(mockHttpExchange);

        assertAll(
                () -> verify(mockHeaders).add(eq("Location"), eq("SuccessUrl")),
                () -> verify(mockHttpExchange).sendResponseHeaders(eq(HTTP_MOVED_TEMP), eq(-1L)));
    }

    @SneakyThrows
    @Test
    public void handle_withDenialAndFailureLandingPageUrl_shouldSetResponseHeaders() {
        callbackUnderTest = RequestTokenGrantCallback.builder()
                .callbackPath(CALLBACK_PATH)
                .failureLandingPageUrl("FailureUrl")
                .build();
        final Headers mockHeaders = mock(Headers.class);
        final HttpExchange mockHttpExchange =
                newMockHttpExchangeFor("denied=true", CALLBACK_PATH, mockHeaders);

        callbackUnderTest.handle(mockHttpExchange);

        assertAll(
                () -> verify(mockHeaders).add(eq("Location"), eq("FailureUrl")),
                () -> verify(mockHttpExchange).sendResponseHeaders(eq(HTTP_MOVED_TEMP), eq(-1L)));
    }

    @SneakyThrows
    @Test
    public void waitForApprovalStatus_withError_shouldThrowException() {
        // This should cause the handle to just return without setting the approval state flag
        final HttpExchange mockHttpExchange = newMockHttpExchangeFor(null, CALLBACK_PATH);
        when(mockHttpExchange.getRequestURI()).thenReturn(null);

        callbackUnderTest.handle(mockHttpExchange);

        assertThrows(SessionException.class, () -> callbackUnderTest.waitForApprovalStatus());
    }

    @SneakyThrows
    private HttpExchange newMockHttpExchangeFor(final String query, final String path) {
        return newMockHttpExchangeFor(query, path, mock(Headers.class));
    }

    @SneakyThrows
    private HttpExchange newMockHttpExchangeFor(
            final String query,
            final String path,
            final Headers mockResponseHeaders) {
        final URI mockUri = mock(URI.class);
        when(mockUri.getQuery()).thenReturn(query);
        when(mockUri.getPath()).thenReturn(path);
        final OutputStream mockResponseBody = mock(OutputStream.class);
        final HttpExchange mockHttpExchange = mock(HttpExchange.class);
        when(mockHttpExchange.getRequestURI()).thenReturn(mockUri);
        when(mockHttpExchange.getResponseHeaders()).thenReturn(mockResponseHeaders);
        lenient().when(mockHttpExchange.getResponseBody()).thenReturn(mockResponseBody);

        return mockHttpExchange;
    }
}
