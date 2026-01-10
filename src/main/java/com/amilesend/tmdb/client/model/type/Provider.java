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
package com.amilesend.tmdb.client.model.type;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/** Describes a single streaming provider. */
@SuperBuilder
@Data
public class Provider {
    /** The relative path to the streaming provider logo. */
    private final String logoPath;
    /** The provider identifier. */
    private final Integer providerId;
    /** The provider name. */
    private final String providerName;
    /** The display priority, or sort order/weight. */
    private final Integer displayPriority;
}
