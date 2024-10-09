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
package com.amilesend.tmdb.client.data.network;

import com.amilesend.tmdb.client.model.network.GetAlternativeNamesResponse;
import com.amilesend.tmdb.client.model.network.GetImagesResponse;
import com.amilesend.tmdb.client.model.network.GetNetworkDetailsResponse;
import com.amilesend.tmdb.client.model.type.ImageResource;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class NetworksApiDataValidator {
    //////////////////////////////
    // GetNetworkDetailsResponse
    //////////////////////////////

    public static void assertSameGetNetworkDetailsResponse(
            final GetNetworkDetailsResponse expected,
            final GetNetworkDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getHeadquarters(), actual.getHeadquarters()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getLogoPath(), actual.getLogoPath()),
                () -> assertEquals(expected.getHomepage(), actual.getHomepage()));
    }

    ////////////////////////////////
    // GetAlternativeNamesResponse
    ////////////////////////////////

    public static void assertSameGetAlternativeNamesResponse(
            final GetAlternativeNamesResponse expected,
            final GetAlternativeNamesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getResults(), actual.getResults()));
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static void assertSameGetImagesResponse(final GetImagesResponse expected, final GetImagesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameImageResources(expected.getLogos(), actual.getLogos()));
    }

    private static void assertSameImageResources(final List<ImageResource> expected, final List<ImageResource> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameImageResource(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameImageResource(final ImageResource expected, final ImageResource actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getFilePath(), actual.getFilePath()),
                () -> assertEquals(expected.getHeight(), actual.getHeight()),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getWidth(), actual.getWidth()),
                () -> assertEquals(expected.getFileType(), actual.getFileType()),
                () -> assertEquals(expected.getAspectRatio(), actual.getAspectRatio(), 0.01D));
    }
}
