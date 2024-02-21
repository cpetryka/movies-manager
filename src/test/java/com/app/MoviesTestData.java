package com.app;

import com.app.json.model.MovieData;
import com.app.json.model.MoviesData;
import com.app.model.Genre;
import com.app.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MoviesTestData {
    String MOVIES_FILENAME = "movies-test.json";

    MovieData MOVIE_1_DATA = new MovieData(
            "SPIDER MAN NO WAY HOME",
            Genre.ACTION,
            "JON WATTS",
            LocalDate.of(2021, 12, 17),
            List.of(
                    "TOM HOLLAND",
                    "ZENDAYA",
                    "BENEDICT CUMBERBATCH"
            ),
            148,
            8.5
    );

    Movie MOVIE_1 = MOVIE_1_DATA.toMovie();

    MovieData MOVIE_2_DATA = new MovieData(
            "AFTER",
            Genre.ROMANCE,
            "JENNY GAGE",
            LocalDate.of(2019, 4, 12),
            List.of(
                    "JOSEPHINE LANGFORD",
                    "HERO FIENNES TIFFIN"
            ),
            105,
            5.4
    );

    Movie MOVIE_2 = MOVIE_2_DATA.toMovie();

    MovieData MOVIE_3_DATA = new MovieData(
            "TRANSFORMERS RISE OF THE BEASTS",
            Genre.ACTION,
            "STEVEN CAPLE JR",
            LocalDate.of(2023, 6, 6),
            List.of(
                    "ANTHONY RAMOS",
                    "DOMINIQUE FISHBACK",
                    "LUNA LAUREN VELEZ"
            ),
            128,
            6.0
    );

    Movie MOVIE_3 = MOVIE_3_DATA.toMovie();

    MoviesData MOVIES_DATA = new MoviesData(List.of(MOVIE_1_DATA, MOVIE_2_DATA, MOVIE_3_DATA));

    List<Movie> MOVIES_LIST = List.of(MOVIE_1, MOVIE_2, MOVIE_3);
}
