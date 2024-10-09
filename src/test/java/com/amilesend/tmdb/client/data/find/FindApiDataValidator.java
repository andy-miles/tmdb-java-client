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
package com.amilesend.tmdb.client.data.find;

import com.amilesend.tmdb.client.model.find.FindByIdResponse;
import com.amilesend.tmdb.client.model.find.type.TvEpisodeSearchResult;
import com.amilesend.tmdb.client.model.find.type.TvSeasonSearchResult;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameMovieSearchResults;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSamePersonSearchResults;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameTvSeriesSearchResults;
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
                () -> assertSameMovieSearchResults(expected.getMovieResults(), actual.getMovieResults()),
                () -> assertSamePersonSearchResults(expected.getPersonResults(), actual.getPersonResults()),
                () -> assertSameTvSeriesSearchResults(expected.getTvResults(), actual.getTvResults()),
                () -> assertSameTvEpisodeSearchResults(expected.getTvEpisodeResults(), actual.getTvEpisodeResults()),
                () -> assertSameTvSeasonSearchResults(expected.getTvSeasonResults(), actual.getTvSeasonResults()));
    }

    private static void assertSameTvEpisodeSearchResults(
            final List<TvEpisodeSearchResult> expected,
            final List<TvEpisodeSearchResult> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size();  ++i) {
            assertSameTvEpisodeSearchResult(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameTvEpisodeSearchResult(
            final TvEpisodeSearchResult expected,
            final TvEpisodeSearchResult actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
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

    private static void assertSameTvSeasonSearchResults(
            final List<TvSeasonSearchResult> expected,
            final List<TvSeasonSearchResult> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size();  ++i) {
            assertSameTvSeasonSearchResult(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameTvSeasonSearchResult(
            final TvSeasonSearchResult expected,
            final TvSeasonSearchResult actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
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
