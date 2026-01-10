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
package com.amilesend.tmdb.client.parse.adapters;

import com.amilesend.tmdb.client.model.search.type.MediaType;
import com.amilesend.tmdb.client.model.search.type.MovieCastCredit;
import com.amilesend.tmdb.client.model.search.type.PersonCredit;
import com.amilesend.tmdb.client.model.search.type.TvCastCredit;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Type;

import static com.amilesend.tmdb.client.model.search.type.MediaType.MEDIA_TYPE_OBJ_NAME;
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
public class PersonCreditDeserializerTest {
    @Mock
    private Type mockType;
    @Mock
    private JsonDeserializationContext mockContext;
    private PersonCreditDeserializer deserializerUnderTest = new PersonCreditDeserializer();

    @Test
    public void deserialize_withJsonNull_shouldReturnNull() {
        assertNull(deserializerUnderTest.deserialize(JsonNull.INSTANCE, mockType, mockContext));
    }

    @Test
    public void deserialize_withMovieMediaType_shouldReturnMovieCastCredit() {
        final MovieCastCredit expected = mock(MovieCastCredit.class);
        when(mockContext.deserialize(any(JsonElement.class), eq(MovieCastCredit.class))).thenReturn(expected);
        final JsonElement element = setUpNewJsonElement(true, MediaType.MOVIE);

        final PersonCredit actual = deserializerUnderTest.deserialize(element, mockType, mockContext);

        assertAll(
                () -> assertEquals(expected, actual),
                () -> verify(mockContext).deserialize(eq(element), eq(MovieCastCredit.class)));
    }

    @Test
    public void deserialize_withTvMediaType_shouldReturnTvCastCredit() {
        final TvCastCredit expected = mock(TvCastCredit.class);
        when(mockContext.deserialize(any(JsonElement.class), eq(TvCastCredit.class))).thenReturn(expected);
        final JsonElement element = setUpNewJsonElement(true, MediaType.TV);;

        final PersonCredit actual = deserializerUnderTest.deserialize(element, mockType, mockContext);

        assertAll(
                () -> assertEquals(expected, actual),
                () -> verify(mockContext).deserialize(eq(element), eq(TvCastCredit.class)));
    }

    @Test
    public void deserialize_withUnsupportedMediaType_shouldThrowException() {
        final JsonElement element = setUpNewJsonElement(true, MediaType.TV_SEASON);
        assertThrows(JsonParseException.class, () -> deserializerUnderTest.deserialize(element, mockType, mockContext));
    }

    static JsonElement setUpNewJsonElement(final boolean hasMediaTypeAttribute, final MediaType mediaType) {
        final JsonObject mockJsonObject = mock(JsonObject.class);
        when(mockJsonObject.has(eq(MEDIA_TYPE_OBJ_NAME))).thenReturn(hasMediaTypeAttribute);

        final JsonElement mockMediaTypeElement = new JsonPrimitive(mediaType.getValue());
        when(mockJsonObject.get(eq(MEDIA_TYPE_OBJ_NAME))).thenReturn(mockMediaTypeElement);

        final JsonElement mockRootElement = mock(JsonElement.class);
        when(mockRootElement.getAsJsonObject()).thenReturn(mockJsonObject);

        return mockRootElement;
    }
}
