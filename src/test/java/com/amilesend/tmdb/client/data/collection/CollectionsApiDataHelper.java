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
package com.amilesend.tmdb.client.data.collection;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.collection.GetCollectionDetailsResponse;
import com.amilesend.tmdb.client.model.collection.GetCollectionImagesResponse;
import com.amilesend.tmdb.client.model.collection.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.collection.type.CollectionPart;
import com.amilesend.tmdb.client.model.collection.type.CollectionTranslationData;
import com.amilesend.tmdb.client.model.type.Image;
import com.amilesend.tmdb.client.model.type.Translation;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class CollectionsApiDataHelper {
    //////////////////////
    // CollectionDetails
    //////////////////////

    public static GetCollectionDetailsResponse newCollectionDetails() {
        return GetCollectionDetailsResponse.builder()
                .id(1241)
                .name("Collection Name")
                .overview("Collection Summary")
                .posterPath("/collectionPosterPath.jpg")
                .backdropPath("/collectionBackdropPath.jpg")
                .parts(newParts())
                .build();
    }

    private static List<CollectionPart> newParts() {
        final List<CollectionPart> parts = new ArrayList<>(4);
        for (int i = 1; i <= 4; ++i) {
            parts.add(newPart(i));
        }
        return parts;
    }

    private static CollectionPart newPart(final int index) {
        return CollectionPart.builder()
                .id(index)
                .adult(false)
                .backdropPath("/backdropPath.jpg")
                .title("Part Title " + index)
                .originalLanguage(Locale.US.getLanguage())
                .originalTitle("Original Title " + index)
                .overview("Summary " + index)
                .posterPath("/posterPath.jpg")
                .mediaType("movie")
                .genreIds(List.of(1, 2, 3))
                .popularity(0.7D)
                .releaseDate(LocalDate.of(2004, 6, 15))
                .video(false)
                .voteAverage(0.65D)
                .voteCount(2000)
                .build();
    }

    /////////////////////
    // CollectionImages
    /////////////////////

    public static GetCollectionImagesResponse newCollectionImages() {
        return GetCollectionImagesResponse.builder()
                .id(1)
                .backdrops(newCollectionImageList())
                .posters(newCollectionImageList())
                .build();
    }


    private static List<Image> newCollectionImageList() {
        final List<Image> collectionImages = new ArrayList<>(5);
        for (int i = 1; i <= 5; ++i) {
            collectionImages.add(newCollectionImage(i));
        }

        return collectionImages;
    }

    private static Image newCollectionImage(final int index) {
        return Image.builder()
                .aspectRatio(1.77D)
                .height(1080)
                .languageCode(Locale.US.getLanguage())
                .filePath("/imageFilePath" + index)
                .voteAverage(0.9D)
                .voteCount(1000)
                .width(1920)
                .build();
    }

    /////////////////
    // Translations
    /////////////////

    public static GetTranslationsResponse newTranslations() {
        return GetTranslationsResponse.builder()
                .id(1)
                .translations(newTranslationList())
                .build();
    }

    private static List<Translation<CollectionTranslationData>> newTranslationList() {
        final List<Translation<CollectionTranslationData>> translations = new ArrayList<>(3);
        for (int i = 1; i <= 3; ++i) {
            translations.add(newTranslation(i));
        }

        return translations;
    }

    private static Translation<CollectionTranslationData> newTranslation(final int index) {
        return Translation.<CollectionTranslationData>builder()
                .countryCode(Locale.US.getCountry())
                .languageCode(Locale.US.getLanguage())
                .name("English " + index)
                .englishName("English " + index)
                .data(CollectionTranslationData.builder()
                        .title("Title " + index)
                        .overview("Summary " + index)
                        .homepage("https://someurl.com")
                        .build())
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/CollectionsApi/";

        public static final SerializedResource COLLECTION_DETAILS =
                new SerializedResource(RESOURCE_FOLDER + "CollectionDetails.json");
        public static final SerializedResource COLLECTION_IMAGES =
                new SerializedResource(RESOURCE_FOLDER + "CollectionImages.json");
        public static final SerializedResource TRANSLATIONS =
                new SerializedResource(RESOURCE_FOLDER + "Translations.json");
    }
}
