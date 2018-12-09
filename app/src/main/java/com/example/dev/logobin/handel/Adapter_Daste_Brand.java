package com.example.dev.logobin.handel;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dev.logobin.R;
import com.example.dev.logobin.model.M_ZirDaste;

import java.util.ArrayList;

public class Adapter_Daste_Brand extends RecyclerView.Adapter<Adapter_Daste_Brand.MyViewHolder> {

    private ArrayList<M_ZirDaste> daste;
    private OnItemClickListener clickListener;

    public Adapter_Daste_Brand(ArrayList<M_ZirDaste> daste, OnItemClickListener clickListener) {
        this.daste = daste;
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(M_ZirDaste m_zirDaste);
    }

    @NonNull
    @Override
    public Adapter_Daste_Brand.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_recyclerview_daste_brand,null);

        return new MyViewHolder(view);
    }

    private int row_index ;
    @Override
    public void onBindViewHolder(@NonNull final Adapter_Daste_Brand.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final M_ZirDaste  m_zirDaste=daste.get(position);
        holder.daste.setText(m_zirDaste.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(m_zirDaste);
                row_index=position;
                notifyDataSetChanged();
            }
        });
        if(row_index==position){

            holder.daste.setTextColor(Color.parseColor("#9E9E9E"));
            holder.daste.setBackgroundResource(R.drawable.stork_gray_select);
        }
        else
        {
            holder.daste.setTextColor(Color.parseColor("#BDBDBD"));
            holder.daste.setBackgroundResource(R.drawable.stork_gray);
        }

    }

    @Override
    public int getItemCount() {
        return daste.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView daste;
        public MyViewHolder(View itemView) {
            super(itemView);
            daste=(TextView)itemView.findViewById(R.id.z_m_DasteBrand_Textview);
        }
    }
}
