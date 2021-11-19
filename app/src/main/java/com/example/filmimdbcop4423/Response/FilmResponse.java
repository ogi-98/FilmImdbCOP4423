package com.example.filmimdbcop4423.Response;

import com.example.filmimdbcop4423.Model.FilmModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilmResponse {

    @SerializedName("results")
    @Expose
    private FilmModel film;

    public FilmModel getFilm(){
        return film;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "film=" + film +
                '}';
    }


}
