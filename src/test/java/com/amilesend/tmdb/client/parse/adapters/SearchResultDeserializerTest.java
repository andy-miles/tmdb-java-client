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
package com.amilesend.tmdb.client.parse.adapters;

import com.amilesend.tmdb.client.model.search.type.MediaType;
import com.amilesend.tmdb.client.model.search.type.MovieSearchResult;
import com.amilesend.tmdb.client.model.search.type.PersonSearchResult;
import com.amilesend.tmdb.client.model.search.type.SearchResult;
import com.amilesend.tmdb.client.model.search.type.TvSeriesSearchResult;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Type;

import static com.amilesend.tmdb.client.parse.adapters.PersonCreditDeserializerTest.setUpNewJsonElement;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchResultDeserializerTest {
    @Mock
    private Type mockType;
    @Mock
    private JsonDeserializationContext mockContext;
    private SearchResultDeserializer deserializerUnderTest = new SearchResultDeserializer();

    @Test
    public void deserialize_withJsonNull_shouldReturnNull() {
        assertNull(deserializerUnderTest.deserialize(JsonNull.INSTANCE, mockType, mockContext));
    }

    @Test
    public void deserialize_withMovieMediaType_shouldReturnMovieSearchResult() {
        final MovieSearchResult expected = mock(MovieSearchResult.class);
        when(mockContext.deserialize(any(JsonElement.class), eq(MovieSearchResult.class))).thenReturn(expected);
        final JsonElement element = setUpNewJsonElement(true, MediaType.MOVIE);

        final SearchResult actual = deserializerUnderTest.deserialize(element, mockType, mockContext);

        assertAll(
                () -> assertEquals(expected, actual),
                () -> verify(mockContext).deserialize(eq(element), eq(MovieSearchResult.class)));
    }

    @Test
    public void deserialize_withTvMediaType_shouldReturnTvSeriesSearchResult() {
        final TvSeriesSearchResult expected = mock(TvSeriesSearchResult.class);
        when(mockContext.deserialize(any(JsonElement.class), eq(TvSeriesSearchResult.class))).thenReturn(expected);
        final JsonElement element = setUpNewJsonElement(true, MediaType.TV);;

        final SearchResult actual = deserializerUnderTest.deserialize(element, mockType, mockContext);

        assertAll(
                () -> assertEquals(expected, actual),
                () -> verify(mockContext).deserialize(eq(element), eq(TvSeriesSearchResult.class)));
    }

    @Test
    public void deserialize_withPersonMediaType_shouldReturnPersonSearchResult() {
        final PersonSearchResult expected = mock(PersonSearchResult.class);
        when(mockContext.deserialize(any(JsonElement.class), eq(PersonSearchResult.class))).thenReturn(expected);
        final JsonElement element = setUpNewJsonElement(true, MediaType.PERSON);;

        final SearchResult actual = deserializerUnderTest.deserialize(element, mockType, mockContext);

        assertAll(
                () -> assertEquals(expected, actual),
                () -> verify(mockContext).deserialize(eq(element), eq(PersonSearchResult.class)));
    }

    @Test
    public void deserialize_withUnsupportedMediaType_shouldThrowException() {
        final JsonElement element = setUpNewJsonElement(true, MediaType.TV_SEASON);
        assertThrows(JsonParseException.class, () -> deserializerUnderTest.deserialize(element, mockType, mockContext));
    }
}
