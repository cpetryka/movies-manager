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
    final String title;
    final Genre genre;
    final String director;
    final LocalDate releaseDate;
    final List<String> cast;
    final int duration;
    final double rating;
}
