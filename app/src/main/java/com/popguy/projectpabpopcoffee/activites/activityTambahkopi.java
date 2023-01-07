package com.popguy.projectpabpopcoffee.activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.databinding.ActivityMainBinding;
import com.popguy.projectpabpopcoffee.databinding.ActivityTambahkopiBinding;
import com.popguy.projectpabpopcoffee.model.ValueNoData;
import com.popguy.projectpabpopcoffee.retrofit.ApiService;
import com.popguy.projectpabpopcoffee.retrofit.Utilities;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityTambahkopi extends AppCompatActivity {

    private ActivityTambahkopiBinding binding;
    ProgressDialog progressDialog;
    Uri imageUri;
    StorageReference storageReference;
    String downloadUrl;

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

        binding.btnTambahGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();


            }
        });

    }

    private void writeDataBase(String downloadUrl) {

        String namaKopi = binding.etNamakopi.getText().toString();
        String asalKopi = binding.etAsalkopi.getText().toString();
        String deskripsiKopi = binding.etDeskripsi.getText().toString();
//                String gambarKopi = binding.btnTambahGambar.getText().toString();

        ApiService.endpointCoffee().insertKopi(namaKopi, asalKopi, deskripsiKopi, downloadUrl)
                .enqueue(new Callback<ValueNoData>() {
                    @Override
                    public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                        FancyToast.makeText(activityTambahkopi.this,"Tambah Kopi Berhasil",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ValueNoData> call, Throwable t) {
                        FancyToast.makeText(activityTambahkopi.this,"Tambah Kopi Gagal",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                    }
                });
    }


    private void uploadImage() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sedang Menambahkan FIle");
        progressDialog.show();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.JAPAN);
        Date now = new Date();
        String filename = formatter.format(now);

        storageReference = FirebaseStorage.getInstance().getReference("images/"+filename);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                 downloadUrl = uri.toString();
                                 writeDataBase(downloadUrl);
                            }
                        });
//                        Toast.makeText(activityTambahkopi.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(activityTambahkopi.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && data != null && data.getData() != null) {
            imageUri = data.getData();
            Cursor returnCursor =
                    getContentResolver().query(imageUri, null, null, null, null);
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
            returnCursor.moveToFirst();
            binding.btnTambahGambar.setText(returnCursor.getString(nameIndex));
        }
    }


}