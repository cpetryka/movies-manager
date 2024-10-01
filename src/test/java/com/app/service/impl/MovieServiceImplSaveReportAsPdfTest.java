package com.app.service.impl;

import com.app.application.service.impl.MovieServiceImpl;
import com.app.domain.movies_management.model.repository.MovieRepository;
import com.app.application.service.HtmlService;
import com.app.application.service.PdfService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieServiceImplSaveReportAsPdfTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private HtmlService htmlService;

    @Mock
    private PdfService pdfService;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void beforeEach() {
        when(movieRepository.getMovies())
                .thenReturn(MOVIES_LIST);
    }

    @Test
    @DisplayName("when pdf file path is null")
    void test1() {
        assertThatThrownBy(() -> movieService.saveReportAsPdf(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("PDF file path is null or empty");
    }

    @Test
    @DisplayName("when pdf file path is empty")
    void test2() {
        assertThatThrownBy(() -> movieService.saveReportAsPdf(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("PDF file path is null or empty");
    }

    @Test
    @DisplayName("when report has been saved correctly")
    void test3() {
        doNothing()
                .when(pdfService)
                .convertHtmlContentToPdfFile(anyString(), anyString());

        assertDoesNotThrow(() -> movieService.saveReportAsPdf("movies-report.pdf"));

        verify(pdfService, times(1))
                .convertHtmlContentToPdfFile(anyString(), anyString());
    }
}
