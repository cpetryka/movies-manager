package com.app.service;

import com.app.model.Movie;
import com.app.model.MovieAdditionalInfo;
import com.app.utils.MinMax;
import com.app.utils.Statistics;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface MovieService {
    List<Movie> sortBy(Comparator<Movie> movieComparator);
    List<Movie> findAllBy(Predicate<Movie> predicate);
    <T> Map<T, Long> countBy(Function<Movie, T> classifier);
    <T, U> Map<T, MinMax<List<Movie>>> groupAndFindMinMaxByCriteria(Function<Movie, T> groupingFunction,
            Function<Movie, U> minMaxGroupingFunction, Comparator<U> minMaxComparator);
    <T extends Comparable<T>> Statistics<T> getStatistics(Function<Movie, T> extractor);
    List<Movie> sortCast(Comparator<String> castComparator);
    Map<String, List<Movie>> groupByCastMembers(Comparator<List<Movie>> moviesComparator);
    List<Movie> findMoviesClosestToCriteria(Comparator<Movie> movieComparator);
    List<MovieAdditionalInfo> getAdditionalInfoAboutMovieByTitle(String title);
    void sendReportByEmail(String emailTo, String subject);
    void saveReportAsPdf(String pdfFilePath);
}
