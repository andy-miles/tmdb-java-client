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
package com.amilesend.tmdb.client.model.type;

import com.amilesend.tmdb.client.model.Resource;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/** Describes an image resource. */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class ImageResource extends Resource<String, ImageResource> {
    /** The image aspect ratio. */
    private final Double aspectRatio;
    /** The path to the image file. */
    private final String filePath;
    /** The image height in pixels. */
    private final Integer height;
    /** The file type. */
    private final String fileType;
    /** The vote average. */
    private final Double voteAverage;
    /** The number of votes. */
    private final Integer voteCount;
    /** The image width in pixels. */
    private final Integer width;
}
