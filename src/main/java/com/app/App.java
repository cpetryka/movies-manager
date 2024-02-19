package com.app;

import com.app.config.AppConfig;
import com.app.json.deserializer.impl.MoviesDataJsonDeserializer;
import com.app.repository.impl.MovieRepositoryImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var movieRepositoryImpl
                = context.getBean("movieRepositoryImpl", MovieRepositoryImpl.class);
        System.out.println(movieRepositoryImpl.getMovies());
    }
}
