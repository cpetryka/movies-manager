package com.app.validation.impl;

import com.app.config.AppTestConfig;
import com.app.infrastructure.json.model.MovieData;
import com.app.domain.model.Genre;
import com.app.application.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:application-test.properties")
class MovieDataValidatorTest {
    @Autowired
    Validator<MovieData> validator;

    static Stream<Arguments> validationData() {
        return Stream.of(
                Arguments.of(
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
                        ),
                        Map.of("title", "string does not match regex: SPIDER MAN NO WAY HOMe")
                ),
                Arguments.of(
                        new MovieData(
                                "SPIDER MAN NO WAY HOME",
                                Genre.ACTION,
                                "JON WATTs",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        "TOM HOLLAND",
                                        "ZENDAYA",
                                        "BENEDICT CUMBERBATCH"
                                ),
                                148,
                                List.of(8.0, 9.0),
                                "634649"
                        ),
                        Map.of("director", "string does not match regex: JON WATTs")
                ),
                Arguments.of(
                        new MovieData(
                                "SPIDER MAN NO WAY HOME",
                                Genre.ACTION,
                                "JON WATTS",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        "TOM HOLLANd",
                                        "ZENDAYA",
                                        "BENEDICT CUMBERBATCH"
                                ),
                                148,
                                List.of(8.0, 9.0),
                                "634649"
                        ),
                        Map.of("cast", "not all items match regex: [TOM HOLLANd, ZENDAYA, BENEDICT CUMBERBATCH]")
                ),
                Arguments.of(
                        new MovieData(
                                "SPIDER MAN NO WAY HOME",
                                Genre.ACTION,
                                "JON WATTS",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        "TOM HOLLAND",
                                        "ZENDAYA",
                                        "BENEDICT CUMBERBATCH"
                                ),
                                0,
                                List.of(8.0, 9.0),
                                "634649"
                        ),
                        Map.of("duration", "value must be positive: 0")
                ),
                Arguments.of(
                        new MovieData(
                                "SPIDER MAN NO WAY HOME",
                                Genre.ACTION,
                                "JON WATTS",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        "TOM HOLLAND",
                                        "ZENDAYA",
                                        "BENEDICT CUMBERBATCH"
                                ),
                                148,
                                List.of(10.0, 11.0),
                                "634649"
                        ),
                        Map.of("rating", "value should be in range [0.0; 10.0]: 10.5")
                ),
                Arguments.of(
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
                                -10,
                                List.of(-5.5),
                                "634649"
                        ),
                        Map.of(
                                "title", "string does not match regex: SPIDER MAN NO WAY HOMe",
                                "duration", "value must be positive: -10",
                                "rating", "value should be in range [0.0; 10.0]: -5.5"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("validationData")
    @DisplayName("when validation result is not correct")
    void test1(MovieData movieData, Map<String, String> expectedErrors) {
        assertThat(validator.validate(movieData))
                .isEqualTo(expectedErrors);
    }
}
