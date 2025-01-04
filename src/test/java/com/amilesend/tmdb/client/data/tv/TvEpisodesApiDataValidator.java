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
package com.amilesend.tmdb.client.data.tv;

import com.amilesend.tmdb.client.model.tv.episodes.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetEpisodeDetailsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetVideosResponse;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameCastCredits;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameCrewCredits;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameVideos;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameChanges;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class TvEpisodesApiDataValidator {
    ////////////////////////////
    // GetEpisodeDetailsResponse
    ////////////////////////////

    public static void assertSameGetEpisodeDetailsResponse(
            final GetEpisodeDetailsResponse expected,
            final GetEpisodeDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> assertSameCrewCredits(expected.getCrew(), actual.getCrew()),
                () -> assertEquals(expected.getEpisodeNumber(), actual.getEpisodeNumber()),
                () -> assertSameCastCredits(expected.getGuestStars(), actual.getGuestStars()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getProductionCode(), actual.getProductionCode()),
                () -> assertEquals(expected.getRuntime(), actual.getRuntime()),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getStillPath(), actual.getStillPath()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()));
    }

    /////////////////////////////
    // GetAccountStatesResponse
    /////////////////////////////

    public static void assertSameGetAccountStatesResponse(
            final GetAccountStatesResponse expected,
            final GetAccountStatesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getRated(), actual.getRated()),
                () -> assertEquals(expected.getFavorite(), actual.getFavorite()),
                () -> assertEquals(expected.getWatchlist(), actual.getWatchlist()),
                () -> assertEquals(expected.getId(), actual.getId()));
    }

    ///////////////////////
    // GetChangesResponse
    ///////////////////////

    public static void assertSameGetChangesResponse(
            final GetChangesResponse expected,
            final GetChangesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertSameChanges(expected.getChanges(), actual.getChanges());
    }


    ///////////////////////
    // GetCreditsResponse
    ///////////////////////

    public static void assertSameGetCreditsResponse(
            final GetCreditsResponse expected,
            final GetCreditsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameCastCredits(expected.getCast(), actual.getCast()),
                () -> assertSameCrewCredits(expected.getCrew(), actual.getCrew()),
                () -> assertSameCastCredits(expected.getGuestStars(), actual.getGuestStars()));
    }

    ///////////////////////////
    // GetExternalIdsResponse
    ///////////////////////////

    public static void assertSameGetExternalIdsResponse(
            final GetExternalIdsResponse expected,
            final GetExternalIdsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getImdbId(), actual.getImdbId()),
                () -> assertEquals(expected.getTvdbId(), actual.getTvdbId()),
                () -> assertEquals(expected.getTvrageId(), actual.getTvrageId()));
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static void assertSameGetImagesResponse(final GetImagesResponse expected, final GetImagesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getStills(), actual.getStills()));
    }

    ////////////////////////////
    // GetTranslationsResponse
    ////////////////////////////

    public static void assertSameGetTranslationsResponse(
            final GetTranslationsResponse expected,
            final GetTranslationsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getTranslations(), actual.getTranslations()));
    }

    //////////////////////
    // GetVideosResponse
    //////////////////////

    public static void assertSameGetVideosResponse(final GetVideosResponse expected, final GetVideosResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameVideos(expected.getResults(), actual.getResults()));
    }
}
