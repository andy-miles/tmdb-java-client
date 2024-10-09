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
package com.amilesend.tmdb.client.parse.adapters;

import com.amilesend.tmdb.client.model.search.type.MediaType;
import com.amilesend.tmdb.client.model.search.type.MovieCastCredit;
import com.amilesend.tmdb.client.model.search.type.PersonCredit;
import com.amilesend.tmdb.client.model.search.type.TvCastCredit;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import static com.amilesend.tmdb.client.model.search.type.MediaType.parseMediaTypeFromJson;

/**
 * GSON deserializer that inspects the {@code media_type} attribute and returns the associated POJO type.
 *
 * @see PersonCredit
 * @see MovieCastCredit
 * @see TvCastCredit
 */
public class PersonCreditDeserializer implements JsonDeserializer<PersonCredit> {
    @Override
    public PersonCredit deserialize(
            final JsonElement jsonElement,
            final Type type,
            final JsonDeserializationContext context) throws JsonParseException {
        if (jsonElement.isJsonNull()) {
            return null;
        }

        final MediaType mediaType = parseMediaTypeFromJson(jsonElement);
        switch (mediaType) {
            case MOVIE:
                return context.deserialize(jsonElement, MovieCastCredit.class);
            case TV:
                return context.deserialize(jsonElement, TvCastCredit.class);
            default:
                throw new JsonParseException("Unknown/unsupported media type: " + mediaType.name());
        }
    }
}
