package com.albkali.movielist.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    //to specify the base URL of our API, creating the retrofit instance and setting up the Gson convertor.

    public static String BASE_URL ="http://velmm.com/apis/";
    private static Retrofit retrofit; public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL) .addConverterFactory(GsonConverterFactory.create()) .build();
        }
        return retrofit; }


}
