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
import com.amilesend.tmdb.client.model.movie.list.GetNowPlayingRequest;
import com.amilesend.tmdb.client.model.movie.list.GetNowPlayingResponse;
import com.amilesend.tmdb.client.model.movie.list.GetPopularRequest;
import com.amilesend.tmdb.client.model.movie.list.GetPopularResponse;
import com.amilesend.tmdb.client.model.movie.list.GetTopRatedRequest;
import com.amilesend.tmdb.client.model.movie.list.GetTopRatedResponse;
import com.amilesend.tmdb.client.model.movie.list.GetUpcomingRequest;
import com.amilesend.tmdb.client.model.movie.list.GetUpcomingResponse;
import lombok.NonNull;

/** TMDB Movie Lists API. */
public class MovieListsApi extends ApiBase {
    private static String API_PATH = "/movie";

    /**
     * Creates a new {@code MovieListsApi} object.
     *
     * @param connection the connection
     */
    public MovieListsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the list of movies that are now playing.
     *
     * @param request the request
     * @return the response
     * @see GetNowPlayingRequest
     * @see GetNowPlayingResponse
     */
    public GetNowPlayingResponse getNowPlaying(@NonNull final GetNowPlayingRequest request) {
        return executeGet(API_PATH + "/now_playing", request, GetNowPlayingResponse.class);
    }

    /**
     * Gets the list of popular movies.
     *
     * @param request the request
     * @return the response
     * @see GetPopularRequest
     * @see GetPopularResponse
     */
    public GetPopularResponse getPopular(@NonNull final GetPopularRequest request) {
        return executeGet(API_PATH +"/popular", request, GetPopularResponse.class);
    }

    /**
     * Gets the list of top-rated movies.
     *
     * @param request the request
     * @return the response
     * @see GetTopRatedRequest
     * @see GetTopRatedResponse
     */
    public GetTopRatedResponse getTopRated(@NonNull final GetTopRatedRequest request) {
        return executeGet(API_PATH + "/top_rated", request, GetTopRatedResponse.class);
    }

    /**
     * Gets the list of upcoming movies.
     *
     * @param request the request
     * @return the response
     * @see GetUpcomingRequest
     * @see GetUpcomingResponse
     */
    public GetUpcomingResponse getUpcoming(@NonNull final GetUpcomingRequest request) {
        return executeGet(API_PATH + "/upcoming", request, GetUpcomingResponse.class);
    }
}
