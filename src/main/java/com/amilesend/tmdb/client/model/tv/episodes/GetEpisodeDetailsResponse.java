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
package com.amilesend.tmdb.client.model.tv.episodes;

import com.amilesend.tmdb.client.model.tv.episodes.type.TvEpisode;
import com.amilesend.tmdb.client.model.type.CastCredit;
import com.amilesend.tmdb.client.model.type.CrewCredit;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Response that describes a TV episode.
 *
 * @see TvEpisode
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetEpisodeDetailsResponse extends TvEpisode {
    /** The list of crew credits. */
    private final List<CrewCredit> crew;
    /** The list of guest star credits. */
    private final List<CastCredit> guestStars;
}
