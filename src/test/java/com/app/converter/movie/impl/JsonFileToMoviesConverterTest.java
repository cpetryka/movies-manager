package com.app.converter.movie.impl;

import com.app.config.AppTestConfig;
import com.app.infrastructure.persistence.converter.movie.FileToMoviesConverter;
import com.app.infrastructure.persistence.converter.movie.impl.JsonFileToMoviesConverter;
import com.app.infrastructure.persistence.json.deserializer.JsonDeserializer;
import com.app.infrastructure.persistence.json.model.MovieData;
import com.app.infrastructure.persistence.json.model.MoviesData;
import com.app.domain.model.Genre;
import com.app.application.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static com.app.MoviesTestData.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:application-test.properties")
class JsonFileToMoviesConverterTest {
    @Autowired
    private Validator<MovieData> movieDataValidator;

    @Mock
    private JsonDeserializer<MoviesData> moviesDataJsonDeserializer;

    private FileToMoviesConverter fileToCarsConverter;

    @BeforeEach
    void setUp() {
        fileToCarsConverter = new JsonFileToMoviesConverter(moviesDataJsonDeserializer, movieDataValidator);
    }

    @Test
    @DisplayName("when all data is correct")
    void test1() {
        when(moviesDataJsonDeserializer.fromJson(anyString()))
                .thenReturn(MOVIES_DATA);

        assertThat(fileToCarsConverter.convert(MOVIES_FILENAME))
                .hasSize(5);
    }

    @Test
    @DisplayName("when not all data is correct")
    void test2() {
        when(moviesDataJsonDeserializer.fromJson(anyString()))
                .thenReturn(new MoviesData(List.of(
                        MOVIE_1_DATA,
                        new MovieData(
                                "SPIDER MAN NO WAY HOMe",
                                Genre.ACTION,
                                "JON WATTS",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        "TOM HOLLAND",
                                        "ZENDAYA",
                                        "BENEDICT CUMBERBATCH"
                                ),
                                148,
                                List.of(8.0, 9.0),
                                "634649"
                        )
                )));

        assertThat(fileToCarsConverter.convert("placeholder.json"))
                .hasSize(1)
                .containsOnly(MOVIE_1);
    }
}
