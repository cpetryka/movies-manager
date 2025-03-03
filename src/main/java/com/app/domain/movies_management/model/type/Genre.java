package com.app.domain.movies_management.model.type;

public enum Genre {
    ACTION("action"),
    COMEDY("comedy"),
    DRAMA("drama"),
    FANTASY("fantasy"),
    HORROR("horror"),
    ROMANCE("romance"),
    THRILLER("thriller"),
    SCIENCE_FICTION("science fiction");

    private final String value;

    Genre(String value) {
        this.value = value;
    }

    /**
     * Returns the value of the genre.
     *
     * @return the value of the genre.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the genre from the given string.
     *
     * @param genre the string to be converted to a genre.
     * @return the genre from the given string.
     */
    public static Genre fromString(String genre) {
        for (var g : Genre.values()) {
            if (g.getValue().equalsIgnoreCase(genre)) {
                return g;
            }
        }

        return null;
    }
}
