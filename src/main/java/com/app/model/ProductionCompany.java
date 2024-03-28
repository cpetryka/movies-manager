package com.app.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductionCompany {
    private String name;
    @SerializedName(value = "origin_country")
    private String originCountry;

    private String getNotEmptyOriginCountry() {
        return !originCountry.isEmpty() ? originCountry : "N/A";
    }

    @Override
    public String toString() {
        return "ProductionCompany(" +
                "name='" + name + '\'' +
                ", originCountry='" + getNotEmptyOriginCountry() + '\'' +
                ')';
    }
}
