package com.amilesend.tmdb.client.connection.auth;

import com.amilesend.client.connection.auth.AuthManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;

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
