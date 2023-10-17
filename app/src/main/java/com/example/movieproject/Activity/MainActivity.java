package com.example.movieproject.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieproject.Adapters.FilmListAdapter;
import com.example.movieproject.Domain.DetailFilm;
import com.example.movieproject.R;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterNewMovies,adapterUpcomingMovies;
    private RecyclerView recyclerViewNewMovies,recyclerViewUpcomingMovies;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private StringRequest mStringRequest2;
    private ProgressBar loadingProgress1,loadingProgress2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        sendRequest1();
        sendRequest2();
    }

    private void sendRequest1() {
        mRequestQueue = Volley.newRequestQueue(this);
        loadingProgress1.setVisibility(View.VISIBLE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
        Gson gson = new Gson();
        loadingProgress1.setVisibility(View.GONE);
        DetailFilm items   = gson.fromJson(response, DetailFilm.class);
        adapterNewMovies = new FilmListAdapter(items);
        recyclerViewNewMovies.setAdapter(adapterNewMovies);

        }, error -> {
            loadingProgress1.setVisibility(View.GONE);

        });
        mRequestQueue.add(mStringRequest);
    }
    private void sendRequest2() {
        mRequestQueue = Volley.newRequestQueue(this);
        loadingProgress2.setVisibility(View.VISIBLE);
        mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=5", response -> {
            Gson gson = new Gson();
            loadingProgress2.setVisibility(View.GONE);
            DetailFilm items   = gson.fromJson(response, DetailFilm.class);
            adapterUpcomingMovies = new FilmListAdapter(items);
            recyclerViewUpcomingMovies.setAdapter(adapterUpcomingMovies);

        }, error -> {
            loadingProgress2.setVisibility(View.GONE);

        });
        mRequestQueue.add(mStringRequest2);

    }
    private void initViews() {
        recyclerViewNewMovies = (RecyclerView) findViewById(R.id.view1);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewUpcomingMovies = (RecyclerView) findViewById(R.id.view2);
        recyclerViewUpcomingMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loadingProgress1 = (ProgressBar) findViewById(R.id.loading1);
        loadingProgress2 = (ProgressBar) findViewById(R.id.loading2);
    }

}