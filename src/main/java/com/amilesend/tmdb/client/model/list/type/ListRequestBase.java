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
package com.amilesend.tmdb.client.model.list.type;

import com.amilesend.client.parse.strategy.GsonExclude;
import com.amilesend.client.util.Validate;
import com.amilesend.tmdb.client.model.PathParameter;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.SessionBasedRequest;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Defines a request that includes the list identifier.
 *
 * @see SessionBasedRequest
 */
@SuperBuilder
@Data
public abstract class ListRequestBase implements SessionBasedRequest {
    /** The list identifier (required). */
    @GsonExclude
    @PathParameter
    private final int listId;
    /** The session identifier (required). */
    @GsonExclude
    @QueryParameter
    private final String sessionId;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.isTrue(listId > 0, "listId must be > 0");
        Validate.notBlank(sessionId, "sessionId must not be blank");

        return urlBuilder.addQueryParameter("session_id", URLEncoder.encode(sessionId, StandardCharsets.UTF_8));
    }
}
