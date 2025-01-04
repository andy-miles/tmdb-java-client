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

import lombok.NonNull;
import okhttp3.HttpUrl;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;

/**
 * Interface that signifies that a request defines a session identifier.
 *
 * @see QueryParameterBasedRequest
 */
public interface SessionBasedRequest extends QueryParameterBasedRequest {
    /**
     * Gets the session identifier.
     *
     * @return the session identifier
     */
    String getSessionId();

    /**
     * Defines the session identifier as a query parameter in the request URL builder if defined.
     *
     * @param urlBuilder the URL builder
     * @return the builder
     */
    default HttpUrl.Builder populateSessionIdQueryParameterIfDefined(@NonNull final HttpUrl.Builder urlBuilder) {
        return appendIfNotBlank(urlBuilder, "session_id", getSessionId());
    }
}
