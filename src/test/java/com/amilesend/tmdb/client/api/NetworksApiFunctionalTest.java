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
import com.amilesend.tmdb.client.model.network.GetAlternativeNamesRequest;
import com.amilesend.tmdb.client.model.network.GetAlternativeNamesResponse;
import com.amilesend.tmdb.client.model.network.GetImagesRequest;
import com.amilesend.tmdb.client.model.network.GetImagesResponse;
import com.amilesend.tmdb.client.model.network.GetNetworkDetailsRequest;
import com.amilesend.tmdb.client.model.network.GetNetworkDetailsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.tmdb.client.data.network.NetworksApiDataHelper.Responses.GET_ALTERNATIVE_NAMES_RESPONSE;
import static com.amilesend.tmdb.client.data.network.NetworksApiDataHelper.Responses.GET_IMAGES_RESPONSE;
import static com.amilesend.tmdb.client.data.network.NetworksApiDataHelper.Responses.GET_NETWORK_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.network.NetworksApiDataHelper.newGetAlternativeNamesResponse;
import static com.amilesend.tmdb.client.data.network.NetworksApiDataHelper.newGetImagesResponse;
import static com.amilesend.tmdb.client.data.network.NetworksApiDataHelper.newGetNetworkDetailsResponse;
import static com.amilesend.tmdb.client.data.network.NetworksApiDataValidator.assertSameGetAlternativeNamesResponse;
import static com.amilesend.tmdb.client.data.network.NetworksApiDataValidator.assertSameGetImagesResponse;
import static com.amilesend.tmdb.client.data.network.NetworksApiDataValidator.assertSameGetNetworkDetailsResponse;

public class NetworksApiFunctionalTest extends FunctionalTestBase {
    private NetworksApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getNetworksApi();
    }

    @Test
    public void getNetworkDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_NETWORK_DETAILS_RESPONSE);
        final GetNetworkDetailsResponse expected = newGetNetworkDetailsResponse();

        final GetNetworkDetailsResponse actual = apiUnderTest.getNetworkDetails(GetNetworkDetailsRequest.builder()
                .networkId(1)
                .build());

        assertSameGetNetworkDetailsResponse(expected, actual);
    }

    @Test
    public void getAlternativeNames_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ALTERNATIVE_NAMES_RESPONSE);
        final GetAlternativeNamesResponse expected = newGetAlternativeNamesResponse();

        final GetAlternativeNamesResponse actual = apiUnderTest.getAlternativeNames(
                GetAlternativeNamesRequest.builder()
                        .networkId(1)
                        .build());

        assertSameGetAlternativeNamesResponse(expected, actual);
    }

    @Test
    public void getImages_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_IMAGES_RESPONSE);
        final GetImagesResponse expected = newGetImagesResponse();

        final GetImagesResponse actual = apiUnderTest.getImages(GetImagesRequest.builder()
                .networkId(1)
                .build());

        assertSameGetImagesResponse(expected, actual);
    }
}
