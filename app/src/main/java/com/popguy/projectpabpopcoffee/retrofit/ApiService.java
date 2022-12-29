package com.popguy.projectpabpopcoffee.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static String BASE_URL_KOPI = "https://pab2coffeeapi.000webhostapp.com/api/";

    private static Retrofit retrofit;

    public static ApiInterface endpointCoffee() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_KOPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
