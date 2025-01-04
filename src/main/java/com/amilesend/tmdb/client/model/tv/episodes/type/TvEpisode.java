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
package com.amilesend.tmdb.client.model.tv.episodes.type;

import com.amilesend.tmdb.client.model.NamedResource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * The response describing a TV episode's details.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class TvEpisode extends NamedResource<Integer, TvEpisode> {
    /** The episode air date. */
    private final LocalDate airDate;
    /** The episode number relative to the season. */
    private final Integer episodeNumber;
    /** The episode overview. */
    private final String overview;
    /** The production code. */
    private final String productionCode;
    /** The runtime in minutes. */
    private final Integer runtime;
    /** The associated season number. */
    private final Integer seasonNumber;
    /** The relative path to the still image. */
    private final String stillPath;
    /** The average vote value. */
    private final Double voteAverage;
    /** The total number of votes for the episode. */
    private final Integer voteCount;
}
