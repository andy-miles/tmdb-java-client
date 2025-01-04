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
package com.amilesend.tmdb.client.data.account;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.acount.AddFavoriteResponse;
import com.amilesend.tmdb.client.model.acount.AddWatchlistResponse;
import com.amilesend.tmdb.client.model.acount.GetAccountDetailsResponse;
import com.amilesend.tmdb.client.model.acount.GetFavoriteMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetFavoriteTvShowsResponse;
import com.amilesend.tmdb.client.model.acount.GetListsResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvEpisodesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvShowsResponse;
import com.amilesend.tmdb.client.model.acount.GetWatchlistMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetWatchlistTvResponse;
import com.amilesend.tmdb.client.model.acount.type.Avatar;
import com.amilesend.tmdb.client.model.acount.type.FavoriteMovie;
import com.amilesend.tmdb.client.model.acount.type.FavoriteTV;
import com.amilesend.tmdb.client.model.acount.type.Gravatar;
import com.amilesend.tmdb.client.model.acount.type.ListDescriptor;
import com.amilesend.tmdb.client.model.acount.type.RatedMovie;
import com.amilesend.tmdb.client.model.acount.type.RatedTV;
import com.amilesend.tmdb.client.model.acount.type.RatedTVEpisode;
import com.amilesend.tmdb.client.model.acount.type.TMDBAvatar;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class AccountApiDataHelper {
    ////////////
    // Account
    ////////////

    public static GetAccountDetailsResponse newAccountDetails() {
        return GetAccountDetailsResponse.builder()
                .id(1)
                .name("AccountNameValue")
                .avatar(Avatar.builder()
                        .gravatar(Gravatar.builder()
                                .hash("GravatarHashValue")
                                .build())
                        .tmdb(TMDBAvatar.builder()
                                .avatarPath("/AvatarPath.jpg")
                                .build())
                        .build())
                .languageCode(Locale.US.getLanguage())
                .countryCode(Locale.US.getCountry())
                .includeAdult(false)
                .username("UsernameValue")
                .build();
    }

    //////////////////
    // FavoriteMovie
    //////////////////

    public static GetFavoriteMoviesResponse newGetFavoriteMoviesResponse() {
        return GetFavoriteMoviesResponse.builder()
                .page(1)
                .results(List.of(newFavoriteMovie(1), newFavoriteMovie(2), newFavoriteMovie(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    private static FavoriteMovie newFavoriteMovie(final int index) {
        return FavoriteMovie.builder()
                .id(index)
                .adult(false)
                .backdropPath("/backdrop.jpg")
                .genreIds(List.of(5, 6, 7))
                .originalLanguage(Locale.US.getLanguage())
                .originalTitle("Original Title " + index)
                .overview("Movie Overview " + index)
                .popularity(0.7D)
                .posterPath("/poster.jpg")
                .releaseDate(LocalDate.of(2000, 6, 7))
                .title("Movie " + index)
                .video(false)
                .voteAverage(0.6D)
                .voteCount(1000)
                .build();
    }

    ///////////////
    // FavoriteTV
    ///////////////

    public static GetFavoriteTvShowsResponse newGetFavoriteTVResponse() {
        return GetFavoriteTvShowsResponse.builder()
                .page(1)
                .results(List.of(newFavoriteTV(1), newFavoriteTV(2), newFavoriteTV(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    private static FavoriteTV newFavoriteTV(final int index) {
        return FavoriteTV.builder()
                .id(index)
                .name("TV Show Name " + index)
                .adult(false)
                .backdropPath("/backdrop.jpg")
                .genreIds(List.of(5, 6, 7))
                .originCountry(List.of(Locale.US.getCountry()))
                .originalLanguage(Locale.US.getLanguage())
                .originalName("Original TV Show Name " + index)
                .overview("Movie Overview " + index)
                .popularity(0.7D)
                .posterPath("/poster.jpg")
                .firstAirDate(LocalDate.of(2014, 2, 1))
                .voteAverage(0.6D)
                .voteCount(1000)
                .build();
    }

    //////////
    // Lists
    //////////

    public static GetListsResponse newGetListsResponse() {
        return GetListsResponse.builder()
                .page(1)
                .results(List.of(newListDescriptor(1), newListDescriptor(2), newListDescriptor(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    private static ListDescriptor newListDescriptor(final int index) {
        return ListDescriptor.builder()
                .id(index)
                .name("List " + index)
                .description("List Description " + index)
                .favoriteCount(4)
                .itemCount(10)
                .languageCode(Locale.US.getLanguage())
                .listType("List Type")
                .posterPath("/poster.jpg")
                .build();
    }

    ////////////////
    // RatedMovies
    ////////////////

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
                .backdropPath("/backdrop.jpg")
                .genreIds(List.of(5, 6, 7))
                .originalLanguage(Locale.US.getLanguage())
                .originalTitle("Original Title " + index)
                .overview("Movie Overview " + index)
                .popularity(0.7D)
                .posterPath("/poster.jpg")
                .releaseDate(LocalDate.of(2000, 6, 7))
                .title("Movie " + index)
                .video(false)
                .voteAverage(0.6D)
                .voteCount(1000)
                .rating(8)
                .build();
    }

    ////////////
    // RatedTV
    ////////////

    public static GetRatedTvShowsResponse newGetRatedTVResponse() {
        return GetRatedTvShowsResponse.builder()
                .page(1)
                .results(List.of(newRatedTV(1), newRatedTV(2), newRatedTV(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    public static RatedTV newRatedTV(final int index) {
            return RatedTV.builder()
                    .id(index)
                    .name("TV Show Name " + index)
                    .adult(false)
                    .backdropPath("/backdrop.jpg")
                    .genreIds(List.of(5, 6, 7))
                    .originCountry(List.of(Locale.US.getCountry()))
                    .originalLanguage(Locale.US.getLanguage())
                    .originalName("Original TV Show Name " + index)
                    .overview("Movie Overview " + index)
                    .popularity(0.7D)
                    .posterPath("/poster.jpg")
                    .firstAirDate(LocalDate.of(2014, 2, 1))
                    .voteAverage(0.6D)
                    .voteCount(1000)
                    .rating(9)
                    .build();
    }

    ///////////////////
    // RatedTVEpisode
    ///////////////////

    public static GetRatedTvEpisodesResponse newGetRatedTVEpisodesResponse() {
        return GetRatedTvEpisodesResponse.builder()
                .page(1)
                .results(List.of(newRatedTVEpisode(1), newRatedTVEpisode(2), newRatedTVEpisode(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    public static RatedTVEpisode newRatedTVEpisode(final int index) {
        return RatedTVEpisode.builder()
                .id(index)
                .name("Episode " + index)
                .airDate(LocalDate.of(2017, 9, 16))
                .episodeNumber(1)
                .overview("Episode Summary " + index)
                .productionCode("ProdCode")
                .runtime(30)
                .seasonNumber(1)
                .showId(10 + index)
                .stillPath("/stillPath.jpg")
                .voteAverage(0.75D)
                .voteCount(534)
                .rating(9)
                .build();
    }

    ////////////////////
    // WatchlistMovies
    ////////////////////

    public static GetWatchlistMoviesResponse newGetWatchlistMoviesResponse() {
        return GetWatchlistMoviesResponse.builder()
                .page(1)
                .results(List.of(newFavoriteMovie(1), newFavoriteMovie(2), newFavoriteMovie(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    ////////////////
    // WatchlistTV
    ////////////////

    public static GetWatchlistTvResponse newGetWatchlistTVResponse() {
        return GetWatchlistTvResponse.builder()
                .page(1)
                .results(List.of(newFavoriteTV(1), newFavoriteTV(2), newFavoriteTV(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    ////////
    // Add
    ////////

    public static AddFavoriteResponse newAddFavoriteResponse() {
        return AddFavoriteResponse.builder()
                .statusCode(201)
                .statusMessage("Success")
                .build();
    }

    public static AddWatchlistResponse newAddWatchlistResponse() {
        return AddWatchlistResponse.builder()
                .statusCode(201)
                .statusMessage("Success")
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/AccountApi/";
        public static final SerializedResource GET_ACCOUNT_DETAILS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAccountDetailsResponse.json");
        public static final SerializedResource GET_FAVORITE_MOVIES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetFavoriteMoviesResponse.json");
        public static final SerializedResource GET_FAVORITE_TV_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetFavoriteTVResponse.json");
        public static final SerializedResource GET_LISTS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetListsResponse.json");
        public static final SerializedResource GET_RATED_MOVIES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetRatedMoviesResponse.json");
        public static final SerializedResource GET_RATED_TV_EPISODES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetRatedTVEpisodesResponse.json");
        public static final SerializedResource GET_RATED_TV_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetRatedTVResponse.json");
        public static final SerializedResource GET_WATCHLIST_MOVIES_RESPONSE = GET_FAVORITE_MOVIES_RESPONSE;
        public static final SerializedResource GET_WATCHLIST_TV_RESPONSE = GET_FAVORITE_TV_RESPONSE;
        public static final SerializedResource POST_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "PostResponse.json");
    }
}
