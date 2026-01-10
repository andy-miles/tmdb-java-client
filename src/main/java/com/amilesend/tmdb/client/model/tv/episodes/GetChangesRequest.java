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
package com.amilesend.tmdb.client.model.tv.episodes;

import com.amilesend.client.util.Validate;
import com.amilesend.tmdb.client.model.PathParameter;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

import java.time.LocalDate;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotNull;

/**
 * Defines the request to retrieve a list of changes for a TV episode.
 *
 * @see QueryParameterBasedRequest
 */
@SuperBuilder
@Data
public class GetChangesRequest implements QueryParameterBasedRequest {
    /** The episode identifier (required). */
    @PathParameter
    private final int episodeId;
    /** The start date (optional). */
    @QueryParameter
    private final LocalDate startDate;
    /** The end date (optional). */
    @QueryParameter
    private final LocalDate endDate;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.isTrue(episodeId > 0, "episodeId must be > 0");
        appendIfNotNull(urlBuilder, "start_date", startDate);
        appendIfNotNull(urlBuilder, "end_date", endDate);

        return urlBuilder;
    }
}
