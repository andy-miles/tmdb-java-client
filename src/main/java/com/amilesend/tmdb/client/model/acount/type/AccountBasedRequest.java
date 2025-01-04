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
package com.amilesend.tmdb.client.model.acount.type;

import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import org.apache.commons.lang3.Validate;

/**
 * Interface that signifies that a request requires an account identifier.
 * Note: This is typically used as a path parameter.
 *
 * @see QueryParameterBasedRequest
 */
public interface AccountBasedRequest extends QueryParameterBasedRequest {
    /**
     * Gets the account identifier.
     *
     * @return the account identifier
     */
    int getAccountId();

    /**
     * Validates if the defined account identifier is valid.
     */
    default void validateAccountId() {
        Validate.isTrue(getAccountId() > 0, "accountId must be > 0");
    }
}
