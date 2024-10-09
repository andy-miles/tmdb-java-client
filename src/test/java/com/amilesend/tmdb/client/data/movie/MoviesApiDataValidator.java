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
package com.amilesend.tmdb.client.data.movie;

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
import com.amilesend.tmdb.client.model.type.Keyword;
import com.amilesend.tmdb.client.model.type.MediaListInfo;
import com.amilesend.tmdb.client.model.type.ProductionCompany;
import com.amilesend.tmdb.client.model.type.Review;
import com.amilesend.tmdb.client.model.type.Video;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

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
                () -> assertSameMovieCollection(expected.getBelongsToCollection(), actual.getBelongsToCollection()),
                () -> assertEquals(expected.getBudget(), actual.getBudget()),
                () -> assertEquals(expected.getGenres(), actual.getGenres()),
                () -> assertEquals(expected.getHomepage(), actual.getHomepage()),
                () -> assertEquals(expected.getImdbId(), actual.getImdbId()),
                () -> assertSameProductionCompanies(expected.getProductionCompanies(), actual.getProductionCompanies()),
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
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getId(), actual.getId()));
    }

    public static void assertSameProductionCompanies(
            final List<ProductionCompany> expected,
            final List<ProductionCompany> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameProductionCompany(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameProductionCompany(final ProductionCompany expected, final ProductionCompany actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getLogoPath(), actual.getLogoPath()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getId(), actual.getId()));
    }

    private static void assertSameMovieCollection(final MovieCollection expected, final MovieCollection actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getId(), actual.getId()));
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
                () -> assertEquals(expected.getRated(), actual.getRated()),
                () -> assertEquals(expected.getFavorite(), actual.getFavorite()),
                () -> assertEquals(expected.getWatchlist(), actual.getWatchlist()),
                () -> assertEquals(expected.getId(), actual.getId()));
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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getTitles(), actual.getTitles()));
    }

    ///////////////////////
    // GetChangesResponse
    ///////////////////////

    public static void assertSameGetChangesResponse(
            final GetChangesResponse expected,
            final GetChangesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertSameChanges(expected.getChanges(), actual.getChanges());
    }

    public static void assertSameChanges(final List<Change<Poster>> expected, final List<Change<Poster>> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameChange(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameChange(final Change<Poster> expected, final Change<Poster> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getKey(), actual.getKey()),
                () -> assertSameChangeItems(expected.getItems(), actual.getItems()));
    }

    private static void assertSameChangeItems(
            final List<ChangeItem<Poster>> expected,
            final List<ChangeItem<Poster>> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameChangeItem(expected.get(i), actual.get(i));
        }
    }

    public static void assertSameChangeItem(final ChangeItem<?> expected, final ChangeItem<?> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameCastCredits(expected.getCast(), actual.getCast()),
                () -> assertSameCrewCredits(expected.getCrew(), actual.getCrew()));
    }


    public static void assertSameCastCredits(final List<CastCredit> expected, final List<CastCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameCastCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameCastCredit(final CastCredit expected, final CastCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
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

    public static void assertSameCrewCredits(final List<CrewCredit> expected, final List<CrewCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameCrewCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameCrewCredit(final CrewCredit expected, final CrewCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
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
                () -> assertEquals(expected.getId(), actual.getId()),
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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameKeywords(expected.getKeywords(), actual.getKeywords()));
    }

    public static void assertSameKeywords(final List<Keyword> expected, final List<Keyword> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameKeyword(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameKeyword(final Keyword expected, final Keyword actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()));
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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameMediaListInfoLists(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    public static void assertSameMediaListInfoLists(
            final List<MediaListInfo> expected,
            final List<MediaListInfo> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameMediaListInfoList(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameMediaListInfoList(final MediaListInfo expected, final MediaListInfo actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
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
                () -> assertSameMovieCollection(expected.getBelongsToCollection(), actual.getBelongsToCollection()),
                () -> assertEquals(expected.getBudget(), actual.getBudget()),
                () -> assertEquals(expected.getGenres(), actual.getGenres()),
                () -> assertEquals(expected.getHomepage(), actual.getHomepage()),
                () -> assertEquals(expected.getImdbId(), actual.getImdbId()),
                () -> assertSameProductionCompanies(expected.getProductionCompanies(), actual.getProductionCompanies()),
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
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getId(), actual.getId()));
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
                () -> assertSameMovies(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    private static void assertSameMovies(final List<Movie> expected, final List<Movie> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameMovie(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameMovie(final Movie expected, final Movie actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
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
                () -> assertEquals(expected.getId(), actual.getId()),
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
                () -> assertSameReviews(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    public static void assertSameReviews(final List<Review> expected, final List<Review> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameReview(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameReview(final Review expected, final Review actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
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
                () -> assertSameMovies(expected.getResults(), actual.getResults()),
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
                () -> assertEquals(expected.getId(), actual.getId()),
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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameVideos(expected.getResults(), actual.getResults()));
    }

    public static void assertSameVideos(final List<Video> expected, final List<Video> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameVideo(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameVideo(final Video expected, final Video actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getLanguageCode(), actual.getLanguageCode()),
                () -> assertEquals(expected.getCountryCode(), actual.getCountryCode()),
                () -> assertEquals(expected.getName(), actual.getName()),
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
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getResults(), actual.getResults()));
    }
}
