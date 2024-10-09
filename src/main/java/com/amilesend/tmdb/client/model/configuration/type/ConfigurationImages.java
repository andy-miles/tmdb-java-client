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
package com.amilesend.tmdb.client.model.configuration.type;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/** Defines the configuration details for an image. */
@Builder
@Data
public class ConfigurationImages {
    /** The base URL for the image. */
    private final String baseUrl;
    /** The secure base URL for the image. */
    private final String secureBaseUrl;
    /** The list of supported backdrop sizes. */
    private final List<String> backdropSizes;
    /** The list of supported logo sizes. */
    private final List<String> logoSizes;
    /** The list of supported poster sizes. */
    private final List<String> posterSizes;
    /** The list of supported profile sizes. */
    private final List<String> profileSizes;
    /** The list of still sizes. */
    private final List<String> stillSizes;
}
