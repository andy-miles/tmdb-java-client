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
package com.amilesend.tmdb.client.connection.auth;

/** Defines the exception thrown from {@link RequestTokenGrantReceiver}. */
public class SessionException extends Exception {
    /**
     * Creates a new {@code SessionException}.
     *
     * @param msg the exception message
     */
    public SessionException(final String msg) {
        super(msg);
    }

    /**
     * Creates a new {@code SessionException}.
     *
     * @param msg the exception message
     * @param cause the cause of the exception
     */
    public SessionException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}