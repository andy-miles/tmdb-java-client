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
package com.amilesend.tmdb.client.api;

import com.amilesend.client.connection.Connection;
import com.amilesend.tmdb.client.model.people.GetChangesRequest;
import com.amilesend.tmdb.client.model.people.GetChangesResponse;
import com.amilesend.tmdb.client.model.people.GetCombinedCreditsRequest;
import com.amilesend.tmdb.client.model.people.GetCombinedCreditsResponse;
import com.amilesend.tmdb.client.model.people.GetExternalIDsRequest;
import com.amilesend.tmdb.client.model.people.GetExternalIDsResponse;
import com.amilesend.tmdb.client.model.people.GetImagesRequest;
import com.amilesend.tmdb.client.model.people.GetImagesResponse;
import com.amilesend.tmdb.client.model.people.GetLatestResponse;
import com.amilesend.tmdb.client.model.people.GetMovieCreditsRequest;
import com.amilesend.tmdb.client.model.people.GetMovieCreditsResponse;
import com.amilesend.tmdb.client.model.people.GetPersonDetailsRequest;
import com.amilesend.tmdb.client.model.people.GetPersonDetailsResponse;
import com.amilesend.tmdb.client.model.people.GetTranslationsRequest;
import com.amilesend.tmdb.client.model.people.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.people.GetTvCreditsRequest;
import com.amilesend.tmdb.client.model.people.GetTvCreditsResponse;
import com.amilesend.tmdb.client.model.people.type.PersonIdRequestBase;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/** TMDB People API. */
public class PeopleApi extends ApiBase {
    private static String API_PATH = "/person/";

    /**
     * Creates a new {@code PeopleApi} object.
     *
     * @param connection the connection
     */
    public PeopleApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the details for a person.
     *
     * @param request the request
     * @return the response
     * @see GetPersonDetailsRequest
     * @see GetPersonDetailsResponse
     */
    public GetPersonDetailsResponse getPersonDetails(@NonNull final GetPersonDetailsRequest request) {
        return executeGet(getApiPath(request, StringUtils.EMPTY), request, GetPersonDetailsResponse.class);
    }

    /**
     * Gets the list of changes for a person.
     *
     * @param request the request
     * @return the response
     * @see GetChangesRequest
     * @see GetChangesResponse
     */
    public GetChangesResponse getChanges(@NonNull final GetChangesRequest request) {
        final String apiPath = new StringBuilder(API_PATH)
                .append(request.getPersonId())
                .append("/changes")
                .toString();
        return executeGet(apiPath, request, GetChangesResponse.class);
    }

    /**
     * Gets the credits for a person.
     *
     * @param request the request
     * @return the response
     * @see GetCombinedCreditsRequest
     * @see GetCombinedCreditsResponse
     */
    public GetCombinedCreditsResponse getCombinedCredits(@NonNull final GetCombinedCreditsRequest request) {
        return executeGet(getApiPath(request, "/combined_credits"), request, GetCombinedCreditsResponse.class);
    }

    /**
     * Gets the external site identifiers for a person.
     *
     * @param request the request
     * @return the response
     * @see GetExternalIDsRequest
     * @see GetExternalIDsResponse
     */
    public GetExternalIDsResponse getExternalIDs(@NonNull final GetExternalIDsRequest request) {
        return executeGet(getApiPath(request, "/external_ids"), request, GetExternalIDsResponse.class);
    }

    /**
     * Gets the list of profile images for a person.
     *
     * @param request the request
     * @return the response
     * @see GetImagesRequest
     * @see GetImagesResponse
     */
    public GetImagesResponse getImages(@NonNull final GetImagesRequest request) {
        return executeGet(getApiPath(request, "/images"), request, GetImagesResponse.class);
    }

    /**
     * Gets the newest created person in the database.
     *
     * @return the response
     * @see GetLatestResponse
     */
    public GetLatestResponse getLatest() {
        return executeGet(API_PATH + "/latest", GetLatestResponse.class);
    }

    /**
     * Gets the list of movie credits for a person.
     *
     * @param request the request
     * @return the response
     * @see GetMovieCreditsRequest
     * @see GetMovieCreditsResponse
     */
    public GetMovieCreditsResponse getMovieCredits(@NonNull final GetMovieCreditsRequest request) {
        return executeGet(getApiPath(request, "/movie_credits"), request, GetMovieCreditsResponse.class);
    }

    /**
     * Gets the list of TV credits for a person.
     *
     * @param request the request
     * @return the response
     * @see GetTvCreditsRequest
     * @see GetTvCreditsResponse
     */
    public GetTvCreditsResponse getTvCredits(@NonNull final GetTvCreditsRequest request) {
        return executeGet(getApiPath(request, "/tv_credits"), request, GetTvCreditsResponse.class);
    }

    /**
     * Gets the list of translations that belong to a person.
     *
     * @param request the request
     * @return the response
     * @see GetTranslationsRequest
     * @see GetTranslationsResponse
     */
    public GetTranslationsResponse getTranslations(@NonNull final GetTranslationsRequest request) {
        return executeGet(getApiPath(request, "/translations"), request, GetTranslationsResponse.class);
    }

    private static String getApiPath(final PersonIdRequestBase request, final String subApiPath) {
        return new StringBuilder(API_PATH)
                .append(request.getPersonId())
                .append(subApiPath)
                .toString();
    }
}
