package com.example.dev.logobin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.squareup.picasso.Picasso;

public class NoInternet extends FragmentActivity {

    private Handler handler ;
    private  Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nointernet);

        ImageView imageinternet=(ImageView)findViewById(R.id.NoInternet_Imageview);
        Picasso.get().load(R.drawable.nointernet).resize(300,300).into(imageinternet);


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        if (isNetworkAvailable()) {
//            super.onBackPressed();
            finish();
        } else {

            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            finish();

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        handler=new Handler();


            runnable=new Runnable() {
                @Override
                public void run() {
                    Log.i("Nointernet",""+isNetworkAvailable());
                    if (isNetworkAvailable()){
                        onBackPressed();
                    }else {
                        handler.postDelayed(this,500);
                    }


                }
            };
            handler.postDelayed(runnable,500);





    }
}
