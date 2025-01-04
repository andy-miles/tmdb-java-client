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
package com.amilesend.tmdb.client.model.people.lists.type;

import com.amilesend.tmdb.client.model.NamedResource;
import com.amilesend.tmdb.client.model.Resource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * Describes media associated with a popular person.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class Media extends Resource<Integer, Media> {
    /** Adult flag indicator. */
    private final Boolean adult;
    /** The backdrop image path. */
    private final String backdropPath;
    /** The list of associated genre identifiers. */
    private final List<Integer> genreIds;
    /** The type of media (e.g., movie, tv, etc.). */
    private final String mediaType;
    /** The original content language. */
    private final String originalLanguage;
    /** The original content title. */
    private final String originalTitle;
    /** The content summary. */
    private final String overview;
    /** The poster image path. */
    private final String posterPath;
    /** The media release date. */
    private final LocalDate releaseDate;
    /** The title/name. */
    private final String title;
    /** Flag indicator if the media is a video. */
    private final Boolean video;
    /** The user vote rating. */
    private final Double voteAverage;
    /** The user vote count. */
    private final Integer voteCount;
}
