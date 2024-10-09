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
package com.amilesend.tmdb.client.model.type;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Describes lists of streaming providers for content in a country.
 *
 * @see Provider
 */
@Builder
@Data
public class WatchProviderResult {
    /** The full URL to the content. */
    private final String link;
    /** List of streaming providers that provide the content as part of a subscription. */
    private final List<Provider> flatrate;
    /** List of streaming providers that provide the content for rental. */
    private final List<Provider> rent;
    /** The streaming providers that provid ethe content for purchase. */
    private final List<Provider> buy;
}
