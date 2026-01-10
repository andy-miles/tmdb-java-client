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
import com.amilesend.tmdb.client.model.find.FindByIdRequest;
import com.amilesend.tmdb.client.model.find.FindByIdResponse;
import com.amilesend.tmdb.client.model.find.type.ExternalSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.find.FindApiDataHelper.Responses.FIND_BY_ID_RESPONSE;
import static com.amilesend.tmdb.client.data.find.FindApiDataHelper.newFindByIdResponse;
import static com.amilesend.tmdb.client.data.find.FindApiDataValidator.assertSameFindByIdResponse;

public class FindApiFunctionalTest extends FunctionalTestBase {
    private FindApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getFindApi();
    }

    @Test
    public void findById_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, FIND_BY_ID_RESPONSE);
        final FindByIdResponse expected = newFindByIdResponse();

        final FindByIdResponse actual = apiUnderTest.findById(
                FindByIdRequest.builder()
                        .externalId("ExternalId")
                        .externalSource(ExternalSource.TVDB)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameFindByIdResponse(expected, actual);
    }
}
