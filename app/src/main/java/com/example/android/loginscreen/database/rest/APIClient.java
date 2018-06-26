package com.example.android.loginscreen.database.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL = "127.0.0.1:4567";
    private static Retrofit retroFit = null;

    public static Retrofit getClient(){
        if(retroFit == null){
            retroFit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retroFit;
    }

}
