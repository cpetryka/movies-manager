package com.app.converter.movie;

import com.app.converter.Converter;
import com.app.json.model.MoviesData;
import com.app.model.Movie;

import java.util.List;

public interface FileToMoviesConverter extends Converter<String, List<Movie>> { }
