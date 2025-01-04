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
package com.amilesend.tmdb.client.model.people;

import com.amilesend.tmdb.client.model.NamedResource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * Describes a person.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetPersonDetailsResponse extends NamedResource<Integer, GetPersonDetailsResponse> {
    /** Adult flag indicator. */
    private final Boolean adult;
    /** Alternative aliases/names. */
    private final List<String> alsoKnownAs;
    /** Biography. */
    private final String biography;
    /** Date of death, if available. */
    private final LocalDate deathDate;
    /** Gender identifier. */
    private final Integer gender;
    /** Homepage/website. */
    private final String homepage;
    /** Internet Movie Database Identifier. */
    private final String imdbId;
    /** Type of jobs/roles the person is known for. */
    private final String knownForDepartment;
    /** The geographical birth location. */
    private final String placeOfBirth;
    /** The person's popularity. */
    private final Double popularity;
    /** Relative path to the person's profile. */
    private final String profilePath;
}
