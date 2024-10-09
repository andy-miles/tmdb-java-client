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
package com.amilesend.tmdb.client.parse;

import com.amilesend.tmdb.client.connection.Connection;
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
import com.amilesend.tmdb.client.parse.strategy.AnnotationBasedExclusionStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.gsonfire.GsonFireBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** Factory that vends new pre-configured {@link Gson} instances. */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class GsonFactory {
    private static final GsonFactory INSTANCE = new GsonFactory();

    /**
     * Gets the singleton {@code GsonFactory} instance.
     *
     * @return the factory instance
     */
    public static GsonFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Gets a new {@link Gson} instance that is configured for use by {@link Connection}.
     *
     * @return the pre-configured Gson instance
     */
    public Gson newInstanceForConnection() {
        return newGsonBuilder().create();
    }

    /**
     * Gets a new {@link Gson} instance that is configured for use by {@link Connection} that provides pretty-printed
     * formatted JSON (i.e., useful for testing and/or debugging).
     *
     * @return the pre-configured Gson instance
     */
    public Gson newInstanceForPrettyPrinting() {
        return newGsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    private GsonBuilder newGsonBuilder() {
        return new GsonFireBuilder()
                .createGsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setExclusionStrategies(new AnnotationBasedExclusionStrategy())
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                .registerTypeAdapter(MediaType.class, new MediaTypeTypeAdapter())
                .registerTypeAdapter(TvEpisodeGroupType.class, new TvEpisodeGroupTypeTypeAdapter())
                .registerTypeAdapter(SearchResult.class, new SearchResultDeserializer())
                .registerTypeAdapter(PersonCredit.class, new PersonCreditDeserializer())
                .registerTypeAdapter(TimeWindow.class, new TimeWindowTypeAdapter());
    }
}
