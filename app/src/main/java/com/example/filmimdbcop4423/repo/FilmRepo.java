package com.example.filmimdbcop4423.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.filmimdbcop4423.Model.FilmModel;
import com.example.filmimdbcop4423.service.FilmApiClient;


import java.util.List;

public class FilmRepo {


    private static FilmRepo instance;

    private FilmApiClient filmApiClient;

    public static FilmRepo getInstance() {
        if (instance == null) {
            instance = new FilmRepo();
        }
        return instance;

    }

    private FilmRepo(){
        filmApiClient = FilmApiClient.getInstance();
    }

    public LiveData<List<FilmModel>> getFilms(){ return filmApiClient.getFilms();}



    public void searchFilmApi(String query, int pagenum) {

        filmApiClient.searchFilmApi(query, pagenum);


    }



}