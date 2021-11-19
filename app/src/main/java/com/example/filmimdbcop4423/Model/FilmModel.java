package com.example.filmimdbcop4423.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FilmModel implements Parcelable {

    // Model Class for our films
    @SerializedName("title")
    private String title;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("id")
    private Integer movie_id;
    @SerializedName("vote_count")
    private Integer vote_count;
    @SerializedName("vote_average")
    private Double vote_average;  //float
    @SerializedName("popularity")
    private Double popularity;  //float
    @SerializedName("overview")
    private String film_overview;

    public FilmModel(String title, String original_title,
                     String poster_path, String backdrop_path,
                     String release_date, Integer movie_id,
                     Integer vote_count, Double vote_average,
                     Double popularity, String film_overview) {
        this.title = title;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.popularity = popularity;
        this.film_overview = film_overview;
    }

    protected FilmModel(Parcel in) {
        title = in.readString();
        original_title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        release_date = in.readString();
        if (in.readByte() == 0) {
            movie_id = null;
        } else {
            movie_id = in.readInt();
        }
        if (in.readByte() == 0) {
            vote_count = null;
        } else {
            vote_count = in.readInt();
        }
        if (in.readByte() == 0) {
            vote_average = null;
        } else {
            vote_average = in.readDouble();
        }
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        film_overview = in.readString();
    }

    public static final Creator<FilmModel> CREATOR = new Creator<FilmModel>() {
        @Override
        public FilmModel createFromParcel(Parcel in) {
            return new FilmModel(in);
        }

        @Override
        public FilmModel[] newArray(int size) {
            return new FilmModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getFilm_overview() {
        return film_overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(original_title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(release_date);
        if (movie_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(movie_id);
        }
        if (vote_count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(vote_count);
        }
        if (vote_average == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(vote_average);
        }
        if (popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(popularity);
        }
        dest.writeString(film_overview);
    }


    @Override
    public String toString() {
        return "FilmModel{" +
                "title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", movie_id=" + movie_id +
                ", vote_count=" + vote_count +
                ", vot_average=" + vote_average +
                ", popularity=" + popularity +
                ", film_overview='" + film_overview + '\'' +
                '}';
    }
}
