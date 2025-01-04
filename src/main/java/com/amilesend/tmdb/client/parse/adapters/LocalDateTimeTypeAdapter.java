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
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** GSON adapter to format and serializes {@link LocalDateTime} objects. */
@Slf4j
public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz");
    public static final DateTimeFormatter ISO_8601_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    @Override
    public JsonElement serialize(
            final LocalDateTime dateTime,
            final Type typeOfSrc,
            final JsonSerializationContext context) {
        return new JsonPrimitive(dateTime.atZone(ZoneId.of("UTC")).format(DATE_TIME_FORMAT));
    }

    @Override
    public LocalDateTime deserialize(
            final JsonElement jsonElement,
            final Type typeOfT,
            final JsonDeserializationContext context) throws JsonParseException {
        if (jsonElement.isJsonNull()) {
            return null;
        }

        final String dateTimeAsString = jsonElement.getAsString();
        if (StringUtils.isBlank(dateTimeAsString)) {
            return null;
        }

        try {
            return LocalDateTime.parse(dateTimeAsString, DATE_TIME_FORMAT);
        } catch (final DateTimeParseException ex) {
            if (log.isDebugEnabled()) {
                log.debug("Datetime format does not match pattern: \"yyyy-MM-dd HH:mm:ss zzz\"", ex);
            }
        }

        // Fallback to attempt parsing ISO_8601 format
        try {
            return LocalDateTime.parse(dateTimeAsString, ISO_8601_FORMAT);
        } catch (final DateTimeParseException ex) {
            throw new JsonParseException("Datetime format does not match pattern: \"yyyy-MM-dd HH:mm:ss zzz\" " +
                    "or \"yyyy-MM-dd'T'HH:mm:ss.SSSX\"", ex);
        }
    }
}
