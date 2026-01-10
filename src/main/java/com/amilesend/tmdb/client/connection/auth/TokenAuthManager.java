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

import com.amilesend.client.connection.auth.AuthManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;

import static com.amilesend.client.connection.Connection.Headers.AUTHORIZATION;

/**
 * Authorization manager for read access tokens.
 *
 * @see AuthManager
 * @see TokenAuthInfo
 */
@RequiredArgsConstructor
public class TokenAuthManager implements AuthManager<TokenAuthInfo> {
    private static final String PREFIX = "Bearer ";

    /** The authorization information. */
    @NonNull
    private final TokenAuthInfo authInfo;

    @Override
    public TokenAuthInfo getAuthInfo() {
        return authInfo;
    }

    @Override
    public Request.Builder addAuthentication(final Request.Builder requestBuilder) {
        return requestBuilder.addHeader(AUTHORIZATION, PREFIX + authInfo.getReadAccessToken());
    }
}
