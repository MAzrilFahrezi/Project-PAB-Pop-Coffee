package com.popguy.projectpabpopcoffee.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.databinding.ActivityUserBinding;

public class User_Activity extends AppCompatActivity {
    private ActivityUserBinding binding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(User_Activity.this, LoginActivity.class));
                Toast.makeText(User_Activity.this, "Signed Out !", Toast.LENGTH_SHORT).show();
            }
        });

    }


}