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

import com.amilesend.tmdb.client.model.review.GetReviewDetailsResponse;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class ReviewsApiDataValidator {
    public static void assertSameGetReviewDetailsResponse(
            final GetReviewDetailsResponse expected,
            final GetReviewDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAuthor(), actual.getAuthor()),
                () -> assertEquals(expected.getAuthorDetails(), actual.getAuthorDetails()),
                () -> assertEquals(expected.getContent(), actual.getContent()),
                () -> assertEquals(expected.getCreatedAt(), actual.getCreatedAt()),
                () -> assertEquals(expected.getLanguageCode(), actual.getLanguageCode()),
                () -> assertEquals(expected.getMediaId(), actual.getMediaId()),
                () -> assertEquals(expected.getMediaTitle(), actual.getMediaTitle()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()),
                () -> assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt()),
                () -> assertEquals(expected.getUrl(), actual.getUrl()));
    }
}
