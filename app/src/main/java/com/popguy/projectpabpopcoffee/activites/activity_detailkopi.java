package com.popguy.projectpabpopcoffee.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.databinding.ActivityDetailkopiBinding;

public class activity_detailkopi extends AppCompatActivity {

    private ActivityDetailkopiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailkopiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String namaKopi = getIntent().getStringExtra("nama_kopi");
        String asalKopi = getIntent().getStringExtra("asal_kopi");
        String deskripsiKopi = getIntent().getStringExtra("deskripsi_kopi");

        binding.tvKopi.setText(namaKopi);
        binding.tvAsalkopi.setText(asalKopi);
        binding.tvDeskripsi.setText(deskripsiKopi);
    }
}