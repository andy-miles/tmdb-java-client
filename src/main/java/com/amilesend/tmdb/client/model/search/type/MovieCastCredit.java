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
package com.amilesend.tmdb.client.model.search.type;

import com.amilesend.tmdb.client.model.Resource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Defines a movie cast credit for a person.
 *
 * @see Resource
 * @see PersonCredit
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class MovieCastCredit extends PersonCredit {
    /** The original title. */
    private final String originalTitle;
    /** The content release date. */
    private final LocalDate releaseDate;
    /** The content title. */
    private final String title;
    /** Video flag indicator. */
    private final Boolean video;
}
