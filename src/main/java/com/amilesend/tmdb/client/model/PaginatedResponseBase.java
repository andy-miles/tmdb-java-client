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
package com.amilesend.tmdb.client.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Defines a paginated response with a limited set of results.
 *
 * @param <T> the result type
 */
@SuperBuilder
@Data
public abstract class PaginatedResponseBase<T> {
    /** The page number associated with the results. */
    private final Integer page;
    /** The list of results. */
    private final List<T> results;
    /** The total number of pages available. */
    private final Integer totalPages;
    /** The total number of results available. */
    private final Integer totalResults;
}
