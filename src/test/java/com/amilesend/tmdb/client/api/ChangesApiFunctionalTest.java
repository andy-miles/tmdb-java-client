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

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.change.GetMovieChangesRequest;
import com.amilesend.tmdb.client.model.change.GetMovieChangesResponse;
import com.amilesend.tmdb.client.model.change.GetPersonChangesRequest;
import com.amilesend.tmdb.client.model.change.GetPersonChangesResponse;
import com.amilesend.tmdb.client.model.change.GetTvChangesRequest;
import com.amilesend.tmdb.client.model.change.GetTvChangesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.amilesend.tmdb.client.data.change.ChangesApiDataHelper.Responses.GET_MOVIE_CHANGES_RESPONSE;
import static com.amilesend.tmdb.client.data.change.ChangesApiDataHelper.Responses.GET_PERSON_CHANGES_RESPONSE;
import static com.amilesend.tmdb.client.data.change.ChangesApiDataHelper.Responses.GET_TV_CHANGES_RESPONSE;
import static com.amilesend.tmdb.client.data.change.ChangesApiDataHelper.newGetMovieChangesResponse;
import static com.amilesend.tmdb.client.data.change.ChangesApiDataHelper.newGetPersonChangesResponse;
import static com.amilesend.tmdb.client.data.change.ChangesApiDataHelper.newGetTvChangesResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangesApiFunctionalTest extends FunctionalTestBase {
    private ChangesApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getChangesApi();
    }

    @Test
    public void getMovieChanges_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_MOVIE_CHANGES_RESPONSE);
        final GetMovieChangesResponse expected = newGetMovieChangesResponse();

        final GetMovieChangesResponse actual = apiUnderTest.getMovieChanges(
                GetMovieChangesRequest.builder()
                        .startDate(LocalDate.now().minus(5, ChronoUnit.DAYS))
                        .endDate(LocalDate.now().minus(1, ChronoUnit.DAYS))
                        .page(1)
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void getPersonChanges_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_PERSON_CHANGES_RESPONSE);
        final GetPersonChangesResponse expected = newGetPersonChangesResponse();

        final GetPersonChangesResponse actual = apiUnderTest.getPersonChanges(
                GetPersonChangesRequest.builder()
                        .startDate(LocalDate.now().minus(5, ChronoUnit.DAYS))
                        .endDate(LocalDate.now().minus(1, ChronoUnit.DAYS))
                        .page(1)
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void getTvChanges_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TV_CHANGES_RESPONSE);
        final GetTvChangesResponse expected = newGetTvChangesResponse();

        final GetTvChangesResponse actual = apiUnderTest.getTvChanges(
                GetTvChangesRequest.builder()
                        .startDate(LocalDate.now().minus(5, ChronoUnit.DAYS))
                        .endDate(LocalDate.now().minus(1, ChronoUnit.DAYS))
                        .page(1)
                        .build());

        assertEquals(expected, actual);
    }
}
