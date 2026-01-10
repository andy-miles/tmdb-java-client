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
package com.amilesend.tmdb.client.data.discover;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.discover.DiscoverMoviesResponse;
import com.amilesend.tmdb.client.model.discover.DiscoverTvResponse;
import com.amilesend.tmdb.client.model.discover.type.TvShow;
import com.amilesend.tmdb.client.model.movie.type.Movie;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class DiscoverApiDataHelper {
    ///////////////////////////
    // DiscoverMoviesResponse
    ///////////////////////////

    public static DiscoverMoviesResponse newDiscoverMoviesResponse() {
        return DiscoverMoviesResponse.builder()
                .page(1)
                .results(movieResultList())
                .totalPages(1)
                .totalResults(5)
                .build();
    }

    private static List<Movie> movieResultList() {
        final List<Movie> movieResults = new ArrayList<>(5);
        for (int i = 1; i <= 5; ++i) {
            movieResults.add(newMovieResult(i));
        }

        return movieResults;
    }

    private static Movie newMovieResult(final int index) {
        return Movie.builder()
                .id(index)
                .adult(false)
                .backdropPath("/backdrop.jpg")
                .originalLanguage("en")
                .originalLanguage("Original Title " + index)
                .overview("Summary " + index)
                .popularity(0.75D)
                .posterPath("/poster.jpg")
                .releaseDate(LocalDate.of(2021, 12, 1))
                .title("Title " + index)
                .video(false)
                .voteAverage(0.45D)
                .voteCount(230)
                .genreIds(List.of(12, 56, 4))
                .build();
    }

    ///////////////////////
    // DiscoverTvResponse
    ///////////////////////

    public static DiscoverTvResponse newDiscoverTvResponse() {
        return DiscoverTvResponse.builder()
                .page(1)
                .results(newTvResultList())
                .totalPages(1)
                .totalResults(8)
                .build();
    }


    private static List<TvShow> newTvResultList() {
        final List<TvShow> tvResults = new ArrayList<>(8);
        for (int i = 1; i <= 8; ++i) {
            tvResults.add(newTvResult(i));
        }

        return tvResults;
    }

    private static TvShow newTvResult(final int index) {
        return TvShow.builder()
                .id(index)
                .backdropPath("/backdrop.jpg")
                .firstAirDate(LocalDate.of(2012, 4, 5))
                .name("TV Show " + index)
                .originCountry(List.of(Locale.US.getCountry()))
                .originalLanguage(Locale.US.getLanguage())
                .originalName("TV Show " + index)
                .overview("Summary " + index)
                .popularity(0.83D)
                .posterPath("/poster.jpg")
                .voteAverage(0.7345D)
                .voteCount(234)
                .genreIds(List.of(435,23,1))
                .build();
    }

    @UtilityClass
    public static class Responses {
        public static final SerializedResource DISCOVER_MOVIES_RESPONSE =
                new SerializedResource("/DiscoverApi/DiscoverMoviesResponse.json");
        public static final SerializedResource DISCOVER_TV_RESPONSE =
                new SerializedResource("/DiscoverApi/DiscoverTvResponse.json");
    }
}
