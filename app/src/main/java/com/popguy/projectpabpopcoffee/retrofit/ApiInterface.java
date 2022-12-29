package com.popguy.projectpabpopcoffee.retrofit;

import com.popguy.projectpabpopcoffee.model.CoffeeModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("coffee")
    Call<CoffeeModel> getCoffee();

}
