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
package com.amilesend.tmdb.client.model.people.lists.type;

import com.amilesend.tmdb.client.model.NamedResource;
import com.amilesend.tmdb.client.model.type.Person;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Describes a popular person and the associated media that the person is known for.
 *
 * @see Person
 * @see Media
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class PopularPerson extends NamedResource<Integer, PopularPerson> {
    /** Adult flag indicator. */
    private final Boolean adult;
    /** The person's gender. */
    private final Integer gender;
    /** The associated media that the person is known for. */
    private final List<Media> knownFor;
    /** The job department that the person is known for. */
    private final String knownForDepartment;
    /** The person's popularity. */
    private final Double popularity;
    /** The path to the person's profile. */
    private final String profilePath;
}
