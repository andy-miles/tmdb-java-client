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
import com.amilesend.tmdb.client.model.tv.episodes.AddRatingResponse;
import com.amilesend.tmdb.client.model.tv.episodes.DeleteRatingResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetEpisodeDetailsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetVideosResponse;
import com.amilesend.tmdb.client.model.tv.episodes.type.TvEpisodeTranslationData;
import com.amilesend.tmdb.client.model.type.Translation;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newCastCredit;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newCrewCredit;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newImage;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newVideo;
import static com.amilesend.tmdb.client.data.people.PeopleApiDataHelper.newChange;

@UtilityClass
public class TvEpisodesApiDataHelper {
    ////////////////////////////
    // GetEpisodeDetailsResponse
    ////////////////////////////

    public static GetEpisodeDetailsResponse newGetEpisodeDetailsResponse() {
        return GetEpisodeDetailsResponse.builder()
                .id(1)
                .name("Tv Episode Name Value")
                .airDate(LocalDate.of(2010, 11, 4))
                .crew(List.of(newCrewCredit(4), newCrewCredit(5), newCrewCredit(6)))
                .episodeNumber(4)
                .guestStars(List.of(newCastCredit(1), newCastCredit(2), newCastCredit(3)))
                .overview("Episode Overview Value")
                .productionCode("Production Code Value")
                .runtime(22)
                .seasonNumber(1)
                .stillPath("/epStill.png")
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

    ///////////////////////
    // GetChangesResponse
    ///////////////////////

    public static GetChangesResponse newGetChangesResponse() {
        return GetChangesResponse.builder()
                .changes(List.of(newChange(1), newChange(2), newChange(3)))
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
                .guestStars(List.of(newCastCredit(7), newCastCredit(8), newCastCredit(9)))
                .build();
    }

    ///////////////////////////
    // GetExternalIdsResponse
    ///////////////////////////

    public static GetExternalIdsResponse newGetExternalIdsResponse() {
        return GetExternalIdsResponse.builder()
                .id(1)
                .imdbId("IMDB ID Value")
                .tvdbId(Integer.valueOf(1000))
                .tvrageId(Integer.valueOf(2000))
                .wikidataId("Wikidata ID Value")
                .build();
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static GetImagesResponse newGetImagesResponse() {
        return GetImagesResponse.builder()
                .id(1)
                .stills(List.of(newImage(5), newImage(6), newImage(7)))
                .build();
    }

    ////////////////////////////
    // GetTranslationsResponse
    ////////////////////////////

    public static GetTranslationsResponse newGetTranslationsResponse() {
        return GetTranslationsResponse.builder()
                .id(1)
                .translations(List.of(newTranslation(1), newTranslation(2), newTranslation(3)))
                .build();
    }

    private static Translation<TvEpisodeTranslationData> newTranslation(final int index) {
        return Translation.<TvEpisodeTranslationData>builder()
                .countryCode(Locale.US.getCountry())
                .languageCode(Locale.US.getLanguage())
                .name("Translation name " + index)
                .englishName("English name " + index)
                .data(newTvEpisodeTranslationsData(index))
                .build();
    }

    private static TvEpisodeTranslationData newTvEpisodeTranslationsData(final int index) {
        return TvEpisodeTranslationData.builder()
                .name("Translated Episode Name " + index)
                .overview("Translated Overview " + index)
                .build();
    }

    //////////////////////
    // GetVideosResponse
    //////////////////////

    public static GetVideosResponse newGetVideosResponse() {
        return GetVideosResponse.builder()
                .id(1)
                .results(List.of(newVideo(1), newVideo(2), newVideo(3)))
                .build();
    }

    //////////////////////
    // AddRatingResponse
    //////////////////////

    public static AddRatingResponse newAddRatingResponse() {
        return AddRatingResponse.builder()
                .statusCode(200)
                .statusMessage("Added")
                .build();
    }

    /////////////////////////
    // DeleteRatingResponse
    /////////////////////////

    public static DeleteRatingResponse newDeleteRatingResponse() {
        return DeleteRatingResponse.builder()
                .statusCode(200)
                .statusMessage("Deleted")
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/TvEpisodesApi/";

        public static final SerializedResource GET_EPISODES_DETAILS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetEpisodeDetailsResponse.json");
        public static final SerializedResource GET_ACCOUNT_STATES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAccountStatesResponse.json");
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
        public static final SerializedResource GET_VIDEOS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetVideosResponse.json");
        public static final SerializedResource ADD_RATING_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "AddRatingResponse.json");
        public static final SerializedResource DELETE_RATING_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "DeleteRatingResponse.json");
    }
}
