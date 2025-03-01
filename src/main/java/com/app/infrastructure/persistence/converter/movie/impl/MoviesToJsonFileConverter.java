package com.app.infrastructure.persistence.converter.movie.impl;

import com.app.domain.movies_management.model.Movie;
import com.app.infrastructure.persistence.converter.movie.MoviesToFileConverter;
import com.app.infrastructure.persistence.entity.MovieEntity;
import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.infrastructure.persistence.json.serializer.JsonSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MoviesToJsonFileConverter implements MoviesToFileConverter {
    private final JsonSerializer<MoviesEntity> moviesEntityJsonSerializer;

    @Value("${movies.data.output.filename}")
    private String filename;

    @Override
    public String convert(List<Movie> movies) {
        moviesEntityJsonSerializer.toJson(
                new MoviesEntity(movies.stream().map(Movie::toMovieEntity).toList()),
                filename
        );

        return "";
    }
}
