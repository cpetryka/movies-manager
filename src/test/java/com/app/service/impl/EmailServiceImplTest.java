package com.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simplejavamail.api.mailer.Mailer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class EmailServiceImplTest {
    @Test
    @DisplayName("when emailTo parameter is null")
    void test1() {
        // Mock and inject a Mailer instance
        Mailer mailer = mock(Mailer.class);
        EmailServiceImpl emailService = new EmailServiceImpl(mailer);

        // Verify if IllegalArgumentException is thrown
        assertThatThrownBy(() -> emailService.send(
                null,
                "Test subject",
                "<html><body><h1>Test Content</h1></body></html>"
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email to is null or empty");
    }

    @Test
    @DisplayName("when emailTo parameter is empty")
    void test2() {
        // Mock and inject a Mailer instance
        Mailer mailer = mock(Mailer.class);
        EmailServiceImpl emailService = new EmailServiceImpl(mailer);

        // Verify if IllegalArgumentException is thrown
        assertThatThrownBy(() -> emailService.send(
                "",
                "Test subject",
                "<html><body><h1>Test Content</h1></body></html>"
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email to is null or empty");
    }
}
