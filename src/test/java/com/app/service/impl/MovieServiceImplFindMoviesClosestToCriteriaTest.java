package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.model.Movie;
import com.app.domain.model.Rating;
import com.app.domain.model.RatingItem;
import com.app.domain.repository.MovieRepository;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.app.MoviesTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieServiceImplFindMoviesClosestToCriteriaTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void beforeEach() {
        when(movieRepository.getMovies())
                .thenReturn(MOVIES_LIST);
    }

    private static Stream<Arguments> otherRatingWithExpectedMovies() {
        return Stream.of(
                Arguments.of(
                        Rating.of(RatingItem.EIGHT_STARS, RatingItem.NINE_STARS),
                        List.of(MOVIE_1)
                ),
                Arguments.of(
                        Rating.of(RatingItem.SIX_STARS),
                        List.of(MOVIE_3, MOVIE_4)
                ),
                Arguments.of(
                        Rating.of(RatingItem.TEN_STARS, RatingItem.TEN_STARS, RatingItem.TEN_STARS, RatingItem.TEN_STARS,
                                RatingItem.FIVE_STARS, RatingItem.FIVE_STARS, RatingItem.FIVE_STARS,
                                RatingItem.ONE_STAR, RatingItem.ONE_STAR, RatingItem.TWO_STARS),
                        List.of(MOVIE_2, MOVIE_3, MOVIE_4)
                )
        );
    }

    @Test
    @DisplayName("when movie comparator is null")
    void test1() {
        assertThatThrownBy(() -> movieService.findMoviesClosestToCriteria(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Movie comparator is null");
    }

    @ParameterizedTest
    @MethodSource("otherRatingWithExpectedMovies")
    @DisplayName("when comparator checks the smallest difference between the movie's rating and another specified rating.")
    void test2(Rating otherRating, List<Movie> expectedMovies) {
        Comparator<Movie> carComparator = Comparator.comparing(car -> car.calculateRatingDifference(otherRating));

        assertThat(movieService.findMoviesClosestToCriteria(carComparator))
                .isEqualTo(expectedMovies);
    }
}
