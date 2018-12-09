package com.example.dev.logobin.handel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dev.logobin.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Tablighat extends RecyclerView.Adapter<Adapter_Tablighat.MyViewHolder> {
    ArrayList<Integer> listImage;

    public Adapter_Tablighat(ArrayList<Integer> listImage) {
        this.listImage = listImage;
    }

    @NonNull
    @Override
    public Adapter_Tablighat.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_recycler_tablighat_model,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Tablighat.MyViewHolder holder, int position) {
        Picasso.get().load(listImage.get(position)).fit().into(holder.tablighat);
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView tablighat;
        public MyViewHolder(View itemView) {
            super(itemView);
            tablighat=(ImageView)itemView.findViewById(R.id.z_m_RecyclerTablighat_Imageview);
        }
    }
}
