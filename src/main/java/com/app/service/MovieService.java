package com.app.service;

import com.app.model.Movie;

import java.util.Comparator;
import java.util.List;

public interface MovieService {
    List<Movie> sortBy(Comparator<Movie>movieComparator);
}
