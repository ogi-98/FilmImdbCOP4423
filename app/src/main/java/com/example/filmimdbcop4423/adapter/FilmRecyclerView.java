package com.example.filmimdbcop4423.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmimdbcop4423.Model.FilmModel;
import com.example.filmimdbcop4423.R;

import java.util.List;

public class FilmRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<FilmModel> mFilms;
    private OnFilmListener onFilmListener;



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);

        return new FilmViewHolder(view,onFilmListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((FilmViewHolder)holder).title.setText(mFilms.get(position).getTitle());
        ((FilmViewHolder)holder).overview.setText(mFilms.get(position).getFilm_overview());
        ((FilmViewHolder)holder).votecount.setText("Vote Count: " + mFilms.get(position).getVote_count().toString());
        ((FilmViewHolder)holder).voteavarage.setText("Rate: " + (mFilms.get(position).getVote_average()/ 2) + "/5"  ) ;
        ((FilmViewHolder)holder).popularity.setText("Views: " + mFilms.get(position).getPopularity().toString());
        ((FilmViewHolder)holder).ratingBar.setRating(mFilms.get(position).getVote_average().floatValue() / 2 );
        ((FilmViewHolder)holder).release_date.setText("Date: " +mFilms.get(position).getRelease_date());

        Glide.with(holder.itemView.getContext())
                .load( "https://image.tmdb.org/t/p/w500/"+ mFilms.get(position).getPoster_path())
                .into((((FilmViewHolder)holder)).imageView);
    }

    @Override
    public int getItemCount() {
        if (mFilms != null){
            return mFilms.size();
        }
        return 0;
    }

    public void setmFilms(List<FilmModel> mFilms) {
        this.mFilms = mFilms;
        notifyDataSetChanged();
    }

    public FilmModel getSelectedFilm(int i) {
        if (mFilms.size() > 0){
            return  mFilms.get(i);
        }
        return null;
    }


    public FilmRecyclerView(OnFilmListener onFilmListener) {
        this.onFilmListener = onFilmListener;
    }

    public List<FilmModel> getMFilms() {
        return mFilms;
    }
}
