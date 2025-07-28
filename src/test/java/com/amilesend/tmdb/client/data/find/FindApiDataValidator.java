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
package com.amilesend.tmdb.client.data.find;

import com.amilesend.tmdb.client.data.search.SearchApiDataValidator;
import com.amilesend.tmdb.client.model.find.FindByIdResponse;
import com.amilesend.tmdb.client.model.find.type.TvEpisodeSearchResult;
import com.amilesend.tmdb.client.model.find.type.TvSeasonSearchResult;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateListOf;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateNamedResource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class FindApiDataValidator {
    /////////////////////
    // FindByIdResponse
    /////////////////////

    public static void assertSameFindByIdResponse(
            final FindByIdResponse expected,
            final FindByIdResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateListOf(
                        expected.getMovieResults(),
                        actual.getMovieResults(),
                        SearchApiDataValidator::assertSameMovieSearchResult),
                () -> validateListOf(
                        expected.getPersonResults(),
                        actual.getPersonResults(),
                        SearchApiDataValidator::assertSamePersonSearchResult),
                () -> validateListOf(
                        expected.getTvResults(),
                        actual.getTvResults(),
                        SearchApiDataValidator::assertSameTvSeriesSearchResult),
                () -> validateListOf(
                        expected.getTvEpisodeResults(),
                        actual.getTvEpisodeResults(),
                        FindApiDataValidator::assertSameTvEpisodeSearchResult),
                () -> validateListOf(
                        expected.getTvSeasonResults(),
                        actual.getTvSeasonResults(),
                        FindApiDataValidator::assertSameTvSeasonSearchResult));
    }

    private static void assertSameTvEpisodeSearchResult(
            final TvEpisodeSearchResult expected,
            final TvEpisodeSearchResult actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> assertEquals(expected.getEpisodeNumber(), actual.getEpisodeNumber()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getProductionCode(), actual.getProductionCode()),
                () -> assertEquals(expected.getRuntime(), actual.getRuntime()),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getStillPath(), actual.getStillPath()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()),
                () -> assertEquals(expected.getShowId(), actual.getShowId()));
    }

    private static void assertSameTvSeasonSearchResult(
            final TvSeasonSearchResult expected,
            final TvSeasonSearchResult actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getShowId(), actual.getShowId()),
                () -> assertEquals(expected.getEpisodeCount(), actual.getEpisodeCount()));
    }
}
