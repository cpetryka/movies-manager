package com.app.application.validation.impl;

import com.app.application.validation.Validator;
import com.app.infrastructure.json.model.MovieData;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class MovieDataValidator implements Validator<MovieData> {
    private final String regex;

    @Override
    public Map<String, String> validate(MovieData movieData) {
        var errors = new HashMap<String, String>();

        if(doesNotMatchesRegex(movieData.title())) {
            errors.put("title", "string does not match regex: " + movieData.title());
        }

        if(doesNotMatchesRegex(movieData.director())) {
            errors.put("director", "string does not match regex: " + movieData.director());
        }

        if(movieData.cast().stream().anyMatch(this::doesNotMatchesRegex)) {
            errors.put("cast", "not all items match regex: " + movieData.cast());
        }

        if(movieData.duration() <= 0) {
            errors.put("duration", "value must be positive: " + movieData.duration());
        }

        if(movieData.getAverageRating() < 0 || movieData.getAverageRating() > 10) {
            errors.put("rating", "value should be in range [0.0; 10.0]: " + movieData.getAverageRating());
        }

        return errors;
    }

    private boolean doesNotMatchesRegex(String expression) {
        return expression == null || !expression.matches(regex);
    }
}
