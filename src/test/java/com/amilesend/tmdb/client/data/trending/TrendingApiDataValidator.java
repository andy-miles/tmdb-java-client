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
package com.amilesend.tmdb.client.data.trending;

import com.amilesend.tmdb.client.model.trending.GetAllTrendingResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingMoviesResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingPeopleResponse;
import com.amilesend.tmdb.client.model.trending.GetTrendingTvResponse;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameMovieSearchResults;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSamePersonSearchResults;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameSearchResults;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameTvSeriesSearchResults;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class TrendingApiDataValidator {
    ///////////////////////////
    // GetAllTrendingResponse
    ///////////////////////////

    public static void assertSameGetAllTrendingResponse(
            final GetAllTrendingResponse expected,
            final GetAllTrendingResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSameSearchResults(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    //////////////////////////////
    // GetTrendingMoviesResponse
    //////////////////////////////

    public static void assertSameGetTrendingMoviesResponse(
            final GetTrendingMoviesResponse expected,
            final GetTrendingMoviesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSameMovieSearchResults(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    //////////////////////////////
    // GetTrendingPeopleResponse
    //////////////////////////////

    public static void assertSameGetTrendingPeopleResponse(
            final GetTrendingPeopleResponse expected,
            final GetTrendingPeopleResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSamePersonSearchResults(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    //////////////////////////
    // GetTrendingTvResponse
    //////////////////////////

    public static void assertSameGetTrendingTvResponse(
            final GetTrendingTvResponse expected,
            final GetTrendingTvResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSameTvSeriesSearchResults(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }
}
