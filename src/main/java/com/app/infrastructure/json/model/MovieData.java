package com.app.infrastructure.json.model;

import com.app.domain.model.Genre;
import com.app.domain.model.Movie;
import com.app.domain.model.Rating;
import com.app.domain.model.RatingItem;

import java.time.LocalDate;
import java.util.List;

public record MovieData(String title, Genre genre, String director, LocalDate releaseDate,
                        List<String> cast, int duration, List<Double> ratings, String tmdbId) {

    public double getAverageRating() {
        return ratings.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }

    public Movie toMovie() {
        return Movie
                .builder()
                .title(title)
                .genre(genre)
                .director(director)
                .releaseDate(releaseDate)
                .cast(cast)
                .duration(duration)
                .rating(new Rating(ratings
                        .stream()
                        .map(RatingItem::fromValue)
                        .toList()))
                .tmdbId(tmdbId)
                .build();
    }
}
