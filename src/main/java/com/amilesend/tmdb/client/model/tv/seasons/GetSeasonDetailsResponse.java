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
package com.amilesend.tmdb.client.model.tv.seasons;

import com.amilesend.tmdb.client.model.tv.episodes.GetEpisodeDetailsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.type.TvSeason;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Response that describes a TV season.
 *
 * @see TvSeason
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetSeasonDetailsResponse extends TvSeason {
    /** The list of episode details. */
    private final List<GetEpisodeDetailsResponse> episodes;
}
