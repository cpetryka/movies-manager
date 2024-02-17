package com.app;

import com.app.config.AppConfig;
import com.app.json.deserializer.impl.MoviesDataJsonDeserializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var moviesDtoJsonDeserializer
                = context.getBean("moviesDataJsonDeserializer", MoviesDataJsonDeserializer.class);
        System.out.println(moviesDtoJsonDeserializer.fromJson("movies.json"));

    }
}
