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
package com.amilesend.tmdb.client.parse.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Defines a {@link GsonParser} implementation for a list of objects.
 *
 * @param <T> the object type
 */
public class ListParser<T> implements GsonParser<List<T>> {
    private final Type typeSpecifier;

    /**
     * Creates a new {@code ListParser} for the given class type.
     *
     * @param clazz the class type
     */
    public ListParser(@NonNull final Class<T> clazz) {
        typeSpecifier = TypeToken.getParameterized(List.class, clazz).getType();
    }

    @Override
    public List<T> parse(@NonNull final Gson gson, @NonNull final InputStream jsonStream) {
        return gson.fromJson(new InputStreamReader(jsonStream), typeSpecifier);
    }
}
