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

import com.amilesend.tmdb.client.model.PathParameter;
import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.SessionBasedRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

/**
 * The request to retrieve paginated session-based resources that is associated with an account.
 *
 * @see com.amilesend.tmdb.client.api.AccountApi
 */
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AccountAndSessionBasedPaginatedRequestBase extends PaginatedRequestBase
        implements AccountBasedRequest, SessionBasedRequest {
    /** The account identifier (required). */
    @PathParameter
    private final int accountId;
    /** The session identifier that is authorized to access the account information (optional). */
    @QueryParameter
    private final String sessionId;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull HttpUrl.Builder urlBuilder) {
        validateAccountId();
        urlBuilder = super.populateQueryParameters(urlBuilder);
        return populateSessionIdQueryParameterIfDefined(urlBuilder);
    }
}
