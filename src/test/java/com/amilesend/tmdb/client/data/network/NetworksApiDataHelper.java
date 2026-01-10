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
package com.amilesend.tmdb.client.data.network;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.network.GetAlternativeNamesResponse;
import com.amilesend.tmdb.client.model.network.GetImagesResponse;
import com.amilesend.tmdb.client.model.network.GetNetworkDetailsResponse;
import com.amilesend.tmdb.client.model.type.AlternateName;
import com.amilesend.tmdb.client.model.type.ImageResource;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class NetworksApiDataHelper {
    //////////////////////////////
    // GetNetworkDetailsResponse
    //////////////////////////////

    public static GetNetworkDetailsResponse newGetNetworkDetailsResponse() {
        return GetNetworkDetailsResponse.builder()
                .headquarters("Some Location, Country")
                .originCountry("Country")
                .homepage("https://somewebsite.com")
                .id(1)
                .logoPath("/networklogo.jpg")
                .name("Network Name Value")
                .build();
    }

    ////////////////////////////////
    // GetAlternativeNamesResponse
    ////////////////////////////////

    public static GetAlternativeNamesResponse newGetAlternativeNamesResponse() {
        return GetAlternativeNamesResponse.builder()
                .id(1)
                .results(List.of(newAlternateName(1), newAlternateName(2), newAlternateName(3)))
                .build();
    }

    private static AlternateName newAlternateName(final int index) {
        return AlternateName.builder()
                .name("Alt Name " + index)
                .type("Type " + index)
                .build();
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static GetImagesResponse newGetImagesResponse() {
        return GetImagesResponse.builder()
                .logos(List.of(newImageResource(1), newImageResource(2), newImageResource(3)))
                .build();
    }

    private static ImageResource newImageResource(final int index) {
        return ImageResource.builder()
                .id("ImageId" + index)
                .aspectRatio(1.33D)
                .height(480)
                .width(600)
                .voteAverage(0.5D)
                .filePath("/image" + index + ".jpg")
                .fileType("FileType" + index)
                .voteCount(100)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/NetworksApi/";

        public static final SerializedResource GET_NETWORK_DETAILS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetNetworkDetailsResponse.json");
        public static final SerializedResource GET_ALTERNATIVE_NAMES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAlternativeNamesResponse.json");
        public static final SerializedResource GET_IMAGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetImagesResponse.json");
    }
}
