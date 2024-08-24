package com.app.infrastructure.converter.movie.impl;

import com.app.infrastructure.converter.movie.FileToMoviesConverter;
import com.app.infrastructure.json.deserializer.JsonDeserializer;
import com.app.infrastructure.json.model.MovieData;
import com.app.infrastructure.json.model.MoviesData;
import com.app.domain.model.Movie;
import com.app.application.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonFileToMoviesConverter implements FileToMoviesConverter {
    private final JsonDeserializer<MoviesData> moviesDataJsonDeserializer;
    private final Validator<MovieData> movieDataValidator;

    @Override
    public List<Movie> convert(String filename) {
        return moviesDataJsonDeserializer
                .fromJson(filename)
                .movies()
                .stream()
                .filter(movieData -> Validator.validate(movieData, movieDataValidator))
                .map(MovieData::toMovie)
                .toList();
    }
}
