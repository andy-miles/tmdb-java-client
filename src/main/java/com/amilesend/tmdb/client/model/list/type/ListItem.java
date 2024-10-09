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
package com.amilesend.tmdb.client.model.list.type;

import com.amilesend.tmdb.client.model.Resource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/** Describes an item in a list. */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class ListItem extends Resource<Integer, ListItem> {
    /** Adult flag indicator. */
    private final Boolean adult;
    /** The path to the backdrop image. */
    private final String backdropPath;
    /** The list of genre identifiers. */
    private final List<Integer> genreIds;
    /** The type of media of the list item. */
    private final String mediaType;
    /** The original language. */
    private final String originalLanguage;
    /** The original title. */
    private final String originalTitle;
    /** The summary. */
    private final String overview;
    /** The popularity rating. */
    private final Double popularity;
    /** The path to the poster image. */
    private final String posterPath;
    /** The release date. */
    private final LocalDate releaseDate;
    /** The movie title. */
    private final String title;
    /** Video flag indicator. */
    private final Boolean video;
    /** Vote rating average. */
    private final Double voteAverage;
    /** The total vote count. */
    private final Integer voteCount;
}
