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
import com.amilesend.tmdb.client.model.change.GetMovieChangesRequest;
import com.amilesend.tmdb.client.model.change.GetMovieChangesResponse;
import com.amilesend.tmdb.client.model.change.GetPersonChangesRequest;
import com.amilesend.tmdb.client.model.change.GetPersonChangesResponse;
import com.amilesend.tmdb.client.model.change.GetTvChangesRequest;
import com.amilesend.tmdb.client.model.change.GetTvChangesResponse;
import lombok.NonNull;

/** TMDB Changes API. */
public class ChangesApi extends ApiBase {
    /**
     * Creates a new {@code ChangesApi} object.
     *
     * @param connection the connection
     */
    public ChangesApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the list of movie changes for the given request.
     *
     * @param request the request
     * @return the response containing the list of movie changes
     * @see GetMovieChangesRequest
     * @see GetMovieChangesResponse
     */
    public GetMovieChangesResponse getMovieChanges(@NonNull final GetMovieChangesRequest request) {
        return executeGet("/movie/changes", request, GetMovieChangesResponse.class);
    }

    /**
     * Gets the list of person changes for the given request.
     *
     * @param request the request
     * @return the response containing the list of person changes
     * @see GetPersonChangesRequest
     * @see GetPersonChangesResponse
     */
    public GetPersonChangesResponse getPersonChanges(@NonNull final GetPersonChangesRequest request) {
        return executeGet("/person/changes", request, GetPersonChangesResponse.class);
    }

    /**
     * Gets the list of TV show changes for the given request.
     *
     * @param request the request
     * @return the response containing the list of TV show changes
     * @see GetTvChangesRequest
     * @see GetTvChangesResponse
     */
    public GetTvChangesResponse getTvChanges(@NonNull final GetTvChangesRequest request) {
        return executeGet("/tv/changes", request, GetTvChangesResponse.class);
    }
}
