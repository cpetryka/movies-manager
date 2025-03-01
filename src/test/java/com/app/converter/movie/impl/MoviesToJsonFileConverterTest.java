package com.app.converter.movie.impl;

import com.app.application.validation.Validator;
import com.app.config.AppTestConfig;
import com.app.domain.movies_management.model.Movie;
import com.app.infrastructure.persistence.converter.movie.FileToMoviesConverter;
import com.app.infrastructure.persistence.converter.movie.MoviesToFileConverter;
import com.app.infrastructure.persistence.converter.movie.impl.JsonFileToMoviesConverter;
import com.app.infrastructure.persistence.converter.movie.impl.MoviesToJsonFileConverter;
import com.app.infrastructure.persistence.entity.ActorEntity;
import com.app.infrastructure.persistence.entity.MovieEntity;
import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.infrastructure.persistence.json.deserializer.JsonDeserializer;
import com.app.infrastructure.persistence.json.serializer.JsonSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;

import static com.app.MoviesTestData.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:application-test.properties")
class MoviesToJsonFileConverterTest {
    @Mock
    private JsonSerializer<MoviesEntity> moviesEntityJsonSerializer;

    @InjectMocks
    private MoviesToJsonFileConverter moviesToJsonFileConverter;

    // @Captor is an annotation in Mockito that simplifies the creation of ArgumentCaptor objects.
    // ArgumentCaptor allows capturing arguments passed to methods verified using verify(),
    // enabling us to precisely check what values were passed to the mocked method.

    // In our case, moviesEntityCaptor captures the MoviesEntity object that is passed to the toJson() method.
    // This allows us to retrieve the object later and verify whether it was created correctly
    // and contains the expected data.
    @Captor
    private ArgumentCaptor<MoviesEntity> moviesEntityCaptor;

    private static final String TEST_FILENAME = "test_output.json";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(moviesToJsonFileConverter, "filename", TEST_FILENAME);
    }

    @Test
    @DisplayName("should correctly convert movies to JSON file")
    void testConvertMoviesToJson() {
        // Given
        List<Movie> movies = List.of(
                MOVIE_1, MOVIE_2, MOVIE_3, MOVIE_4
        );

        // When
        String result = moviesToJsonFileConverter.convert(movies);

        // Then
        verify(moviesEntityJsonSerializer).toJson(moviesEntityCaptor.capture(), eq(TEST_FILENAME));
        MoviesEntity capturedMoviesEntity = moviesEntityCaptor.getValue();
        assertThat(capturedMoviesEntity.movies()).hasSize(4);
        assertThat(result).isEmpty();
    }
}
