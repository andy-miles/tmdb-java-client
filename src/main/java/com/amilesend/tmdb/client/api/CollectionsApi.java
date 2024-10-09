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

import com.amilesend.tmdb.client.connection.Connection;
import com.amilesend.tmdb.client.model.collection.GetCollectionDetailsRequest;
import com.amilesend.tmdb.client.model.collection.GetCollectionDetailsResponse;
import com.amilesend.tmdb.client.model.collection.GetCollectionImagesRequest;
import com.amilesend.tmdb.client.model.collection.GetCollectionImagesResponse;
import com.amilesend.tmdb.client.model.collection.GetTranslationsRequest;
import com.amilesend.tmdb.client.model.collection.GetTranslationsResponse;
import lombok.NonNull;

/** TMDB Collections API. */
public class CollectionsApi extends ApiBase {
    private static final String API_PATH = "/collection/";

    /**
     * Creates a new {@code CollectionsApi} object.
     *
     * @param connection the connection
     */
    public CollectionsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the collection details for the given identifier.
     *
     * @param request the request
     * @return the collection details
     * @see GetCollectionDetailsResponse
     * @see GetCollectionDetailsResponse
     */
    public GetCollectionDetailsResponse getCollectionDetails(@NonNull final GetCollectionDetailsRequest request) {
        return executeGet(API_PATH + request.getCollectionId(), request, GetCollectionDetailsResponse.class);
    }

    /**
     * Gets the images associated with a collection.
     *
     * @param request the request
     * @return the images
     * @see GetCollectionImagesRequest
     * @see GetCollectionImagesResponse
     */
    public GetCollectionImagesResponse getCollectionImages(@NonNull final GetCollectionImagesRequest request) {
        final String apiPath = new StringBuilder(API_PATH)
                .append(request.getCollectionId())
                .append("/images")
                .toString();
        return executeGet(apiPath, request, GetCollectionImagesResponse.class);
    }

    /**
     * Gets the translation information for a collection.
     *
     * @param request the request
     * @return the translations
     * @see GetTranslationsRequest
     * @see GetTranslationsResponse
     */
    public GetTranslationsResponse getTranslations(@NonNull final GetTranslationsRequest request) {
        final String apiPath = new StringBuilder(API_PATH)
                .append(request.getCollectionId())
                .append("/translations")
                .toString();
        return executeGet(apiPath, request, GetTranslationsResponse.class);
    }
}
