package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.movies_management.model.Actor;
import com.app.domain.movies_management.model.ActorComparators;
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

import static com.app.MoviesTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MovieServiceImplSortCastTest {
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
    @DisplayName("when comparator is null")
    void test1() {
        assertThatThrownBy(() -> movieService.sortCast(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cast comparator is null");
    }

    @Test
    @DisplayName("when comparator is not null")
    void test2() {
        Comparator<Actor> comparator = Comparator.comparing(Actor::getFullName);

        List<Movie> expectedMoviesList = List.of(
                MOVIE_1.sortCast(comparator),
                MOVIE_2.sortCast(comparator),
                MOVIE_3.sortCast(comparator)
        );

        assertThat(movieService.sortCast(ActorComparators.byNameComparator))
                .isEqualTo(expectedMoviesList);
    }
}
