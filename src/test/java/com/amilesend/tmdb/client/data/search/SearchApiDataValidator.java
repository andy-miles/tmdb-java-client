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
package com.amilesend.tmdb.client.data.search;

import com.amilesend.tmdb.client.model.search.SearchCollectionsResponse;
import com.amilesend.tmdb.client.model.search.SearchCompaniesResponse;
import com.amilesend.tmdb.client.model.search.SearchKeywordsResponse;
import com.amilesend.tmdb.client.model.search.SearchMoviesResponse;
import com.amilesend.tmdb.client.model.search.SearchMultiResponse;
import com.amilesend.tmdb.client.model.search.SearchPeopleResponse;
import com.amilesend.tmdb.client.model.search.SearchTvResponse;
import com.amilesend.tmdb.client.model.search.type.CollectionSearchResult;
import com.amilesend.tmdb.client.model.search.type.CompanySearchResult;
import com.amilesend.tmdb.client.model.search.type.MovieCastCredit;
import com.amilesend.tmdb.client.model.search.type.MovieSearchResult;
import com.amilesend.tmdb.client.model.search.type.PersonCredit;
import com.amilesend.tmdb.client.model.search.type.PersonSearchResult;
import com.amilesend.tmdb.client.model.search.type.SearchResult;
import com.amilesend.tmdb.client.model.search.type.TvCastCredit;
import com.amilesend.tmdb.client.model.search.type.TvSeriesSearchResult;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

import static com.amilesend.tmdb.client.data.movie.MoviesApiDataValidator.assertSameKeywords;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class SearchApiDataValidator {
    //////////////////////////////
    // SearchCollectionsResponse
    //////////////////////////////

    public static void assertSameSearchCollectionsResponse(
            final SearchCollectionsResponse expected,
            final SearchCollectionsResponse actual) {

        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSameCollectionSearchResults(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    private static void assertSameCollectionSearchResults(
            final List<CollectionSearchResult> expected,
            final List<CollectionSearchResult> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameCollectionSearchResult(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameCollectionSearchResult(
            final CollectionSearchResult expected,
            final CollectionSearchResult actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()));
    }

    ////////////////////////////
    // SearchCompaniesResponse
    ////////////////////////////

    public static void assertSameSearchCompaniesResponse(
            final SearchCompaniesResponse expected,
            final SearchCompaniesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameCompanySearchResults(expected.getResults(), actual.getResults()));
    }

    private static void assertSameCompanySearchResults(
            final List<CompanySearchResult> expected,
            final List<CompanySearchResult> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameCompanySearchResult(expected.get(i), actual.get(i));
        }
    }

    private static void assertSameCompanySearchResult(
            final CompanySearchResult expected,
            final CompanySearchResult actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getLogoPath(), actual.getLogoPath()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()));
    }

    ///////////////////////////
    // SearchKeywordsResponse
    ///////////////////////////

    public static void assertSameSearchKeywordsResponse(
            final SearchKeywordsResponse expected,
            final SearchKeywordsResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()),
                () -> assertSameKeywords(expected.getResults(), actual.getResults()));
    }

    /////////////////////////
    // SearchMoviesResponse
    /////////////////////////

    public static void assertSameSearchMoviesResponse(
            final SearchMoviesResponse expected,
            final SearchMoviesResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSameMovieSearchResults(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    public static void assertSameMovieSearchResults(
            final List<MovieSearchResult> expected,
            final List<MovieSearchResult> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameMovieSearchResult(expected.get(i), actual.get(i));
        }
    }

    public static void assertSameMovieSearchResult(final MovieSearchResult expected, final MovieSearchResult actual) {
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
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()));
    }

    ////////////////////////
    // SearchMultiResponse
    ////////////////////////

    public static void assertSameSearchMultiResponse(
            final SearchMultiResponse expected,
            final SearchMultiResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSameSearchResults(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    /////////////////////////
    // SearchPeopleResponse
    /////////////////////////

    public static void assertSameSearchPeopleResponse(
            final SearchPeopleResponse expected,
            final SearchPeopleResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSamePersonSearchResults(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    public static void assertSamePersonSearchResults(
            final List<PersonSearchResult> expected,
            final List<PersonSearchResult> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSamePersonSearchResult(expected.get(i), actual.get(i));
        }
    }

    public static void assertSamePersonSearchResult(final PersonSearchResult expected, final PersonSearchResult actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getKnownForDepartment(), actual.getKnownForDepartment()),
                () -> assertEquals(expected.getProfilePath(), actual.getProfilePath()),
                () -> assertSamePersonCredits(expected.getKnowFor(), actual.getKnowFor()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()));
    }

    private static void assertSamePersonCredits(
            final List<PersonCredit> expected,
            final List<PersonCredit> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            final PersonCredit expectedCredit = expected.get(i);
            if (expectedCredit instanceof MovieCastCredit) {
                assertSameMovieCastCredit((MovieCastCredit) expectedCredit, (MovieCastCredit) actual.get(i));
            } else if (expectedCredit instanceof TvCastCredit) {
                assertSameTvCastCredit((TvCastCredit) expectedCredit, (TvCastCredit) actual.get(i));
            } else {
                fail("Unknown type for TrendingPersonCredit");
            }
        }
    }

    private static void assertSameMovieCastCredit(
            final MovieCastCredit expected,
            final MovieCastCredit actual) {
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
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()),
                () -> assertEquals(expected.getOriginalTitle(), actual.getOriginalTitle()),
                () -> assertEquals(expected.getReleaseDate(), actual.getReleaseDate()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getVideo(), actual.getVideo()));
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
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getFirstAirDate(), actual.getFirstAirDate()));
    }

    public static void assertSameSearchResults(
            final List<SearchResult> expected,
            final List<SearchResult> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            final SearchResult expectedResource = expected.get(i);
            if (expectedResource instanceof MovieSearchResult) {
                assertSameMovieSearchResult((MovieSearchResult) expectedResource, (MovieSearchResult) actual.get(i));
            } else if (expectedResource instanceof PersonSearchResult) {
                assertSamePersonSearchResult((PersonSearchResult) expectedResource, (PersonSearchResult) actual.get(i));
            } else if (expectedResource instanceof TvSeriesSearchResult) {
                assertSameTvSeriesSearchResult(
                        (TvSeriesSearchResult) expectedResource,
                        (TvSeriesSearchResult) actual.get(i));
            } else {
                fail("Unknown type for TrendingResource");
            }
        }
    }

    /////////////////////
    // SearchTvResponse
    /////////////////////

    public static void assertSameSearchTvResponse(
            final SearchTvResponse expected,
            final SearchTvResponse actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getPage(), actual.getPage()),
                () -> assertSameTvSeriesSearchResults(expected.getResults(), actual.getResults()),
                () -> assertEquals(expected.getTotalPages(), actual.getTotalPages()),
                () -> assertEquals(expected.getTotalResults(), actual.getTotalResults()));
    }

    public static void assertSameTvSeriesSearchResults(
            final List<TvSeriesSearchResult> expected,
            final List<TvSeriesSearchResult> actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); ++i) {
            assertSameTvSeriesSearchResult(expected.get(i), actual.get(i));
        }
    }

    public static void assertSameTvSeriesSearchResult(
            final TvSeriesSearchResult expected,
            final TvSeriesSearchResult actual) {
        if (Objects.isNull(expected)) {
            assertNull(actual);
            return;
        }

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getAdult(), actual.getAdult()),
                () -> assertEquals(expected.getBackdropPath(), actual.getBackdropPath()),
                () -> assertEquals(expected.getOriginalLanguage(), actual.getOriginalLanguage()),
                () -> assertEquals(expected.getOriginalName(), actual.getOriginalName()),
                () -> assertEquals(expected.getOverview(), actual.getOverview()),
                () -> assertEquals(expected.getPosterPath(), actual.getPosterPath()),
                () -> assertEquals(expected.getGenreIds(), actual.getGenreIds()),
                () -> assertEquals(expected.getPopularity(), actual.getPopularity(), 0.01D),
                () -> assertEquals(expected.getFirstAirDate(), actual.getFirstAirDate()),
                () -> assertEquals(expected.getVoteAverage(), actual.getVoteAverage(), 0.01D),
                () -> assertEquals(expected.getVoteCount(), actual.getVoteCount()),
                () -> assertEquals(expected.getOriginCountry(), actual.getOriginCountry()),
                () -> assertEquals(expected.getMediaType(), actual.getMediaType()));
    }
}
