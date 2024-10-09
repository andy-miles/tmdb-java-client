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
package com.amilesend.tmdb.client.data.tv;

import com.amilesend.tmdb.client.model.tv.episodes.groups.GetTvEpisodeGroupDetailsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.groups.type.GroupEpisode;
import com.amilesend.tmdb.client.model.tv.episodes.groups.type.TvEpisodeGroup;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getDescription(), actual.getDescription()),
                () -> assertEquals(expected.getEpisodeCount(), actual.getEpisodeCount()),
                () -> assertEquals(expected.getGroupCount(), actual.getGroupCount()),
                () -> assertSameTvEpisodeGroups(expected.getGroups(), actual.getGroups()),
                () -> assertSameTvSeriesNetwork(expected.getNetwork(), actual.getNetwork()),
                () -> assertEquals(expected.getType(), actual.getType()));
    }

    private static void assertSameTvEpisodeGroups(final List<TvEpisodeGroup> expected, final List<TvEpisodeGroup> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameTvEpisodeGroup(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameTvEpisodeGroup(final TvEpisodeGroup expected, final TvEpisodeGroup actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getLocked(), actual.getLocked()),
                () -> assertSameGroupEpisodes(expected.getEpisodes(), actual.getEpisodes()));
    }

    private static void assertSameGroupEpisodes(final List<GroupEpisode> expected, final List<GroupEpisode> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameGroupEpisode(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameGroupEpisode(final GroupEpisode expected, final GroupEpisode actual) {
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
                () -> assertEquals(expected.getOrder(), actual.getOrder()));
    }
}
