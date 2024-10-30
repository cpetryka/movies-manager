package com.app.domain.movies_management.model;

import java.util.Comparator;
import java.util.function.Function;

public interface ActorComparators {
    private static <T, U extends Comparable<U>> Comparator<T> generalComparator(
            Function<T, U> extractor, boolean descending) {
        return descending
                ? Comparator.comparing(extractor, Comparator.reverseOrder())
                : Comparator.comparing(extractor);
    }

    Comparator<Actor> byNameComparator = generalComparator(actor -> actor.name, false);
    Comparator<Actor> byNameComparatorDesc = generalComparator(actor -> actor.name, true);
}
