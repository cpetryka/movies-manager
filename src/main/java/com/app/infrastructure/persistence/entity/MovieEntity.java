package com.app.infrastructure.persistence.entity;

import com.app.domain.movies_management.model.Actor;
import com.app.domain.movies_management.model.type.Genre;
import com.app.domain.movies_management.model.Movie;
import com.app.domain.movies_management.model.vo.Rating;
import com.app.domain.movies_management.model.vo.RatingItem;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class MovieEntity {
    String title;
    String genre;
    String director;
    LocalDate releaseDate;
    List<ActorEntity> cast;
    int duration;
    List<Double> ratings;
    @ToString.Exclude
    String tmdbId;

    public double getAverageRating() {
        return ratings
                .stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }

    public Movie toMovie() {
        return Movie
                .builder()
                .title(title)
                .genre(Genre.valueOf(genre))
                .director(director)
                .releaseDate(releaseDate)
                .cast(cast
                        .stream()
                        .map(ActorEntity::toActor)
                        .toList())
                .duration(duration)
                .rating(new Rating(ratings
                        .stream()
                        .map(RatingItem::fromValue)
                        .toList()))
                .tmdbId(tmdbId)
                .build();
    }
}
