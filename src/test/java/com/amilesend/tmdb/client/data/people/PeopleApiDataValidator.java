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
package com.amilesend.tmdb.client.data.people;

import com.amilesend.tmdb.client.model.people.GetChangesResponse;
import com.amilesend.tmdb.client.model.people.GetCombinedCreditsResponse;
import com.amilesend.tmdb.client.model.people.GetExternalIDsResponse;
import com.amilesend.tmdb.client.model.people.GetImagesResponse;
import com.amilesend.tmdb.client.model.people.GetLatestResponse;
import com.amilesend.tmdb.client.model.people.GetMovieCreditsResponse;
import com.amilesend.tmdb.client.model.people.GetPersonDetailsResponse;
import com.amilesend.tmdb.client.model.people.GetTranslationsResponse;
import com.amilesend.tmdb.client.model.people.GetTvCreditsResponse;
import com.amilesend.tmdb.client.model.people.type.CombinedCastCredit;
import com.amilesend.tmdb.client.model.people.type.CombinedCrewCredit;
import com.amilesend.tmdb.client.model.people.type.MovieCastCredit;
import com.amilesend.tmdb.client.model.people.type.MovieCrewCredit;
import com.amilesend.tmdb.client.model.people.type.TvCastCredit;
import com.amilesend.tmdb.client.model.people.type.TvCrewCredit;
import com.amilesend.tmdb.client.model.type.Change;
import com.amilesend.tmdb.client.model.type.ChangeItem;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameChangeItem;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class PeopleApiDataValidator {
    /////////////////////////////
    // GetPersonDetailsResponse
    /////////////////////////////
    public static void assertSameGetPersonDetailsResponse(
            final GetPersonDetailsResponse expected,
            final GetPersonDetailsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getAlsoKnownAs(), actual.getAlsoKnownAs()),
                () -> assertEquals(expected.getBiography(), actual.getBiography()),
                () -> assertEquals(expected.getDeathDate(), actual.getDeathDate()),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getHomepage(), actual.getHomepage()),
                () -> assertEquals(expected.getImdbId(), actual.getImdbId()),
                () -> assertEquals(expected.getKnownForDepartment(), actual.getKnownForDepartment()),
                () -> assertEquals(expected.getPlaceOfBirth(), actual.getPlaceOfBirth()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getProfilePath(), actual.getProfilePath()));
    }

    ///////////////////////
    // GetChangesResponse
    ///////////////////////

    public static void assertSameGetChangesResponse(
            final GetChangesResponse expected,
            final GetChangesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertSameChanges(expected.getChanges(), actual.getChanges());
    }

    public static void assertSameChanges(final List<Change<String>> expected, final List<Change<String>> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameChange(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameChange(final Change<String> expected, final Change<String> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getKey(), actual.getKey()),
                () -> assertSameChangeItems(expected.getItems(), actual.getItems()));
    }

    private static void assertSameChangeItems(
            final List<ChangeItem<String>> expected,
            final List<ChangeItem<String>> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameChangeItem(expected.get(i), actual.get(i));
        }
    }

    ///////////////////////////////
    // GetCombinedCreditsResponse
    ///////////////////////////////

    public static void assertSameGetCombinedCreditsResponse(
            final GetCombinedCreditsResponse expected,
            final GetCombinedCreditsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameCombinedCastCredits(expected.getCast(), actual.getCast()),
                () -> assertSameCombinedCrewCredits(expected.getCrew(), actual.getCrew()));
    }

    private static void assertSameCombinedCastCredits(
            final List<CombinedCastCredit> expected,
            final List<CombinedCastCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameCombinedCastCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameCombinedCastCredit(
            final CombinedCastCredit expected,
            final CombinedCastCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getCharacter(), actual.getCharacter()),
                () -> assertEquals(expected.getCreditId(), actual.getCreditId()),
                () -> assertEquals(expected.getOrder(), actual.getOrder()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()));
    }

    private static void assertSameCombinedCrewCredits(
            final List<CombinedCrewCredit> expected,
            final List<CombinedCrewCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameCombinedCrewCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameCombinedCrewCredit(
            final CombinedCrewCredit expected,
            final CombinedCrewCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getCreditId(), actual.getCreditId()),
                () -> assertEquals(expected.getDepartment(), actual.getDepartment()),
                () -> assertEquals(expected.getJob(), actual.getJob()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()));
    }

    ///////////////////////////
    // GetExternalIDsResponse
    ///////////////////////////

    public static void assertSameGetExternalIDsResponse(
            final GetExternalIDsResponse expected,
            final GetExternalIDsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getTvrageId(), actual.getTvrageId()),
                () -> assertEquals(expected.getWikidataId(), actual.getWikidataId()),
                () -> assertEquals(expected.getFacebookId(), actual.getFacebookId()),
                () -> assertEquals(expected.getInstagramId(), actual.getInstagramId()),
                () -> assertEquals(expected.getTiktokId(), actual.getTiktokId()),
                () -> assertEquals(expected.getTwitterId(), actual.getTwitterId()),
                () -> assertEquals(expected.getYoutubeId(), actual.getYoutubeId()));
    }

    //////////////////////
    // GetImagesResponse
    //////////////////////

    public static void assertSameGetImagesResponse(
            final GetImagesResponse expected,
            final GetImagesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getProfiles(), actual.getProfiles()));
    }

    //////////////////////
    // GetLatestResponse
    //////////////////////

    public static void assertSameGetLatestResponse(final GetLatestResponse expected, final GetLatestResponse actual) {
        assertSameGetPersonDetailsResponse(expected, actual);
    }

    ////////////////////////////
    // GetMovieCreditsResponse
    ////////////////////////////

    public static void assertSameGetMovieCreditsResponse(
            final GetMovieCreditsResponse expected,
            final GetMovieCreditsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameMovieCastCredits(expected.getCast(), actual.getCast()),
                () -> assertSameMovieCrewCredits(expected.getCrew(), actual.getCrew()));
    }

    private static void assertSameMovieCastCredits(
            final List<MovieCastCredit> expected,
            final List<MovieCastCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameMovieCastCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameMovieCastCredit(final MovieCastCredit expected, final MovieCastCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getCharacter(), actual.getCharacter()),
                () -> assertEquals(expected.getCreditId(), actual.getCreditId()),
                () -> assertEquals(expected.getOrder(), actual.getOrder()));
    }

    private static void assertSameMovieCrewCredits(
            final List<MovieCrewCredit> expected,
            final List<MovieCrewCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameMovieCrewCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameMovieCrewCredit(final MovieCrewCredit expected, final MovieCrewCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getCreditId(), actual.getCreditId()),
                () -> assertEquals(expected.getDepartment(), actual.getDepartment()),
                () -> assertEquals(expected.getJob(), actual.getJob()));
    }

    /////////////////////////
    // GetTvCreditsResponse
    /////////////////////////

    public static void assertSameGetTvCreditsResponse(
            final GetTvCreditsResponse expected,
            final GetTvCreditsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertSameTvCastCredits(expected.getCast(), actual.getCast()),
                () -> assertSameTvCrewCredit(expected.getCrew(), actual.getCrew()));
    }

    private static void assertSameTvCastCredits(
            final List<TvCastCredit> expected,
            final List<TvCastCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameTvCastCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameTvCastCredit(
            final TvCastCredit expected,
            final TvCastCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getFirstAirDate(), actual.getFirstAirDate()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getCharacter(), actual.getCharacter()),
                () -> assertEquals(expected.getCreditId(), actual.getCreditId()),
                () -> assertEquals(expected.getEpisodeCount(), actual.getEpisodeCount()));
    }

    private static void assertSameTvCrewCredit(
            final List<TvCrewCredit> expected,
            final List<TvCrewCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameTvCrewCredit(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameTvCrewCredit(
            final TvCrewCredit expected,
            final TvCrewCredit actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getFirstAirDate(), actual.getFirstAirDate()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getCreditId(), actual.getCreditId()),
                () -> assertEquals(expected.getDepartment(), actual.getDepartment()),
                () -> assertEquals(expected.getEpisodeCount(), actual.getEpisodeCount()),
                () -> assertEquals(expected.getJob(), actual.getJob()));
    }

    ////////////////////////////
    // GetTranslationsResponse
    ////////////////////////////

    public static void assertSameGetTranslationsResponse(
            final GetTranslationsResponse expected,
            final GetTranslationsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getTranslations(), actual.getTranslations()));
    }
}
