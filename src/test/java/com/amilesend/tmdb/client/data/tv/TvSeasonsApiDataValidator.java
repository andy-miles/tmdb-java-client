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
import com.amilesend.tmdb.client.model.type.ChangeItem;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameCastCredits;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameChangeItem;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameCrewCredits;
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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getSeasonId(), actual.getSeasonId()),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> assertSameEpisodes(expected.getEpisodes(), actual.getEpisodes()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D));
    }

    private static void assertSameEpisodes(
            final List<GetEpisodeDetailsResponse> expected,
            final List<GetEpisodeDetailsResponse> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size();  ++i) {
            assertSameEpisode(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameEpisode(
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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameAggregateCastCredits(expected.getCast(), actual.getCast()),
                () -> assertSameAggregateCrewCredits(expected.getCrew(), actual.getCrew()));
    }

    public static void assertSameAggregateCrewCredits(
            final List<AggregateCrewCredit> expected,
            final List<AggregateCrewCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameAggregateCrewCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameAggregateCrewCredit(
            final AggregateCrewCredit expected,
            final AggregateCrewCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
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

    public static void assertSameAggregateCastCredits(
            final List<AggregateCastCredit> expected,
            final List<AggregateCastCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameAggregateCastCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameAggregateCastCredit(
            final AggregateCastCredit expected,
            final AggregateCastCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
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

        assertSameChanges(expected.getChanges(), actual.getChanges());
    }

    public static void assertSameChanges(
            final List<Change<EpisodeChangeDescriptor>> expected,
            final List<Change<EpisodeChangeDescriptor>> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameChange(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameChange(
            final Change<EpisodeChangeDescriptor> expected,
            final Change<EpisodeChangeDescriptor> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getKey(), actual.getKey()),
                () -> assertSameChangeItems(expected.getItems(), actual.getItems()));
    }

    private static void assertSameChangeItems(
            final List<ChangeItem<EpisodeChangeDescriptor>> expected,
            final List<ChangeItem<EpisodeChangeDescriptor>> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameChangeItem(expected.get(i), actual.get(i));
        }
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
                () -> assertSameCrewCredits(expected.getCrew(), actual.getCrew()));
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
                () -> assertEquals(expected.getId(), actual.getId()),
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
                () -> assertEquals(expected.getId(), actual.getId()),
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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getResults(), actual.getResults()));
    }
}
