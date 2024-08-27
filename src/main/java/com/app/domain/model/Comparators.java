package com.app.domain.model;

import java.util.Comparator;
import java.util.function.Function;

public interface Comparators {
    private static <T, U extends Comparable<U>> Comparator<T> generalComparator(
            Function<T, U> extractor, boolean descending) {
        return descending
                ? Comparator.comparing(extractor, Comparator.reverseOrder())
                : Comparator.comparing(extractor);
    }

    Comparator<Movie> byTitleComparator = generalComparator(movie -> movie.title, false);
    Comparator<Movie> byTitleComparatorDesc = generalComparator(movie -> movie.title, true);

    Comparator<Movie> byReleaseDateComparator = generalComparator(movie -> movie.releaseDate, false);
    Comparator<Movie> byReleaseDateComparatorDesc = generalComparator(movie -> movie.releaseDate, true);

    Comparator<Movie> byRatingComparator = generalComparator(movie -> movie.rating.getAverageRating(), false);
    Comparator<Movie> byRatingComparatorDesc = generalComparator(movie -> movie.rating.getAverageRating(), true);
}
