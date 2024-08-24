package com.app;

import com.app.infrastructure.config.AppConfig;
import com.app.application.service.impl.MovieServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // Create a new context that will be used to get beans from the container
        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get the MovieRepositoryImpl bean from the container
        var movieService = context.getBean("movieServiceImpl", MovieServiceImpl.class);

        // Invoke methods generating a report about movies and then send it by email and save as a PDF file
        movieService.sendReportByEmail("cezaryp10@gmail.com", "Complete movies report");
        movieService.saveReportAsPdf("movies-report.pdf");
    }
}
