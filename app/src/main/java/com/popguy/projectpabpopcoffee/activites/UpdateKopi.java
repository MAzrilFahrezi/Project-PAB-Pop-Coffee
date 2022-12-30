package com.popguy.projectpabpopcoffee.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.popguy.projectpabpopcoffee.databinding.ActivityUpdateKopiBinding;

public class UpdateKopi extends AppCompatActivity {
    private ActivityUpdateKopiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateKopiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}