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
package com.amilesend.tmdb.client.data.list;

import com.amilesend.tmdb.client.model.list.GetListDetailsResponse;
import com.amilesend.tmdb.client.model.list.type.ListItem;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class ListsApiDataValidator {
    public static void assertSameGetListDetailsResponse(
            final GetListDetailsResponse expected,
            final GetListDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getCreatedBy(), actual.getCreatedBy()),
                () -> assertEquals(expected.getDescription(), actual.getDescription()),
                () -> assertEquals(expected.getFavoriteCount(), actual.getFavoriteCount()),
                () -> assertSameListItems(expected.getItems(), actual.getItems()),
                () -> assertEquals(expected.getItemCount(), actual.getItemCount()),
                () -> assertEquals(expected.getLanguageCode(), actual.getLanguageCode()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()));
    }

    private static void assertSameListItems(final List<ListItem> expected, final List<ListItem> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size();  ++i) {
            assertSameListItem(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameListItem(final ListItem expected, final ListItem actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
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
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()));
    }
}
