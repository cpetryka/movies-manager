package com.app.domain.movies_management.model.type;

import java.util.Arrays;
import java.util.Objects;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromValue(String value) {
        final String lowerCaseValue = value.toLowerCase();

        return Arrays.stream(Gender.values())
                .filter(rating -> Objects.equals(rating.value, lowerCaseValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Incorrect gender given"));
    }
}
