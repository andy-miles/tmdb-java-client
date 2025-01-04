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

import com.amilesend.tmdb.client.FunctionalTestBase;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.Responses.GET_CHANGES_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.Responses.GET_COMBINED_CREDITS_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.Responses.GET_EXTERNAL_IDS_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.Responses.GET_IMAGES_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.Responses.GET_LATEST_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.Responses.GET_MOVIE_CREDITS_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.Responses.GET_PERSON_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.Responses.GET_TRANSLATIONS_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.Responses.GET_TV_CREDITS_RESPONSE;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newGetChangesResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newGetCombinedCreditsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newGetExternalIDsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newGetImagesResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newGetLatestResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newGetMovieCreditsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newGetPersonDetailsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newGetTvCreditsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameGetChangesResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameGetCombinedCreditsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameGetExternalIDsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameGetImagesResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameGetLatestResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameGetMovieCreditsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameGetPersonDetailsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataValidator.assertSameGetTvCreditsResponse;

public class PeopleApiFunctionalTest extends FunctionalTestBase {
    private PeopleApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getPeopleApi();
    }

    @Test
    public void getPersonDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_PERSON_DETAILS_RESPONSE);
        final GetPersonDetailsResponse expected = newGetPersonDetailsResponse();

        final GetPersonDetailsResponse actual = apiUnderTest.getPersonDetails(
                GetPersonDetailsRequest.builder()
                        .personId(1)
                        .build());

        assertSameGetPersonDetailsResponse(expected, actual);
    }

    @Test
    public void getChanges_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CHANGES_RESPONSE);
        final GetChangesResponse expected = newGetChangesResponse();

        final GetChangesResponse actual = apiUnderTest.getChanges(GetChangesRequest.builder()
                .page(1)
                .personId(1)
                .build());

        assertSameGetChangesResponse(expected, actual);
    }

    @Test
    public void getCombinedCredits_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_COMBINED_CREDITS_RESPONSE);
        final GetCombinedCreditsResponse expected = newGetCombinedCreditsResponse();

        final GetCombinedCreditsResponse actual = apiUnderTest.getCombinedCredits(GetCombinedCreditsRequest.builder()
                .personId(1)
                .language(Locale.US.getLanguage())
                .build());

        assertSameGetCombinedCreditsResponse(expected, actual);
    }

    @Test
    public void getExternalIDs_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_EXTERNAL_IDS_RESPONSE);
        final GetExternalIDsResponse expected = newGetExternalIDsResponse();

        final GetExternalIDsResponse actual = apiUnderTest.getExternalIDs(GetExternalIDsRequest.builder()
                .personId(1)
                .build());

        assertSameGetExternalIDsResponse(expected, actual);
    }

    @Test
    public void getImages_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_IMAGES_RESPONSE);
        final GetImagesResponse expected = newGetImagesResponse();

        final GetImagesResponse actual = apiUnderTest.getImages(GetImagesRequest.builder()
                .personId(1)
                .build());

        assertSameGetImagesResponse(expected, actual);
    }

    @Test
    public void getLatest_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_LATEST_RESPONSE);
        final GetLatestResponse expected = newGetLatestResponse();

        final GetLatestResponse actual = apiUnderTest.getLatest();

        assertSameGetLatestResponse(expected, actual);
    }

    @Test
    public void getMovieCredits_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_MOVIE_CREDITS_RESPONSE);
        final GetMovieCreditsResponse expected = newGetMovieCreditsResponse();

        final GetMovieCreditsResponse actual = apiUnderTest.getMovieCredits(
                GetMovieCreditsRequest.builder()
                        .personId(1)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetMovieCreditsResponse(expected, actual);
    }

    @Test
    public void getTvCredits_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TV_CREDITS_RESPONSE);
        final GetTvCreditsResponse expected = newGetTvCreditsResponse();

        final GetTvCreditsResponse actual = apiUnderTest.getTvCredits(
                GetTvCreditsRequest.builder()
                        .personId(1)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetTvCreditsResponse(expected, actual);
    }

    @Test
    public void getTranslations_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TRANSLATIONS_RESPONSE);
        final GetTranslationsResponse expected = newGetTranslationsResponse();

        final GetTranslationsResponse actual = apiUnderTest.getTranslations(GetTranslationsRequest.builder()
                .personId(1)
                .build());

        assertSameGetTranslationsResponse(expected, actual);
    }
}
