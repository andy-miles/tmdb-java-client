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
package com.amilesend.tmdb.client.model.people.type;

import com.amilesend.tmdb.client.model.Resource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@Getter
@ToString(callSuper = true)
public class MovieCrewCredit extends Resource<Integer, MovieCrewCredit> {
    /** Adult flag indicator. */
    private final Boolean adult;
    /** Path to the content backdrop image. */
    private final String backdropPath;
    /** List of genre identifiers. */
    private final List<Integer> genreIds;
    /** The original language. */
    private final String originalLanguage;
    /** The original title. */
    private final String originalTitle;
    /** The overview. */
    private final String overview;
    /** Popularity score. */
    private final Double popularity;
    /** Path to the poster image. */
    private final String posterPath;
    /** The release date. */
    private final LocalDate releaseDate;
    /** The title. */
    private final String title;
    /** Video flag indicator. */
    private final Boolean video;
    /** The average vote value. */
    private final Double voteAverage;
    /** The vote count. */
    private final Integer voteCount;
    /** The credit identifier. */
    private final String creditId;
    /** The job type. */
    private final String department;
    /** The job title. */
    private final String job;
}
