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
import com.amilesend.tmdb.client.model.keyword.GetKeywordDetailsRequest;
import com.amilesend.tmdb.client.model.keyword.GetKeywordDetailsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.tmdb.client.data.keyword.KeywordsApiDataHelper.Responses.KEYWORD_DETAILS;
import static com.amilesend.tmdb.client.data.keyword.KeywordsApiDataHelper.newKeywordDetails;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeywordsApiFunctionalTest extends FunctionalTestBase {
    private KeywordsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getKeywordsApi();
    }

    @Test
    public void getKeywordDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, KEYWORD_DETAILS);
        final GetKeywordDetailsResponse expected = newKeywordDetails();

        final GetKeywordDetailsResponse actual = apiUnderTest.getKeywordDetails(
                GetKeywordDetailsRequest.builder().keywordId(1000).build());

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()));
    }
}
