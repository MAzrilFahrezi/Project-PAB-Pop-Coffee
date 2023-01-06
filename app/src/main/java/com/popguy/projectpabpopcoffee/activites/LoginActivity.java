package com.popguy.projectpabpopcoffee.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.databinding.ActivityLogin2Binding;
import com.popguy.projectpabpopcoffee.retrofit.Utilities;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {

    private ActivityLogin2Binding binding;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.etEmail.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    binding.etEmail.setError("Email Harus di isi !");
                }
                if (TextUtils.isEmpty(password)) {
                    binding.etPassword.setError("Password Harus di Isi !");
                } else {

                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    progressDialog.cancel();
                                    FancyToast.makeText(LoginActivity.this,"Login Succesfull",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                                    Utilities.setValue(LoginActivity.this, "xEmail", email);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.cancel();
                                    FancyToast.makeText(LoginActivity.this,"Login Failed",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                                }
                            });

                }

            }
        });

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        binding.tvForgetassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.etEmail.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    binding.etEmail.setError("Email Harus di Isi !");
                } else {

                    progressDialog.setTitle("Sending Email");
                    progressDialog.show();
                    firebaseAuth.sendPasswordResetEmail(email)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    progressDialog.cancel();
                                    Toast.makeText(LoginActivity.this, "Reset Password Sudah Dikirimkan ke Email Anda", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.cancel();
                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }

            }
        });

    }

    void saveData(String email){
        SharedPreferences sharedPreferences = getSharedPreferences("logindata", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("logincounter", true);
        editor.putString("usermail", email);
        editor.apply();
    }

}