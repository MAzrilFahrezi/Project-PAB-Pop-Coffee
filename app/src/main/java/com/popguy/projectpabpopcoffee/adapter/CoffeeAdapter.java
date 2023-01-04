package com.popguy.projectpabpopcoffee.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.activites.MainActivity;
import com.popguy.projectpabpopcoffee.model.CoffeeModel;
import com.popguy.projectpabpopcoffee.model.ValueNoData;
import com.popguy.projectpabpopcoffee.retrofit.ApiService;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.MyViewHolder> {

    private List<CoffeeModel.Data> resultCoffee = new ArrayList<>();
    private OnClickListener listener;
    private Context context;

    public CoffeeAdapter(Context context){
        this.context= context;
//        this.resultCoffee = resultCoffee;
//        this.listener = listener;
    }

    @NonNull
    @Override
    public CoffeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.kopi_adapter,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeAdapter.MyViewHolder holder, int position) {

        CoffeeModel.Data result = resultCoffee.get(position);
        holder.tvNamaKopi.setText(result.getNama_kopi());
        Glide.with(holder.itemView.getContext())
                .load(result.getGambarKopi())
                .placeholder(R.drawable.loading)
                .fitCenter()
                .into(holder.ivKopi);
        holder.cvKopi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder messageWindow = new AlertDialog.Builder(context);
                messageWindow.setMessage("Pilih perintah yang diinginkan!");
                messageWindow.setTitle("Perhatian ! ");
                messageWindow.setCancelable(true);

                messageWindow.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder messageWindow = new AlertDialog.Builder(context);
                        messageWindow.setMessage("Konfirmasi Delete");
                        messageWindow.setTitle("Attention ! ");
                        messageWindow.setCancelable(true);
                        messageWindow.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                    }
                });
                messageWindow.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ApiService.endpointCoffee().deleteCoffee(result.getId()).enqueue(new Callback<ValueNoData>() {
                            @Override
                            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                                Toast.makeText(context, "berhasil menghapus biji", Toast.LENGTH_SHORT).show();
                                ((MainActivity) context).onResume();
                            }

                            @Override
                            public void onFailure(Call<ValueNoData> call, Throwable t) {
                                Toast.makeText(context, "gagal menghapus biji", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                messageWindow.show();
                return false;
            }
        });



        holder.cvKopi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.onDetail(result);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultCoffee.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivKopi;
        TextView tvNamaKopi;
        CardView cvKopi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivKopi = itemView.findViewById(R.id.iv_gambar);
            tvNamaKopi = itemView.findViewById(R.id.tv_namaKopi);
            cvKopi = itemView.findViewById(R.id.cv_kopi);
        }
    }

    public void setData(List<CoffeeModel.Data> data){
        resultCoffee.clear();
        resultCoffee.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnClickListener{
        void onDetail(CoffeeModel.Data result);
    }
    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }
}
