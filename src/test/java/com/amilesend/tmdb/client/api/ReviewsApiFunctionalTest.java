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
import com.amilesend.tmdb.client.model.review.GetReviewDetailsRequest;
import com.amilesend.tmdb.client.model.review.GetReviewDetailsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.tmdb.client.data.review.ReviewsApiDataHelper.Responses.GET_REVIEW_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.review.ReviewsApiDataHelper.newGetReviewDetailsResponse;
import static com.amilesend.tmdb.client.data.review.ReviewsApiDataValidator.assertSameGetReviewDetailsResponse;

public class ReviewsApiFunctionalTest extends FunctionalTestBase {
    private ReviewsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getReviewsApi();
    }

    @Test
    public void getReviewDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_REVIEW_DETAILS_RESPONSE);
        final GetReviewDetailsResponse expected = newGetReviewDetailsResponse();

        final GetReviewDetailsResponse actual = apiUnderTest.getReviewDetails(GetReviewDetailsRequest.builder()
                .reviewId("ReviewIdValue")
                .build());

        assertSameGetReviewDetailsResponse(expected, actual);
    }
}
