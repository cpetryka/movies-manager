package com.app.json.model;

import com.app.model.Genre;

import java.time.LocalDate;
import java.util.List;

public record MovieData(String title, Genre genre, String director, LocalDate releaseDate, List<String> cast, int duration, double rating) {}
