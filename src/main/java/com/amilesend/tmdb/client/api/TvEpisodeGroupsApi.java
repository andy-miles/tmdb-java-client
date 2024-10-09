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
import com.amilesend.tmdb.client.model.tv.episodes.groups.GetTvEpisodeGroupDetailsRequest;
import com.amilesend.tmdb.client.model.tv.episodes.groups.GetTvEpisodeGroupDetailsResponse;
import lombok.NonNull;

/** TMDB TV Episode Groups API. */
public class TvEpisodeGroupsApi extends ApiBase {
    private static final String API_PATH = "/tv/episode_group/";

    /**
     * Creates a new {@code TvEpisodeGroupsApi} object.
     *
     * @param connection the connection
     */
    public TvEpisodeGroupsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the details for a specific TV episode group.
     *
     * @param request the request
     * @return the response
     * @see GetTvEpisodeGroupDetailsRequest
     * @see GetTvEpisodeGroupDetailsResponse
     */
    public GetTvEpisodeGroupDetailsResponse getDetails(@NonNull final GetTvEpisodeGroupDetailsRequest request) {
        return executeGet(
                API_PATH + request.getTvEpisodeGroupId(),
                request,
                GetTvEpisodeGroupDetailsResponse.class);
    }
}
