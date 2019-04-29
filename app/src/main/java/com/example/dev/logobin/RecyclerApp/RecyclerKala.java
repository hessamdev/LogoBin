package com.example.dev.logobin.RecyclerApp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    private Context context ;

    private OnItemClickListener clickListener;

    public RecyclerKala(FragmentActivity activity, ArrayList<M_Kala> listKala, Context context, OnItemClickListener clickListener) {
        this.activity = activity;
        ListKala = listKala;
        this.context = context;
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
        Animation animation= AnimationUtils.loadAnimation(context,R.anim.recycler_fall_down);
        Picasso.get().load(kala.getImage()).resize(500,500).into(holder.imageView_Logo);
//        Glide.with(context).load(kala.getImage()).into(holder.imageView_Logo);
        holder.name.setText(kala.getTitle());
        if (kala.getCprice() != null){
            holder.gheymat.setVisibility(View.VISIBLE);
            if (kala.getCprice().equals("null")) {
                holder.gheymat.setText("تماس");
                holder.gheymat.setTextSize(14f);
            }else {
                holder.gheymat.setText(kala.getCprice());
            }
        }
        if (kala.getActive() == null) {
            holder.view.setVisibility(View.GONE);
        }else if (kala.getActive().equals("yes")) {
                holder.view.setVisibility(View.VISIBLE);
            } else {
                holder.view.setVisibility(View.GONE);
            }

        holder.itemView.startAnimation(animation);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(kala);
//                activity.GetManager().OpenView(new Kala_View(),"Kala_View",true);
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
        View view;
        public KalaHolder(View itemView) {
            super(itemView);
            view=(View) itemView.findViewById(R.id.z_model_recyclerKala_view_company);
            imageView_Logo =(ImageView)itemView.findViewById(R.id.z_model_rexyclershop_ImageView_kala);
            saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_Imageview_Saye);
            Picasso.get().load(R.drawable.sayehpng).resize(300,300).into(saye);
            name=(TextView)itemView.findViewById(R.id.z_model_recyclerKala_Textview_name);
            gheymat=(TextView)itemView.findViewById(R.id.z_model_recyclerKala_Textview_Gheymat);
        }
    }
}
