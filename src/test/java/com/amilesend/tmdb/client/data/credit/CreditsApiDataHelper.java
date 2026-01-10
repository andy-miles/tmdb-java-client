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
package com.amilesend.tmdb.client.data.credit;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.credit.GetCreditDetailsResponse;
import com.amilesend.tmdb.client.model.credit.type.Media;
import com.amilesend.tmdb.client.model.credit.type.Person;
import com.amilesend.tmdb.client.model.credit.type.Season;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class CreditsApiDataHelper {
    public static GetCreditDetailsResponse newCreditDetails() {
        return GetCreditDetailsResponse.builder()
                .id("CreditIdValue")
                .creditType("cast")
                .department("Acting")
                .job("Actor")
                .media(newMedia())
                .person(newPerson())
                .build();
    }

    private static Media newMedia() {
        return Media.builder()
                .id(1)
                .name("Media Name")
                .adult(false)
                .backdropPath("/backdrop.jpg")
                .originalLanguage("en")
                .originalName("Name")
                .overview("Summary")
                .posterPath("/poster.jpg")
                .mediaType("tv")
                .genreIds(List.of(1, 2))
                .popularity(0.93D)
                .firstAirDate(LocalDate.of(2004, 9, 15))
                .voteAverage(88.0D)
                .voteCount(500)
                .originCountry(List.of("US"))
                .character("Character Name")
                .seasons(List.of(newSeason(1), newSeason(2)))
                .build();
    }

    private static Person newPerson() {
        return Person.builder()
                .id(1)
                .name("Person Name")
                .adult(true)
                .originalName("Original Name")
                .mediaType("person")
                .popularity(100D)
                .gender(1)
                .knownForDepartment("Acting")
                .profilePath("/profile.jpg")
                .build();
    }

    private static Season newSeason(final int index) {
        return Season.builder()
                .id(1)
                .name("Season " + index)
                .airDate(LocalDate.of(2022, 1,2))
                .episodeCount(12)
                .overview("Overview " + index)
                .posterPath("/poster.jpg")
                .seasonNumber(index)
                .showId(100)
                .build();
    }

    @UtilityClass
    public static class Responses {
        public static final SerializedResource CREDIT_DETAILS =
                new SerializedResource("/CreditsApi/CreditDetails.json");
    }
}
