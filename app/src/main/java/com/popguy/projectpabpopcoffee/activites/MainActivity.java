package com.popguy.projectpabpopcoffee.activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.adapter.CoffeeAdapter;
import com.popguy.projectpabpopcoffee.databinding.ActivityMainBinding;
import com.popguy.projectpabpopcoffee.databinding.ActivityTambahkopiBinding;
import com.popguy.projectpabpopcoffee.model.CoffeeModel;
import com.popguy.projectpabpopcoffee.retrofit.ApiService;
import com.popguy.projectpabpopcoffee.retrofit.Utilities;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "coffee";
    private ActivityMainBinding binding;

    private CoffeeAdapter coffeeAdapter;
    private List<CoffeeModel.Data> coffeeResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (!Utilities.checkValue(MainActivity.this, "xEmail")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        binding.cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, User_Activity.class));
            }
        });

        binding.btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, activityTambahkopi.class));
            }
        });


    }

    private void getCoffee() {
        ApiService.endpointCoffee().getCoffee()
                .enqueue(new Callback<CoffeeModel>() {
                    @Override
                    public void onResponse(Call<CoffeeModel> call, Response<CoffeeModel> response) {
                        Log.d(TAG, response.body().getData().get(0).getNama_kopi());
                        coffeeResult = response.body().getData();
                        loadAdapter(coffeeResult);
                    }

                    @Override
                    public void onFailure(Call<CoffeeModel> call, Throwable t) {

                    }
                });
    }



    private void loadAdapter(List<CoffeeModel.Data> coffeeResult) {
        coffeeAdapter = new CoffeeAdapter(MainActivity.this);
        binding.rvKopi.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvKopi.setAdapter(coffeeAdapter);
        coffeeAdapter.setData(coffeeResult);
        coffeeAdapter.setOnClickListener(new CoffeeAdapter.OnClickListener() {
            @Override
            public void onDetail(CoffeeModel.Data coffee) {
                gotodetail(coffee);


            }
        });
    }

    private void gotodetail(CoffeeModel.Data coffee) {

        Intent intent = new Intent(MainActivity.this,activity_detailkopi.class);
        intent.putExtra("asal_kopi", coffee.getAsal_kopi());
        intent.putExtra("nama_kopi", coffee.getNama_kopi());
        intent.putExtra("deskripsi_kopi", coffee.getDeskripsi_kopi());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        getCoffee();
    }
}