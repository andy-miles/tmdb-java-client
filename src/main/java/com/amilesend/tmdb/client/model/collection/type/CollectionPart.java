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
package com.amilesend.tmdb.client.model.collection.type;

import com.amilesend.tmdb.client.model.Resource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * Collection part.
 *
 * @see Resource
 * @see CollectionPart
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class CollectionPart extends Resource<Integer, CollectionPart> {
    /** Adult flag indicator. */
    private final Boolean adult;
    /** Path to backdrop image. */
    private final String backdropPath;
    /** The title. */
    private final String title;
    /** The original content language. */
    private final String originalLanguage;
    /** The original content title. */
    private final String originalTitle;
    /** The content summary. */
    private final String overview;
    /** Path to the poster image. */
    private final String posterPath;
    /** The type of media. */
    private final String mediaType;
    /** List of applicable genre identifiers. */
    private final List<Integer> genreIds;
    /** Popularity rating. */
    private final Double popularity;
    /** The release date. */
    private final LocalDate releaseDate;
    /** Video flag indicator. */
    private final Boolean video;
    /** The user vote rating. */
    private final Double voteAverage;
    /** Total number of votes. */
    private final Integer voteCount;
}
