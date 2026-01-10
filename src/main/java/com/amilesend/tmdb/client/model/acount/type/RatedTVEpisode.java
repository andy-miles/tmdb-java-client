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
package com.amilesend.tmdb.client.model.acount.type;

import com.amilesend.tmdb.client.model.NamedResource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Describes a rated TV show episode.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class RatedTVEpisode extends NamedResource<Integer, RatedTVEpisode> {
    /** The episodes initial air date. */
    private final LocalDate airDate;
    /** The episode number (within a season) associated with the episode. */
    private final Integer episodeNumber;
    /** A summary of the episode. */
    private final String overview;
    /** The production code identifier associated with the episode. */
    private final String productionCode;
    /** The episode runtime in minutes. */
    private final Integer runtime;
    /** The associated season number of the episode. */
    private final Integer seasonNumber;
    /** The associated show identifier. */
    private final Integer showId;
    /** Relative path to an image still associated with the episode. */
    private final String stillPath;
    /** The average user rating of the episode. */
    private final Double voteAverage;
    /** The total user vote count for the episode. */
    private final Integer voteCount;
    /** The user's rating of the episode. */
    private final Integer rating;
}
