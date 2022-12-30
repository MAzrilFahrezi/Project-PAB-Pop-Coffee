package com.popguy.projectpabpopcoffee.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.databinding.ActivityMainBinding;
import com.popguy.projectpabpopcoffee.databinding.ActivityTambahkopiBinding;
import com.popguy.projectpabpopcoffee.model.ValueNoData;
import com.popguy.projectpabpopcoffee.retrofit.ApiService;
import com.popguy.projectpabpopcoffee.retrofit.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityTambahkopi extends AppCompatActivity {

    private ActivityTambahkopiBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahkopiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaKopi = binding.etNamakopi.getText().toString();
                String asalKopi = binding.etAsalkopi.getText().toString();
                String deskripsiKopi = binding.etDeskripsi.getText().toString();

                ApiService.endpointCoffee().insertKopi(namaKopi, asalKopi, deskripsiKopi)
                        .enqueue(new Callback<ValueNoData>() {
                            @Override
                            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                                Toast.makeText(activityTambahkopi.this, "Tambah BIJI Berhasil !", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<ValueNoData> call, Throwable t) {
                                Toast.makeText(activityTambahkopi.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}