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
package com.amilesend.tmdb.client.data.company;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.company.GetAlternativeNamesResponse;
import com.amilesend.tmdb.client.model.company.GetCompanyDetailsResponse;
import com.amilesend.tmdb.client.model.company.GetCompanyImagesResponse;
import com.amilesend.tmdb.client.model.type.AlternateName;
import com.amilesend.tmdb.client.model.type.ImageResource;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class CompaniesApiDataHelper {
    ///////////////////
    // CompanyDetails
    ///////////////////

    public static GetCompanyDetailsResponse newCompanyDetails() {
        return GetCompanyDetailsResponse.builder()
                .id(1)
                .name("Company Name")
                .description("Company Description")
                .headquarters("Company Headquarter Location")
                .homepage("https://someurl.com")
                .logoPath("/companyLogo.jpg")
                .originCountry(Locale.US.getCountry())
                .parentCompany("Parent Company Name")
                .build();
    }

    /////////////////////
    // AlternativeNames
    /////////////////////

    public static GetAlternativeNamesResponse newAlternativeNames() {
        return GetAlternativeNamesResponse.builder()
                .id(1)
                .results(newAlternateNameList())
                .build();
    }

    private static List<AlternateName> newAlternateNameList() {
        final List<AlternateName> names = new ArrayList<>(3);
        for (int i = 1; i <= 3; ++i) {
            names.add(newAlternateName(i));
        }

        return names;
    }

    private static AlternateName newAlternateName(final int index) {
        return AlternateName.builder()
                .type("Type " + index)
                .name("Alternate Name " + index)
                .build();
    }

    //////////////////
    // CompanyImages
    //////////////////

    public static GetCompanyImagesResponse newCompanyImages() {
        return GetCompanyImagesResponse.builder()
                .id(1)
                .logos(newCompanyImageList())
                .build();
    }


    private static List<ImageResource> newCompanyImageList() {
        final List<ImageResource> images = new ArrayList<>(4);
        for (int i = 1; i <= 4; ++i) {
            images.add(newCompanyImage(i));
        }

        return images;
    }

    private static ImageResource newCompanyImage(final int index) {
        return ImageResource.builder()
                .id("ID_" + index)
                .aspectRatio(3.03951367781155D)
                .filePath("/imagePath.jpg")
                .height(329)
                .fileType(".jpg")
                .voteAverage(0.5D)
                .voteCount(10)
                .width(1000)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/CompaniesApi/";

        public static final SerializedResource COMPANY_DETAILS =
                new SerializedResource(RESOURCE_FOLDER + "CompanyDetails.json");
        public static final SerializedResource COMPANY_IMAGES =
                new SerializedResource(RESOURCE_FOLDER + "CompanyImages.json");
        public static final SerializedResource ALTERNATIVE_NAMES =
                new SerializedResource(RESOURCE_FOLDER + "AlternativeNames.json");
    }
}
