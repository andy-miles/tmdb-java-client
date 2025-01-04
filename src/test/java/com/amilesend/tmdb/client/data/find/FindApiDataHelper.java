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
package com.amilesend.tmdb.client.data.find;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.find.FindByIdResponse;
import com.amilesend.tmdb.client.model.find.type.TvEpisodeSearchResult;
import com.amilesend.tmdb.client.model.find.type.TvSeasonSearchResult;
import com.amilesend.tmdb.client.model.search.type.MediaType;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;

import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newMovieSearchResult;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newPersonSearchResult;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newTvSeriesSearchResult;

@UtilityClass
public class FindApiDataHelper {
    /////////////////////
    // FindByIdResponse
    /////////////////////

    public static FindByIdResponse newFindByIdResponse() {
        return FindByIdResponse.builder()
                .movieResults(List.of(
                        newMovieSearchResult(1),
                        newMovieSearchResult(2)))
                .personResults(List.of(
                        newPersonSearchResult(1),
                        newPersonSearchResult(2)))
                .tvResults(List.of(
                        newTvSeriesSearchResult(1),
                        newTvSeriesSearchResult(2)))
                .tvEpisodeResults(List.of(
                        newTvEpisodeSearchResult(1),
                        newTvEpisodeSearchResult(2)))
                .tvSeasonResults(List.of(
                        newTvSeasonSearchResult(1),
                        newTvSeasonSearchResult(2)))
                .build();
    }

    private static TvEpisodeSearchResult newTvEpisodeSearchResult(final int index) {
        return TvEpisodeSearchResult.builder()
                .id(index)
                .name("Episode " + index)
                .airDate(LocalDate.of(2014, 2, index))
                .episodeNumber(index)
                .overview("Overview " + index)
                .productionCode("Production Code " + index)
                .runtime(22)
                .seasonNumber(2)
                .stillPath("/still" + index + ".jpg")
                .voteAverage(128.3D)
                .voteCount(5000)
                .mediaType(MediaType.TV_EPISODE)
                .showId("Show ID Value")
                .build();
    }

    private static TvSeasonSearchResult newTvSeasonSearchResult(final int index) {
        return TvSeasonSearchResult.builder()
                .id(index)
                .name("Season " + index)
                .overview("Season Overview " + index)
                .posterPath("/poster" + index + ".jpg")
                .mediaType(MediaType.TV_SEASON)
                .voteAverage(103.2D)
                .airDate(LocalDate.of(2023, 9, 12))
                .seasonNumber(index)
                .showId(100)
                .episodeCount(12)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/FindApi/";

        public static final SerializedResource FIND_BY_ID_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "FindByIdResponse.json");
    }
}
