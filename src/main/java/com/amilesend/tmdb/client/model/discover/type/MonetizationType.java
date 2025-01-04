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

/** The watch region filter monetization type. */
@RequiredArgsConstructor
@Getter
public enum MonetizationType {
    FLAT_RATE("flatrate"),
    FREE("free"),
    ADS("ads"),
    RENT("rent"),
    BUY("buy");

    /** The type value. */
    private final String typeValue;

    @Override
    public String toString() {
        return typeValue;
    }
}
