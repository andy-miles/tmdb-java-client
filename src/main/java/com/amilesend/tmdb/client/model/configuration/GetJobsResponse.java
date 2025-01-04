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
package com.amilesend.tmdb.client.model.configuration;

import com.amilesend.tmdb.client.model.configuration.type.ConfigurationResponseBase;
import com.amilesend.tmdb.client.model.configuration.type.Job;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * The response that describes a list of jobs.
 *
 * @see Job
 * @see ConfigurationResponseBase
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetJobsResponse extends ConfigurationResponseBase<Job> {
    /**
     * Creates a new {@code GetJobsResponse}.
     *
     * @param results the list of jobs
     */
    public GetJobsResponse(final List<Job> results) {
        super(results);
    }
}
