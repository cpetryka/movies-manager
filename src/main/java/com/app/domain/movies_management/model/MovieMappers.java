package com.app.domain.movies_management.model;

import com.app.domain.movies_management.model.type.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public interface MovieMappers {
    Function<Movie, String> toTitleMapper = movie -> movie.title;
    Function<Movie, Genre> toGenreMapper = movie -> movie.genre;
    Function<Movie, String> toDirectorMapper = movie -> movie.director;
    Function<Movie, LocalDate> toReleaseDataMapper = movie -> movie.releaseDate;
    Function<Movie, List<Actor>> toCastMapper = movie -> movie.cast;
    Function<Movie, Integer> toDurationMapper = movie -> movie.duration;
    Function<Movie, Double> toRatingMapper = movie -> movie.rating.getAverageRating();
    Function<Movie, String> toTmdbIdMapper = movie -> movie.tmdbId;
}
