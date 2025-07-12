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

import com.amilesend.client.connection.Connection;
import com.amilesend.tmdb.client.model.list.GetListDetailsRequest;
import com.amilesend.tmdb.client.model.list.GetListDetailsResponse;
import com.amilesend.tmdb.client.model.network.GetAlternativeNamesRequest;
import com.amilesend.tmdb.client.model.network.GetAlternativeNamesResponse;
import com.amilesend.tmdb.client.model.network.GetImagesRequest;
import com.amilesend.tmdb.client.model.network.GetImagesResponse;
import com.amilesend.tmdb.client.model.network.GetNetworkDetailsRequest;
import com.amilesend.tmdb.client.model.network.GetNetworkDetailsResponse;
import com.amilesend.tmdb.client.model.network.type.NetworksRequestBase;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/** TMDB Networks API. */
public class NetworksApi extends ApiBase {
    private static String API_PATH = "/network/";

    /**
     * Creates a new {@code NetworksApi} object.
     *
     * @param connection the connection
     */
    public NetworksApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the network details.
     *
     * @param request the request
     * @return the response
     * @see GetListDetailsRequest
     * @see GetListDetailsResponse
     */
    public GetNetworkDetailsResponse getNetworkDetails(@NonNull final GetNetworkDetailsRequest request) {
        return executeGet(getApiPath(request, StringUtils.EMPTY), request, GetNetworkDetailsResponse.class);
    }

    /**
     * Gets the list of alternative names for a network.
     *
     * @param request the request
     * @return the response
     * @see GetAlternativeNamesRequest
     * @see GetAlternativeNamesResponse
     */
    public GetAlternativeNamesResponse getAlternativeNames(@NonNull final GetAlternativeNamesRequest request) {
        return executeGet(
                getApiPath(request, "/alternative_names"),
                request,
                GetAlternativeNamesResponse.class);
    }

    /**
     * Gets the list of images for a network.
     *
     * @param request the request
     * @return the response
     * @see GetImagesRequest
     * @see GetImagesResponse
     */
    public GetImagesResponse getImages(@NonNull final GetImagesRequest request) {
        return executeGet(getApiPath(request, "/images"), request, GetImagesResponse.class);
    }

    private static String getApiPath(final NetworksRequestBase request, final String subApiPath) {
        return new StringBuilder(API_PATH)
                .append(request.getNetworkId())
                .append(subApiPath)
                .toString();
    }
}
