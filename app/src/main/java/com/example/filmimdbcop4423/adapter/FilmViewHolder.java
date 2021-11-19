package com.example.filmimdbcop4423.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmimdbcop4423.R;

public class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView title, overview, votecount, voteavarage, popularity, release_date;

    ImageView imageView;

    RatingBar ratingBar;

    OnFilmListener onFilmListener;


    public FilmViewHolder(@NonNull View itemView, OnFilmListener onFilmListener) {
        super(itemView);

        this.title = itemView.findViewById(R.id.title);
        this.overview = itemView.findViewById(R.id.overview);
        this.votecount = itemView.findViewById(R.id.vote_count);
        this.voteavarage = itemView.findViewById(R.id.vote_average);
        this.popularity = itemView.findViewById(R.id.popularity);
        this.release_date = itemView.findViewById(R.id.release_date);
        this.imageView = itemView.findViewById(R.id.image);
        this.ratingBar = itemView.findViewById(R.id.ratingBar);
        this.onFilmListener = onFilmListener;

        itemView.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        onFilmListener.onFilmClick(getAdapterPosition());
    }



}
