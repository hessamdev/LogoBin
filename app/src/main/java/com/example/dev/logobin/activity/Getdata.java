package com.example.dev.logobin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.dev.logobin.Network.GetBrand;
import com.example.dev.logobin.Network.GetDastebandi;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.handel.DB_DasteBandi;
import com.example.dev.logobin.handel.DB_ZirDaste;

public class Getdata extends FragmentActivity {

    GetBrand getBrand;
    GetDastebandi getDastebandi;
    DB_Brand db_brand;
    DB_DasteBandi db_dasteBandi;
    DB_ZirDaste db_zirDaste;

    private Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdata);

        db_brand=new DB_Brand(this);
        db_brand.delete();
        db_dasteBandi=new DB_DasteBandi(this);
        db_dasteBandi.delete();

        db_zirDaste=new DB_ZirDaste(this);
        db_zirDaste.delete();



        getDastebandi=new GetDastebandi(this);
        getDastebandi.getDaste();

        getBrand=new GetBrand(this);
        getBrand.getBrand();



        handler=new Handler();
        handler.postDelayed(runnable,500);





    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (db_brand.getData().size() > 0  ){
                gohome();
                Log.i("Run","true home");
                handler.removeCallbacks(runnable);
            }else {
                Log.i("Run","false");
                handler.postDelayed(runnable,500);

            }
        }
    } ;

    private void gohome(){
        Toast.makeText(this, "go home", Toast.LENGTH_SHORT).show();
        Intent intent  = new Intent(Getdata.this,MainPage.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onResume() {
        super.onResume();


    }
}




