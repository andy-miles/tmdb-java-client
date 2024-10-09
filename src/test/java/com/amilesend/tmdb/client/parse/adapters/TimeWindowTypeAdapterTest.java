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
package com.amilesend.tmdb.client.parse.adapters;

import com.amilesend.tmdb.client.model.trending.type.TimeWindow;
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
public class TimeWindowTypeAdapterTest {
    @Mock
    private Type mockType;
    @Mock
    private JsonSerializationContext mockSerializationContext;
    @Mock
    private JsonDeserializationContext mockDeserializationContext;
    private TimeWindowTypeAdapter adapterUnderTest = new TimeWindowTypeAdapter();

    @Test
    public void serialize_withTimeWindow_shouldReturnValue() {
        final JsonElement actual = adapterUnderTest.serialize(TimeWindow.DAY, mockType, mockSerializationContext);
        assertEquals("day", actual.getAsString());
    }

    @Test
    public void deserialize_withValidTimeWindowValue_shouldReturnTimeWindow() {
        final JsonElement element = new JsonPrimitive("week");

        final TimeWindow actual = adapterUnderTest.deserialize(element, mockType, mockDeserializationContext);

        assertEquals(TimeWindow.WEEK, actual);
    }

    @Test
    public void deserialize_withUnknownTimeWindowValue_shouldThrowException() {
        final JsonElement element = new JsonPrimitive("unknown_value");
        assertThrows(JsonParseException.class,
                () -> adapterUnderTest.deserialize(element, mockType, mockDeserializationContext));
    }
}
