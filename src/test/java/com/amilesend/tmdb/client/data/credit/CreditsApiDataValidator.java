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
package com.amilesend.tmdb.client.data.credit;

import com.amilesend.tmdb.client.model.credit.GetCreditDetailsResponse;
import com.amilesend.tmdb.client.model.credit.type.Media;
import com.amilesend.tmdb.client.model.credit.type.Person;
import com.amilesend.tmdb.client.model.credit.type.Season;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class CreditsApiDataValidator {
    public static void assertSameCreditDetails(final GetCreditDetailsResponse expected, final GetCreditDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getCreditType(), actual.getCreditType()),
                () -> assertEquals(expected.getDepartment(), actual.getDepartment()),
                () -> assertEquals(expected.getJob(), actual.getJob()),
                () -> assertSameMedia(expected.getMedia(), actual.getMedia()),
                () -> assertSamePersonCredit(expected.getPerson(), actual.getPerson()));
    }

    private static void assertSameMedia(final Media expected, final Media actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getFirstAirDate(), actual.getFirstAirDate()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getCharacter(), actual.getCharacter()),
                () -> assertSameSeasonList(expected.getSeasons(), actual.getSeasons()));
    }

    private static void assertSamePersonCredit(final Person expected, final Person actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getKnownForDepartment(), actual.getKnownForDepartment()),
                () -> assertEquals(expected.getProfilePath(), actual.getProfilePath()));
    }

    private static void assertSameSeasonList(final List<Season> expected, final List<Season> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameSeason(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameSeason(final Season expected, final Season actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAirDate(), actual.getAirDate()),
                () -> assertEquals(expected.getEpisodeCount(), actual.getEpisodeCount()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getSeasonNumber(), actual.getSeasonNumber()),
                () -> assertEquals(expected.getShowId(), actual.getShowId()));
    }
}
