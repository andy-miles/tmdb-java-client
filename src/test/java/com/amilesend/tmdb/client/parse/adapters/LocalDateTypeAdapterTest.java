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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LocalDateTypeAdapterTest {
    @Mock
    private Type mockType;
    @Mock
    private JsonSerializationContext mockSerializationContext;
    @Mock
    private JsonDeserializationContext mockDeserializationContext;
    private LocalDateTypeAdapter adapterUnderTest = new LocalDateTypeAdapter();

    @Test
    public void serialize_withValidLocalDate_shouldReturnJsonElement() {
        final LocalDate localDate = LocalDate.of(2020, 5, 28);

        final JsonElement actual = adapterUnderTest.serialize(localDate, mockType, mockSerializationContext);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals("2020-05-28", actual.getAsString()));
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
    public void deserialize_withSpecFormat_shouldReturnLocalDate() {
        final JsonElement specFormattedElement = new JsonPrimitive("2020-05-28");

        final LocalDate actual =
                adapterUnderTest.deserialize(specFormattedElement, mockType, mockDeserializationContext);

        assertEquals(LocalDate.of(2020, 5, 28), actual);
    }
}
