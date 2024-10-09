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
package com.amilesend.tmdb.client.model.search.type;

import com.amilesend.tmdb.client.model.Resource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Defines the base class for a credit.
 *
 * @see Resource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
/** Indicates that an object is a credit for a person. */
public abstract class PersonCredit extends Resource<Integer, PersonCredit>  {
    /** Adult flag indicator. */
    private final Boolean adult;
    /** Path to the content backdrop image. */
    private final String backdropPath;
    /** List of genre identifiers. */
    private final List<Integer> genreIds;
    /** List of countries that the TV show originates from. */
    private final List<String> originCountry;
    /** The original airing language. */
    private final String originalLanguage;
    /** The overview. */
    private final String overview;
    /** Popularity score. */
    private final Double popularity;
    /** Path to the poster image. */
    private final String posterPath;
    /** The average vote value. */
    private final Double voteAverage;
    /** The vote count. */
    private final Integer voteCount;
    /** The media type for the credit. */
    private final MediaType mediaType;
}
