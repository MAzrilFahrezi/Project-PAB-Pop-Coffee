package com.popguy.projectpabpopcoffee.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.databinding.ActivityMainBinding;
import com.popguy.projectpabpopcoffee.databinding.ActivityTambahkopiBinding;

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

    }
}