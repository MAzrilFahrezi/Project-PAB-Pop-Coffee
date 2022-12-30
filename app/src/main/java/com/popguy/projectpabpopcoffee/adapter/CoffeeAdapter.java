package com.popguy.projectpabpopcoffee.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.model.CoffeeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.MyViewHolder> {

    private List<CoffeeModel.Data> resultCoffee = new ArrayList<>();
    private OnAdapterListener listener;

    public CoffeeAdapter(){
//        this.resultCoffee = resultCoffee;
//        this.listener = listener;
    }

    public CoffeeAdapter(List<CoffeeModel.Data> coffeeResult, OnAdapterListener listener) {
        this.resultCoffee = coffeeResult;
        this.listener = listener;
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



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(result);
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

    public interface OnAdapterListener{
        void onClick(CoffeeModel.Data result);
    }
}
