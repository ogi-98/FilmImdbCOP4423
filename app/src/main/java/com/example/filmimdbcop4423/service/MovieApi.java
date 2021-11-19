package com.example.filmimdbcop4423.service;

import com.example.filmimdbcop4423.Model.FilmModel;
import com.example.filmimdbcop4423.Response.FilmSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {


    // search films
    @GET("/3/search/movie")
    Call<FilmSearchResponse> searchFilm(
        @Query("api_key") String key,
        @Query("query") String query,
        @Query("page") String page
    );

    // get specific id 299537
    @GET("3/movie/{movie_id}?")
    Call<FilmModel> getFilm(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );




}
