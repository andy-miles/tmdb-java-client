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
package com.amilesend.tmdb.client.model.tv.seasons;

import com.amilesend.tmdb.client.model.Resource;
import com.amilesend.tmdb.client.model.tv.seasons.type.AggregateCastCredit;
import com.amilesend.tmdb.client.model.tv.seasons.type.AggregateCrewCredit;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * The response describing the list of aggregate credits for a TV season.
 *
 * @see Resource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetAggregateCreditsResponse extends Resource<Integer, GetAggregateCreditsResponse> {
    /** The list of cast credits for the season. */
    private final List<AggregateCastCredit> cast;
    /** The list of crew credits for the season. */
    private final List<AggregateCrewCredit> crew;
}
