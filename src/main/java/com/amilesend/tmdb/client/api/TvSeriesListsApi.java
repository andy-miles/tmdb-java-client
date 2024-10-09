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
import com.amilesend.tmdb.client.model.tv.series.list.GetAiringTodayRequest;
import com.amilesend.tmdb.client.model.tv.series.list.GetAiringTodayResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetOnTheAirRequest;
import com.amilesend.tmdb.client.model.tv.series.list.GetOnTheAirResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetPopularRequest;
import com.amilesend.tmdb.client.model.tv.series.list.GetPopularResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetTopRatedRequest;
import com.amilesend.tmdb.client.model.tv.series.list.GetTopRatedResponse;
import lombok.NonNull;

/** TMDB TV Series Lists API. */
public class TvSeriesListsApi extends ApiBase {
    private static final String API_PATH = "/tv/";

    /**
     * Creates a new {@code TvSeriesListsApi} object.
     *
     * @param connection the connection
     */
    public TvSeriesListsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the list of TV series that is airing today.
     *
     * @param request the request
     * @return the response
     * @see GetAiringTodayRequest
     * @see GetAiringTodayResponse
     */
    public GetAiringTodayResponse getAiringToday(@NonNull final GetAiringTodayRequest request) {
        return executeGet(API_PATH + "airing_today", request, GetAiringTodayResponse.class);
    }

    /**
     * Gets the list of TV series that is airing in the next 7 days.
     *
     * @param request the request
     * @return the response
     * @see GetOnTheAirResponse
     * @see GetOnTheAirResponse
     */
    public GetOnTheAirResponse getOnTheAir(@NonNull final GetOnTheAirRequest request) {
        return executeGet(API_PATH + "on_the_air", request, GetOnTheAirResponse.class);
    }

    /**
     * Gets the list of TV series that are popular.
     *
     * @param request the request
     * @return the response
     * @see GetPopularRequest
     * @see GetPopularResponse
     */
    public GetPopularResponse getPopular(@NonNull final GetPopularRequest request) {
        return executeGet(API_PATH + "popular", request, GetPopularResponse.class);
    }

    /**
     * Gets the list of TV series that are top-rated.
     *
     * @param request the request
     * @return the response
     * @see GetTopRatedRequest
     * @see GetTopRatedResponse
     */
    public GetTopRatedResponse getTopRated(@NonNull final GetTopRatedRequest request) {
        return executeGet(API_PATH + "top_rated", request, GetTopRatedResponse.class);
    }
}
