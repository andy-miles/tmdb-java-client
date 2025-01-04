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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.tv.seasons.GetAccountStatesRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetAggregateCreditsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetAggregateCreditsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetChangesRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetCreditsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetExternalIdsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetImagesRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetSeasonDetailsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetSeasonDetailsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetTranslationsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetWatchProvidersRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetWatchProvidersResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.Responses.GET_ACCOUNT_STATES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.Responses.GET_AGGREGATE_CREDITS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.Responses.GET_CHANGES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.Responses.GET_CREDITS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.Responses.GET_EXTERNAL_IDS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.Responses.GET_IMAGES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.Responses.GET_SEASON_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.Responses.GET_TRANSLATIONS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.Responses.GET_WATCH_PROVIDERS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newGetAccountStatesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newGetAggregateCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newGetChangesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newGetCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newGetExternalIdsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newGetImagesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newGetSeasonDetailsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newGetWatchProvidersResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataValidator.assertSameGetAccountStatesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataValidator.assertSameGetAggregateCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataValidator.assertSameGetChangesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataValidator.assertSameGetCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataValidator.assertSameGetExternalIdsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataValidator.assertSameGetImagesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataValidator.assertSameGetSeasonDetailsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataValidator.assertSameGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataValidator.assertSameGetWatchProvidersResponse;

public class TvSeasonsApiFunctionalTest extends FunctionalTestBase {
    private TvSeasonsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getTvSeasonsApi();
    }

    @Test
    public void getSeasonDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_SEASON_DETAILS_RESPONSE);
        final GetSeasonDetailsResponse expected = newGetSeasonDetailsResponse();

        final GetSeasonDetailsResponse actual = apiUnderTest.getSeasonDetails(
                GetSeasonDetailsRequest.builder()
                        .seriesId(50)
                        .seasonNumber(1)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetSeasonDetailsResponse(expected, actual);
    }

    @Test
    public void getAccountStates_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ACCOUNT_STATES_RESPONSE);
        final GetAccountStatesResponse expected = newGetAccountStatesResponse();

        final GetAccountStatesResponse actual = apiUnderTest.getAccountStates(
                GetAccountStatesRequest.builder()
                        .seriesId(50)
                        .seasonNumber(1)
                        .sessionId("sessionIdValue")
                        .build());

        assertSameGetAccountStatesResponse(expected, actual);
    }

    @Test
    public void getAggregateCredits_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_AGGREGATE_CREDITS_RESPONSE);
        final GetAggregateCreditsResponse expected = newGetAggregateCreditsResponse();

        final GetAggregateCreditsResponse actual = apiUnderTest.getAggregateCredits(
                GetAggregateCreditsRequest.builder()
                        .seriesId(500)
                        .seasonNumber(1)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetAggregateCreditsResponse(expected, actual);
    }

    @Test
    public void getChanges_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CHANGES_RESPONSE);
        final GetChangesResponse expected = newGetChangesResponse();

        final GetChangesResponse actual = apiUnderTest.getChanges(
                GetChangesRequest.builder()
                        .seasonId(500)
                        .page(Integer.valueOf(1))
                        .startDate(LocalDate.of(2020, 1, 1))
                        .endDate(LocalDate.of(2021, 1, 1))
                        .build());

        assertSameGetChangesResponse(expected, actual);
    }

    @Test
    public void getCredits_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CREDITS_RESPONSE);
        final GetCreditsResponse expected = newGetCreditsResponse();

        final GetCreditsResponse actual = apiUnderTest.getCredits(
                GetCreditsRequest.builder()
                        .seriesId(1)
                        .seasonNumber(1)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetCreditsResponse(expected, actual);
    }

    @Test
    public void getExternalIds_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_EXTERNAL_IDS_RESPONSE);
        final GetExternalIdsResponse expected = newGetExternalIdsResponse();

        final GetExternalIdsResponse actual = apiUnderTest.getExternalIds(
                GetExternalIdsRequest.builder()
                        .seriesId(400)
                        .seasonNumber(1)
                        .build());

        assertSameGetExternalIdsResponse(expected, actual);
    }

    @Test
    public void getImages_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_IMAGES_RESPONSE);
        final GetImagesResponse expected = newGetImagesResponse();

        final GetImagesResponse actual = apiUnderTest.getImages(
                GetImagesRequest.builder()
                        .seriesId(400)
                        .seasonNumber(1)
                        .language(Locale.US.getLanguage())
                        .includeImageLanguage("US, AU")
                        .build());

        assertSameGetImagesResponse(expected, actual);
    }

    @Test
    public void getTranslations_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TRANSLATIONS_RESPONSE);
        final GetTranslationsResponse expected = newGetTranslationsResponse();

        final GetTranslationsResponse actual = apiUnderTest.getTranslations(
                GetTranslationsRequest.builder()
                        .seriesId(400)
                        .seasonNumber(1)
                        .build());

        assertSameGetTranslationsResponse(expected, actual);
    }

    @Test
    public void getWatchProviders_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_WATCH_PROVIDERS_RESPONSE);
        final GetWatchProvidersResponse expected = newGetWatchProvidersResponse();

        final GetWatchProvidersResponse actual = apiUnderTest.getWatchProviders(
                GetWatchProvidersRequest.builder()
                        .seriesId(400)
                        .seasonNumber(1)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetWatchProvidersResponse(expected, actual);
    }
}
