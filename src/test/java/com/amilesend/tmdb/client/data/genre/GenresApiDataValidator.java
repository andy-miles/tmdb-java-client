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
package com.amilesend.tmdb.client.data.genre;

import com.amilesend.tmdb.client.data.DataValidatorHelper;
import com.amilesend.tmdb.client.model.genre.GetMovieGenresResponse;
import com.amilesend.tmdb.client.model.genre.GetTvGenresResponse;
import com.amilesend.tmdb.client.model.genre.type.GetGenresResultBase;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateListOf;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class GenresApiDataValidator {
    public static void assertSameGetMovieGenresResponse(
            final GetMovieGenresResponse expected,
            final GetMovieGenresResponse actual) {
        assertSameGetGenresResult(expected, actual);
    }

    public static void assertSameGetTvGenresResponse(
            final GetTvGenresResponse expected,
            final GetTvGenresResponse actual) {
        assertSameGetGenresResult(expected, actual);
    }

    private static void assertSameGetGenresResult(
            final GetGenresResultBase expected,
            final GetGenresResultBase actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        validateListOf(expected.getGenres(), actual.getGenres(), DataValidatorHelper::validateNamedResource);
    }
}
