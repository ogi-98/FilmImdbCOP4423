package com.example.filmimdbcop4423;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Welcome to MyApp", Toast.LENGTH_LONG).show();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}