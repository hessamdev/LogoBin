package com.example.dev.logobin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dev.logobin.App;
import com.example.dev.logobin.Network.GetBrand;
import com.example.dev.logobin.Network.GetHome;
import com.example.dev.logobin.Network.GetSherkat;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.example.dev.logobin.handel.DB_BookMark;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.handel.DB_Sherkat;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.model.M_Home;
import com.example.dev.logobin.ui.Sherkat_View;

import java.util.ArrayList;

public class Getdata extends FragmentActivity {


    private User_Data user_data;

    private boolean brand_1=false,sherkat=false,brand_2=false;

    private ImageView logo_loading;
    private Animation zoomin;

    private ArrayList<M_Home> home;
    private int daste=0;

    private String id_Sherkat;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdata);
        user_data=new User_Data(this);

        Intent intent =getIntent();
        String action=intent.getAction();
        Uri data= intent.getData();
        if (data != null) {
            id_Sherkat = data.getQueryParameter("sh");
//            Toast.makeText(this, id_Sherkat, Toast.LENGTH_SHORT).show();
        }








        logo_loading=(ImageView)findViewById(R.id.image_logo_loading);

        zoomin= AnimationUtils.loadAnimation(this,R.anim.zoomin);
        final Animation zoomout= AnimationUtils.loadAnimation(this,R.anim.zoomout);

        zoomin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (user_data.getDatauser().get(0).getPhone() == null && isNetworkAvailable()) {
                    gohome(home);

                }else {

                    if (isNetworkAvailable() && !user_data.getDatauser().get(0).getPhone().equals("null")) {
//                        GetDataForHome();
                        if (brand_2 ) {
                            gohome(home);
                        } else {
                            logo_loading.startAnimation(zoomout);
                        }
                    } else {
                        Intent intent = new Intent(Getdata.this, NoInternet.class);
                        startActivity(intent);
                        onPause();
                        App.getInstance().cancelAllRequests("Cancel");
                    }
                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            logo_loading.startAnimation(zoomin);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logo_loading.setImageResource(R.drawable.logo_png);
        logo_loading.startAnimation(zoomin);





    }


    private void GetDataForHome(){
        GetHome getHome=new GetHome(this);
        getHome.getHome(user_data.getMahale(), new GetHome.Back_Brand() {
            @Override
            public void ok(ArrayList<M_Home> brands, int NnbDaste) {
                daste=NnbDaste;
                brand_2=true;
                home=new ArrayList<>(brands);
            }

            @Override
            public void faild(String Error) {
                Toast.makeText(Getdata.this, "مشکل در اتصال به سرور...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isNetworkAvailable () {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void gohome(ArrayList<M_Home> listhome){
//        Toast.makeText(this, "go home", Toast.LENGTH_SHORT).show();

//        if (id_Sherkat!=null){
//            Log.i("Go sherkat","send intent");
////            intent.putExtra("id_sh",id_Sherkat);
//
//            Bundle bundle = new Bundle();
//            bundle.putString("ID", id_Sherkat);
//            Sherkat_View sherkat_view = new Sherkat_View();
//            sherkat_view.setArguments(bundle);
//            GetManager().OpenView(sherkat_view, "Sherkat_View", true);
//
//        }else {
            Intent intent  = new Intent(Getdata.this,MainPage.class);
            intent.putParcelableArrayListExtra("listhome",listhome);
            intent.putExtra("NubDaste",daste);
            Log.i("Go sherkat","send intent");
            intent.putExtra("id_sh",id_Sherkat);
            startActivity(intent);
            finish();
//        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (brand_2){
            Log.i("Getdata","resume");
        }else if (user_data.getDatauser().get(0).getPhone()!= null){
            GetDataForHome();
            logo_loading.startAnimation(zoomin);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}




