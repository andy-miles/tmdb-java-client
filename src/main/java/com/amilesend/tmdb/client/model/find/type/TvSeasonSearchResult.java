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
package com.amilesend.tmdb.client.model.find.type;

import com.amilesend.tmdb.client.model.NamedResource;
import com.amilesend.tmdb.client.model.search.type.MediaType;
import com.amilesend.tmdb.client.model.search.type.SearchResult;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Describes a TV season search result.
 *
 * @see NamedResource
 * @see SearchResult
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class TvSeasonSearchResult extends NamedResource<Integer, TvSeasonSearchResult> implements SearchResult {
    /** The TV season overview. */
    private final String overview;
    /** The poster image path. */
    private final String posterPath;
    /** The media type. */
    private final MediaType mediaType;
    /** The average user vote score. */
    private final Double voteAverage;
    /** The season air date. */
    private final LocalDate airDate;
    /** The season number.*/
    private final Integer seasonNumber;
    /** The associated TV series identifier. */
    private final Integer showId;
    /** The number of episodes in the season. */
    private final Integer episodeCount;
}
