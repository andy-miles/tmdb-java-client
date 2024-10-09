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
package com.amilesend.tmdb.client.model.tv.seasons.type;

import com.amilesend.tmdb.client.model.PathParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import com.amilesend.tmdb.client.parse.strategy.GsonExclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.Validate;

/**
 * The base class for TV seasons API based requests.
 *
 * @see QueryParameterBasedRequest
 */
@SuperBuilder
@Data
public class SeasonRequestBase implements QueryParameterBasedRequest {
    /** The TV series identifier (required). */
    @GsonExclude
    @PathParameter
    private final int seriesId;
    /** The season number (required). */
    @GsonExclude
    @PathParameter
    private final int seasonNumber;

    @Override
    public HttpUrl.Builder populateQueryParameters(final HttpUrl.Builder urlBuilder) {
        Validate.isTrue(seriesId > 0, "seriesId must be > 0");
        Validate.isTrue(seasonNumber > 0, "seasonNumber must be > 0");
        return urlBuilder;
    }
}
