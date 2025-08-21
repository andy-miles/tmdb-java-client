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
package com.amilesend.tmdb.client.model.trending.type;

import com.amilesend.client.util.Validate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/** Defines the possible time windows used in requests for fetching trending resources. */
@Getter
@RequiredArgsConstructor
public enum TimeWindow {
    /** Time window limited to the day only .*/
    DAY("day"),
    /** Time window limited to this week only. */
    WEEK("week");

    private static final Map<String, TimeWindow> VALUE_TO_TIME_WINDOW = Map.of(
            "day", DAY,
            "week", WEEK);

    private final String value;

    /**
     * Returns the associated {@code TimeWindow} for the given value.
     *
     * @param value the value
     * @return the time window reference
     */
    public static TimeWindow fromValue(final String value) {
        Validate.notBlank(value, "Value must not be blank");
        return VALUE_TO_TIME_WINDOW.get(value);
    }
}
