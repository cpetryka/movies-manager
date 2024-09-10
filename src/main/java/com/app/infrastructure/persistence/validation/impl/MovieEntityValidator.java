package com.app.infrastructure.persistence.validation.impl;

import com.app.application.validation.Validator;
import com.app.infrastructure.persistence.entity.MovieEntity;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class MovieEntityValidator implements Validator<MovieEntity> {
    private final String regex;

    @Override
    public Map<String, String> validate(MovieEntity movieEntity) {
        var errors = new HashMap<String, String>();

        if(doesNotMatchesRegex(movieEntity.getTitle())) {
            errors.put("title", "string does not match regex: " + movieEntity.getTitle());
        }

        if(doesNotMatchesRegex(movieEntity.getDirector())) {
            errors.put("director", "string does not match regex: " + movieEntity.getDirector());
        }

        if(movieEntity.getCast().stream().anyMatch(this::doesNotMatchesRegex)) {
            errors.put("cast", "not all items match regex: " + movieEntity.getCast());
        }

        if(movieEntity.getDuration() <= 0) {
            errors.put("duration", "value must be positive: " + movieEntity.getDuration());
        }

        if(movieEntity.getAverageRating() < 0 || movieEntity.getAverageRating() > 10) {
            errors.put("rating", "value should be in range [0.0; 10.0]: " + movieEntity.getAverageRating());
        }

        return errors;
    }

    private boolean doesNotMatchesRegex(String expression) {
        return expression == null || !expression.matches(regex);
    }
}
