package com.app.model;

import com.app.utils.MovieCriteria;

import java.time.LocalDate;
import java.util.function.Predicate;

public interface Predicates {
    static Predicate<Movie> hasReleaseDateBetweenPredicate(LocalDate minDate, LocalDate maxDate) {
        return movie -> movie.hasReleaseDateBetween(minDate, maxDate);
    }

    static Predicate<Movie> matchesCriteriaPredicate(MovieCriteria movieCriteria) {
        return movie -> movie.matchesCriteria(movieCriteria);
    }
}
