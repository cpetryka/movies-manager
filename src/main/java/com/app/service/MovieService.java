package com.app.service;

import com.app.model.Movie;
import com.app.utils.MinMax;
import com.app.utils.Statistics;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface MovieService {
    List<Movie> sortBy(Comparator<Movie>movieComparator);
    <T> Map<T, Long> countBy(Function<Movie, T> classifier);
    <T, U> Map<T, MinMax<List<Movie>>> groupAndFindMinMaxByCriteria(Function<Movie, T> groupingFunction,
            Function<Movie, U> minMaxGroupingFunction, Comparator<U> minMaxComparator);
    <T extends Comparable<T>> Statistics<T> getStatistics(Function<Movie, T> extractor);
    List<Movie> sortCast(Comparator<String> castComparator);
}
