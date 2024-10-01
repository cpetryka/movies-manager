package com.app.domain.movies_management.model.repository;

import com.app.domain.movies_management.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getMovies();
    List<Movie> getMoviesByTitle(String title);
}
