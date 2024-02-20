package com.app.service.impl;

import com.app.model.Movie;
import com.app.repository.MovieRepository;
import com.app.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> sortBy(Comparator<Movie> movieComparator) {
        if(movieComparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }

        return movieRepository
                .getMovies()
                .stream()
                .sorted(movieComparator)
                .toList();
    }
}
