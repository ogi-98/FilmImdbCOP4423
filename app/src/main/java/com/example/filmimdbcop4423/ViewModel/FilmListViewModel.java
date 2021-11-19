package com.example.filmimdbcop4423.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.filmimdbcop4423.Model.FilmModel;
import com.example.filmimdbcop4423.repo.FilmRepo;

import java.util.List;

public class FilmListViewModel extends ViewModel {


    private FilmRepo filmRepo;


    public FilmListViewModel() {

        filmRepo = FilmRepo.getInstance();
    }


    public LiveData<List<FilmModel>> getFilms() {
        return filmRepo.getFilms();
    }


    public void searchFilmApi(String query, int pageNum) {
        filmRepo.searchFilmApi(query,pageNum);
    }


}
