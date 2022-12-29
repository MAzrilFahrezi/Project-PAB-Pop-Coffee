package com.popguy.projectpabpopcoffee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.popguy.projectpabpopcoffee.R;
import com.popguy.projectpabpopcoffee.model.CoffeeModel;

import java.util.ArrayList;
import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.MyViewHolder> {

    private List<CoffeeModel.Data> resultCoffee = new ArrayList<>();
    private OnAdapterListener listener;

    public CoffeeAdapter(List<CoffeeModel.Data> resultCoffee, OnAdapterListener listener){
        this.resultCoffee = resultCoffee;
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


    }

    @Override
    public int getItemCount() {
        return resultCoffee.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivKopi;
        TextView tvNamaKopi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivKopi = itemView.findViewById(R.id.iv_gambar);
            tvNamaKopi = itemView.findViewById(R.id.tv_namaKopi);
        }
    }

    public interface OnAdapterListener{
        void onClick(CoffeeModel.Data result);
    }
}
