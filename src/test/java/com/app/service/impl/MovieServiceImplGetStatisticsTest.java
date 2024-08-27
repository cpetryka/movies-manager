package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.model.Genre;
import com.app.domain.model.RatingItem;
import com.app.domain.repository.MovieRepository;
import com.app.domain.utils.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.app.MoviesTestData.MOVIES_LIST;
import static com.app.domain.model.Mappers.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieServiceImplGetStatisticsTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void beforeEach() {
        when(movieRepository.getMovies()).thenReturn(MOVIES_LIST);
    }

    private static Stream<Arguments> extractorsWithStatistics() {
        return Stream.of(
                Arguments.of(
                        toGenreMapper,
                        new Statistics<>(Genre.ACTION, Genre.ROMANCE, BigDecimal.ZERO)
                ),
                Arguments.of(
                        toReleaseDataMapper,
                        new Statistics<>(
                                LocalDate.of(2012, 4, 20),
                                LocalDate.of(2023, 6, 6),
                                BigDecimal.ZERO
                        )
                ),
                Arguments.of(
                        toDurationMapper,
                        new Statistics<>(101, 148, BigDecimal.valueOf(120))
                ),
                Arguments.of(
                        toRatingMapper,
                        new Statistics<>(5.4, 8.5, BigDecimal.valueOf(6.7))
                )
        );
    }

    @Test
    @DisplayName("when extractor is null")
    void test1() {
        assertThatThrownBy(() -> movieService.getStatistics(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Extractor is null");
    }

    @SuppressWarnings("rawtypes")
    @ParameterizedTest
    @MethodSource("extractorsWithStatistics")
    @DisplayName("when extractor is not null")
    void test2(Function extractor, Statistics<?> statistics) {
        assertThat(movieService.getStatistics(extractor))
                .isEqualTo(statistics);
    }
}
