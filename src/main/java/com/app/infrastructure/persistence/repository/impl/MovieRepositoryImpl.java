package com.app.infrastructure.persistence.repository.impl;

import com.app.domain.movies_management.model.repository.MovieRepository;
import com.app.infrastructure.persistence.converter.movie.FileToMoviesConverter;
import com.app.domain.movies_management.model.Movie;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {
    @Value("${movies.data.filename}")
    private String filename;

    private final FileToMoviesConverter fileToMoviesConverter;

    private List<Movie> movies;

    @PostConstruct
    private void setUp() {
        this.movies = fileToMoviesConverter.convert(filename);
    }

    @Override
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public List<Movie> getMoviesByTitle(String title) {
        return movies
                .stream()
                .filter(movie -> movie.isTitleEqualTo(title))
                .toList();
    }
}
