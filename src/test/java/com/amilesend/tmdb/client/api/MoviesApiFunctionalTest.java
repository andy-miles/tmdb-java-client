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
import com.amilesend.tmdb.client.model.movie.AddRatingRequest;
import com.amilesend.tmdb.client.model.movie.AddRatingResponse;
import com.amilesend.tmdb.client.model.movie.DeleteRatingRequest;
import com.amilesend.tmdb.client.model.movie.DeleteRatingResponse;
import com.amilesend.tmdb.client.model.movie.GetAccountStatesRequest;
import com.amilesend.tmdb.client.model.movie.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.movie.GetAlternativeTitlesRequest;
import com.amilesend.tmdb.client.model.movie.GetAlternativeTitlesResponse;
import com.amilesend.tmdb.client.model.movie.GetChangesRequest;
import com.amilesend.tmdb.client.model.movie.GetChangesResponse;
import com.amilesend.tmdb.client.model.movie.GetCreditsRequest;
import com.amilesend.tmdb.client.model.movie.GetCreditsResponse;
import com.amilesend.tmdb.client.model.movie.GetExternalIdsRequest;
import com.amilesend.tmdb.client.model.movie.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.movie.GetImagesRequest;
import com.amilesend.tmdb.client.model.movie.GetImagesResponse;
import com.amilesend.tmdb.client.model.movie.GetKeywordsRequest;
import com.amilesend.tmdb.client.model.movie.GetKeywordsResponse;
import com.amilesend.tmdb.client.model.movie.GetLatestResponse;
import com.amilesend.tmdb.client.model.movie.GetListsRequest;
import com.amilesend.tmdb.client.model.movie.GetListsResponse;
import com.amilesend.tmdb.client.model.movie.GetMovieDetailsRequest;
import com.amilesend.tmdb.client.model.movie.GetMovieDetailsResponse;
import com.amilesend.tmdb.client.model.movie.GetRecommendationsRequest;
import com.amilesend.tmdb.client.model.movie.GetRecommendationsResponse;
import com.amilesend.tmdb.client.model.movie.GetReleaseDatesRequest;
import com.amilesend.tmdb.client.model.movie.GetReleaseDatesResponse;
import com.amilesend.tmdb.client.model.movie.GetReviewsRequest;
import com.amilesend.tmdb.client.model.movie.GetReviewsResponse;
import com.amilesend.tmdb.client.model.movie.GetSimilarRequest;
import com.amilesend.tmdb.client.model.movie.GetSimilarResponse;
import com.amilesend.tmdb.client.model.movie.GetTranslationsRequest;
import com.amilesend.tmdb.client.model.movie.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.movie.GetVideosRequest;
import com.amilesend.tmdb.client.model.movie.GetVideosResponse;
import com.amilesend.tmdb.client.model.movie.GetWatchProvidersRequest;
import com.amilesend.tmdb.client.model.movie.GetWatchProvidersResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.ADD_RATING_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.DELETE_RATING_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_ACCOUNT_STATES_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_ALTERNATIVE_TITLES_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_CHANGES_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_CREDITS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_EXTERNAL_IDS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_IMAGES_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_KEYWORDS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_LATEST_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_LISTS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_MOVIE_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_RECOMMENDATIONS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_RELEASE_DATES_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_REVIEWS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_SIMILAR_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_TRANSLATIONS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_VIDEOS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.Responses.GET_WATCH_PROVIDERS_RESPONSE;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newAddRatingResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newDeleteRatingResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetAccountStatesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetAlternativeTitlesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetChangesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetCreditsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetExternalIdsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetImagesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetKeywordsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetLatestResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetListsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetMovieDetailsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetRecommendationsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetReleaseDatesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetReviewsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetSimilarResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetVideosResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newGetWatchProvidersResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetAccountStatesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetAlternativeTitlesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetChangesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetCreditsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetExternalIdsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetImagesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetKeywordsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetLatestResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetListsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetRecommendationsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetReleaseDatesResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetReviewsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetSimilarResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetVideosResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameGetWatchProvidersResponse;
import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameMovieDetails;
import static org.junit.Assert.assertEquals;

public class MoviesApiFunctionalTest extends FunctionalTestBase {
    private MoviesApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getMoviesApi();
    }

    @Test
    public void getMovieDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_MOVIE_DETAILS_RESPONSE);
        final GetMovieDetailsResponse expected = newGetMovieDetailsResponse();

        final GetMovieDetailsResponse actual = apiUnderTest.getMovieDetails(GetMovieDetailsRequest.builder()
                .movieId(1)
                .language(Locale.US.getLanguage())
                .build());

        assertSameMovieDetails(expected, actual);
    }

    @Test
    public void getAccountStates_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ACCOUNT_STATES_RESPONSE);
        final GetAccountStatesResponse expected = newGetAccountStatesResponse();

        final GetAccountStatesResponse actual = apiUnderTest.getAccountStates(GetAccountStatesRequest.builder()
                .movieId(1)
                .sessionId("sessionIdValue")
                .build());

        assertSameGetAccountStatesResponse(expected, actual);
    }

    @Test
    public void getAlternativeTitles_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ALTERNATIVE_TITLES_RESPONSE);
        final GetAlternativeTitlesResponse expected = newGetAlternativeTitlesResponse();

        final GetAlternativeTitlesResponse actual = apiUnderTest.getAlternativeTitles(
                GetAlternativeTitlesRequest.builder()
                        .movieId(4000)
                        .country(Locale.US.getCountry())
                        .build());

        assertSameGetAlternativeTitlesResponse(expected, actual);
    }

    @Test
    public void getChanges_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CHANGES_RESPONSE);
        final GetChangesResponse expected = newGetChangesResponse();

        final GetChangesResponse actual = apiUnderTest.getChanges(GetChangesRequest.builder()
                .movieId(4000)
                .page(1)
                .startDate(LocalDate.of(2023, 1, 1))
                .endDate(LocalDate.of(2024, 1, 1))
                .build());

        assertSameGetChangesResponse(expected, actual);
    }

    @Test
    public void getCredits_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CREDITS_RESPONSE);
        final GetCreditsResponse expected = newGetCreditsResponse();

        final GetCreditsResponse actual = apiUnderTest.getCredits(GetCreditsRequest.builder()
                .movieId(4000)
                .language(Locale.US.getLanguage())
                .build());

        assertSameGetCreditsResponse(expected, actual);
    }

    @Test
    public void getExternalIds_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_EXTERNAL_IDS_RESPONSE);
        final GetExternalIdsResponse expected = newGetExternalIdsResponse();

        final GetExternalIdsResponse actual = apiUnderTest.getExternalIds(GetExternalIdsRequest.builder()
                .movieId(4000)
                .build());

        assertSameGetExternalIdsResponse(expected, actual);
    }

    @Test
    public void getImages_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_IMAGES_RESPONSE);
        final GetImagesResponse expected = newGetImagesResponse();

        final GetImagesResponse actual = apiUnderTest.getImages(GetImagesRequest.builder()
                .movieId(4000)
                .language(Locale.US.getLanguage())
                .includeImageLanguage(Locale.US.getLanguage().toString())
                .build());

        assertSameGetImagesResponse(expected, actual);
    }

    @Test
    public void getKeywords_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_KEYWORDS_RESPONSE);
        final GetKeywordsResponse expected = newGetKeywordsResponse();

        final GetKeywordsResponse actual = apiUnderTest.getKeywords(GetKeywordsRequest.builder()
                .movieId(4000)
                .build());

        assertSameGetKeywordsResponse(expected, actual);
    }

    @Test
    public void getLatest_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_LATEST_RESPONSE);
        final GetLatestResponse expected = newGetLatestResponse();

        final GetLatestResponse actual = apiUnderTest.getLatest();

        assertSameGetLatestResponse(expected, actual);
    }

    @Test
    public void getLists_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_LISTS_RESPONSE);
        final GetListsResponse expected = newGetListsResponse();

        final GetListsResponse actual = apiUnderTest.getLists(GetListsRequest.builder()
                .page(1)
                .language(Locale.US.getLanguage())
                .movieId(4000)
                .build());

        assertSameGetListsResponse(expected, actual);
    }

    @Test
    public void getRecommendations_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_RECOMMENDATIONS_RESPONSE);
        final GetRecommendationsResponse expected = newGetRecommendationsResponse();

        final GetRecommendationsResponse actual = apiUnderTest.getRecommendations(GetRecommendationsRequest.builder()
                .movieId(4000)
                .language(Locale.US.getLanguage())
                .page(1)
                .build());

        assertSameGetRecommendationsResponse(expected, actual);
    }

    @Test
    public void getReleaseDates_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_RELEASE_DATES_RESPONSE);
        final GetReleaseDatesResponse expected = newGetReleaseDatesResponse();

        final GetReleaseDatesResponse actual = apiUnderTest.getReleaseDates(GetReleaseDatesRequest.builder()
                .movieId(4000)
                .build());

        assertSameGetReleaseDatesResponse(expected, actual);
    }

    @Test
    public void getReviews_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_REVIEWS_RESPONSE);
        final GetReviewsResponse expected = newGetReviewsResponse();

        final GetReviewsResponse actual = apiUnderTest.getReviews(GetReviewsRequest.builder()
                .page(1)
                .language(Locale.US.getLanguage())
                .movieId(4000)
                .build());

        assertSameGetReviewsResponse(expected, actual);
    }

    @Test
    public void getSimilar_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_SIMILAR_RESPONSE);
        final GetSimilarResponse expected = newGetSimilarResponse();

        final GetSimilarResponse actual = apiUnderTest.getSimilar(GetSimilarRequest.builder()
                .movieId(4000)
                .page(1)
                .language(Locale.US.getLanguage())
                .build());

        assertSameGetSimilarResponse(expected, actual);
    }

    @Test
    public void getTranslations_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TRANSLATIONS_RESPONSE);
        final GetTranslationsResponse expected = newGetTranslationsResponse();

        final GetTranslationsResponse actual = apiUnderTest.getTranslations(GetTranslationsRequest.builder()
                .movieId(4000)
                .build());

        assertSameGetTranslationsResponse(expected, actual);
    }

    @Test
    public void getVideos_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_VIDEOS_RESPONSE);
        final GetVideosResponse expected = newGetVideosResponse();

        final GetVideosResponse actual = apiUnderTest.getVideos(GetVideosRequest.builder()
                .language(Locale.US.getLanguage())
                .movieId(4000)
                .build());

        assertSameGetVideosResponse(expected, actual);
    }

    @Test
    public void getWatchProvidersResponse_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_WATCH_PROVIDERS_RESPONSE);
        final GetWatchProvidersResponse expected = newGetWatchProvidersResponse();

        final GetWatchProvidersResponse actual = apiUnderTest.getWatchProviders(
                GetWatchProvidersRequest.builder()
                        .movieId(1)
                        .build());

        assertSameGetWatchProvidersResponse(expected, actual);
    }

    @Test
    public void addRating_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, ADD_RATING_RESPONSE);
        final AddRatingResponse expected = newAddRatingResponse();

        final AddRatingResponse actual = apiUnderTest.addRating(AddRatingRequest.builder()
                .sessionId("SessionIdValue")
                .movieId(1)
                .value(8.5D)
                .build());

        assertEquals(expected, actual);
    }

    @Test
    public void deleteRating_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, DELETE_RATING_RESPONSE);
        final DeleteRatingResponse expected = newDeleteRatingResponse();

        final DeleteRatingResponse actual = apiUnderTest.deleteRating(DeleteRatingRequest.builder()
                .sessionId("SessionIdValue")
                .movieId(1)
                .build());

        assertEquals(expected, actual);
    }
}
