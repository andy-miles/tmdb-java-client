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
import com.amilesend.tmdb.client.model.acount.GetRatedMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvEpisodesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvShowsResponse;
import com.amilesend.tmdb.client.model.guest.GetRatedMoviesRequest;
import com.amilesend.tmdb.client.model.guest.GetRatedTvEpisodesRequest;
import com.amilesend.tmdb.client.model.guest.GetRatedTvShowsRequest;
import com.amilesend.tmdb.client.model.guest.type.GuestSessionRequestBase;
import lombok.NonNull;

public class GuestSessionsApi extends ApiBase {
    private static final String API_PATH = "/guest_session/";

    /**
     * Creates a new {@code GuestSessionsApi} object.
     *
     * @param connection the connection
     */
    public GuestSessionsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the list of movies that a guest user rated.
     *
     * @param request the request
     * @return the list of rated movies
     * @see GetRatedMoviesRequest
     * @see GetRatedMoviesResponse
     */
    public GetRatedMoviesResponse getRatedMovies(@NonNull final GetRatedMoviesRequest request) {
        return executeGet(getApiPath(request, "/rated/movies"), request, GetRatedMoviesResponse.class);
    }

    /**
     * Gets the list of TV shows that a guest user rated.
     *
     * @param request the request
     * @return the list of rated TV shows
     * @see GetRatedTvShowsRequest
     * @see GetRatedTvShowsResponse
     */
    public GetRatedTvShowsResponse getRatedTvShows(@NonNull final GetRatedTvShowsRequest request) {
        return executeGet(getApiPath(request, "/rated/tv"), request, GetRatedTvShowsResponse.class);
    }

    /**
     * Gets the list of TV episodes that a guest user rated.
     *
     * @param request the request
     * @return the list of TV episodes
     * @see GetRatedTvEpisodesRequest
     * @see GetRatedTvEpisodesResponse
     */
    public GetRatedTvEpisodesResponse getRatedTvEpisodes(@NonNull final GetRatedTvEpisodesRequest request) {
        return executeGet(
                getApiPath(request, "/rated/tv/episodes"),
                request,
                GetRatedTvEpisodesResponse.class);
    }

    private static String getApiPath(final GuestSessionRequestBase request, final String subApiPath) {
        return new StringBuilder(API_PATH)
                .append(request.getGuestSessionId())
                .append(subApiPath)
                .toString();
    }
}
