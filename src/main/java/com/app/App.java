package com.app;

import com.app.model.Genre;
import com.app.model.Movie;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {
        var movie = Movie
                .builder()
                .title("Inception")
                .genre(Genre.SCIENCE_FICTION)
                .director("Christopher Nolan")
                .releaseDate(LocalDate.of(2010, 7, 16))
                .cast(List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"))
                .duration(148)
                .rating(8.8)
                .build();

        System.out.println(movie);
    }
}
