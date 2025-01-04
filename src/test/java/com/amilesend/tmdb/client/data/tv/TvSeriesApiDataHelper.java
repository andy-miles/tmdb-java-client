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
package com.amilesend.tmdb.client.data.tv;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.acount.type.Rated;
import com.amilesend.tmdb.client.model.tv.seasons.type.TvSeason;
import com.amilesend.tmdb.client.model.tv.series.AddRatingResponse;
import com.amilesend.tmdb.client.model.tv.series.DeleteRatingResponse;
import com.amilesend.tmdb.client.model.tv.series.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetAggregateCreditsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetAlternativeTitlesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetContentRatingsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetEpisodeGroupsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetKeywordsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetListsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetRecommendationsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetReviewsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetScreenedTheatricallyResponse;
import com.amilesend.tmdb.client.model.tv.series.GetSeriesDetailsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetSimilarResponse;
import com.amilesend.tmdb.client.model.tv.series.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetVideosResponse;
import com.amilesend.tmdb.client.model.tv.series.GetWatchProvidersResponse;
import com.amilesend.tmdb.client.model.tv.series.type.ContentRating;
import com.amilesend.tmdb.client.model.tv.series.type.EpisodeGroup;
import com.amilesend.tmdb.client.model.tv.series.type.Poster;
import com.amilesend.tmdb.client.model.tv.series.type.ScreenedTvSeriesEpisode;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeries;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesCredit;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesEpisode;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesNetwork;
import com.amilesend.tmdb.client.model.tv.series.type.TvSeriesTranslationData;
import com.amilesend.tmdb.client.model.type.Change;
import com.amilesend.tmdb.client.model.type.ChangeItem;
import com.amilesend.tmdb.client.model.type.Translation;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.amilesend.tmdb.client.data.genre.GenresApiDataHelper.newGenreList;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newAlternativeTitle;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newCastCredit;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newCrewCredit;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newImage;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newKeyword;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newLanguage;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newMediaListInfo;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newProductionCompany;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newProductionCountry;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newReview;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newVideo;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newWatchProviderResult;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newAggregateCastCredit;
import static com.amilesend.tmdb.client.data.tv.TvSeasonsApiDataHelper.newAggregateCrewCredit;

@UtilityClass
public class TvSeriesApiDataHelper {
    ///////////////////////////////////////
    // assertSameGetSeriesDetailsResponse
    ///////////////////////////////////////

    public static GetSeriesDetailsResponse newGetSeriesDetailsResponse() {
        return GetSeriesDetailsResponse.builder()
                .id(1000)
                .name("TV Series Name Value")
                .adult(false)
                .backdropPath("/backdrop.jpg")
                .createdBy(List.of(newTvSeriesCredit(1), newTvSeriesCredit(2), newTvSeriesCredit(3)))
                .episodeRunTime(List.of(22, 24, 23))
                .firstAirDate(LocalDate.of(2020, 1, 1))
                .genres(newGenreList(3))
                .homepage("https://homepage.com")
                .inProduction(false)
                .languages(List.of(Locale.US.getLanguage()))
                .lastAirDate(LocalDate.of(2023, 1, 1))
                .lastEpisodeToAir(newTvSeriesEpisode())
                .name("None")
                .networks(List.of(newTvSeriesNetwork(1), newTvSeriesNetwork(2), newTvSeriesNetwork(3)))
                .numberOfEpisodes(3)
                .numberOfSeasons(3)
                .originCountry(List.of(Locale.US.getCountry()))
                .originalLanguage(Locale.US.getLanguage())
                .originalName("Original TV Series Name Value")
                .originalLanguage("TV Series Overview")
                .popularity(0.42D)
                .posterPath("/poster.jpg")
                .productionCompanies(List.of(
                        newProductionCompany(1),
                        newProductionCompany(2),
                        newProductionCompany(3)))
                .productionCountries(List.of(
                        newProductionCountry(1),
                        newProductionCountry(2),
                        newProductionCountry(3)))
                .seasons(List.of(newTvSeason(1), newTvSeason(2), newTvSeason(3)))
                .spokenLanguages(List.of(newLanguage()))
                .status("Ended")
                .tagline("TV Series Tag Line")
                .type("Holiday Special")
                .voteAverage(0.45D)
                .voteCount(2000)
                .build();
    }

    private static TvSeriesCredit newTvSeriesCredit(final int index) {
        return TvSeriesCredit.builder()
                .id(index)
                .name("Credit " + index)
                .creditId("Credit ID " + index)
                .gender(1)
                .profilePath("/profile" + index + ".png")
                .build();
    }

    private static TvSeriesEpisode newTvSeriesEpisode() {
        return TvSeriesEpisode.builder()
                .id(1)
                .name("TV Episode Name Value")
                .airDate(LocalDate.of(2010, 11, 4))
                .episodeNumber(4)
                .overview("Episode Overview Value")
                .productionCode("Production Code Value")
                .runtime(22)
                .seasonNumber(1)
                .stillPath("/epStill.png")
                .voteAverage(0.64D)
                .voteCount(1000)
                .episodeType("Episode Type Value")
                .build();
    }

    static TvSeriesNetwork newTvSeriesNetwork(final int index) {
        return TvSeriesNetwork.builder()
                .id(index)
                .name("Network " + index)
                .logoPath("/logo" + index + ".png")
                .originCountry(Locale.US.getCountry())
                .build();
    }

    private static TvSeason newTvSeason(final int index) {
        return TvSeason.builder()
                .id(index)
                .name("TV Season " + index)
                .seasonId("Season ID " + index)
                .airDate(LocalDate.of(2010 + index, 1, 1))
                .overview("TV Season Overview " + index)
                .posterPath("/poster" + index + ".jpg")
                .seasonNumber(index)
                .voteAverage(0.34D)
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

    ////////////////////////////////
    // GetAggregateCreditsResponse
    ////////////////////////////////

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

    /////////////////////////////////
    // GetAlternativeTitlesResponse
    /////////////////////////////////

    public static GetAlternativeTitlesResponse newGetAlternativeTitlesResponse() {
        return GetAlternativeTitlesResponse.builder()
                .id(300)
                .titles(List.of(
                        newAlternativeTitle(1),
                        newAlternativeTitle(2),
                        newAlternativeTitle(3)))
                .build();
    }

    ///////////////////////
    // GetChangesResponse
    ///////////////////////

    public static GetChangesResponse newGetChangesResponse() {
        return GetChangesResponse.builder()
                .changes(List.of(newChange(1), newChange(5), newChange(10)))
                .build();
    }

    private static Change<Poster> newChange(final int index) {
        return Change.<Poster>builder()
                .key("Change Key " + index)
                .items(List.of(newChangeItem(index), newChangeItem(index + 1), newChangeItem(index + 3)))
                .build();
    }

    private static ChangeItem<Poster> newChangeItem(final int index) {
        return ChangeItem.<Poster>builder()
                .id("ChangeItem ID " + index)
                .action("Action " + index)
                .time(LocalDateTime.of(2023, 1, 1, 12, 0, 0))
                .languageCode(Locale.US.getLanguage())
                .countryCode(Locale.US.getCountry())
                .value(newPoster(index))
                .originalValue(newPoster(index - 1))
                .build();
    }

    private static Poster newPoster(final int index) {
        return Poster.builder()
                .languageCode(Locale.US.getLanguage())
                .filePath("/filePath" + index)
                .build();
    }

    //////////////////////////////
    // GetContentRatingsResponse
    //////////////////////////////

    public static GetContentRatingsResponse newGetContentRatingsResponse() {
        return GetContentRatingsResponse.builder()
                .id(100)
                .results(List.of(
                        newContentRating(1),
                        newContentRating(5),
                        newContentRating(10)))
                .build();
    }

    private static ContentRating newContentRating(final int index) {
        return ContentRating.builder()
                .countryCode("Country Code " + index)
                .rating("Rating " + index)
                .descriptors(List.of("Descriptor " + index, "Descriptor " + (index + 1), "Descriptor " + (index + 2)))
                .build();
    }

    ///////////////////////
    // GetCreditsResponse
    ///////////////////////

    public static GetCreditsResponse newGetCreditsResponse() {
        return GetCreditsResponse.builder()
                .id(100)
                .cast(List.of(
                        newCastCredit(1),
                        newCastCredit(2),
                        newCastCredit(3)))
                .crew(List.of(
                        newCrewCredit(1),
                        newCrewCredit(2),
                        newCrewCredit(3)))
                .build();
    }

    /////////////////////////////
    // GetEpisodeGroupsResponse
    /////////////////////////////

    public static GetEpisodeGroupsResponse newGetEpisodeGroupsResponse() {
        return GetEpisodeGroupsResponse.builder()
                .id(500)
                .results(List.of(newEpisodeGroup(1), newEpisodeGroup(2), newEpisodeGroup(3)))
                .build();
    }

    private static EpisodeGroup newEpisodeGroup(final int index) {
        return EpisodeGroup.builder()
                .id("Episode Group ID " + index)
                .name("Episode Group " + index)
                .groupCount(index)
                .episodeCount(12)
                .description("Description " + index)
                .groupCount(index)
                .network(TvSeriesNetwork.builder()
                        .id(100 + index)
                        .name("Network " + index)
                        .originCountry("Country " + index)
                        .logoPath("/logo" + index + ".jpg")
                        .build())
                .type(index)
                .build();
    }

    ///////////////////////////
    // GetExternalIdsResponse
    ///////////////////////////

    public static GetExternalIdsResponse newGetExternalIdsResponse() {
        return GetExternalIdsResponse.builder()
                .id(500)
                .imdbId("IMDB ID Value")
                .freebaseMid("Freebase MID")
                .freebaseId("Freebase ID")
                .tvdbId(1)
                .tvrageId(2)
                .wikidataId("Wikidata ID")
                .facebookId("Facebook ID")
                .instagramId("Instagram ID")
                .twitterId("Twitter ID")
                .build();
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static GetImagesResponse newGetImagesResponse() {
        return GetImagesResponse.builder()
                .id(500)
                .backdrops(List.of(newImage(1), newImage(2), newImage(3)))
                .logos(List.of(newImage(4), newImage(5), newImage(6)))
                .posters(List.of(newImage(7), newImage(8), newImage(9)))
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

    ///////////////////////////////
    // GetRecommendationsResponse
    ///////////////////////////////

    public static GetRecommendationsResponse newGetRecommendationsResponse() {
        return GetRecommendationsResponse.builder()
                .page(1)
                .results(List.of(newTvSeries(1), newTvSeries(2), newTvSeries(3)))
                .totalPages(1)
                .totalResults(3)
                .build();
    }

    static TvSeries newTvSeries(final int index) {
        return TvSeries.builder()
                .id(index)
                .name("TV Series " + index)
                .adult(false)
                .backdropPath("/backdrop" + index + ".jpg")
                .originalLanguage(Locale.US.getLanguage())
                .originalTitle("TV Series " + index)
                .overview("Overview " + index)
                .posterPath("/poster" + index + ".jpg")
                .mediaType("Media Type")
                .genreIds(List.of(1, 4, 10))
                .popularity(0.78D)
                .firstAirDate(LocalDate.of(2014, 5, 6))
                .voteAverage(0.82D)
                .voteCount(3000)
                .originCountry(List.of(Locale.US.getCountry()))
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

    ////////////////////////////////////
    // GetScreenedTheatricallyResponse
    ////////////////////////////////////

    public static GetScreenedTheatricallyResponse newGetScreenedTheatricallyResponse() {
        return GetScreenedTheatricallyResponse.builder()
                .id(500)
                .results(List.of(
                        newScreenedTvSeriesEpisode(1, 1, 1),
                        newScreenedTvSeriesEpisode(2, 1, 2),
                        newScreenedTvSeriesEpisode(3, 1, 3)))
                .build();
    }

    private static ScreenedTvSeriesEpisode newScreenedTvSeriesEpisode(
            final int index,
            final int season,
            final int episode) {
        return ScreenedTvSeriesEpisode.builder()
                .id(index)
                .seasonNumber(season)
                .episodeNumber(episode)
                .build();
    }

    ///////////////////////
    // GetSimilarResponse
    ///////////////////////

    public static GetSimilarResponse newGetSimilarResponse() {
        return GetSimilarResponse.builder()
                .results(List.of(
                        newTvSeries(1),
                        newTvSeries(2),
                        newTvSeries(3),
                        newTvSeries(4)))
                .totalPages(1)
                .page(1)
                .totalResults(4)
                .build();
    }

    ////////////////////////////
    // GetTranslationsResponse
    ////////////////////////////

    public static GetTranslationsResponse newGetTranslationsResponse() {
        return GetTranslationsResponse.builder()
                .id(500)
                .translations(List.of(newTranslation(1), newTranslation(2), newTranslation(3)))
                .build();
    }

    private static Translation<TvSeriesTranslationData> newTranslation(final int index) {
        return Translation.<TvSeriesTranslationData>builder()
                .countryCode("Country Code " + index)
                .languageCode("Language Code " + index)
                .name("Translation Name " + index)
                .englishName("English Name " + index)
                .data(newTvSeriesTranslationData(index))
                .build();
    }

    private static TvSeriesTranslationData newTvSeriesTranslationData(final int index) {
        return TvSeriesTranslationData.builder()
                .name("TV Series Translation Name " + index)
                .overview("TV Series Translation Overview " + index)
                .homepage("https://somesite.com")
                .tagline("TV Series Translation Tagline " + index)
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
        private static final String RESOURCE_FOLDER = "/TvSeriesApi/";

        public static final SerializedResource GET_SERIES_DETAILS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetSeriesDetailsResponse.json");
        public static final SerializedResource GET_ACCOUNT_STATES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAccountStatesResponse.json");
        public static final SerializedResource GET_AGGREGATE_CREDITS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAggregateCreditsResponse.json");
        public static final SerializedResource GET_ALTERNATIVE_TITLES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetAlternativeTitlesResponse.json");
        public static final SerializedResource GET_CHANGES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetChangesResponse.json");
        public static final SerializedResource GET_CONTENT_RATINGS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetContentRatingsResponse.json");
        public static final SerializedResource GET_CREDITS_RESPONSE =
                new SerializedResource((RESOURCE_FOLDER + "GetCreditsResponse.json"));
        public static final SerializedResource GET_EPISODE_GROUPS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetEpisodeGroupsResponse.json");
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
        public static final SerializedResource GET_REVIEWS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetReviewsResponse.json");
        public static final SerializedResource GET_SCREENED_THREATRICALLY_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetScreenedTheatricallyResponse.json");
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
