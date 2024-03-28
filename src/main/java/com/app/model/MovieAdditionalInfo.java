package com.app.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
public class MovieAdditionalInfo {
    @SerializedName(value = "production_companies")
    private List<ProductionCompany> productionCompanies;
    private long budget;
    private long revenue;
    private String overview;
    @SerializedName(value = "poster_path")
    private String posterPath;

    private String getPosterUrl() {
        return "https://image.tmdb.org/t/p/original" + posterPath;
    }

    public String convertMovieAdditionalInfoToFormatedString(String title) {
        return "Additional information about the film titled '%s'".formatted(title) + "\n" +
                "Production Companies: " + productionCompanies + "\n" +
                "Budget: " + budget + "\n" +
                "Revenue: " + revenue + "\n" +
                "Overview: " + overview + "\n" +
                "Poster Url: " + getPosterUrl();
    }
}