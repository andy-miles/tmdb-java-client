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

import com.amilesend.tmdb.client.model.genre.GetMovieGenresResponse;
import com.amilesend.tmdb.client.model.genre.GetTvGenresResponse;
import com.amilesend.tmdb.client.model.genre.type.GetGenresResultBase;
import com.amilesend.tmdb.client.model.type.Genre;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertSameGenres(expected.getGenres(), actual.getGenres());
    }

    public static void assertSameGenres(final List<Genre> expected, final List<Genre> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size();  ++i) {
            assertSameGenre(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameGenre(final Genre expected, final Genre actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()));
    }
}
