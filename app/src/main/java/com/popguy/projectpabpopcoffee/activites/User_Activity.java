package com.popguy.projectpabpopcoffee.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.databinding.ActivityUserBinding;
import com.popguy.projectpabpopcoffee.retrofit.Utilities;
import com.shashank.sony.fancytoastlib.FancyToast;

public class User_Activity extends AppCompatActivity {
    private ActivityUserBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Utilities.clearUser(User_Activity.this);
                startActivity(new Intent(User_Activity.this, LoginActivity.class));
                FancyToast.makeText(User_Activity.this,"Signed Out !",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                finish();
            }
        });

        binding.ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(User_Activity.this, AboutUsActivity.class));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        UserId = firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firebaseFirestore.collection("User").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                showProgressbar();
                if (value != null){
                    binding.tvNamaUser.setText(value.getString("nama"));
                    binding.tvEmailuser.setText(value.getString("email"));
                    binding.tvNohp.setText(value.getString("noHp"));
                    hideProgressBar();
                }
            }
        });
    }

    private void showProgressbar(){
        binding.progressbar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        binding.progressbar.setVisibility(View.GONE);
    }


}