package com.popguy.projectpabpopcoffee.activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.adapter.CoffeeAdapter;
import com.popguy.projectpabpopcoffee.databinding.ActivityMainBinding;
import com.popguy.projectpabpopcoffee.model.CoffeeModel;
import com.popguy.projectpabpopcoffee.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "coffee";
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, User_Activity.class));
            }
        });
        getCoffee();
    }


    private void getCoffee() {
        ApiService.endpointCoffee().getCoffee()
                .enqueue(new Callback<CoffeeModel>() {
                    @Override
                    public void onResponse(Call<CoffeeModel> call, Response<CoffeeModel> response) {
                        Log.d(TAG, response.body().getData().get(0).getNama_kopi());
                    }

                    @Override
                    public void onFailure(Call<CoffeeModel> call, Throwable t) {

                    }
                });
    }

}