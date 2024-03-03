package com.app.model;

import java.time.LocalDate;
import java.util.function.Function;

public interface Mappers {
    Function<Movie, Genre> toGenreMapper = movie -> movie.genre;
    Function<Movie, String> toDirectorMapper = movie -> movie.director;
    Function<Movie, LocalDate> toReleaseDataMapper = movie -> movie.releaseDate;
    Function<Movie, Integer> toDurationMapper = movie -> movie.duration;
    Function<Movie, Double> toRatingMapper = movie -> movie.rating;
}
