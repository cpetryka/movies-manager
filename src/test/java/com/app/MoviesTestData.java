package com.app;

import com.app.infrastructure.persistence.entity.MovieEntity;
import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.domain.movies_management.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MoviesTestData {
    String MOVIES_FILENAME = "movies-test.json";

    MovieEntity MOVIE_1_ENTITY = new MovieEntity(
            "SPIDER MAN NO WAY HOME",
            "ACTION",
            "JON WATTS",
            LocalDate.of(2021, 12, 17),
            List.of(
                    "TOM HOLLAND",
                    "ZENDAYA",
                    "BENEDICT CUMBERBATCH"
            ),
            148,
            List.of(8.0, 9.0),
            "634649"
    );

    Movie MOVIE_1 = MOVIE_1_ENTITY.toMovie();

    MovieEntity MOVIE_2_ENTITY = new MovieEntity(
            "AFTER",
            "ROMANCE",
            "JENNY GAGE",
            LocalDate.of(2019, 4, 12),
            List.of(
                    "JOSEPHINE LANGFORD",
                    "HERO FIENNES TIFFIN"
            ),
            105,
            List.of(4.0, 5.0, 6.0, 7.0, 5.0),
            "537915"
    );

    Movie MOVIE_2 = MOVIE_2_ENTITY.toMovie();

    MovieEntity MOVIE_3_ENTITY = new MovieEntity(
            "TRANSFORMERS RISE OF THE BEASTS",
            "ACTION",
            "STEVEN CAPLE JR",
            LocalDate.of(2023, 6, 6),
            List.of(
                    "ANTHONY RAMOS",
                    "DOMINIQUE FISHBACK",
                    "LUNA LAUREN VELEZ"
            ),
            128,
            List.of(6.0, 7.0, 8.0, 6.0, 5.0),
            "667538"
    );

    Movie MOVIE_3 = MOVIE_3_ENTITY.toMovie();

    MovieEntity MOVIE_4_ENTITY = new MovieEntity(
            "UNCHARTED",
            "ACTION",
            "RUBEN FLEISCHER",
            LocalDate.of(2022, 2, 10),
            List.of(
                    "TOM HOLLAND",
                    "MARK WAHLBERG",
                    "SOPHIA ALI"
            ),
            116,
            List.of(6.0, 7.0, 8.0, 6.0, 5.0),
            "335787"
    );

    Movie MOVIE_4 = MOVIE_4_ENTITY.toMovie();

    MovieEntity MOVIE_5_ENTITY = new MovieEntity(
            "THE LUCKY ONE",
            "ROMANCE",
            "SCOTT HICKS",
            LocalDate.of(2012, 4, 20),
            List.of(
                    "ZAC EFRON",
                    "TAYLOR SCHILLING",
                    "BLYTHE DANNER"
            ),
            101,
            List.of(6.0, 8.0),
            "77877"
    );

    Movie MOVIE_5 = MOVIE_5_ENTITY.toMovie();

    MoviesEntity MOVIES_DATA = new MoviesEntity(List.of(MOVIE_1_ENTITY, MOVIE_2_ENTITY, MOVIE_3_ENTITY, MOVIE_4_ENTITY, MOVIE_5_ENTITY));

    List<Movie> MOVIES_LIST = List.of(MOVIE_1, MOVIE_2, MOVIE_3, MOVIE_4, MOVIE_5);
}
