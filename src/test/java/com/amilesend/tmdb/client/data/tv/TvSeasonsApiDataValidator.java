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
package com.amilesend.tmdb.client.data.tv;

import com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator;
import com.amilesend.tmdb.client.model.tv.episodes.GetEpisodeDetailsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetAggregateCreditsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetSeasonDetailsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetWatchProvidersResponse;
import com.amilesend.tmdb.client.model.tv.seasons.type.AggregateCastCredit;
import com.amilesend.tmdb.client.model.tv.seasons.type.AggregateCrewCredit;
import com.amilesend.tmdb.client.model.tv.seasons.type.EpisodeChangeDescriptor;
import com.amilesend.tmdb.client.model.type.Change;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateListOf;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateNamedResource;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateResource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class TvSeasonsApiDataValidator {
    /////////////////////////////
    // GetSeasonDetailsResponse
    /////////////////////////////

    public static void assertSameGetSeasonDetailsResponse(
            final GetSeasonDetailsResponse expected,
            final GetSeasonDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getSeasonId(), actual.getSeasonId()),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> validateListOf(
                        expected.getEpisodes(),
                        actual.getEpisodes(),
                        TvSeasonsApiDataValidator::assertSameEpisode),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D));
    }

    private static void assertSameEpisode(
            final GetEpisodeDetailsResponse expected,
            final GetEpisodeDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> validateListOf(
                        expected.getCrew(),
                        actual.getCrew(),
                        MoviesApiDataValidator::assertSameCrewCredit),
                () -> assertEquals(expected.getEpisodeNumber(), actual.getEpisodeNumber()),
                () -> validateListOf(
                        expected.getGuestStars(),
                        actual.getGuestStars(),
                        MoviesApiDataValidator::assertSameCastCredit),
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
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getRated(), actual.getRated()),
                () -> assertEquals(expected.getFavorite(), actual.getFavorite()),
                () -> assertEquals(expected.getWatchlist(), actual.getWatchlist()));
    }

    ////////////////////////////////
    // GetAggregateCreditsResponse
    ////////////////////////////////

    public static void assertSameGetAggregateCreditsResponse(
            final GetAggregateCreditsResponse expected,
            final GetAggregateCreditsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getCast(),
                        actual.getCast(),
                        TvSeasonsApiDataValidator::assertSameAggregateCastCredit),
                () -> validateListOf(
                        expected.getCrew(),
                        actual.getCrew(),
                        TvSeasonsApiDataValidator::assertSameAggregateCrewCredit));
    }

    public static void assertSameAggregateCrewCredit(
            final AggregateCrewCredit expected,
            final AggregateCrewCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getKnownForDepartment(), actual.getKnownForDepartment()),
                () -> assertEquals(expected.getProfilePath(), actual.getProfilePath()),
                () -> assertEquals(expected.getJobs(), actual.getJobs()),
                () -> assertEquals(expected.getDepartment(), actual.getDepartment()),
                () -> assertEquals(expected.getTotalEpisodeCount(), actual.getTotalEpisodeCount()));
    }

    public static void assertSameAggregateCastCredit(
            final AggregateCastCredit expected,
            final AggregateCastCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getKnownForDepartment(), actual.getKnownForDepartment()),
                () -> assertEquals(expected.getProfilePath(), actual.getProfilePath()),
                () -> assertEquals(expected.getRoles(), actual.getRoles()),
                () -> assertEquals(expected.getTotalEpisodeCount(), actual.getTotalEpisodeCount()),
                () -> assertEquals(expected.getOrder(), actual.getOrder()));
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

        validateListOf(expected.getChanges(), actual.getChanges(), TvSeasonsApiDataValidator::assertSameChange);
    }

    public static void assertSameChange(
            final Change<EpisodeChangeDescriptor> expected,
            final Change<EpisodeChangeDescriptor> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getKey(), actual.getKey()),
                () -> validateListOf(
                        expected.getItems(),
                        actual.getItems(),
                        MoviesApiDataValidator::assertSameChangeItem));
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
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getCast(),
                        actual.getCast(),
                        MoviesApiDataValidator::assertSameCastCredit),
                () -> validateListOf(
                        expected.getCrew(),
                        actual.getCrew(),
                        MoviesApiDataValidator::assertSameCrewCredit));
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
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getFreebaseId(), actual.getFreebaseId()),
                () -> assertEquals(expected.getFreebaseMid(), actual.getFreebaseMid()),
                () -> assertEquals(expected.getTvdbId(), actual.getTvdbId()),
                () -> assertEquals(expected.getTvrageId(), actual.getTvrageId()),
                () -> assertEquals(expected.getWikidataId(), actual.getWikidataId()));
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
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getPosters(), actual.getPosters()));
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
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getTranslations(), actual.getTranslations()));
    }

    /////////////////////////////
    // GetWatchProvidersResponse
    //////////////////////////////

    public static void assertSameGetWatchProvidersResponse(
            final GetWatchProvidersResponse expected,
            final GetWatchProvidersResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getResults(), actual.getResults()));
    }
}
