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
package com.amilesend.tmdb.client.data.movie;

import com.amilesend.tmdb.client.model.PaginatedResponseBase;
import com.amilesend.tmdb.client.model.movie.list.GetNowPlayingResponse;
import com.amilesend.tmdb.client.model.movie.list.GetUpcomingResponse;
import com.amilesend.tmdb.client.model.movie.type.Movie;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class MovieListsApiDataValidator {
    public static void assertSameGetNowPlayingResponse(
            final GetNowPlayingResponse expected,
            final GetNowPlayingResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getDates(), actual.getDates()),
                () -> assertSamePaginatedResponse(expected, actual));
    }

    public static void assertSameGetUpcomingResponse(
            final GetUpcomingResponse expected,
            final GetUpcomingResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getDates(), actual.getDates()),
                () -> assertSamePaginatedResponse(expected, actual));
    }

    public static void assertSamePaginatedResponse(
            final PaginatedResponseBase<Movie> expected,
            final PaginatedResponseBase<Movie> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSameMovies(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getResults(), actual.getResults()));
    }

    public static void assertSameMovies(
            final List<Movie> expected,
            final List<Movie> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameMovie(expected.get(i), actual.get(i));
        }
    }

    public static void assertSameMovie(final Movie expected, final Movie actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getId(), actual.getId()));
    }
}
