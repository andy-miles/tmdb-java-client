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
package com.amilesend.tmdb.client.model.discover.type;

import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import com.amilesend.tmdb.client.model.discover.filter.WatchRegionFilter;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

import java.util.Objects;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;
import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotNull;

/**
 * Defines the base class for discover-based requests.
 *
 * @see QueryParameterBasedRequest
 */
@SuperBuilder
@Data
public abstract class DiscoverRequestBase implements QueryParameterBasedRequest {
    /** Filter on adult content. */
    @QueryParameter
    private final Boolean includeAdult;
    /** Filter by language */
    @QueryParameter
    private final String language;
    /** The page number. */
    @QueryParameter
    private final Integer page;
    /** Sort by specifier. Default is {@link SortBy#POPULARITY_ASC}. */
    @QueryParameter
    private final SortBy sortBy;
    /** Filter by vote average comparison {@code (>=)}. */
    @QueryParameter
    private final Double voteAverageGte;
    /** Filter by vote average comparison {@code (<=)}. */
    @QueryParameter
    private final Double voteAverageLte;
    /** Filter by vote count comparison {@code (>=)}. */
    @QueryParameter
    private final Double voteCountGte;
    /** Filter by vote count comparison {@code (<=)}. */
    @QueryParameter
    private final Double voteCountLte;
    /** Filter based on watch region and associated attributes. */
    @QueryParameter
    private final WatchRegionFilter watchRegionFilter;
    /** Filter on a delimited list of companies. */
    @QueryParameter
    private final String withCompanies;
    /** Filter on a delimited list of genres. */
    @QueryParameter
    private final String withGenres;
    /** Filter on a delimited list of keywords. */
    @QueryParameter
    private final String withKeywords;
    /** Filter on the origin country. */
    @QueryParameter
    private final String withOriginCountry;
    /** Filter on the original language. */
    @QueryParameter
    private final String withOriginalLanguage;
    /** Filter on runtime comparison {@code (>=)}. */
    @QueryParameter
    private final Integer withRuntimeGte;
    /** Filter on runtime comparison {@code (<=)}. */
    @QueryParameter
    private final Integer withRuntimeLte;
    /** Filter on excluding a delimited list of companies. */
    @QueryParameter
    private final String withoutCompanies;
    /** Filter on excluding a delimited list of genres. */
    @QueryParameter
    private final String withoutGenres;
    /** Filter on excluding a delimited list of keywords. */
    @QueryParameter
    private final String withoutKeywords;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull HttpUrl.Builder urlBuilder) {
        appendIfNotNull(urlBuilder, "page", page);
        appendIfNotNull(urlBuilder, "include_adult", includeAdult);
        appendIfNotBlank(urlBuilder, "language", language);
        appendIfNotNull(urlBuilder, "sort_by", sortBy);
        appendIfNotNull(urlBuilder, "vote_average.gte", voteAverageGte);
        appendIfNotNull(urlBuilder, "vote_average.lte", voteAverageLte);
        appendIfNotNull(urlBuilder, "vote_count.gte", voteCountGte);
        appendIfNotNull(urlBuilder, "vote_count.lte", voteCountLte);

        if (Objects.nonNull(watchRegionFilter)) {
            urlBuilder = watchRegionFilter.populateQueryParameters(urlBuilder);
        }

        appendIfNotBlank(urlBuilder, "with_companies", withCompanies);
        appendIfNotBlank(urlBuilder, "with_genres", withGenres);
        appendIfNotBlank(urlBuilder, "with_keywords", withKeywords);
        appendIfNotBlank(urlBuilder, "with_origin_country", withOriginCountry);
        appendIfNotBlank(urlBuilder, "with_original_language", withOriginalLanguage);
        appendIfNotNull(urlBuilder, "with_runtime.gte", withRuntimeGte);
        appendIfNotNull(urlBuilder, "with_runtime.lte", withRuntimeLte);
        appendIfNotBlank(urlBuilder, "without_companies", withoutCompanies);
        appendIfNotBlank(urlBuilder, "without_genres", withoutGenres);
        appendIfNotBlank(urlBuilder, "without_keywords", withoutKeywords);

        return urlBuilder;
    }
}
