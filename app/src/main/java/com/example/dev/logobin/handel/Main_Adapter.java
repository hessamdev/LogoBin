package com.example.dev.logobin.handel;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.Network.GetKala;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.example.dev.logobin.model.Model_All;
import com.example.dev.logobin.model.Model_RecyclerHorizemtal;
import com.example.dev.logobin.ui.Brand_View;
import com.example.dev.logobin.ui.Kala_View;
import com.example.dev.logobin.ui.Sherkat_View;
import com.example.dev.logobin.ui.Show_All_Item;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.MyViewHolder> {

    int Holder;
    private ArrayList<Model_All> list ;
    Runnable runnable;

    private OnItemClickListener clickListener;
    private ArrayList<Model_All> getlistadapter;
    private FragmentActivity activity;

    public Main_Adapter(ArrayList<Model_All> getlistadapter,FragmentActivity activity, OnItemClickListener clickListener) {
        this.getlistadapter=getlistadapter;
        this.activity=activity;
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
        Holder=viewType;
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Model_All model=list.get(position);

        switch (Holder){
            case R.layout.z_m_recyclerview_brand :

                Picasso.get().load(model.getUrl_Image()).resize(300,300).into(holder.imageView_Logo);
                holder.name.setText(model.getTitel());
                holder.number_rate.setText(model.getRate());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(model);
                        Toast.makeText(activity, ""+model.getId(), Toast.LENGTH_SHORT).show();


                        Bundle bundle = new Bundle();
                        Brand_View brand_view= new Brand_View();
                        bundle.putString("Factory_id",model.getId());
                        bundle.putString("Url_Image",model.getUrl_Image());
                        bundle.putString("Rate",model.getRate());
                        brand_view.setArguments(bundle);

                        GetKala getKala=new GetKala(activity);
//                        getKala.getKala(model.getId());

                        activity.GetManager().OpenView(brand_view,"Brand_View",true);


                    }
                });
                break;
            case R.layout.z_m_recyclerview_brand_grid:
                Picasso.get().load(model.getImage()).resize(300,300).into(holder.imageView_Logo);
                holder.name.setText(model.getName());
                holder.number_rate.setText(model.getRate());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(model);
                        activity.GetManager().OpenView(new Brand_View(),"Brand_View",true);
                    }
                });
                break;
            case R.layout.z_m_recyclerview_kala:
                Picasso.get().load(model.getUrl_Image()).resize(300,300).into(holder.imageView_Logo);
                holder.name.setText(model.getName());
                if (model.getRate() != null){
                    holder.gheymat.setVisibility(View.VISIBLE);
                    holder.gheymat.setText(model.getRate());
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(model);
                        activity.GetManager().OpenView(new Kala_View(),"Kala_View",true);
                    }
                });
                break;
            case R.layout.z_m_recyclerview_sherkat :
                Picasso.get().load(model.getImage()).resize(300,300).into(holder.imageView_Logo);
                holder.name.setText(model.getName());
                holder.number_rate.setText(model.getRate());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(model);
                        activity.GetManager().OpenView(new Sherkat_View(),"Sherkat_View",true);
                    }
                });
                break;
            case R.layout.z_m_recyclerview_sherkat_gheymat:
                String t ="تومان";
                Picasso.get().load(model.getImage()).resize(300,300).into(holder.imageView_Logo);
                holder.number_rate.setText(model.getRate());
                holder.gheymat.setText(model.getGheymat()+t);
                holder.timeup.setText(model.getTime());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(model);
                        activity.GetManager().OpenView(new Sherkat_View(),"Sherkat_View",true);
                    }
                });
                break;
            case R.layout.z_m_recycler_tablighat_model:
                Transformation transformation=new RoundedTransformationBuilder().cornerRadiusDp(20).build();
                holder.imageView_Logo.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.get().load(model.getImage()).transform(transformation).into(holder.imageView_Logo);
                holder.imageView_Logo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        }
    }

    private String type;

    @Override
    public int getItemViewType(int position) {
        Model_All model_all=list.get(position);
           type=model_all.getType();


        switch (type) {
            case "Brand_R":
                return R.layout.z_m_recyclerview_brand;
            case "Brand_G":
                return R.layout.z_m_recyclerview_brand_grid;
            case "Recycler_Image" :
                return R.layout.z_m_heder_image_recycler;
            case "Kala_G":
                return R.layout.z_m_recyclerview_kala;
            case "Sherkat_R":
                return R.layout.z_m_recyclerview_sherkat;
            case "Sherkat_gheymat":
                return R.layout.z_m_recyclerview_sherkat_gheymat;
            case "Recycler_Tabligh":
                return R.layout.z_m_recycler_tablighat_model;
        }return super.getItemViewType(position);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_Logo,saye;
        TextView name,number_rate,gheymat,timeup;
        RecyclerView Heder_Image;
        public MyViewHolder(View itemView) {
            super(itemView);
            switch (Holder){
                case R.layout.z_m_recyclerview_brand:
                    imageView_Logo =(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_ImageView_Brand);
                    saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_Imageview_Saye);
                    Picasso.get().load(R.drawable.sayehpng).resize(300,300).into(saye);
                    name=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_name);
                    number_rate=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_Rate);
                    break;
                case R.layout.z_m_recyclerview_brand_grid:
                    imageView_Logo =(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_ImageView_Brand);
                    saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_Imageview_Saye);
                    Picasso.get().load(R.drawable.sayehpng).resize(300,300).into(saye);
                    name=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_name);
                    number_rate=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_Rate);
                    break;
                case R.layout.z_m_recyclerview_kala:
                    imageView_Logo =(ImageView)itemView.findViewById(R.id.z_model_rexyclershop_ImageView_kala);
                    saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_Imageview_Saye);
                    Picasso.get().load(R.drawable.sayehpng).resize(300,300).into(saye);
                    name=(TextView)itemView.findViewById(R.id.z_model_recyclerKala_Textview_name);
                    gheymat=(TextView)itemView.findViewById(R.id.z_model_recyclerKala_Textview_Gheymat);
                    break;
                case R.layout.z_m_recyclerview_sherkat:
                    imageView_Logo =(ImageView)itemView.findViewById(R.id.z_model_recyclerSherkat_ImageView_Sherkat);
                    saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerSherkat_Imageview_Saye);
                    Picasso.get().load(R.drawable.sayehpng).resize(300,300).into(saye);
                    name=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_name);
                    number_rate=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_Rate);
                    break;
                case R.layout.z_m_recyclerview_sherkat_gheymat:
                    imageView_Logo =(ImageView)itemView.findViewById(R.id.z_model_recyclerSherkat_ImageView_Sherkat);
                    saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerSherkat_Imageview_Saye);
                    Picasso.get().load(R.drawable.sayehpng).resize(300,300).into(saye);
                    gheymat=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_Gheymat);
                    timeup=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_TimeUpdate);
                    number_rate=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_Rate);
                    break;
                case R.layout.z_m_recycler_tablighat_model:
                    imageView_Logo=(ImageView)itemView.findViewById(R.id.z_m_RecyclerTablighat_Imageview);
                    break;
            }

        }

    }


























    public static class Adaprer_RecyclerVertical extends RecyclerView.Adapter<Adaprer_RecyclerVertical.ImageViewHolder>{

        private int Holder;
        FragmentActivity activity;

        private ArrayList<Model_RecyclerHorizemtal> list;
        private Context context;

        public Adaprer_RecyclerVertical(ArrayList<Model_RecyclerHorizemtal> list, Context context , FragmentActivity activity) {
            this.list = list;
            this.context = context;
            this.activity=activity;
        }

        @NonNull
        @Override
        public Adaprer_RecyclerVertical.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Holder=viewType;
            View view=LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final Adaprer_RecyclerVertical.ImageViewHolder holder, final int position) {
            final Model_RecyclerHorizemtal Model =list.get(position);

            switch (Holder){
                case R.layout.z_m_recyclerhorizental:
                    holder.Titel.setText(Model.getTitel());
                    holder.Titel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Show_All_Item fragment=new Show_All_Item();
                            Bundle bundle =new Bundle();
                            bundle.putParcelableArrayList("List",Model.getList_horizental());
                            fragment.setArguments(bundle);
                            activity.GetManager().OpenView(fragment,"Show",true);
                        }
                    });

                    holder.Horizental.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true));
                    Main_Adapter mainadapter=new Main_Adapter(Model.getList_horizental(),activity, new OnItemClickListener() {
                        @Override
                        public void onItemClick(Model_All model) {

                        }
                    });
                    holder.Horizental.setAdapter(mainadapter);
                    break;
                case R.layout.z_m_recycler_tablighat:
                    RecyclerView.LayoutManager layoutManagerTabligh=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true);
                    holder.Vertical.setLayoutManager(layoutManagerTabligh);
                    SnapHelper snapHelper=new LinearSnapHelper();

                    snapHelper.attachToRecyclerView(holder.Vertical);

                    final Main_Adapter main_adapter=new Main_Adapter(Model.getList_horizental(), activity, new OnItemClickListener() {
                        @Override
                        public void onItemClick(Model_All model) {

                        }
                    });
                    holder.Vertical.setAdapter(main_adapter);
                    holder.Vertical.setNestedScrollingEnabled(false);
                    break;

            }



        }

        private String type;
        @Override
        public int getItemViewType(int position) {
            Model_RecyclerHorizemtal model_recyclerHorizemtal=list.get(position);
            type=model_recyclerHorizemtal.getType();
            switch (type){
                case "Home":
                    return R.layout.z_m_recyclerhorizental;
                case "Tabligh":
                    return R.layout.z_m_recycler_tablighat;

            }return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {
            private TextView Titel;
            private ImageView tabligh;
            private RecyclerView Horizental,Vertical;
            public ImageViewHolder(View itemView) {
                super(itemView);
                switch (Holder){
                    case R.layout.z_m_recyclerhorizental:
                        Titel=(TextView)itemView.findViewById(R.id.z_m_recycler_Titel);
                        Horizental=(RecyclerView)itemView.findViewById(R.id.RecyclerHorizontall);
                        break;
                    case R.layout.z_m_recycler_tablighat:
                        Vertical=(RecyclerView)itemView.findViewById(R.id.z_m_recyclertabligh_recycler);


                }


            }
        }
    }





}



