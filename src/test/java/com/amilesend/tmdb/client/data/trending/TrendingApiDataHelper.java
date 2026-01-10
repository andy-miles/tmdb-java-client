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
package com.amilesend.tmdb.client.data.trending;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.trending.GetAllTrendingResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingMoviesResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingPeopleResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingTvResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newMovieSearchResult;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newPersonSearchResult;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newTvSeriesSearchResult;

@UtilityClass
public class TrendingApiDataHelper {
    ///////////////////////////
    // GetAllTrendingResponse
    ///////////////////////////

    public static GetAllTrendingResponse newGetAllTrendingResponse() {
        return GetAllTrendingResponse.builder()
                .page(1)
                .totalResults(6)
                .totalPages(1)
                .results(List.of(
                        newMovieSearchResult(1),
                        newPersonSearchResult(2),
                        newTvSeriesSearchResult(3),
                        newPersonSearchResult(4),
                        newMovieSearchResult(5),
                        newTvSeriesSearchResult(6)))
                .build();
    }

    //////////////////////////////
    // GetTrendingMoviesResponse
    //////////////////////////////

    public static GetTrendingMoviesResponse newGetTrendingMoviesResponse() {
        return GetTrendingMoviesResponse.builder()
                .page(1)
                .totalResults(3)
                .totalPages(1)
                .results(List.of(
                        newMovieSearchResult(1),
                        newMovieSearchResult(2),
                        newMovieSearchResult(3)))
                .build();
    }

    //////////////////////////////
    // GetTrendingPeopleResponse
    //////////////////////////////

    public static GetTrendingPeopleResponse newGetTrendingPeopleResponse() {
        return GetTrendingPeopleResponse.builder()
                .page(1)
                .totalResults(4)
                .totalPages(1)
                .results(List.of(
                        newPersonSearchResult(1),
                        newPersonSearchResult(2),
                        newPersonSearchResult(3),
                        newPersonSearchResult(4)))
                .build();
    }

    //////////////////////////
    // GetTrendingTvResponse
    //////////////////////////

    public static GetTrendingTvResponse newGetTrendingTvResponse() {
        return GetTrendingTvResponse.builder()
                .page(1)
                .totalResults(5)
                .totalPages(1)
                .results(List.of(
                        newTvSeriesSearchResult(1),
                        newTvSeriesSearchResult(2),
                        newTvSeriesSearchResult(3),
                        newTvSeriesSearchResult(4),
                        newTvSeriesSearchResult(5)))
                .build();
    }



    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/TrendingApi/";

        public static final SerializedResource GET_ALL_TRENDING_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAllTrendingResponse.json");
        public static final SerializedResource GET_TRENDING_MOVIES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTrendingMoviesResponse.json");
        public static final SerializedResource GET_TRENDING_PEOPLE_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTrendingPeopleResponse.json");
        public static final SerializedResource GET_TRENDING_TV_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTrendingTvResponse.json");
    }
}
