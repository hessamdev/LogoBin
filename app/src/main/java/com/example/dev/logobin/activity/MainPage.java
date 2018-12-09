package com.example.dev.logobin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dev.logobin.Network.GetBrand;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.home.DarkhastUI;
import com.example.dev.logobin.home.DasteUI;
import com.example.dev.logobin.home.Safheasli;
import com.example.dev.logobin.ui.Menu_View;
import com.example.dev.logobin.ui.Search_View;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainPage extends FragmentActivity {

    LinearLayout click_Safheasli;
    LinearLayout click_Darkhast;
    LinearLayout click_Dasteha;
    LinearLayout click_Menu;
    LinearLayout click_Search;
    ImageView Imageview_Darkhastha;
    ImageView Imageview_Dasteha;
    ImageView Imageview_Safheasli;
    ImageView Imageview_search;
    ImageView Imageview_menu;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_mainpage_activity);

        DB_Brand db_brand = new DB_Brand(this);
        GetBrand getBrand = new GetBrand(this);
        int num = db_brand.getData().size();

        Toast.makeText(this, String.valueOf(num), Toast.LENGTH_SHORT).show();

        if (isNetworkAvailable()) {

        init();

            click_Safheasli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Changepage(3);
                }
            });
            click_Dasteha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Changepage(2);
                }
            });
            click_Darkhast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Changepage(1);
                }
            });

            Picasso.get().load(R.drawable.search).resize(100, 100).into(Imageview_search);
            Picasso.get().load(R.drawable.tanzimat).resize(100, 100).into(Imageview_menu);

            click_Search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetManager().OpenView(new Search_View(), "Search_Page", false);
                }
            });
            click_Menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetManager().OpenView(new Menu_View(), "Menu_Page", false);
                }
            });

            Changepage(3);
        } else

        {

            Intent intent = new Intent(MainPage.this, NoInternet.class);
            finish();
            startActivity(intent);
        }

    }

        private void init () {
            click_Safheasli = (LinearLayout) findViewById(R.id.Main_Linear_Safheasli);
            click_Darkhast = (LinearLayout) findViewById(R.id.Main_Linear_Darkhastha);
            click_Dasteha = (LinearLayout) findViewById(R.id.Main_Linear_Dasteha);
            click_Menu = (LinearLayout) findViewById(R.id.Main_Linear_Menu);
            click_Search = (LinearLayout) findViewById(R.id.Main_Linear_Search);
            Imageview_Darkhastha = (ImageView) findViewById(R.id.Main_Imageview_Darkhastha);
            Imageview_Dasteha = (ImageView) findViewById(R.id.Main_Imageview_Dasteha);
            Imageview_Safheasli = (ImageView) findViewById(R.id.Main_Imageview_Safheasli);
            Imageview_search = (ImageView) findViewById(R.id.Main_Imageview_Search);
            Imageview_menu = (ImageView) findViewById(R.id.Main_Imageview_Menu);
        }


        private boolean isNetworkAvailable () {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        private void Changepage ( int page){

            Picasso.get().load(R.drawable.call).resize(100, 100).into(Imageview_Darkhastha);
            Picasso.get().load(R.drawable.categury).resize(50, 50).into(Imageview_Dasteha);
            Picasso.get().load(R.drawable.home).resize(50, 50).into(Imageview_Safheasli);
//        click_Darkhast.setBackgroundColor(getResources().getColor(R.color.c100));
//        click_Dasteha.setBackgroundColor(getResources().getColor(R.color.c100));
//        click_Safheasli.setBackgroundColor(getResources().getColor(R.color.c100));

            String tag = null;
            FragmentView fragment = null;

            switch (page) {
                case 1:
                    tag = "Darkhastha";
                    fragment = new DarkhastUI();

                    break;
                case 2:
                    tag = "Dasteha";
                    fragment = new DasteUI();

                    break;
                case 3:
                    tag = "Safheasli";
                    fragment = new Safheasli();

                    break;
            }
            GetManager().OpenView(fragment, tag, false);

        }


        @Override
        public void onPause () {
            super.onPause();


        }

        @Override
        public void onResume () {
            super.onResume();


        }


    }


