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

import com.amilesend.tmdb.client.model.NamedResource;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Describes a movie list.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class MediaListInfo extends NamedResource<Integer, MediaListInfo> {
    /** The list description. */
    private final String description;
    /** The number of favorite items. */
    private final Integer favoriteCount;
    /** The total number of items in the list. */
    private final Integer itemCount;
    /** The ISO-639-1 language code. */
    @SerializedName("iso_639_1")
    private final String languageCode;
    /** The list type. */
    private final String listType;
    /** The path to the poster image. */
    private final String posterPath;
}
