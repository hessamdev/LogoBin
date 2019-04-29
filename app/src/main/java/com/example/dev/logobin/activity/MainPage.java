package com.example.dev.logobin.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.example.dev.logobin.fragment.FragmentManager;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.handel.Navbotton;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.home.DarkhastUI;
import com.example.dev.logobin.home.DasteUI;
import com.example.dev.logobin.home.Safheasli;
import com.example.dev.logobin.model.M_Home;
import com.example.dev.logobin.ui.Menu_Register_view;
import com.example.dev.logobin.ui.Menu_View;
import com.example.dev.logobin.ui.Search_View;
import com.example.dev.logobin.ui.Sherkat_View;
import com.example.dev.logobin.ui.Show_All_Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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

    private User_Data user_data;

    private ArrayList<M_Home> list_home;
    private int daste;
    private String id_sherkat="";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_mainpage_activity);

        View view = LayoutInflater.from(this).inflate(R.layout.a_mainpage_activity,null);

        Navbotton navbotton = new Navbotton(this);
        navbotton.settview(null);

//        Intent inten =getIntent();
//        String action=inten.getAction();
//        Uri data= inten.getData();
//        if (data != null) {
//            String id = data.getQueryParameter("sh");
//            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
//        }

        init();

        user_data = new User_Data(this);


        if (isNetworkAvailable()) {

            if (user_data.getDatauser().get(0).getPhone() == null) {
                GetManager().OpenView(new Menu_Register_view(), "Register", true);
            } else {


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

                Intent intent = getIntent();
                list_home = new ArrayList<>();
                list_home = intent.getParcelableArrayListExtra("listhome");
                daste = intent.getIntExtra("NubDaste", 0);

                if (intent.getStringExtra("id_sh") !=null){
                    Log.i("Go sherkat","get intent");
                    id_sherkat=intent.getStringExtra("id_sh");
                }

                Changepage(3);

            }
        } else

        {

            Intent intent = new Intent(MainPage.this, NoInternet.class);
            finish();
            startActivity(intent);
        }

    }

    private void init() {
        click_Safheasli = (LinearLayout) findViewById(R.id.Main_Linear_Safheasli);
        click_Darkhast = (LinearLayout) findViewById(R.id.Main_Linear_Darkhastha);
        click_Dasteha = (LinearLayout) findViewById(R.id.Main_Linear_Dasteha);
        click_Menu = (LinearLayout) findViewById(R.id.Main_Linear_Menu);
        click_Search = (LinearLayout) findViewById(R.id.Main_Linear_Search);

        Imageview_search = (ImageView) findViewById(R.id.Main_Imageview_Search);
        Imageview_menu = (ImageView) findViewById(R.id.Main_Imageview_Menu);


    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void Changepage(int page) {



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

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("listhome", list_home);
                bundle.putInt("NumDaste", daste);
                Log.i("Go sherkat",id_sherkat);
                if (!id_sherkat.equals("")) {
                    Log.i("Go sherkat","send Bundel"+id_sherkat);

                    bundle.putString("ID", id_sherkat);
                }
                fragment = new Safheasli();
                fragment.setArguments(bundle);

                break;
        }
        GetManager().OpenView(fragment, tag, false);

    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


