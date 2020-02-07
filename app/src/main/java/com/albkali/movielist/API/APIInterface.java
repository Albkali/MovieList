package com.albkali.movielist.API;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("volley_array.json")
    Call<List<Movie>> getMovies();




}
