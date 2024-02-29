package com.app.service.impl;

import com.app.model.Movie;
import com.app.repository.MovieRepository;
import com.app.service.MovieService;
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
}
