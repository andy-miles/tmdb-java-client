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
package com.amilesend.tmdb.client.api;

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.tv.episodes.AddRatingRequest;
import com.amilesend.tmdb.client.model.tv.episodes.AddRatingResponse;
import com.amilesend.tmdb.client.model.tv.episodes.DeleteRatingRequest;
import com.amilesend.tmdb.client.model.tv.episodes.DeleteRatingResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetAccountStatesRequest;
import com.amilesend.tmdb.client.model.tv.episodes.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetChangesRequest;
import com.amilesend.tmdb.client.model.tv.episodes.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetCreditsRequest;
import com.amilesend.tmdb.client.model.tv.episodes.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetEpisodeDetailsRequest;
import com.amilesend.tmdb.client.model.tv.episodes.GetEpisodeDetailsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetExternalIdsRequest;
import com.amilesend.tmdb.client.model.tv.episodes.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetImagesRequest;
import com.amilesend.tmdb.client.model.tv.episodes.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetTranslationsRequest;
import com.amilesend.tmdb.client.model.tv.episodes.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.GetVideosRequest;
import com.amilesend.tmdb.client.model.tv.episodes.GetVideosResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;
import java.util.StringJoiner;

import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.ADD_RATING_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.DELETE_RATING_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.GET_ACCOUNT_STATES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.GET_CHANGES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.GET_CREDITS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.GET_EPISODES_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.GET_EXTERNAL_IDS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.GET_IMAGES_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.GET_TRANSLATIONS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.Responses.GET_VIDEOS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newAddRatingResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newDeleteRatingResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newGetAccountStatesResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newGetChangesResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newGetCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newGetEpisodeDetailsResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newGetExternalIdsResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newGetImagesResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataHelper.newGetVideosResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataValidator.assertSameGetAccountStatesResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataValidator.assertSameGetChangesResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataValidator.assertSameGetCreditsResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataValidator.assertSameGetEpisodeDetailsResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataValidator.assertSameGetExternalIdsResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataValidator.assertSameGetImagesResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataValidator.assertSameGetTranslationsResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodesApiDataValidator.assertSameGetVideosResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TvEpisodesApiFunctionalTest extends FunctionalTestBase {
    private TvEpisodesApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getTvEpisodesApi();
    }

    @Test
    public void getEpisodeDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_EPISODES_DETAILS_RESPONSE);
        final GetEpisodeDetailsResponse expected = newGetEpisodeDetailsResponse();

        final GetEpisodeDetailsResponse actual = apiUnderTest.getEpisodeDetails(
                GetEpisodeDetailsRequest.builder()
                        .episodeNumber(4)
                        .seasonNumber(1)
                        .seriesId(1)
                        .build());

        assertSameGetEpisodeDetailsResponse(expected, actual);
    }

    @Test
    public void getAccountStates_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_ACCOUNT_STATES_RESPONSE);
        final GetAccountStatesResponse expected = newGetAccountStatesResponse();

        final GetAccountStatesResponse actual = apiUnderTest.getAccountStates(
                GetAccountStatesRequest.builder()
                        .seriesId(1)
                        .seasonNumber(1)
                        .episodeNumber(4)
                        .sessionId("sessionIdValue")
                        .build());

        assertSameGetAccountStatesResponse(expected, actual);
    }

    @Test
    public void getChanges_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CHANGES_RESPONSE);
        final GetChangesResponse expected = newGetChangesResponse();

        final GetChangesResponse actual = apiUnderTest.getChanges(
                GetChangesRequest.builder()
                        .episodeId(4)
                        .startDate(LocalDate.of(2023, 1, 1))
                        .endDate(LocalDate.of(2024, 1, 1))
                        .build());

        assertSameGetChangesResponse(expected, actual);
    }

    @Test
    public void getCredits_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_CREDITS_RESPONSE);
        final GetCreditsResponse expected = newGetCreditsResponse();

        final GetCreditsResponse actual = apiUnderTest.getCredits(
                GetCreditsRequest.builder()
                        .seriesId(1)
                        .seasonNumber(1)
                        .episodeNumber(4)
                        .language(Locale.US.getLanguage())
                        .build());

        assertSameGetCreditsResponse(expected, actual);
    }

    @Test
    public void getExternalIds_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_EXTERNAL_IDS_RESPONSE);
        final GetExternalIdsResponse expected = newGetExternalIdsResponse();

        final GetExternalIdsResponse actual = apiUnderTest.getExternalIds(
                GetExternalIdsRequest.builder()
                        .seriesId(100)
                        .seasonNumber(1)
                        .episodeNumber(2)
                        .build());

        assertSameGetExternalIdsResponse(expected, actual);
    }

    @Test
    public void getImages_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_IMAGES_RESPONSE);
        final GetImagesResponse expected = newGetImagesResponse();

        final GetImagesResponse actual = apiUnderTest.getImages(
                GetImagesRequest.builder()
                        .seriesId(100)
                        .seasonNumber(1)
                        .episodeNumber(2)
                        .language(Locale.US.getLanguage())
                        .includeImageLanguage(
                                new StringJoiner(",")
                                        .add(Locale.US.getLanguage())
                                        .add((Locale.GERMAN.getLanguage()))
                                        .toString())
                        .build());

        assertSameGetImagesResponse(expected, actual);
    }

    @Test
    public void getTranslations_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TRANSLATIONS_RESPONSE);
        final GetTranslationsResponse expected = newGetTranslationsResponse();

        final GetTranslationsResponse actual = apiUnderTest.getTranslations(
                GetTranslationsRequest.builder()
                        .seriesId(100)
                        .seasonNumber(1)
                        .episodeNumber(2)
                        .build());

        assertSameGetTranslationsResponse(expected, actual);
    }

    @Test
    public void getVideos_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_VIDEOS_RESPONSE);
        final GetVideosResponse expected = newGetVideosResponse();

        final GetVideosResponse actual = apiUnderTest.getVideos(
                GetVideosRequest.builder()
                        .seriesId(100)
                        .seasonNumber(1)
                        .episodeNumber(2)
                        .language(Locale.US.getLanguage())
                        .includeVideoLanguage(
                                new StringJoiner(",")
                                        .add(Locale.US.getLanguage())
                                        .add((Locale.GERMAN.getLanguage()))
                                        .toString())
                        .build());

        assertSameGetVideosResponse(expected, actual);
    }

    @Test
    public void addRating_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, ADD_RATING_RESPONSE);
        final AddRatingResponse expected = newAddRatingResponse();

        final AddRatingResponse actual = apiUnderTest.addRating(AddRatingRequest.builder()
                .sessionId("SessionIdValue")
                .seriesId(100)
                .seasonNumber(1)
                .episodeNumber(2)
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
                .seriesId(100)
                .seasonNumber(1)
                .episodeNumber(2)
                .build());

        assertEquals(expected, actual);
    }
}
