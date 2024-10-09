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

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.list.AddMovieRequest;
import com.amilesend.tmdb.client.model.list.AddMovieResponse;
import com.amilesend.tmdb.client.model.list.CheckItemStatusRequest;
import com.amilesend.tmdb.client.model.list.CheckItemStatusResponse;
import com.amilesend.tmdb.client.model.list.ClearListRequest;
import com.amilesend.tmdb.client.model.list.ClearListResponse;
import com.amilesend.tmdb.client.model.list.CreateListRequest;
import com.amilesend.tmdb.client.model.list.CreateListResponse;
import com.amilesend.tmdb.client.model.list.DeleteListRequest;
import com.amilesend.tmdb.client.model.list.DeleteListResponse;
import com.amilesend.tmdb.client.model.list.GetListDetailsRequest;
import com.amilesend.tmdb.client.model.list.GetListDetailsResponse;
import com.amilesend.tmdb.client.model.list.RemoveMovieRequest;
import com.amilesend.tmdb.client.model.list.RemoveMovieResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.Responses.ADD_MOVIES_RESPONSE;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.Responses.CHECK_ITEM_STATUS_RESPONSE;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.Responses.CLEAR_LIST_RESPONSE;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.Responses.CREATE_LIST_RESPONSE;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.Responses.DELETE_LIST_RESPONSE;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.Responses.GET_LIST_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.Responses.REMOVE_MOVIE_RESPONSE;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.newAddMovieResponse;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.newCheckItemStatusResponse;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.newClearListResponse;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.newCreateListResponse;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.newDeleteListResponse;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.newGetListDetailsResponse;
import static com.amilesend.tmdb.client.data.list.ListsApiDataHelper.newRemoveMovieResponse;
import static com.amilesend.tmdb.client.data.list.ListsApiDataValidator.assertSameGetListDetailsResponse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListsApiFunctionalTest extends FunctionalTestBase {
    private static final String SESSION_ID = "SessionId";
    private static final int LIST_ID = 100;

    private ListsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getListsApi();
    }

    @Test
    public void addMovie_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, ADD_MOVIES_RESPONSE);
        final AddMovieResponse expected = newAddMovieResponse();

        final AddMovieResponse actual = apiUnderTest.addMovie(
                AddMovieRequest.builder()
                        .listId(LIST_ID)
                        .mediaId(400)
                        .sessionId(SESSION_ID)
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void checkItemStatus_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, CHECK_ITEM_STATUS_RESPONSE);
        final CheckItemStatusResponse expected = newCheckItemStatusResponse();

        final CheckItemStatusResponse actual = apiUnderTest.checkItemStatus(
                CheckItemStatusRequest.builder()
                        .listId(LIST_ID)
                        .sessionId(SESSION_ID)
                        .language(Locale.US.getLanguage())
                        .movieId(1)
                        .build());

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getItemPresent(), actual.getItemPresent()));
    }

    @Test
    public void clearList_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, CLEAR_LIST_RESPONSE);
        final ClearListResponse expected = newClearListResponse();

        final ClearListResponse actual = apiUnderTest.clearList(
                ClearListRequest.builder()
                        .listId(LIST_ID)
                        .sessionId(SESSION_ID)
                        .confirm(true)
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void createList_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, CREATE_LIST_RESPONSE);
        final CreateListResponse expected = newCreateListResponse();

        final CreateListResponse actual = apiUnderTest.createList(
                CreateListRequest.builder()
                        .sessionId(SESSION_ID)
                        .name("List Name")
                        .language(Locale.US.getLanguage())
                        .description("List Description")
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void deleteList_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, DELETE_LIST_RESPONSE);
        final DeleteListResponse expected = newDeleteListResponse();

        final DeleteListResponse actual = apiUnderTest.deleteList(
                DeleteListRequest.builder()
                        .listId(LIST_ID)
                        .sessionId(SESSION_ID)
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void getListDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_LIST_DETAILS_RESPONSE);
        final GetListDetailsResponse expected = newGetListDetailsResponse();

        final GetListDetailsResponse actual = apiUnderTest.getListDetails(
                GetListDetailsRequest.builder()
                        .listId(LIST_ID)
                        .language(Locale.US.getLanguage())
                        .page(1)
                        .build());

        assertSameGetListDetailsResponse(expected, actual);
    }

    @Test
    public void removeMovie_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, REMOVE_MOVIE_RESPONSE);
        final RemoveMovieResponse expected = newRemoveMovieResponse();

        final RemoveMovieResponse actual = apiUnderTest.removeMovie(
                RemoveMovieRequest.builder()
                        .listId(LIST_ID)
                        .sessionId(SESSION_ID)
                        .mediaId(100)
                        .build());

        assertEquals(expected, actual);
    }
}
