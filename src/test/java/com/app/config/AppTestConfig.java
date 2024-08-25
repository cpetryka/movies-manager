package com.app.config;

import com.app.infrastructure.json.converter.JsonConverter;
import com.app.infrastructure.json.converter.impl.GsonConverter;
import com.app.infrastructure.json.deserializer.JsonDeserializer;
import com.app.infrastructure.json.deserializer.custom.LocalDateDeserializer;
import com.app.infrastructure.json.deserializer.impl.MoviesDataJsonDeserializer;
import com.app.infrastructure.json.model.MovieData;
import com.app.infrastructure.json.model.MoviesData;
import com.app.application.validation.Validator;
import com.app.application.validation.impl.MovieDataValidator;
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
    public JsonConverter<MoviesData> jsonConverter(Gson gson) {
        return new GsonConverter<>(gson);
    }

    @Bean
    public JsonDeserializer<MoviesData> jsonDeserializer(JsonConverter<MoviesData> jsonConverter) {
        return new MoviesDataJsonDeserializer(jsonConverter);
    }

    @Bean
    public Validator<MovieData> validator() {
        return new MovieDataValidator("[A-Z\\d\\s]+");
    }
}
