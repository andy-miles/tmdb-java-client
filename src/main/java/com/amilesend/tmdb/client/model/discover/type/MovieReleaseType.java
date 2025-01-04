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

import com.amilesend.tmdb.client.model.discover.filter.FilterQueryBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Defines the movie release types. This is typically used when defining a
 * {@code DiscoverMoviesRequest#getWithReleaseType()} via the {@link FilterQueryBuilder}.
 * <br/>
 * <a href="https://developer.themoviedb.org/docs/region-support#release-types">API Documentation</a>
 *
 * @see com.amilesend.tmdb.client.model.discover.DiscoverMoviesRequest
 * @see FilterQueryBuilder
 */
@Getter
@RequiredArgsConstructor
public enum MovieReleaseType {
    PREMIERE(1, "Premiere"),
    LIMITED_THEATRICAL(2, "Theatrical (limited)"),
    THEATRICAL(3, "Theatrical"),
    DIGITAL(4, "Digital"),
    PHYSICAL(5, "Physical"),
    TV(6, "TV");

    /** The type value. */
    private final int type;
    /** The type name. */
    private final String typeName;

    @Override
    public String toString() {
        return new StringBuilder("(")
                .append(type)
                .append(") ")
                .append(typeName)
                .toString();
    }
}
