package com.app.config;

import com.app.json.deserializer.custom.LocalDateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@ComponentScan("com.app")
public class AppConfig {
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
    }
}