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

import com.amilesend.tmdb.client.FunctionalTestBase;
import com.amilesend.tmdb.client.model.tv.episodes.groups.GetTvEpisodeGroupDetailsRequest;
import com.amilesend.tmdb.client.model.tv.episodes.groups.GetTvEpisodeGroupDetailsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.tmdb.client.data.tv.TvEpisodeGroupsApiDataHelper.Responses.GET_TV_EPISODE_GROUP_DETAILS_RESPONSE;
import static com.amilesend.tmdb.client.data.tv.TvEpisodeGroupsApiDataHelper.newGetTvEpisodeGroupDetailsResponse;
import static com.amilesend.tmdb.client.data.tv.TvEpisodeGroupsApiDataValidator.assertSameGetTvEpisodeGroupDetailsResponse;

public class TvEpisodeGroupsApiFunctionalTest extends FunctionalTestBase {
    private TvEpisodeGroupsApi apiUnderTest;

    @BeforeEach
    public void setApi() {
        apiUnderTest = getClient().getTvEpisodeGroupsApi();
    }

    @Test
    public void getDetails_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, GET_TV_EPISODE_GROUP_DETAILS_RESPONSE);
        final GetTvEpisodeGroupDetailsResponse expected = newGetTvEpisodeGroupDetailsResponse();

        final GetTvEpisodeGroupDetailsResponse actual = apiUnderTest.getDetails(
                GetTvEpisodeGroupDetailsRequest.builder()
                        .tvEpisodeGroupId(500)
                        .build());

        assertSameGetTvEpisodeGroupDetailsResponse(expected, actual);
    }
}
