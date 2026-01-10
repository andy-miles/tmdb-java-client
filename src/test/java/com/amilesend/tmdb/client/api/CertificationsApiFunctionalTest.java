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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.certification.GetMovieCertificationsResponse;
import com.amilesend.tmdb.client.model.certification.GetTVCertificationsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.tmdb.client.data.certification.CertificationsApiDataHelper.Responses.GET_CERTIFICATION_RESPONSE;
import static com.amilesend.tmdb.client.data.certification.CertificationsApiDataHelper.newGetMovieCertificationsResponse;
import static com.amilesend.tmdb.client.data.certification.CertificationsApiDataHelper.newGetTVCertificationsResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificationsApiFunctionalTest extends FunctionalTestBase {
    private CertificationsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getCertificationsApi();
    }

    @Test
    public void getMovieCertifications_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CERTIFICATION_RESPONSE);
        final GetMovieCertificationsResponse expected = newGetMovieCertificationsResponse();

        final GetMovieCertificationsResponse actual = apiUnderTest.getMovieCertifications();

        assertEquals(expected, actual);
    }

    @Test
    public void getTVCertifications_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CERTIFICATION_RESPONSE);
        final GetTVCertificationsResponse expected = newGetTVCertificationsResponse();

        final GetTVCertificationsResponse actual = apiUnderTest.getTVCertifications();

        assertEquals(expected, actual);
    }
}
