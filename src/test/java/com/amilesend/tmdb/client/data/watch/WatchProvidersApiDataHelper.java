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
package com.amilesend.tmdb.client.data.watch;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.watch.GetAvailableRegionsResponse;
import com.amilesend.tmdb.client.model.watch.GetMovieProvidersResponse;
import com.amilesend.tmdb.client.model.watch.GetTvProvidersResponse;
import com.amilesend.tmdb.client.model.watch.type.Region;
import com.amilesend.tmdb.client.model.watch.type.WatchProvider;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

@UtilityClass
public class WatchProvidersApiDataHelper {
    ////////////////////////////////
    // GetAvailableRegionsResponse
    ////////////////////////////////

    public static GetAvailableRegionsResponse newGetAvailableRegionsResponse() {
        return GetAvailableRegionsResponse.builder()
                .results(List.of(newRegion(1), newRegion(2), newRegion(3)))
                .build();
    }

    private static Region newRegion(final int index) {
        return Region.builder()
                .countryCode("Country Code " + index)
                .englishName("English Name " + index)
                .nativeName("Native Name: " + index)
                .build();
    }

    //////////////////////////////
    // GetMovieProvidersResponse
    //////////////////////////////

    public static GetMovieProvidersResponse newGetMovieProvidersResponse() {
        return GetMovieProvidersResponse.builder()
                .results(List.of(newWatchProvider(1), newWatchProvider(2), newWatchProvider(3)))
                .build();
    }

    ///////////////////////////
    // GetTvProvidersResponse
    ///////////////////////////

    public static GetTvProvidersResponse newGetTvProvidersResponse() {
        return GetTvProvidersResponse.builder()
                .results(List.of(newWatchProvider(4), newWatchProvider(5), newWatchProvider(6)))
                .build();
    }


    private static WatchProvider newWatchProvider(final int index) {
        return WatchProvider.builder()
                .logoPath("/logo" + index + ".jpg")
                .providerId(index)
                .providerName("Provider " + index)
                .displayPriority(index)
                .displayPriorities(Map.of(
                        "US", 1,
                        "GB", 2))
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/WatchProvidersApi/";

        public static final SerializedResource GET_AVAILABLE_REGIONS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAvailableRegionsResponse.json");
        public static final SerializedResource GET_MOVIE_PROVIDERS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetMovieProvidersResponse.json");
        public static final SerializedResource GET_TV_PROVIDERS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTvProvidersResponse.json");
    }
}
