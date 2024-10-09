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
import com.amilesend.tmdb.client.model.collection.GetCollectionDetailsRequest;
import com.amilesend.tmdb.client.model.collection.GetCollectionDetailsResponse;
import com.amilesend.tmdb.client.model.collection.GetCollectionImagesRequest;
import com.amilesend.tmdb.client.model.collection.GetCollectionImagesResponse;
import com.amilesend.tmdb.client.model.collection.GetTranslationsRequest;
import com.amilesend.tmdb.client.model.collection.GetTranslationsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.collection.CollectionsApiDataHelper.Responses.COLLECTION_DETAILS;
import static com.amilesend.tmdb.client.data.collection.CollectionsApiDataHelper.Responses.COLLECTION_IMAGES;
import static com.amilesend.tmdb.client.data.collection.CollectionsApiDataHelper.Responses.TRANSLATIONS;
import static com.amilesend.tmdb.client.data.collection.CollectionsApiDataHelper.newCollectionDetails;
import static com.amilesend.tmdb.client.data.collection.CollectionsApiDataHelper.newCollectionImages;
import static com.amilesend.tmdb.client.data.collection.CollectionsApiDataHelper.newTranslations;
import static com.amilesend.tmdb.client.data.collection.CollectionsApiDataValidator.assertSameCollectionDetails;
import static com.amilesend.tmdb.client.data.collection.CollectionsApiDataValidator.assertSameImages;
import static com.amilesend.tmdb.client.data.collection.CollectionsApiDataValidator.assertSameTranslations;

public class CollectionsApiFunctionalTest extends FunctionalTestBase {
    private static final int COLLECTION_ID = 1241;

    private CollectionsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getCollectionsApi();
    }

    @Test
    public void getCollectionDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, COLLECTION_DETAILS);
        final GetCollectionDetailsResponse expected = newCollectionDetails();

        final GetCollectionDetailsResponse actual = apiUnderTest.getCollectionDetails(
                GetCollectionDetailsRequest.builder()
                        .collectionId(COLLECTION_ID)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameCollectionDetails(expected, actual);
    }

    @Test
    public void getCollectionImages_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, COLLECTION_IMAGES);
        final GetCollectionImagesResponse expected = newCollectionImages();

        final GetCollectionImagesResponse actual = apiUnderTest.getCollectionImages(
                GetCollectionImagesRequest.builder()
                        .collectionId(COLLECTION_ID)
                        .includeImageLanguages(Locale.FRENCH.getLanguage())
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameImages(expected, actual);
    }

    @Test
    public void getTranslations_withValidRequests_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, TRANSLATIONS);
        final GetTranslationsResponse expected = newTranslations();

        final GetTranslationsResponse actual = apiUnderTest.getTranslations(
                GetTranslationsRequest.builder()
                        .collectionId(COLLECTION_ID)
                        .build());

        assertSameTranslations(expected, actual);
    }
}
