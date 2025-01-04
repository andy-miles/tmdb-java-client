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
package com.amilesend.tmdb.client.data.movie;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.acount.type.Rated;
import com.amilesend.tmdb.client.model.movie.AddRatingResponse;
import com.amilesend.tmdb.client.model.movie.DeleteRatingResponse;
import com.amilesend.tmdb.client.model.movie.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.movie.GetAlternativeTitlesResponse;
import com.amilesend.tmdb.client.model.movie.GetChangesResponse;
import com.amilesend.tmdb.client.model.movie.GetCreditsResponse;
import com.amilesend.tmdb.client.model.movie.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.movie.GetImagesResponse;
import com.amilesend.tmdb.client.model.movie.GetKeywordsResponse;
import com.amilesend.tmdb.client.model.movie.GetLatestResponse;
import com.amilesend.tmdb.client.model.movie.GetListsResponse;
import com.amilesend.tmdb.client.model.movie.GetMovieDetailsResponse;
import com.amilesend.tmdb.client.model.movie.GetRecommendationsResponse;
import com.amilesend.tmdb.client.model.movie.GetReleaseDatesResponse;
import com.amilesend.tmdb.client.model.movie.GetReviewsResponse;
import com.amilesend.tmdb.client.model.movie.GetSimilarResponse;
import com.amilesend.tmdb.client.model.movie.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.movie.GetVideosResponse;
import com.amilesend.tmdb.client.model.movie.GetWatchProvidersResponse;
import com.amilesend.tmdb.client.model.movie.type.Movie;
import com.amilesend.tmdb.client.model.movie.type.MovieCollection;
import com.amilesend.tmdb.client.model.movie.type.MovieTranslationData;
import com.amilesend.tmdb.client.model.movie.type.Poster;
import com.amilesend.tmdb.client.model.movie.type.ReleaseDate;
import com.amilesend.tmdb.client.model.movie.type.ReleaseDateCountry;
import com.amilesend.tmdb.client.model.type.AlternativeTitle;
import com.amilesend.tmdb.client.model.type.AuthorDetails;
import com.amilesend.tmdb.client.model.type.CastCredit;
import com.amilesend.tmdb.client.model.type.Change;
import com.amilesend.tmdb.client.model.type.ChangeItem;
import com.amilesend.tmdb.client.model.type.CrewCredit;
import com.amilesend.tmdb.client.model.type.Image;
import com.amilesend.tmdb.client.model.type.Keyword;
import com.amilesend.tmdb.client.model.type.Language;
import com.amilesend.tmdb.client.model.type.MediaListInfo;
import com.amilesend.tmdb.client.model.type.ProductionCompany;
import com.amilesend.tmdb.client.model.type.ProductionCountry;
import com.amilesend.tmdb.client.model.type.Provider;
import com.amilesend.tmdb.client.model.type.Review;
import com.amilesend.tmdb.client.model.type.Translation;
import com.amilesend.tmdb.client.model.type.Video;
import com.amilesend.tmdb.client.model.type.WatchProviderResult;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.amilesend.tmdb.client.data.genre.GenresApiDataHelper.newGenreList;

@UtilityClass
public class MoviesApiDataHelper {
    ////////////////////////////
    // GetMovieDetailsResponse
    ////////////////////////////

    public static GetMovieDetailsResponse newGetMovieDetailsResponse() {
        return GetMovieDetailsResponse.builder()
                .id(1)
                .posterPath("/poster.jpg")
                .adult(false)
                .releaseDate(LocalDate.of(2024, 5, 15))
                .originalLanguage(Locale.US.getLanguage())
                .overview("Overview for movie")
                .backdropPath("/backdrop.jpg")
                .popularity(0.6D)
                .title("Movie Title")
                .voteAverage(0.55D)
                .video(false)
                .voteCount(100)
                .originalTitle("Original Movie Title")
                .belongsToCollection(MovieCollection.builder()
                        .id(3000)
                        .name("MovieCollectionName")
                        .posterPath("/movieCollectionPoster.jpg")
                        .backdropPath("/movieCollectionBackdrop.jpg")
                        .build())
                .budget(10000000)
                .genres(newGenreList(3))
                .homepage("https://someMovieHomepage.com")
                .imdbId("imdbIdValue")
                .productionCompanies(List.of(newProductionCompany(1), newProductionCompany(2)))
                .productionCountries(List.of(newProductionCountry(1), newProductionCountry(2)))
                .revenue(20000000)
                .runtime(120)
                .spokenLanguages(List.of(newLanguage()))
                .status("statusValue")
                .tagline("taglineValue")
                .build();
    }

    public static Language newLanguage() {
        return Language.builder()
                .englishName("English")
                .languageCode(Locale.US.getLanguage())
                .name("English")
                .build();
    }

    public static ProductionCompany newProductionCompany(final int index) {
        return ProductionCompany.builder()
                .id(index)
                .name("Production Company" + index)
                .logoPath("/productionCompany" + index + ".jpg")
                .originCountry(Locale.US.getCountry())
                .build();
    }

    public static ProductionCountry newProductionCountry(final int index) {
        return ProductionCountry.builder()
                .countryCode(Locale.US.getCountry())
                .name("ProductionCountry" + index)
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

    /////////////////////////////////
    // GetAlternativeTitlesResponse
    /////////////////////////////////

    public static GetAlternativeTitlesResponse newGetAlternativeTitlesResponse() {
        return GetAlternativeTitlesResponse.builder()
                .id(300)
                .titles(List.of(newAlternativeTitle(1), newAlternativeTitle(2), newAlternativeTitle(3)))
                .build();
    }

    public static AlternativeTitle newAlternativeTitle(final int index) {
        return AlternativeTitle.builder()
                .countryCode(Locale.US.getCountry())
                .title("Alternate Title " + index)
                .type("Alternate Title Type " + index)
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
        return ChangeItem.builder()
                .id("ChangeItemId" + index)
                .action("Action " + index)
                .time(LocalDateTime.of(2024, 6, 1, 12, 0, 0))
                .languageCode(Locale.US.getLanguage())
                .countryCode(Locale.US.getCountry())
                .value(Poster.builder().filePath("/Poster Value " + index).build())
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

    public static CastCredit newCastCredit(final int index) {
        return CastCredit.builder()
                .id(index)
                .name("CastCredit " + index)
                .adult(false)
                .originalName("CastName " + index)
                .popularity(0.80D)
                .gender(1)
                .knownForDepartment("Actor")
                .profilePath("ProfilePath" + index)
                .castId(index)
                .character("Character" + index)
                .creditId("CreditId" + index)
                .order(1)
                .build();
    }

    public static CrewCredit newCrewCredit(final int index) {
        return CrewCredit.builder()
                .id(index)
                .name("CrewCredit " + index)
                .adult(false)
                .originalName("CrewCredit " + index)
                .popularity(0.80D)
                .gender(1)
                .knownForDepartment("producer")
                .profilePath("ProfilePath" + index)
                .creditId("CreditId" + index)
                .department("Department" + index)
                .job("Job" + index)
                .build();
    }

    ///////////////////////////
    // GetExternalIdsResponse
    ///////////////////////////

    public static GetExternalIdsResponse newGetExternalIdsResponse() {
        return GetExternalIdsResponse.builder()
                .id(1)
                .imdbId("imdbIdValue")
                .facebookId("facebookIdValue")
                .wikidataId("wikidataIdValue")
                .instagramId("instagramIdValue")
                .twitterId("twitterIdValue")
                .build();
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static GetImagesResponse newGetImagesResponse() {
        return GetImagesResponse.builder()
                .id(1)
                .backdrops(List.of(newImage(1), newImage(2), newImage(3)))
                .logos(List.of(newImage(4), newImage(5), newImage(6)))
                .posters(List.of(newImage(7), newImage(8), newImage(9)))
                .build();
    }

    public static Image newImage(final int index) {
        return Image.builder()
                .aspectRatio(1.33D)
                .height(960)
                .languageCode(Locale.US.getLanguage())
                .filePath("/image" + index + ".jpg")
                .voteAverage(0.63D)
                .voteCount(150)
                .width(1280)
                .build();
    }

    ////////////////////////
    // GetKeywordsResponse
    ////////////////////////

    public static GetKeywordsResponse newGetKeywordsResponse() {
        return GetKeywordsResponse.builder()
                .id(1)
                .keywords(List.of(newKeyword(1), newKeyword(2), newKeyword(3)))
                .build();
    }

    public static Keyword newKeyword(final int index) {
        return Keyword.builder()
                .id(index)
                .name("Keyword " + index)
                .build();
    }

    //////////////////////
    // GetLatestResponse
    //////////////////////

    public static GetLatestResponse newGetLatestResponse() {
        return GetLatestResponse.builder()
                .id(1)
                .posterPath("/poster.jpg")
                .adult(false)
                .releaseDate(LocalDate.of(2024, 5, 15))
                .originalLanguage(Locale.US.getLanguage())
                .overview("Overview for movie")
                .backdropPath("/backdrop.jpg")
                .popularity(0.6D)
                .title("Movie Title")
                .voteAverage(0.55D)
                .video(false)
                .voteCount(100)
                .originalTitle("Original Movie Title")
                .belongsToCollection(MovieCollection.builder()
                        .id(3000)
                        .name("MovieCollectionName")
                        .posterPath("/movieCollectionPoster.jpg")
                        .backdropPath("/movieCollectionBackdrop.jpg")
                        .build())
                .budget(10000000)
                .genres(newGenreList(3))
                .homepage("https://someMovieHomepage.com")
                .imdbId("imdbIdValue")
                .productionCompanies(List.of(newProductionCompany(1), newProductionCompany(2)))
                .productionCountries(List.of(newProductionCountry(1), newProductionCountry(2)))
                .revenue(20000000)
                .runtime(120)
                .spokenLanguages(List.of(Language.builder()
                        .englishName("English")
                        .languageCode(Locale.US.getLanguage())
                        .name("English")
                        .build()))
                .status("statusValue")
                .tagline("taglineValue")
                .build();
    }

    /////////////////////
    // GetListsResponse
    /////////////////////

    public static GetListsResponse newGetListsResponse() {
        return GetListsResponse.builder()
                .id(1)
                .results(List.of(newMediaListInfo(1), newMediaListInfo(2), newMediaListInfo(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    public static MediaListInfo newMediaListInfo(final int index) {
        return MediaListInfo.builder()
                .id(index)
                .name("MediaList " + index)
                .description("Description " + index)
                .favoriteCount(40)
                .itemCount(100)
                .languageCode(Locale.US.getLanguage())
                .listType("List Type " + index)
                .posterPath("/poster" + index + ".jpg")
                .build();
    }

    ///////////////////////////////
    // GetRecommendationsResponse
    ///////////////////////////////

    public static GetRecommendationsResponse newGetRecommendationsResponse() {
        return GetRecommendationsResponse.builder()
                .page(1)
                .results(List.of(newMovie(1), newMovie(2), newMovie(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    private static Movie newMovie(final int index) {
        return Movie.builder()
                .adult(false)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(1, 2, 3))
                .id(index)
                .originalLanguage(Locale.US.getLanguage())
                .originalTitle("Original Movie Title " + index)
                .overview("Overview for movie " + index)
                .popularity(0.6D)
                .posterPath("/poster" + index + ".jpg")
                .releaseDate(LocalDate.of(2024, 5, 15))
                .title("Movie Title " + index)
                .video(false)
                .voteAverage(0.55D)
                .voteCount(100)
                .build();
    }

    ////////////////////////////
    // GetReleaseDatesResponse
    ////////////////////////////

    public static GetReleaseDatesResponse newGetReleaseDatesResponse() {
        return GetReleaseDatesResponse.builder()
                .results(List.of(
                        newReleaseDateCountry(1),
                        newReleaseDateCountry(2),
                        newReleaseDateCountry(3)))
                .build();
    }

    private static ReleaseDateCountry newReleaseDateCountry(final int index) {
        return ReleaseDateCountry.builder()
                .countryCode(Locale.US.getCountry())
                .releaseDates(List.of(
                        newReleaseDate(index * 10 + 1),
                        newReleaseDate(index * 10 + 2),
                        newReleaseDate(index * 10 + 3)))
                .build();
    }

    private static ReleaseDate newReleaseDate(final int index) {
        return ReleaseDate.builder()
                .certification("Certification " + index)
                .languageCode(Locale.US.getLanguage())
                .note("ReleaseDate Note " + index)
                .releaseDate(LocalDateTime.of(2020, 7, 1, 12, 00, 00))
                .type(1)
                .build();
    }

    ///////////////////////
    // GetReviewsResponse
    ///////////////////////

    public static GetReviewsResponse newGetReviewsResponse() {
        return GetReviewsResponse.builder()
                .page(1)
                .results(List.of(newReview(1), newReview(2), newReview(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    public static Review newReview(final int index) {
        return Review.builder()
                .id("Review_" + index)
                .author("Author " + index)
                .authorDetails(AuthorDetails.builder()
                        .name("Author Name " + index)
                        .username("username" + index)
                        .avatarPath("/username" + index + ".jpg")
                        .rating(10 * index)
                        .build())
                .content("Review Content " + index)
                .createdAt(LocalDateTime.of(2023, 9, 12, 14, 32))
                .updatedAt(LocalDateTime.of(2023, 9, 12, 14, 32))
                .url("https://somereview.com/" + index)
                .build();
    }

    ///////////////////////
    // GetSimilarResponse
    ///////////////////////

    public static GetSimilarResponse newGetSimilarResponse() {
        return GetSimilarResponse.builder()
                .page(1)
                .results(List.of(newMovie(1), newMovie(2), newMovie(3)))
                .totalPages(1)
                .totalResults(3)
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

    private static Translation<MovieTranslationData> newTranslation(final int index) {
        return Translation.<MovieTranslationData>builder()
                .countryCode(Locale.US.getCountry())
                .languageCode(Locale.US.getLanguage())
                .name("Translation name " + index)
                .englishName("English name " + index)
                .data(newMovieTranslationData(index))
                .build();
    }

    private static MovieTranslationData newMovieTranslationData(final int index) {
        return MovieTranslationData.builder()
                .homepage("https://somewebpage.com/" + index)
                .overview("Translation Overview " + index)
                .runtime(83)
                .tagline("Tagline " + index)
                .title("Title " + index)
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

    public static Video newVideo(final int index) {
        return Video.builder()
                .id("Video_" + index)
                .languageCode(Locale.US.getLanguage())
                .countryCode(Locale.US.getCountry())
                .name("Video Name " + index)
                .key("Key_" + index)
                .site("Site " + index)
                .size(1080)
                .type("Type " + index)
                .official(true)
                .publishedAt(LocalDateTime.of(2020, 2, 22, 8, 0, 0))
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

    public static WatchProviderResult newWatchProviderResult(final int index) {
        return WatchProviderResult.builder()
                .link("https://someurl/provider/" + index)
                .flatrate(List.of(newProvider(1), newProvider(2), newProvider(3)))
                .rent(List.of(newProvider(1), newProvider(2), newProvider(3)))
                .buy(List.of(newProvider(1), newProvider(2), newProvider(3)))
                .build();
    }

    private static Provider newProvider(final int index) {
        return Provider.builder()
                .displayPriority(1 * 5)
                .logoPath("/logo" + index + ".jpg")
                .providerId(index)
                .providerName("Provider " + index)
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
        private static final String RESOURCE_FOLDER = "/MoviesApi/";

        public static final SerializedResource GET_MOVIE_DETAILS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetMovieDetailsResponse.json");
        public static final SerializedResource GET_ACCOUNT_STATES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAccountStatesResponse.json");
        public static final SerializedResource GET_ALTERNATIVE_TITLES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAlternativeTitlesResponse.json");
        public static final SerializedResource GET_CHANGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetChangesResponse.json");
        public static final SerializedResource GET_CREDITS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetCreditsResponse.json");
        public static final SerializedResource GET_EXTERNAL_IDS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetExternalIdsResponse.json");
        public static final SerializedResource GET_IMAGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetImagesResponse.json");
        public static final SerializedResource GET_KEYWORDS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetKeywordsResponse.json");
        public static final SerializedResource GET_LATEST_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetLatestResponse.json");
        public static final SerializedResource GET_LISTS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetListsResponse.json");
        public static final SerializedResource GET_RECOMMENDATIONS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetRecommendationsResponse.json");
        public static final SerializedResource GET_RELEASE_DATES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetReleaseDatesResponse.json");
        public static final SerializedResource GET_REVIEWS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetReviewsResponse.json");
        public static final SerializedResource GET_SIMILAR_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetSimilarResponse.json");
        public static final SerializedResource GET_TRANSLATIONS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTranslationsResponse.json");
        public static final SerializedResource GET_VIDEOS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetVideosResponse.json");
        public static final SerializedResource GET_WATCH_PROVIDERS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetWatchProvidersResponse.json");
        public static final SerializedResource ADD_RATING_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "AddRatingResponse.json");
        public static final SerializedResource DELETE_RATING_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "DeleteRatingResponse.json");
    }
}
