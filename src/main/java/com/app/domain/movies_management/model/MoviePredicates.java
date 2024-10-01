package com.app.domain.movies_management.model;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public interface MoviePredicates {
    static Predicate<Movie> hasReleaseDateBetweenPredicate(LocalDate minDate, LocalDate maxDate) {
        return movie -> movie.hasReleaseDateBetween(minDate, maxDate);
    }

    static Predicate<Movie> matchesCriteriaPredicate(MovieCriteria movieCriteria) {
        return movie -> movie.matchesCriteria(movieCriteria);
    }

    static Predicate<Movie> matchesKeywordsPredicate(List<String> keywords) {
        return movie -> movie.matchesKeywords(keywords);
    }
}
