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

import com.amilesend.tmdb.client.model.Resource;
import com.amilesend.tmdb.client.model.tv.episodes.type.TvEpisodeTranslationData;
import com.amilesend.tmdb.client.model.type.Translation;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Response that contains a list of translations for a TV episode.
 *
 * @see Resource
 * @see Translation
 * @see TvEpisodeTranslationData
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetTranslationsResponse extends Resource<Integer, GetTranslationsResponse> {
    /** The list of TV episode translations. */
    private final List<Translation<TvEpisodeTranslationData>> translations;
}
