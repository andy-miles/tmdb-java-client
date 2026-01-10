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
package com.amilesend.tmdb.client.model.tv.episodes.groups.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/** Defines the TV Episode Group Type. */
@RequiredArgsConstructor
@Getter
public enum TvEpisodeGroupType {
    ORIGINAL_AIR_DATE(1, "Original air date"),
    ABSOLUTE(2, "Absolute"),
    DVD(3, "DVD"),
    DIGITAL(4, "Digital"),
    STORY_ARC(5, "Story arc"),
    PRODUCTION(6, "Production"),
    TV(7, "TV");

    private static final Map<Integer, TvEpisodeGroupType> TYPE_TO_ENUM = Map.of(
            1, ORIGINAL_AIR_DATE,
            2, ABSOLUTE,
            3, DVD,
            4, DIGITAL,
            5, STORY_ARC,
            6, PRODUCTION,
            7, TV);

    /** The TV episode group type value. */
    private final int type;
    /** The TV episode group type name. */
    private final String name;

    /**
     * Retrieves the {@code TvEpisodeGroupType} enum reference for the given type value.
     *
     * @param type the type value (1-7)
     * @return the associated enum reference
     */
    public static TvEpisodeGroupType fromType(final int type) {
        return TYPE_TO_ENUM.get(type);
    }

    @Override
    public String toString() {
        return new StringBuilder("(")
                .append(type)
                .append(") ")
                .append(name)
                .toString();
    }
}
