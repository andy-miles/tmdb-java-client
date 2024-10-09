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
package com.amilesend.tmdb.client.model.tv.episodes;

import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.tv.episodes.type.EpisodeRequestBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;

/**
 * The request to retrieve related videos for a TV episode.
 *
 * @see EpisodeRequestBase
 */
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetVideosRequest extends EpisodeRequestBase {
    /** A comma-delimited list of languages to filter the results by (optional). */
    @QueryParameter
    private final String includeVideoLanguage;
    /** The language (optional). */
    @QueryParameter
    private final String language;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull HttpUrl.Builder urlBuilder) {
        urlBuilder = super.populateQueryParameters(urlBuilder);

        appendIfNotBlank(urlBuilder, "include_video_language", includeVideoLanguage);
        appendIfNotBlank(urlBuilder, "language", language);

        return urlBuilder;
    }
}
