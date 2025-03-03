package com.app.application.service;

import com.app.domain.movies_management.model.Actor;
import com.app.domain.movies_management.model.Movie;

import java.util.List;

public interface MovieDataFetcherService {
    String fetchDirector(String tmdbId);
    List<Actor> fetchCast(String tmdbId);
    Movie fetchMovie(String tmdbId);
}
