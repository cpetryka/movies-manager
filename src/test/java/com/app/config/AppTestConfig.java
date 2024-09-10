package com.app.config;

import com.app.infrastructure.persistence.entity.MovieEntity;
import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.infrastructure.persistence.json.converter.JsonConverter;
import com.app.infrastructure.persistence.json.converter.impl.GsonConverter;
import com.app.infrastructure.persistence.json.deserializer.JsonDeserializer;
import com.app.infrastructure.persistence.json.deserializer.custom.LocalDateDeserializer;
import com.app.infrastructure.persistence.json.deserializer.impl.MoviesEntityJsonDeserializer;
import com.app.application.validation.Validator;
import com.app.infrastructure.persistence.validation.impl.MovieEntityValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.time.LocalDate;

@RequiredArgsConstructor
public class AppTestConfig {
    private final Environment environment;

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
    }

    @Bean
    public JsonConverter<MoviesEntity> jsonConverter(Gson gson) {
        return new GsonConverter<>(gson);
    }

    @Bean
    public JsonDeserializer<MoviesEntity> jsonDeserializer(JsonConverter<MoviesEntity> jsonConverter) {
        return new MoviesEntityJsonDeserializer(jsonConverter);
    }

    @Bean
    public Validator<MovieEntity> validator() {
        return new MovieEntityValidator("[A-Z\\d\\s]+");
    }
}
