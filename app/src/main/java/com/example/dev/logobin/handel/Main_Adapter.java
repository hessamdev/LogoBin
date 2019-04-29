package com.example.dev.logobin.handel;


import android.content.Context;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.example.dev.logobin.model.M_Home;
import com.example.dev.logobin.model.Model_RecyclerHorizemtal;
import com.example.dev.logobin.ui.Brand_View;
import com.example.dev.logobin.ui.Sherkat_View;
import com.example.dev.logobin.ui.Show_All_Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.MyViewHolder> {

    int Holder;
    private ArrayList<M_Home> list ;
    private Animation animation;


    private ArrayList<M_Home> getlistadapter;
    private FragmentActivity activity;
    private OnItemClickListener clickListener;

    public Main_Adapter(ArrayList<M_Home> getlistadapter, FragmentActivity activity, OnItemClickListener clickListener) {
        this.getlistadapter = getlistadapter;
        this.activity = activity;
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
        void onItemClick(M_Home model);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder=viewType;
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final M_Home model=list.get(position);
        animation= AnimationUtils.loadAnimation(activity,R.anim.zoomin);



        switch (holder.getItemViewType()){
            case R.layout.z_m_recyclerview_brand_2 :

                holder.itemView.startAnimation(animation);
                Picasso.get().load(model.getImage()).resize(200,200).into(holder.imageView_Logo);
                holder.name.setText(model.getTitle());
                holder.number_rate.setText(model.getRate());
                if (model.getState() != null){
                if (model.getState().equals("tab")){
                    holder.tab.setVisibility(View.VISIBLE);
                }else {
                    holder.tab.setVisibility(View.GONE);
                }
                  }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(model);
//                        Toast.makeText(activity.getApplicationContext(), ""+model.getId(), Toast.LENGTH_SHORT).show();


                        Bundle bundle = new Bundle();
                        Brand_View brand_view= new Brand_View();
                        bundle.putString("Factory_id",model.getId());
                        bundle.putString("Url_Image",model.getImage());
                        bundle.putString("Rate",model.getRate());
                        brand_view.setArguments(bundle);

                        activity.GetManager().OpenView(brand_view,"Brand_View",true);


                    }
                });
                break;
            case R.layout.z_m_recyclerview_brand_grid:
                Picasso.get().load(model.getImage()).resize(300,300).into(holder.imageView_Logo);
                holder.name.setText(model.getTitle());
                holder.number_rate.setText(model.getRate());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(model);
                        activity.GetManager().OpenView(new Brand_View(),"Brand_View",true);
                    }
                });
                break;
            case R.layout.z_m_recyclerview_sherkat :
                holder.itemView.startAnimation(animation);
                if (list.get(0).getPartId() != null) {

                    if (model.getState().equals("off")){
                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            holder.online.setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.circle_gray_kala) );
                        } else {
                            holder.online.setBackground(ContextCompat.getDrawable(activity, R.drawable.circle_gray_kala));
                        }

                    }else {

                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            holder.online.setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.circle_green_kala) );
                        } else {
                            holder.online.setBackground(ContextCompat.getDrawable(activity, R.drawable.circle_green_kala));
                        }
                    }

                    if (model.getType().equals("tab")){
                        holder.tab.setVisibility(View.VISIBLE);
                    }else {
                        holder.tab.setVisibility(View.GONE);
                    }

                    Picasso.get().load(model.getImage()).resize(200, 200).into(holder.imageView_Logo);
                    holder.name.setText(model.getTitle());
                    holder.number_rate.setText(model.getRate());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickListener.onItemClick(model);
                            Toast.makeText(activity, "" + model.getId(), Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("Image", model.getImage());
                            bundle.putString("ID", model.getId());
                            bundle.putString("Rate", model.getRate());
                            bundle.putString("Title", model.getTitle());
                            Sherkat_View sherkat_view = new Sherkat_View();
                            sherkat_view.setArguments(bundle);
                            activity.GetManager().OpenView(sherkat_view, "Sherkat_View", true);
                        }
                    });
                }
                break;
            case R.layout.z_m_recyclerview_sherkat_gheymat:
                holder.itemView.startAnimation(animation);
                String t =" تومان";
                if (model.getState().equals("off")){
                    final int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        holder.online.setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.circle_gray_kala) );
                    } else {
                        holder.online.setBackground(ContextCompat.getDrawable(activity, R.drawable.circle_gray_kala));
                    }
                }else {
                    final int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        holder.online.setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.circle_green_kala) );
                    } else {
                        holder.online.setBackground(ContextCompat.getDrawable(activity, R.drawable.circle_green_kala));
                    }
                }

                holder.expendImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model.setExpend(true);
                        notifyItemChanged(position);
                    }
                });

                if (model.getExpend()){
                    holder.expendImage.setVisibility(View.GONE);
                    holder.expend.setVisibility(View.VISIBLE);
                }else {
                    holder.expend.setVisibility(View.INVISIBLE);
                    holder.expendImage.setVisibility(View.VISIBLE);
                }

                Picasso.get().load(model.getImage()).resize(200,200).into(holder.imageView_Logo);
                holder.number_rate.setText(model.getRate());
                holder.gheymat.setText(model.getPrice()+t);
                holder.timeup.setText(model.getLastseen());
                holder.cost.setText(model.getCost());
                holder.customers.setText(model.getCustomers());
                holder.minimum.setText(model.getMinimum());
                holder.pack.setText(model.getPack());
                holder.unit.setText(model.getUnit());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(model);
                        Bundle bundle = new Bundle();
                        bundle.putString("Image", model.getImage());
                        bundle.putString("ID", model.getId());
                        bundle.putString("Rate", model.getRate());
                        bundle.putString("Title", model.getTitle());
                        Sherkat_View sherkat_view = new Sherkat_View();
                        sherkat_view.setArguments(bundle);
                        activity.GetManager().OpenView(sherkat_view, "Sherkat_View", true);
                    }
                });
                break;

        }
    }

    private String type;

    @Override
    public int getItemViewType(int position) {
        M_Home model_all=list.get(position);
           type=model_all.getViewv();


        switch (type) {
            case "brand":
                return R.layout.z_m_recyclerview_brand_2;
            case "Brand_G":
                return R.layout.z_m_recyclerview_brand_grid;
            case "Recycler_Image" :
                return R.layout.z_m_heder_image_recycler;
            case "company":
                return R.layout.z_m_recyclerview_sherkat;
            case "Sherkat_gheymat":
                return R.layout.z_m_recyclerview_sherkat_gheymat;

        }return super.getItemViewType(position);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView saye;
        CircleImageView imageView_Logo;
        TextView name,number_rate,gheymat,timeup,tab,pack,unit,minimum,customers,cost,lastseen;
        RelativeLayout sherkatview,sherkatgone,expendImage;
        LinearLayout expend;
        View online;
        RecyclerView Heder_Image;
        public MyViewHolder(View itemView) {
            super(itemView);
            switch (Holder){
                case R.layout.z_m_recyclerview_brand_2:
                    tab=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_tab) ;
                    imageView_Logo =(CircleImageView)itemView.findViewById(R.id.z_model_recyclerBrand_ImageView_Brand);
                    saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_Imageview_Saye);
                    Picasso.get().load(R.drawable.sayehpng).resize(200,200).into(saye);
                    name=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_name);
                    number_rate=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_Rate);
                    break;
                case R.layout.z_m_recyclerview_brand_grid:
                    imageView_Logo =(CircleImageView)itemView.findViewById(R.id.z_model_recyclerBrand_ImageView_Brand);
                    saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerBrand_Imageview_Saye);
                    Picasso.get().load(R.drawable.sayehpng).resize(200,200).into(saye);
                    name=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_name);
                    number_rate=(TextView)itemView.findViewById(R.id.z_model_recyclerBrand_Textview_Rate);
                    break;
                case R.layout.z_m_recyclerview_sherkat:
                    online=(View) itemView.findViewById(R.id.z_model_recyclerSherkat_view_online);
                    tab=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_tab) ;
                    sherkatview=(RelativeLayout)itemView.findViewById(R.id.z_m_recyclersherkat_RelativeView) ;
                    sherkatgone=(RelativeLayout)itemView.findViewById(R.id.z_m_recyclersherkat_RelativeGone) ;
                    imageView_Logo =(CircleImageView)itemView.findViewById(R.id.z_model_recyclerSherkat_ImageView_Sherkat);
                    saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerSherkat_Imageview_Saye);
                    Picasso.get().load(R.drawable.sayehpng).resize(200,200).into(saye);
                    name=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_name);
                    number_rate=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_Rate);
                    break;
                case R.layout.z_m_recyclerview_sherkat_gheymat:
                    online=(View) itemView.findViewById(R.id.z_model_recyclerSherkat_view_online);
                    imageView_Logo =(CircleImageView)itemView.findViewById(R.id.z_model_recyclerSherkat_ImageView_Sherkat);
                    saye=(ImageView)itemView.findViewById(R.id.z_model_recyclerSherkat_Imageview_Saye);
                    Picasso.get().load(R.drawable.sayehpng).resize(200,200).into(saye);
                    gheymat=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_Gheymat);
                    timeup=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_TimeUpdate);
                    number_rate=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_Rate);
                    pack=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_pack);
                    unit=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_unit);
                    minimum=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_minimum);
                    customers=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_customers);
                    cost=(TextView)itemView.findViewById(R.id.z_model_recyclerSherkat_Textview_cost);
                    expend =(LinearLayout)itemView.findViewById(R.id.z_model_recyclerBrand_Linear_Expend);
                    expendImage=(RelativeLayout)itemView.findViewById(R.id.z_model_recyclerSherkat_Relative_ExpendView);
                    break;

            }

        }

    }















    public static class Adaprer_RecyclerVertical extends RecyclerView.Adapter<Adaprer_RecyclerVertical.ImageViewHolder>{

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

            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_recyclerhorizental,parent,false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final Adaprer_RecyclerVertical.ImageViewHolder holder, final int position) {
            final Model_RecyclerHorizemtal Model =list.get(position);


                    holder.Titel.setText(Model.getTitel());
                    holder.All.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Model.getList_horizental().get(0).getPartId() != null ) {
                                Show_All_Item fragment = new Show_All_Item();
                                Bundle bundle = new Bundle();
                                bundle.putString("Title", Model.getTitel());
                                bundle.putString("Part",Model.getList_horizental().get(0).getPartId());
                                bundle.putString("Viewv",Model.getList_horizental().get(0).getViewv());
                                if (Model.getList_horizental().get(0).getViewv().equals("Sherkat_gheymat")  ){
                                    bundle.putParcelableArrayList("List",Model.getList_horizental());
                                }
                                if (Model.getList_horizental().get(0).getPartId().equals("Search")){
                                    bundle.putParcelableArrayList("List",Model.getList_horizental());
                                    bundle.putString("Viewv","Sherkat_gheymat");
                                }
                                fragment.setArguments(bundle);
                                activity.GetManager().OpenView(fragment, "Show", true);
                            }
                        }
                    });
                    holder.TitelAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Model.getList_horizental().get(0).getPartId() != null && Model.getList_horizental().size() >0) {
                                Show_All_Item fragment = new Show_All_Item();
                                Bundle bundle = new Bundle();
                                bundle.putString("Title", Model.getTitel());
                                bundle.putString("Part",Model.getList_horizental().get(0).getPartId());
                                bundle.putString("Viewv",Model.getList_horizental().get(0).getViewv());
                                if (Model.getList_horizental().get(0).getViewv().equals("Sherkat_gheymat")){
                                    bundle.putParcelableArrayList("List",Model.getList_horizental());
                                }
                                if (Model.getList_horizental().get(0).getPartId().equals("Search")){
                                    bundle.putParcelableArrayList("List",Model.getList_horizental());
                                    bundle.putString("Viewv","Sherkat_gheymat");
                                }
                                fragment.setArguments(bundle);
                                activity.GetManager().OpenView(fragment, "Show", true);
                            }
                        }
                    });
                    holder.Titel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Model.getList_horizental().get(0).getPartId() != null && Model.getList_horizental().size() >0) {
                                Show_All_Item fragment = new Show_All_Item();
                                Bundle bundle = new Bundle();
                                bundle.putString("Title", Model.getTitel());
                                bundle.putString("Part",Model.getList_horizental().get(0).getPartId());
                                bundle.putString("Viewv",Model.getList_horizental().get(0).getViewv());
                                if (Model.getList_horizental().get(0).getViewv().equals("Sherkat_gheymat")){
                                    bundle.putParcelableArrayList("List",Model.getList_horizental());
                                }
                                if (Model.getList_horizental().get(0).getPartId().equals("Search")){
                                    bundle.putParcelableArrayList("List",Model.getList_horizental());
                                    bundle.putString("Viewv","Sherkat_gheymat");
                                }
                                fragment.setArguments(bundle);
                                activity.GetManager().OpenView(fragment, "Show", true);
                            }
                        }
                    });


                        if (Model.getList_horizental().get(0).getPartId() != null ) {
                            holder.gone.setVisibility(View.GONE);
                            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
                            holder.Horizental.setLayoutManager(layoutManager);
//                            holder.Horizental.setHasFixedSize(true);
                            Main_Adapter mainadapter = new Main_Adapter(Model.getList_horizental(), activity, new OnItemClickListener() {
                                @Override
                                public void onItemClick(M_Home model) {

                                }
                            });
                            holder.Horizental.setHasFixedSize(true);
                            holder.Horizental.setNestedScrollingEnabled(true);
                            holder.Horizental.setAdapter(mainadapter);

                    }else {
                        holder.Horizental.setVisibility(View.GONE);
                        holder.TitelAll.setVisibility(View.GONE);
                        holder.image_All.setVisibility(View.GONE);
                    }

        }



        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {
            private TextView Titel,TitelAll;
            private RelativeLayout All;
            private LinearLayout gone;
            private ImageView tabligh,image_All;
            private RecyclerView Horizental,Vertical;
            public ImageViewHolder(View itemView) {
                super(itemView);

                        image_All=(ImageView)itemView.findViewById(R.id.imageView_All) ;
                        gone=(LinearLayout) itemView.findViewById(R.id.Recyclerhorizental_view_gone);
                        All=(RelativeLayout) itemView.findViewById(R.id.z_m_recycler_Relative_all);
                        Titel=(TextView)itemView.findViewById(R.id.z_m_recycler_Titel);
                        TitelAll=(TextView)itemView.findViewById(R.id.z_m_recycler_Titel_all);
                        Horizental=(RecyclerView)itemView.findViewById(R.id.RecyclerHorizontall);

                }
            }
        }
    }









