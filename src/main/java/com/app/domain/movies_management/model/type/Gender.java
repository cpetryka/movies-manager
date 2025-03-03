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

    /**
     * Returns the value of the given gender
     *
     * @return the value of the given gender
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the gender from the given string.
     *
     * @param value the string to be converted to a gender.
     * @return the gender from the given string.
     */
    public static Gender fromValue(String value) {
        final String lowerCaseValue = value.toLowerCase();

        return Arrays.stream(Gender.values())
                .filter(rating -> Objects.equals(rating.value, lowerCaseValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Incorrect gender given"));
    }
}
