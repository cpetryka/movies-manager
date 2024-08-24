package com.app.infrastructure.converter.movie;

import com.app.infrastructure.converter.Converter;
import com.app.domain.model.Movie;

import java.util.List;

public interface FileToMoviesConverter extends Converter<String, List<Movie>> { }
