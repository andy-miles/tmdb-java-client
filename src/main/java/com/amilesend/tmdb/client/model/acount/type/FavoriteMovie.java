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
package com.amilesend.tmdb.client.model.acount.type;

import com.amilesend.tmdb.client.model.Resource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * Describes a favorite movie resource.
 *
 * @see Resource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class FavoriteMovie extends Resource<Integer, FavoriteMovie> {
    /** Flag indicator if this media resource is adult content. */
    private final Boolean adult;
    /** Relative path to the backdrop image for this media. */
    private final String backdropPath;
    /** List of applicable genre identifiers. */
    private final List<Integer> genreIds;
    /** The original language of the media resource. */
    private final String originalLanguage;
    /** The original title of the movie. */
    private final String originalTitle;
    /** A summary description of the media resource. */
    private final String overview;
    /** Popularity score of the media resource. */
    private final Double popularity;
    /** Relative path to the media poster. */
    private final String posterPath;
    /** The release date of the movie. */
    private final LocalDate releaseDate;
    /** The title of the movie. */
    private final String title;
    /** Indicates if the movie is a non-movie type (e.g., DVD compilation, music concert, etc.). */
    private final Boolean video;
    /** The average user vote score of the media resource. */
    private final Double voteAverage;
    /** The total number of votes for the media resource. */
    private final Integer voteCount;
}
