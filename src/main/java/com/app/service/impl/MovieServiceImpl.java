package com.app.service.impl;

import com.app.model.Movie;
import com.app.repository.MovieRepository;
import com.app.service.MovieService;
import com.app.utils.MinMax;
import com.app.utils.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.app.model.Mappers.toCastMapper;

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
     * Finds and returns a list of {@code Movie} objects that match the specified criterion.
     *
     * @param moviePredicate The predicate used to test each {@code Movie} object.
     * @return A list of {@code Movie} objects that match the specified criterion.
     */
    @Override
    public List<Movie> findAllBy(Predicate<Movie> moviePredicate) {
        if(moviePredicate == null) {
            throw new IllegalArgumentException("Movie predicate is null");
        }

        return movieRepository
                .getMovies()
                .stream()
                .filter(moviePredicate)
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

    /**
     * Finds minimum, maximum, and average values of a field extracted from each movie for a list of {@code Movie} objects.
     * This is a generic method which means it can work with any field of the {@code Movie} class that implements
     * the {@link Comparable} interface. The average is calculated specifically for {@code BigDecimal} values.
     *
     * @param <T>       the type of the field to be analyzed. This type must implement the Comparable interface.
     * @param extractor a function that extracts the value of type T from a {@code Movie} object.
     * @return an instance of the {@code Statistics} class containing the min, max, and average values.
     * @throws IllegalArgumentException if the provided extractor is null.
     * @see Statistics
     */
    @Override
    public <T extends Comparable<T>> Statistics<T> getStatistics(Function<Movie, T> extractor) {
        if(extractor == null) {
            throw new IllegalArgumentException("Extractor is null");
        }

        var movies = movieRepository.getMovies();

        T min = movies
                .stream()
                .map(extractor)
                .min(Comparator.naturalOrder())
                .orElse(null);

        T max = movies
                .stream()
                .map(extractor)
                .max(Comparator.naturalOrder())
                .orElse(null);

        BigDecimal avg = movies
                .stream()
                .map(extractor)
                // Leave only numbers
                .filter(Number.class::isInstance)
                // Convert to BigDecimal
                .map(v -> {
                    if (v instanceof BigDecimal vv) {
                        return vv;
                    }
                    return new BigDecimal(v.toString());
                })
                // Sum all the numbers
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                // Divide by the number of elements to get the average
                .divide(BigDecimal.valueOf(movies.size()), RoundingMode.HALF_UP);

        return new Statistics<>(min, max, avg);
    }

    /**
     * Sorts cast members list of each {@code Movie} object based on the provided comparator.
     *
     * @param castComparator A Comparator<String> used to sort the cast members list of each movie.
     * @return A list of {@code Movie} objects with sorted cast members lists.
     */
    @Override
    public List<Movie> sortCast(Comparator<String> castComparator) {
        if(castComparator == null) {
            throw new IllegalArgumentException("Cast comparator is null");
        }

        return movieRepository
                .getMovies()
                .stream()
                .map(movie -> movie.sortCast(castComparator))
                .toList();
    }

    /**
     * Returns a map where each key is a cast member and the value is a list of {@code Movie} objects
     * that have this cast member. The map is sorted based on the provided comparator for value.
     *
     * @param moviesComparator The comparator used to sort the map values.
     * @return A map where keys are cast members and values are lists of movies sorted based on the given comparator.
     * @throws IllegalArgumentException if the provided movies comparator is null.
     */
    @Override
    public Map<String, List<Movie>> groupByCastMembers(Comparator<List<Movie>> moviesComparator) {
        if(moviesComparator == null) {
            throw new IllegalArgumentException("Movies comparator is null");
        }

        return movieRepository
                .getMovies()
                .stream()
                // Convert each movie to a list of entries where each entry is a cast member and the movie
                .flatMap(movie -> toCastMapper
                        .apply(movie)
                        .stream()
                        .map(castMember -> new AbstractMap.SimpleEntry<>(castMember, movie))
                )
                // Group by cast member
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        // Map each cast member to a list of movies
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(moviesComparator))
                // Convert to a LinkedHashMap to preserve the order
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    /**
     * Finds and returns a list of {@code Movie} objects that are closest to the specified criterion based on the
     * provided comparator. In case more than one movie matches the criteria equally, all such movies are returned.
     *
     * @param movieComparator The criterion for finding the closest {@code Movie} objects.
     * @return A list of {@code Movie} objects that are closest to the specified criterion.
     * @throws IllegalArgumentException if the provided movie comparator is null.
     */
    @Override
    public List<Movie> findMoviesClosestToCriteria(Comparator<Movie> movieComparator) {
        if(movieComparator == null) {
            throw new IllegalArgumentException("Movie comparator is null");
        }

        var movies = movieRepository.getMovies();

        var minMovie = movies
                .stream()
                .min(movieComparator)
                .orElseThrow(() -> new IllegalStateException("Cannot find movies by criteria"));

        return movies
                .stream()
                .filter(movie -> movieComparator.compare(movie, minMovie) == 0)
                .toList();
    }
}
