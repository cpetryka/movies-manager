package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.movies_management.model.type.Genre;
import com.app.domain.movies_management.model.repository.MovieRepository;
import com.app.application.utils.MinMax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.app.MoviesTestData.*;
import static com.app.domain.movies_management.model.MovieMappers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MovieServiceImplGroupAndFindMinMaxByCriteriaTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void beforeEach() {
        when(movieRepository.getMovies()).thenReturn(MOVIES_LIST);
    }

    @Test
    @DisplayName("when grouping function is null")
    void test1() {
        assertThatThrownBy(() -> movieService.groupAndFindMinMaxByCriteria(
                null, toGenreMapper, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Grouping function is null");
    }

    @Test
    @DisplayName("when min max grouping function is null")
    void test2() {
        assertThatThrownBy(() -> movieService.groupAndFindMinMaxByCriteria(
                toGenreMapper, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Min max grouping function is null");
    }

    @Test
    @DisplayName("when comparator is null")
    void test3() {
        assertThatThrownBy(() -> movieService.groupAndFindMinMaxByCriteria(
                toGenreMapper, toDirectorMapper, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Min max comparator is null");
    }

    @Test
    @DisplayName("should group by genre and find at least one min and at least one max by rating")
    void test4() {
        assertThat(movieService.groupAndFindMinMaxByCriteria(
                toGenreMapper, toRatingMapper, Comparator.naturalOrder()))
                .containsAllEntriesOf(Map.of(
                        Genre.ACTION, new MinMax<>(List.of(MOVIE_3, MOVIE_4), List.of(MOVIE_1)),
                        Genre.ROMANCE, new MinMax<>(List.of(MOVIE_2), List.of(MOVIE_5))
                ));
    }
}
