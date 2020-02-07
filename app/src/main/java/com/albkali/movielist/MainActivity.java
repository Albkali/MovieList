package com.albkali.movielist;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.albkali.movielist.API.APIClient;
import com.albkali.movielist.API.APIInterface;
import com.albkali.movielist.API.Movie;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 100;
    private static final String TAG=MainActivity.class.getSimpleName();
    private MoviesAdapter mAdapter;
    private RecyclerView mMovieRecyclerView;
    List<Movie> mMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMovieRecyclerView.setLayoutManager(layoutManager); mMovieRecyclerView.setHasFixedSize(true);
        mAdapter = new MoviesAdapter(getApplicationContext(),mMoviesList);
        mMovieRecyclerView.setAdapter(mAdapter);

        APIInterface apiService = APIClient.getClient().create(APIInterface.class);
        Call<List<Movie>> call=apiService.getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                mMoviesList = response.body(); Log.d("TAG","Response = "+mMoviesList); mAdapter.setMovieList(mMoviesList);
            }
            @Override

            public void onFailure(Call<List<Movie>> call, Throwable t) { Log.d("TAG","Response = "+t.toString());
            } });


    }
}
