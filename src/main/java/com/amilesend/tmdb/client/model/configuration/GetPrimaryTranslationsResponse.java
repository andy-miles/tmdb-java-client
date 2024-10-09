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
package com.amilesend.tmdb.client.model.configuration;

import com.amilesend.tmdb.client.model.configuration.type.ConfigurationResponseBase;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * The response that describes a list of translations.
 *
 * @see ConfigurationResponseBase
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetPrimaryTranslationsResponse extends ConfigurationResponseBase<String> {
    /**
     * Creates a new {@code GetPrimaryTranslationsResponse}.
     *
     * @param results the list of translations
     */
    public GetPrimaryTranslationsResponse(final List<String> results) {
        super(results);
    }
}