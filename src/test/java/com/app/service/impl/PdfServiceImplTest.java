package com.app.service.impl;

import com.app.application.service.PdfService;
import com.app.infrastructure.service.impl.PdfServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
class PdfServiceImplTest {
    private final PdfService pdfService = new PdfServiceImpl();
    private final static String SAMPLE_HTML_CONTENT = "<html><body><h1>Test Content</h1></body></html>";
    private final static Path SAMPLE_PDF_FILE_PATH = Path.of("pdf-report-test.pdf");

    @Test
    @DisplayName("when html content is null")
    void test1() {
        assertThatThrownBy(() -> pdfService.convertHtmlContentToPdfFile(null, SAMPLE_PDF_FILE_PATH.toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("HTML content is null");
    }

    @Test
    @DisplayName("when pdf file path is null")
    void test2() {
        assertThatThrownBy(() -> pdfService.convertHtmlContentToPdfFile(SAMPLE_HTML_CONTENT, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("PDF file path is null or empty");
    }

    @Test
    @DisplayName("when pdf file path is empty")
    void test3() {
        assertThatThrownBy(() -> pdfService.convertHtmlContentToPdfFile(SAMPLE_HTML_CONTENT, ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("PDF file path is null or empty");
    }

    @Test
    @DisplayName("when all parameters are correct")
    void test4() {
        // If the file exists, delete it
        try {
            Files.delete(SAMPLE_PDF_FILE_PATH);
        }
        catch(Exception ignored) {}

        // Create a new pdf file
        pdfService.convertHtmlContentToPdfFile(SAMPLE_HTML_CONTENT, SAMPLE_PDF_FILE_PATH.toString());

        // Verify if the file has been created
        assertTrue(Files.exists(SAMPLE_PDF_FILE_PATH));

        // If the file exists, delete it
        try {
            Files.delete(SAMPLE_PDF_FILE_PATH);
        }
        catch(Exception ignored) {}
    }
}
