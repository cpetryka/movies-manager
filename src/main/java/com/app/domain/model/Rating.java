package com.app.domain.model;

import java.util.List;

public class Rating {
    private final List<RatingItem> ratingItems;

    public Rating(List<RatingItem> ratingItems) {
        this.ratingItems = ratingItems;
    }

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
