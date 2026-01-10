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
package com.amilesend.tmdb.client.model.find;

import com.amilesend.client.util.Validate;
import com.amilesend.tmdb.client.model.PathParameter;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import com.amilesend.tmdb.client.model.find.type.ExternalSource;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import okhttp3.HttpUrl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;

/**
 * The request to find resources by an external identifier.
 *
 * @see QueryParameterBasedRequest
 */
@Builder
@Data
public class FindByIdRequest implements QueryParameterBasedRequest {
    /** The external identifier (required). */
    @PathParameter
    private final String externalId;
    /** The external identifier type (required). */
    @QueryParameter
    private final ExternalSource externalSource;
    /** The language to filter on (optional). */
    @QueryParameter
    private final String language;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.notBlank(externalId, "externalId must not be blank");
        Validate.notNull(externalSource, "externalSource must not be null");

        urlBuilder.addQueryParameter(
                "external_source",
                URLEncoder.encode(externalSource.getValue(), StandardCharsets.UTF_8));
        return appendIfNotBlank(urlBuilder, "language", language);
    }
}
