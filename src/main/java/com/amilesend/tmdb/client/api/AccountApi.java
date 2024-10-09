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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.connection.Connection;
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
import com.amilesend.tmdb.client.model.acount.type.AccountBasedRequest;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/**
 * Defines APIs to access user account information.  Note: A valid session must be authenticated and obtained.  This
 * can be done via the {@link com.amilesend.tmdb.client.connection.auth.SessionManager}.
 *
 * @see com.amilesend.tmdb.client.connection.auth.SessionManager
 */
public class AccountApi extends ApiBase {
    private static final String API_PATH = "/account";

    /**
     * Creates a new {@code AccountApi} object.
     *
     * @param connection the underlying client connection
     */
    public AccountApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the account information.
     *
     * @param request the request
     * @return the account details
     * @see GetAccountDetailsRequest
     * @see GetAccountDetailsResponse
     */
    public GetAccountDetailsResponse getAccountDetails(@NonNull final GetAccountDetailsRequest request) {
        return executeGetForAccount(StringUtils.EMPTY, request, GetAccountDetailsResponse.class);
    }

    /**
     * Gets the account information associated with a session.
     *
     * @param request the request
     * @return the response
     * @see GetAccountDetailsForSessionRequest
     * @see GetAccountDetailsResponse
     */
    public GetAccountDetailsResponse getAccountDetailsForSession(
            @NonNull final GetAccountDetailsForSessionRequest request) {
        return executeGet(API_PATH, request, GetAccountDetailsResponse.class);
    }

    /**
     * Adds or removes a movie/TV show from an account's favorite list.
     *
     * @param request the request specifying the movie or tv show
     * @return the response indicating the status of the request
     * @see AddFavoriteRequest
     * @see AddFavoriteResponse
     */
    public AddFavoriteResponse addFavorite(@NonNull final AddFavoriteRequest request) {
        return executePostForAccount("/favorite", request, AddFavoriteResponse.class);
    }

    /**
     * Adds or removes a movie/TV show from an account's watch list.
     *
     * @param request the request specifying the move or tv show
     * @return the response indicating the status of the request
     * @see AddWatchlistRequest
     * @see AddWatchlistResponse
     */
    public AddWatchlistResponse addWatchlist(@NonNull final AddWatchlistRequest request) {
        return executePostForAccount("/watchlist", request, AddWatchlistResponse.class);
    }

    /**
     * Gets the list of favorite movies for an account.
     *
     * @param request the request
     * @return the list of movies
     * @see GetFavoriteMoviesRequest
     * @see GetFavoriteMoviesResponse
     */
    public GetFavoriteMoviesResponse getFavoriteMovies(@NonNull final GetFavoriteMoviesRequest request) {
        return executeGetForAccount("/favorite/movies", request, GetFavoriteMoviesResponse.class);
    }

    /**
     * Gets the list of favorite TV shows for an account.
     *
     * @param request the request
     * @return the list of favorite TV shows
     * @see GetFavoriteTvShowsRequest
     * @see GetFavoriteTvShowsResponse
     */
    public GetFavoriteTvShowsResponse getFavoriteTvShows(@NonNull final GetFavoriteTvShowsRequest request) {
        return executeGetForAccount("/favorite/tv", request, GetFavoriteTvShowsResponse.class);
    }

    /**
     * Gets the list of a user's configured lists.
     *
     * @param request the request
     * @return the user's lists
     * @see GetListsRequest
     * @see GetListsResponse
     */
    public GetListsResponse getLists(@NonNull final GetListsRequest request) {
        return executeGetForAccount("/lists", request, GetListsResponse.class);
    }

    /**
     * Gets the list of movies that a user rated.
     *
     * @param request the request
     * @return the list of rated movies
     * @see GetRatedMoviesRequest
     * @see GetRatedMoviesResponse
     */
    public GetRatedMoviesResponse getRatedMovies(@NonNull final GetRatedMoviesRequest request) {
        return executeGetForAccount("/rated/movies", request, GetRatedMoviesResponse.class);
    }

    /**
     * Gets the list of TV shows that a user rated.
     *
     * @param request the request
     * @return the list of rated TV shows
     * @see GetRatedTvShowsRequest
     * @see GetRatedTvShowsResponse
     */
    public GetRatedTvShowsResponse getRatedTvShows(@NonNull final GetRatedTvShowsRequest request) {
        return executeGetForAccount("/rated/tv", request, GetRatedTvShowsResponse.class);
    }

    /**
     * Gets the list of TV show episodes that a user rated.
     *
     * @param request the request
     * @return the list of TV show episodes
     * @see GetRatedTvEpisodesRequest
     * @see GetRatedTvEpisodesResponse
     */
    public GetRatedTvEpisodesResponse getRatedTvEpisodes(@NonNull final GetRatedTvEpisodesRequest request) {
        return executeGetForAccount("/rated/tv/episodes", request, GetRatedTvEpisodesResponse.class);
    }

    /**
     * Gets the list of movies on a user's watchlist.
     *
     * @param request the request
     * @return the list of movies
     * @see GetWatchlistMoviesRequest
     * @see GetWatchlistMoviesResponse
     */
    public GetWatchlistMoviesResponse getWatchlistMovies(@NonNull final GetWatchlistMoviesRequest request) {
        return executeGetForAccount("/watchlist/movies", request, GetWatchlistMoviesResponse.class);
    }

    /**
     * Gets the list of TV shows on a user's watchlist.
     *
     * @param request the request
     * @return the list of TV shows
     * @see GetWatchlistTvRequest
     * @see GetWatchlistTvResponse
     */
    public GetWatchlistTvResponse getWatchlistTvShows(@NonNull final GetWatchlistTvRequest request) {
        return executeGetForAccount("/watchlist/tv", request, GetWatchlistTvResponse.class);
    }

    private <T> T executeGetForAccount(
            final String apiSubPath,
            final AccountBasedRequest request,
            final Class<T> responseType) {
        final String fullApiPath = new StringBuilder(API_PATH)
                .append("/")
                .append(request.getAccountId())
                .append(apiSubPath)
                .toString();
        return executeGet(fullApiPath, request, responseType);
    }

    private <T> T executePostForAccount(
            final String apiSubPath,
            final AccountBasedRequest request,
            final Class<T> responseType) {
        final String fullApiPath = new StringBuilder(API_PATH)
                .append("/")
                .append(request.getAccountId())
                .append(apiSubPath)
                .toString();
        return executePost(fullApiPath, request, responseType);
    }
}
