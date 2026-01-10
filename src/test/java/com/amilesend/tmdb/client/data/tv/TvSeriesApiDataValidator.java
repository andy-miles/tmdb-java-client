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

import com.amilesend.tmdb.client.data.DataValidatorHelper;
import com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator;
import com.amilesend.tmdb.client.model.tv.seasons.type.TvSeason;
import com.amilesend.tmdb.client.model.tv.series.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetAggregateCreditsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetAlternativeTitlesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetContentRatingsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetEpisodeGroupsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetKeywordsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetListsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetRecommendationsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetReviewsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetScreenedTheatricallyResponse;
import com.amilesend.tmdb.client.model.tv.series.GetSeriesDetailsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetSimilarResponse;
import com.amilesend.tmdb.client.model.tv.series.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetVideosResponse;
import com.amilesend.tmdb.client.model.tv.series.GetWatchProvidersResponse;
import com.amilesend.tmdb.client.model.tv.series.type.EpisodeGroup;
import com.amilesend.tmdb.client.model.tv.series.type.Poster;
import com.amilesend.tmdb.client.model.tv.series.type.ScreenedTvSeriesEpisode;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeries;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesCredit;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesEpisode;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesNetwork;
import com.amilesend.tmdb.client.model.type.Change;
import com.amilesend.tmdb.client.model.type.ChangeItem;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateListOf;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateNamedResource;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateResource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TvSeriesApiDataValidator {
    ///////////////////////////////////////
    // assertSameGetSeriesDetailsResponse
    ///////////////////////////////////////

    public static void assertSameGetSeriesDetailsResponse(
            final GetSeriesDetailsResponse expected,
            final GetSeriesDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> validateListOf(expected.getCreatedBy(), actual.getCreatedBy(), TvSeriesApiDataValidator::assertSameTvSeriesCredit),
                () -> assertEquals(expected.getEpisodeRunTime(), actual.getEpisodeRunTime()),
                () -> assertEquals(expected.getFirstAirDate(), actual.getFirstAirDate()),
                () -> validateListOf(expected.getGenres(), actual.getGenres(), DataValidatorHelper::validateNamedResource),
                () -> assertEquals(expected.getHomepage(), actual.getHomepage()),
                () -> assertEquals(expected.getInProduction(), actual.getInProduction()),
                () -> assertEquals(expected.getLanguages(), actual.getLanguages()),
                () -> assertEquals(expected.getLastAirDate(), actual.getLastAirDate()),
                () -> assertSameTvSeriesEpisode(expected.getLastEpisodeToAir(), actual.getLastEpisodeToAir()),
                () -> assertEquals(expected.getNextEpisodeToAir(), actual.getNextEpisodeToAir()),
                () -> validateListOf(expected.getNetworks(), actual.getNetworks(), TvSeriesApiDataValidator::assertSameTvSeriesNetwork),
                () -> assertEquals(expected.getNumberOfEpisodes(), actual.getNumberOfEpisodes()),
                () -> assertEquals(expected.getNumberOfSeasons(), actual.getNumberOfSeasons()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> validateListOf(expected.getProductionCompanies(), actual.getProductionCompanies(), MoviesApiDataValidator::assertSameProductionCompany),
                () -> assertEquals(expected.getProductionCountries(), actual.getProductionCountries()),
                () -> validateListOf(expected.getSeasons(), actual.getSeasons(), TvSeriesApiDataValidator::assertSameTvSeason),
                () -> assertEquals(expected.getLanguages(), actual.getLanguages()),
                () -> assertEquals(expected.getStatus(), actual.getStatus()),
                () -> assertEquals(expected.getTagline(), actual.getTagline()),
                () -> assertEquals(expected.getType(), actual.getType()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()));
    }

    private static void assertSameTvSeriesCredit(final TvSeriesCredit expected, final TvSeriesCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getCreditId(), actual.getCreditId()),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getProfilePath(), actual.getProfilePath()));
    }

    private static void assertSameTvSeriesEpisode(final TvSeriesEpisode expected, final TvSeriesEpisode actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> assertEquals(expected.getEpisodeNumber(), expected.getEpisodeNumber()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getProductionCode(), actual.getProductionCode()),
                () -> assertEquals(expected.getRuntime(), actual.getRuntime()),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getStillPath(), actual.getStillPath()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getEpisodeType(), actual.getEpisodeType()));
    }

    static void assertSameTvSeriesNetwork(final TvSeriesNetwork expected, final TvSeriesNetwork actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getLogoPath(), actual.getLogoPath()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()));
    }

    private static void assertSameTvSeason(final TvSeason expected, final TvSeason actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getSeasonId(), actual.getSeasonId()),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D));
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
                () -> validateListOf(expected.getCast(), actual.getCast(), TvSeasonsApiDataValidator::assertSameAggregateCastCredit),
                () -> validateListOf(expected.getCrew(), actual.getCrew(), TvSeasonsApiDataValidator::assertSameAggregateCrewCredit));
    }

    /////////////////////////////////
    // GetAlternativeTitlesResponse
    /////////////////////////////////

    public static void assertSameGetAlternativeTitlesResponse(
            final GetAlternativeTitlesResponse expected,
            final GetAlternativeTitlesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getTitles(), actual.getTitles()));
    }

    ///////////////////////
    // GetChangesResponse
    ///////////////////////

    public static void assertSameGetChangesResponse(
            final GetChangesResponse expected,
            final GetChangesResponse actual) {
        validateListOf(expected.getChanges(), actual.getChanges(), TvSeriesApiDataValidator::assertSameChange);
    }

    private static void assertSameChange(final Change<Poster> expected, final Change<Poster> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getKey(), actual.getKey()),
                () -> validateListOf(expected.getItems(), actual.getItems(), TvSeriesApiDataValidator::assertSameChangeItem));
    }

    private static void assertSameChangeItem(
            final ChangeItem<Poster> expected,
            final ChangeItem<Poster> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getAction(), actual.getAction()),
                () -> assertEquals(expected.getTime(), actual.getTime()),
                () -> assertEquals(expected.getLanguageCode(), actual.getLanguageCode()),
                () -> assertEquals(expected.getCountryCode(), actual.getCountryCode()),
                () -> assertEquals(expected.getValue(), actual.getValue()),
                () -> assertEquals(expected.getOriginalValue(), actual.getOriginalValue()));
    }

    //////////////////////////////
    // GetContentRatingsResponse
    //////////////////////////////

    public static void assertSameGetContentRatingsResponse(
            final GetContentRatingsResponse expected,
            final GetContentRatingsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getResults(), actual.getResults()));
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
                () -> validateListOf(expected.getCast(), actual.getCast(), MoviesApiDataValidator::assertSameCastCredit),
                () -> validateListOf(expected.getCrew(), actual.getCrew(), MoviesApiDataValidator::assertSameCrewCredit));
    }

    /////////////////////////////
    // GetEpisodeGroupsResponse
    /////////////////////////////

    public static void assertSameGetEpisodeGroupsResponse(
            final GetEpisodeGroupsResponse expected,
            final GetEpisodeGroupsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        TvSeriesApiDataValidator::assertSameEpisodeGroup));
    }

    private static void assertSameEpisodeGroup(final EpisodeGroup expected, final EpisodeGroup actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getDescription(), actual.getDescription()),
                () -> assertEquals(expected.getEpisodeCount(), actual.getEpisodeCount()),
                () -> assertEquals(expected.getGroupCount(), actual.getGroupCount()),
                () -> assertSameTvSeriesNetwork(expected.getNetwork(), actual.getNetwork()),
                () -> assertEquals(expected.getType(), actual.getType()));
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
                () -> assertEquals(expected.getImdbId(), actual.getImdbId()),
                () -> assertEquals(expected.getFreebaseMid(), actual.getFreebaseMid()),
                () -> assertEquals(expected.getFreebaseId(), actual.getFreebaseId()),
                () -> assertEquals(expected.getTvdbId(), actual.getTvdbId()),
                () -> assertEquals(expected.getTvrageId(), actual.getTvrageId()),
                () -> assertEquals(expected.getWikidataId(), actual.getWikidataId()),
                () -> assertEquals(expected.getFacebookId(), actual.getFacebookId()),
                () -> assertEquals(expected.getInstagramId(), actual.getInstagramId()),
                () -> assertEquals(expected.getTwitterId(), actual.getTwitterId()));
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
                () -> assertEquals(expected.getBackdrops(), actual.getBackdrops()),
                () -> assertEquals(expected.getLogos(), actual.getLogos()),
                () -> assertEquals(expected.getPosters(), actual.getPosters()));
    }

    ////////////////////////
    // GetKeywordsResponse
    ////////////////////////

    public static void assertSameGetKeywordsResponse(
            final GetKeywordsResponse expected,
            final GetKeywordsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getKeywords(),
                        actual.getKeywords(),
                        DataValidatorHelper::validateNamedResource));
    }

    /////////////////////
    // GetListsResponse
    /////////////////////

    public static void assertSameGetListsResponse(
            final GetListsResponse expected,
            final GetListsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        MoviesApiDataValidator::assertSameMediaListInfoList),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    ///////////////////////////////
    // GetRecommendationsResponse
    ///////////////////////////////

    public static void assertSameGetRecommendationsResponse(
            final GetRecommendationsResponse expected,
            final GetRecommendationsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        TvSeriesApiDataValidator::assertSameTvSeries),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    public static void assertSameTvSeries(final TvSeries expected, final TvSeries actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getFirstAirDate(), actual.getFirstAirDate()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()));
    }

    ///////////////////////
    // GetReviewsResponse
    ///////////////////////

    public static void assertSameGetReviewsResponse(
            final GetReviewsResponse expected,
            final GetReviewsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        MoviesApiDataValidator::assertSameReview),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    ////////////////////////////////////
    // GetScreenedTheatricallyResponse
    ////////////////////////////////////

    public static void assertSameGetScreenedTheatricallyResponse(
            final GetScreenedTheatricallyResponse expected,
            final GetScreenedTheatricallyResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        TvSeriesApiDataValidator::assertSameScreenedTvSeriesEpisode));
    }

    private static void assertSameScreenedTvSeriesEpisode(
            final ScreenedTvSeriesEpisode expected,
            final ScreenedTvSeriesEpisode actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getEpisodeNumber(), actual.getEpisodeNumber()));
    }

    ///////////////////////
    // GetSimilarResponse
    ///////////////////////

    public static void assertSameGetSimilarResponse(
            final GetSimilarResponse expected,
            final GetSimilarResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        TvSeriesApiDataValidator::assertSameTvSeries));
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

    //////////////////////
    // GetVideosResponse
    //////////////////////

    public static void assertSameGetVideosResponse(
            final GetVideosResponse expected,
            final GetVideosResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        MoviesApiDataValidator::assertSameVideo));
    }

    //////////////////////////////
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
