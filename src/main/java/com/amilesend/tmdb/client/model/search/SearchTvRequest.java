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
package com.amilesend.tmdb.client.model.search;

import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.search.type.SearchRequestBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * The request to search for TV shows.
 *
 * @see SearchRequestBase
 */
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchTvRequest extends SearchRequestBase {
    /** Flag to include adult-flagged resources in the search (optional). */
    @QueryParameter
    private final Boolean includeAdult;
    /** Filter on the first air date year. Valid values range from 1000 to 9999 (optional). */
    @QueryParameter
    private final Integer firstAirDateYear;
    /** The language to filter on (optional). */
    @QueryParameter
    private final String language;
    /** The region to filter on (optional). */
    @QueryParameter
    private final String region;
    /** The year of release to filter on (optional). */
    @QueryParameter
    private final Integer year;
}
