package com.app.utils;

import com.app.model.Genre;

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
