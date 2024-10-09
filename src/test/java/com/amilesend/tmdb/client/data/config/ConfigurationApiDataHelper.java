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
package com.amilesend.tmdb.client.data.config;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.configuration.GetConfigurationDetailsResponse;
import com.amilesend.tmdb.client.model.configuration.GetCountriesResponse;
import com.amilesend.tmdb.client.model.configuration.GetJobsResponse;
import com.amilesend.tmdb.client.model.configuration.GetLanguagesResponse;
import com.amilesend.tmdb.client.model.configuration.GetPrimaryTranslationsResponse;
import com.amilesend.tmdb.client.model.configuration.GetTimezonesResponse;
import com.amilesend.tmdb.client.model.configuration.type.ConfigurationCountry;
import com.amilesend.tmdb.client.model.configuration.type.ConfigurationImages;
import com.amilesend.tmdb.client.model.configuration.type.ConfigurationLanguage;
import com.amilesend.tmdb.client.model.configuration.type.ConfigurationTimezone;
import com.amilesend.tmdb.client.model.configuration.type.Job;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class ConfigurationApiDataHelper {

    /////////////////////////
    // ConfigurationDetails
    /////////////////////////

    public static GetConfigurationDetailsResponse newConfigurationDetails() {
        return GetConfigurationDetailsResponse.builder()
                .changeKeys(newStringList("change key ", 12))
                .images(newConfigurationImage())
                .build();
    }

    private static ConfigurationImages newConfigurationImage() {
        return ConfigurationImages.builder()
                .baseUrl("http://somebaseurl.com")
                .secureBaseUrl("https://somebaseurl.com")
                .backdropSizes(newStringList("backdrop ", 5))
                .logoSizes(newStringList("logo ", 4))
                .posterSizes(newStringList("poster ", 3))
                .profileSizes(newStringList("profile ", 2))
                .stillSizes(newStringList("still ", 8))
                .build();
    }

    /////////////////////////
    // ConfigurationCountry
    /////////////////////////

    public static GetCountriesResponse newGetCountriesResponse() {
        return new GetCountriesResponse(newConfigurationCountryList());
    }

    private static List<ConfigurationCountry> newConfigurationCountryList() {
        final List<ConfigurationCountry> countries = new ArrayList<>(10);
        for (int i = 1; i <= 10; ++i) {
            countries.add(newConfigurationCountry(i));
        }

        return countries;
    }

    private static ConfigurationCountry newConfigurationCountry(final int index) {
        return ConfigurationCountry.builder()
                .countryCode(Locale.US.getCountry())
                .englishName("Country " + index)
                .nativeName("Country " + index)
                .build();
    }

    ////////
    // Job
    ////////

    public static GetJobsResponse newGetJobsResponse() {
        return new GetJobsResponse(newJobList());
    }

    private static List<Job> newJobList() {
        final List<Job> jobs = new ArrayList<>(10);
        for (int i = 1; i <= 10; ++i) {
            jobs.add(newJob(i));
        }

        return jobs;
    }

    private static Job newJob(final int index) {
        return Job.builder()
                .department("Department " + index)
                .jobs(newStringList("Job ", 20))
                .build();
    }

    //////////////////////////
    // ConfigurationLanguage
    //////////////////////////

    public static GetLanguagesResponse newGetLanguagesResponse() {
        return new GetLanguagesResponse(newConfigurationLanguageList());
    }

    private static List<ConfigurationLanguage> newConfigurationLanguageList() {
        final List<ConfigurationLanguage> languages = new ArrayList<>(20);
        for (int i = 1; i <= 20; ++i) {
            languages.add(newConfigurationLanguage(i));
        }

        return languages;
    }

    private static ConfigurationLanguage newConfigurationLanguage(final int index) {
        return ConfigurationLanguage.builder()
                .languageCode(Locale.US.getLanguage())
                .name("Language " + index)
                .englishName("Language " + index)
                .build();
    }

    ////////////////////////
    // PrimaryTranslations
    ////////////////////////

    public static GetPrimaryTranslationsResponse newGetPrimaryTranslationsResponse() {
        return new GetPrimaryTranslationsResponse(newPrimaryTranslations());
    }

    private static List<String> newPrimaryTranslations() {
        final List<String> translations = new ArrayList<>(100);
        for (int i = 1; i <= 100; ++i) {
            translations.add("Translation " + i);
        }

        return translations;
    }

    //////////////////////////
    // ConfigurationTimezone
    //////////////////////////

    public static GetTimezonesResponse newGetTimezonesResponse() {
        return new GetTimezonesResponse(newConfigurationTimezoneList());
    }

    private static List<ConfigurationTimezone> newConfigurationTimezoneList() {
        final List<ConfigurationTimezone> timezones = new ArrayList<>(50);
        for (int i = 1; i <= 50; ++i) {
            timezones.add(newConfigurationTimezone(i));
        }

        return timezones;
    }

    private static ConfigurationTimezone newConfigurationTimezone(final int index) {
        return ConfigurationTimezone.builder()
                .countryCode("CountryCode " + index)
                .zones(newStringList("Zone ", 3))
                .build();
    }


    private static List<String> newStringList(final String prefix, final int size) {
        final List<String> list = new ArrayList<>(size);
        for (int i = 1; i <= size; ++i) {
            list.add(prefix + i);
        }

        return list;
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/ConfigurationApi/";

        public static final SerializedResource CONFIGURATION_DETAILS =
                new SerializedResource(RESOURCE_FOLDER + "ConfigurationDetails.json");
        public static final SerializedResource COUNTRIES =
                new SerializedResource(RESOURCE_FOLDER + "Countries.json");
        public static final SerializedResource JOBS =
                new SerializedResource(RESOURCE_FOLDER + "Jobs.json");
        public static final SerializedResource LANGUAGES =
                new SerializedResource(RESOURCE_FOLDER + "Languages.json");
        public static final SerializedResource PRIMARY_TRANSLATIONS =
                new SerializedResource(RESOURCE_FOLDER + "PrimaryTranslations.json");
        public static final SerializedResource TIMEZONES =
                new SerializedResource(RESOURCE_FOLDER + "Timezones.json");
    }
}
