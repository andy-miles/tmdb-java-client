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
import com.amilesend.tmdb.client.model.acount.GetRatedMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvEpisodesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvShowsResponse;
import com.amilesend.tmdb.client.model.acount.type.SortBy;
import com.amilesend.tmdb.client.model.guest.GetRatedMoviesRequest;
import com.amilesend.tmdb.client.model.guest.GetRatedTvEpisodesRequest;
import com.amilesend.tmdb.client.model.guest.GetRatedTvShowsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.guest.GuestSessionsApiDataHelper.Responses.GET_RATED_MOVIES_RESPONSE;
import static com.amilesend.tmdb.client.data.guest.GuestSessionsApiDataHelper.Responses.GET_RATED_TV_EPISODES_RESPONSE;
import static com.amilesend.tmdb.client.data.guest.GuestSessionsApiDataHelper.Responses.GET_RATED_TV_SHOWS_RESPONSE;
import static com.amilesend.tmdb.client.data.guest.GuestSessionsApiDataHelper.newGetRatedMoviesResponse;
import static com.amilesend.tmdb.client.data.guest.GuestSessionsApiDataHelper.newGetRatedTvEpisodesResponse;
import static com.amilesend.tmdb.client.data.guest.GuestSessionsApiDataHelper.newGetRatedTvShowsResponse;
import static com.amilesend.tmdb.client.data.guest.GuestSessionsApiDataValidator.assertSameGetRatedMoviesResponse;
import static com.amilesend.tmdb.client.data.guest.GuestSessionsApiDataValidator.assertSameGetRatedTvEpisodesResponse;
import static com.amilesend.tmdb.client.data.guest.GuestSessionsApiDataValidator.assertSameGetRatedTvShowsResponse;

public class GuestSessionsApiFunctionalTest extends FunctionalTestBase {
    private GuestSessionsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getGuestSessionsApi();
    }

    @Test
    public void getRatedMovies_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_RATED_MOVIES_RESPONSE);
        final GetRatedMoviesResponse expected = newGetRatedMoviesResponse();

        final GetRatedMoviesResponse actual = apiUnderTest.getRatedMovies(GetRatedMoviesRequest.builder()
                .page(1)
                .sortBy(SortBy.CREATED_ASC)
                .language(Locale.US.getLanguage())
                .guestSessionId("GuestSessionIdValue")
                .build());

        assertSameGetRatedMoviesResponse(expected, actual);
    }

    @Test
    public void getRatedTvShows_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_RATED_TV_SHOWS_RESPONSE);
        final GetRatedTvShowsResponse expected = newGetRatedTvShowsResponse();

        final GetRatedTvShowsResponse actual = apiUnderTest.getRatedTvShows(GetRatedTvShowsRequest.builder()
                .guestSessionId("GuestSessionIdValue")
                .page(1)
                .sortBy(SortBy.CREATED_ASC)
                .language(Locale.US.getLanguage())
                .build());

        assertSameGetRatedTvShowsResponse(expected, actual);
    }

    @Test
    public void getRatedTvEpisodes_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_RATED_TV_EPISODES_RESPONSE);
        final GetRatedTvEpisodesResponse expected = newGetRatedTvEpisodesResponse();

        final GetRatedTvEpisodesResponse actual = apiUnderTest.getRatedTvEpisodes(GetRatedTvEpisodesRequest.builder()
                .guestSessionId("GuestSessionIdValue")
                .sortBy(SortBy.CREATED_ASC)
                .language(Locale.US.getLanguage())
                .build());

        assertSameGetRatedTvEpisodesResponse(expected, actual);
    }
}
