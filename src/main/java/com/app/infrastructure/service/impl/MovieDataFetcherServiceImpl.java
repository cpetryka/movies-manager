package com.app.infrastructure.service.impl;

import com.app.application.service.HttpClientService;
import com.app.application.service.MovieDataFetcherService;
import com.app.domain.movies_management.model.Actor;
import com.app.domain.movies_management.model.Movie;
import com.app.domain.movies_management.model.type.Gender;
import com.app.domain.movies_management.model.type.Genre;
import com.app.domain.movies_management.model.vo.Rating;
import com.app.infrastructure.api.dto.TmdbActorDto;
import com.app.infrastructure.api.dto.TmdbMovieDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "MainAsyncLogger")
public class MovieDataFetcherServiceImpl implements MovieDataFetcherService {
    private final Gson gson;
    private final HttpClientService httpClientService;

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    /*
     * Fetches the director of a movie with the given TMDB ID.
     * If the director is not found, returns "Unknown".
     *
     * @param tmdbId the TMDB ID of the movie.
     * @return the director of the movie.
     */
    @Override
    public String fetchDirector(String tmdbId) {
        var url = "https://api.themoviedb.org/3/movie/%s/credits?api_key=%s".formatted(tmdbId, tmdbApiKey);

        JsonObject jsonObject;

        try {
            jsonObject = httpClientService.get(url, JsonObject.class);
        } catch (Exception e) {
            log.error("Error fetching director details: " + e.getMessage());
            return "Unknown";
        }

        if (jsonObject.has("crew")) {
            for (var element : jsonObject.getAsJsonArray("crew")) {
                var crewMember = element.getAsJsonObject();

                if ("Director".equals(crewMember.get("job").getAsString())) {
                    return crewMember.get("name").getAsString();
                }
            }
        }

        return "Unknown";
    }

    /*
     * Fetches the cast of a movie with the given TMDB ID.
     *
     * @param tmdbId the TMDB ID of the movie.
     * @return the cast of the movie.
     */
    public List<Actor> fetchCast(String tmdbId) {
        var jsonResponse = httpClientService.getRaw(
                "https://api.themoviedb.org/3/movie/%s/credits?api_key=%s".formatted(tmdbId, tmdbApiKey)
        );

        JsonObject jsonObject;

        try {
            jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        } catch (JsonSyntaxException e) {
            log.error("Error parsing JSON response for cast: " + e.getMessage());
            return Collections.emptyList();
        }

        var cast = new ArrayList<Actor>();

        if (jsonObject.has("cast")) {
            for (JsonElement element : jsonObject.getAsJsonArray("cast")) {
                var castMember = element.getAsJsonObject();

                var personId = castMember.get("id").getAsInt();
                var knownForDepartment = castMember.get("known_for_department").getAsString();

                if(knownForDepartment.equals("Acting")) {
                    var actor = fetchActorDetails(personId);

                    if (actor != null) {
                        cast.add(actor);
                    }
                }
            }
        }

        return cast;
    }

    /*
     * Fetches the details of an actor with the given TMDB ID.
     *
     * @param personId the TMDB ID of the actor.
     * @return the actor details.
     */
    private Actor fetchActorDetails(int personId) {
        var url = "https://api.themoviedb.org/3/person/%d?api_key=%s".formatted(personId, tmdbApiKey);

        TmdbActorDto actorDto;

        try {
            actorDto = httpClientService.get(url, TmdbActorDto.class);
        } catch (Exception e) {
            log.error("Error fetching actor details: " + e.getMessage());
            return null;
        }

        return Actor
                .builder()
                .name(actorDto.name())
                .gender(actorDto.gender() == 1 ? Gender.FEMALE : Gender.MALE)
                .birthDate(actorDto.birthday() != null ? LocalDate.parse(actorDto.birthday(), DateTimeFormatter.ISO_LOCAL_DATE) : null)
                .deathDate(actorDto.deathday() != null ? LocalDate.parse(actorDto.deathday(), DateTimeFormatter.ISO_LOCAL_DATE) : null)
                .biography(actorDto.biography())
                .build();
    }

    /*
     * Fetches the details of a movie with the given TMDB ID.
     *
     * @param tmdbId the TMDB ID of the movie.
     * @return the movie details.
     */
    public Movie fetchMovie(String tmdbId) {
        var url = "https://api.themoviedb.org/3/movie/%s?api_key=%s".formatted(tmdbId, tmdbApiKey);

        try {
            var movieDto = httpClientService.get(url, TmdbMovieDto.class);
            var director = fetchDirector(tmdbId);
            var cast = fetchCast(tmdbId);

            return Movie.builder()
                    .title(movieDto.title())
                    .genres(movieDto.genres().stream()
                            .map(genreDto -> Genre.fromString(genreDto.name()))
                            .filter(Objects::nonNull)
                            .toList())
                    .director(director)
                    .releaseDate(movieDto.releaseDate() != null ? LocalDate.parse(movieDto.releaseDate(), DateTimeFormatter.ISO_LOCAL_DATE) : null)
                    .cast(cast)
                    .duration(movieDto.runtime())
                    .rating(Rating.empty())
                    .tmdbId(tmdbId)
                    .build();
        } catch (Exception e) {
            log.error("Error fetching movie details: " + e.getMessage());
            return null;
        }
    }
}
