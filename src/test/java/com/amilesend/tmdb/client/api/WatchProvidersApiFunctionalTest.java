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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.watch.GetAvailableRegionsRequest;
import com.amilesend.tmdb.client.model.watch.GetAvailableRegionsResponse;
import com.amilesend.tmdb.client.model.watch.GetMovieProvidersRequest;
import com.amilesend.tmdb.client.model.watch.GetMovieProvidersResponse;
import com.amilesend.tmdb.client.model.watch.GetTvProvidersRequest;
import com.amilesend.tmdb.client.model.watch.GetTvProvidersResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.watch.WatchProvidersApiDataHelper.Responses.GET_AVAILABLE_REGIONS_RESPONSE;
import static com.amilesend.tmdb.client.data.watch.WatchProvidersApiDataHelper.Responses.GET_MOVIE_PROVIDERS_RESPONSE;
import static com.amilesend.tmdb.client.data.watch.WatchProvidersApiDataHelper.Responses.GET_TV_PROVIDERS_RESPONSE;
import static com.amilesend.tmdb.client.data.watch.WatchProvidersApiDataHelper.newGetAvailableRegionsResponse;
import static com.amilesend.tmdb.client.data.watch.WatchProvidersApiDataHelper.newGetMovieProvidersResponse;
import static com.amilesend.tmdb.client.data.watch.WatchProvidersApiDataHelper.newGetTvProvidersResponse;
import static org.junit.Assert.assertEquals;

public class WatchProvidersApiFunctionalTest extends FunctionalTestBase {
    private WatchProvidersApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getWatchProvidersApi();
    }

    @Test
    public void getAvailableRegions_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_AVAILABLE_REGIONS_RESPONSE);
        final GetAvailableRegionsResponse expected = newGetAvailableRegionsResponse();

        final GetAvailableRegionsResponse actual = apiUnderTest.getAvailableRegions(
                GetAvailableRegionsRequest.builder()
                        .language(Locale.US.getLanguage())
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void getMovieProviders_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_MOVIE_PROVIDERS_RESPONSE);
        final GetMovieProvidersResponse expected = newGetMovieProvidersResponse();

        final GetMovieProvidersResponse actual = apiUnderTest.getMovieProviders(
                GetMovieProvidersRequest.builder()
                        .watchRegion("US")
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void _withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TV_PROVIDERS_RESPONSE);
        final GetTvProvidersResponse expected = newGetTvProvidersResponse();

        final GetTvProvidersResponse actual = apiUnderTest.getTvProviders(
                GetTvProvidersRequest.builder()
                        .watchRegion("US")
                        .build());

        assertEquals(expected, actual);
    }
}
