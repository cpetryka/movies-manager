package com.app.domain.movies_management.model;

import com.app.domain.movies_management.model.type.Genre;

import java.time.LocalDate;
import java.util.List;

public record MovieCriteria(
        List<Genre> requiredGenres,
        LocalDate requiredReleaseDateMin,
        LocalDate requiredReleaseDateMax,
        List<String> requiredCast,
        int requiredDurationMin,
        int requiredDurationMax,
        double requiredRatingMin
) {}
