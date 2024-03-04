package com.app.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Comparator;
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
}
