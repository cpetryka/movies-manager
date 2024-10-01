package com.app.domain.movies_management.model.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Rating {
    private final List<RatingItem> ratingItems;

    /**
     * Returns the average rating of the movie.
     *
     * @return the average rating of the movie.
     */
    public double getAverageRating() {
        return ratingItems.stream()
                .mapToDouble(RatingItem::getValue)
                .average()
                .orElse(0);
    }

    public static Rating of(RatingItem... ratingItems) {
        return new Rating(List.of(ratingItems));
    }
}
