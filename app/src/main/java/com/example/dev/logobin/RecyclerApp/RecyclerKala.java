package com.example.dev.logobin.RecyclerApp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.example.dev.logobin.handel.Main_Adapter;
import com.example.dev.logobin.model.M_Kala;
import com.example.dev.logobin.model.Model_All;
import com.example.dev.logobin.ui.Kala_View;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerKala extends RecyclerView.Adapter<RecyclerKala.KalaHolder> {

    private FragmentActivity activity;

    private ArrayList<M_Kala> ListKala;

    private OnItemClickListener clickListener;

    public RecyclerKala(FragmentActivity activity, ArrayList<M_Kala> listKala, OnItemClickListener clickListener) {
        this.activity = activity;
        ListKala = listKala;
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(M_Kala model);
    }

    @NonNull
    @Override
    public KalaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_recyclerview_kala,parent,false);
        return new KalaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KalaHolder holder, int position) {
        final M_Kala kala=ListKala.get(position);
        Picasso.get().load(kala.getImage()).resize(500,500).into(holder.imageView_Logo);
        holder.name.setText(kala.getTitle());
        if (!kala.getCprice().equals("null")){
            holder.gheymat.setVisibility(View.VISIBLE);
            holder.gheymat.setText(kala.getCprice());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(kala);
                activity.GetManager().OpenView(new Kala_View(),"Kala_View",true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListKala.size();
    }

    public class KalaHolder extends RecyclerView.ViewHolder {
        TextView gheymat,name;
        ImageView imageView_Logo,saye;
        public KalaHolder(View itemView) {
            super(itemView);
            imageView_Logo =(ImageView)itemView.findViewById(R.id.z_model_rexyclershop_ImageView_kala);
            saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_Imageview_Saye);
            Picasso.get().load(R.drawable.sayehpng).resize(300,300).into(saye);
            name=(TextView)itemView.findViewById(R.id.z_model_recyclerKala_Textview_name);
            gheymat=(TextView)itemView.findViewById(R.id.z_model_recyclerKala_Textview_Gheymat);
        }
    }
}
