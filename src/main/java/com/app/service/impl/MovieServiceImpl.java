package com.app.service.impl;

import com.app.model.Movie;
import com.app.repository.MovieRepository;
import com.app.service.MovieService;
import com.app.utils.MinMax;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    /**
     * Sorts a list of {@code Movie} objects based on the provided criterion.
     *
     * @param movieComparator The criterion for sorting {@code Movie} objects. It's an object implementing the
     *                        {@link Comparator<Movie>} interface, which defines how two {@code Movie} objects
     *                        are compared to each other.
     * @return A list of {@code Movie} objects sorted based on the given criterion.
     * @throws IllegalArgumentException if the provided movie comparator is null.
     */
    @Override
    public List<Movie> sortBy(Comparator<Movie> movieComparator) {
        if(movieComparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }

        return movieRepository
                .getMovies()
                .stream()
                .sorted(movieComparator)
                .toList();
    }

    /**
     * Counts the number of {@code Movie} objects based on the provided classifier.
     *
     * @param <T>        the type of the key in the resulting map, determined by the classifier's output.
     * @param classifier a {@link Function} interface that takes a {@code Movie} object and returns
     *                   a value of type {@code T} that will be used as a key in the resulting map.
     * @return A map with keys of type {@code T} and values representing the number of {@code Movie}
     *         objects that match each key.
     * @throws IllegalArgumentException if the provided classifier is null.
     */
    @Override
    public <T> Map<T, Long> countBy(Function<Movie, T> classifier) {
        if(classifier == null) {
            throw new IllegalArgumentException("Classifier is null");
        }

        return movieRepository
                .getMovies()
                .stream()
                .collect(Collectors.groupingBy(classifier, Collectors.counting()));
    }

    /**
     * Groups {@code Movie} objects by specified criteria and then finds the minimum
     * and maximum values for each group based on provided another criteria.
     *
     * @param <T>                    the type of the grouping key.
     * @param <U>                    the type of the min-max key.
     * @param groupingFunction       a function that produces the key used to group {@code Movie} objects.
     * @param minMaxGroupingFunction a function that produces the key used to group {@code Movie} objects for each group.
     * @param minMaxComparator a comparator based on which min and max are determined.
     * @return a map where keys are produced by the grouping function and values are {@code MinMax} objects
     * containing the minimum and maximum values based on the comparator for each group.
     * @throws IllegalArgumentException if any of the provided functions or comparator is null.
     * @see MinMax
     */
    @Override
    public <T, U> Map<T, MinMax<List<Movie>>> groupAndFindMinMaxByCriteria(Function<Movie, T> groupingFunction,
            Function<Movie, U> minMaxGroupingFunction, Comparator<U> minMaxComparator) {
        if(groupingFunction == null) {
            throw new IllegalArgumentException("Grouping function is null");
        }

        if(minMaxGroupingFunction == null) {
            throw new IllegalArgumentException("Min max grouping function is null");
        }

        if(minMaxComparator == null) {
            throw new IllegalArgumentException("Min max comparator is null");
        }

        return movieRepository
                .getMovies()
                .stream()
                .collect(Collectors.groupingBy(
                        groupingFunction,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(minMaxGroupingFunction),
                                groupedMovies -> {
                                    var minKey = groupedMovies
                                            .keySet()
                                            .stream()
                                            .min(minMaxComparator)
                                            .orElseThrow();

                                    var maxKey = groupedMovies
                                            .keySet()
                                            .stream()
                                            .max(minMaxComparator)
                                            .orElseThrow();

                                    return new MinMax<>(
                                            groupedMovies.get(minKey),
                                            groupedMovies.get(maxKey)
                                    );
                                }
                        )
                ));
    }
}
