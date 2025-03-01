package com.app.infrastructure.persistence.converter.movie;

import com.app.infrastructure.persistence.converter.Converter;
import com.app.domain.movies_management.model.Movie;

import java.util.List;

public interface FileToMoviesConverter extends Converter<String, List<Movie>> { }
