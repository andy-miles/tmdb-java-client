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
package com.amilesend.tmdb.client.model.collection;

import com.amilesend.tmdb.client.model.PathParameter;
import com.amilesend.tmdb.client.model.collection.type.CollectionBasedRequest;
import lombok.Builder;
import lombok.Data;
import okhttp3.HttpUrl;

/**
 * Request to retrieve translations.
 *
 * @see CollectionBasedRequest
 */
@Builder
@Data
public class GetTranslationsRequest implements CollectionBasedRequest {
    /** The collection identifier (required). */
    @PathParameter
    private final int collectionId;

    @Override
    public HttpUrl.Builder populateQueryParameters(final HttpUrl.Builder urlBuilder) {
        validateCollectionId();
        return urlBuilder;
    }
}
