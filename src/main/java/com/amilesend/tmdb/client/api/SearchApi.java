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

import com.amilesend.client.connection.Connection;
import com.amilesend.tmdb.client.model.search.SearchCollectionsRequest;
import com.amilesend.tmdb.client.model.search.SearchCollectionsResponse;
import com.amilesend.tmdb.client.model.search.SearchCompaniesRequest;
import com.amilesend.tmdb.client.model.search.SearchCompaniesResponse;
import com.amilesend.tmdb.client.model.search.SearchKeywordsRequest;
import com.amilesend.tmdb.client.model.search.SearchKeywordsResponse;
import com.amilesend.tmdb.client.model.search.SearchMoviesRequest;
import com.amilesend.tmdb.client.model.search.SearchMoviesResponse;
import com.amilesend.tmdb.client.model.search.SearchMultiRequest;
import com.amilesend.tmdb.client.model.search.SearchMultiResponse;
import com.amilesend.tmdb.client.model.search.SearchPeopleRequest;
import com.amilesend.tmdb.client.model.search.SearchPeopleResponse;
import com.amilesend.tmdb.client.model.search.SearchTvRequest;
import com.amilesend.tmdb.client.model.search.SearchTvResponse;
import lombok.NonNull;

/** TMDB Search API. */
public class SearchApi extends ApiBase {

    private static String API_PATH = "/search/";

    /**
     * Creates a new {@code SearchApi} object.
     *
     * @param connection the connection
     */
    public SearchApi(final Connection connection) {
        super(connection);
    }

    /**
     * Search for collections by their alternative, original, and translated names.
     *
     * @param request the request
     * @return the response
     * @see SearchCollectionsRequest
     * @see SearchCollectionsResponse
     */
    public SearchCollectionsResponse searchCollections(@NonNull final SearchCollectionsRequest request) {
        return executeGet(API_PATH + "collection", request, SearchCollectionsResponse.class);
    }

    /**
     * Search for companies by their original and alternative names.
     *
     * @param request the request
     * @return the response
     * @see SearchCompaniesRequest
     * @see SearchCompaniesResponse
     */
    public SearchCompaniesResponse searchCompanies(@NonNull final SearchCompaniesRequest request) {
        return executeGet(API_PATH + "company", request, SearchCompaniesResponse.class);
    }

    /**
     * Search for keywords by name.
     *
     * @param request the request
     * @return the response
     * @see SearchKeywordsRequest
     * @see SearchKeywordsResponse
     */
    public SearchKeywordsResponse searchKeywords(@NonNull final SearchKeywordsRequest request) {
        return executeGet(API_PATH + "keyword", request, SearchKeywordsResponse.class);
    }

    /**
     * Search for movies by their alternative, original, and translated titles.
     *
     * @param request the request
     * @return the response
     * @see SearchMoviesRequest
     * @see SearchMoviesResponse
     */
    public SearchMoviesResponse searchMovies(@NonNull final SearchMoviesRequest request) {
        return executeGet(API_PATH + "movie", request, SearchMoviesResponse.class);
    }

    /**
     * Search for people, movies, and TV shows.
     *
     * @param request the request
     * @return the response
     * @see SearchMultiRequest
     * @see SearchMultiResponse
     */
    public SearchMultiResponse searchMulti(@NonNull final SearchMultiRequest request) {
        return executeGet(API_PATH + "multi", request, SearchMultiResponse.class);
    }

    /**
     * Search for people by the name and aliases.
     *
     * @param request the request
     * @return the response
     * @see SearchPeopleRequest
     * @see SearchPeopleResponse
     */
    public SearchPeopleResponse searchPeople(@NonNull final SearchPeopleRequest request) {
        return executeGet(API_PATH + "person", request, SearchPeopleResponse.class);
    }

    /**
     * Search for TV shows by their original, translated, and aka names.
     *
     * @param request the request
     * @return the response
     * @see SearchTvRequest
     * @see SearchTvResponse
     */
    public SearchTvResponse searchTv(@NonNull final SearchTvRequest request) {
        return executeGet(API_PATH + "tv", request, SearchTvResponse.class);
    }
}
