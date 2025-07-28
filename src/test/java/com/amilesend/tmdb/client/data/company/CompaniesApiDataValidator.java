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
package com.amilesend.tmdb.client.data.company;

import com.amilesend.tmdb.client.model.company.GetAlternativeNamesResponse;
import com.amilesend.tmdb.client.model.company.GetCompanyDetailsResponse;
import com.amilesend.tmdb.client.model.company.GetCompanyImagesResponse;
import com.amilesend.tmdb.client.model.type.ImageResource;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateListOf;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateNamedResource;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateResource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class CompaniesApiDataValidator {

    ///////////////////
    // CompanyDetails
    ///////////////////

    public static void assertSameCompanyDetails(
            final GetCompanyDetailsResponse expected,
            final GetCompanyDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getDescription(), actual.getDescription()),
                () -> assertEquals(expected.getHeadquarters(), actual.getHeadquarters()),
                () -> assertEquals(expected.getHomepage(), actual.getHomepage()),
                () -> assertEquals(expected.getLogoPath(), actual.getLogoPath()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getParentCompany(), actual.getParentCompany()));
    }

    /////////////////////
    // AlternativeNames
    /////////////////////

    public static void assertSameAlternativeNames(
            final GetAlternativeNamesResponse expected,
            final GetAlternativeNamesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getResults(), actual.getResults()));
    }

    //////////////////
    // CompanyImages
    //////////////////

    public static void assertSameCompanyImages(
            final GetCompanyImagesResponse expected,
            final GetCompanyImagesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getLogos(),
                        actual.getLogos(),
                        CompaniesApiDataValidator::assertSameCompanyImage));
    }

    private static void assertSameCompanyImage(final ImageResource expected, final ImageResource actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getAspectRatio(), actual.getAspectRatio(), 0.01D),
                () -> assertEquals(expected.getFilePath(), actual.getFilePath()),
                () -> assertEquals(expected.getHeight(), actual.getHeight()),
                () -> assertEquals(expected.getFileType(), actual.getFileType()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getWidth(), actual.getWidth()));
    }
}
