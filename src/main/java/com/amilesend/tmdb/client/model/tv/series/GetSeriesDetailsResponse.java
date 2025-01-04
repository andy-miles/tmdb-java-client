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
package com.amilesend.tmdb.client.model.tv.series;

import com.amilesend.tmdb.client.model.NamedResource;
import com.amilesend.tmdb.client.model.tv.seasons.type.TvSeason;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesCredit;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesEpisode;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesNetwork;
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
 * Describes a TV series.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetSeriesDetailsResponse extends NamedResource<Integer, GetSeriesDetailsResponse> {
    /** Adult content flag indicator. */
    private final Boolean adult;
    /** Relative path to the backdrop image. */
    private final String backdropPath;
    /** Lies of credits for the TV series creation. */
    private final List<TvSeriesCredit> createdBy;
    /** The list of episode runtimes (in minutes). */
    private final List<Integer> episodeRunTime;
    /** The first air date of the TV series. */
    private final LocalDate firstAirDate;
    /** The list of applicable genres. */
    private final List<Genre> genres;
    /** The TV series homepage. */
    private final String homepage;
    /** Flag to indicate if the series is still in production. */
    private final Boolean inProduction;
    /** List of applicable languages. */
    private final List<String> languages;
    /** The last air date of the latest episode for the series. */
    private final LocalDate lastAirDate;
    /** Describes the last aired episode. */
    private final TvSeriesEpisode lastEpisodeToAir;
    /** Describes the next episode to air. */
    private final TvSeriesEpisode nextEpisodeToAir;
    /** List of networks that the TV series is aired on. */
    private final List<TvSeriesNetwork> networks;
    /** Total number of episodes. */
    private final Integer numberOfEpisodes;
    /** Total number of seasons. */
    private final Integer numberOfSeasons;
    /** List of countries that the TV series was produced in. */
    private final List<String> originCountry;
    /** The original language of the TV series. */
    private final String originalLanguage;
    /** The original name of the TV series. */
    private final String originalName;
    /** The series overview description. */
    private final String overview;
    /** The popularity weight. */
    private final Double popularity;
    /** The relative path to the TV series poster image. */
    private final String posterPath;
    /** List of production companies associated with the TV series. */
    private final List<ProductionCompany> productionCompanies;
    /** List of production countries associated with the TV series. */
    private final List<ProductionCountry> productionCountries;
    /** List of seasons for the series. */
    private final List<TvSeason> seasons;
    /** List of spoken languages for the series. */
    private final List<Language> spokenLanguages;
    /** The series' current status. */
    private final String status;
    /** The series tagline. */
    private final String tagline;
    /** The type of series. */
    private final String type;
    /** The average vote value. */
    private final Double voteAverage;
    /** The total number of votes for the series. */
    private final Integer voteCount;
}
