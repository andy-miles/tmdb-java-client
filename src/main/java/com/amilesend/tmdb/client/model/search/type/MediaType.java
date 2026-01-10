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
package com.amilesend.tmdb.client.model.search.type;

import com.amilesend.client.util.Validate;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/** Defines the possible media types for trending resources. */
@Getter
@RequiredArgsConstructor
public enum MediaType {
    MOVIE("movie"),
    PERSON("person"),
    TV("tv"),
    TV_SEASON("tv_season"),
    TV_EPISODE("tv_episode");

    public static final String MEDIA_TYPE_OBJ_NAME = "media_type";

    private static final Map<String, MediaType> VALUE_TO_MEDIA_TYPE = Map.of(
            "movie", MOVIE,
            "person", PERSON,
            "tv", TV,
            "tv_season", TV_SEASON,
            "tv_episode", TV_EPISODE);

    /** The media type value. */
    private final String value;

    /**
     * Returns the associated {@code MediaType} for the given value.
     *
     * @param value the value
     * @return the media type reference
     */
    public static MediaType fromValue(final String value) {
        Validate.notBlank(value, "value must not be blank");
        return VALUE_TO_MEDIA_TYPE.get(value);
    }

    /**
     * Helper method to parse the {@code media_type} attributes from a {@link JsonElement}.
     *
     * @param rootElement the JSON object element
     * @return the media type reference
     */
    public static MediaType parseMediaTypeFromJson(final JsonElement rootElement) {
        final JsonObject parsedObject = rootElement.getAsJsonObject();
        if (!parsedObject.has(MEDIA_TYPE_OBJ_NAME)) {
            throw new JsonParseException("\"" + MEDIA_TYPE_OBJ_NAME + "\" does not exist");
        }

        final JsonElement mediaTypeElement = parsedObject.get(MEDIA_TYPE_OBJ_NAME);
        return MediaType.fromValue(mediaTypeElement.getAsString());
    }

    @Override
    public String toString() {
        return value;
    }
}
