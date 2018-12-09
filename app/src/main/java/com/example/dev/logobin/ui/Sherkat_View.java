package com.example.dev.logobin.ui;

import android.content.Intent;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Adapter_Daste_Brand;
import com.example.dev.logobin.handel.Main_Adapter;
import com.example.dev.logobin.model.Model_All;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class Sherkat_View extends FragmentView {

    private ArrayList<Model_All> list_Kala;
    private ArrayList<String> list_Daste;


    @Override
    public void OnCreate() {
        View view=LayoutInflater.from(Activity).inflate(R.layout.fragment_sherkat2,null);
        setUpList();

        LinearLayout back=(LinearLayout)view.findViewById(R.id.Sherkat_Imageview_Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });

        LinearLayout Share=(LinearLayout)view.findViewById(R.id.sherkat_Linear_share);
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                String name="name";
                String appname="appname";
                sendIntent.putExtra(Intent.EXTRA_SUBJECT,appname);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                Activity.startActivity(Intent.createChooser(sendIntent,"Share Using"));

            }
        });

        LinearLayout bookmark=(LinearLayout)view.findViewById(R.id.sherkat_Linear_bookmark);
        final ImageView imagebookmark=(ImageView)view.findViewById(R.id.sherkat_imageview_bookmark) ;
        imagebookmark.setImageResource(R.drawable.bookmark_empty);
        imagebookmark.setTag(R.drawable.bookmark_empty);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( Integer.parseInt(imagebookmark.getTag().toString()) == R.drawable.bookmark_empty){
                    imagebookmark.setImageResource(R.drawable.bookmark_full);
                    imagebookmark.setTag(R.drawable.bookmark_full);
                }else {
                    imagebookmark.setImageResource(R.drawable.bookmark_empty);
                    imagebookmark.setTag(R.drawable.bookmark_empty);
                }

            }
        });


        ImageView heder = (ImageView) view.findViewById(R.id.Sherkat_Imageview_Heder);
        Transformation transformation=new RoundedTransformationBuilder().cornerRadiusDp(20).build();
        heder.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load(R.drawable.baner1).transform(transformation).into(heder);
//        FrameLayout rate=(FrameLayout) view.findViewById(R.id.Sherkat_Frame_Rate);
//        View Rate=LayoutInflater.from(Activity).inflate(R.layout.z_n_rating_g2,null);
//        rate.addView(Rate);
//        rate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog=new Dialog(Activity);
//                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.setContentView(R.layout.z_m_alertdialog_rating);
//                Button sabt = (Button)dialog.findViewById(R.id.AlertRate_Button_Sabt);
//                final TextView rate=(TextView)dialog.findViewById(R.id.z_m_rating_textview) ;
//                final String[] s = {null};
//                final MaterialRatingBar materialRatingBar=(MaterialRatingBar) dialog.findViewById(R.id.z_m_rating_ratingbar);
//                rate.setText(String.valueOf(materialRatingBar.getRating()));
//                materialRatingBar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
//                    @Override
//                    public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
//                        rate.setText(String.valueOf(rating));
//                    }
//                });
//                sabt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(Activity, "امتیاز شما "+ String.valueOf(materialRatingBar.getRating()) +" ثبت شد.", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        });
//        FrameLayout look=(FrameLayout) view.findViewById(R.id.Sherkat_Frame_Look);
//        View Look=LayoutInflater.from(Activity).inflate(R.layout.z_n_look_g,null);
//        look.addView(Look);
//        look.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog=new Dialog(Activity);
//                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.setContentView(R.layout.z_m_alertdialog_look);
//
//                dialog.show();
//            }
//        });
//

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.Sherkat_Recyclerview);
        RecyclerView recyclerViewDaste=(RecyclerView)view.findViewById(R.id.Sherkat_Recycerview_Daste);
        recyclerViewDaste.setLayoutManager(new LinearLayoutManager(Activity,LinearLayoutManager.HORIZONTAL,true));
        recyclerViewDaste.setHasFixedSize(true);
//        recyclerViewDaste.setAdapter(new Adapter_Daste_Brand(list_Daste, new Adapter_Daste_Brand.OnItemClickListener() {
//            @Override
//            public void onItemClick(String Daste) {
//                Toast.makeText(Activity, ""+Daste, Toast.LENGTH_SHORT).show();
//            }
//        }));


        recyclerView.setLayoutManager(new GridLayoutManager(Activity,2, LinearLayoutManager.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        // recyclerView_Kala.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(new Main_Adapter(list_Kala,Activity, new Main_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Model_All model) {

            }
        }));

        ViewMain=view;
    }
    public void setUpList(){
        list_Daste=new ArrayList<>();
        list_Daste.add("تمام موارد");
        list_Daste.add("بستنی");
        list_Daste.add("کره");
        list_Daste.add("پنیر");
        list_Daste.add("شیر");
        list_Daste.add("ماست");
        list_Daste.add("ماست");
        list_Daste.add("بستنی");
        list_Daste.add("کره");
        list_Daste.add("پنیر");
        list_Daste.add("شیر");
        list_Daste.add("ماست");


        list_Kala=new ArrayList<>();
        String toman=" تومان";
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala1,"پنیر کاله","5.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala2,"ماست دامداران","6.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala3,"ماست دامداران","100"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala4,"ماست دامداران","10.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala5,"شیر دامداران","3.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala6,"شیر دامداران","20.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala7,"پنیر خامه ای دامداران","100.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala1,"پنیر کاله","1.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala2,"ماست دامداران","2.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala3,"ماست دامداران","3.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala4,"ماست دامداران","4.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala5,"شیر دامداران","5.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala6,"شیر دامداران","6.000"+toman));
        list_Kala.add(new Model_All("Kala_G",R.drawable.kala7,"پنیر خامه ای دامداران","7.000"+toman));
    }


}
