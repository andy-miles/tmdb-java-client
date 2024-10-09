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
package com.amilesend.tmdb.client.model.tv.seasons.type;

import com.amilesend.tmdb.client.model.type.Person;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Describes a cast credit that is specific to a {@link com.amilesend.tmdb.client.model.credit.type.Person}.
 *
 * @see com.amilesend.tmdb.client.model.type.Person
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class AggregateCastCredit extends Person {
    /** The list of roles associated with the TV season and person. */
    private final List<Role> roles;
    /** The total number of credited episodes. */
    private final Integer totalEpisodeCount;
    /** The display order. */
    private final Integer order;
}
