package com.app.infrastructure.persistence.converter.movie.impl;

import com.app.infrastructure.persistence.converter.movie.FileToMoviesConverter;
import com.app.infrastructure.persistence.entity.MovieEntity;
import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.infrastructure.persistence.json.deserializer.JsonDeserializer;
import com.app.domain.model.Movie;
import com.app.application.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonFileToMoviesConverter implements FileToMoviesConverter {
    private final JsonDeserializer<MoviesEntity> moviesEntityJsonDeserializer;
    private final Validator<MovieEntity> movieEntityValidator;

    @Override
    public List<Movie> convert(String filename) {
        return moviesEntityJsonDeserializer
                .fromJson(filename)
                .movies()
                .stream()
                .filter(movieData -> Validator.validate(movieData, movieEntityValidator))
                .map(MovieEntity::toMovie)
                .toList();
    }
}
