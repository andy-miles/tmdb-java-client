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
package com.amilesend.tmdb.client.model.find.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/** Describes the external source type. */
@RequiredArgsConstructor
@Getter
public enum ExternalSource {
    /** Internet Movie Database identifier. */
    IMDB("imdb_id"),
    /** Facebook identifier. */
    FACEBOOK("facebook_id"),
    /** Instagram identifier. */
    INSTAGRAM("instagram_id"),
    /** TV Database identifier. */
    TVDB("tvdb_id"),
    /** TikTok identifier. */
    TIKTOK("tiktok_id")
    /** Twitter (X) identifier. */,
    TWITTER("twitter_id"),
    /** Wikidata identifier. */
    WIKIDATA("wikidata_id"),
    /** Youtube identifier. */
    YOUTUBE("youtube_id");

    private static final Map<String, ExternalSource> VALUE_TO_EXTERNAL_SOURCE = Map.of(
            "imdb_id", IMDB,
            "facebook_id", FACEBOOK,
            "instagram_id", INSTAGRAM,
            "tvdb_id", TVDB,
            "tiktok_id", TIKTOK,
            "twitter_id", TWITTER,
            "wikidata_id", WIKIDATA,
            "youtube_id", YOUTUBE);

    /** The external source type value. */
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
