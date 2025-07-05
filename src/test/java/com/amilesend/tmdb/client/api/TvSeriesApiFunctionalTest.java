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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.tv.series.AddRatingRequest;
import com.amilesend.tmdb.client.model.tv.series.AddRatingResponse;
import com.amilesend.tmdb.client.model.tv.series.DeleteRatingRequest;
import com.amilesend.tmdb.client.model.tv.series.DeleteRatingResponse;
import com.amilesend.tmdb.client.model.tv.series.GetAccountStatesRequest;
import com.amilesend.tmdb.client.model.tv.series.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetAggregateCreditsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetAggregateCreditsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetAlternativeTitlesRequest;
import com.amilesend.tmdb.client.model.tv.series.GetAlternativeTitlesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetChangesRequest;
import com.amilesend.tmdb.client.model.tv.series.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetContentRatingsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetContentRatingsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetCreditsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetEpisodeGroupsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetEpisodeGroupsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetExternalIdsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetImagesRequest;
import com.amilesend.tmdb.client.model.tv.series.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.series.GetKeywordsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetKeywordsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetListsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetListsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetRecommendationsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetRecommendationsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetReviewsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetReviewsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetScreenedTheatricallyRequest;
import com.amilesend.tmdb.client.model.tv.series.GetScreenedTheatricallyResponse;
import com.amilesend.tmdb.client.model.tv.series.GetSeriesDetailsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetSeriesDetailsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetSimilarRequest;
import com.amilesend.tmdb.client.model.tv.series.GetSimilarResponse;
import com.amilesend.tmdb.client.model.tv.series.GetTranslationsRequest;
import com.amilesend.tmdb.client.model.tv.series.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.series.GetVideosRequest;
import com.amilesend.tmdb.client.model.tv.series.GetVideosResponse;
import com.amilesend.tmdb.client.model.tv.series.GetWatchProvidersRequest;
import com.amilesend.tmdb.client.model.tv.series.GetWatchProvidersResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.ADD_RATING_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.DELETE_RATING_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_ACCOUNT_STATES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_AGGREGATE_CREDITS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_ALTERNATIVE_TITLES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_CHANGES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_CONTENT_RATINGS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_CREDITS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_EPISODE_GROUPS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_EXTERNAL_IDS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_IMAGES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_KEYWORDS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_LATEST_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_LISTS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_RECOMMENDATIONS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_REVIEWS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_SCREENED_THREATRICALLY_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_SERIES_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_SIMILAR_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_TRANSLATIONS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_VIDEOS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.Responses.GET_WATCH_PROVIDERS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newAddRatingResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newDeleteRatingResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetAccountStatesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetAggregateCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetAlternativeTitlesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetChangesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetContentRatingsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetEpisodeGroupsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetExternalIdsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetImagesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetKeywordsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetListsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetRecommendationsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetReviewsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetScreenedTheatricallyResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetSeriesDetailsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetSimilarResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetVideosResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newGetWatchProvidersResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetAccountStatesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetAggregateCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetAlternativeTitlesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetChangesResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetContentRatingsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetEpisodeGroupsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetExternalIdsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetKeywordsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetListsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetRecommendationsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetReviewsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetScreenedTheatricallyResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetSeriesDetailsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetSimilarResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetVideosResponse;
import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataValidator.assertSameGetWatchProvidersResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TvSeriesApiFunctionalTest extends FunctionalTestBase {
    private TvSeriesApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getTvSeriesApi();
    }

    @Test
    public void getSeriesDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_SERIES_DETAILS_RESPONSE);
        final GetSeriesDetailsResponse expected = newGetSeriesDetailsResponse();

        final GetSeriesDetailsResponse actual = apiUnderTest.getSeriesDetails(
                GetSeriesDetailsRequest.builder()
                        .seriesId(50)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetSeriesDetailsResponse(expected, actual);
    }

    @Test
    public void getAccountStates_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ACCOUNT_STATES_RESPONSE);
        final GetAccountStatesResponse expected = newGetAccountStatesResponse();

        final GetAccountStatesResponse actual = apiUnderTest.getAccountStates(
                GetAccountStatesRequest.builder()
                        .seriesId(50)
                        .sessionId("sessionIdValue")
                        .build());

        assertSameGetAccountStatesResponse(expected, actual);
    }

    @Test
    public void getAggregateCredits_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_AGGREGATE_CREDITS_RESPONSE);
        final GetAggregateCreditsResponse expected = newGetAggregateCreditsResponse();

        final GetAggregateCreditsResponse actual = apiUnderTest.getAggregateCredits(
                GetAggregateCreditsRequest.builder()
                        .seriesId(500)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetAggregateCreditsResponse(expected, actual);
    }

    @Test
    public void getAlternativeTitles_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ALTERNATIVE_TITLES_RESPONSE);
        final GetAlternativeTitlesResponse expected = newGetAlternativeTitlesResponse();

        final GetAlternativeTitlesResponse actual = apiUnderTest.getAlternativeTitles(
                GetAlternativeTitlesRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetAlternativeTitlesResponse(expected, actual);
    }

    @Test
    public void getChanges_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CHANGES_RESPONSE);
        final GetChangesResponse expected = newGetChangesResponse();

        final GetChangesResponse actual = apiUnderTest.getChanges(
                GetChangesRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetChangesResponse(expected, actual);
    }

    @Test
    public void getContentRatings_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CONTENT_RATINGS_RESPONSE);
        final GetContentRatingsResponse expected = newGetContentRatingsResponse();

        final GetContentRatingsResponse actual = apiUnderTest.getContentRatings(
                GetContentRatingsRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetContentRatingsResponse(expected, actual);
    }

    @Test
    public void getCredits_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CREDITS_RESPONSE);
        final GetCreditsResponse expected = newGetCreditsResponse();

        final GetCreditsResponse actual = apiUnderTest.getCredits(
                GetCreditsRequest.builder()
                        .seriesId(500)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetCreditsResponse(expected, actual);
    }

    @Test
    public void getEpisodesGroups_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_EPISODE_GROUPS_RESPONSE);
        final GetEpisodeGroupsResponse expected = newGetEpisodeGroupsResponse();

        final GetEpisodeGroupsResponse actual = apiUnderTest.getEpisodesGroups(
                GetEpisodeGroupsRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetEpisodeGroupsResponse(expected, actual);
    }

    @Test
    public void getExternalIds_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_EXTERNAL_IDS_RESPONSE);
        final GetExternalIdsResponse expected = newGetExternalIdsResponse();

        final GetExternalIdsResponse actual = apiUnderTest.getExternalIds(
                GetExternalIdsRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetExternalIdsResponse(expected, actual);
    }

    @Test
    public void getImages_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_IMAGES_RESPONSE);
        final GetImagesResponse expected = newGetImagesResponse();

        final GetImagesResponse actual = apiUnderTest.getImages(
                GetImagesRequest.builder()
                        .seriesId(500)
                        .language(Locale.US.getLanguage())
                        .build());
    }

    @Test
    public void getKeywords_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_KEYWORDS_RESPONSE);
        final GetKeywordsResponse expected = newGetKeywordsResponse();

        final GetKeywordsResponse actual = apiUnderTest.getKeywords(
                GetKeywordsRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetKeywordsResponse(expected, actual);
    }

    @Test
    public void getLatest_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_LATEST_RESPONSE);
        final GetSeriesDetailsResponse expected = newGetSeriesDetailsResponse();

        final GetSeriesDetailsResponse actual = apiUnderTest.getLatest();

        assertSameGetSeriesDetailsResponse(expected, actual);
    }

    @Test
    public void getLists_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_LISTS_RESPONSE);
        final GetListsResponse expected = newGetListsResponse();

        final GetListsResponse actual = apiUnderTest.getLists(
                GetListsRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetListsResponse(expected, actual);
    }

    @Test
    public void getRecommendations_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_RECOMMENDATIONS_RESPONSE);
        final GetRecommendationsResponse expected = newGetRecommendationsResponse();

        final GetRecommendationsResponse actual = apiUnderTest.getRecommendations(
                GetRecommendationsRequest.builder()
                        .seriesId(500)
                        .language(Locale.US.getLanguage())
                        .page(1)
                        .build());

        assertSameGetRecommendationsResponse(expected, actual);
    }

    @Test
    public void getReviews_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_REVIEWS_RESPONSE);
        final GetReviewsResponse expected = newGetReviewsResponse();

        final GetReviewsResponse actual = apiUnderTest.getReviews(GetReviewsRequest.builder()
                .page(1)
                .language(Locale.US.getLanguage())
                .seriesId(500)
                .build());

        assertSameGetReviewsResponse(expected, actual);
    }

    @Test
    public void getScreenedTheatrically_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_SCREENED_THREATRICALLY_RESPONSE);
        final GetScreenedTheatricallyResponse expected = newGetScreenedTheatricallyResponse();

        final GetScreenedTheatricallyResponse actual = apiUnderTest.getScreenedTheatrically(
                GetScreenedTheatricallyRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetScreenedTheatricallyResponse(expected, actual);
    }

    @Test
    public void getSimilar_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_SIMILAR_RESPONSE);
        final GetSimilarResponse expected = newGetSimilarResponse();

        final GetSimilarResponse actual = apiUnderTest.getSimilar(
                GetSimilarRequest.builder()
                        .seriesId(500)
                        .language(Locale.US.getLanguage())
                        .page(1)
                        .build());

        assertSameGetSimilarResponse(expected, actual);
    }

    @Test
    public void getTranslations_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TRANSLATIONS_RESPONSE);
        final GetTranslationsResponse expected = newGetTranslationsResponse();

        final GetTranslationsResponse actual = apiUnderTest.getTranslations(
                GetTranslationsRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetTranslationsResponse(expected, actual);
    }

    @Test
    public void getVideos_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_VIDEOS_RESPONSE);
        final GetVideosResponse expected = newGetVideosResponse();

        final GetVideosResponse actual = apiUnderTest.getVideos(
                GetVideosRequest.builder()
                        .seriesId(500)
                        .language(Locale.US.getLanguage())
                        .includeVideoLanguage(Locale.US.getLanguage())
                        .build());

        assertSameGetVideosResponse(expected, actual);
    }

    @Test
    public void getWatchProviders_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_WATCH_PROVIDERS_RESPONSE);
        final GetWatchProvidersResponse expected = newGetWatchProvidersResponse();

        final GetWatchProvidersResponse actual = apiUnderTest.getWatchProviders(
                GetWatchProvidersRequest.builder()
                        .seriesId(500)
                        .build());

        assertSameGetWatchProvidersResponse(expected, actual);
    }

    @Test
    public void addRating_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, ADD_RATING_RESPONSE);
        final AddRatingResponse expected = newAddRatingResponse();

        final AddRatingResponse actual = apiUnderTest.addRating(AddRatingRequest.builder()
                .sessionId("SessionIdValue")
                .seriesId(1)
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
                .seriesId(1)
                .build());

        assertEquals(expected, actual);
    }
}
