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
import com.amilesend.tmdb.client.model.certification.GetMovieCertificationsResponse;
import com.amilesend.tmdb.client.model.certification.GetTVCertificationsResponse;

/** TMDB Certifications API. */
public class CertificationsApi extends ApiBase {
    /**
     * Creates a new {@code CertificationsApi} object.
     *
     * @param connection the connection
     */
    public CertificationsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the list of movie certifications for all countries.
     *
     * @return the response containing the map of movie certifications
     * @see GetMovieCertificationsResponse
     */
    public GetMovieCertificationsResponse getMovieCertifications() {
        return executeGet("/certification/movie/list", GetMovieCertificationsResponse.class);
    }

    /**
     * Gets the list of TV certifications for all countries.
     *
     * @return the response containing the map of TV certifications
     * @see GetTVCertificationsResponse
     */
    public GetTVCertificationsResponse getTVCertifications() {
        return executeGet("/certification/tv/list", GetTVCertificationsResponse.class);
    }
}
