/*
 * tmdb-java-client - A client to access the TMDB API
 * Copyright © 2024-2026 Andy Miles (andy.miles@amilesend.com)
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
import com.amilesend.client.parse.parser.ListParser;
import com.amilesend.tmdb.client.model.configuration.GetConfigurationDetailsResponse;
import com.amilesend.tmdb.client.model.configuration.GetCountriesResponse;
import com.amilesend.tmdb.client.model.configuration.GetJobsResponse;
import com.amilesend.tmdb.client.model.configuration.GetLanguagesResponse;
import com.amilesend.tmdb.client.model.configuration.GetPrimaryTranslationsResponse;
import com.amilesend.tmdb.client.model.configuration.GetTimezonesResponse;
import com.amilesend.tmdb.client.model.configuration.type.ConfigurationCountry;
import com.amilesend.tmdb.client.model.configuration.type.ConfigurationLanguage;
import com.amilesend.tmdb.client.model.configuration.type.ConfigurationTimezone;
import com.amilesend.tmdb.client.model.configuration.type.Job;
import com.amilesend.tmdb.client.parse.GsonFactory;
import okhttp3.Request;

import java.util.List;

/** TMDB Configuration API. */
public class ConfigurationApi extends ApiBase {
    private static final String API_PATH = "/configuration";

    /**
     * Creates a new {@code ConfigurationApi} object.
     *
     * @param connection the connection
     */
    public ConfigurationApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the configuration details that provides context for API integration.
     *
     * @return the configuration details
     * @see GetConfigurationDetailsResponse
     */
    public GetConfigurationDetailsResponse getConfigurationDetails() {
        return executeGet(API_PATH, GetConfigurationDetailsResponse.class);
    }

    /**
     * Gets the list of support countries.
     *
     * @return the list of countries
     * @see GetCountriesResponse
     */
    public GetCountriesResponse getCountries() {
        final String apiPath = new StringBuilder(API_PATH)
                .append("/countries")
                .toString();
        return new GetCountriesResponse(executeGetForList(apiPath, ConfigurationCountry.class));
    }

    /**
     * Gets the list of available jobs.
     *
     * @return the list of jobs
     * @see GetJobsResponse
     */
    public GetJobsResponse getJobs() {
        final String apiPath = new StringBuilder(API_PATH)
                .append("/jobs")
                .toString();
        return new GetJobsResponse(executeGetForList(apiPath, Job.class));
    }

    /**
     * Gets the list of supported languages.
     *
     * @return the list of languages
     * @see GetLanguagesResponse
     * @see ConfigurationLanguage
     */
    public GetLanguagesResponse getLanguages() {
        final String apiPath =  new StringBuilder(API_PATH)
                .append("/languages")
                .toString();
        return new GetLanguagesResponse(executeGetForList(apiPath, ConfigurationLanguage.class));
    }

    /**
     * Gets the list of supported translations.
     *
     * @return the list of translations (as locales, e.g., "en-US")
     * @see GetPrimaryTranslationsResponse
     */
    public GetPrimaryTranslationsResponse getPrimaryTranslations() {
        final String apiPath = new StringBuilder(API_PATH)
                .append("/primary_translations")
                .toString();
        return new GetPrimaryTranslationsResponse(executeGetForList(apiPath, String.class));
    }

    /**
     * Gets the list of supported timezones.
     *
     * @return the list of timezones
     * @see ConfigurationTimezone
     */
    public GetTimezonesResponse getTimezones() {
        final String apiPath = new StringBuilder(API_PATH)
                .append("/timezones")
                .toString();
        return new GetTimezonesResponse(executeGetForList(apiPath, ConfigurationTimezone.class));
    }

    private <T> List<T> executeGetForList(final String apiPath, final Class<T> responseType) {
        final Connection<GsonFactory> connection = getConnection();
        final String url = new StringBuilder(connection.getBaseUrl())
                .append(apiPath)
                .toString();
        final Request request = connection.newRequestBuilder().url(url).build();
        return connection.execute(request, new ListParser<>(responseType));
    }
}
