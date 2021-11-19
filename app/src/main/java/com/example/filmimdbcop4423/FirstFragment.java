package com.example.filmimdbcop4423;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filmimdbcop4423.Model.FilmModel;
import com.example.filmimdbcop4423.Response.FilmSearchResponse;
import com.example.filmimdbcop4423.ViewModel.FilmListViewModel;
import com.example.filmimdbcop4423.adapter.FilmRecyclerView;
import com.example.filmimdbcop4423.adapter.OnFilmListener;
import com.example.filmimdbcop4423.databinding.FragmentFirstBinding;
import com.example.filmimdbcop4423.service.MovieApi;
import com.example.filmimdbcop4423.service.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FirstFragment extends Fragment implements OnFilmListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Button buton;


    private RecyclerView recyclerView;
    private FilmRecyclerView filmRecyclerViewAdapter;


    private Button butonSearch;
    private EditText searchTextField;


    private FilmListViewModel filmListViewModel;


    private FragmentFirstBinding binding;




    public FirstFragment() {
        // Required empty public constructor
        super(R.layout.fragment_first);
    }

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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


        binding = FragmentFirstBinding.inflate(inflater, container,false);
        View view = binding.getRoot();

        recyclerView = binding.rvFilms;


        filmListViewModel = ViewModelProviders.of(this).get(FilmListViewModel.class);

        ConfigureRecylerView();


        observeDatas();


        return view;

    }

    private void observeDatas(){

        filmListViewModel.getFilms().observe(getViewLifecycleOwner(), new Observer<List<FilmModel>>() {
            @Override
            public void onChanged(List<FilmModel> filmModels) {

                if (filmModels != null){
                    for (FilmModel filmModel: filmModels){

                        //TODO:

                        Log.v("FFragment", "on changed: " + filmModel.toString());

                        filmRecyclerViewAdapter.setmFilms(filmModels);

                    }
                }


            }
        });
    }


    private void searchFilmApi(String query, int pageNum){

        filmListViewModel.searchFilmApi(query,pageNum);


    }


    private void ConfigureRecylerView(){

        filmRecyclerViewAdapter = new FilmRecyclerView(this);

        recyclerView.setAdapter(filmRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    @Override
    public void onFilmClick(int position) {


        FilmModel selectedfilm = filmRecyclerViewAdapter.getSelectedFilm(position);


        Bundle bundle = new Bundle();
        bundle.putParcelable("film", selectedfilm);


        Navigation.findNavController(getView()).navigate(R.id.action_firstFragment_to_secondFragment, bundle);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        butonSearch = getView().findViewById(R.id.search_bar_button);
        searchTextField = getView().findViewById(R.id.search_bar_text);

        butonSearch.setOnClickListener(searchBtnClick);

    }
    private View.OnClickListener searchBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String searchStr ;
            searchStr = searchTextField.getText().toString();
            hideKeyboardFrom(v.getContext(),v);

            if (searchStr.matches("")) {
                Toast.makeText(v.getContext(), "Text is Null", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(v.getContext(), "Searching...", Toast.LENGTH_SHORT).show();
                searchFilmApi(searchStr,1);
            }

        }
    };

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_firstFragment_to_secondFragment);

        }
    };

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}