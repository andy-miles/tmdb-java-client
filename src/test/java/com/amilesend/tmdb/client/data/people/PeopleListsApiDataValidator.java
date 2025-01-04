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
package com.amilesend.tmdb.client.data.people;

import com.amilesend.tmdb.client.model.people.lists.GetPopularPersonsResponse;
import com.amilesend.tmdb.client.model.people.lists.type.Media;
import com.amilesend.tmdb.client.model.people.lists.type.PopularPerson;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class PeopleListsApiDataValidator {
    //////////////////////////////
    // GetPopularPersonsResponse
    //////////////////////////////

    public static void assertSameGetPopularPersonsResponse(
            final GetPopularPersonsResponse expected,
            final GetPopularPersonsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSamePoplarPersons(expected.getResults(), actual.getResults()));
    }

    private static void assertSamePoplarPersons(
            final List<PopularPerson> expected,
            final List<PopularPerson> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSamePopularPerson(expected.get(i), actual.get(i));
        }
    }

    private static void assertSamePopularPerson(final PopularPerson expected, final PopularPerson actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(),  0.01D),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getKnownForDepartment(), actual.getKnownForDepartment()),
                () -> assertEquals(expected.getProfilePath(), actual.getProfilePath()),
                () -> assertSameMediaList(expected.getKnownFor(), actual.getKnownFor()));
    }

    private static void assertSameMediaList(final List<Media> expected, final List<Media> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameMedia(expected.get(i), actual.get(i));
        }
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
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()));
    }
}
