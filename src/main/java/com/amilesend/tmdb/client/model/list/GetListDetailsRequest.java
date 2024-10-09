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
package com.amilesend.tmdb.client.model.list;

import com.amilesend.tmdb.client.model.PathParameter;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.Validate;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;
import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotNull;

/**
 * The request to retrieve details about a list.
 *
 * @see QueryParameterBasedRequest
 */
@Builder
@Data
public class GetListDetailsRequest implements QueryParameterBasedRequest {
    /** The list identifier (required). */
    @PathParameter
    private final int listId;
    /** The IETF BCP 47 language tag (optional). */
    @QueryParameter
    private final String language;
    /** The page number (optional). */
    @QueryParameter
    private final Integer page;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.isTrue(listId > 0, "listId must be > 0");

        appendIfNotBlank(urlBuilder, "language", language);
        appendIfNotNull(urlBuilder, "page", page);

        return urlBuilder;
    }
}