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
package com.amilesend.tmdb.client.parse;

import com.amilesend.client.connection.Connection;
import com.amilesend.client.parse.GsonFactoryBase;
import com.amilesend.client.parse.strategy.AnnotationBasedExclusionStrategy;
import com.amilesend.client.parse.strategy.AnnotationBasedSerializationExclusionStrategy;
import com.amilesend.tmdb.client.model.search.type.MediaType;
import com.amilesend.tmdb.client.model.search.type.PersonCredit;
import com.amilesend.tmdb.client.model.search.type.SearchResult;
import com.amilesend.tmdb.client.model.trending.type.TimeWindow;
import com.amilesend.tmdb.client.model.tv.episodes.groups.type.TvEpisodeGroupType;
import com.amilesend.tmdb.client.parse.adapters.LocalDateTimeTypeAdapter;
import com.amilesend.tmdb.client.parse.adapters.LocalDateTypeAdapter;
import com.amilesend.tmdb.client.parse.adapters.LocalTimeTypeAdapter;
import com.amilesend.tmdb.client.parse.adapters.MediaTypeTypeAdapter;
import com.amilesend.tmdb.client.parse.adapters.PersonCreditDeserializer;
import com.amilesend.tmdb.client.parse.adapters.SearchResultDeserializer;
import com.amilesend.tmdb.client.parse.adapters.TimeWindowTypeAdapter;
import com.amilesend.tmdb.client.parse.adapters.TvEpisodeGroupTypeTypeAdapter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.gsonfire.GsonFireBuilder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** Factory that vends new pre-configured {@link Gson} instances. */
@NoArgsConstructor
public class GsonFactory extends GsonFactoryBase<Connection> {
       @Override
    protected GsonBuilder configure(final GsonBuilder gsonBuilder, final Connection connection) {
        return gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                .registerTypeAdapter(MediaType.class, new MediaTypeTypeAdapter())
                .registerTypeAdapter(TvEpisodeGroupType.class, new TvEpisodeGroupTypeTypeAdapter())
                .registerTypeAdapter(SearchResult.class, new SearchResultDeserializer())
                .registerTypeAdapter(PersonCredit.class, new PersonCreditDeserializer())
                .registerTypeAdapter(TimeWindow.class, new TimeWindowTypeAdapter());
    }

    @Override
    protected GsonBuilder newGsonBuilder() {
        return new GsonFireBuilder().createGsonBuilder()
                .setExclusionStrategies(new AnnotationBasedExclusionStrategy())
                .addSerializationExclusionStrategy(new AnnotationBasedSerializationExclusionStrategy());
    }
}
