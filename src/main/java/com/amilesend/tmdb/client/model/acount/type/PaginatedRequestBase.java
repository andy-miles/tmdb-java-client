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
package com.amilesend.tmdb.client.model.acount.type;

import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;
import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotNull;

/**
 * Defines the base class for paginated requests.
 *
 * @see QueryParameterBasedRequest
 */
@SuperBuilder
@Data
public abstract class PaginatedRequestBase implements QueryParameterBasedRequest {
    /** The language to filter the resources on. */
    @QueryParameter
    private final String language;
    /** The page number (must be > 0). */
    @QueryParameter
    private final Integer page;
    /** Sort by option. */
    @QueryParameter
    private final SortBy sortBy;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        appendIfNotNull(urlBuilder, "page", page);
        appendIfNotBlank(urlBuilder, "language", language);
        appendIfNotNull(urlBuilder, "sort_by", sortBy);

        return urlBuilder;
    }
}
