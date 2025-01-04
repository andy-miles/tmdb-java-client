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
package com.amilesend.tmdb.client.model.discover.filter;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * Builder to construct a filter string that includes a delimited list of items. This allows for defined predicates
 * (i.e., "and", "or") to further define filter queries.
 * <br/>
 * Example:
 * <code>
 *     String providers = new FilterQueryBuilder<String>("netflix", QueryBuilder.Type.ADD).append("hulu").build();
 *     String genres = new FilterQueryBuilder<String>("drama", QueryBuilder.Type.OR).append("horror").build();
 * </code>
 *
 * @param <T> the type
 */
public class FilterQueryBuilder<T> {
    private final StringBuilder query;
    private final String delimiter;

    /**
     * Creates a new {@code QueryBuilder}.
     *
     * @param attribute the name of the attribute
     * @param type the attribute type
     * @see Type
     */
    public FilterQueryBuilder(@NonNull final T attribute, final Type type) {
        query = new StringBuilder(attribute.toString());
        delimiter = Optional.ofNullable(type).map(Type::getDelimiter).orElseGet(() -> Type.AND.getDelimiter());
    }

    /**
     * Appends an attribute value to the list.
     *
     * @param attribute the attribute value
     * @return the builder
     */
    public FilterQueryBuilder<T> append(@NonNull final T attribute) {
        query.append(delimiter).append(attribute);
        return this;
    }

    /**
     * Returns a new delimited string for the filter.
     *
     * @return the list of attributes
     */
    public String build() {
        return query.toString();
    }

    /** Defines the predicate type for the delimited filter list. */
    @Getter
    @RequiredArgsConstructor
    public enum Type {
        AND(","),
        OR("|");

        /** The delimiter. */
        private final String delimiter;
    }
}
