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
package com.amilesend.tmdb.client.model.credit.type;

import com.amilesend.tmdb.client.model.NamedResource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * Describes media being credited.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class Media extends NamedResource<Integer, Media> {
    /** Adult flag indicator. */
    private final Boolean adult;
    /** The backdrop image path. */
    private final String backdropPath;
    /** The original content language. */
    private final String originalLanguage;
    /** The original content name. */
    private final String originalName;
    /** The content summary. */
    private final String overview;
    /** The poster image path. */
    private final String posterPath;
    /** The media type. */
    private final String mediaType;
    /** The list of associated genre identifiers. */
    private final List<Integer> genreIds;
    /** The popularity rating. */
    private final Double popularity;
    /** The first air date. */
    private final LocalDate firstAirDate;
    /** The user vote rating. */
    private final Double voteAverage;
    /** The user vote count. */
    private final Integer voteCount;
    /** The list of origin countries. */
    private final List<String> originCountry;
    /** The character. */
    private final String character;
    /** The list of seasons. */
    private final List<Season> seasons;
}
