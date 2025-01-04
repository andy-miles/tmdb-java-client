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
import com.amilesend.tmdb.client.model.tv.seasons.GetAccountStatesRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetAccountStatesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetAggregateCreditsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetAggregateCreditsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetChangesRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetChangesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetCreditsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetCreditsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetExternalIdsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetExternalIdsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetImagesRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetImagesResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetSeasonDetailsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetSeasonDetailsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetTranslationsRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.tv.seasons.GetWatchProvidersRequest;
import com.amilesend.tmdb.client.model.tv.seasons.GetWatchProvidersResponse;
import com.amilesend.tmdb.client.model.tv.seasons.type.SeasonRequestBase;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/** TMDB TV Seasons API. */
public class TvSeasonsApi extends ApiBase {
    private static final String TV_API_PATH = "/tv/";
    private static final String SEASON_API_PATH = "/season/";

    /**
     * Creates a new {@code TvSeasonsApi} object.
     *
     * @param connection the connection
     */
    public TvSeasonsApi(final Connection connection) {
        super(connection);
    }

    /**
     * Gets the details for a specific TV season.
     *
     * @param request the request
     * @return the response
     * @see GetSeasonDetailsRequest
     * @see GetSeasonDetailsResponse
     */
    public GetSeasonDetailsResponse getSeasonDetails(@NonNull final GetSeasonDetailsRequest request) {
        return executeGet(getApiPath(request, StringUtils.EMPTY), request, GetSeasonDetailsResponse.class);
    }

    /**
     * Gets the associated account states for a specific TV season.
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
     * Gets the associated credits for a specific TV season.
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
     * Gets the changes for a specific TV season.
     *
     * @param request the request
     * @return the response
     * @see GetChangesRequest
     * @see GetChangesResponse
     */
    public GetChangesResponse getChanges(@NonNull final GetChangesRequest request) {
        final String apiPath = new StringBuilder("/tv/season/")
                .append(request.getSeasonId())
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
     * Gets the external identifiers for a specific TV season.
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
     * Gets the image stills associated with a specific TV season.
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
     * Gets the translation information for a specific TV season.
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

    private static String getApiPath(final SeasonRequestBase request, final String suffix) {
        return new StringBuilder(TV_API_PATH)
                .append(request.getSeriesId())
                .append(SEASON_API_PATH)
                .append(request.getSeasonNumber())
                .append(suffix)
                .toString();
    }
}
