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

import com.amilesend.tmdb.client.data.SerializedResource;
import com.amilesend.tmdb.client.model.search.SearchCollectionsResponse;
import com.amilesend.tmdb.client.model.search.SearchCompaniesResponse;
import com.amilesend.tmdb.client.model.search.SearchKeywordsResponse;
import com.amilesend.tmdb.client.model.search.SearchMoviesResponse;
import com.amilesend.tmdb.client.model.search.SearchMultiResponse;
import com.amilesend.tmdb.client.model.search.SearchPeopleResponse;
import com.amilesend.tmdb.client.model.search.SearchTvResponse;
import com.amilesend.tmdb.client.model.search.type.CollectionSearchResult;
import com.amilesend.tmdb.client.model.search.type.CompanySearchResult;
import com.amilesend.tmdb.client.model.search.type.MediaType;
import com.amilesend.tmdb.client.model.search.type.MovieCastCredit;
import com.amilesend.tmdb.client.model.search.type.MovieSearchResult;
import com.amilesend.tmdb.client.model.search.type.PersonSearchResult;
import com.amilesend.tmdb.client.model.search.type.TvCastCredit;
import com.amilesend.tmdb.client.model.search.type.TvSeriesSearchResult;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static com.amilesend.tmdb.client.data.movie.MoviesApiDataHelper.newKeyword;

@UtilityClass
public class SearchApiDataHelper {
    /////////////////////////////
    // SearchCollectionResponse
    /////////////////////////////

    public static SearchCollectionsResponse newSearchCollectionResponse() {
        return SearchCollectionsResponse.builder()
                .page(1)
                .totalPages(1)
                .totalResults(2)
                .results(List.of(newCollectionSearchResult(1), newCollectionSearchResult(2)))
                .build();
    }

    private static CollectionSearchResult newCollectionSearchResult(final int index) {
        return CollectionSearchResult.builder()
                .id(1)
                .name("Collection " + index)
                .adult(false)
                .backdropPath("/backdrop" + index + ".jpg")
                .originalLanguage("Original Language")
                .originalName("Original Name " + index)
                .overview("Overview " + index)
                .posterPath("/poster" + index + ".jpg")
                .build();
    }

    ////////////////////////////
    // SearchCompaniesResponse
    ////////////////////////////

    public static SearchCompaniesResponse newSearchCompaniesResponse() {
        return SearchCompaniesResponse.builder()
                .page(1)
                .totalResults(4)
                .totalPages(1)
                .results(List.of(
                        newCompanySearchResult(1),
                        newCompanySearchResult(2),
                        newCompanySearchResult(3),
                        newCompanySearchResult(4)))
                .build();
    }

    private static CompanySearchResult newCompanySearchResult(final int index) {
        return CompanySearchResult.builder()
                .id(index)
                .name("Company " + index)
                .logoPath("/companyLogo" + index + ".jpg")
                .originCountry(Locale.US.getCountry())
                .build();
    }

    ///////////////////////////
    // SearchKeywordsResponse
    ///////////////////////////

    public static SearchKeywordsResponse newSearchKeywordsResponse() {
        return SearchKeywordsResponse.builder()
                .page(1)
                .totalResults(4)
                .totalPages(1)
                .results(List.of(newKeyword(1), newKeyword(2), newKeyword(3), newKeyword(4)))
                .build();
    }

    /////////////////////////
    // SearchMoviesResponse
    /////////////////////////

    public static SearchMoviesResponse newSearchMoviesResponse() {
        return SearchMoviesResponse.builder()
                .page(1)
                .totalPages(1)
                .totalResults(3)
                .results(List.of(
                        newMovieSearchResult(1),
                        newMovieSearchResult(2),
                        newMovieSearchResult(3)))
                .build();
    }

    public static MovieSearchResult newMovieSearchResult(final int index) {
        return MovieSearchResult.builder()
                .adult(false)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(1, 2, 3))
                .id(index)
                .originalLanguage(Locale.US.getLanguage())
                .originalTitle("Original Movie Title " + index)
                .overview("Overview for movie " + index)
                .popularity(0.6D)
                .posterPath("/poster" + index + ".jpg")
                .releaseDate(LocalDate.of(2024, 5, 15))
                .title("Movie Title " + index)
                .video(false)
                .voteAverage(0.55D)
                .voteCount(100)
                .mediaType(MediaType.MOVIE)
                .build();
    }

    ////////////////////////
    // SearchMultiResponse
    ////////////////////////

    public static SearchMultiResponse newSearchMultiResponse() {
        return SearchMultiResponse.builder()
                .page(1)
                .totalResults(6)
                .totalPages(1)
                .results(List.of(
                        newPersonSearchResult(1),
                        newMovieSearchResult(2),
                        newTvSeriesSearchResult(3),
                        newTvSeriesSearchResult(4),
                        newPersonSearchResult(5),
                        newMovieSearchResult(6)))
                .build();
    }

    /////////////////////////
    // SearchPeopleResponse
    /////////////////////////

    public static SearchPeopleResponse newSearchPeopleResponse() {
        return SearchPeopleResponse.builder()
                .page(1)
                .totalPages(1)
                .totalResults(2)
                .results(List.of(
                        newPersonSearchResult(1),
                        newPersonSearchResult(2)))
                .build();
    }

    public static PersonSearchResult newPersonSearchResult(final int index) {
        return PersonSearchResult.builder()
                .id(index)
                .name("Person " + index)
                .adult(true)
                .originalName("Original Name " + index)
                .popularity(100D)
                .gender(1)
                .knownForDepartment("acting")
                .profilePath("/profile" + index + ".jpg")
                .mediaType(MediaType.PERSON)
                .knowFor(List.of(
                        newMovieCastCredit(1),
                        newMovieCastCredit(2),
                        newMovieCastCredit(3),
                        newTvCastCredit(4),
                        newTvCastCredit(5),
                        newMovieCastCredit(6)))
                .build();
    }

    private static TvCastCredit newTvCastCredit(final int index) {
        return TvCastCredit.builder()
                .adult(true)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(10, 15, 20))
                .originCountry(List.of(Locale.US.getCountry()))
                .originalLanguage(Locale.US.getLanguage())
                .overview("Overview " + index)
                .popularity(134.0D)
                .posterPath("/poster" + index + ".jpg")
                .voteAverage(87.3D)
                .voteCount(2000)
                .mediaType(MediaType.TV)
                .name("TV Series Name " + index)
                .originalName("Original Name " + index)
                .firstAirDate(LocalDate.of(2012, 3, 4))
                .build();
    }

    public static MovieCastCredit newMovieCastCredit(final int index) {
        return MovieCastCredit.builder()
                .adult(true)
                .backdropPath("/backdrop" + index + ".jpg")
                .genreIds(List.of(10, 15, 20))
                .originCountry(List.of(Locale.US.getCountry()))
                .originalLanguage(Locale.US.getLanguage())
                .overview("Overview " + index)
                .popularity(134.0D)
                .posterPath("/poster" + index + ".jpg")
                .voteAverage(87.3D)
                .voteCount(2000)
                .mediaType(MediaType.MOVIE)
                .originalTitle("Original Title " + index)
                .releaseDate(LocalDate.of(2007, 7, 8))
                .title("Title " + index)
                .video(false)
                .build();
    }

    /////////////////////
    // SearchTvResponse
    /////////////////////

    public static SearchTvResponse newSearchTvResponse() {
        return SearchTvResponse.builder()
                .page(1)
                .totalResults(4)
                .totalPages(1)
                .results(List.of(
                        newTvSeriesSearchResult(1),
                        newTvSeriesSearchResult(2),
                        newTvSeriesSearchResult(3),
                        newTvSeriesSearchResult(4)))
                .build();
    }

    public static TvSeriesSearchResult newTvSeriesSearchResult(final int index) {
        return TvSeriesSearchResult.builder()
                .id(index)
                .name("TV Series " + index)
                .adult(false)
                .backdropPath("/backdrop" + index + ".jpg")
                .originalLanguage(Locale.US.getLanguage())
                .originalName("TV Series " + index)
                .overview("Overview " + index)
                .posterPath("/poster" + index + ".jpg")
                .genreIds(List.of(1, 4, 10))
                .popularity(0.78D)
                .firstAirDate(LocalDate.of(2014, 5, 6))
                .voteAverage(0.82D)
                .voteCount(3000)
                .originCountry(List.of(Locale.US.getCountry()))
                .mediaType(MediaType.TV)
                .build();
    }


    @UtilityClass
    public static class Responses {
        private static final String RESOURCE_FOLDER = "/SearchApi/";

        public static final SerializedResource SEARCH_COLLECTIONS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "SearchCollectionsResponse.json");
        public static final SerializedResource SEARCH_COMPANIES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "SearchCompaniesResponse.json");
        public static final SerializedResource SEARCH_KEYWORDS_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "SearchKeywordsResponse.json");
        public static final SerializedResource SEARCH_MOVIES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "SearchMoviesResponse.json");
        public static final SerializedResource SEARCH_MULTI_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "SearchMultiResponse.json");
        public static final SerializedResource SEARCH_PEOPLE_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "SearchPeopleResponse.json");
        public static final SerializedResource SEARCH_TV_SERIES_RESPONSE =
                new SerializedResource(RESOURCE_FOLDER + "SearchTvResponse.json");
    }
}
