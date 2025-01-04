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
package com.amilesend.tmdb.client.model;

import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Objects;

import static com.amilesend.tmdb.client.parse.adapters.LocalDateTypeAdapter.FORMATTER;

/** Defines a request that contain parameters to be constructed as query parameters in an HTTP request. */
public interface QueryParameterBasedRequest {
    /**
     * Helper method to populate query parameters of a {@code HttpUrl.Builder}.
     *
     * @param urlBuilder the HttpUrl.Builder instance
     * @return the HttpUrl.Builder instance
     */
    HttpUrl.Builder populateQueryParameters(HttpUrl.Builder urlBuilder);

    /**
     * Helper method to populate a query parameter if the value is not {@code null}.
     *
     * @param urlBuilder the URL builder
     * @param name the query parameter name
     * @param value the query parameter value
     * @return the builder
     */
    static HttpUrl.Builder appendIfNotNull(
            final HttpUrl.Builder urlBuilder,
            final String name,
            final Object value) {
        if (Objects.isNull(value)) {
            return urlBuilder;
        }

        if (value instanceof LocalDate) {
            final String localDateStr = ((LocalDate) value).format(FORMATTER);
            return urlBuilder.addQueryParameter(name, URLEncoder.encode(localDateStr, StandardCharsets.UTF_8));
        }

        return urlBuilder.addQueryParameter(name, URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
    }

    /**
     * Helper method to populate a query parameter if the string value is not blank.
     *
     * @param urlBuilder the URL builder
     * @param name the query parameter name
     * @param value the query parameter value
     * @return the builder
     */
    static HttpUrl.Builder appendIfNotBlank(
            final HttpUrl.Builder urlBuilder,
            final String name,
            final String value) {
        if (StringUtils.isNotBlank(value)) {
            urlBuilder.addQueryParameter(name, URLEncoder.encode(value, StandardCharsets.UTF_8));
        }

        return urlBuilder;
    }
}
