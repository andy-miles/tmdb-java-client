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
package com.amilesend.tmdb.client.model.credit;

import com.amilesend.tmdb.client.model.Resource;
import com.amilesend.tmdb.client.model.credit.type.Media;
import com.amilesend.tmdb.client.model.credit.type.Person;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * The response describing credit details.
 *
 * @see Resource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetCreditDetailsResponse extends Resource<String, GetCreditDetailsResponse> {
    /** The type of credit. */
    private final String creditType;
    /** The job department. */
    private final String department;
    /** The job. */
    private final String job;
    /** The media being credited. */
    private final Media media;
    /** The person being credited. */
    private final Person person;
}
