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
package com.amilesend.tmdb.client.model.movie.type;

import com.amilesend.tmdb.client.model.Resource;
import com.amilesend.tmdb.client.model.type.Genre;
import com.amilesend.tmdb.client.model.type.Language;
import com.amilesend.tmdb.client.model.type.ProductionCompany;
import com.amilesend.tmdb.client.model.type.ProductionCountry;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * The response describing a movie's details.
 *
 * @see Resource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class MovieDetails extends Resource<Integer, MovieDetails> {
    /** Adult flag indicator. */
    private final boolean adult;
    /** The path to the backdrop image. */
    private final String backdropPath;
    /** The associated movie collection. */
    private final MovieCollection belongsToCollection;
    /** The production budget. */
    private final int budget;
    /**
     * The list of applicable genres.
     *
     * @see Genre
     */
    private final List<Genre> genres;
    /** The movie website. */
    private final String homepage;
    /** The IMDB identifier. */
    private final String imdbId;
    /** The original language. */
    private final String originalLanguage;
    /** The original title. */
    private final String originalTitle;
    /** The summary. */
    private final String overview;
    /** The popularity rating. */
    private final double popularity;
    /** The path to the poster image. */
    private final String posterPath;
    /**
     * The list of associated production companies.
     *
     * @see ProductionCompany
     */
    private final List<ProductionCompany> productionCompanies;
    /**
     * The list of countries that the movie was produced in.
     *
     * @see ProductionCountry
     */
    private final List<ProductionCountry> productionCountries;
    /** The release date. */
    private final LocalDate releaseDate;
    /** The movie revenue. */
    private final long revenue;
    /** The movie runtime (in minutes). */
    private final int runtime;
    /** The list of available spoken languages that are available for the movie (original and dubs). */
    private final List<Language> spokenLanguages;
    /** The movie status. */
    private final String status;
    /** The movie tagline. */
    private final String tagline;
    /** The movie title. */
    private final String title;
    /** Video flag indicator. */
    private final boolean video;
    /** Vote rating average. */
    private final double voteAverage;
    /** The total vote count. */
    private final int voteCount;
}
