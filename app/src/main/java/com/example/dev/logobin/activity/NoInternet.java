package com.example.dev.logobin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;

public class NoInternet extends FragmentActivity {

    private Handler handler ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nointernet);

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
            super.onBackPressed();
        } else {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        handler=new Handler();

        final Runnable r=new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable()){
                    onBackPressed();
                    handler.postDelayed(this,500);
                }

            }
        };
        handler.postDelayed(r,500);

    }
}
