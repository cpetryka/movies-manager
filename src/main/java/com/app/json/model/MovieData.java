package com.app.json.model;

import com.app.model.Genre;
import com.app.model.Movie;

import java.time.LocalDate;
import java.util.List;

public record MovieData(String title, Genre genre, String director, LocalDate releaseDate,
                        List<String> cast, int duration, double rating, String imbdId) {
    public Movie toMovie() {
        return Movie
                .builder()
                .title(title)
                .genre(genre)
                .director(director)
                .releaseDate(releaseDate)
                .cast(cast)
                .duration(duration)
                .rating(rating)
                .imbdId(imbdId)
                .build();
    }
}
