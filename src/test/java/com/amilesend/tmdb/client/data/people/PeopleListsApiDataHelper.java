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
package com.amilesend.tmdb.client.data.people;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.people.lists.GetPopularPersonsResponse;
import com.amilesend.tmdb.client.model.people.lists.type.Media;
import com.amilesend.tmdb.client.model.people.lists.type.PopularPerson;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class PeopleListsApiDataHelper {
    //////////////////////////////
    // GetPopularPersonsResponse
    //////////////////////////////

    public static GetPopularPersonsResponse newGetPopularPersonsResponse() {
        return GetPopularPersonsResponse.builder()
                .page(1)
                .totalPages(1)
                .totalResults(3)
                .results(List.of(newPopularPerson(1), newPopularPerson(2), newPopularPerson(3)))
                .build();
    }

    private static PopularPerson newPopularPerson(final int index) {
        return PopularPerson.builder()
                .id(index)
                .name("Person " + index)
                .adult(true)
                .popularity(0.89D)
                .gender(1)
                .knownForDepartment("actor")
                .profilePath("/profile/" + index)
                .knownFor(List.of(
                        newMedia(index * 2 + 1),
                        newMedia(index * 2 + 2),
                        newMedia(index * 2 + 3)))
                .build();
    }

    private static Media newMedia(final int index) {
        return Media.builder()
                .id(index)
                .adult(false)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(10, 15, 20))
                .mediaType("movie")
                .originalLanguage(Locale.US.getLanguage())
                .originalTitle("Original Media Title " + index)
                .overview("Overview " + index)
                .posterPath("/poster" + index + ".jpg")
                .releaseDate(LocalDate.of(2020, 3, 15))
                .title("Media Title " + index)
                .video(false)
                .voteAverage(0.80D)
                .voteCount(100)
                .build();
    }

    @UtilityClass
    public static class Responses {
        public static final SerializedResource GET_POPULAR_PERSONS_RESPONSE =
                new SerializedResource("/PeopleListsApi/GetPopularPersonsResponse.json");
    }
}
