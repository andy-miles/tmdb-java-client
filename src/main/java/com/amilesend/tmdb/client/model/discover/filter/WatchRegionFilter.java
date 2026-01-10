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
package com.amilesend.tmdb.client.model.discover.filter;

import com.amilesend.client.util.StringUtils;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import com.amilesend.tmdb.client.model.discover.type.MonetizationType;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import okhttp3.HttpUrl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;

/**
 * Defines the watch region filter.
 *
 * @see QueryParameterBasedRequest
 */
@Builder
@Data
public class WatchRegionFilter implements QueryParameterBasedRequest {
    /** Filter on the watch region (required). */
    @QueryParameter
    private final String watchRegion;
    /**
     * Filter on the monetization types (optional).
     * @see MonetizationType
     */
    @QueryParameter
    private final String withWatchMonetizationTypes;
    /** Filter on a delimited list of providers by ID (optional). */
    @QueryParameter
    private final String withWatchProviders;
    /** Filter on excluding a delimited list of providers by ID (optional). */
    @QueryParameter
    private final String withoutWatchProviders;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        if (StringUtils.isBlank(watchRegion)) {
            return urlBuilder;
        }

        urlBuilder.addQueryParameter("watch_region", URLEncoder.encode(watchRegion, StandardCharsets.UTF_8));
        appendIfNotBlank(urlBuilder, "with_watch_monetization_types", withWatchMonetizationTypes);
        appendIfNotBlank(urlBuilder, "with_watch_providers", withWatchProviders);
        appendIfNotBlank(urlBuilder, "without_watch_providers", withoutWatchProviders);

        return urlBuilder;
    }
}
