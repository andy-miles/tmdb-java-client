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
package com.amilesend.tmdb.client.data.genre;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.genre.GetMovieGenresResponse;
import com.amilesend.tmdb.client.model.genre.GetTvGenresResponse;
import com.amilesend.tmdb.client.model.type.Genre;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class GenresApiDataHelper {
    public static GetMovieGenresResponse newGetMovieGenresResponse() {
        return GetMovieGenresResponse.builder()
                .genres(newGenreList(12))
                .build();
    }

    public static GetTvGenresResponse newGetTvGenresResponse() {
        return GetTvGenresResponse.builder()
                .genres(newGenreList(5))
                .build();
    }

    public static List<Genre> newGenreList(final int size) {
        final List<Genre> genres = new ArrayList<>(size);
        for (int i = 1; i <= size; ++i) {
            genres.add(newGenre(i));
        }

        return genres;
    }

    public static Genre newGenre(final int index) {
        return Genre.builder()
                .id(index)
                .name("Genre " + index)
                .build();
    }

    @UtilityClass
    public static class Responses {
        public static final SerializedResource GET_MOVIE_GENRES_RESPONSE =
                new SerializedResource("/GenresApi/GetMovieGenresResponse.json");
        public static final SerializedResource GET_TV_GENRES_RESPONSE =
                new SerializedResource("/GenresApi/GetTvGenresResponse.json");
    }
}
