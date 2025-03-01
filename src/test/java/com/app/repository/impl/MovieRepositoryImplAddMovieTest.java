package com.app.repository.impl;

import com.app.infrastructure.persistence.repository.impl.MovieRepositoryImpl;
import com.app.infrastructure.persistence.converter.movie.FileToMoviesConverter;
import com.app.domain.movies_management.model.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;

import static com.app.MoviesTestData.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieRepositoryImplAddMovieTest {
    @Mock
    private FileToMoviesConverter fileToMoviesConverter;

    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(fileToMoviesConverter.convert(MOVIES_FILENAME))
                .thenReturn(new ArrayList<>());

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
    @DisplayName("when addMovie method works correctly")
    void test1() {
        // Ad the beginning, there should be no movies in the collection
        assertThat(movieRepository.getMovies())
                .isEmpty();

        // The first added movie should be at index 0
        assertThat(movieRepository.addMovie(MOVIE_1))
                .isZero();

        // The second added movie should be at index 1
        assertThat(movieRepository.addMovie(MOVIE_2))
                .isOne();

        // There should be 2 movies in total
        assertThat(movieRepository.getMovies())
                .hasSize(2);
    }
}
