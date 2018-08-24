package com.example.dev.logobin.handler;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dev.logobin.R;
import com.example.dev.logobin.model.Model_All;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.MyViewHolder> {

    private ArrayList<Model_All> list ;
    private OnItemClickListener clickListener;
    private ArrayList<Model_All> getlistadapter;


    public Main_Adapter(ArrayList<Model_All> getlistadapter, OnItemClickListener clickListener) {
        this.getlistadapter=getlistadapter;
        this.clickListener = clickListener;
        list=new ArrayList<>();
        add();

    }

    public void add(){
        if (getlistadapter != null){
        list.addAll(getlistadapter);
      //  notifyItemInserted(list.size());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Model_All model);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,null);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Model_All model=list.get(position);
        Picasso.get().load(model.getImage()).resize(300,300).into(holder.imageBrand);
        holder.name.setText(model.getName());
        holder.number_rate.setText(model.getRate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(model);
            }
        });


    }
    @Override
    public int getItemViewType(int position) {
        Model_All model_all=list.get(position);
        String type=model_all.getType();
        switch (type) {
            case "Brand_R":
                return R.layout.z_m_recyclerview_brand;
            case "Brand_G":
                return R.layout.z_m_recyclerview_brand_grid;
        }return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBrand;
        TextView name,number_rate;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageBrand=(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_ImageView_Brand);

            ImageView saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_Imageview_Saye);
            Picasso.get().load(R.drawable.sayehpng).resize(300,300).into(saye);

            name=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_name);
            number_rate=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_Rate);

        }

    }

}
