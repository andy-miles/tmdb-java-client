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
package com.amilesend.tmdb.client.model.discover.filter;

import com.amilesend.client.connection.RequestException;
import com.amilesend.client.util.StringUtils;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import okhttp3.HttpUrl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;

/**
 * Defines the region filter options.
 *
 * @see QueryParameterBasedRequest
 */
@Builder
@Data
public class RegionFilter implements QueryParameterBasedRequest {
    /** Filter by region (required). */
    @QueryParameter
    private final String region;
    /** Filter by certification (e.g., "PG-13"). Note: {@link #certificationCountry} must be defined (optional). */
    private final String certification;
    /**
     * Filter by certification comparison {@code (>=)}. Note: {@link #certificationCountry} must be defined (optional).
     */
    @QueryParameter
    private final String certificationGte;
    /**
     * Filter by certification comparison {@code (<=)}. Note: {@link #certificationCountry} must be defined (optional).
     */
    @QueryParameter
    private final String certificationLte;
    /** The certification country (e.g., "us") (optional). */
    @QueryParameter
    private final String certificationCountry;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        if (StringUtils.isBlank(region)) {
            return urlBuilder;
        }

        urlBuilder.addQueryParameter("region", URLEncoder.encode(region, StandardCharsets.UTF_8));
        appendIfNotBlank(urlBuilder, "certification", certification);
        appendIfNotBlank(urlBuilder, "certification.gte", certificationGte);
        appendIfNotBlank(urlBuilder, "certification.lte", certificationLte);

        if (!isCertificationFilterDefined()) {
            return urlBuilder;
        }

        if (StringUtils.isBlank(certificationCountry)) {
            throw new RequestException(
                    "RegionFilter must define a certification country when a certification is defined");
        }

        return urlBuilder.addQueryParameter(
                "certification_country",
                URLEncoder.encode(certificationCountry, StandardCharsets.UTF_8));
    }

    private boolean isCertificationFilterDefined() {
        return StringUtils.isNotBlank(certification)
                | StringUtils.isNotBlank(certificationGte)
                | StringUtils.isNotBlank(certificationLte);
    }
}
