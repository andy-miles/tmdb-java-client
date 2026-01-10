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

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LocalDateTimeTypeAdapterTest {
    @Mock
    private Type mockType;
    @Mock
    private JsonSerializationContext mockSerializationContext;
    @Mock
    private JsonDeserializationContext mockDeserializationContext;
    private LocalDateTimeTypeAdapter adapterUnderTest = new LocalDateTimeTypeAdapter();

    @Test
    public void serialize_withValidLocalDateTime_shouldReturnJsonElement() {
        final LocalDateTime localDateTime =
                LocalDateTime.of(2020, 5, 28, 12, 30, 0);

        final JsonElement actual = adapterUnderTest.serialize(localDateTime, mockType, mockSerializationContext);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals("2020-05-28 12:30:00 UTC", actual.getAsString()));
    }

    @SneakyThrows
    @Test
    public void deserialize_withJsonNull_shouldReturnNull() {
        assertNull(adapterUnderTest.deserialize(JsonNull.INSTANCE, mockType, mockDeserializationContext));
    }

    @SneakyThrows
    @Test
    public void deserialize_withBlankTimeValue_shouldReturnNull() {
        final JsonElement emptyElement = new JsonPrimitive("");
        assertNull(adapterUnderTest.deserialize(emptyElement, mockType, mockDeserializationContext));
    }

    @SneakyThrows
    @Test
    public void deserialize_withMalformedFormat_shouldThrowException() {
        final JsonElement malformedElement = new JsonPrimitive("Will not parse");

        final Throwable thrown = assertThrows(JsonParseException.class,
                () -> adapterUnderTest.deserialize(malformedElement, mockType, mockDeserializationContext));

        assertInstanceOf(DateTimeParseException.class, thrown.getCause());
    }

    @SneakyThrows
    @Test
    public void deserialize_withSpecFormat_shouldReturnLocalDateTime() {
        final JsonElement specFormattedElement = new JsonPrimitive("2020-05-28 12:30:00 UTC");

        final LocalDateTime actual =
                adapterUnderTest.deserialize(specFormattedElement, mockType, mockDeserializationContext);

        assertEquals(LocalDateTime.of(2020, 5, 28, 12, 30, 0), actual);
    }

    @SneakyThrows
    @Test
    public void deserialize_withIsoFormat_shouldReturnLocalDateTime() {
        final JsonElement isoFormattedElement = new JsonPrimitive("2020-05-28T12:30:00.000Z");

        final LocalDateTime actual =
                adapterUnderTest.deserialize(isoFormattedElement, mockType, mockDeserializationContext);

        assertEquals(LocalDateTime.of(2020, 5, 28, 12, 30, 0), actual);
    }
}
