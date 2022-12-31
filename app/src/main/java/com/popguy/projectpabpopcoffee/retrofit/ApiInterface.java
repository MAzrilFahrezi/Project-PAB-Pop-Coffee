package com.popguy.projectpabpopcoffee.retrofit;

import com.popguy.projectpabpopcoffee.model.CoffeeModel;
import com.popguy.projectpabpopcoffee.model.ValueNoData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("coffee")
    Call<CoffeeModel> getCoffee();

    @POST("coffee")
    @FormUrlEncoded
    Call<ValueNoData> insertKopi(@Field("nama_kopi") String namaKopi,
                               @Field("asal_kopi") String asalKopi,
                               @Field("deskripsi_kopi") String deskripsiKopi);

    @POST("coffee/update/{id}")
    @FormUrlEncoded
    Call<ValueNoData> updateKopi(@Path("id") Integer id,
                                 @Field("nama_kopi") String namaKopi,
                                 @Field("asal_kopi") String asalKopi,
                                 @Field("deskripsi_kopi") String deskripsiKopi);

    @POST("coffee/delete/{id}")
//    @FormUrlEncoded
    Call<ValueNoData> deleteCoffee(@Path("id") Integer id);
}
