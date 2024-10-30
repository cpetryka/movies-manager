package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.movies_management.model.Movie;
import com.app.domain.movies_management.model.repository.MovieRepository;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieServiceImplGroupByCastMembersTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void beforeEach() {
        when(movieRepository.getMovies())
                .thenReturn(List.of(MOVIE_1, MOVIE_4));
    }

    @Test
    @DisplayName("when movies comparator is not null")
    void test1() {
        assertThatThrownBy(() -> movieService.groupByCastMembers(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Movies comparator is null");
    }

    @Test
    @DisplayName("when movies comparator is null")
    void test2() {
        Comparator<List<Movie>> comparator = Comparator.comparingInt(List::size);

        Map<String, List<Movie>> expectedGroupedMovies = Map.of(
                "Tom Holland", List.of(MOVIE_1, MOVIE_4),
                "Zendaya", List.of(MOVIE_1),
                "Benedict Cumberbatch", List.of(MOVIE_1),
                "Mark Wahlberg", List.of(MOVIE_4),
                "Sophia Ali", List.of(MOVIE_4)
        );

        assertThat(movieService.groupByCastMembers(comparator))
                .isEqualTo(expectedGroupedMovies);
    }
}
