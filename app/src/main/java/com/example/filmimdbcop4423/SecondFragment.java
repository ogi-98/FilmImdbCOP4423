package com.example.filmimdbcop4423;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filmimdbcop4423.Model.FilmModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private ImageView image, backImage;
    private TextView title, releaseDate, overview, popular, voteAvarage, voteCount;
    private RatingBar ratingBar;

    FilmModel filmModel ;


    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            filmModel = bundle.getParcelable("film");
        }

        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        image = view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.title_detail);
        releaseDate = view.findViewById(R.id.release_date_detail);
        overview = view.findViewById(R.id.overview_detail);
        popular = view.findViewById(R.id.textPopular);
        voteAvarage = view.findViewById(R.id.textVoteAvarage);
        ratingBar = view.findViewById(R.id.ratingBar2);
        backImage = view.findViewById(R.id.imageViewBack);
        voteCount = view.findViewById(R.id.textVoteCount);




        backImage.setOnClickListener(backClick);


        getDataFromInternet();
    }

    private void getDataFromInternet(){

                Log.d("second", "incoming intent " + filmModel.getMovie_id());

                title.setText(filmModel.getTitle());
                title.setMovementMethod(new ScrollingMovementMethod());
                releaseDate.setText("Release Date: " + filmModel.getRelease_date());
                overview.setText(filmModel.getFilm_overview());
                popular.setText("Views: " + filmModel.getPopularity().toString());
                voteAvarage.setText(""+filmModel.getVote_average() / 2);
                voteCount.setText("Vote Count: " + filmModel.getVote_count());

                ratingBar.setRating(filmModel.getVote_average().floatValue() / 2 );

                Glide.with(getActivity())
                        .load("https://image.tmdb.org/t/p/w500/" + filmModel.getBackdrop_path())
                        .into(image);


    }


    private View.OnClickListener backClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_secondFragment_to_firstFragment);

        }
    };

}