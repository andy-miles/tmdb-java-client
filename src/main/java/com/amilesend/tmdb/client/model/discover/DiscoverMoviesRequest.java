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
package com.amilesend.tmdb.client.model.discover;

import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import com.amilesend.tmdb.client.model.discover.filter.RegionFilter;
import com.amilesend.tmdb.client.model.discover.type.DiscoverRequestBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

import java.time.LocalDate;
import java.util.Objects;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;
import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotNull;

/**
 * The request to filter and discover movies.
 *
 * @see DiscoverRequestBase
 * @see QueryParameterBasedRequest
 */
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiscoverMoviesRequest extends DiscoverRequestBase {
    /** Defines the region and associated certification filters. */
    @QueryParameter
    private final RegionFilter regionFilter;
    /** Filter on video content. */
    @QueryParameter
    private final Boolean includeVideo;
    /** Filter on the primary release year. */
    @QueryParameter
    private final Integer primaryReleaseYear;
    /** Filter by the content primary release date comparison {@code (>=)}. */
    @QueryParameter
    private final LocalDate primaryReleaseDateGte;
    /** Filter by the content primary release date comparison {@code (<=)}. */
    @QueryParameter
    private final LocalDate primaryReleaseDateLte;
    /** Filter by the release date comparison {@code (>=)}. */
    @QueryParameter
    private final LocalDate releaseDateGte;
    /** Filter by the release date comparison {@code (<=)}. */
    @QueryParameter
    private final LocalDate releaseDateLte;
    /** Filter on a delimited list of cast members. */
    @QueryParameter
    private final String withCast;
    /** Filter on a delimited list of crew members. */
    @QueryParameter
    private final String withCrew;
    /** Filter on a delimited list of people. */
    @QueryParameter
    private final String withPeople;
    /** Filter on release type. */
    @QueryParameter
    private final String withReleaseType;
    /** Filter on year. */
    @QueryParameter
    private final Integer year;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull HttpUrl.Builder urlBuilder) {
        urlBuilder = super.populateQueryParameters(urlBuilder);

        if (Objects.nonNull(regionFilter)) {
            urlBuilder = regionFilter.populateQueryParameters(urlBuilder);
        }

        appendIfNotNull(urlBuilder, "include_video", includeVideo);
        appendIfNotNull(urlBuilder, "primary_release_year", primaryReleaseYear);
        appendIfNotNull(urlBuilder, "primary_release_date.gte", primaryReleaseDateGte);
        appendIfNotNull(urlBuilder, "primary_release_date.lte", primaryReleaseDateLte);
        appendIfNotNull(urlBuilder, "release_date.gte", releaseDateGte);
        appendIfNotNull(urlBuilder, "release_date.lte", releaseDateLte);
        appendIfNotBlank(urlBuilder, "with_cast", withCast);
        appendIfNotBlank(urlBuilder, "with_crew", withCrew);
        appendIfNotBlank(urlBuilder, "with_people", withPeople);
        appendIfNotBlank(urlBuilder, "with_release_type", withReleaseType);
        appendIfNotNull(urlBuilder, "year", year);

        return urlBuilder;
    }
}
