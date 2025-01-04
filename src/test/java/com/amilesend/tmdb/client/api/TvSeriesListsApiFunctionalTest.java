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
import com.amilesend.tmdb.client.model.tv.series.list.GetAiringTodayRequest;
import com.amilesend.tmdb.client.model.tv.series.list.GetAiringTodayResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetOnTheAirRequest;
import com.amilesend.tmdb.client.model.tv.series.list.GetOnTheAirResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetPopularRequest;
import com.amilesend.tmdb.client.model.tv.series.list.GetPopularResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetTopRatedRequest;
import com.amilesend.tmdb.client.model.tv.series.list.GetTopRatedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataHelper.Responses.GET_AIRING_TODAY_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataHelper.Responses.GET_ON_THE_AIR_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataHelper.Responses.GET_POPULAR_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataHelper.Responses.GET_TOP_RATED_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataHelper.newGetAiringTodayResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataHelper.newGetOnTheAirResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataHelper.newGetPopularResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataHelper.newGetTopRatedResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataValidator.assertSameGetAiringTodayResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataValidator.assertSameGetOnTheAirResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataValidator.assertSameGetPopularResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesListsApiDataValidator.assertSameGetTopRatedResponse;

public class TvSeriesListsApiFunctionalTest extends FunctionalTestBase {
    private TvSeriesListsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getTvSeriesListsApi();
    }

    @Test
    public void getAiringToday_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_AIRING_TODAY_RESPONSE);
        final GetAiringTodayResponse expected = newGetAiringTodayResponse();

        final GetAiringTodayResponse actual = apiUnderTest.getAiringToday(
                GetAiringTodayRequest.builder()
                        .language(Locale.US.getLanguage())
                        .page(1)
                        .timezone("America/Los_Angeles")
                        .build());

        assertSameGetAiringTodayResponse(expected, actual);
    }

    @Test
    public void getOnTheAir_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ON_THE_AIR_RESPONSE);
        final GetOnTheAirResponse expected = newGetOnTheAirResponse();

        final GetOnTheAirResponse actual = apiUnderTest.getOnTheAir(
                GetOnTheAirRequest.builder()
                        .language(Locale.US.getLanguage())
                        .timezone("America/Los_Angeles")
                        .page(1)
                        .build());

        assertSameGetOnTheAirResponse(expected, actual);
    }

    @Test
    public void getPopular_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_POPULAR_RESPONSE);
        final GetPopularResponse expected = newGetPopularResponse();

        final GetPopularResponse actual = apiUnderTest.getPopular(
                GetPopularRequest.builder()
                        .language(Locale.US.getLanguage())
                        .page(1)
                        .build());

        assertSameGetPopularResponse(expected, actual);
    }

    @Test
    public void getTopRated_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TOP_RATED_RESPONSE);
        final GetTopRatedResponse expected = newGetTopRatedResponse();

        final GetTopRatedResponse actual = apiUnderTest.getTopRated(
                GetTopRatedRequest.builder()
                        .language(Locale.US.getLanguage())
                        .page(1)
                        .build());

        assertSameGetTopRatedResponse(expected, actual);
    }
}
