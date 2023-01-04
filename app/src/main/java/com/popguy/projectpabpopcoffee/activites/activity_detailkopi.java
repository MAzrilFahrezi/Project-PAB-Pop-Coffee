package com.popguy.projectpabpopcoffee.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.databinding.ActivityDetailkopiBinding;

public class activity_detailkopi extends AppCompatActivity {

    private ActivityDetailkopiBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailkopiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onBackPressed(); }
        });

        String namaKopi = getIntent().getStringExtra("nama_kopi");
        String asalKopi = getIntent().getStringExtra("asal_kopi");
        String deskripsiKopi = getIntent().getStringExtra("deskripsi_kopi");
        String gambarKopi = getIntent().getStringExtra("gambarKopi");

        binding.tvKopi.setText(namaKopi);
        binding.tvAsalkopi.setText(asalKopi);
        binding.tvDeskripsi.setText(deskripsiKopi);
        Glide.with(this)
                .load(gambarKopi)
                .placeholder(R.drawable.robusta)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivKopi);
    }
}