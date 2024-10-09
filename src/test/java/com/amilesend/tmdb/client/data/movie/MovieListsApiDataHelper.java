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
package com.amilesend.tmdb.client.data.movie;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.movie.list.GetNowPlayingResponse;
import com.amilesend.tmdb.client.model.movie.list.GetPopularResponse;
import com.amilesend.tmdb.client.model.movie.list.GetTopRatedResponse;
import com.amilesend.tmdb.client.model.movie.list.GetUpcomingResponse;
import com.amilesend.tmdb.client.model.movie.list.type.DateRange;
import com.amilesend.tmdb.client.model.movie.type.Movie;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class MovieListsApiDataHelper {
    public static GetNowPlayingResponse newGetNowPlayingResponse() {
        return GetNowPlayingResponse.builder()
                .dates(newDateRange())
                .page(1)
                .results(List.of(newMovieItem(1), newMovieItem(2)))
                .totalPages(1)
                .totalResults(2)
                .build();
    }

    public static GetPopularResponse newGetPopularResponse() {
        return GetPopularResponse.builder()
                .page(1)
                .results(List.of(newMovieItem(1), newMovieItem(2)))
                .totalPages(1)
                .totalResults(2)
                .build();
    }

    public static GetTopRatedResponse newGetTopRatedResponse() {
        return GetTopRatedResponse.builder()
                .page(1)
                .results(List.of(newMovieItem(1), newMovieItem(2)))
                .totalPages(1)
                .totalResults(2)
                .build();
    }

    public static GetUpcomingResponse newGetUpcomingResponse() {
        return GetUpcomingResponse.builder()
                .dates(newDateRange())
                .page(1)
                .results(List.of(newMovieItem(1), newMovieItem(2)))
                .totalPages(1)
                .totalResults(2)
                .build();
    }

    private static DateRange newDateRange() {
        return DateRange.builder()
                .minimum(LocalDate.of(2024, 5, 1))
                .maximum(LocalDate.of(2024, 6, 1))
                .build();
    }

    private static Movie newMovieItem(final int index) {
        return Movie.builder()
                .id(index)
                .posterPath("/poster.jpg")
                .adult(false)
                .releaseDate(LocalDate.of(2024, 5, 15))
                .originalLanguage(Locale.US.getLanguage())
                .overview("Overview for movie " + index)
                .backdropPath("/backdrop.jpg")
                .popularity(0.6D)
                .title("Movie Title " + index)
                .voteAverage(0.55D)
                .video(false)
                .voteCount(100)
                .genreIds(List.of(1, 3, 4))
                .originalTitle("Original Movie Title " + index)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/MovieListsApi/";

        public static final SerializedResource GET_NOW_PLAYING_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetNowPlayingResponse.json");
        public static final SerializedResource GET_POPULAR_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetPopularResponse.json");
        public static final SerializedResource GET_TOP_RATED_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTopRatedResponse.json");
        public static final SerializedResource GET_UPCOMING_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetUpcomingResponse.json");
    }
}
