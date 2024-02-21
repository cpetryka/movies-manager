package com.app.service;

import com.app.model.Movie;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface MovieService {
    List<Movie> sortBy(Comparator<Movie>movieComparator);
    <T> Map<T, Long> countBy(Function<Movie, T> classifier);
}
