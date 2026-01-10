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
import com.amilesend.tmdb.client.model.keyword.GetKeywordDetailsRequest;
import com.amilesend.tmdb.client.model.keyword.GetKeywordDetailsResponse;

public class KeywordsApi extends ApiBase {
    private static String API_PATH = "/keyword/";

    /**
     * Creates a new {@code KeywordsApi} object.
     *
     * @param connection the connection
     */
    public KeywordsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the keyword details for the given identifier.
     *
     * @param request the request
     * @return the details
     * @see GetKeywordDetailsResponse
     */
    public GetKeywordDetailsResponse getKeywordDetails(final GetKeywordDetailsRequest request) {
        final String apiPath = new StringBuilder(API_PATH)
                .append(request.getKeywordId())
                .toString();
        return executeGet(apiPath, request, GetKeywordDetailsResponse.class);
    }
}
