package com.app.domain.repository;

import com.app.domain.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getMovies();
    List<Movie> getMoviesByTitle(String title);
}
