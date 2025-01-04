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
package com.amilesend.tmdb.client.model.review;

import com.amilesend.tmdb.client.model.Resource;
import com.amilesend.tmdb.client.model.type.AuthorDetails;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * The response describing a review.
 *
 * @see Resource
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetReviewDetailsResponse extends Resource<String, GetReviewDetailsResponse> {
    /** The author's name. */
    private final String author;
    /** The author's details. */
    private final AuthorDetails authorDetails;
    /** The review content. */
    private final String content;
    /** The review creation timestamp. */
    private final LocalDateTime createdAt;
    /** The ISO-639-1 language code of the review. */
    @SerializedName("iso_639_1")
    private final String languageCode;
    /** The associated media identifier. */
    private final Integer mediaId;
    /** The associated media title. */
    private final String mediaTitle;
    /** The associated media type. */
    private final String mediaType;
    /** The last updated timestamp. */
    private final LocalDateTime updatedAt;
    /** The URL to the review. */
    private final String url;
}
