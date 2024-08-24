package com.app.domain.utils;

import com.app.domain.model.Genre;

import java.time.LocalDate;
import java.util.List;

public record MovieCriteria(
        Genre requiredGenre,
        LocalDate requiredReleaseDateMin,
        LocalDate requiredReleaseDateMax,
        List<String> requiredCast,
        int requiredDurationMin,
        int requiredDurationMax,
        double requiredRatingMin
) {}
