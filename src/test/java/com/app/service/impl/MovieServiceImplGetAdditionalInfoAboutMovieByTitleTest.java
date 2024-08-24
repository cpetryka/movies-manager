package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.model.MovieAdditionalInfo;
import com.app.domain.model.ProductionCompany;
import com.app.domain.repository.MovieRepository;
import com.app.application.service.HttpClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static com.app.MoviesTestData.MOVIE_4;
import static org.mockito.ArgumentMatchers.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieServiceImplGetAdditionalInfoAboutMovieByTitleTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    @DisplayName("when title parameter is null")
    void test1() {
        String title = null;

        when(movieRepository.getMoviesByTitle(title)).thenReturn(List.of());

        assertThat(movieService.getAdditionalInfoAboutMovieByTitle(title))
                .isEqualTo(List.of());
    }

    @Test
    @DisplayName("when title parameter is empty")
    void test2() {
        String title = "";

        when(movieRepository.getMoviesByTitle(title)).thenReturn(List.of());

        assertThat(movieService.getAdditionalInfoAboutMovieByTitle(title))
                .isEqualTo(List.of());
    }

    @Test
    @DisplayName("when title parameter is provided and movie is found")
    void test3() {
        String title = "UNCHARTED";

        when(movieRepository.getMoviesByTitle(title))
                .thenReturn(List.of(MOVIE_4));

        var movieAdditionalInfo = new MovieAdditionalInfo(
                List.of(
                        new ProductionCompany("Columbia Pictures", "US"),
                        new ProductionCompany("Atlas Entertainment", "US"),
                        new ProductionCompany("PlayStation Productions", "US"),
                        new ProductionCompany("Arad Productions", "US")
                ),
                120000000,
                407100000,
                "A young street-smart, Nathan Drake and his wisecracking partner Victor “Sully” Sullivan embark on a dangerous pursuit of “the greatest treasure never found” while also tracking clues that may lead to Nathan’s long-lost brother.",
                "/rJHC1RUORuUhtfNb4Npclx0xnOf.jpg"
                );

        when(httpClientService.get(anyString(), any()))
                .thenReturn(movieAdditionalInfo); // Simplified return logic for demonstration.

        assertThat(movieService.getAdditionalInfoAboutMovieByTitle(title))
                .isEqualTo(List.of(movieAdditionalInfo));
    }
}
