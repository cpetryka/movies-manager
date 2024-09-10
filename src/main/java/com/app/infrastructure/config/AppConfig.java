package com.app.infrastructure.config;

import com.app.application.validation.Validator;
import com.app.application.validation.impl.MovieDataValidator;
import com.app.infrastructure.persistence.json.deserializer.custom.LocalDateDeserializer;
import com.app.infrastructure.persistence.json.model.MovieData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.time.LocalDate;

@Configuration
@ComponentScan("com.app")
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class AppConfig {
    private final Environment environment;

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build();
    }

    @Bean
    public Mailer mailer() {
        return MailerBuilder
                .withSMTPServer(
                        environment.getRequiredProperty("email.host"),
                        environment.getRequiredProperty("email.port", Integer.class),
                        environment.getRequiredProperty("email.username"),
                        environment.getRequiredProperty("email.password"))
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .async()
                .buildMailer();
    }

    @Bean
    public Validator<MovieData> validator() {
        return new MovieDataValidator(environment.getRequiredProperty("validation.regex"));
    }
}