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

import com.amilesend.tmdb.client.connection.Connection;
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
import com.amilesend.tmdb.client.model.list.type.ListRequestBase;
import lombok.NonNull;

/** TMDB Lists v3 API (public lists). */
public class ListsApi extends ApiBase {
    private static String API_PATH = "/list";
    private static String API_BASE_PATH = API_PATH + "/";

    /**
     * Creates a new {@code ListsApi} object.
     *
     * @param connection the connection
     */
    public ListsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Adds a movie to a list.
     *
     * @param request the request
     * @return the response
     * @see AddMovieRequest
     * @see AddMovieResponse
     */
    public AddMovieResponse addMovie(@NonNull final AddMovieRequest request) {
        return executePost(getApiPath(request, "/add_item"), request, AddMovieResponse.class);
    }

    /**
     * Checks an item status within a specific list.
     *
     * @param request the request
     * @return the response
     * @see CheckItemStatusRequest
     * @see CheckItemStatusResponse
     */
    public CheckItemStatusResponse checkItemStatus(@NonNull final CheckItemStatusRequest request) {
        return executeGet(getApiPath(request, "/item_status"), request, CheckItemStatusResponse.class);
    }

    /**
     * Clears a list.
     *
     * @param request the request
     * @return the response
     * @see ClearListRequest
     * @see ClearListResponse
     */
    public ClearListResponse clearList(@NonNull final ClearListRequest request) {
        return executePost(
                new StringBuilder(API_BASE_PATH)
                        .append(request.getListId())
                        .append("/clear")
                        .toString(),
                request,
                ClearListResponse.class);
    }

    /**
     * Creates a new list.
     *
     * @param request the request
     * @return the response
     * @see CreateListRequest
     * @see CreateListResponse
     */
    public CreateListResponse createList(@NonNull final CreateListRequest request) {
        return executePost(API_PATH, request, CreateListResponse.class);
    }

    /**
     * Deletes a list.
     *
     * @param request the request
     * @return the response
     * @see DeleteListRequest
     * @see DeleteListResponse
     */
    public DeleteListResponse deleteList(@NonNull final DeleteListRequest request) {
        return executeDelete(API_BASE_PATH + request.getListId(), request, DeleteListResponse.class);
    }

    /**
     * Gets the list details.
     *
     * @param request the request
     * @return the response
     * @see GetListDetailsRequest
     * @see GetListDetailsResponse
     */
    public GetListDetailsResponse getListDetails(@NonNull final GetListDetailsRequest request) {
        return executeGet(API_BASE_PATH + request.getListId(), request, GetListDetailsResponse.class);
    }

    /**
     * Removes a movie from a list.
     *
     * @param request the request
     * @return the response
     * @see RemoveMovieRequest
     * @see RemoveMovieResponse
     */
    public RemoveMovieResponse removeMovie(@NonNull final RemoveMovieRequest request) {
        return executePost(getApiPath(request, "/remove_item"), request, RemoveMovieResponse.class);
    }

    private static String getApiPath(final ListRequestBase request, final String apiSubPath) {
        return new StringBuilder(API_BASE_PATH)
                .append(request.getListId())
                .append(apiSubPath)
                .toString();
    }
}
