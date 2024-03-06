package com.app.model;

import com.app.utils.MovieCriteria;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Movie {
    final String title;
    final Genre genre;
    final String director;
    final LocalDate releaseDate;
    final List<String> cast;
    final int duration;
    final double rating;

    /**
     * Creates a new {@code Movie} instance with sorted cast members based on the provided {@code Comparator}.
     *
     * @param castComparator comparator used for sorting cast members.
     * @return A new {@code Movie} object with sorted cast members list.
     */
    public Movie sortCast(Comparator<String> castComparator) {
        return Movie
                .builder()
                .title(this.title)
                .genre(this.genre)
                .director(this.director)
                .releaseDate(this.releaseDate)
                .cast(this.cast.stream().sorted(castComparator).toList())
                .duration(this.duration)
                .rating(this.rating)
                .build();
    }

    /**
     * Checks if the movie's release date is within the specified range.
     *
     * @param minDate The minimum date in the range.
     * @param maxDate The maximum date in the range.
     * @return true if the movie's release date is within the range, false otherwise.
     */
    public boolean hasReleaseDateBetween(LocalDate minDate, LocalDate maxDate) {
        return this.releaseDate.isAfter(minDate) && this.releaseDate.isBefore(maxDate);
    }

    /**
     * Calculates the difference between the movie's rating and another specified rating.
     *
     * @param otherRating The other specified rating.
     * @return The difference between the movie's rating and the other specified rating.
     */
    public Double calculateRatingDifference(Double otherRating) {
        return Math.abs(this.rating - otherRating);
    }

    /**
     * Checks if the movie matches the specified criteria.
     *
     * @param movieCriteria The specified criteria.
     * @return true if the movie matches the criteria, false otherwise.
     */
    public boolean matchesCriteria(MovieCriteria movieCriteria) {
        var matchesGenre = this.genre.equals(movieCriteria.requiredGenre());
        var matchesReleaseDate = this.releaseDate.isAfter(movieCriteria.requiredReleaseDateMin())
                && this.releaseDate.isBefore(movieCriteria.requiredReleaseDateMax());
        var containsCastMembers = new HashSet<>(this.cast).containsAll(movieCriteria.requiredCast());
        var matchesDuration = this.duration >= movieCriteria.requiredDurationMin()
                && this.duration <= movieCriteria.requiredDurationMax();
        var matchesRating = this.rating >= movieCriteria.requiredRatingMin();

        return matchesGenre && matchesReleaseDate && containsCastMembers && matchesDuration && matchesRating;
    }

    /**
     * Checks if the movie contains all the specified keywords.
     *
     * @param keywords The specified keywords.
     * @return true if the movie contains all the keywords, false otherwise.
     */
    public boolean matchesKeywords(List<String> keywords) {
        String selectedMovieDataString = this.title + " " + this.director + " " + this.cast;

        return keywords
                .stream()
                // Convert the keywords to uppercase and check if the selected movie data string contains them.
                .allMatch(keyword -> selectedMovieDataString.contains(keyword.toUpperCase()));
    }
}
