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
package com.amilesend.tmdb.client.model.find;

import com.amilesend.tmdb.client.model.find.type.TvEpisodeSearchResult;
import com.amilesend.tmdb.client.model.find.type.TvSeasonSearchResult;
import com.amilesend.tmdb.client.model.search.type.MovieSearchResult;
import com.amilesend.tmdb.client.model.search.type.PersonSearchResult;
import com.amilesend.tmdb.client.model.search.type.TvSeriesSearchResult;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/** The response containing the resources that match the external identifier. */
@Builder
@Data
public class FindByIdResponse {
    /**
     * The list of movies. Can be returned by:
     * <ul>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#IMDB}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#FACEBOOK}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#INSTAGRAM}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#TIKTOK}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#TWITTER}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#WIKIDATA}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#YOUTUBE}</li>
     * </ul>
     */
    private final List<MovieSearchResult> movieResults;
    /**
     * The list of persons. Can be returned by:
     * <ul>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#IMDB}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#FACEBOOK}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#INSTAGRAM}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#TIKTOK}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#TWITTER}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#WIKIDATA}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#YOUTUBE}</li>
     * </ul>
     */
    private final List<PersonSearchResult> personResults;
    /**
     * The list of TV shows. Can be returned by:
     * <ul>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#IMDB}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#FACEBOOK}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#INSTAGRAM}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#TVDB}</li></lio>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#TIKTOK}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#TWITTER}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#WIKIDATA}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#YOUTUBE}</li>
     * </ul>
     */
    private final List<TvSeriesSearchResult> tvResults;
    /**
     * The list of TV episodes. Can be returned by:
     * <ul>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#IMDB}</li>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#TVDB}</li></lio>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#WIKIDATA}</li>
     * </ul>
     * */
    private final List<TvEpisodeSearchResult> tvEpisodeResults;
    /**
     * The list of TV seasons. Can be returned by:
     * <ul>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#TVDB}</li></lio>
     *     <li>{@link com.amilesend.tmdb.client.model.find.type.ExternalSource#WIKIDATA}</li>
     * </ul>
     */
    private final List<TvSeasonSearchResult> tvSeasonResults;
}
