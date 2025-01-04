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
package com.amilesend.tmdb.client.model.list;

import com.amilesend.tmdb.client.model.NamedResource;
import com.amilesend.tmdb.client.model.list.type.ListItem;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * The list details.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetListDetailsResponse extends NamedResource<Integer, GetListDetailsResponse> {
    /** List author. */
    private final String createdBy;
    /** List description. */
    private final String description;
    /** Total favorite count. */
    private final int favoriteCount;
    /** The list of items in the list. */
    private List<ListItem> items;
    /** The number of items in the list. */
    private final Integer itemCount;
    /** The language code. */
    @SerializedName("iso_639_1")
    private final String languageCode;
    /** Path to the post image. */
    private final String posterPath;
}
