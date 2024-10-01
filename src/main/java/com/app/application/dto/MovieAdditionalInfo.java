package com.app.application.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
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

    @Override
    public String toString() {
        return "MovieAdditionalInfo(" +
                "productionCompanies=" + productionCompanies +
                ", budget=" + budget +
                ", revenue=" + revenue +
                ", overview='" + overview + '\'' +
                ", posterUrl='" + getPosterUrl() + '\'' +
                ')';
    }
}