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
package com.amilesend.tmdb.client.model.people;

import com.amilesend.tmdb.client.model.Resource;
import com.amilesend.tmdb.client.model.movie.GetExternalIdsResponse;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Defines the response that describes the available external site identifiers
 * for a person.
 *
 * @see Resource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetExternalIDsResponse extends Resource<Integer, GetExternalIdsResponse> {
    /** The Internet Movie Database identifier. */
    private final String imdbId;
    /** The TV Tage identifier. */
    private final Integer tvrageId;
    /** The wikidata identifier. */
    private final String wikidataId;
    /** The facebook identifier. */
    private final String facebookId;
    /** The Instagram identifier. */
    private final String instagramId;
    /** The TikTok identifier. */
    private final String tiktokId;
    /** The Twitter (X) identifier. */
    private final String twitterId;
    /** The YouTube identifier. */
    private final String youtubeId;
}
