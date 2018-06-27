package com.example.android.loginscreen.database.rest;

import com.example.android.loginscreen.database.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("/getuserbyemail/{email}")
    Call<User> getUserByEmail(@Path("email") String email);
}
