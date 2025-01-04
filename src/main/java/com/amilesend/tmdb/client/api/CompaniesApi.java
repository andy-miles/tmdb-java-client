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

import com.amilesend.tmdb.client.connection.Connection;
import com.amilesend.tmdb.client.model.company.GetAlternativeNamesRequest;
import com.amilesend.tmdb.client.model.company.GetAlternativeNamesResponse;
import com.amilesend.tmdb.client.model.company.GetCompanyDetailsRequest;
import com.amilesend.tmdb.client.model.company.GetCompanyDetailsResponse;
import com.amilesend.tmdb.client.model.company.GetCompanyImagesRequest;
import com.amilesend.tmdb.client.model.company.GetCompanyImagesResponse;
import lombok.NonNull;

/** TMDB Companies API. */
public class CompaniesApi extends ApiBase {
    private static final String API_PATH = "/company/";

    /**
     * Creates a new {@code CompaniesApi} object.
     *
     * @param connection the connection
     */
    public CompaniesApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the company details.
     *
     * @param request the request
     * @return the company details
     * @see GetCompanyDetailsRequest
     * @see GetCompanyDetailsResponse
     */
    public GetCompanyDetailsResponse getCompanyDetails(@NonNull final GetCompanyDetailsRequest request) {
        final String apiPath = new StringBuilder(API_PATH)
                .append(request.getCompanyId())
                .toString();
        return executeGet(apiPath, request, GetCompanyDetailsResponse.class);
    }

    /**
     * Gets the alternative company names.
     *
     * @param request the request
     * @return the alternative names
     * @see GetAlternativeNamesRequest
     * @see GetAlternativeNamesResponse
     */
    public GetAlternativeNamesResponse getAlternativeNames(@NonNull final GetAlternativeNamesRequest request) {
        final String apiPath =new StringBuilder(API_PATH)
                .append(request.getCompanyId())
                .append("/alternative_names")
                .toString();
        return executeGet(apiPath, request, GetAlternativeNamesResponse.class);
    }

    /**
     * Gets the images associated with a company.
     *
     * @param request the request
     * @return the company images
     * @see GetCompanyImagesResponse
     */
    public GetCompanyImagesResponse getImages(@NonNull final GetCompanyImagesRequest request) {
        final String apiPath = new StringBuilder(API_PATH)
                .append(request.getCompanyId())
                .append("/images")
                .toString();
        return executeGet(apiPath, request, GetCompanyImagesResponse.class);
    }
}
