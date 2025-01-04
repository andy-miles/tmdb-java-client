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
package com.amilesend.tmdb.client.model.discover.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Defines how to sort the contents of a discover-based response.
 *
 * @see DiscoverRequestBase
 */
@Getter
@RequiredArgsConstructor
public enum SortBy {
    ORIGINAL_TITLE_ASC("original_title.asc"),
    ORIGINAL_TITLE_DESC("original_title.desc"),
    POPULARITY_ASC("popularity.asc"),
    POPULARITY_DESC("popularity.desc"),
    REVENUE_ASC("revenue.asc"),
    REVENUE_DESC("revenue.desc"),
    PRIMARY_RELEASE_DATE_ASC("primary_release_date.asc"),
    PRIMARY_RELEASE_DATE_DESC("primary_release_date.desc"),
    TITLE_ASC("title.asc"),
    TITLE_DESC("title.desc"),
    VOTE_AVERAGE_ASC("vote_average.asc"),
    VOTE_AVERAGE_DESC("vote_average.desc"),
    VOTE_COUNT_ASC("vote_count.asc"),
    VOTE_COUNT_DESC("vote_count.desc");

    /** The sort by value. */
    private final String sortValue;

    @Override
    public String toString() {
        return sortValue;
    }
}
