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
package com.amilesend.tmdb.client.data.people;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.people.GetChangesResponse;
import com.amilesend.tmdb.client.model.people.GetCombinedCreditsResponse;
import com.amilesend.tmdb.client.model.people.GetExternalIDsResponse;
import com.amilesend.tmdb.client.model.people.GetImagesResponse;
import com.amilesend.tmdb.client.model.people.GetLatestResponse;
import com.amilesend.tmdb.client.model.people.GetMovieCreditsResponse;
import com.amilesend.tmdb.client.model.people.GetPersonDetailsResponse;
import com.amilesend.tmdb.client.model.people.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.people.GetTvCreditsResponse;
import com.amilesend.tmdb.client.model.people.type.CombinedCastCredit;
import com.amilesend.tmdb.client.model.people.type.CombinedCrewCredit;
import com.amilesend.tmdb.client.model.people.type.MovieCastCredit;
import com.amilesend.tmdb.client.model.people.type.MovieCrewCredit;
import com.amilesend.tmdb.client.model.people.type.PersonTranslationsData;
import com.amilesend.tmdb.client.model.people.type.TvCastCredit;
import com.amilesend.tmdb.client.model.people.type.TvCrewCredit;
import com.amilesend.tmdb.client.model.type.Change;
import com.amilesend.tmdb.client.model.type.ChangeItem;
import com.amilesend.tmdb.client.model.type.Translation;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newImage;

@UtilityClass
public class PeopleApiDataHelper {
    /////////////////////////////
    // GetPersonDetailsResponse
    /////////////////////////////

    public static GetPersonDetailsResponse newGetPersonDetailsResponse() {
        return GetPersonDetailsResponse.builder()
                .id(1)
                .name("Person Name Value")
                .adult(true)
                .alsoKnownAs(List.of("Alias Value"))
                .biography("Biography Value")
                .deathDate(LocalDate.of(2023, 8 , 13))
                .gender(1)
                .homepage("https://homepage")
                .imdbId("IMDB ID value")
                .knownForDepartment("actor")
                .placeOfBirth("Dallas TX, USA")
                .popularity(0.73D)
                .profilePath("/profile.jpg")
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

    public static Change newChange(final int index) {
        return Change.builder()
                .key("Key" + index)
                .items(List.of(
                        newChangeItem(index * 1),
                        newChangeItem(index * 2),
                        newChangeItem(index * 3)))
                .build();
    }

    private static ChangeItem newChangeItem(final int index) {
        return ChangeItem.<String>builder()
                .id("ChangeItemId" + index)
                .action("Action " + index)
                .time(LocalDateTime.of(2024, 6, 1, 12, 0, 0))
                .languageCode(Locale.US.getLanguage())
                .countryCode(Locale.US.getCountry())
                .value("Change Value " + index)
                .build();
    }

    ///////////////////////////////
    // GetCombinedCreditsResponse
    ///////////////////////////////

    public static GetCombinedCreditsResponse newGetCombinedCreditsResponse() {
        return GetCombinedCreditsResponse.builder()
                .id(1)
                .cast(List.of(
                        newCombinedCastCredit(1),
                        newCombinedCastCredit(2),
                        newCombinedCastCredit(3)))
                .crew(List.of(
                        newCombinedCrewCredit(1),
                        newCombinedCrewCredit(2),
                        newCombinedCrewCredit(3)))
                .build();
    }

    private static CombinedCastCredit newCombinedCastCredit(final int index) {
        return CombinedCastCredit.builder()
                .id(index)
                .adult(true)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(5, 9, 14))
                .originalLanguage("Original Language")
                .originalTitle("Original Title " + index)
                .overview("Overview " + index)
                .popularity(index * 0.4D)
                .posterPath("/poster" + index + ".jpg")
                .releaseDate(LocalDate.of(2020, 4, 5))
                .title("Title " + index)
                .video(false)
                .voteAverage(index * 0.6D)
                .voteCount(100)
                .character("Character " + index)
                .order(index)
                .mediaType("movie")
                .build();
    }

    private static CombinedCrewCredit newCombinedCrewCredit(final int index) {
        return CombinedCrewCredit.builder()
                .id(index)
                .adult(false)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(5, 9, 14))
                .originalLanguage("Original Language")
                .originalTitle("Original Title " + index)
                .overview("Overview " + index)
                .popularity(index * 0.3D)
                .posterPath("/poster" + index + ".jpg")
                .releaseDate(LocalDate.of(2020, 4, 5))
                .title("Title " + index)
                .video(false)
                .voteAverage(index * 0.7D)
                .voteCount(200)
                .creditId("CreditId" + index)
                .department("Department " + index)
                .job("Job " + index)
                .mediaType("movie")
                .build();
    }

    ///////////////////////////
    // GetExternalIDsResponse
    ///////////////////////////

    public static GetExternalIDsResponse newGetExternalIDsResponse() {
        return GetExternalIDsResponse.builder()
                .id(1)
                .imdbId("IMDB ID")
                .tvrageId(100)
                .wikidataId("WikiData ID")
                .facebookId("Facebook ID")
                .instagramId("Instagram ID")
                .tiktokId("TikTok ID")
                .twitterId("Twitter ID")
                .youtubeId("YouTube ID")
                .build();
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static GetImagesResponse newGetImagesResponse() {
        return GetImagesResponse.builder()
                .id(1)
                .profiles(List.of(newImage(1), newImage(2), newImage(3)))
                .build();
    }

    //////////////////////
    // GetLatestResponse
    //////////////////////

    public static GetLatestResponse newGetLatestResponse() {
        return GetLatestResponse.builder()
                .id(2)
                .name("Person Name Value")
                .adult(true)
                .alsoKnownAs(List.of("Alias Value"))
                .biography("Biography Value")
                .deathDate(LocalDate.of(2024, 1 , 7))
                .gender(2)
                .homepage("https://homepage")
                .imdbId("IMDB ID value")
                .knownForDepartment("actor")
                .placeOfBirth("New York, NY USA")
                .popularity(0.82D)
                .profilePath("/profile.jpg")
                .build();
    }

    ////////////////////////////
    // GetMovieCreditsResponse
    ////////////////////////////

    public static GetMovieCreditsResponse newGetMovieCreditsResponse() {
        return GetMovieCreditsResponse.builder()
                .id(1)
                .cast(List.of(newMovieCastCredit(1), newMovieCastCredit(2), newMovieCastCredit(3)))
                .crew(List.of(newMovieCrewCredit(4), newMovieCrewCredit(5), newMovieCrewCredit(6)))
                .build();
    }

    private static MovieCastCredit newMovieCastCredit(final int index) {
        return MovieCastCredit.builder()
                .id(index)
                .adult(true)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(index * 2 + 1, index * 3 + 1, index * 4 + 1))
                .originalLanguage("Original Language " + index)
                .originalTitle("Original Title " + index)
                .overview("Overview " + index)
                .popularity(0.4D * index)
                .posterPath("/poster" + index + ".jpg")
                .releaseDate(LocalDate.of(2010, 4, 7))
                .title("Title " + index)
                .video(false)
                .voteAverage(0.6D * index)
                .voteCount(103 * index)
                .character("Character " + index)
                .creditId("CreditId" + index)
                .order(index)
                .build();
    }

    private static MovieCrewCredit newMovieCrewCredit(final int index) {
        return MovieCrewCredit.builder()
                .id(index)
                .adult(true)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(index * 3 + 3, index * 4 + 3, index * 5 + 3))
                .originalLanguage("Original Language " + index)
                .originalTitle("Original Title " + index)
                .overview("Overview " + index)
                .popularity(0.32D * index)
                .posterPath("/poster" + index + ".jpg")
                .releaseDate(LocalDate.of(2008, 6, 2))
                .title("Title " + index)
                .video(false)
                .voteAverage(0.48D * index)
                .voteCount(94 * index)
                .creditId("CreditId" + index)
                .department("Job Type " + index)
                .job("Job " + index)
                .build();
    }

    /////////////////////////
    // GetTvCreditsResponse
    /////////////////////////

    public static GetTvCreditsResponse newGetTvCreditsResponse() {
        return GetTvCreditsResponse.builder()
                .id(1)
                .cast(List.of(newTvCastCredit(1), newTvCastCredit(2), newTvCastCredit(3)))
                .crew(List.of(newTvCrewCredit(1), newTvCrewCredit(2), newTvCrewCredit(3)))
                .build();
    }

    private static TvCastCredit newTvCastCredit(final int index) {
        return TvCastCredit.builder()
                .id(index)
                .name("TvCastCredit " + index)
                .adult(true)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(index * 2 + 1, index * 3 + 1, index * 4 + 1))
                .originCountry(List.of(Locale.US.getCountry()))
                .originalLanguage("Original Language " + index)
                .originalName("Original Name " + index)
                .overview("Overview " + index)
                .popularity(0.4D * index)
                .posterPath("/poster" + index + ".jpg")
                .firstAirDate(LocalDate.of(2010, 4, 7))
                .voteAverage(0.6D * index)
                .voteCount(103 * index)
                .character("Character " + index)
                .creditId("CreditId" + index)
                .episodeCount(12)
                .build();
    }

    private static TvCrewCredit newTvCrewCredit(final int index) {
        return TvCrewCredit.builder()
                .id(index)
                .name("TvCastCredit " + index)
                .adult(true)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(index * 3 + 3, index * 4 + 3, index * 5 + 3))
                .originCountry(List.of(Locale.US.getCountry()))
                .originalLanguage("Original Language " + index)
                .originalName("Original Name " + index)
                .overview("Overview " + index)
                .popularity(0.4D * index)
                .posterPath("/poster" + index + ".jpg")
                .firstAirDate(LocalDate.of(2010, 4, 7))
                .voteAverage(0.6D * index)
                .voteCount(103 * index)
                .creditId("CreditId" + index)
                .department("Department " + index)
                .episodeCount(12)
                .job("Job " + index)
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

    private static Translation<PersonTranslationsData> newTranslation(final int index) {
        return Translation.<PersonTranslationsData>builder()
                .countryCode(Locale.US.getCountry())
                .languageCode(Locale.US.getLanguage())
                .name("Translation name " + index)
                .englishName("English name " + index)
                .data(newPersonTranslationsData(index))
                .build();
    }

    private static PersonTranslationsData newPersonTranslationsData(final int index) {
        return PersonTranslationsData.builder()
                .biography("Biography " + index)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/PeopleApi/";

        public static final SerializedResource GET_PERSON_DETAILS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetPersonDetailsResponse.json");
        public static final SerializedResource GET_CHANGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetChangesResponse.json");
        public static final SerializedResource GET_COMBINED_CREDITS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetCombinedCreditsResponse.json");
        public static final SerializedResource GET_EXTERNAL_IDS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetExternalIDsResponse.json");
        public static final SerializedResource GET_IMAGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetImagesResponse.json");
        public static final SerializedResource GET_LATEST_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetLatestResponse.json");
        public static final SerializedResource GET_MOVIE_CREDITS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetMovieCreditsResponse.json");
        public static final SerializedResource GET_TV_CREDITS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTvCreditsResponse.json");
        public static final SerializedResource GET_TRANSLATIONS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTranslationsResponse.json");
    }
}
