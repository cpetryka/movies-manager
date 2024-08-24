package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.model.Genre;
import com.app.domain.repository.MovieRepository;
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

import java.util.List;
import java.util.Map;

import static com.app.MoviesTestData.*;
import static com.app.domain.model.Mappers.toGenreMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieServiceImplCountByTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void beforeEach() {
        when(movieRepository.getMovies())
                .thenReturn(List.of(MOVIE_1, MOVIE_2, MOVIE_3));
    }

    @Test
    @DisplayName("when classifier is null")
    void test1() {
        assertThatThrownBy(() -> movieService.countBy(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Classifier is null");
    }

    @Test
    @DisplayName("when movies are counted correctly by classifier")
    void test2() {
        Assertions.assertThat(movieService.countBy(toGenreMapper))
                .hasSize(2)
                .containsAllEntriesOf(Map.of(
                        Genre.ACTION, 2L,
                        Genre.ROMANCE, 1L
                ));
    }
}
