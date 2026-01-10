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
package com.amilesend.tmdb.client.model.search.type;

import com.amilesend.tmdb.client.model.type.Person;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Defines a person search result.
 *
 * @see Person
 * @see SearchResult
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class PersonSearchResult extends Person implements SearchResult {
    /**
     * The list of credits that the person is known for.
     *
     * @see PersonCredit
     * @see MovieCastCredit
     * @see TvCastCredit
     */
    private final List<PersonCredit> knowFor;
    /** The media type for the movie. */
    private final MediaType mediaType;

    /**
     * Helper method to return the list of TV cast credits for a person.
     *
     * @return the list of TV cast credits
     */
    public List<TvCastCredit> getKnownForTv() {
        if (knowFor == null) {
            return Collections.emptyList();
        }

        return knowFor.stream()
                .filter(c -> TvCastCredit.class.isInstance(c))
                .map(c -> TvCastCredit.class.cast(c))
                .collect(Collectors.toList());
    }

    /**
     * Helper method to return the list of movie cast credits for a person.
     *
     * @return the list of movie cast credits
     */
    public List<MovieCastCredit> getKnownForMovies() {
        if (knowFor == null) {
            return Collections.emptyList();
        }

        return knowFor.stream()
                .filter(c -> MovieCastCredit.class.isInstance(c))
                .map(c -> MovieCastCredit.class.cast(c))
                .collect(Collectors.toList());
    }
}
