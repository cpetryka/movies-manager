package com.app.service.impl;

import com.app.repository.MovieRepository;
import com.app.service.EmailService;
import com.app.service.HtmlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.app.MoviesTestData.MOVIES_LIST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class HtmlServiceImplSendReportByEmailTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private HtmlService htmlService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void beforeEach() {
        when(movieRepository.getMovies())
                .thenReturn(MOVIES_LIST);
    }

    @Test
    @DisplayName("when emailTo parameter is null")
    void test1() {
        assertThatThrownBy(() -> movieService.sendReportByEmail(null, ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email to is null or empty");
    }

    @Test
    @DisplayName("when emailTo parameter is empty")
    void test2() {
        assertThatThrownBy(() -> movieService.sendReportByEmail("", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email to is null or empty");
    }

    @Test
    @DisplayName("when report has been sent correctly")
    void test3() {
        // Set up mock methods
        doNothing()
                .when(emailService)
                .send(anyString(), anyString(), anyString());

        when(htmlService.oneToHtml(anyString(), any()))
                .thenReturn("");

        when(htmlService.manyToHtml(anyString(), anyList()))
                .thenReturn("");

        when(htmlService.pairsToHtml(anyString(), anyMap()))
                .thenReturn("");

        // Test if method does not throw any exception when all parameters are correct
        assertDoesNotThrow(() -> movieService.sendReportByEmail("test@email.com", ""));

        // Test if certain methods were called expected number of times
        verify(emailService, times(1))
                .send(anyString(), anyString(), anyString());

        verify(htmlService, times(1))
                .oneToHtml(anyString(), any());

        verify(htmlService, times(6))
                .manyToHtml(anyString(), anyList());

        verify(htmlService, times(3))
                .pairsToHtml(anyString(), anyMap());
    }

}
