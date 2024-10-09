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
package com.amilesend.tmdb.client.model.type;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/** Defines a translation. */
@Builder
@Data
public class Translation<T> {
    /** The ISO-3166-1 country code. */
    @SerializedName("iso_3166_1")
    private final String countryCode;
    /** The ISO-639-1 language code. */
    @SerializedName("iso_639_1")
    private final String languageCode;
    /** The translation name. */
    private final String name;
    /** The translation name in English. */
    private final String englishName;
    /** The translation data. */
    private final T data;
}
