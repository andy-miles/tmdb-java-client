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

import com.amilesend.client.connection.Connection;
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
import com.amilesend.tmdb.client.model.tv.series.type.SeriesRequestBase;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/** TMDB TV Series API. */
public class TvSeriesApi extends ApiBase {
    private static final String API_PATH = "/tv/";

    /**
     * Creates a new {@code TvSeriesApi} object.
     *
     * @param connection the connection
     */
    public TvSeriesApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the details for a TV series
     *
     * @param request the request
     * @return the response
     * @see GetSeriesDetailsRequest
     * @see GetSeriesDetailsResponse
     */
    public GetSeriesDetailsResponse getSeriesDetails(@NonNull final GetSeriesDetailsRequest request) {
        return executeGet(getApiPath(request, StringUtils.EMPTY), request, GetSeriesDetailsResponse.class);
    }

    /**
     * Gets the associated account states for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetAccountStatesRequest
     * @see GetAccountStatesResponse
     */
    public GetAccountStatesResponse getAccountStates(@NonNull final GetAccountStatesRequest request) {
        return executeGet(getApiPath(request, "/account_states"), request, GetAccountStatesResponse.class);
    }

    /**
     * Gets the associated credits for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetAggregateCreditsRequest
     * @see GetAggregateCreditsResponse
     */
    public GetAggregateCreditsResponse getAggregateCredits(@NonNull final GetAggregateCreditsRequest request) {
        return executeGet(getApiPath(request, "/aggregate_credits"), request, GetAggregateCreditsResponse.class);
    }

    /**
     * Gets the list of alternative titles for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetAlternativeTitlesRequest
     * @see GetAlternativeTitlesResponse
     */
    public GetAlternativeTitlesResponse getAlternativeTitles(@NonNull final GetAlternativeTitlesRequest request) {
        return executeGet(
                getApiPath(request, "/alternative_titles"),
                request,
                GetAlternativeTitlesResponse.class);
    }

    /**
     * Gets the changes for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetChangesRequest
     * @see GetChangesResponse
     */
    public GetChangesResponse getChanges(@NonNull final GetChangesRequest request) {
        final String apiPath = new StringBuilder("/tv/")
                .append(request.getSeriesId())
                .append("/changes")
                .toString();
        return executeGet(apiPath, request, GetChangesResponse.class);
    }

    /**
     * Gets the content ratings for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetContentRatingsRequest
     * @see GetContentRatingsResponse
     */
    public GetContentRatingsResponse getContentRatings(@NonNull final GetContentRatingsRequest request) {
        return executeGet(getApiPath(request, "/content_ratings"), request, GetContentRatingsResponse.class);
    }

    /**
     * Gets the credits for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetCreditsRequest
     * @see GetCreditsResponse
     */
    public GetCreditsResponse getCredits(@NonNull final GetCreditsRequest request) {
        return executeGet(getApiPath(request, "/credits"), request, GetCreditsResponse.class);
    }

    /**
     * Gets the episode groups for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetEpisodeGroupsRequest
     * @see GetEpisodeGroupsResponse
     */
    public GetEpisodeGroupsResponse getEpisodesGroups(@NonNull final GetEpisodeGroupsRequest request) {
        return executeGet(getApiPath(request, "/episode_groups"), request, GetEpisodeGroupsResponse.class);
    }

    /**
     * Gets the external identifiers for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetExternalIdsRequest
     * @see GetExternalIdsResponse
     */
    public GetExternalIdsResponse getExternalIds(@NonNull final GetExternalIdsRequest request) {
        return executeGet(getApiPath(request, "/external_ids"), request, GetExternalIdsResponse.class);
    }

    /**
     * Gets the images associated with a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetImagesRequest
     * @see GetImagesResponse
     */
    public GetImagesResponse getImages(@NonNull final GetImagesRequest request) {
        return executeGet(getApiPath(request, "/images"), request, GetImagesResponse.class);
    }

    /**
     * Gets the keywords associated with a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetKeywordsRequest
     * @see GetKeywordsResponse
     */
    public GetKeywordsResponse getKeywords(@NonNull final GetKeywordsRequest request) {
        return executeGet(getApiPath(request, "/keywords"), request, GetKeywordsResponse.class);
    }

    /**
     * Gets the latest TV series details.
     *
     * @return the response
     * @see GetSeriesDetailsResponse
     */
    public GetSeriesDetailsResponse getLatest() {
        return executeGet(API_PATH + "latest", GetSeriesDetailsResponse.class);
    }

    /**
     * Gets the lists for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetListsRequest
     * @see GetListsResponse
     */
    public GetListsResponse getLists(@NonNull final GetListsRequest request) {
        return executeGet(getApiPath(request, "/lists"), request, GetListsResponse.class);
    }

    /**
     * Gets the list of recommendations associated with a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetRecommendationsRequest
     * @see GetRecommendationsResponse
     */
    public GetRecommendationsResponse getRecommendations(@NonNull final GetRecommendationsRequest request) {
        return executeGet(getApiPath(request, "/recommendations"), request, GetRecommendationsResponse.class);
    }

    /**
     * Gets the reviews for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetReviewsRequest
     * @see GetReviewsResponse
     */
    public GetReviewsResponse getReviews(@NonNull final GetReviewsRequest request) {
        return executeGet(getApiPath(request, "/reviews"), request, GetReviewsResponse.class);
    }

    /**
     * Gets the list of TV series episodes that were screened theatrically for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetScreenedTheatricallyRequest
     * @see GetScreenedTheatricallyResponse
     */
    public GetScreenedTheatricallyResponse getScreenedTheatrically(
            @NonNull final GetScreenedTheatricallyRequest request) {
        return executeGet(
                getApiPath(request, "/screened_theatrically"),
                request,
                GetScreenedTheatricallyResponse.class);
    }

    /**
     * Gets a list of TV series that are similar to the given TV series.
     *
     * @param request the request
     * @return the response
     * @see GetSimilarRequest
     * @see GetSimilarResponse
     */
    public GetSimilarResponse getSimilar(@NonNull final GetSimilarRequest request) {
        return executeGet(getApiPath(request, "/similar"), request, GetSimilarResponse.class);
    }

    /**
     * Gets a list of translations for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetTranslationsRequest
     * @see GetTranslationsResponse
     */
    public GetTranslationsResponse getTranslations(@NonNull final GetTranslationsRequest request) {
        return executeGet(getApiPath(request, "/translations"), request, GetTranslationsResponse.class);
    }

    /**
     * Gets a list of related videos for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetVideosRequest
     * @see GetVideosResponse
     */
    public GetVideosResponse getVideos(@NonNull final GetVideosRequest request) {
        return executeGet(getApiPath(request, "/videos"), request, GetVideosResponse.class);
    }

    /**
     * Gets the list of streaming providers for a TV series.
     *
     * @param request the request
     * @return the response
     * @see GetWatchProvidersRequest
     * @see GetWatchProvidersResponse
     */
    public GetWatchProvidersResponse getWatchProviders(@NonNull final GetWatchProvidersRequest request) {
        return executeGet(getApiPath(request, "/watch/providers"), request, GetWatchProvidersResponse.class);
    }

    /**
     * Adds a rating for the TV series.
     *
     * @param request the request
     * @return the response
     * @see AddRatingRequest
     * @see AddRatingResponse
     */
    public AddRatingResponse addRating(@NonNull final AddRatingRequest request) {
        return executePost(getApiPath(request, "/rating"), request, AddRatingResponse.class);
    }

    /**
     * Deletes a rating for a TV series.
     *
     * @param request the request
     * @return the response
     * @see DeleteRatingRequest
     * @see DeleteRatingResponse
     */
    public DeleteRatingResponse deleteRating(@NonNull final DeleteRatingRequest request) {
        return executeDelete(getApiPath(request, "/rating"), request, DeleteRatingResponse.class);
    }

    private static String getApiPath(final SeriesRequestBase request, final String suffix) {
        return new StringBuilder(API_PATH)
                .append(request.getSeriesId())
                .append(suffix)
                .toString();
    }
}
