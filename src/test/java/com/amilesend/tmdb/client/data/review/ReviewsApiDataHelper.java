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
package com.amilesend.tmdb.client.data.review;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.review.GetReviewDetailsResponse;
import com.amilesend.tmdb.client.model.type.AuthorDetails;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Locale;

@UtilityClass
public class ReviewsApiDataHelper {
    public static GetReviewDetailsResponse newGetReviewDetailsResponse() {
        return GetReviewDetailsResponse.builder()
                .id("ReviewIdValue")
                .author("Jon Smith")
                .authorDetails(AuthorDetails.builder()
                        .name("Jon Smith")
                        .username("jonsmith")
                        .avatarPath("/jonsmith.png")
                        .rating(7)
                        .build())
                .content("The review content.")
                .createdAt(LocalDateTime.of(2024, 5, 3, 14, 23,30))
                .languageCode(Locale.US.getLanguage())
                .mediaId(1000)
                .mediaTitle("Some Movie")
                .mediaType("movie")
                .updatedAt(LocalDateTime.of(2024, 5, 15, 9, 12,00))
                .url("https://www.themoviedb.org/review/ReviewIdValue")
                .build();
    }

    @UtilityClass
    public static class Responses {
        public static final SerializedResource GET_REVIEW_DETAILS_RESPONSE =
                new SerializedResource("/ReviewsApi/GetReviewDetailsResponse.json");
    }
}
