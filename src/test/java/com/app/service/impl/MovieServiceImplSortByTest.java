package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.movies_management.model.Movie;
import com.app.domain.movies_management.model.repository.MovieRepository;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.app.MoviesTestData.*;
import static com.app.domain.movies_management.model.MovieComparators.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieServiceImplSortByTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void beforeEach() {
        when(movieRepository.getMovies())
                .thenReturn(List.of(MOVIE_1, MOVIE_2, MOVIE_3));
    }

    private static Stream<Arguments> comparatorsWithSortedMovies() {
        return Stream.of(
                Arguments.of(
                        byTitleComparator,
                        List.of(MOVIE_2, MOVIE_1, MOVIE_3)
                ),
                Arguments.of(
                        byTitleComparatorDesc,
                        List.of(MOVIE_3, MOVIE_1, MOVIE_2)
                ),
                Arguments.of(
                        byReleaseDateComparator,
                        List.of(MOVIE_2, MOVIE_1, MOVIE_3)
                ),
                Arguments.of(
                        byReleaseDateComparatorDesc,
                        List.of(MOVIE_3, MOVIE_1, MOVIE_2)
                ),
                Arguments.of(
                        byRatingComparator,
                        List.of(MOVIE_2, MOVIE_3, MOVIE_1)
                ),
                Arguments.of(
                        byRatingComparatorDesc,
                        List.of(MOVIE_1, MOVIE_3, MOVIE_2)
                )
        );
    }

    @Test
    @DisplayName("when comparator is null")
    void test1() {
        assertThatThrownBy(() -> movieService.sortBy(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator is null");
    }

    @ParameterizedTest
    @MethodSource("comparatorsWithSortedMovies")
    @DisplayName("when comparator is not null")
    void test2(Comparator<Movie> movieComparator, List<Movie> expectedSortedMovies) {
        assertThat(movieService.sortBy(movieComparator))
                .isEqualTo(expectedSortedMovies);
    }
}
