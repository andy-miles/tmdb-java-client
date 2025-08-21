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

import com.amilesend.client.util.StringUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** GSON adapter to format and serializes {@link LocalDate} objects. */
public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public JsonElement serialize(
            final LocalDate date,
            final Type typeOfSrc,
            final JsonSerializationContext context) {
        return new JsonPrimitive(date.format(FORMATTER));
    }

    @Override
    public LocalDate deserialize(
            final JsonElement jsonElement,
            final Type typeOfT,
            final JsonDeserializationContext context) throws JsonParseException {
        if (jsonElement.isJsonNull()) {
            return null;
        }

        final String timeAsString = jsonElement.getAsString();
        if (StringUtils.isBlank(timeAsString)) {
            return null;
        }

        try {
            return LocalDate.parse(timeAsString, FORMATTER);
        } catch (final DateTimeParseException ex) {
            throw new JsonParseException("Date format does not match pattern: \"yyyy-MM-dd\"", ex);
        }
    }
}
