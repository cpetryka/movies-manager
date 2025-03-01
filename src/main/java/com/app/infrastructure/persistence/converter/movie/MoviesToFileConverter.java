package com.app.infrastructure.persistence.converter.movie;

import com.app.domain.movies_management.model.Movie;
import com.app.infrastructure.persistence.converter.Converter;

import java.util.List;

public interface MoviesToFileConverter extends Converter<List<Movie>, String> {
}
