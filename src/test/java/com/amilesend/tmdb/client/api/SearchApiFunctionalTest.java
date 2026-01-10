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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.Responses.SEARCH_COLLECTIONS_RESPONSE;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.Responses.SEARCH_COMPANIES_RESPONSE;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.Responses.SEARCH_KEYWORDS_RESPONSE;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.Responses.SEARCH_MOVIES_RESPONSE;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.Responses.SEARCH_MULTI_RESPONSE;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.Responses.SEARCH_PEOPLE_RESPONSE;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.Responses.SEARCH_TV_SERIES_RESPONSE;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newSearchCollectionResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newSearchCompaniesResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newSearchKeywordsResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newSearchMoviesResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newSearchMultiResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newSearchPeopleResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataHelper.newSearchTvResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameSearchCollectionsResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameSearchCompaniesResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameSearchKeywordsResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameSearchMoviesResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameSearchMultiResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameSearchPeopleResponse;
import static com.amilesend.tmdb.client.data.search.SearchApiDataValidator.assertSameSearchTvResponse;

public class SearchApiFunctionalTest extends FunctionalTestBase {
    private SearchApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getSearchApi();
    }

    @Test
    public void searchCollections_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, SEARCH_COLLECTIONS_RESPONSE);
        final SearchCollectionsResponse expected = newSearchCollectionResponse();

        final SearchCollectionsResponse actual = apiUnderTest.searchCollections(
                SearchCollectionsRequest.builder()
                        .includeAdult(true)
                        .region(Locale.US.getCountry())
                        .language(Locale.US.getLanguage())
                        .page(1)
                        .query("Search Query")
                        .build());

        assertSameSearchCollectionsResponse(expected, actual);
    }

    @Test
    public void searchCompanies_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, SEARCH_COMPANIES_RESPONSE);
        final SearchCompaniesResponse expected = newSearchCompaniesResponse();

        final SearchCompaniesResponse actual = apiUnderTest.searchCompanies(
                SearchCompaniesRequest.builder()
                        .page(1)
                        .query("Search Query")
                        .build());

        assertSameSearchCompaniesResponse(expected, actual);
    }

    @Test
    public void searchKeywords_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, SEARCH_KEYWORDS_RESPONSE);
        final SearchKeywordsResponse expected = newSearchKeywordsResponse();

        final SearchKeywordsResponse actual = apiUnderTest.searchKeywords(
                SearchKeywordsRequest.builder()
                        .page(1)
                        .query("Search Query")
                        .build());

        assertSameSearchKeywordsResponse(expected, actual);
    }

    @Test
    public void searchMovies_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, SEARCH_MOVIES_RESPONSE);
        final SearchMoviesResponse expected = newSearchMoviesResponse();

        final SearchMoviesResponse actual = apiUnderTest.searchMovies(
                SearchMoviesRequest.builder()
                        .page(1)
                        .region(Locale.US.getCountry())
                        .language(Locale.US.getLanguage())
                        .query("Search Query")
                        .build());

        assertSameSearchMoviesResponse(expected, actual);
    }

    @Test
    public void searchMulti_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, SEARCH_MULTI_RESPONSE);
        final SearchMultiResponse expected = newSearchMultiResponse();

        final SearchMultiResponse actual = apiUnderTest.searchMulti(
                SearchMultiRequest.builder()
                        .page(1)
                        .language(Locale.US.getLanguage())
                        .includeAdult(true)
                        .query("Search Query")
                        .build());

        assertSameSearchMultiResponse(expected, actual);
    }

    @Test
    public void searchPeople_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, SEARCH_PEOPLE_RESPONSE);
        final SearchPeopleResponse expected = newSearchPeopleResponse();

        final SearchPeopleResponse actual = apiUnderTest.searchPeople(
                SearchPeopleRequest.builder()
                        .page(1)
                        .language(Locale.US.getLanguage())
                        .includeAdult(true)
                        .query("Search Query")
                        .build());

        assertSameSearchPeopleResponse(expected, actual);
    }

    @Test
    public void searchTv_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, SEARCH_TV_SERIES_RESPONSE);
        final SearchTvResponse expected = newSearchTvResponse();

        final SearchTvResponse actual = apiUnderTest.searchTv(
                SearchTvRequest.builder()
                        .page(1)
                        .region(Locale.US.getCountry())
                        .language(Locale.US.getLanguage())
                        .query("Search Query")
                        .build());

        assertSameSearchTvResponse(expected, actual);
    }
}
