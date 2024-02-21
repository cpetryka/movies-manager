package com.app.model;

import java.util.function.Function;

public interface Mappers {
    Function<Movie, Genre> toGenreMapper = movie -> movie.genre;
    Function<Movie, String> toDirectorMapper = movie -> movie.director;
}
