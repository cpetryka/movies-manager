package com.app.model;

import java.time.LocalDate;
import java.util.function.Predicate;

public interface Predicates {
    static Predicate<Movie> hasReleaseDateBetweenPredicate(LocalDate minDate, LocalDate maxDate) {
        return movie -> movie.hasReleaseDateBetween(minDate, maxDate);
    }
}
