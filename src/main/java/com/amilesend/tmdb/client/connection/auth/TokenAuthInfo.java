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
package com.amilesend.tmdb.client.connection.auth;

import com.amilesend.client.connection.auth.AuthInfo;
import com.amilesend.client.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

/**
 * Defines the authorization information that contains an access token.
 *
 * @see AuthInfo
 */
@EqualsAndHashCode
@Getter
public class TokenAuthInfo implements AuthInfo {
    /** The API read access token. */
    private final String readAccessToken;

    /**
     * Creates a new {@code TokenAuthInfo} object.
     *
     * @param readAccessToken the read access token.
     */
    public TokenAuthInfo(final String readAccessToken) {
        this.readAccessToken = Optional.ofNullable(readAccessToken)
                .filter(StringUtils::isNotBlank)
                .orElseThrow(() -> new IllegalArgumentException("token must not be blank"));
    }

    // Don't expose the auth token and secret.
    @Override
    public String toString() {
        return "**********";
    }
}
