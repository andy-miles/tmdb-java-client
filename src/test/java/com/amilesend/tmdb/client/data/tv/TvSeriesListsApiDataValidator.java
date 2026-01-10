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
package com.amilesend.tmdb.client.data.tv;

import com.amilesend.tmdb.client.model.PaginatedResponseBase;
import com.amilesend.tmdb.client.model.tv.series.list.GetAiringTodayResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetOnTheAirResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetPopularResponse;
import com.amilesend.tmdb.client.model.tv.series.list.GetTopRatedResponse;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeries;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateListOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class TvSeriesListsApiDataValidator {
    ///////////////////////////
    // GetAiringTodayResponse
    ///////////////////////////

    public static void assertSameGetAiringTodayResponse(
            final GetAiringTodayResponse expected,
            final GetAiringTodayResponse actual) {
        assertSamePaginatedResponse(expected, actual);
    }

    ////////////////////////
    // GetOnTheAirResponse
    ////////////////////////

    public static void assertSameGetOnTheAirResponse(
            final GetOnTheAirResponse expected,
            final GetOnTheAirResponse actual) {
        assertSamePaginatedResponse(expected, actual);
    }

    ///////////////////////
    // GetPopularResponse
    ///////////////////////

    public static void assertSameGetPopularResponse(
            final GetPopularResponse expected,
            final GetPopularResponse actual) {
        assertSamePaginatedResponse(expected, actual);
    }

    ////////////////////////
    // GetTopRatedResponse
    ////////////////////////

    public static void assertSameGetTopRatedResponse(
            final GetTopRatedResponse expected,
            final GetTopRatedResponse actual) {
        assertSamePaginatedResponse(expected, actual);
    }

    private static void assertSamePaginatedResponse(
            final PaginatedResponseBase<TvSeries> expected,
            final PaginatedResponseBase<TvSeries> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        TvSeriesApiDataValidator::assertSameTvSeries),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }
}
