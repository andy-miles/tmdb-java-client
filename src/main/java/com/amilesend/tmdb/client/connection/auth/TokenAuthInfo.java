package com.amilesend.tmdb.client.connection.auth;

import com.amilesend.client.connection.auth.AuthInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

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
