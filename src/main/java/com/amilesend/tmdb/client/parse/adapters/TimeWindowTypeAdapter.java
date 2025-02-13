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

import com.amilesend.tmdb.client.model.trending.type.TimeWindow;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Optional;

/** GSON adapter to format and serializes {@link TimeWindow} enums. */
public class TimeWindowTypeAdapter implements JsonSerializer<TimeWindow>, JsonDeserializer<TimeWindow> {
    @Override
    public JsonElement serialize(final TimeWindow timeWindow, final Type type, final JsonSerializationContext context) {
        return new JsonPrimitive(timeWindow.getValue());
    }

    @Override
    public TimeWindow deserialize(
            final JsonElement jsonElement,
            final Type type,
            final JsonDeserializationContext context) throws JsonParseException {
        return Optional.ofNullable(TimeWindow.fromValue(jsonElement.getAsString()))
                .orElseThrow(() -> new JsonParseException("Unknown TimeWindow value: " + jsonElement.getAsString()));
    }
}
