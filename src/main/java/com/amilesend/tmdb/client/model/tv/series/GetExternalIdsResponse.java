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
package com.amilesend.tmdb.client.model.tv.series;

import com.amilesend.tmdb.client.model.Resource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Describes the external identifiers associated with a TV series.
 *
 * @see Resource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetExternalIdsResponse extends Resource<Integer, GetExternalIdsResponse> {
    /** The Internet Movie Database identifier. */
    private final String imdbId;
    /** The Freebase MID. */
    private final String freebaseMid;
    /** The Freebase ID. */
    private final String freebaseId;
    /** The TVDB identifier. */
    private final Integer tvdbId;
    /** The TV Rage identifier. */
    private final Integer tvrageId;
    /** The Wikidata identifier. */
    private final String wikidataId;
    /** The Facebook identifier. */
    private final String facebookId;
    /** The Instagram identifier. */
    private final String instagramId;
    /** The Twitter identifier. */
    private final String twitterId;
}
