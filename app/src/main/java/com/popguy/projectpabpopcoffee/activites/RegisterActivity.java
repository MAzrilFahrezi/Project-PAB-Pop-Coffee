package com.popguy.projectpabpopcoffee.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.databinding.ActivityRegister2Binding;
import com.popguy.projectpabpopcoffee.model.UserModels;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.security.Policy;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegister2Binding binding;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = binding.etNama.getText().toString();
                String email = binding.etEmail.getText().toString();
                String noHp = binding.etNohp.getText().toString();
                String password = binding.etPassword.getText().toString();

                if (TextUtils.isEmpty(nama)){
                    binding.etNama.setError("Nama Harus di Isi !");
                }
                if (TextUtils.isEmpty(email)){
                    binding.etEmail.setError("Email Harus di Isi !");
                }
                if (TextUtils.isEmpty(noHp)){
                    binding.etNohp.setError("Nomor Handphone Harus di Isi !");
                }
                if (TextUtils.isEmpty(password)){
                    binding.etPassword.setError("Password Harus di Isi !");
                }
                else {
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    progressDialog.cancel();

                                    firebaseFirestore.collection("User")
                                            .document(FirebaseAuth.getInstance().getUid())
                                            .set(new UserModels(nama, email, noHp));
                                    FancyToast.makeText(RegisterActivity.this,"Register Succesfull",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    FancyToast.makeText(RegisterActivity.this,"Register Failed",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                                    progressDialog.cancel();
                                }
                            });
                }
            }
        });

        binding.etLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


    }
}