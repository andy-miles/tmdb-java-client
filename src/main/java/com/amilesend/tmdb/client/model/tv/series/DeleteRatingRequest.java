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
package com.amilesend.tmdb.client.model.tv.series;

import com.amilesend.tmdb.client.model.GuestSessionBasedRequest;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.SessionBasedRequest;
import com.amilesend.tmdb.client.model.tv.series.type.SeriesRequestBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

/**
 * The request to deleting a rating for a TV series.
 *
 * @see SeriesRequestBase
 * @see SessionBasedRequest
 * @see GuestSessionBasedRequest
 */
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeleteRatingRequest extends SeriesRequestBase implements SessionBasedRequest, GuestSessionBasedRequest {
    /** The session identifier (optional). */
    @QueryParameter
    private final String sessionId;
    /** The guest session identifier (optional). */
    @QueryParameter
    private final String guestSessionId;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        super.populateQueryParameters(urlBuilder);
        populateSessionIdQueryParameterIfDefined(urlBuilder);
        return populateGuestSessionIdQueryParameterIfDefined(urlBuilder);
    }
}
