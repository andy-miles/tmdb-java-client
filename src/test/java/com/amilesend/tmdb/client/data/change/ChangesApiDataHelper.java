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
package com.amilesend.tmdb.client.data.change;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.change.GetMovieChangesResponse;
import com.amilesend.tmdb.client.model.change.GetPersonChangesResponse;
import com.amilesend.tmdb.client.model.change.GetTvChangesResponse;
import com.amilesend.tmdb.client.model.change.type.Change;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ChangesApiDataHelper {
    ////////////////////////////
    // GetMovieChangesResponse
    ////////////////////////////

    public static GetMovieChangesResponse newGetMovieChangesResponse() {
        return GetMovieChangesResponse.builder()
                .page(1)
                .results(newChanges())
                .totalPages(1)
                .totalResults(10)
                .build();
    }

    private static List<Change> newChanges() {
        final List<Change> changes = new ArrayList<>(10);
        for (int i = 1; i <= 10; ++i) {
            changes.add(newChange(i));
        }

        return changes;
    }

    private static Change newChange(final int index) {
        return Change.builder()
                .id(index)
                .adult(false)
                .build();
    }

    /////////////////////////////
    // GetPersonChangesResponse
    /////////////////////////////

    public static GetPersonChangesResponse newGetPersonChangesResponse() {
        return GetPersonChangesResponse.builder()
                .page(1)
                .results(newChanges())
                .totalPages(1)
                .totalResults(10)
                .build();
    }

    /////////////////////////
    // GetTvChangesResponse
    /////////////////////////

    public static GetTvChangesResponse newGetTvChangesResponse() {
        return GetTvChangesResponse.builder()
                .page(1)
                .results(newChanges())
                .totalPages(1)
                .totalResults(10)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/ChangesApi/";

        public static SerializedResource GET_MOVIE_CHANGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetMovieChangesResponse.json");
        public static SerializedResource GET_PERSON_CHANGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetPersonChangesResponse.json");
        public static SerializedResource GET_TV_CHANGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTvChangesResponse.json");
    }
}
