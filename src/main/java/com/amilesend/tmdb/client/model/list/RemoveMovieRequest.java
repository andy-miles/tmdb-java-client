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

import com.amilesend.client.util.Validate;
import com.amilesend.tmdb.client.model.BodyBasedRequest;
import com.amilesend.tmdb.client.model.BodyParameter;
import com.amilesend.tmdb.client.model.list.type.ListRequestBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

/**
 * The request to remove a movie from a list.
 *
 * @see ListRequestBase
 * @see BodyBasedRequest
 */
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RemoveMovieRequest extends ListRequestBase implements BodyBasedRequest {
    /** The move media identifier (required). */
    @BodyParameter
    private final int mediaId;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.isTrue(mediaId > 0, "mediaId must be > 0");
        return super.populateQueryParameters(urlBuilder);
    }
}
