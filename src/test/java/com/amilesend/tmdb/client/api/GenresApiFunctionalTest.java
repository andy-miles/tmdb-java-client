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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.genre.GetMovieGenresRequest;
import com.amilesend.tmdb.client.model.genre.GetMovieGenresResponse;
import com.amilesend.tmdb.client.model.genre.GetTvGenresRequest;
import com.amilesend.tmdb.client.model.genre.GetTvGenresResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.genre.GenresApiDataHelper.Responses.GET_MOVIE_GENRES_RESPONSE;
import static com.amilesend.tmdb.client.data.genre.GenresApiDataHelper.Responses.GET_TV_GENRES_RESPONSE;
import static com.amilesend.tmdb.client.data.genre.GenresApiDataHelper.newGetMovieGenresResponse;
import static com.amilesend.tmdb.client.data.genre.GenresApiDataHelper.newGetTvGenresResponse;
import static com.amilesend.tmdb.client.data.genre.GenresApiDataValidator.assertSameGetMovieGenresResponse;
import static com.amilesend.tmdb.client.data.genre.GenresApiDataValidator.assertSameGetTvGenresResponse;

public class GenresApiFunctionalTest extends FunctionalTestBase {
    private GenresApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getGenresApi();
    }

    @Test
    public void getMovieGenres_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_MOVIE_GENRES_RESPONSE);
        final GetMovieGenresResponse expected = newGetMovieGenresResponse();

        final GetMovieGenresResponse actual = apiUnderTest.getMovieGenres(
                GetMovieGenresRequest.builder().language(Locale.US.getLanguage()).build());

        assertSameGetMovieGenresResponse(expected, actual);
    }

    @Test
    public void getTvGenres_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TV_GENRES_RESPONSE);
        final GetTvGenresResponse expected = newGetTvGenresResponse();

        final GetTvGenresResponse actual = apiUnderTest.getTvGenres(
                GetTvGenresRequest.builder().language(Locale.US.getLanguage()).build());

        assertSameGetTvGenresResponse(expected, actual);
    }
}
