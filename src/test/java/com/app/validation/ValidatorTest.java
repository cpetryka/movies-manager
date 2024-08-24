package com.app.validation;

import com.app.application.validation.Validator;
import com.app.config.AppTestConfig;
import com.app.infrastructure.json.model.MovieData;
import com.app.domain.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static com.app.MoviesTestData.MOVIE_1_DATA;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:application-test.properties")
class ValidatorTest {
    @Autowired
    Validator<MovieData> validator;

    @Test
    @DisplayName("when validation passes")
    void test1() {
        assertThat(Validator.validate(MOVIE_1_DATA, validator)).isTrue();
    }

    @Test
    @DisplayName("when validation fails")
    void test2() {
        var incorrectMovieData = new MovieData(
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
                8.5,
                "634649"
        );

        assertThat(Validator.validate(incorrectMovieData, validator)).isFalse();
    }
}
