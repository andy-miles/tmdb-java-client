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
package com.amilesend.tmdb.client.model.guest.type;

import com.amilesend.tmdb.client.model.GuestSessionBasedRequest;
import com.amilesend.tmdb.client.model.PathParameter;
import com.amilesend.tmdb.client.model.acount.type.PaginatedRequestBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.Validate;

/**
 * Base class to retrieve a list of resources for a guest session.
 *
 * @see PaginatedRequestBase
 * @see GuestSessionBasedRequest
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GuestSessionRequestBase extends PaginatedRequestBase implements GuestSessionBasedRequest {
    /** The guest session identifier. */
    @PathParameter
    private final String guestSessionId;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.notBlank(guestSessionId, "guestSessionId must not be blank");
        super.populateQueryParameters(urlBuilder);
        return populateGuestSessionIdQueryParameterIfDefined(urlBuilder);
    }
}
