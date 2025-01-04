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

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.movie.list.GetNowPlayingRequest;
import com.amilesend.tmdb.client.model.movie.list.GetNowPlayingResponse;
import com.amilesend.tmdb.client.model.movie.list.GetPopularRequest;
import com.amilesend.tmdb.client.model.movie.list.GetPopularResponse;
import com.amilesend.tmdb.client.model.movie.list.GetTopRatedRequest;
import com.amilesend.tmdb.client.model.movie.list.GetTopRatedResponse;
import com.amilesend.tmdb.client.model.movie.list.GetUpcomingRequest;
import com.amilesend.tmdb.client.model.movie.list.GetUpcomingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataHelper.Responses.GET_NOW_PLAYING_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataHelper.Responses.GET_POPULAR_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataHelper.Responses.GET_TOP_RATED_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataHelper.Responses.GET_UPCOMING_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataHelper.newGetNowPlayingResponse;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataHelper.newGetPopularResponse;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataHelper.newGetTopRatedResponse;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataHelper.newGetUpcomingResponse;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataValidator.assertSameGetNowPlayingResponse;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataValidator.assertSameGetUpcomingResponse;
import static com.amilesend.tmdb.client.data.movie.MovieListsApiDataValidator.assertSamePaginatedResponse;

public class MovieListsApiFunctionalTest extends FunctionalTestBase {
    private MovieListsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getMovieListsApi();
    }

    @Test
    public void getNowPlaying_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_NOW_PLAYING_RESPONSE);
        final GetNowPlayingResponse expected = newGetNowPlayingResponse();

        final GetNowPlayingResponse actual = apiUnderTest.getNowPlaying(
                GetNowPlayingRequest.builder()
                        .page(1)
                        .language(Locale.US.getLanguage())
                        .region(Locale.US.getCountry())
                        .build());

        assertSameGetNowPlayingResponse(expected, actual);
    }

    @Test
    public void getPopular_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_POPULAR_RESPONSE);
        final GetPopularResponse expected = newGetPopularResponse();

        final GetPopularResponse actual = apiUnderTest.getPopular(
                GetPopularRequest.builder()
                        .page(1)
                        .language(Locale.US.getLanguage())
                        .region(Locale.US.getCountry())
                        .build());

        assertSamePaginatedResponse(expected, actual);
    }

    @Test
    public void getTopRated_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TOP_RATED_RESPONSE);
        final GetTopRatedResponse expected = newGetTopRatedResponse();

        final GetTopRatedResponse actual = apiUnderTest.getTopRated(
                GetTopRatedRequest.builder()
                        .page(1)
                        .language(Locale.US.getLanguage())
                        .region(Locale.US.getCountry())
                        .build());

        assertSamePaginatedResponse(expected, actual);
    }

    @Test
    public void getUpcoming_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_UPCOMING_RESPONSE);
        final GetUpcomingResponse expected = newGetUpcomingResponse();

        final GetUpcomingResponse actual = apiUnderTest.getUpcoming(
                GetUpcomingRequest.builder()
                        .page(1)
                        .language(Locale.US.getLanguage())
                        .region(Locale.US.getCountry())
                        .build());

        assertSameGetUpcomingResponse(expected, actual);
    }
}
