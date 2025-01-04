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
package com.amilesend.tmdb.client.data.tv;

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.tv.episodes.groups.GetTvEpisodeGroupDetailsResponse;
import com.amilesend.tmdb.client.model.tv.episodes.groups.type.GroupEpisode;
import com.amilesend.tmdb.client.model.tv.episodes.groups.type.TvEpisodeGroup;
import com.amilesend.tmdb.client.model.tv.episodes.groups.type.TvEpisodeGroupType;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;

import static com.amilesend.tmdb.client.data.tv.TvSeriesApiDataHelper.newTvSeriesNetwork;

@UtilityClass
public class TvEpisodeGroupsApiDataHelper {
    public static GetTvEpisodeGroupDetailsResponse newGetTvEpisodeGroupDetailsResponse() {
        return GetTvEpisodeGroupDetailsResponse.builder()
                .id("TVEpisodeGroupDetailsID_500")
                .name("TV Episode Group Value")
                .description("TV Episode Group Description")
                .episodeCount(9)
                .groupCount(3)
                .groups(List.of(
                        newTvEpisodeGroup(1),
                        newTvEpisodeGroup(2),
                        newTvEpisodeGroup(3)))
                .network(newTvSeriesNetwork(1))
                .type(TvEpisodeGroupType.TV)
                .build();
    }

    private static TvEpisodeGroup newTvEpisodeGroup(final int index) {
        final int episodeIndex = 3 * index;
        return TvEpisodeGroup.builder()
                .id("TvEpisodeGroupID_" + index)
                .name("TV Episode Group " + index)
                .episodes(List.of(
                    newGroupEpisode(episodeIndex),
                    newGroupEpisode(episodeIndex + 1),
                    newGroupEpisode(episodeIndex + 2)))
                .locked(true)
                .build();
    }

    private static GroupEpisode newGroupEpisode(final int index) {
        return GroupEpisode.builder()
                .id(index)
                .name("TV Episode Name " + index)
                .airDate(LocalDate.of(2010, 11, 4))
                .episodeNumber(index)
                .overview("Episode Overview " + index)
                .productionCode("Production Code " + index)
                .runtime(22)
                .seasonNumber(1)
                .stillPath("/epStill" + index + ".jpg")
                .voteAverage(0.64D)
                .voteCount(1000)
                .order(index)
                .build();
    }

    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/TvEpisodeGroupsApi/";

        public static final SerializedResource GET_TV_EPISODE_GROUP_DETAILS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "GetTvEpisodeGroupDetailsResponse.json");
    }
}
