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
import com.amilesend.tmdb.client.model.acount.AddFavoriteRequest;
import com.amilesend.tmdb.client.model.acount.AddFavoriteResponse;
import com.amilesend.tmdb.client.model.acount.AddWatchlistRequest;
import com.amilesend.tmdb.client.model.acount.AddWatchlistResponse;
import com.amilesend.tmdb.client.model.acount.GetAccountDetailsForSessionRequest;
import com.amilesend.tmdb.client.model.acount.GetAccountDetailsRequest;
import com.amilesend.tmdb.client.model.acount.GetAccountDetailsResponse;
import com.amilesend.tmdb.client.model.acount.GetFavoriteMoviesRequest;
import com.amilesend.tmdb.client.model.acount.GetFavoriteMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetFavoriteTvShowsRequest;
import com.amilesend.tmdb.client.model.acount.GetFavoriteTvShowsResponse;
import com.amilesend.tmdb.client.model.acount.GetListsRequest;
import com.amilesend.tmdb.client.model.acount.GetListsResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedMoviesRequest;
import com.amilesend.tmdb.client.model.acount.GetRatedMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvEpisodesRequest;
import com.amilesend.tmdb.client.model.acount.GetRatedTvEpisodesResponse;
import com.amilesend.tmdb.client.model.acount.GetRatedTvShowsRequest;
import com.amilesend.tmdb.client.model.acount.GetRatedTvShowsResponse;
import com.amilesend.tmdb.client.model.acount.GetWatchlistMoviesRequest;
import com.amilesend.tmdb.client.model.acount.GetWatchlistMoviesResponse;
import com.amilesend.tmdb.client.model.acount.GetWatchlistTvRequest;
import com.amilesend.tmdb.client.model.acount.GetWatchlistTvResponse;
import com.amilesend.tmdb.client.model.acount.type.SortBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.GET_ACCOUNT_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.GET_FAVORITE_MOVIES_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.GET_FAVORITE_TV_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.GET_LISTS_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.GET_RATED_MOVIES_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.GET_RATED_TV_EPISODES_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.GET_RATED_TV_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.GET_WATCHLIST_MOVIES_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.GET_WATCHLIST_TV_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.Responses.POST_RESPONSE;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newAccountDetails;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newAddFavoriteResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newAddWatchlistResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newGetFavoriteMoviesResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newGetFavoriteTVResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newGetListsResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newGetRatedMoviesResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newGetRatedTVEpisodesResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newGetRatedTVResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newGetWatchlistMoviesResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataHelper.newGetWatchlistTVResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataValidator.assertSameAccountDetails;
import static com.amilesend.tmdb.client.data.account.AccountApiDataValidator.assertSameGetFavoriteMoviesResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataValidator.assertSameGetFavoriteTVResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataValidator.assertSameGetListsResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataValidator.assertSameGetRatedMoviesResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataValidator.assertSameGetRatedTVEpisodesResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataValidator.assertSameGetRatedTVResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataValidator.assertSameGetWatchlistMoviesResponse;
import static com.amilesend.tmdb.client.data.account.AccountApiDataValidator.assertSameGetWatchlistTVResponse;
import static org.junit.Assert.assertEquals;

public class AccountApiFunctionalTest extends FunctionalTestBase {
    private static final int ACCOUNT_ID = 1;
    private static final String SESSION_ID = "SessionId";

    private AccountApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getAccountApi();
    }

    @Test
    public void getAccountDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ACCOUNT_DETAILS_RESPONSE);
        final GetAccountDetailsResponse expected = newAccountDetails();

        final GetAccountDetailsResponse actual = apiUnderTest.getAccountDetails(
                GetAccountDetailsRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .sessionId(SESSION_ID)
                        .build());

        assertSameAccountDetails(expected, actual);
    }

    @Test
    public void getAccountDetailsForSession_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ACCOUNT_DETAILS_RESPONSE);
        final GetAccountDetailsResponse expected = newAccountDetails();

        final GetAccountDetailsResponse actual = apiUnderTest.getAccountDetailsForSession(
                GetAccountDetailsForSessionRequest.builder()
                        .sessionId(SESSION_ID)
                        .build());

        assertSameAccountDetails(expected, actual);
    }

    @Test
    public void addFavorite_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, POST_RESPONSE);
        final AddFavoriteResponse expected = newAddFavoriteResponse();

        final AddFavoriteResponse actual = apiUnderTest.addFavorite(
                AddFavoriteRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .sessionId(SESSION_ID)
                        .mediaType("movie")
                        .mediaId(100)
                        .favorite(true)
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void addWatchlist_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, POST_RESPONSE);
        final AddWatchlistResponse expected = newAddWatchlistResponse();

        final AddWatchlistResponse actual = apiUnderTest.addWatchlist(
                AddWatchlistRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .sessionId(SESSION_ID)
                        .mediaType("movie")
                        .mediaId(123)
                        .watchlist(true)
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void getFavoriteMovies_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_FAVORITE_MOVIES_RESPONSE);
        final GetFavoriteMoviesResponse expected = newGetFavoriteMoviesResponse();

        final GetFavoriteMoviesResponse actual = apiUnderTest.getFavoriteMovies(
                GetFavoriteMoviesRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .language(Locale.US.getLanguage())
                        .sessionId(SESSION_ID)
                        .page(1)
                        .sortBy(SortBy.CREATED_ASC)
                        .build());

        assertSameGetFavoriteMoviesResponse(expected, actual);
    }

    @Test
    public void getFavoriteTvShows_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_FAVORITE_TV_RESPONSE);
        final GetFavoriteTvShowsResponse expected = newGetFavoriteTVResponse();

        final GetFavoriteTvShowsResponse actual = apiUnderTest.getFavoriteTvShows(
                GetFavoriteTvShowsRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .language(Locale.US.getLanguage())
                        .sessionId(SESSION_ID)
                        .page(1)
                        .sortBy(SortBy.CREATED_ASC)
                        .build());

        assertSameGetFavoriteTVResponse(expected, actual);
    }

    @Test
    public void getLists_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_LISTS_RESPONSE);
        final GetListsResponse expected = newGetListsResponse();

        final GetListsResponse actual = apiUnderTest.getLists(
                GetListsRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .page(1)
                        .sessionId(SESSION_ID)
                        .build());

        assertSameGetListsResponse(expected, actual);
    }

    @Test
    public void getRatedMovies_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_RATED_MOVIES_RESPONSE);
        final GetRatedMoviesResponse expected = newGetRatedMoviesResponse();

        final GetRatedMoviesResponse actual = apiUnderTest.getRatedMovies(
                GetRatedMoviesRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .page(1)
                        .sessionId(SESSION_ID)
                        .sortBy(SortBy.CREATED_ASC)
                        .build());

        assertSameGetRatedMoviesResponse(expected, actual);
    }

    @Test
    public void getRatedTvShows_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_RATED_TV_RESPONSE);
        final GetRatedTvShowsResponse expected = newGetRatedTVResponse();

        final GetRatedTvShowsResponse actual = apiUnderTest.getRatedTvShows(
                GetRatedTvShowsRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .page(1)
                        .sessionId(SESSION_ID)
                        .sortBy(SortBy.CREATED_ASC)
                        .build());

        assertSameGetRatedTVResponse(expected, actual);
    }

    @Test
    public void getRatedTvShowsEpisodes_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_RATED_TV_EPISODES_RESPONSE);
        final GetRatedTvEpisodesResponse expected = newGetRatedTVEpisodesResponse();

        final GetRatedTvEpisodesResponse actual = apiUnderTest.getRatedTvEpisodes(
                GetRatedTvEpisodesRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .page(1)
                        .sessionId(SESSION_ID)
                        .sortBy(SortBy.CREATED_ASC)
                        .build());

        assertSameGetRatedTVEpisodesResponse(expected, actual);
    }

    @Test
    public void getWatchlistMovies_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_WATCHLIST_MOVIES_RESPONSE);
        final GetWatchlistMoviesResponse expected = newGetWatchlistMoviesResponse();

        final GetWatchlistMoviesResponse actual = apiUnderTest.getWatchlistMovies(
                GetWatchlistMoviesRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .page(1)
                        .sessionId(SESSION_ID)
                        .sortBy(SortBy.CREATED_ASC)
                        .build());

        assertSameGetWatchlistMoviesResponse(expected, actual);
    }

    @Test
    public void getWatchlistTvShows_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_WATCHLIST_TV_RESPONSE);
        final GetWatchlistTvResponse expected = newGetWatchlistTVResponse();

        final GetWatchlistTvResponse actual = apiUnderTest.getWatchlistTvShows(
                GetWatchlistTvRequest.builder()
                        .accountId(ACCOUNT_ID)
                        .page(1)
                        .sessionId(SESSION_ID)
                        .sortBy(SortBy.CREATED_ASC)
                        .build());

        assertSameGetWatchlistTVResponse(expected, actual);
    }
}
