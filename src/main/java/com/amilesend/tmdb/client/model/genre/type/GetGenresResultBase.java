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
package com.amilesend.tmdb.client.model.genre.type;

import com.amilesend.tmdb.client.model.type.Genre;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/** The base class for a response that contains a list of genres. */
@SuperBuilder
@Data
public abstract class GetGenresResultBase {
    /** The list of genres. */
    private final List<Genre> genres;
}
