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
package com.amilesend.tmdb.client.connection;

import com.amilesend.tmdb.client.model.acount.GetAccountDetailsResponse;
import com.amilesend.tmdb.client.parse.GsonFactory;
import com.amilesend.tmdb.client.parse.parser.GsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import static com.google.common.net.HttpHeaders.ACCEPT;
import static com.google.common.net.HttpHeaders.USER_AGENT;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConnectionTest {
    private static final String REQUEST_URL = "http://localhost";
    private static final String READ_ACCESS_TOKEN = "ReadAccessToken";
    private static final int SUCCESS_RESPONSE_CODE = 200;
    private static final int REQUEST_ERROR_CODE = 403;
    private static final int THROTTLED_ERROR_CODE = 429;
    private static final int SERVER_ERROR_RESPONSE_CODE = 503;

    @Mock
    protected OkHttpClient mockHttpClient;
    @Mock
    protected Gson mockGson;
    @Mock
    protected GsonFactory mockGsonFactory;
    protected Connection connectionUnderTest;

    @BeforeEach
    public void setUp() {
        when(mockGsonFactory.newInstanceForConnection()).thenReturn(mockGson);
        connectionUnderTest =
                spy(Connection.builder()
                        .httpClient(mockHttpClient)
                        .gsonFactory(mockGsonFactory)
                        .baseUrl(REQUEST_URL)
                        .readAccessToken(READ_ACCESS_TOKEN)
                        .build());

    }

    @Test
    public void ctor_withInvalidParameters_shouldThrowException() {
        final String baseUrl = "https://someurl.com";
        final String readAccessToken = "token";

        assertAll(
                () -> assertThrows(NullPointerException.class, () -> new Connection(
                        null,
                        mockGsonFactory,
                        baseUrl,
                        readAccessToken)),
                () -> assertThrows(NullPointerException.class, () -> new Connection(
                        mockHttpClient,
                        null,
                        baseUrl,
                        readAccessToken)),
                () -> assertThrows(NullPointerException.class, () -> new Connection(
                        mockHttpClient,
                        GsonFactory.getInstance(),
                        baseUrl,
                        null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Connection(
                        mockHttpClient,
                        GsonFactory.getInstance(),
                        baseUrl,
                        StringUtils.EMPTY)));
    }

    @Test
    public void ctor_withBaseUrl_shouldUseConfiguredUrl() {
        final Connection connection = new Connection(
                mockHttpClient,
                mockGsonFactory,
                "http://localhost",
                "token");
        assertEquals("http://localhost", connection.getBaseUrl());
    }

    @Test
    public void newRequestBuilder_shouldReturnBuilderWithHeadersDefined() {
        final Request actual = connectionUnderTest.newRequestBuilder().url(REQUEST_URL).build();

        assertAll(
                () -> assertEquals("TMDBJavaClient/1.0", actual.header(USER_AGENT)),
                () -> assertEquals(Connection.JSON_CONTENT_TYPE, actual.header(ACCEPT)));
    }

    ////////////
    // Execute
    ////////////

    @Test
    public void execute_withValidRequestAndResponse_shouldReturnParsedObject() {
        final GetAccountDetailsResponse mockAccountDetails = mock(GetAccountDetailsResponse.class);
        final GsonParser<GetAccountDetailsResponse> mockParser = mock(GsonParser.class);
        when(mockParser.parse(any(Gson.class), any(InputStream.class))).thenReturn(mockAccountDetails);
        setUpHttpClientMock(newMockedResponse(SUCCESS_RESPONSE_CODE));

        final GetAccountDetailsResponse actual = connectionUnderTest.execute(mock(Request.class), mockParser);

        assertAll(
                () -> assertEquals(mockAccountDetails, actual),
                () -> verify(mockParser).parse(isA(Gson.class), isA(InputStream.class)));
    }

    @Test
    public void execute_withThrottledResponse_shouldThrowException() {
        final long expected = 60L;
        setUpHttpClientMock(newMockedResponse(THROTTLED_ERROR_CODE, expected));

        final Throwable thrown = assertThrows(ThrottledException.class,
                () -> connectionUnderTest.execute(mock(Request.class), mock(GsonParser.class)));

        assertEquals(expected, ((ThrottledException) thrown).getRetryAfterSeconds());
    }

    @Test
    public void execute_withThrottledResponseAndNullRetryAfterHeader_shouldThrowException() {
        setUpHttpClientMock(newMockedResponse(THROTTLED_ERROR_CODE, (Long) null));

        final Throwable thrown = assertThrows(ThrottledException.class,
                () -> connectionUnderTest.execute(mock(Request.class), mock(GsonParser.class)));

        assertEquals(10L, ((ThrottledException) thrown).getRetryAfterSeconds());
    }

    @Test
    public void execute_withParseException_shouldThrowException() {
        final GsonParser<GetAccountDetailsResponse> mockParser = mock(GsonParser.class);
        when(mockParser.parse(any(Gson.class), any(InputStream.class))).thenThrow(new JsonParseException("Exception"));
        setUpHttpClientMock(newMockedResponse(SUCCESS_RESPONSE_CODE));

        try (final MockedConstruction<GZIPInputStream> streamCons = mockConstruction(GZIPInputStream.class)) {
            final Throwable thrown = assertThrows(ResponseParseException.class,
                    () -> connectionUnderTest.execute(mock(Request.class), mockParser));
            assertInstanceOf(JsonParseException.class, thrown.getCause());
        }
    }

    @Test
    public void execute_withServerErrorResponseCode_shouldThrowException() {
        setUpHttpClientMock(newMockedResponse(SERVER_ERROR_RESPONSE_CODE));

        assertThrows(ResponseException.class,
                () -> connectionUnderTest.execute(mock(Request.class), mock(GsonParser.class)));
    }

    @Test
    public void execute_withRequestErrorResponseCode_shouldThrowException() {
        setUpHttpClientMock(newMockedResponse(REQUEST_ERROR_CODE));

        assertThrows(RequestException.class,
                () -> connectionUnderTest.execute(mock(Request.class), mock(GsonParser.class)));
    }

    @Test
    public void execute_withIOException_shouldThrowException() {
        setUpHttpClientMock(new IOException("Exception"));

        final Throwable thrown = assertThrows(RequestException.class,
                () -> connectionUnderTest.execute(mock(Request.class), mock(GsonParser.class)));

        assertInstanceOf(IOException.class, thrown.getCause());
    }

    @Test
    public void execute_withInvalidParameters_shouldThrowException() {
        assertAll(
                () -> assertThrows(NullPointerException.class,
                        () -> connectionUnderTest.execute(null, mock(GsonParser.class))),
                () -> assertThrows(NullPointerException.class,
                        () -> connectionUnderTest.execute(mock(Request.class), null)));
    }

    private Response newMockedResponse(final int code) {
        final ResponseBody mockBody = mock(ResponseBody.class);
        lenient().when(mockBody.byteStream()).thenReturn(mock(InputStream.class));
        lenient().when(mockBody.source()).thenReturn(mock(BufferedSource.class));

        final Response mockResponse = mock(Response.class);
        lenient().when(mockResponse.code()).thenReturn(code);
        lenient().when(mockResponse.isSuccessful()).thenReturn(code == SUCCESS_RESPONSE_CODE);
        lenient().when(mockResponse.body()).thenReturn(mockBody);

        return mockResponse;
    }

    private Response newMockedResponse(final int code, final Long retryAfterSeconds) {
        final Response mockResponse = mock(Response.class);
        when(mockResponse.code()).thenReturn(code);
        lenient().when(mockResponse.isSuccessful()).thenReturn(String.valueOf(code).startsWith("2"));
        if (retryAfterSeconds != null) {
            lenient().when(mockResponse.header(eq("Retry-After"))).thenReturn(String.valueOf(retryAfterSeconds));
        }

        return mockResponse;
    }

    @SneakyThrows
    protected Response setUpHttpClientMock(final Response mockResponse) {
        final Call mockCall = mock(Call.class);
        when(mockCall.execute()).thenReturn(mockResponse);
        when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);

        return mockResponse;
    }

    @SneakyThrows
    private void setUpHttpClientMock(final IOException ioException) {
        final Call mockCall = mock(Call.class);
        when(mockCall.execute()).thenThrow(ioException);
        when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);
    }
}
