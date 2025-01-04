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

import com.amilesend.tmdb.client.connection.Connection;
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
import com.amilesend.tmdb.client.model.tv.episodes.type.EpisodeRequestBase;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/** TMDB TV Episodes API. */
public class TvEpisodesApi extends ApiBase {
    private static final String API_PATH = "/tv/";
    private static final String SEASON_API_PATH = "/season/";
    private static final String EPISODE_API_PATH = "/episode/";


    /**
     * Creates a new {@code TvEpisodesApi} object.
     *
     * @param connection the connection
     */
    public TvEpisodesApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the details for a specific TV episode.
     *
     * @param request the request
     * @return the response
     * @see GetEpisodeDetailsRequest
     * @see GetEpisodeDetailsResponse
     */
    public GetEpisodeDetailsResponse getEpisodeDetails(@NonNull final GetEpisodeDetailsRequest request) {
        return executeGet(getApiPath(request, StringUtils.EMPTY), request, GetEpisodeDetailsResponse.class);
    }

    /**
     * Gets the associated account states for a specific TV episode.
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
     * Gets the changes for a specific TV episode.
     *
     * @param request the request
     * @return the response
     * @see GetChangesRequest
     * @see GetChangesResponse
     */
    public GetChangesResponse getChanges(@NonNull final GetChangesRequest request) {
        final String apiPath = new StringBuilder("/tv/episode/")
                .append(request.getEpisodeId())
                .append("/changes")
                .toString();
        return executeGet(apiPath, request, GetChangesResponse.class);
    }

    /**
     * Gets the credits for a specific TV episode.
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
     * Gets the external identifiers for a specific TV episode.
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
     * Gets the image stills associated with a specific TV episode.
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
     * Gets the translation information for a specific TV episode.
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
     * Gets the list of videos for a specific TV episode.
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
     * Adds a rating for a specific TV episode.
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
     * Deletes a rating for a specific TV episode.
     *
     * @param request the request
     * @return the response
     * @see DeleteRatingRequest
     * @see DeleteRatingResponse
     */
    public DeleteRatingResponse deleteRating(@NonNull final DeleteRatingRequest request) {
        return executeDelete(getApiPath(request, "/rating"), request, DeleteRatingResponse.class);
    }

    private static String getApiPath(final EpisodeRequestBase request, final String suffix) {
        return new StringBuilder(API_PATH)
                .append(request.getSeriesId())
                .append(SEASON_API_PATH)
                .append(request.getSeasonNumber())
                .append(EPISODE_API_PATH)
                .append(request.getEpisodeNumber())
                .append(suffix)
                .toString();
    }
}
