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
package com.amilesend.tmdb.client.api;

import com.amilesend.client.connection.Connection;
import com.amilesend.tmdb.client.model.genre.GetMovieGenresRequest;
import com.amilesend.tmdb.client.model.genre.GetMovieGenresResponse;
import com.amilesend.tmdb.client.model.genre.GetTvGenresRequest;
import com.amilesend.tmdb.client.model.genre.GetTvGenresResponse;
import lombok.NonNull;

/** TMDB Genres API. */
public class GenresApi extends ApiBase {
    /**
     * Create a new {@code GenresApi} object.
     *
     * @param connection the connection
     */
    public GenresApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the list of supported movie genres.
     *
     * @param request the request
     * @return the response containing the list of genres
     * @see GetMovieGenresRequest
     * @see GetMovieGenresResponse
     */
    public GetMovieGenresResponse getMovieGenres(@NonNull final GetMovieGenresRequest request) {
        return executeGet("/genre/movie/list", request, GetMovieGenresResponse.class);
    }

    /**
     * Gets the list of supported TV show genres.
     *
     * @param request the request
     * @return the response containing the list of genres
     * @see GetTvGenresRequest
     * @see GetTvGenresResponse
     */
    public GetTvGenresResponse getTvGenres(@NonNull final GetTvGenresRequest request) {
        return executeGet("/genre/tv/list", request, GetTvGenresResponse.class);
    }
}
