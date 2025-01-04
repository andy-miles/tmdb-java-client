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
package com.amilesend.tmdb.client.data.certification;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.certification.GetMovieCertificationsResponse;
import com.amilesend.tmdb.client.model.certification.GetTVCertificationsResponse;
import com.amilesend.tmdb.client.model.certification.type.Certification;
import lombok.experimental.UtilityClass;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class CertificationsApiDataHelper {
    public static GetMovieCertificationsResponse newGetMovieCertificationsResponse() {
        return GetMovieCertificationsResponse.builder()
                .certifications(newCertificationMap())
                .build();
    }

    public static GetTVCertificationsResponse newGetTVCertificationsResponse() {
        return GetTVCertificationsResponse.builder()
                .certifications(newCertificationMap())
                .build();
    }

    private static Map<String, List<Certification>> newCertificationMap() {
        final Map<String, List<Certification>> certs = new LinkedHashMap<>(3);
        for (int i = 1; i <= 3; ++i) {
            certs.put(
                    "Country " + i,
                    List.of(newCertification(1), newCertification(2), newCertification(3)));
        }

        return certs;
    }

    private static Certification newCertification(int index) {
        return Certification.builder()
                .certification("Certification " + index)
                .meaning("Meaning " + index)
                .order(index)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/CertificationsApi/";

        public static final SerializedResource GET_CERTIFICATION_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetCertificationResponse.json");
    }
}
