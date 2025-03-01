package com.app.domain.movies_management.model.vo;

import java.util.Arrays;

public enum RatingItem {
    ONE_STAR(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5),
    SIX_STARS(6),
    SEVEN_STARS(7),
    EIGHT_STARS(8),
    NINE_STARS(9),
    TEN_STARS(10);

    private final double value;

    RatingItem(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static RatingItem fromValue(double value) {
        final double rounded = Math.round(value);

        return Arrays.stream(RatingItem.values())
                .filter(rating -> rating.value == rounded)
                .findFirst()
                .orElse(ONE_STAR);
    }
}
