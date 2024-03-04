package com.app.service.impl;

import com.app.model.Predicates;
import com.app.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    @DisplayName("when movie predicate is null")
    void test1() {
        assertThatThrownBy(() -> movieService.findAllBy(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Movie predicate is null");
    }

    @Test
    @DisplayName("when movies don't contain release date in provided range")
    void test2() {
        Assertions.assertThat(movieService.findAllBy(
                        Predicates.hasReleaseDateBetweenPredicate(
                                LocalDate.of(2014, 1, 1),
                                LocalDate.of(2018, 1, 1)
                        )))
                .isEmpty();
    }

    @Test
    @DisplayName("when movies contain release date in provided range")
    void test3() {
        Assertions.assertThat(movieService.findAllBy(
                        Predicates.hasReleaseDateBetweenPredicate(
                                LocalDate.of(2022, 1, 1),
                                LocalDate.of(2024, 1, 1)
                        )))
                .isEqualTo(List.of(MOVIE_3, MOVIE_4));
    }
}
