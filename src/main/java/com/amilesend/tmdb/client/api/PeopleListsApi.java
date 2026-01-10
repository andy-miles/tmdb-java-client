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
package com.amilesend.tmdb.client.api;

import com.amilesend.client.connection.Connection;
import com.amilesend.tmdb.client.model.people.lists.GetPopularPersonsRequest;
import com.amilesend.tmdb.client.model.people.lists.GetPopularPersonsResponse;
import lombok.NonNull;

/** TMDB People Lists API. */
public class PeopleListsApi extends ApiBase {
    private static String API_PATH = "/person/popular";

    /**
     * Creates a new {@code PeopleListsApi} object.
     *
     * @param connection the connection
     */
    public PeopleListsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets an ordered list of persons ordered by popularity.
     *
     * @param request the request
     * @return the response
     * @see GetPopularPersonsRequest
     * @see GetPopularPersonsResponse
     */
    public GetPopularPersonsResponse getPopular(@NonNull final GetPopularPersonsRequest request) {
        return executeGet(API_PATH, request, GetPopularPersonsResponse.class);
    }
}
