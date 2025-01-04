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
package com.amilesend.tmdb.client.data.list;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.list.AddMovieResponse;
import com.amilesend.tmdb.client.model.list.CheckItemStatusResponse;
import com.amilesend.tmdb.client.model.list.ClearListResponse;
import com.amilesend.tmdb.client.model.list.CreateListResponse;
import com.amilesend.tmdb.client.model.list.DeleteListResponse;
import com.amilesend.tmdb.client.model.list.GetListDetailsResponse;
import com.amilesend.tmdb.client.model.list.RemoveMovieResponse;
import com.amilesend.tmdb.client.model.list.type.ListItem;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class ListsApiDataHelper {
    public static AddMovieResponse newAddMovieResponse() {
        return AddMovieResponse.builder()
                .statusMessage("Success")
                .statusCode(201)
                .build();
    }

    public static CheckItemStatusResponse newCheckItemStatusResponse() {
        return CheckItemStatusResponse.builder()
                .id(1)
                .itemPresent(true)
                .build();
    }

    public static ClearListResponse newClearListResponse() {
        return ClearListResponse.builder()
                .statusMessage("Success")
                .statusCode(201)
                .build();
    }

    public static CreateListResponse newCreateListResponse() {
        return CreateListResponse.builder()
                .listId(100)
                .success(true)
                .statusCode(201)
                .statusMessage("Success")
                .build();
    }

    public static DeleteListResponse newDeleteListResponse() {
        return DeleteListResponse.builder()
                .statusMessage("Success")
                .statusCode(201)
                .build();
    }

    public static RemoveMovieResponse newRemoveMovieResponse() {
        return RemoveMovieResponse.builder()
                .statusMessage("Success")
                .statusCode(201)
                .build();
    }

    public static GetListDetailsResponse newGetListDetailsResponse() {
        final int listSize = 10;
        return GetListDetailsResponse.builder()
                .id(1)
                .createdBy("User name")
                .description("List description")
                .favoriteCount(50)
                .items(newListItemList(listSize))
                .itemCount(listSize)
                .languageCode(Locale.US.getLanguage())
                .posterPath("/poster.jpg")
                .build();
    }

    private static List<ListItem> newListItemList(final int size) {
        final List<ListItem> items = new ArrayList<>(size);
        for (int i = 1; i <= size; ++i) {
            items.add(newListItem(i));
        }

        return items;
    }

    private static ListItem newListItem(final int index) {
        return ListItem.builder()
                .id(index)
                .adult(false)
                .backdropPath("/backdrop.jpg")
                .genreIds(List.of(43, 59, 100))
                .mediaType("movie")
                .originalLanguage(Locale.US.getLanguage())
                .originalTitle("Original Title " + index)
                .overview("Summary " + index)
                .popularity(0.98D)
                .posterPath("poster.jpg")
                .releaseDate(LocalDate.of(2018,8, 2))
                .title("Title " + index)
                .video(false)
                .voteAverage(0.45D)
                .voteCount(45)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/ListsApi/";

        public static final SerializedResource ADD_MOVIES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "AddMoviesResponse.json");
        public static final SerializedResource CHECK_ITEM_STATUS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "CheckItemStatusResponse.json");
        public static final SerializedResource CLEAR_LIST_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "ClearListResponse.json");
        public static final SerializedResource CREATE_LIST_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "CreateListResponse.json");
        public static final SerializedResource DELETE_LIST_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "DeleteListResponse.json");
        public static final SerializedResource GET_LIST_DETAILS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetListDetailsResponse.json");
        public static final SerializedResource REMOVE_MOVIE_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "RemoveMovieResponse.json");
    }
}
