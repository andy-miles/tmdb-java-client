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

import com.amilesend.client.connection.Connection;
import com.amilesend.tmdb.client.model.discover.DiscoverMoviesRequest;
import com.amilesend.tmdb.client.model.discover.DiscoverMoviesResponse;
import com.amilesend.tmdb.client.model.discover.DiscoverTvRequest;
import com.amilesend.tmdb.client.model.discover.DiscoverTvResponse;
import lombok.NonNull;

/** TMDB Discover API. */
public class DiscoverApi extends ApiBase {
    /**
     * Creates a new {@code DiscoverApi} object.
     *
     * @param connection the connection
     */
    public DiscoverApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets a paginated list of movies.
     *
     * @param request the request that defines filter criteria
     * @return the response containing th paginated list of movies
     */
    public DiscoverMoviesResponse discoverMovies(@NonNull final DiscoverMoviesRequest request) {
        return executeGet("/discover/movie", request, DiscoverMoviesResponse.class);
    }

    /**
     * Gets a paginated list of TV shows.
     *
     * @param request the request that defines filter criteria
     * @return the response containing th paginated list of TV shows
     */
    public DiscoverTvResponse discoverTv(@NonNull final DiscoverTvRequest request) {
        return executeGet("/discover/tv", request, DiscoverTvResponse.class);
    }
}
