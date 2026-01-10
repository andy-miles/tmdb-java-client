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
package com.amilesend.tmdb.client;

import com.amilesend.client.connection.Connection;
import com.amilesend.client.connection.DefaultConnectionBuilder;
import com.amilesend.client.connection.retry.NoRetryStrategy;
import com.amilesend.tmdb.client.api.AccountApi;
import com.amilesend.tmdb.client.api.AuthenticationApi;
import com.amilesend.tmdb.client.api.CertificationsApi;
import com.amilesend.tmdb.client.api.ChangesApi;
import com.amilesend.tmdb.client.api.CollectionsApi;
import com.amilesend.tmdb.client.api.CompaniesApi;
import com.amilesend.tmdb.client.api.ConfigurationApi;
import com.amilesend.tmdb.client.api.CreditsApi;
import com.amilesend.tmdb.client.api.DiscoverApi;
import com.amilesend.tmdb.client.api.FindApi;
import com.amilesend.tmdb.client.api.GenresApi;
import com.amilesend.tmdb.client.api.GuestSessionsApi;
import com.amilesend.tmdb.client.api.KeywordsApi;
import com.amilesend.tmdb.client.api.ListsApi;
import com.amilesend.tmdb.client.api.MovieListsApi;
import com.amilesend.tmdb.client.api.MoviesApi;
import com.amilesend.tmdb.client.api.NetworksApi;
import com.amilesend.tmdb.client.api.PeopleApi;
import com.amilesend.tmdb.client.api.PeopleListsApi;
import com.amilesend.tmdb.client.api.ReviewsApi;
import com.amilesend.tmdb.client.api.SearchApi;
import com.amilesend.tmdb.client.api.TrendingApi;
import com.amilesend.tmdb.client.api.TvEpisodeGroupsApi;
import com.amilesend.tmdb.client.api.TvEpisodesApi;
import com.amilesend.tmdb.client.api.TvSeasonsApi;
import com.amilesend.tmdb.client.api.TvSeriesApi;
import com.amilesend.tmdb.client.api.TvSeriesListsApi;
import com.amilesend.tmdb.client.api.WatchProvidersApi;
import com.amilesend.tmdb.client.connection.auth.TokenAuthInfo;
import com.amilesend.tmdb.client.connection.auth.TokenAuthManager;
import com.amilesend.tmdb.client.parse.GsonFactory;
import lombok.Getter;
import okhttp3.OkHttpClient;

/**
 * A helper class to vend API classes that are associated with a {@link Connection} to TMDB service.
 *
 * @see Connection
 */
public class Tmdb {
    public static final String API_URL = "https://api.themoviedb.org/3";
    public static final String USER_AGENT = "TMDBJavaClient/3.4";

    @Getter
    private final Connection connection;

    /**
     * Creates a new {@code Tmdb} object that is configured with the default settings.
     *
     * @param readAccessToken the authenticated read access token
     */
    public Tmdb(final String readAccessToken) {
       this(readAccessToken, USER_AGENT);
    }

    /**
     * Creates a new {@code Tmdb} object that is configured with the default settings.
     *
     * @param readAccessToken the authenticated read access token
     * @param userAgent the user agent to define in requests made to the service
     */
    public Tmdb(final String readAccessToken, final String userAgent) {
        final TokenAuthInfo authInfo = new TokenAuthInfo(readAccessToken);
        connection = new DefaultConnectionBuilder()
                .userAgent(userAgent)
                .baseUrl(API_URL)
                .httpClient(new OkHttpClient.Builder().build())
                .authManager(new TokenAuthManager(authInfo))
                .gsonFactory(new GsonFactory())
                .isGzipContentEncodingEnabled(false)
                .retryStrategy(new NoRetryStrategy())
                .build();
    }

    /**
     * Creates a new {@code Tmdb} object for the given {@link Connection}.
     *
     * @param connection the connection
     */
    public Tmdb(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets a new {@link AccountApi} instance for the TMDB connection.
     *
     * @return the Account API
     * @see AccountApi
     */
    public AccountApi getAccountApi() {
        return new AccountApi(connection);
    }

    /**
     * Gets a new {@link AuthenticationApi} instance for the TMDB connection.
     *
     * @return the Authentication API
     * @see AuthenticationApi
     */
    public AuthenticationApi getAuthenticationApi() {
        return new AuthenticationApi(connection);
    }

    /**
     * Gets a new {@link CertificationsApi} instance for the TMDB connection.
     *
     * @return the Certifications API
     * @see CertificationsApi
     */
    public CertificationsApi getCertificationsApi() {
        return new CertificationsApi(connection);
    }

    /**
     * Gets a new {@link ChangesApi} instance for the TMDB connection.
     *
     * @return the Changes API
     * @see ChangesApi
     */
    public ChangesApi getChangesApi() {
        return new ChangesApi(connection);
    }

    /**
     * Gets a new {@link CollectionsApi} instance for the TMDB connection.
     *
     * @return the Collections API
     * @see CollectionsApi
     */
    public CollectionsApi getCollectionsApi() {
        return new CollectionsApi(connection);
    }

    /**
     * Gets a new {@link CompaniesApi} instance for the TMDB connection.
     *
     * @return the Companies API
     * @see CompaniesApi
     */
    public CompaniesApi getCompaniesApi() {
        return new CompaniesApi(connection);
    }

    /**
     * Gets a new {@link ConfigurationApi} instance for the TMDB connection.
     *
     * @return the Configuration API
     * @see ConfigurationApi
     */
    public ConfigurationApi getConfigurationApi() {
        return new ConfigurationApi(connection);
    }

    /**
     * Gets a new {@link CreditsApi} instance for the TMDB connection.
     *
     * @return the Credits API
     * @see CreditsApi
     */
    public CreditsApi getCreditsApi() {
        return new CreditsApi(connection);
    }

    /**
     * Gets a new {@link DiscoverApi} instance for the TMDB connection.
     *
     * @return the Discover API
     * @see DiscoverApi
     */
    public DiscoverApi getDiscoverApi() {
        return new DiscoverApi(connection);
    }

    /**
     * Gets a new {@link FindApi} instance for the TMDB connection.
     *
     * @return the Find API
     * @see FindApi
     */
    public FindApi getFindApi() {
        return new FindApi(connection);
    }

    /**
     * Gets a new {@link GenresApi} instance for the TMDB connection.
     *
     * @return the Genres API
     * @see GenresApi
     */
    public GenresApi getGenresApi() {
        return new GenresApi(connection);
    }

    /**
     * Gets a new {@link GuestSessionsApi} instance for the TMDB connection.
     *
     * @return the Guest Sessions API
     * @see GuestSessionsApi
     */
    public GuestSessionsApi getGuestSessionsApi() {
        return new GuestSessionsApi(connection);
    }

    /**
     * Gets a new {@link KeywordsApi} instance for the TMDB connection.
     *
     * @return the Keywords API
     * @see KeywordsApi
     */
    public KeywordsApi getKeywordsApi() {
        return new KeywordsApi(connection);
    }

    /**
     * Gets a new {@link ListsApi} instance for the TMDB connection.
     *
     * @return the Lists API
     * @see ListsApi
     */
    public ListsApi getListsApi() {
        return new ListsApi(connection);
    }

    /**
     * Gets a new {@link MovieListsApi} instance for the TMDB connection.
     *
     * @return the Movie Lists API
     * @see MovieListsApi
     */
    public MovieListsApi getMovieListsApi() {
        return new MovieListsApi(connection);
    }

    /**
     * Gets a new {@link MoviesApi} instance for the TMDB connection.
     *
     * @return the Movies API
     * @see MoviesApi
     */
    public MoviesApi getMoviesApi() {
        return new MoviesApi(connection);
    }

    /**
     * Gets a new {@link NetworksApi} instance for the TMDB connection.
     *
     * @return the Networks API
     * @see NetworksApi
     */
    public NetworksApi getNetworksApi() {
        return new NetworksApi(connection);
    }

    /**
     * Gets a new {@link PeopleApi} instance for the TMDB connection.
     *
     * @return the People API
     * @see PeopleApi
     */
    public PeopleApi getPeopleApi() {
        return new PeopleApi(connection);
    }

    /**
     * Gets a new {@link PeopleListsApi} instance for the TMDB connection.
     *
     * @return the People Lists API
     * @see PeopleListsApi
     */
    public PeopleListsApi getPeopleListsApi() {
        return new PeopleListsApi(connection);
    }

    /**
     * Gets a new {@link ReviewsApi} instance for the TMDB connection.
     *
     * @return the Reviews API
     * @see ReviewsApi
     */
    public ReviewsApi getReviewsApi() {
        return new ReviewsApi(connection);
    }

    /**
     * Gets a new {@link SearchApi} instance for the TMDB connection.
     *
     * @return the Search API
     * @see SearchApi
     */
    public SearchApi getSearchApi() {
        return new SearchApi(connection);
    }

    /**
     * Gets a new {@link TrendingApi} instance for the TMDB connection.
     *
     * @return the Trending API
     * @see TrendingApi
     */
    public TrendingApi getTrendingApi() {
        return new TrendingApi(connection);
    }

    /**
     * Gets a new {@link TvEpisodeGroupsApi} instance for the TMDB connection.
     *
     * @return the TV Episode Groups API
     * @see TvEpisodeGroupsApi
     */
    public TvEpisodeGroupsApi getTvEpisodeGroupsApi() {
        return new TvEpisodeGroupsApi(connection);
    }

    /**
     * Gets a new {@link TvEpisodesApi} instance for the TMDB connection.
     *
     * @return the TV Episodes API
     * @see TvEpisodesApi
     */
    public TvEpisodesApi getTvEpisodesApi() {
        return new TvEpisodesApi(connection);
    }

    /**
     * Gets a new {@link TvSeasonsApi} instance for the TMDB connection.
     *
     * @return the TV Seasons API
     * @see TvSeasonsApi
     */
    public TvSeasonsApi getTvSeasonsApi() {
        return new TvSeasonsApi(connection);
    }

    /**
     * Gets a new {@link TvSeriesApi} instance for the TMDB connection.
     *
     * @return the TV Series API
     * @see TvSeriesApi
     */
    public TvSeriesApi getTvSeriesApi() {
        return new TvSeriesApi(connection);
    }

    /**
     * Gets a new {@link TvSeriesListsApi} instance for the TMDB connection.
     *
     * @return the TV Series Lists API
     * @see TvSeriesListsApi
     */
    public TvSeriesListsApi getTvSeriesListsApi() {
        return new TvSeriesListsApi(connection);
    }

    /**
     * gets a new {@link WatchProvidersApi} instance for the TMDB connection.
     *
     * @return the Watch Providers API
     * @see WatchProvidersApi
     */
    public WatchProvidersApi getWatchProvidersApi() {
        return new WatchProvidersApi(connection);
    }
}
