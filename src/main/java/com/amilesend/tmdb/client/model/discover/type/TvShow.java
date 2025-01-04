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

import com.amilesend.tmdb.client.model.NamedResource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * Describes a TV show.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class TvShow extends NamedResource<Integer, TvShow> {
    /** The relative path to the backdrop image. */
    private final String backdropPath;
    /** The first air date for the TV show. */
    private final LocalDate firstAirDate;
    /** The list of genre identifiers. */
    private List<Integer> genreIds;
    /** The list of countries. */
    private final List<String> originCountry;
    /** The original airing language. */
    private final String originalLanguage;
    /** The original airing name. */
    private final String originalName;
    /** Overview describing the show. */
    private final String overview;
    /** The popularity score. */
    private final Double popularity;
    /** The relative path to the poster image. */
    private final String posterPath;
    /** The average vote rating. */
    private final Double voteAverage;
    /** The total number of votes. */
    private final Integer voteCount;
}
