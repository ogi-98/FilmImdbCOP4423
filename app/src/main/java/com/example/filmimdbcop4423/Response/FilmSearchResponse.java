package com.example.filmimdbcop4423.Response;


import com.example.filmimdbcop4423.Model.FilmModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// For get search result
public class FilmSearchResponse {
    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<FilmModel> films;

    public int getTotal_count() {
        return total_count;
    }

    public List<FilmModel> getFilms() {
        return films;
    }

    @Override
    public String toString() {
        return "FilmSearchResponse{" +
                "total_count=" + total_count +
                ", films=" + films +
                '}';
    }
}
