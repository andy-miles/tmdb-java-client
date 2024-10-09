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
package com.amilesend.tmdb.client.model.discover.type;

import com.amilesend.tmdb.client.model.discover.filter.FilterQueryBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Defines the status of a TV show. This is typically used when defining a
 * {@code DiscoverTvRequest#getWithStatus()} via the {@link FilterQueryBuilder}.
 *
 * @see com.amilesend.tmdb.client.model.discover.DiscoverTvRequest
 * @see FilterQueryBuilder
 */
@Getter
@RequiredArgsConstructor
public enum TvStatus {
    RETURNING_SERIES(0),
    PLANNED(1),
    IN_PRODUCTION(2),
    ENDED(3),
    CANCELED(4),
    PILOT(5);

    /** The status value. */
    private final int status;

    @Override
    public String toString() {
        return String.valueOf(status);
    }
}
