package com.example.movieproject.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movieproject.Adapters.ImageListAdapter;
import com.example.movieproject.Domain.FilmItem;
import com.example.movieproject.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar lodProgressBar;
    private TextView titleTxt,movieRateTxt,movieTimeTxt,movieDateTxt,movieSummaryTxt,movieActorTxt;
    private NestedScrollView scrollView;
    private int idFilm;
    private ShapeableImageView pic1;
    private ImageView pic2,backImage;
    private RecyclerView.Adapter adapterImageList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        idFilm = getIntent().getIntExtra("id",0);
        initViews();
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        lodProgressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/"+idFilm, response -> {
            Gson gson = new Gson();
            lodProgressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            FilmItem items = gson.fromJson(response,FilmItem.class);
            Glide.with(DetailsActivity.this).load(items.getPoster()).into(pic1);
            Glide.with(DetailsActivity.this).load(items.getPoster()).into(pic2);
            titleTxt.setText(items.getTitle());
            movieSummaryTxt.setText(items.getPlot());
            movieRateTxt.setText(items.getRated());
            movieDateTxt.setText(items.getReleased());
            movieTimeTxt.setText(items.getRuntime());
            movieActorTxt.setText(items.getActors());
            if (items.getImages() != null){
                adapterImageList = new ImageListAdapter(items.getImages());
                recyclerView.setAdapter(adapterImageList);

            }


        }, error -> {
            lodProgressBar.setVisibility(View.GONE);
            Log.i("RESPONSE", "OnErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest);
    }

    private void initViews() {
        titleTxt = (TextView) findViewById(R.id.movieNameTxt);
        lodProgressBar = (ProgressBar) findViewById(R.id.derailloading);
        scrollView = findViewById(R.id.scrollView3);
        pic1 = findViewById(R.id.posterNomallmg);
        pic2 = findViewById(R.id.posterBigImg);
        movieRateTxt = (TextView) findViewById(R.id.movieRateTxt);
        movieActorTxt = (TextView) findViewById(R.id.movieActorInfo);
        movieTimeTxt = (TextView) findViewById(R.id.movieTimeTxr);
        movieDateTxt = (TextView) findViewById(R.id.movieDateTxt);
        movieSummaryTxt = (TextView) findViewById(R.id.moviesSummaryInfo);
        backImage = (ImageView) findViewById(R.id.backImage);
        recyclerView = findViewById(R.id.imageRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        backImage.setOnClickListener((View.OnClickListener) view -> {
            finish();
        });


    }
}