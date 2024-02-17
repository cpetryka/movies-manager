package com.app.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Movie {
    private final String title;
    private final Genre genre;
    private final String director;
    private final LocalDate releaseDate;
    private final List<String> cast;
    private final int duration;
    private final double rating;
}
