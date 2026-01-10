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

import com.amilesend.tmdb.client.model.tv.episodes.groups.GetTvEpisodeGroupDetailsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.groups.type.GroupEpisode;
import com.amilesend.tmdb.client.model.tv.episodes.groups.type.TvEpisodeGroup;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateListOf;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateNamedResource;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameTvSeriesNetwork;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class TvEpisodeGroupsApiDataValidator {
    public static void assertSameGetTvEpisodeGroupDetailsResponse(
            final GetTvEpisodeGroupDetailsResponse expected,
            final GetTvEpisodeGroupDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getDescription(), actual.getDescription()),
                () -> assertEquals(expected.getEpisodeCount(), actual.getEpisodeCount()),
                () -> assertEquals(expected.getGroupCount(), actual.getGroupCount()),
                () -> validateListOf(
                        expected.getGroups(),
                        actual.getGroups(),
                        TvEpisodeGroupsApiDataValidator::assertSameTvEpisodeGroup),
                () -> assertSameTvSeriesNetwork(expected.getNetwork(), actual.getNetwork()),
                () -> assertEquals(expected.getType(), actual.getType()));
    }

    private static void assertSameTvEpisodeGroup(final TvEpisodeGroup expected, final TvEpisodeGroup actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getLocked(), actual.getLocked()),
                () -> validateListOf(
                        expected.getEpisodes(),
                        actual.getEpisodes(),
                        TvEpisodeGroupsApiDataValidator::assertSameGroupEpisode));
    }

    private static void assertSameGroupEpisode(final GroupEpisode expected, final GroupEpisode actual) {
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
                () -> assertEquals(expected.getOrder(), actual.getOrder()));
    }
}
