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
import com.amilesend.tmdb.client.model.people.lists.GetPopularPersonsRequest;
import com.amilesend.tmdb.client.model.people.lists.GetPopularPersonsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.tmdb.client.data.people.PeopleListsApiDataHelper.Responses.GET_POPULAR_PERSONS_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleListsApiDataHelper.newGetPopularPersonsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleListsApiDataValidator.assertSameGetPopularPersonsResponse;

public class PeopleListsApiFunctionalTest extends FunctionalTestBase {
    private PeopleListsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getPeopleListsApi();
    }

    @Test
    public void getPopular_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_POPULAR_PERSONS_RESPONSE);
        final GetPopularPersonsResponse expected = newGetPopularPersonsResponse();

        final GetPopularPersonsResponse actual = apiUnderTest.getPopular(GetPopularPersonsRequest.builder().build());

        assertSameGetPopularPersonsResponse(expected, actual);
    }
}
