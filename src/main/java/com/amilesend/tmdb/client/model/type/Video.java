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
package com.amilesend.tmdb.client.model.type;

import com.amilesend.tmdb.client.model.NamedResource;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Describes a video.
 *
 * @see NamedResource
 */
@SuperBuilder
@Getter
@ToString
public class Video extends NamedResource<String, Video> {
    /** The ISO-639-1 language code. */
    @SerializedName("iso_639_1")
    private final String languageCode;
    /** The ISO-3166-1 country code. */
    @SerializedName("iso_3166_1")
    private final String countryCode;
    /** The key identifier. */
    private final String key;
    /** The name of the streaming video site. */
    private final String site;
    /** The video image size. */
    private final Integer size;
    /** The video type. */
    private final String type;
    /** Indicates if the video is officially distributed by the publisher. */
    private final Boolean official;
    /** The timestamp for when the video was published. */
    private final LocalDateTime publishedAt;
}
