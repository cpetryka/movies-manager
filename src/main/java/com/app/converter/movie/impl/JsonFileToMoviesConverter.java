package com.app.converter.movie.impl;

import com.app.converter.movie.FileToMoviesConverter;
import com.app.json.deserializer.JsonDeserializer;
import com.app.json.model.MovieData;
import com.app.json.model.MoviesData;
import com.app.model.Movie;
import com.app.validation.Validator;
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
