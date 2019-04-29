package com.example.dev.logobin.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.example.dev.logobin.Network.GetDastebandi;
import com.example.dev.logobin.Network.GetKala;
import com.example.dev.logobin.Network.PostBrand;
import com.example.dev.logobin.R;
import com.example.dev.logobin.RecyclerApp.RecyclerKala;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Adapter_Daste_Brand;
import com.example.dev.logobin.handel.Create_Alert;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.model.M_Kala;
import com.example.dev.logobin.model.M_ZirDaste;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class Brand_View extends FragmentView {

    private String Factory_Id,Url_ImageBrand,SRate;
    private RecyclerView recyclerView_Kala;
    private RecyclerKala Adapter;

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerViewDaste;
    private Adapter_Daste_Brand Adapter_Daste;

    private GetKala getKala;

    private GetDastebandi getDastebandi;


    private ArrayList<M_Kala> list_Kala;
    private ArrayList<M_ZirDaste> list_Daste;

    private FrameLayout rate , Logo_Smal;
    private ImageView smallLogo;

    private LayoutAnimationController controller;

    private int load;
    private String zirdaste_Id;
    private boolean endless =true ;
    private boolean getdata =true ;

    private AppBarLayout barLayout;
    private Dialog dialog;

    private User_Data user_data;
    private ImageView bezoodiloading;
    private RelativeLayout bezoodi;

    @Override
    public void OnCreate() {
        View view = LayoutInflater.from(Activity).inflate(R.layout.fragment_brand2,null);
        barLayout =(AppBarLayout)view.findViewById(R.id.appbar_brand) ;
        barLayout.setVisibility(View.INVISIBLE);
        bezoodi=(RelativeLayout) view.findViewById(R.id.Brand_Relative_bezoodi);
        bezoodiloading=(ImageView) view.findViewById(R.id.loading_bezoodi);
        DrawableImageViewTarget target = new DrawableImageViewTarget(bezoodiloading);
        Glide.with(Activity)
                .load(R.drawable.loader)
                .into(target);

        bezoodi.setVisibility(View.GONE);
        Create_Alert create_alert=new Create_Alert(Activity);

        dialog=create_alert.createAlert_Load();
        dialog.show();



        Factory_Id=getArguments().getString("Factory_id");
        Url_ImageBrand=getArguments().getString("Url_Image");
        SRate=getArguments().getString("Rate");
        list_Kala=new ArrayList<>();

        user_data=new User_Data(Activity);

//        Toast.makeText(Activity, ""+Factory_Id , Toast.LENGTH_SHORT).show();

        getDastebandi=new GetDastebandi(Activity);

        getKala=new GetKala(Activity);

        recyclerViewDaste=(RecyclerView)view.findViewById(R.id.Brand_Recycerview_Daste);
        recyclerViewDaste.setLayoutManager(new LinearLayoutManager(Activity,LinearLayoutManager.HORIZONTAL,true));
        recyclerViewDaste.setHasFixedSize(true);

        recyclerView_Kala = (RecyclerView) view.findViewById(R.id.Brand_Recyclerview);
        recyclerView_Kala.setVisibility(View.GONE);
        recyclerView_Kala.setNestedScrollingEnabled(true);
        layoutManager = new GridLayoutManager(Activity,2, LinearLayout.VERTICAL,false);
        recyclerView_Kala.setLayoutManager(layoutManager);
        recyclerView_Kala.setHasFixedSize(true);


        controller  = AnimationUtils.loadLayoutAnimation(Activity,R.anim.recycler_fall_down_layout);




        getDastebandi.zirdastebrand(Factory_Id, new GetDastebandi.callback() {
            @Override
            public void onsuccess(ArrayList<M_ZirDaste> listss) {
                if (listss.size()==0){
                    Toast.makeText(Activity, "اطلاعاتت در حال تکمیل میباشد ... ", Toast.LENGTH_SHORT).show();
                    barLayout.setVisibility(View.VISIBLE);
                    bezoodi.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }else  {
                    zirdaste_Id = listss.get(0).getId();
                    load = 0;
                    setUpRecyclerKala(zirdaste_Id, load);
                    Adapter_Daste = new Adapter_Daste_Brand(listss, new Adapter_Daste_Brand.OnItemClickListener() {
                        @Override
                        public void onItemClick(M_ZirDaste m_zirDaste) {
                            zirdaste_Id = m_zirDaste.getId();
//                            Toast.makeText(Activity, "Id =" + m_zirDaste.getId(), Toast.LENGTH_SHORT).show();
                            endless = true;
                            load = 0;
                            list_Kala.clear();
                            recyclerView_Kala.setAdapter(Adapter);
                            setUpRecyclerKala(zirdaste_Id, load);


                        }
                    });
                    recyclerViewDaste.setAdapter(Adapter_Daste);

                }
            }

            @Override
            public void faild(String eror) {
                Toast.makeText(Activity, "Api DasteBandi Error", Toast.LENGTH_SHORT).show();
            }
        });




        CollapsingToolbarLayout colaps=view.findViewById(R.id.collapsing_toolbar);
        colaps.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                Log.i("top",""+top);
//                Log.i("bottom",""+bottom);
//                Log.i("oldBottom",""+oldBottom);
            }
        });
        smallLogo=(ImageView)view.findViewById(R.id.Brand_Imageview_smallLogo);
        Logo_Smal=(FrameLayout)view.findViewById(R.id.Brand_Frame_smalLogo) ;
        Picasso.get().load(Url_ImageBrand).resize(100,100).into(smallLogo);
        barLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offsetAlpha = (appBarLayout.getY() / barLayout.getTotalScrollRange());
//                rate.setAlpha( 0 + (offsetAlpha * -1));
                smallLogo.setAlpha( 0 + (offsetAlpha * -1));
                Logo_Smal.setAlpha( 0 + (offsetAlpha * -1));
//                Log.i("ofset",""+offsetAlpha);
            }
        });

        FrameLayout back=(FrameLayout) view.findViewById(R.id.Brand_Imageview_Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });


        ImageView heder = (ImageView) view.findViewById(R.id.Brand_Imageview_Heder);
        heder.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load(Url_ImageBrand).into(heder);
        rate=(FrameLayout) view.findViewById(R.id.Brand_Frame_Rate);
        View Rate=LayoutInflater.from(Activity).inflate(R.layout.z_n_rating,null);
        TextView numrate=(TextView)Rate.findViewById(R.id.z_n_Textview_rate) ;
        numrate.setText(SRate);
        rate.addView(Rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(Activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.z_m_alertdialog_rating);
                Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.1f);
                TextView sabt = (TextView) dialog.findViewById(R.id.AlertRate_Button_Sabt);
                final TextView rate=(TextView)dialog.findViewById(R.id.z_m_rating_textview) ;
                final String[] s = {null};
                final MaterialRatingBar materialRatingBar=(MaterialRatingBar) dialog.findViewById(R.id.z_m_rating_ratingbar);
                rate.setText(String.valueOf(materialRatingBar.getRating()));
                materialRatingBar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
                    @Override
                    public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                        if (rating >=1f) {
                            rate.setText(String.valueOf(rating));
                        }
                        if (rating < 1.0f){
//                    materialRatingBar.setRating(1f);
                            ratingBar.setRating(1.0f);
                            Log.i("Rating",rating+"");
                        }
                    }
                });
                sabt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PostBrand postBrand= new PostBrand(Activity);

                        postBrand.Post_Rate_Brand(user_data.getUser_id(),Factory_Id,rate.getText().toString());
                        Toast.makeText(Activity, "امتیاز شما "+ rate.getText().toString() +" ثبت شد.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });






        ViewMain=view;
    }

    private void setUpRecyclerKala(String IdZirDaste,int loading){
        getKala.getKala_Brand(Factory_Id, IdZirDaste,user_data.getMahale(), loading, new GetKala.vcallback() {
            @Override
            public void onSuccess(ArrayList<M_Kala> list) {
                //                Toast.makeText(Activity, "Total item to Load = "+list.size(), Toast.LENGTH_SHORT).show();
//                if (list.size()==0){
//                    endless=false;
//                }
//                Toast.makeText(Activity, "Add kala back", Toast.LENGTH_SHORT).show();
                list_Kala.addAll(list);
                Adapter=new RecyclerKala(Activity, list_Kala,Activity, new RecyclerKala.OnItemClickListener() {
                    @Override
                    public void onItemClick(M_Kala model) {
//                        Toast.makeText(Activity, ""+model.getId(), Toast.LENGTH_SHORT).show();
                        Kala_View kala_view=new Kala_View();

                        Bundle bundle =new Bundle();
                        bundle.putString("ID_kala",model.getId());
                        bundle.putString("ID_mahale",user_data.getMahale());
                        bundle.putString("ImageKala",model.getImage());
                        bundle.putString("NameKala",model.getTitle());
                        kala_view.setArguments(bundle);

                        Activity.GetManager().OpenView(kala_view,"Kala_View",true);
                    }
                });
//                if (load == 0) {
                recyclerView_Kala.setAdapter(Adapter);
//                    recyclerView_Kala.setLayoutAnimation(controller);
//                }

                Adapter.notifyDataSetChanged();
                dialog.dismiss();
                barLayout.setVisibility(View.VISIBLE);
                recyclerView_Kala.setVisibility(View.VISIBLE);
//                getdata=true
            }

            @Override
            public void faild(String Eror) {
                Toast.makeText(Activity, "Kala daryaft nashod", Toast.LENGTH_SHORT).show();
                Log.i("Kala_get_eror",Eror);
                dialog.dismiss();
                Activity.onBackPressed();
            }
        });
    }



    @Override
    public void OnResume() {
        super.OnResume();
    }

}

