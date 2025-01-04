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
package com.amilesend.tmdb.client.model.tv.series.type;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/** Describes the poster for a change. */
@Builder
@Data
public class Poster {
    /** The relative path to the post image. */
    private final String filePath;
    /** The associate ISO-639-1 language code. */
    @SerializedName("iso_639_1")
    private final String languageCode;
}
