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
package com.amilesend.tmdb.client.data.account;

import com.amilesend.tmdb.client.model.acount.GetAccountDetailsResponse;
import com.amilesend.tmdb.client.model.acount.GetFavoriteMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetFavoriteTvShowsResponse;
import com.amilesend.tmdb.client.model.acount.GetListsResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvEpisodesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvShowsResponse;
import com.amilesend.tmdb.client.model.acount.GetWatchlistMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetWatchlistTvResponse;
import com.amilesend.tmdb.client.model.acount.type.FavoriteMovie;
import com.amilesend.tmdb.client.model.acount.type.FavoriteTV;
import com.amilesend.tmdb.client.model.acount.type.ListDescriptor;
import com.amilesend.tmdb.client.model.acount.type.RatedMovie;
import com.amilesend.tmdb.client.model.acount.type.RatedTV;
import com.amilesend.tmdb.client.model.acount.type.RatedTVEpisode;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class AccountApiDataValidator {
    ////////////
    // Account
    ////////////
    public static void assertSameAccountDetails(final GetAccountDetailsResponse expected, final GetAccountDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAvatar(), actual.getAvatar()),
                () -> assertEquals(expected.getLanguageCode(), actual.getLanguageCode()),
                () -> assertEquals(expected.getCountryCode(), actual.getCountryCode()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getIncludeAdult(), actual.getIncludeAdult()),
                () -> assertEquals(expected.getUsername(), actual.getUsername()));
    }

    ///////////////////
    // FavoriteMovies
    ///////////////////

    public static void assertSameGetFavoriteMoviesResponse(
            final GetFavoriteMoviesResponse expected,
            final GetFavoriteMoviesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameFavoriteMovies(expected.getResults(), actual.getResults()));
    }

    private static void assertSameFavoriteMovies(final List<FavoriteMovie> expected, final List<FavoriteMovie> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameFavoriteMovie(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameFavoriteMovie(final FavoriteMovie expected, final FavoriteMovie actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()));
    }

    ///////////////
    // FavoriteTV
    ///////////////

    public static void assertSameGetFavoriteTVResponse(
            final GetFavoriteTvShowsResponse expected,
            final GetFavoriteTvShowsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameFavoriteTVShows(expected.getResults(), actual.getResults()));
    }

    private static void assertSameFavoriteTVShows(final List<FavoriteTV> expected, final List<FavoriteTV> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameFavoriteTV(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameFavoriteTV(final FavoriteTV expected, final FavoriteTV actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getFirstAirDate(), actual.getFirstAirDate()),
                () -> assertEquals(expected.getName(), actual.getName()));
    }

    //////////
    // Lists
    //////////

    public static void assertSameGetListsResponse(final GetListsResponse expected, final GetListsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameListDescriptors(expected.getResults(), actual.getResults()));
    }

    private static void assertSameListDescriptors(
            final List<ListDescriptor> expected,
            final List<ListDescriptor> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameListDescriptor(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameListDescriptor(final ListDescriptor expected, final ListDescriptor actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getDescription(), actual.getDescription()),
                () -> assertEquals(expected.getFavoriteCount(), actual.getFavoriteCount()),
                () -> assertEquals(expected.getItemCount(), actual.getItemCount()),
                () -> assertEquals(expected.getLanguageCode(), actual.getLanguageCode()),
                () -> assertEquals(expected.getListType(), actual.getListType()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()));
    }

    ///////////////
    // RatedMovie
    ///////////////

    public static void assertSameGetRatedMoviesResponse(
            final GetRatedMoviesResponse expected,
            final GetRatedMoviesResponse actual){
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameRatedMovies(expected.getResults(), actual.getResults()));
    }

    private static void assertSameRatedMovies(final List<RatedMovie> expected, final List<RatedMovie> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameRatedMovie(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameRatedMovie(final RatedMovie expected, final RatedMovie actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getRating(), actual.getRating()));
    }

    ////////////
    // RatedTV
    ////////////

    public static void assertSameGetRatedTVResponse(final GetRatedTvShowsResponse expected, final GetRatedTvShowsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameRatedTVShows(expected.getResults(), actual.getResults()));
    }

    private static void assertSameRatedTVShows(final List<RatedTV> expected, final List<RatedTV> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameRatedTV(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameRatedTV(final RatedTV expected, final RatedTV actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getFirstAirDate(), actual.getFirstAirDate()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getRating(), actual.getRating()));
    }

    ///////////////////
    // RatedTVEpisode
    ///////////////////

    public static void assertSameGetRatedTVEpisodesResponse(
            final GetRatedTvEpisodesResponse expected,
            final GetRatedTvEpisodesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameRatedTVEpisodes(expected.getResults(), actual.getResults()));
    }

    private static void assertSameRatedTVEpisodes(
            final List<RatedTVEpisode> expected,
            final List<RatedTVEpisode> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameRatedTVEpisode(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameRatedTVEpisode(final RatedTVEpisode expected, final RatedTVEpisode actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> assertEquals(expected.getEpisodeNumber(), actual.getEpisodeNumber()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getProductionCode(), actual.getProductionCode()),
                () -> assertEquals(expected.getRuntime(), actual.getRuntime()),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getShowId(), actual.getShowId()),
                () -> assertEquals(expected.getStillPath(), actual.getStillPath()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getRating(), actual.getRating()));
    }

    ////////////////////
    // WatchlistMovies
    ////////////////////

    public static void assertSameGetWatchlistMoviesResponse(
            final GetWatchlistMoviesResponse expected,
            final GetWatchlistMoviesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameFavoriteMovies(expected.getResults(), actual.getResults()));
    }

    ////////////////
    // WatchlistTV
    ////////////////

    public static void assertSameGetWatchlistTVResponse(
            final GetWatchlistTvResponse expected,
            final GetWatchlistTvResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameFavoriteTVShows(expected.getResults(), actual.getResults()));
    }
}
