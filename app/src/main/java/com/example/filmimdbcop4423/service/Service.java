package com.example.filmimdbcop4423.service;

import com.example.filmimdbcop4423.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Credentials.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create());


    private static Retrofit retrofit = retrofitBuilder.build();
    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getFilmApi() {
        return movieApi;
    }


}
