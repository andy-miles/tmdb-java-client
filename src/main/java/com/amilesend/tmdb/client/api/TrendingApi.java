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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.connection.Connection;
import com.amilesend.tmdb.client.model.trending.GetAllTrendingRequest;
import com.amilesend.tmdb.client.model.trending.GetAllTrendingResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingMoviesRequest;
import com.amilesend.tmdb.client.model.trending.GetTrendingMoviesResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingPeopleRequest;
import com.amilesend.tmdb.client.model.trending.GetTrendingPeopleResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingTvRequest;
import com.amilesend.tmdb.client.model.trending.GetTrendingTvResponse;
import com.amilesend.tmdb.client.model.trending.type.GetTrendingRequestBase;
import lombok.NonNull;

/** TMDB Trending API. */
public class TrendingApi  extends ApiBase {
    private static final String API_PATH = "/trending/";
    private static final String ALL_PATH = "all/";
    private static final String MOVIES_PATH = "movie/";
    private static final String PEOPLE_PATH = "person/";
    private static final String TV_PATH = "tv/";

    /**
     * Creates a new {@code TrendingApi} object.
     *
     * @param connection the connection
     */
    public TrendingApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the list of trending resources (e.g., movies, tv shows, people).
     *
     * @param request the request
     * @return the response
     * @see GetAllTrendingRequest
     * @see GetAllTrendingResponse
     */
    public GetAllTrendingResponse getAllTrending(@NonNull final GetAllTrendingRequest request) {
        return executeGet(getApiPath(ALL_PATH, request), request, GetAllTrendingResponse.class);
    }

    /**
     * Gets the list of trending movies.
     *
     * @param request the request
     * @return the response
     * @see GetTrendingMoviesRequest
     * @see GetTrendingMoviesResponse
     */
    public GetTrendingMoviesResponse getTrendingMovies(@NonNull final GetTrendingMoviesRequest request) {
        return executeGet(getApiPath(MOVIES_PATH, request), request, GetTrendingMoviesResponse.class);
    }

    /**
     * Gets the list of trending people.
     *
     * @param request the request
     * @return the response
     * @see GetTrendingPeopleRequest
     * @see GetTrendingPeopleResponse
     */
    public GetTrendingPeopleResponse getTrendingPeople(@NonNull final GetTrendingPeopleRequest request) {
        return executeGet(getApiPath(PEOPLE_PATH, request), request, GetTrendingPeopleResponse.class);
    }

    /**
     * Gets the list of trending TV shows.
     *
     * @param request the request
     * @return the response
     * @see GetTrendingTvRequest
     * @see GetTrendingTvResponse
     */
    public GetTrendingTvResponse getTrendingTv(@NonNull final GetTrendingTvRequest request) {
        return executeGet(getApiPath(TV_PATH, request), request, GetTrendingTvResponse.class);
    }

    private static String getApiPath(final String apiMethodPath, final GetTrendingRequestBase request) {
        return new StringBuilder(API_PATH)
                .append(apiMethodPath)
                .append("/")
                .append(request.getTimeWindow().getValue())
                .toString();
    }
}
