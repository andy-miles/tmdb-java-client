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

import com.amilesend.client.parse.strategy.GsonExclude;
import com.amilesend.tmdb.client.model.BodyBasedRequest;
import com.amilesend.tmdb.client.model.BodyParameter;
import com.amilesend.tmdb.client.model.PathParameter;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.SessionBasedRequest;
import com.amilesend.tmdb.client.model.acount.type.AccountBasedRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.Validate;

/**
 * The request information associated with adding a media item to an account's watchlist.
 *
 * @see BodyBasedRequest
 * @see AccountBasedRequest
 * @see SessionBasedRequest
 */
@Builder
@Data
public class AddWatchlistRequest implements BodyBasedRequest, AccountBasedRequest, SessionBasedRequest {
    /** The account identifier (required). */
    @GsonExclude
    @PathParameter
    private final int accountId;
    /** The session identifier (optional). */
    @GsonExclude
    @QueryParameter
    private final String sessionId;
    /** The media type (e.g., "movie", or "tv"; required). */
    @BodyParameter
    private final String mediaType;
    /** The unique media identifier (required). */
    @BodyParameter
    private final int mediaId;
    /**
     * When {@code true}, add the media to a watchlist; else, {@code false} to remove the media from a watchlist
     * (required).
     */
    @BodyParameter
    private final boolean watchlist;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        validateAccountId();
        Validate.isTrue(mediaId > 0, "mediaId must be > 0");
        Validate.notBlank(mediaType, "mediaType must not be blank");

        return populateSessionIdQueryParameterIfDefined(urlBuilder);
    }
}
