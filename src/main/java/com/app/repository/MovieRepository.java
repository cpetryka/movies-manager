package com.app.repository;

import com.app.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    List<Movie> getMovies();
    List<Movie> getMoviesByTitle(String title);
}
