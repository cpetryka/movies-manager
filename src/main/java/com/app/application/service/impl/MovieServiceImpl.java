package com.app.application.service.impl;

import com.app.application.service.*;
import com.app.domain.model.*;
import com.app.domain.repository.MovieRepository;
import com.app.domain.utils.MinMax;
import com.app.domain.utils.MovieCriteria;
import com.app.domain.utils.Statistics;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.app.domain.model.Comparators.byTitleComparator;
import static com.app.domain.model.Mappers.*;
import static com.app.domain.model.Predicates.hasReleaseDateBetweenPredicate;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final HttpClientService httpClientService;
    private final HtmlService htmlService;
    private final EmailService emailService;
    private final PdfService pdfService;

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

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

    /**
     * Retrieves additional information about a movie from the external API based on the movie title.
     *
     * @param title The title of the movie for which additional information will be retrieved.
     * @return A list of {@code MovieAdditionalInfo} objects containing additional information about the movie.
     */
    @Override
    public List<MovieAdditionalInfo> getAdditionalInfoAboutMovieByTitle(String title) {
        return movieRepository
                .getMoviesByTitle(title)
                .stream()
                .map(movie -> httpClientService.get(
                        "https://api.themoviedb.org/3/movie/%s?api_key=%s".formatted(toTmdbIdMapper.apply(movie), tmdbApiKey),
                        new TypeToken<MovieAdditionalInfo>() {}
                ))
                .toList();
    }

    /**
     * Generates a report containing the results of all service methods in HTML format.
     *
     * @return A string containing the report in HTML format.
     */
    private String generateReportInHtmlFormat() {
        return """
                <html>
                    <head>
                        <style>
                            body {
                                font-family: Arial, Helvetica, sans-serif;
                                font-size: 13px;
                            }

                            .box-heading {
                                font-style: italic;
                                margin-bottom: 0;
                                margin-top: 24px;
                            }

                            .box-content p {
                                margin-top: 6px;
                            }

                            .box-content ol {
                                margin-top: 6px;
                                padding-left: 22px;
                            }
                        </style>
                    </head>
                    <body>
                        <main id="main">
                            <h1>Complete movies report</h1>
                            %s%n
                            %s%n
                            %s%n
                            %s%n
                            %s%n
                            %s%n
                            %s%n
                            %s%n
                            %s%n
                            %s%n
                            %s%n
                        </main>
                    </body>
                <html>
            """.formatted(
                htmlService.manyToHtml("Movies sorted by title", sortBy(byTitleComparator)),
                htmlService.manyToHtml("Movies with release date between 01.01.2022 and 01.01.2024",
                        findAllBy(hasReleaseDateBetweenPredicate(
                                LocalDate.of(2022, 1, 1),
                                LocalDate.of(2024, 1, 1)
                        ))),
                htmlService.pairsToHtml("Movies counted by their genre", countBy(toGenreMapper)),
                htmlService.pairsToHtml("The best and the worst movie in a certain genre",
                        groupAndFindMinMaxByCriteria(toGenreMapper, toRatingMapper, Comparator.naturalOrder())),
                htmlService.oneToHtml("Statistics based on average rating", getStatistics(toRatingMapper)),
                htmlService.manyToHtml("Movies with their cast sorted in a natural order",
                        sortCast(Comparator.naturalOrder())),
                htmlService.pairsToHtml("Movies grouped by cast members",
                        groupByCastMembers(Comparator.comparing(List::size))),
                htmlService.manyToHtml("Movies with rating closest to 6.0",
                        findMoviesClosestToCriteria(Comparator.comparing(car -> car.calculateRatingDifference(Rating.of(RatingItem.SIX_STARS))))
                ), htmlService.manyToHtml("Movies matching the provided criteria",
                        findAllBy(Predicates.matchesCriteriaPredicate(new MovieCriteria(
                                Genre.ACTION,
                                LocalDate.of(2021, 10, 10),
                                LocalDate.of(2024, 10, 10),
                                List.of("TOM HOLLAND"),
                                110,
                                150,
                                5.0
                        )))), htmlService.manyToHtml("Movies containing the following keywords 'SPIDER MAN' and 'ZENDAYA'",
                        findAllBy(Predicates.matchesKeywordsPredicate(List.of("SPIDER MAN", "ZENDAYA")))), htmlService.manyToHtml("Additional info about the movie 'UNCHARTED'",
                        getAdditionalInfoAboutMovieByTitle("UNCHARTED")));
    }

    /**
     * Sends a report (containing the results of all service methods) to the specified email address.
     *
     * @param emailTo The email address to which the report will be sent.
     * @param subject The subject of the report.
     */
    @Override
    public void sendReportByEmail(String emailTo, String subject) {
        if(emailTo == null || emailTo.isEmpty()) {
            throw new IllegalArgumentException("Email to is null or empty");
        }

        emailService.send(
                emailTo,
                subject,
                generateReportInHtmlFormat()
        );
    }

    /**
     * Saves a report (containing the results of all service methods) as a PDF file at the specified path.
     *
     * @param pdfFilePath The path where the PDF file will be saved.
     * @throws IllegalArgumentException if the provided PDF file path is null or empty.
     */
    @Override
    public void saveReportAsPdf(String pdfFilePath) {
        if(pdfFilePath == null || pdfFilePath.isEmpty()) {
            throw new IllegalArgumentException("PDF file path is null or empty");
        }

        pdfService.convertHtmlContentToPdfFile(
                generateReportInHtmlFormat(),
                pdfFilePath
        );
    }
}
