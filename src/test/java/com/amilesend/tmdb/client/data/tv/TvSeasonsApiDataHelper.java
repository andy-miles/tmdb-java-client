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
package com.amilesend.tmdb.client.data.tv;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.acount.type.Rated;
import com.amilesend.tmdb.client.model.tv.episodes.GetEpisodeDetailsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetAggregateCreditsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetSeasonDetailsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetWatchProvidersResponse;
import com.amilesend.tmdb.client.model.tv.seasons.type.AggregateCastCredit;
import com.amilesend.tmdb.client.model.tv.seasons.type.AggregateCrewCredit;
import com.amilesend.tmdb.client.model.tv.seasons.type.EpisodeChangeDescriptor;
import com.amilesend.tmdb.client.model.tv.seasons.type.Job;
import com.amilesend.tmdb.client.model.tv.seasons.type.Role;
import com.amilesend.tmdb.client.model.tv.seasons.type.TvSeasonTranslationData;
import com.amilesend.tmdb.client.model.type.Change;
import com.amilesend.tmdb.client.model.type.ChangeItem;
import com.amilesend.tmdb.client.model.type.Translation;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newCastCredit;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newCrewCredit;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newImage;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newWatchProviderResult;

@UtilityClass
public class TvSeasonsApiDataHelper {
    /////////////////////////////
    // GetSeasonDetailsResponse
    /////////////////////////////

    public static GetSeasonDetailsResponse newGetSeasonDetailsResponse() {
        return GetSeasonDetailsResponse.builder()
                .id(50)
                .name("Season 1")
                .seasonId("Season Id Value")
                .airDate(LocalDate.of(2020, 9, 12))
                .episodes(List.of(
                        newGetEpisodeDetailsResponse(1),
                        newGetEpisodeDetailsResponse(2),
                        newGetEpisodeDetailsResponse(3),
                        newGetEpisodeDetailsResponse(4),
                        newGetEpisodeDetailsResponse(5),
                        newGetEpisodeDetailsResponse(6)))
                .overview("Season 1 Overview Value")
                .posterPath("/Season1Poster.png")
                .seasonNumber(1)
                .voteAverage(0.62D)
                .build();
    }

    private static GetEpisodeDetailsResponse newGetEpisodeDetailsResponse(final int index) {
        return GetEpisodeDetailsResponse.builder()
                .id(10 + index)
                .name("Tv Episode " + index)
                .airDate(LocalDate.of(2010, 11, 4 + index))
                .crew(List.of(newCrewCredit(4), newCrewCredit(5), newCrewCredit(6)))
                .episodeNumber(index)
                .guestStars(List.of(newCastCredit(1), newCastCredit(2), newCastCredit(3)))
                .overview("Episode " + index + " Overview Value")
                .productionCode("Production Code Value " + index)
                .runtime(22)
                .seasonNumber(1)
                .stillPath("/ep" + index + "Still.png")
                .voteAverage(0.64D)
                .voteCount(1000)
                .build();
    }

    /////////////////////////////
    // GetAccountStatesResponse
    /////////////////////////////

    public static GetAccountStatesResponse newGetAccountStatesResponse() {
        return GetAccountStatesResponse.builder()
                .id(1)
                .rated(Rated.builder()
                        .value(80)
                        .build())
                .favorite(false)
                .watchlist(false)
                .build();
    }

    /////////////////////////////
    // GetAccountStatesResponse
    /////////////////////////////

    public static GetAggregateCreditsResponse newGetAggregateCreditsResponse() {
        return GetAggregateCreditsResponse.builder()
                .id(600)
                .cast(List.of(
                        newAggregateCastCredit(1),
                        newAggregateCastCredit(2),
                        newAggregateCastCredit(3)))
                .crew(List.of(
                        newAggregateCrewCredit(1),
                        newAggregateCrewCredit(2),
                        newAggregateCrewCredit(3)))
                .build();
    }

    public static AggregateCrewCredit newAggregateCrewCredit(final int index) {
        return AggregateCrewCredit.builder()
                .id(index)
                .name("Aggregate Crew Credit " + index)
                .adult(true)
                .originalName("Original Name " + index)
                .popularity(0.1D * index)
                .gender(1)
                .knownForDepartment("Department " + index)
                .profilePath("/profile" + index + ".png")
                .jobs(List.of(newJob(5 * index), newJob(5 * index + 1), newJob(5 * index + 2)))
                .totalEpisodeCount(12)
                .build();
    }

    private static Job newJob(final int index) {
        return Job.builder()
                .creditId("Job Credit ID " + index)
                .job("Job " + index)
                .episodeCount(12)
                .build();
    }

    public static AggregateCastCredit newAggregateCastCredit(final int index) {
        return AggregateCastCredit.builder()
                .id(index)
                .name("Aggregate Cast Credit " + index)
                .adult(true)
                .originalName("Original Name " + index)
                .popularity(0.12D * index)
                .gender(1)
                .knownForDepartment("Department " + index)
                .profilePath("/profile" + index + ".png")
                .roles(List.of(newRole(5 * index), newRole(5 * index + 1), newRole(5 * index + 2)))
                .totalEpisodeCount(12)
                .originalName("Original Name " + index)
                .order(index)
                .build();
    }

    private static Role newRole(final int index) {
        return Role.builder()
                .creditId("Role Credit ID " + index)
                .character("Character " + index)
                .episodeCount(12)
                .build();
    }

    ///////////////////////
    // GetChangesResponse
    ///////////////////////

    public static GetChangesResponse newGetChangesResponse() {
        return GetChangesResponse.builder()
                .changes(List.of(newChange(1), newChange(2), newChange(3)))
                .build();
    }

    private static Change newChange(final int index) {
        return Change.builder()
                .key("Key" + index)
                .items(List.of(
                        newChangeItem(index * 1),
                        newChangeItem(index * 2),
                        newChangeItem(index * 3)))
                .build();
    }

    private static ChangeItem newChangeItem(final int index) {
        return ChangeItem.<EpisodeChangeDescriptor>builder()
                .id("ChangeItemId" + index)
                .action("Action " + index)
                .time(LocalDateTime.of(2024, 6, 1, 12, 0, 0))
                .languageCode(Locale.US.getLanguage())
                .countryCode(Locale.US.getCountry())
                .value(newEpisodeChangeDescriptor(index))
                .build();
    }

    private static EpisodeChangeDescriptor newEpisodeChangeDescriptor(final int index) {
        return EpisodeChangeDescriptor.builder()
                .episodeId(index * 105)
                .episodeNumber(index)
                .build();
    }

    ///////////////////////
    // GetCreditsResponse
    ///////////////////////

    public static GetCreditsResponse newGetCreditsResponse() {
        return GetCreditsResponse.builder()
                .id(1)
                .cast(List.of(newCastCredit(1), newCastCredit(2), newCastCredit(3)))
                .crew(List.of(newCrewCredit(4), newCrewCredit(5), newCrewCredit(6)))
                .build();
    }

    ///////////////////////////
    // GetExternalIdsResponse
    ///////////////////////////

    public static GetExternalIdsResponse newGetExternalIdsResponse() {
        return GetExternalIdsResponse.builder()
                .id(400)
                .freebaseId("FreeBaseID Value")
                .freebaseMid("FreeBaseMID Value")
                .tvdbId(10)
                .tvrageId(20)
                .wikidataId("Wikidata ID Value")
                .build();
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static GetImagesResponse newGetImagesResponse() {
        return GetImagesResponse.builder()
                .id(50)
                .posters(List.of(newImage(1), newImage(2), newImage(3)))
                .build();
    }

    ////////////////////////////
    // GetTranslationsResponse
    ////////////////////////////

    public static GetTranslationsResponse newGetTranslationsResponse() {
        return GetTranslationsResponse.builder()
                .id(400)
                .translations(List.of(newTranslation(1), newTranslation(2), newTranslation(3)))
                .build();
    }

    private static Translation<TvSeasonTranslationData> newTranslation(final int index) {
        return Translation.<TvSeasonTranslationData>builder()
                .countryCode("Country Code " + index)
                .languageCode("Language Code " + index)
                .name("Name " + index)
                .englishName("English Name " + index)
                .data(TvSeasonTranslationData.builder()
                        .name("Name " + index)
                        .overview("Overview " + index)
                        .build())
                .build();
    }

    //////////////////////////////
    // GetWatchProvidersResponse
    //////////////////////////////

    public static GetWatchProvidersResponse newGetWatchProvidersResponse() {
        return GetWatchProvidersResponse.builder()
                .id(1)
                .results(Map.of(
                        Locale.US.getCountry(), newWatchProviderResult(1),
                        Locale.GERMANY.getCountry(), newWatchProviderResult(2)))
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/TvSeasonsApi/";

        public static final SerializedResource GET_SEASON_DETAILS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetSeasonDetailsResponse.json");
        public static final SerializedResource GET_ACCOUNT_STATES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAccountStatesResponse.json");
        public static final SerializedResource GET_AGGREGATE_CREDITS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAggregateCreditsResponse.json");
        public static final SerializedResource GET_CHANGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetChangesResponse.json");
        public static final SerializedResource GET_CREDITS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetCreditsResponse.json");
        public static final SerializedResource GET_EXTERNAL_IDS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetExternalIdsResponse.json");
        public static final SerializedResource GET_IMAGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetImagesResponse.json");
        public static final SerializedResource GET_TRANSLATIONS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTranslationsResponse.json");
        public static final SerializedResource GET_WATCH_PROVIDERS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetWatchProvidersResponse.json");
    }
}
