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
import com.amilesend.tmdb.client.model.company.GetAlternativeNamesRequest;
import com.amilesend.tmdb.client.model.company.GetAlternativeNamesResponse;
import com.amilesend.tmdb.client.model.company.GetCompanyDetailsRequest;
import com.amilesend.tmdb.client.model.company.GetCompanyDetailsResponse;
import com.amilesend.tmdb.client.model.company.GetCompanyImagesRequest;
import com.amilesend.tmdb.client.model.company.GetCompanyImagesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.tmdb.client.data.company.CompaniesApiDataHelper.Responses.ALTERNATIVE_NAMES;
import static com.amilesend.tmdb.client.data.company.CompaniesApiDataHelper.Responses.COMPANY_DETAILS;
import static com.amilesend.tmdb.client.data.company.CompaniesApiDataHelper.Responses.COMPANY_IMAGES;
import static com.amilesend.tmdb.client.data.company.CompaniesApiDataHelper.newAlternativeNames;
import static com.amilesend.tmdb.client.data.company.CompaniesApiDataHelper.newCompanyDetails;
import static com.amilesend.tmdb.client.data.company.CompaniesApiDataHelper.newCompanyImages;
import static com.amilesend.tmdb.client.data.company.CompaniesApiDataValidator.assertSameAlternativeNames;
import static com.amilesend.tmdb.client.data.company.CompaniesApiDataValidator.assertSameCompanyDetails;
import static com.amilesend.tmdb.client.data.company.CompaniesApiDataValidator.assertSameCompanyImages;

public class CompaniesApiFunctionalTest extends FunctionalTestBase {
    private static final int COMPANY_ID = 1;

    private CompaniesApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getCompaniesApi();
    }

    @Test
    public void getCompanyDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, COMPANY_DETAILS);
        final GetCompanyDetailsResponse expected = newCompanyDetails();

        final GetCompanyDetailsResponse actual = apiUnderTest.getCompanyDetails(
                GetCompanyDetailsRequest.builder().companyId(COMPANY_ID).build());

        assertSameCompanyDetails(expected, actual);
    }

    @Test
    public void getAlternativeNames_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, ALTERNATIVE_NAMES);
        final GetAlternativeNamesResponse expected = newAlternativeNames();

        final GetAlternativeNamesResponse actual = apiUnderTest.getAlternativeNames(
                GetAlternativeNamesRequest.builder().companyId(COMPANY_ID).build());

        assertSameAlternativeNames(expected, actual);
    }

    @Test
    public void getImages_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, COMPANY_IMAGES);
        final GetCompanyImagesResponse expected = newCompanyImages();

        final GetCompanyImagesResponse actual = apiUnderTest.getImages(
                GetCompanyImagesRequest.builder().companyId(COMPANY_ID).build());

        assertSameCompanyImages(expected, actual);
    }
}
