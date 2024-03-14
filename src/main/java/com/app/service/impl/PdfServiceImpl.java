package com.app.service.impl;

import com.app.service.PdfService;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PdfServiceImpl implements PdfService {
    @SneakyThrows
    @Override
    public void convertHtmlContentToPdfFile(String htmlContent, String pdfFilePath) {
        if(htmlContent == null) {
            throw new IllegalArgumentException("HTML content is null");
        }

        if(pdfFilePath == null || pdfFilePath.isEmpty()) {
            throw new IllegalArgumentException("PDF file path is null or empty");
        }

        HtmlConverter.convertToPdf(
                htmlContent,
                new FileOutputStream(pdfFilePath)
        );
    }
}
