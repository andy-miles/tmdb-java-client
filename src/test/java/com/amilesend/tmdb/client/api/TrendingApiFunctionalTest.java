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
import com.amilesend.tmdb.client.model.trending.GetAllTrendingRequest;
import com.amilesend.tmdb.client.model.trending.GetAllTrendingResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingMoviesRequest;
import com.amilesend.tmdb.client.model.trending.GetTrendingMoviesResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingPeopleRequest;
import com.amilesend.tmdb.client.model.trending.GetTrendingPeopleResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingTvRequest;
import com.amilesend.tmdb.client.model.trending.GetTrendingTvResponse;
import com.amilesend.tmdb.client.model.trending.type.TimeWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.trending.TrendingApiDataHelper.Responses.GET_ALL_TRENDING_RESPONSE;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataHelper.Responses.GET_TRENDING_MOVIES_RESPONSE;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataHelper.Responses.GET_TRENDING_PEOPLE_RESPONSE;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataHelper.Responses.GET_TRENDING_TV_RESPONSE;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataHelper.newGetAllTrendingResponse;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataHelper.newGetTrendingMoviesResponse;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataHelper.newGetTrendingPeopleResponse;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataHelper.newGetTrendingTvResponse;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataValidator.assertSameGetAllTrendingResponse;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataValidator.assertSameGetTrendingMoviesResponse;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataValidator.assertSameGetTrendingPeopleResponse;
import static com.amilesend.tmdb.client.data.trending.TrendingApiDataValidator.assertSameGetTrendingTvResponse;

public class TrendingApiFunctionalTest extends FunctionalTestBase {
    private TrendingApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getTrendingApi();
    }

    @Test
    public void getAllTrending_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ALL_TRENDING_RESPONSE);
        final GetAllTrendingResponse expected = newGetAllTrendingResponse();

        final GetAllTrendingResponse actual = apiUnderTest.getAllTrending(
                GetAllTrendingRequest.builder()
                        .language(Locale.US.getLanguage())
                        .timeWindow(TimeWindow.DAY)
                        .build());

        assertSameGetAllTrendingResponse(expected, actual);
    }

    @Test
    public void getTrendingMovies_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TRENDING_MOVIES_RESPONSE);
        final GetTrendingMoviesResponse expected = newGetTrendingMoviesResponse();

        final GetTrendingMoviesResponse actual = apiUnderTest.getTrendingMovies(
                GetTrendingMoviesRequest.builder()
                        .language(Locale.US.getLanguage())
                        .timeWindow(TimeWindow.WEEK)
                        .build());

        assertSameGetTrendingMoviesResponse(expected, actual);
    }

    @Test
    public void getTrendingPeople_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TRENDING_PEOPLE_RESPONSE);
        final GetTrendingPeopleResponse expected = newGetTrendingPeopleResponse();

        final GetTrendingPeopleResponse actual = apiUnderTest.getTrendingPeople(
                GetTrendingPeopleRequest.builder()
                        .language(Locale.US.getLanguage())
                        .timeWindow(TimeWindow.WEEK)
                        .build());

        assertSameGetTrendingPeopleResponse(expected, actual);
    }

    @Test
    public void getTrendingTv_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TRENDING_TV_RESPONSE);
        final GetTrendingTvResponse expected = newGetTrendingTvResponse();

        final GetTrendingTvResponse actual = apiUnderTest.getTrendingTv(
                GetTrendingTvRequest.builder()
                        .language(Locale.US.getLanguage())
                        .timeWindow(TimeWindow.DAY)
                        .build());

        assertSameGetTrendingTvResponse(expected, actual);
    }
}
