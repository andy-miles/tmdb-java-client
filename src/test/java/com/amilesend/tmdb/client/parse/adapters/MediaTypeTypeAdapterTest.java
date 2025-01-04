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
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MediaTypeTypeAdapterTest {
    @Mock
    private Type mockType;
    @Mock
    private JsonSerializationContext mockSerializationContext;
    @Mock
    private JsonDeserializationContext mockDeserializationContext;
    private MediaTypeTypeAdapter adapterUnderTest = new MediaTypeTypeAdapter();

    @Test
    public void serialize_withMediaType_shouldReturnValue() {
        final JsonElement actual = adapterUnderTest.serialize(MediaType.PERSON, mockType, mockSerializationContext);
        assertEquals("person", actual.getAsString());
    }

    @Test
    public void deserialize_withValidMediaTypeValue_shouldReturnMediaType() {
        final JsonElement element = new JsonPrimitive("tv_season");

        final MediaType actual = adapterUnderTest.deserialize(element, mockType, mockDeserializationContext);

        assertEquals(MediaType.TV_SEASON, actual);
    }

    @Test
    public void deserialize_withUnknownMediaTypeValue_shouldThrowException() {
        final JsonElement element = new JsonPrimitive("unknown_value");
        assertThrows(JsonParseException.class,
                () -> adapterUnderTest.deserialize(element, mockType, mockDeserializationContext));
    }
}
