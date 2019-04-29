package com.example.dev.logobin.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.Network.GetSherkat;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Main_Adapter;
import com.example.dev.logobin.model.M_Home;
import com.example.dev.logobin.model.Model_All;
import com.example.dev.logobin.model.Model_RecyclerHorizemtal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Kala_View extends FragmentView {
    ArrayList<M_Home> list_Sherkat;
    ArrayList<Model_RecyclerHorizemtal> Kalalist;
    ArrayList<Model_All> list_tablighat;

    private String id_mahale,id_kala;

    private RecyclerView recyclerView;

    @Override
    public void OnCreate() {
        View view= LayoutInflater.from(Activity).inflate(R.layout.fragment_kala,null);

        final HorizontalScrollView scrollView=(HorizontalScrollView)view.findViewById(R.id.kala_scroll_title) ;

        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);

        id_kala=getArguments().getString("ID_kala");
        id_mahale=getArguments().getString("ID_mahale");

        setUpList();

        ImageView back =(ImageView) view.findViewById(R.id.Kala_Imageview_Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(Activity,R.anim.fadein);
        ImageView heder = (ImageView) view.findViewById(R.id.Kala_Imageview_Heder);
        heder.setAnimation(animation);
//        Transformation transformation=new RoundedTransformationBuilder().cornerRadiusDp(t_ghazae).build();
//        heder.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load(getArguments().getString("ImageKala")).into(heder);
        heder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image_View image_view=new Image_View();
                Bundle bundle = new Bundle();
                bundle.putString("Image",getArguments().getString("ImageKala"));
                bundle.putString("Title",getArguments().getString("NameKala"));
                image_view.setArguments(bundle);
                Activity.GetManager().OpenView(image_view,"ImageView",true);
            }
        });

        TextView nameKala= (TextView)view.findViewById(R.id.Kala_Textview_NameKala) ;
        nameKala.setText(getArguments().getString("NameKala"));
//        Toast.makeText(Activity, ""+nameKala.getWidth(), Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) view.findViewById(R.id.Kala_Recycerview_Kala);




        ViewMain=view;
    }

    private void setUpList() {
        list_Sherkat=new ArrayList<>();
        GetSherkat getSherkat= new GetSherkat(Activity);
        getSherkat.Getsherkat_for_kala(id_kala, id_mahale, new GetSherkat.Back_Sherkat() {
            @Override
            public void onSuccess(ArrayList<M_Home> list) {
                list_Sherkat.addAll(list);

                Kalalist=new ArrayList<>();
                Kalalist.add(new Model_RecyclerHorizemtal("Home","لیست قیمت شرکت ها",list_Sherkat));

                Main_Adapter.Adaprer_RecyclerVertical Adapter=new Main_Adapter.Adaprer_RecyclerVertical(Kalalist,Activity,Activity);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Activity,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setNestedScrollingEnabled(false);
                //recyclerView_Kala.setHasFixedSize(true);
                recyclerView.setAdapter(Adapter);

            }

            @Override
            public Void onfailor(String Error) {
                return null;
            }
        });

        list_tablighat=new ArrayList<>();
//        list_tablighat.add(new Model_All("Recycler_Tabligh",R.drawable.baner1));
//        list_tablighat.add(new Model_All("Recycler_Tabligh",R.drawable.baner2));
//        list_tablighat.add(new Model_All("Recycler_Tabligh",R.drawable.baner3));
//        list_tablighat.add(new Model_All("Recycler_Tabligh",R.drawable.baner1));






    }
}
