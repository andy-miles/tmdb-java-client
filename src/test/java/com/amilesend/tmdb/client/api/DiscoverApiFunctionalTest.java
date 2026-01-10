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
import com.amilesend.tmdb.client.model.discover.DiscoverMoviesRequest;
import com.amilesend.tmdb.client.model.discover.DiscoverMoviesResponse;
import com.amilesend.tmdb.client.model.discover.DiscoverTvRequest;
import com.amilesend.tmdb.client.model.discover.DiscoverTvResponse;
import com.amilesend.tmdb.client.model.discover.filter.FilterQueryBuilder;
import com.amilesend.tmdb.client.model.discover.filter.RegionFilter;
import com.amilesend.tmdb.client.model.discover.filter.WatchRegionFilter;
import com.amilesend.tmdb.client.model.discover.type.MonetizationType;
import com.amilesend.tmdb.client.model.discover.type.MovieReleaseType;
import com.amilesend.tmdb.client.model.discover.type.SortBy;
import com.amilesend.tmdb.client.model.discover.type.TvStatus;
import com.amilesend.tmdb.client.model.discover.type.TvType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static com.amilesend.tmdb.client.data.discover.DiscoverApiDataHelper.Responses.DISCOVER_MOVIES_RESPONSE;
import static com.amilesend.tmdb.client.data.discover.DiscoverApiDataHelper.Responses.DISCOVER_TV_RESPONSE;
import static com.amilesend.tmdb.client.data.discover.DiscoverApiDataHelper.newDiscoverMoviesResponse;
import static com.amilesend.tmdb.client.data.discover.DiscoverApiDataHelper.newDiscoverTvResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscoverApiFunctionalTest extends FunctionalTestBase {
    private DiscoverApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getDiscoverApi();
    }

    @Test
    public void discoverMovies_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, DISCOVER_MOVIES_RESPONSE);
        final DiscoverMoviesResponse expected = newDiscoverMoviesResponse();

        final DiscoverMoviesResponse actual = apiUnderTest.discoverMovies(
                DiscoverMoviesRequest.builder()
                        .page(1)
                        .regionFilter(RegionFilter.builder()
                                .region(Locale.US.getCountry())
                                .certificationCountry(Locale.US.getCountry())
                                .certification("PG-13")
                                .build())
                        .includeAdult(true)
                        .primaryReleaseYear(2000)
                        .sortBy(SortBy.ORIGINAL_TITLE_ASC)
                        .withCast(new FilterQueryBuilder<>("Cast1", FilterQueryBuilder.Type.AND)
                                .append("Cast2")
                                .build())
                        .withGenres(new FilterQueryBuilder<>("drama", FilterQueryBuilder.Type.OR)
                                .append("horror")
                                .build())
                        .withOriginCountry(Locale.US.getCountry())
                        .withOriginalLanguage(Locale.US.getLanguage())
                        .withReleaseType(
                                new FilterQueryBuilder<>(
                                        MovieReleaseType.THEATRICAL,
                                        FilterQueryBuilder.Type.OR)
                                        .append(MovieReleaseType.DIGITAL)
                                        .build())
                        .watchRegionFilter(WatchRegionFilter.builder()
                                .watchRegion(Locale.US.getCountry())
                                .withWatchProviders(
                                        new FilterQueryBuilder<>("netflix", FilterQueryBuilder.Type.AND)
                                                .append("hulu")
                                                .build())
                                .withoutWatchProviders(
                                        new FilterQueryBuilder<>("amazon", FilterQueryBuilder.Type.AND)
                                                .build())
                                .withWatchMonetizationTypes(
                                        new FilterQueryBuilder<>(MonetizationType.FLAT_RATE, FilterQueryBuilder.Type.OR)
                                                .append(MonetizationType.BUY)
                                                .build())
                                .build())
                        .build());

        assertEquals(expected, actual);
    }

    @Test
    public void discoverTv_withValidRequest_shouldReturnResult() {
        setUpMockResponse(SUCCESS_STATUS_CODE, DISCOVER_TV_RESPONSE);
        final DiscoverTvResponse expected = newDiscoverTvResponse();

        final DiscoverTvResponse actual = apiUnderTest.discoverTv(
                DiscoverTvRequest.builder()
                        .page(1)
                        .includeAdult(true)
                        .withGenres(new FilterQueryBuilder<>("drama", FilterQueryBuilder.Type.OR)
                                .append("horror")
                                .build())
                        .withOriginCountry(Locale.US.getCountry())
                        .withOriginalLanguage(Locale.US.getLanguage())
                        .airDateGte(LocalDate.of(2023, 9, 1))
                        .withStatus(new FilterQueryBuilder<>(TvStatus.CANCELED, FilterQueryBuilder.Type.OR)
                                .append(TvStatus.ENDED)
                                .build())
                        .withType(new FilterQueryBuilder<>(TvType.MINISERIES, FilterQueryBuilder.Type.OR)
                                .append(TvType.SCRIPTED)
                                .build())
                        .build());

        assertEquals(expected, actual);
    }
}
