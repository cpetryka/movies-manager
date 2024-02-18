package com.app.config;

import com.app.json.converter.JsonConverter;
import com.app.json.converter.impl.GsonConverter;
import com.app.json.deserializer.JsonDeserializer;
import com.app.json.deserializer.custom.LocalDateDeserializer;
import com.app.json.deserializer.impl.MoviesDataJsonDeserializer;
import com.app.json.model.MovieData;
import com.app.json.model.MoviesData;
import com.app.validation.Validator;
import com.app.validation.impl.MovieDataValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

public class AppTestConfig {
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
        return new MovieDataValidator();
    }
}
