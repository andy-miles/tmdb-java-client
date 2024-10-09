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
package com.amilesend.tmdb.client.data.tv;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.tv.series.list.GetAiringTodayResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetOnTheAirResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetPopularResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetTopRatedResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newTvSeries;

@UtilityClass
public class TvSeriesListsApiDataHelper {
    ///////////////////////////
    // GetAiringTodayResponse
    ///////////////////////////

    public static GetAiringTodayResponse newGetAiringTodayResponse() {
        return GetAiringTodayResponse.builder()
                .page(1)
                .totalResults(4)
                .totalPages(1)
                .results(List.of(
                        newTvSeries(1),
                        newTvSeries(2),
                        newTvSeries(3),
                        newTvSeries(4)))
                .build();
    }

    ////////////////////////
    // GetOnTheAirResponse
    ////////////////////////

    public static GetOnTheAirResponse newGetOnTheAirResponse() {
        return GetOnTheAirResponse.builder()
                .page(1)
                .totalResults(4)
                .totalPages(1)
                .results(List.of(
                        newTvSeries(5),
                        newTvSeries(6),
                        newTvSeries(7),
                        newTvSeries(8)))
                .build();
    }

    ///////////////////////
    // GetPopularResponse
    ///////////////////////

    public static GetPopularResponse newGetPopularResponse() {
        return GetPopularResponse.builder()
                .page(1)
                .totalResults(4)
                .totalPages(1)
                .results(List.of(
                        newTvSeries(9),
                        newTvSeries(10),
                        newTvSeries(11),
                        newTvSeries(12)))
                .build();
    }

    ////////////////////////
    // GetTopRatedResponse
    ////////////////////////

    public static GetTopRatedResponse newGetTopRatedResponse() {
        return GetTopRatedResponse.builder()
                .page(1)
                .totalResults(4)
                .totalPages(1)
                .results(List.of(
                        newTvSeries(13),
                        newTvSeries(14),
                        newTvSeries(15),
                        newTvSeries(16)))
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/TvSeriesListsApi/";

        public static final SerializedResource GET_AIRING_TODAY_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAiringTodayResponse.json");
        public static final SerializedResource GET_ON_THE_AIR_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetOnTheAirResponse.json");
        public static final SerializedResource GET_POPULAR_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetPopularResponse.json");
        public static final SerializedResource GET_TOP_RATED_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTopRatedResponse.json");
    }
}
