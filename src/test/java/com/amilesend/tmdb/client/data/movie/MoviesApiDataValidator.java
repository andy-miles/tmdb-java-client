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
package com.amilesend.tmdb.client.data.movie;

import com.amilesend.tmdb.client.data.DataValidatorHelper;
import com.amilesend.tmdb.client.model.movie.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.movie.GetAlternativeTitlesResponse;
import com.amilesend.tmdb.client.model.movie.GetChangesResponse;
import com.amilesend.tmdb.client.model.movie.GetCreditsResponse;
import com.amilesend.tmdb.client.model.movie.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.movie.GetImagesResponse;
import com.amilesend.tmdb.client.model.movie.GetKeywordsResponse;
import com.amilesend.tmdb.client.model.movie.GetLatestResponse;
import com.amilesend.tmdb.client.model.movie.GetListsResponse;
import com.amilesend.tmdb.client.model.movie.GetRecommendationsResponse;
import com.amilesend.tmdb.client.model.movie.GetReleaseDatesResponse;
import com.amilesend.tmdb.client.model.movie.GetReviewsResponse;
import com.amilesend.tmdb.client.model.movie.GetSimilarResponse;
import com.amilesend.tmdb.client.model.movie.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.movie.GetVideosResponse;
import com.amilesend.tmdb.client.model.movie.GetWatchProvidersResponse;
import com.amilesend.tmdb.client.model.movie.type.Movie;
import com.amilesend.tmdb.client.model.movie.type.MovieCollection;
import com.amilesend.tmdb.client.model.movie.type.MovieDetails;
import com.amilesend.tmdb.client.model.movie.type.Poster;
import com.amilesend.tmdb.client.model.type.CastCredit;
import com.amilesend.tmdb.client.model.type.Change;
import com.amilesend.tmdb.client.model.type.ChangeItem;
import com.amilesend.tmdb.client.model.type.CrewCredit;
import com.amilesend.tmdb.client.model.type.MediaListInfo;
import com.amilesend.tmdb.client.model.type.ProductionCompany;
import com.amilesend.tmdb.client.model.type.Review;
import com.amilesend.tmdb.client.model.type.Video;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateListOf;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateNamedResource;
import static com.amilesend.tmdb.client.data.DataValidatorHelper.validateResource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class MoviesApiDataValidator {

    /////////////////
    // MovieDetails
    /////////////////

    public static void assertSameMovieDetails(final MovieDetails expected, final MovieDetails actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertSameMovieCollection(expected.getBelongsToCollection(), actual.getBelongsToCollection()),
                () -> assertEquals(expected.getBudget(), actual.getBudget()),
                () -> assertEquals(expected.getGenres(), actual.getGenres()),
                () -> assertEquals(expected.getHomepage(), actual.getHomepage()),
                () -> assertEquals(expected.getImdbId(), actual.getImdbId()),
                () -> validateListOf(
                        expected.getProductionCompanies(),
                        actual.getProductionCompanies(),
                        MoviesApiDataValidator::assertSameProductionCompany),
                () -> assertEquals(expected.getProductionCountries(), actual.getProductionCountries()),
                () -> assertEquals(expected.getRevenue(), actual.getRevenue()),
                () -> assertEquals(expected.getRuntime(), actual.getRuntime()),
                () -> assertEquals(expected.getSpokenLanguages(), actual.getSpokenLanguages()),
                () -> assertEquals(expected.getStatus(), actual.getStatus()),
                () -> assertEquals(expected.getTagline(), actual.getTagline()),
                () -> assertEquals(expected.isAdult(), actual.isAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.isVideo(), actual.isVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()));
    }

    public static void assertSameProductionCompany(final ProductionCompany expected, final ProductionCompany actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getLogoPath(), actual.getLogoPath()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()));
    }

    private static void assertSameMovieCollection(final MovieCollection expected, final MovieCollection actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()));
    }

    /////////////////////////////
    // GetAccountStatesResponse
    /////////////////////////////

    public static void assertSameGetAccountStatesResponse(
            final GetAccountStatesResponse expected,
            final GetAccountStatesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getRated(), actual.getRated()),
                () -> assertEquals(expected.getFavorite(), actual.getFavorite()),
                () -> assertEquals(expected.getWatchlist(), actual.getWatchlist()));
    }

    /////////////////////////////////
    // GetAlternativeTitlesResponse
    /////////////////////////////////

    public static void assertSameGetAlternativeTitlesResponse(
            final GetAlternativeTitlesResponse expected,
            final GetAlternativeTitlesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getTitles(), actual.getTitles()));
    }

    ///////////////////////
    // GetChangesResponse
    ///////////////////////

    public static void assertSameGetChangesResponse(
            final GetChangesResponse expected,
            final GetChangesResponse actual) {
        validateListOf(expected.getChanges(), actual.getChanges(), MoviesApiDataValidator::assertSameChange);
    }

    public static void assertSameChange(final Change<Poster> expected, final Change<Poster> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getKey(), actual.getKey()),
                () -> validateListOf(
                        expected.getItems(),
                        actual.getItems(),
                        MoviesApiDataValidator::assertSameChangeItem));
    }

    public static void assertSameChangeItem(final ChangeItem<?> expected, final ChangeItem<?> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getAction(), actual.getAction()),
                () -> assertEquals(expected.getTime(), actual.getTime()),
                () -> assertEquals(expected.getLanguageCode(), actual.getLanguageCode()),
                () -> assertEquals(expected.getCountryCode(), actual.getCountryCode()),
                () -> assertEquals(expected.getValue(), actual.getValue()),
                () -> assertEquals(expected.getOriginalValue(), actual.getOriginalValue()));
    }

    ///////////////////////
    // GetCreditsResponse
    ///////////////////////

    public static void assertSameGetCreditsResponse(
            final GetCreditsResponse expected,
            final GetCreditsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getCast(),
                        actual.getCast(),
                        MoviesApiDataValidator::assertSameCastCredit),
                () -> validateListOf(
                        expected.getCrew(),
                        actual.getCrew(),
                        MoviesApiDataValidator::assertSameCrewCredit));
    }

    public static void assertSameCastCredit(final CastCredit expected, final CastCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getKnownForDepartment(), actual.getKnownForDepartment()),
                () -> assertEquals(expected.getProfilePath(), actual.getProfilePath()),
                () -> assertEquals(expected.getCastId(), actual.getCastId()),
                () -> assertEquals(expected.getCharacter(), actual.getCharacter()),
                () -> assertEquals(expected.getCreditId(), actual.getCreditId()),
                () -> assertEquals(expected.getOrder(), actual.getOrder()));
    }

    public void assertSameCrewCredit(final CrewCredit expected, final CrewCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getKnownForDepartment(), actual.getKnownForDepartment()),
                () -> assertEquals(expected.getProfilePath(), actual.getProfilePath()),
                () -> assertEquals(expected.getCreditId(), actual.getCreditId()),
                () -> assertEquals(expected.getDepartment(), actual.getDepartment()),
                () -> assertEquals(expected.getJob(), actual.getJob()));
    }

    ///////////////////////////
    // GetExternalIdsResponse
    ///////////////////////////

    public static void assertSameGetExternalIdsResponse(
            final GetExternalIdsResponse expected,
            final GetExternalIdsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getImdbId(), actual.getImdbId()),
                () -> assertEquals(expected.getWikidataId(), actual.getWikidataId()),
                () -> assertEquals(expected.getFacebookId(), actual.getFacebookId()),
                () -> assertEquals(expected.getInstagramId(), actual.getInstagramId()),
                () -> assertEquals(expected.getTwitterId(), actual.getTwitterId()));
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static void assertSameGetImagesResponse(final GetImagesResponse expected, final GetImagesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getBackdrops(), actual.getBackdrops()),
                () -> assertEquals(expected.getLogos(), actual.getLogos()),
                () -> assertEquals(expected.getPosters(), actual.getPosters()));
    }

    ////////////////////////
    // GetKeywordsResponse
    ////////////////////////

    public static void assertSameGetKeywordsResponse(
            final GetKeywordsResponse expected,
            final GetKeywordsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getKeywords(),
                        actual.getKeywords(),
                        DataValidatorHelper::validateNamedResource));
    }

    /////////////////////
    // GetListsResponse
    /////////////////////

    public static void assertSameGetListsResponse(final GetListsResponse expected, final GetListsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        MoviesApiDataValidator::assertSameMediaListInfoList),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    public static void assertSameMediaListInfoList(final MediaListInfo expected, final MediaListInfo actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getDescription(), actual.getDescription()),
                () -> assertEquals(expected.getFavoriteCount(), actual.getFavoriteCount()),
                () -> assertEquals(expected.getItemCount(), actual.getItemCount()),
                () -> assertEquals(expected.getLanguageCode(), actual.getLanguageCode()),
                () -> assertEquals(expected.getListType(), actual.getListType()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()));
    }


    /////////////////
    // MovieDetails
    /////////////////

    public static void assertSameGetLatestResponse(final GetLatestResponse expected, final GetLatestResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertSameMovieCollection(expected.getBelongsToCollection(), actual.getBelongsToCollection()),
                () -> assertEquals(expected.getBudget(), actual.getBudget()),
                () -> assertEquals(expected.getGenres(), actual.getGenres()),
                () -> assertEquals(expected.getHomepage(), actual.getHomepage()),
                () -> assertEquals(expected.getImdbId(), actual.getImdbId()),
                () -> validateListOf(
                        expected.getProductionCompanies(),
                        actual.getProductionCompanies(),
                        MoviesApiDataValidator::assertSameProductionCompany),
                () -> assertEquals(expected.getProductionCountries(), actual.getProductionCountries()),
                () -> assertEquals(expected.getRevenue(), actual.getRevenue()),
                () -> assertEquals(expected.getRuntime(), actual.getRuntime()),
                () -> assertEquals(expected.getSpokenLanguages(), actual.getSpokenLanguages()),
                () -> assertEquals(expected.getStatus(), actual.getStatus()),
                () -> assertEquals(expected.getTagline(), actual.getTagline()),
                () -> assertEquals(expected.isAdult(), actual.isAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.isVideo(), actual.isVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()));
    }

    ///////////////////////////////
    // GetRecommendationsResponse
    ///////////////////////////////

    public static void assertSameGetRecommendationsResponse(
            final GetRecommendationsResponse expected,
            final GetRecommendationsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        MoviesApiDataValidator::assertSameMovie),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    private static void assertSameMovie(final Movie expected, final Movie actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage()),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()));
    }

    ////////////////////////////
    // GetReleaseDatesResponse
    ////////////////////////////

    public static void assertSameGetReleaseDatesResponse(
            final GetReleaseDatesResponse expected,
            final GetReleaseDatesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getResults(), actual.getResults()));
    }

    ///////////////////////
    // GetReviewsResponse
    ///////////////////////

    public static void assertSameGetReviewsResponse(
            final GetReviewsResponse expected,
            final GetReviewsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        MoviesApiDataValidator::assertSameReview),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    public static void assertSameReview(final Review expected, final Review actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getAuthor(), actual.getAuthor()),
                () -> assertEquals(expected.getAuthorDetails(), actual.getAuthorDetails()),
                () -> assertEquals(expected.getContent(), actual.getContent()),
                () -> assertEquals(expected.getCreatedAt(), actual.getCreatedAt()),
                () -> assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt()),
                () -> assertEquals(expected.getUrl(), actual.getUrl()));
    }

    ///////////////////////
    // GetSimilarResponse
    ///////////////////////

    public static void assertSameGetSimilarResponse(final GetSimilarResponse expected, final GetSimilarResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        MoviesApiDataValidator::assertSameMovie),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    ////////////////////////////
    // GetTranslationsResponse
    ////////////////////////////

    public static void assertSameGetTranslationsResponse(
            final GetTranslationsResponse expected,
            final GetTranslationsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getTranslations(), actual.getTranslations()));
    }

    //////////////////////
    // GetVideosResponse
    //////////////////////

    public static void assertSameGetVideosResponse(
            final GetVideosResponse expected,
            final GetVideosResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> validateListOf(
                        expected.getResults(),
                        actual.getResults(),
                        MoviesApiDataValidator::assertSameVideo));
    }

    public static void assertSameVideo(final Video expected, final Video actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateNamedResource(expected, actual),
                () -> assertEquals(expected.getLanguageCode(), actual.getLanguageCode()),
                () -> assertEquals(expected.getCountryCode(), actual.getCountryCode()),
                () -> assertEquals(expected.getKey(), actual.getKey()),
                () -> assertEquals(expected.getSite(), actual.getSite()),
                () -> assertEquals(expected.getType(), actual.getType()),
                () -> assertEquals(expected.getOfficial(), actual.getOfficial()),
                () -> assertEquals(expected.getPublishedAt(), actual.getPublishedAt()));
    }

    //////////////////////////////
    // GetWatchProvidersResponse
    //////////////////////////////

    public static void assertSameGetWatchProvidersResponse(
            final GetWatchProvidersResponse expected,
            final GetWatchProvidersResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> validateResource(expected, actual),
                () -> assertEquals(expected.getResults(), actual.getResults()));
    }
}
