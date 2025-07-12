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
package com.amilesend.tmdb.client.model.list;

import com.amilesend.client.parse.strategy.GsonExclude;
import com.amilesend.tmdb.client.model.BodyBasedRequest;
import com.amilesend.tmdb.client.model.BodyParameter;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.SessionBasedRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.Validate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * The request to create a new list.
 *
 * @see BodyBasedRequest
 * @see SessionBasedRequest
 */
@Builder
@Data
public class CreateListRequest implements BodyBasedRequest, SessionBasedRequest {
    /** The session identifier (required). */
    @GsonExclude
    @QueryParameter
    private final String sessionId;
    /** The list name (required). */
    @BodyParameter
    private final String name;
    /** The list description (required). */
    @BodyParameter
    private final String description;
    /** The list language (required). */
    @BodyParameter
    private final String language;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.notBlank(sessionId, "sessionId must not be blank");
        Validate.notBlank(name, "name must not be blank");
        Validate.notBlank(description, "description must not be blank");
        Validate.notBlank(language, "language must not be blank");


        return urlBuilder.addQueryParameter("session_id", URLEncoder.encode(sessionId, StandardCharsets.UTF_8));
    }
}
