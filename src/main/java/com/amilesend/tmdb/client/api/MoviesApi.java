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

import com.amilesend.client.connection.Connection;
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
import com.amilesend.tmdb.client.model.movie.type.MovieRequestBase;
import lombok.NonNull;

/** The TMDB Movies API. */
public class MoviesApi extends ApiBase {
    private static String API_PATH = "/movie/";

    /**
     * Creates a new {@code MoviesApi} object.
     *
     * @param connection the connection
     */
    public MoviesApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the details for a movie.
     *
     * @param request the request
     * @return the response
     * @see GetMovieDetailsRequest
     * @see GetMovieDetailsResponse
     */
    public GetMovieDetailsResponse getMovieDetails(@NonNull final GetMovieDetailsRequest request) {
        return executeGet(API_PATH + request.getMovieId(), request, GetMovieDetailsResponse.class);
    }

    /**
     * Gets the account states for a movie.
     *
     * @param request the request
     * @return the response
     * @see GetAccountStatesRequest
     * @see GetAccountStatesResponse
     */
    public GetAccountStatesResponse getAccountStates(@NonNull final GetAccountStatesRequest request) {
        return executeGet(
                getApiPath(request, "/account_states"),
                request,
                GetAccountStatesResponse.class);
    }

    /**
     * Gets the list of alternative titles for a movie.
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
     * Gets the changes for a movie.
     *
     * @param request the request
     * @return the response
     * @see GetChangesRequest
     * @see GetChangesResponse
     */
    public GetChangesResponse getChanges(@NonNull final GetChangesRequest request) {
        final String apiPath = new StringBuilder(API_PATH)
                .append(request.getMovieId())
                .append("/changes")
                .toString();
        return executeGet(apiPath, request, GetChangesResponse.class);
    }

    /**
     * Gets the credits for a movie.
     *
     * @param request the request
     * @return the response
     * @see GetCreditsRequest
     * @see GetCreditsResponse
     */
    public GetCreditsResponse getCredits(@NonNull final GetCreditsRequest request) {
        return executeGet(
                getApiPath(request, "/credits"),
                request,
                GetCreditsResponse.class);
    }

    /**
     * Gets the external identifiers for a movie.
     *
     * @param request the request
     * @return the response
     * @see GetExternalIdsRequest
     * @see GetExternalIdsResponse
     */
    public GetExternalIdsResponse getExternalIds(@NonNull final GetExternalIdsRequest request) {
        return executeGet(
                getApiPath(request, "/external_ids"),
                request,
                GetExternalIdsResponse.class);
    }

    /**
     * Gets the images for a movie.
     *
     * @param request the request
     * @return the response
     * @see GetImagesRequest
     * @see GetImagesResponse
     */
    public GetImagesResponse getImages(@NonNull final GetImagesRequest request) {
        return executeGet(
                getApiPath(request, "/images"),
                request,
                GetImagesResponse.class);
    }

    /**
     * Gets the keywords associated with a movie.
     *
     * @param request the request
     * @return the response
     * @see GetKeywordsRequest
     * @see GetKeywordsResponse
     */
    public GetKeywordsResponse getKeywords(@NonNull final GetKeywordsRequest request) {
        return executeGet(
                getApiPath(request, "/keywords"),
                request,
                GetKeywordsResponse.class);
    }

    /**
     * Gets the latest movie.
     *
     * @return the response
     * @see GetLatestResponse
     */
    public GetLatestResponse getLatest() {
        return executeGet(API_PATH + "latest", GetLatestResponse.class);
    }


    /**
     * Gets the lists for a movie.
     *
     * @param request the request
     * @return the response
     * @see GetListsRequest
     * @see GetListsResponse
     */
    public GetListsResponse getLists(@NonNull final GetListsRequest request) {
        return executeGet(
                getApiPath(request, "/lists"),
                request,
                GetListsResponse.class);
    }

    /**
     * Gets the list of recommendations associated with a movie.
     *
     * @param request the request
     * @return the response
     * @see GetRecommendationsRequest
     * @see GetRecommendationsResponse
     */
    public GetRecommendationsResponse getRecommendations(@NonNull final GetRecommendationsRequest request) {
        return executeGet(
                getApiPath(request, "/recommendations"),
                request,
                GetRecommendationsResponse.class);
    }

    /**
     * Gets the release dates for a movie.
     *
     * @param request the request
     * @return the response
     * @see GetReleaseDatesRequest
     * @see GetReleaseDatesResponse
     */
    public GetReleaseDatesResponse getReleaseDates(@NonNull final GetReleaseDatesRequest request) {
        return executeGet(
                getApiPath(request, "/release_dates"),
                request,
                GetReleaseDatesResponse.class);
    }

    /**
     * Gets the reviews for a movie.
     *
     * @param request the request
     * @return the response
     * @see GetReviewsRequest
     * @see GetReviewsResponse
     */
    public GetReviewsResponse getReviews(@NonNull final GetReviewsRequest request) {
        return executeGet(
                getApiPath(request, "/reviews"),
                request,
                GetReviewsResponse.class);
    }

    /**
     * Gets a list of similar movies for a movie.
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
     * Gets the translation information for a movie.
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
     * Gets a list of related videos for a movie.
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
     * Gets the list of streaming providers for a movie.
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
     * Adds a rating for the movie.
     *
     * @param request the request
     * @return the response
     * @see AddRatingRequest
     * @see AddRatingResponse
     */
    public AddRatingResponse addRating(@NonNull final AddRatingRequest request) {
        return executePost(getApiPath(request, "/rating"), request,AddRatingResponse.class);
    }

    /**
     * Deletes a rating for a movie.
     *
     * @param request the request
     * @return the response
     * @see DeleteRatingRequest
     * @see DeleteRatingResponse
     */
    public DeleteRatingResponse deleteRating(@NonNull final DeleteRatingRequest request) {
        return executeDelete(getApiPath(request, "/rating"), request, DeleteRatingResponse.class);
    }

    private static String getApiPath(final MovieRequestBase request, final String subApiPath) {
        return new StringBuilder(API_PATH)
                .append(request.getMovieId())
                .append(subApiPath)
                .toString();
    }
}
