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
package com.amilesend.tmdb.client.model.collection.type;

import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import org.apache.commons.lang3.Validate;

/**
 * Interface for a request that is specific to a collection.
 *
 * @see QueryParameterBasedRequest
 */
public interface CollectionBasedRequest extends QueryParameterBasedRequest {
    /**
     * Gets the collection identifier.
     *
     * @return the collection identifier
     */
    int getCollectionId();

    /** Determines if the collection identifier is valid. */
    default void validateCollectionId() {
        Validate.isTrue(getCollectionId() > 0, "collectionId must be > 0");
    }
}
