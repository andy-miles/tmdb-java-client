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
package com.amilesend.tmdb.client.model.movie.type;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/** Describes a movie release date. */
@Builder
@Data
public class ReleaseDate {
    /** The movie certification. */
    private final String certification;
    /** The ISO-639-1 language code. */
    @SerializedName("iso_639_1")
    private final String languageCode;
    /** Note associated with the movie release date. */
    private final String note;
    /** The release date value. */
    private final LocalDateTime releaseDate;
    /** The release type. */
    private final Integer type;
}
