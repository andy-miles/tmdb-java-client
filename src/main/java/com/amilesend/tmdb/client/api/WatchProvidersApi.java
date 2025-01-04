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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.connection.Connection;
import com.amilesend.tmdb.client.model.watch.GetAvailableRegionsRequest;
import com.amilesend.tmdb.client.model.watch.GetAvailableRegionsResponse;
import com.amilesend.tmdb.client.model.watch.GetMovieProvidersRequest;
import com.amilesend.tmdb.client.model.watch.GetMovieProvidersResponse;
import com.amilesend.tmdb.client.model.watch.GetTvProvidersRequest;
import com.amilesend.tmdb.client.model.watch.GetTvProvidersResponse;
import lombok.NonNull;

/** TMDB TV Watch Providers API. */
public class WatchProvidersApi extends ApiBase {
    private static final String API_PATH = "/watch/providers/";

    /**
     * Creates a new {@code WatchProvidersApi} object.
     *
     * @param connection the connection
     */
    public WatchProvidersApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the list of available regions.
     *
     * @param request the request
     * @return the response
     * @see GetAvailableRegionsRequest
     * @see GetAvailableRegionsResponse
     */
    public GetAvailableRegionsResponse getAvailableRegions(@NonNull final GetAvailableRegionsRequest request) {
        return executeGet(API_PATH + "regions", request, GetAvailableRegionsResponse.class);
    }

    /**
     * Gets the list of movie watch providers.
     *
     * @param request the request
     * @return the response
     * @see GetMovieProvidersRequest
     * @see GetMovieProvidersResponse
     */
    public GetMovieProvidersResponse getMovieProviders(@NonNull final GetMovieProvidersRequest request) {
        return executeGet(API_PATH + "movie", request, GetMovieProvidersResponse.class);
    }

    /**
     * Gets the list of TV watch providers.
     *
     * @param request the request
     * @return the response
     * @see GetTvProvidersRequest
     * @see GetTvProvidersResponse
     */
    public GetTvProvidersResponse getTvProviders(@NonNull final GetTvProvidersRequest request) {
        return executeGet(API_PATH + "tv", request, GetTvProvidersResponse.class);
    }
}
