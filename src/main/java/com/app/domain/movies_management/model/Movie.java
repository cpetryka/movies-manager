package com.app.domain.movies_management.model;

import com.app.domain.movies_management.model.type.Genre;
import com.app.domain.movies_management.model.vo.Rating;
import com.app.domain.movies_management.model.vo.RatingItem;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class Movie {
    final String title;
    final List<Genre> genres;
    final String director;
    final LocalDate releaseDate;
    final List<Actor> cast;
    final int duration;
    final Rating rating;
    final String tmdbId;

    /**
     * Creates a new {@code Movie} instance with sorted cast members based on the provided {@code Comparator}.
     *
     * @param castComparator comparator used for sorting cast members.
     * @return A new {@code Movie} object with sorted cast members list.
     */
    public Movie sortCast(Comparator<Actor> castComparator) {
        return Movie
                .builder()
                .title(this.title)
                .genres(this.genres)
                .director(this.director)
                .releaseDate(this.releaseDate)
                .cast(this.cast.stream().sorted(castComparator).toList())
                .duration(this.duration)
                .rating(this.rating)
                .tmdbId(this.tmdbId)
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
    public Double calculateRatingDifference(Rating otherRating) {
        return Math.abs(this.rating.getAverageRating() - otherRating.getAverageRating());
    }

    /**
     * Checks if the movie matches the specified criteria.
     *
     * @param movieCriteria The specified criteria.
     * @return true if the movie matches the criteria, false otherwise.
     */
    public boolean matchesCriteria(MovieCriteria movieCriteria) {
        var matchesGenre = new HashSet<>(this.genres).containsAll(movieCriteria.requiredGenres());
        var matchesReleaseDate = this.releaseDate.isAfter(movieCriteria.requiredReleaseDateMin())
                && this.releaseDate.isBefore(movieCriteria.requiredReleaseDateMax());
        var containsCastMembers = new HashSet<>(this.cast.stream().map(Actor::getFullName).toList())
                .containsAll(movieCriteria.requiredCast());
        var matchesDuration = this.duration >= movieCriteria.requiredDurationMin()
                && this.duration <= movieCriteria.requiredDurationMax();
        var matchesRating = this.rating.getAverageRating() >= movieCriteria.requiredRatingMin();

        return matchesGenre && matchesReleaseDate && containsCastMembers && matchesDuration && matchesRating;
    }

    /**
     * Checks if the movie contains all the specified keywords.
     *
     * @param keywords The specified keywords.
     * @return true if the movie contains all the keywords, false otherwise.
     */
    public boolean matchesKeywords(List<String> keywords) {
        String selectedMovieDataString = (this.title + " " + this.director + " "
                + this.cast.stream().map(Actor::getFullName).toList()).toLowerCase();

        return keywords
                .stream()
                // Convert the keywords to uppercase and check if the selected movie data string contains them.
                .allMatch(keyword -> selectedMovieDataString.contains(keyword.toLowerCase()));
    }

    /**
     * Checks if the movie's title is equal to the specified title.
     *
     * @param title The specified title.
     * @return true if the movie's title is equal to the specified title, false otherwise.
     */
    public boolean isTitleEqualTo(String title) {
        return this.title.equals(title);
    }

    @Override
    public String toString() {
        return "Movie(" +
                "title=" + title +
                ", genres=" + genres +
                ", director=" + director +
                ", releaseDate=" + releaseDate +
                ", cast=" + cast +
                ", duration=" + duration +
                ", rating=" + rating.getAverageRating() +
                ')';
    }

    public void addReview(RatingItem ratingItem) {
        this.rating.addRatingItem(ratingItem);
    }
}
