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
import com.amilesend.tmdb.client.model.find.FindByIdRequest;
import com.amilesend.tmdb.client.model.find.FindByIdResponse;
import lombok.NonNull;

/** TMDB Find API. */
public class FindApi extends ApiBase {
    /**
     * Creates a new {@code FindApi} object.
     *
     * @param connection the connection
     */
    public FindApi(final Connection connection) {
        super(connection);
    }

    /**
     * Find resources based on an external identifier.
     *
     * @param request the request
     * @return the response
     * @see FindByIdRequest
     * @see FindByIdResponse
     */
    public FindByIdResponse findById(@NonNull final FindByIdRequest request) {
        return executeGet("/find/" + request.getExternalId(), request, FindByIdResponse.class);
    }
}
