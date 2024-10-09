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
import com.amilesend.tmdb.client.model.configuration.GetConfigurationDetailsResponse;
import com.amilesend.tmdb.client.model.configuration.GetCountriesResponse;
import com.amilesend.tmdb.client.model.configuration.GetJobsResponse;
import com.amilesend.tmdb.client.model.configuration.GetLanguagesResponse;
import com.amilesend.tmdb.client.model.configuration.GetPrimaryTranslationsResponse;
import com.amilesend.tmdb.client.model.configuration.GetTimezonesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.Responses.CONFIGURATION_DETAILS;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.Responses.COUNTRIES;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.Responses.JOBS;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.Responses.LANGUAGES;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.Responses.PRIMARY_TRANSLATIONS;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.Responses.TIMEZONES;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.newConfigurationDetails;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.newGetCountriesResponse;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.newGetJobsResponse;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.newGetLanguagesResponse;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.newGetPrimaryTranslationsResponse;
import static com.amilesend.tmdb.client.data.config.ConfigurationApiDataHelper.newGetTimezonesResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigurationApiFunctionalTest extends FunctionalTestBase {
    private ConfigurationApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getConfigurationApi();
    }

    @Test
    public void getConfigurationDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, CONFIGURATION_DETAILS);
        final GetConfigurationDetailsResponse expected = newConfigurationDetails();

        final GetConfigurationDetailsResponse actual = apiUnderTest.getConfigurationDetails();

        assertEquals(expected, actual);
    }

    @Test
    public void getCountries_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, COUNTRIES);
        final GetCountriesResponse expected = newGetCountriesResponse();

        final GetCountriesResponse actual = apiUnderTest.getCountries();

        assertEquals(expected, actual);
    }

    @Test
    public void getJobs_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, JOBS);
        final GetJobsResponse expected = newGetJobsResponse();

        final GetJobsResponse actual = apiUnderTest.getJobs();

        assertEquals(expected, actual);
    }

    @Test
    public void getLanguages_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, LANGUAGES);
        final GetLanguagesResponse expected = newGetLanguagesResponse();

        final GetLanguagesResponse actual = apiUnderTest.getLanguages();

        assertEquals(expected, actual);
    }

    @Test
    public void getPrimaryTranslations_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, PRIMARY_TRANSLATIONS);
        final GetPrimaryTranslationsResponse expected = newGetPrimaryTranslationsResponse();

        final GetPrimaryTranslationsResponse actual = apiUnderTest.getPrimaryTranslations();

        assertEquals(expected, actual);
    }

    @Test
    public void getTimezones_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, TIMEZONES);
        final GetTimezonesResponse expected = newGetTimezonesResponse();

        final GetTimezonesResponse actual = apiUnderTest.getTimezones();

        assertEquals(expected, actual);
    }
}
