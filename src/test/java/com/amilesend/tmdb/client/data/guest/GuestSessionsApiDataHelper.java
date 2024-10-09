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
package com.amilesend.tmdb.client.data.guest;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.acount.GetRatedMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvEpisodesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvShowsResponse;
import com.amilesend.tmdb.client.model.acount.type.RatedMovie;
import com.amilesend.tmdb.client.model.acount.type.RatedTV;
import com.amilesend.tmdb.client.model.acount.type.RatedTVEpisode;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class GuestSessionsApiDataHelper {
    ///////////////////////////
    // GetRatedMoviesResponse
    ///////////////////////////

    public static GetRatedMoviesResponse newGetRatedMoviesResponse() {
        return GetRatedMoviesResponse.builder()
                .page(1)
                .results(List.of(newRatedMovie(1), newRatedMovie(2), newRatedMovie(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    private static RatedMovie newRatedMovie(final int index) {
        return RatedMovie.builder()
                .id(index)
                .adult(false)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(1, 3, 4))
                .originalLanguage(Locale.US.getLanguage())
                .originalTitle("Original Title " + index)
                .overview("Overview " + index)
                .popularity(0.70D)
                .posterPath("/poster" + index +  ".jpg")
                .releaseDate(LocalDate.of(2015, 4, 12))
                .title("Title " + index)
                .video(false)
                .voteAverage(0.5D)
                .voteCount(500)
                .rating(80)
                .build();
    }

    ////////////////////////////
    // GetRatedTvShowsResponse
    ////////////////////////////

    public static GetRatedTvShowsResponse newGetRatedTvShowsResponse() {
        return GetRatedTvShowsResponse.builder()
                .page(1)
                .results(List.of(newRatedTV(1), newRatedTV(2), newRatedTV(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    private static RatedTV newRatedTV(final int index) {
        return RatedTV.builder()
                .id(index)
                .name("Name " + index)
                .adult(false)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(1, 3, 4))
                .originCountry(List.of(Locale.US.getCountry()))
                .originalLanguage(Locale.US.getLanguage())
                .originalName("Original Name " + index)
                .overview("Overview " + index)
                .popularity(0.70D)
                .posterPath("/poster" + index +  ".jpg")
                .firstAirDate(LocalDate.of(2014, 7,3))
                .voteAverage(0.5D)
                .voteCount(500)
                .rating(80)
                .build();
    }

    ///////////////////////////////
    // GetRatedTvEpisodesResponse
    ///////////////////////////////

    public static GetRatedTvEpisodesResponse newGetRatedTvEpisodesResponse() {
        return GetRatedTvEpisodesResponse.builder()
                .page(1)
                .results(List.of(newRatedTVEpisode(1), newRatedTVEpisode(2), newRatedTVEpisode(3)))
                .totalPages(1)
                .totalResults(3)

                .build();
    }

    private static RatedTVEpisode newRatedTVEpisode(final int index) {
        return RatedTVEpisode.builder()
                .id(index)
                .name("TV Episode " + index)
                .airDate(LocalDate.of(2020, 6, 1))
                .episodeNumber(index)
                .overview("Episode Overview " + index)
                .productionCode("Production Code " + index)
                .runtime(22)
                .seasonNumber(1)
                .showId(100)
                .stillPath("/stills" + index)
                .voteAverage(0.65D)
                .voteCount(500)
                .rating(80)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/GuestSessionsApi/";

        public static final SerializedResource GET_RATED_MOVIES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetRatedMoviesResponse.json");
        public static final SerializedResource GET_RATED_TV_SHOWS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetRatedTvShowsResponse.json");
        public static final SerializedResource GET_RATED_TV_EPISODES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetRatedTvEpisodesResponse.json");
    }
}
