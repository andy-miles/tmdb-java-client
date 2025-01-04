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
package com.amilesend.tmdb.client.model.discover;

import com.amilesend.tmdb.client.model.QueryParameter;
import com.amilesend.tmdb.client.model.QueryParameterBasedRequest;
import com.amilesend.tmdb.client.model.discover.type.DiscoverRequestBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

import java.time.LocalDate;

import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;
import static com.amilesend.tmdb.client.model.QueryParameterBasedRequest.appendIfNotNull;

/**
 * The request to filter and discover TV shows.
 *
 * @see DiscoverRequestBase
 * @see QueryParameterBasedRequest
 */
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiscoverTvRequest extends DiscoverRequestBase implements QueryParameterBasedRequest {
    /** Filter on TV shows with an air date greater than or equal ({@code >=}). */
    @QueryParameter
    private final LocalDate airDateGte;
    /** Filter on TV shows with an air date less than or equal ({@code <=}). */
    @QueryParameter
    private final LocalDate airDateLte;
    /** Filter on the initial air date year. */
    @QueryParameter
    private final Integer firstAirDateYear;
    /** Filter on the initial air date greater than or equal ({@code >=}). */
    @QueryParameter
    private final LocalDate firstAirDateGte;
    /** Filter on the initial air date less than or equal ({@code <=}). */
    @QueryParameter
    private final LocalDate firstAirDateLte;
    /** Flag indicator to include undefined first air dates. */
    @QueryParameter
    private final Boolean includeNullFirstAirDates;
    /** Flag indicator to include tv shows that have been screened in theaters. */
    @QueryParameter
    private final Boolean screenedTheatrically;
    /** Filter on timezone. */
    @QueryParameter
    private final String timezone;
    /** Filter on networks. */
    @QueryParameter
    private final String withNetworks;
    /**
     * Filter on status.
     *
     * @see com.amilesend.tmdb.client.model.discover.type.TvStatus
     */
    @QueryParameter
    private final String withStatus;
    /**
     * Filter on type.
     *
     * @see com.amilesend.tmdb.client.model.discover.type.TvType
     */
    @QueryParameter
    private final String withType;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull HttpUrl.Builder urlBuilder) {
        urlBuilder = super.populateQueryParameters(urlBuilder);

        appendIfNotNull(urlBuilder, "air_date.gte", airDateGte);
        appendIfNotNull(urlBuilder, "air_date.lte", airDateLte);
        appendIfNotNull(urlBuilder, "first_air_date_year", firstAirDateYear);
        appendIfNotNull(urlBuilder, "first_air_date.gte", firstAirDateGte);
        appendIfNotNull(urlBuilder, "first_air_date.lte", firstAirDateLte);
        appendIfNotNull(urlBuilder, "include_null_first_air_dates", includeNullFirstAirDates);
        appendIfNotNull(urlBuilder, "screened_theatrically", screenedTheatrically);
        appendIfNotBlank(urlBuilder, "timezone", timezone);
        appendIfNotBlank(urlBuilder, "with_networks", withNetworks);
        appendIfNotBlank(urlBuilder, "with_status", withStatus);
        appendIfNotBlank(urlBuilder, "with_type", withType);

        return urlBuilder;
    }
}
