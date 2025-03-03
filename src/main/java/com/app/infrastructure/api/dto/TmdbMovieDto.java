package com.app.infrastructure.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record TmdbMovieDto(
        String title,
        List<TmdbGenreDto> genres,
        @SerializedName("release_date") String releaseDate,
        int runtime) {}