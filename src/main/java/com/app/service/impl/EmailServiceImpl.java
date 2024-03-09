package com.app.service.impl;

import com.app.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "MainAsyncLogger")
public class EmailServiceImpl implements EmailService {
    @Value("${email.from}")
    private String emailFrom;

    private final Mailer mailer;

    @Override
    public void send(String emailTo, String subject, String content) {
        if(emailTo == null || emailTo.isEmpty()) {
            throw new IllegalArgumentException("Email to is null or empty");
        }

        var email = EmailBuilder
                .startingBlank()
                .from(emailFrom)
                .to(emailTo)
                .withSubject(subject)
                .withHTMLText(content)
                .buildEmail();

        mailer
                .sendMail(email)
                .thenAccept(res -> log.info("Email has been sent"))
                .exceptionally(ex -> {
                    log.error("Email error: %s".formatted(ex.getMessage()));
                    return null;
                })
                .whenComplete((res, err) -> mailer.shutdownConnectionPool());
    }
}
