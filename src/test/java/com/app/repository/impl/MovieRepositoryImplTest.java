package com.app.repository.impl;

import com.app.domain.repository.impl.MovieRepositoryImpl;
import com.app.infrastructure.persistence.converter.movie.FileToMoviesConverter;
import com.app.domain.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static com.app.MoviesTestData.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieRepositoryImplTest {
    @Mock
    private FileToMoviesConverter fileToMoviesConverter;

    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(fileToMoviesConverter.convert(MOVIES_FILENAME))
                .thenReturn(List.of(MOVIE_1, MOVIE_2));

        movieRepository = new MovieRepositoryImpl(fileToMoviesConverter);

        var movieRepositoryClass = movieRepository.getClass();

        try {
            var filenameField = movieRepositoryClass.getDeclaredField("filename");

            if(filenameField.trySetAccessible()) {
                filenameField.set(movieRepository, MOVIES_FILENAME);
            }

            var setUpMethod = movieRepositoryClass.getDeclaredMethod("setUp");

            if(setUpMethod.trySetAccessible()) {
                setUpMethod.invoke(movieRepository);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("when getMovies method works correctly")
    void test1() {
        assertThat(movieRepository.getMovies())
                .hasSize(2)
                .isEqualTo(List.of(MOVIE_1, MOVIE_2));
    }

    @Test
    @DisplayName("when getMoviesByTitle method works correctly")
    void test2() {
        assertThat(movieRepository.getMoviesByTitle("SPIDER MAN NO WAY HOME"))
                .hasSize(1)
                .isEqualTo(List.of(MOVIE_1));
    }
}
