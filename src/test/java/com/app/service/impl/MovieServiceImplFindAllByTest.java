package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.movies_management.model.type.Genre;
import com.app.domain.movies_management.model.Movie;
import com.app.domain.movies_management.model.MoviePredicates;
import com.app.domain.movies_management.model.repository.MovieRepository;
import com.app.domain.movies_management.model.MovieCriteria;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.app.MoviesTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieServiceImplFindAllByTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void beforeEach() {
        when(movieRepository.getMovies())
                .thenReturn(MOVIES_LIST);
    }

    private static Stream<Arguments> predicatesWithExpectedResultLists() {
        return Stream.of(
                Arguments.of(
                        MoviePredicates.hasReleaseDateBetweenPredicate(
                                LocalDate.of(2014, 1, 1),
                                LocalDate.of(2018, 1, 1)
                        ),
                        List.of()
                ),
                Arguments.of(
                        MoviePredicates.hasReleaseDateBetweenPredicate(
                                LocalDate.of(2022, 1, 1),
                                LocalDate.of(2024, 1, 1)
                        ),
                        List.of(MOVIE_3, MOVIE_4)
                ),
                Arguments.of(
                        MoviePredicates.matchesCriteriaPredicate(new MovieCriteria(
                                Genre.ACTION,
                                LocalDate.of(2021, 10, 10),
                                LocalDate.of(2024, 10, 10),
                                List.of("TOM HOLLAND"),
                                140,
                                150,
                                9.2
                        )),
                        List.of()
                ),
                Arguments.of(
                        MoviePredicates.matchesCriteriaPredicate(new MovieCriteria(
                                Genre.ACTION,
                                LocalDate.of(2021, 10, 10),
                                LocalDate.of(2024, 10, 10),
                                List.of("TOM HOLLAND"),
                                110,
                                150,
                                5.0
                        )),
                        List.of(MOVIE_1, MOVIE_4)
                ),
                Arguments.of(
                        MoviePredicates.matchesKeywordsPredicate(List.of(
                                "JOHN"
                        )),
                        List.of()
                ),
                Arguments.of(
                        MoviePredicates.matchesKeywordsPredicate(List.of(
                                "SPIDER MAN",
                                "ZENDAYA"
                        )),
                        List.of(MOVIE_1)
                ),
                Arguments.of(
                        MoviePredicates.matchesKeywordsPredicate(List.of(
                                "HOLLAND"
                        )),
                        List.of(MOVIE_1, MOVIE_4)
                )
        );
    }

    @Test
    @DisplayName("when movie predicate is null")
    void test1() {
        assertThatThrownBy(() -> movieService.findAllBy(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Movie predicate is null");
    }


    @ParameterizedTest
    @DisplayName("when movie predicate is not null")
    @MethodSource("predicatesWithExpectedResultLists")
    void test2(Predicate<Movie> moviePredicate, List<Movie> expectedResult) {
        Assertions.assertThat(movieService.findAllBy(moviePredicate))
                .isEqualTo(expectedResult);
    }
}
