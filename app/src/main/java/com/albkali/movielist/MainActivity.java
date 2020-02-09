package com.albkali.movielist;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

       // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

//        LinearLayoutManager layoutManager =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

//        GridLayoutManager layoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return (position%3==0?2:1);
//            }
//        });
////
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);



        mMovieRecyclerView.setLayoutManager(layoutManager);
        mMovieRecyclerView.setHasFixedSize(true);
        mAdapter = new MoviesAdapter(getApplicationContext(),mMoviesList);
        mMovieRecyclerView.setAdapter(mAdapter);

        APIInterface apiService = APIClient.getClient().create(APIInterface.class);
        Call<List<Movie>> call=apiService.getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                mMoviesList = response.body(); Log.d("TAG","Response = "+mMoviesList);
                mAdapter.setMovieList(mMoviesList);
            }
            @Override

            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.linearViewHorizontal:
                LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this); // (Context context)
                mLinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
                mMovieRecyclerView.setLayoutManager(mLinearLayoutManagerHorizontal);
                break;

            case R.id.linearViewVertical:
                LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context)
                mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
                mMovieRecyclerView.setLayoutManager(mLinearLayoutManagerVertical);
                break;
            case R.id.gridView:
                GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2); // (Context context, int spanCount)
                mMovieRecyclerView.setLayoutManager(mGridLayoutManager);

                break;
            case R.id.staggeredViewHorizontal:
                StaggeredGridLayoutManager mStaggeredHorizontalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
                mMovieRecyclerView.setLayoutManager(mStaggeredHorizontalLayoutManager);
                break;
            case R.id.staggeredViewVertical:
                StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
                mMovieRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
