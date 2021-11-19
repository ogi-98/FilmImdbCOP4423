package com.example.filmimdbcop4423.service;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.filmimdbcop4423.AppRunner;
import com.example.filmimdbcop4423.Credentials;
import com.example.filmimdbcop4423.Model.FilmModel;
import com.example.filmimdbcop4423.Response.FilmSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class FilmApiClient {

    private MutableLiveData<List<FilmModel>> muatableFilm = new MutableLiveData<>();

    private static FilmApiClient instance;

    private DownloadRunnableFilms downloadRunnableFilms;





    public static FilmApiClient getInstance(){
        if (instance == null) {
            instance = new FilmApiClient();
        }
        return instance;
    }


    private FilmApiClient(){
        muatableFilm = new MutableLiveData<>();
    }

    public LiveData<List<FilmModel>> getFilms() {
        return muatableFilm;
    }





// Calling this method
    public void searchFilmApi(String query, Integer pageNumber){

        if (downloadRunnableFilms != null){
            downloadRunnableFilms = null;
        }

        downloadRunnableFilms = new DownloadRunnableFilms(query,pageNumber);

        final Future myHandler = AppRunner.getInstance().getMyNetwork().submit(downloadRunnableFilms);

        AppRunner.getInstance().getMyNetwork().schedule(new Runnable() {
            @Override
            public void run() {
                // cancel retrofit
                myHandler.cancel(true);
            }
        }, 7, TimeUnit.SECONDS);




    }

    private class DownloadRunnableFilms implements Runnable {

        private String query;
        private int pageNum;
        Boolean cancelRequest;

        public DownloadRunnableFilms(String query, Integer pageNum) {
            cancelRequest = false;
            this.query = query;
            this.pageNum = pageNum;
        }

        @Override
        public void run() {

            try {
                Response response = getFilms(query,pageNum).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200){
                    List<FilmModel> list = new ArrayList<>(((FilmSearchResponse)response.body()).getFilms());
                    if (pageNum == 1){


                        muatableFilm.postValue(list);


                    }else {
                        List<FilmModel> currentFilms = muatableFilm.getValue();
                        currentFilms.addAll(list);
                        muatableFilm.postValue(currentFilms);
                    }

                }else {
                    String err = response.errorBody().string();
                    Log.e("FilmApiRun","Error: "+ err);
                    muatableFilm.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                muatableFilm.postValue(null);
            }






        }
        private Call<FilmSearchResponse> getFilms(String query, Integer pageNumber){
            return Service.getFilmApi().searchFilm(
                    Credentials.getApiKey(),
                    query,
                    pageNumber.toString()
            );
        }
        private void setCancelRequest(){
            cancelRequest = true;
            Log.d("FilmApiClint","Cancelling Search");
        }
    }


}
