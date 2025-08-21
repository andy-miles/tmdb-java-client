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
package com.amilesend.tmdb.client.model.acount;

import com.amilesend.client.util.Validate;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.SessionBasedRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import okhttp3.HttpUrl;

/**
 * Defines a request to retrieve account details associated with a session.
 *
 * @see SessionBasedRequest
 */
@Builder
@Data
public class GetAccountDetailsForSessionRequest implements SessionBasedRequest {
    /** The session identifier (required). */
    @QueryParameter
    private final String sessionId;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.notBlank(sessionId, "sessionId must not be blank");
        return populateSessionIdQueryParameterIfDefined(urlBuilder);
    }
}
