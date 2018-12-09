package com.example.dev.logobin.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Main_Adapter;
import com.example.dev.logobin.model.Model_All;
import com.example.dev.logobin.model.Model_RecyclerHorizemtal;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class Kala_View extends FragmentView {
    ArrayList<Model_All> list_Sherkat;
    ArrayList<Model_RecyclerHorizemtal> Kalalist;
    ArrayList<Model_All> list_tablighat;
    @Override
    public void OnCreate() {
        View view= LayoutInflater.from(Activity).inflate(R.layout.fragment_kala,null);
        setUpList();

        ImageView back =(ImageView) view.findViewById(R.id.Kala_Imageview_Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });

        ImageView heder = (ImageView) view.findViewById(R.id.Kala_Imageview_Heder);
        Transformation transformation=new RoundedTransformationBuilder().cornerRadiusDp(20).build();
        heder.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load(getArguments().getString("ImageKala")).into(heder);

        TextView nameKala= (TextView)view.findViewById(R.id.Kala_Textview_NameKala) ;
        nameKala.setText(getArguments().getString("NameKala"));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.Kala_Recycerview_Kala);

        Main_Adapter.Adaprer_RecyclerVertical Adapter=new Main_Adapter.Adaprer_RecyclerVertical(Kalalist,Activity,Activity);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Activity,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        //recyclerView_Kala.setHasFixedSize(true);
        recyclerView.setAdapter(Adapter);


        ViewMain=view;
    }

    private void setUpList() {
        list_Sherkat=new ArrayList<>();
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_alborz,"4.2","1000","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_banian,"3.8","1100","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_karen,"5","990","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_mazrae,"2.9","1000","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_pardis,"4.8","980","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_ronak,"4","1150","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_alborz,"4.2","1000","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_banian,"3.8","1100","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_karen,"5","990","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_mazrae,"2.9","1000","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_pardis,"4.8","980","دقایقی پیش"));
//        list_Sherkat.add(new Model_All("Sherkat_gheymat",R.drawable.sh_ronak,"4","1150","دقایقی پیش"));

        list_tablighat=new ArrayList<>();
//        list_tablighat.add(new Model_All("Recycler_Tabligh",R.drawable.baner1));
//        list_tablighat.add(new Model_All("Recycler_Tabligh",R.drawable.baner2));
//        list_tablighat.add(new Model_All("Recycler_Tabligh",R.drawable.baner3));
//        list_tablighat.add(new Model_All("Recycler_Tabligh",R.drawable.baner1));


        Kalalist=new ArrayList<>();
//        Kalalist.add(new Model_RecyclerHorizemtal("Home","لیست قیمت شرکت ها",list_Sherkat));
        Kalalist.add(new Model_RecyclerHorizemtal("Tabligh",list_tablighat));



    }
}
