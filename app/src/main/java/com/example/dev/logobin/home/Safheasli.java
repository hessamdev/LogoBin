package com.example.dev.logobin.home;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.widget.Toast;


import com.example.dev.logobin.Network.GetHome;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.handel.DB_Sherkat;
import com.example.dev.logobin.handel.Main_Adapter;
import com.example.dev.logobin.handel.Navbotton;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_Home;
import com.example.dev.logobin.model.M_Sherkat;
import com.example.dev.logobin.model.Model_All;
import com.example.dev.logobin.model.Model_ImageSlider;
import com.example.dev.logobin.model.Model_RecyclerHorizemtal;
import com.example.dev.logobin.ui.Sherkat_View;

import java.util.ArrayList;

public class Safheasli extends FragmentView {


    private ArrayList<M_Home> list_home;

    private RecyclerView recyclerView_Vertical;
    private ArrayList<Model_RecyclerHorizemtal> list_Vertical;

    private int dastebandi;
    private boolean swp = false;

    private SwipeRefreshLayout refreshLayout;


    private Main_Adapter.Adaprer_RecyclerVertical adapterVertical;

    private Navbotton navbotton;

    private String id_sherkat;

    @Override
    public void OnCreate() {

        Log.i("Life","OnCreate");
        View view=View.inflate(Activity, R.layout.home_safheasli_ui,null);
//        init(view);
        recyclerView_Vertical=(RecyclerView) view.findViewById(R.id.safheasli_Recyclerview_Asli);
        refreshLayout=(SwipeRefreshLayout) view.findViewById(R.id.swip_refresh_home);

        navbotton=new Navbotton(Activity);

        list_home =new ArrayList<>();
        list_Vertical=new ArrayList<>();

        Bundle extra = getArguments();
        list_home= extra.getParcelableArrayList("listhome");
        dastebandi=extra.getInt("NumDaste")+1;

        if (extra.getString("ID") != null){
            id_sherkat=extra.getString("ID");
        }



        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Activity,LinearLayoutManager.VERTICAL,false);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView_Vertical.setLayoutManager(layoutManager);
        adapterVertical=new Main_Adapter.Adaprer_RecyclerVertical(list_Vertical,Activity,Activity);
        recyclerView_Vertical.setHasFixedSize(true);
        recyclerView_Vertical.setNestedScrollingEnabled(false);

        getlistpars(list_home,dastebandi);

        recyclerView_Vertical.setAdapter(adapterVertical);












        refreshLayout.setColorSchemeColors(
               Activity.getResources().getColor(R.color.c500),
               Activity.getResources().getColor(R.color.c400),
               Activity.getResources().getColor(R.color.c300),
               Activity.getResources().getColor(R.color.c600)
        );
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                GetHome getHome=new GetHome(Activity);
                User_Data user_data=new User_Data(Activity);
                getHome.getHome(user_data.getMahale(), new GetHome.Back_Brand() {
                    @Override
                    public void ok(ArrayList<M_Home> brands, int NnbDaste) {
//                        list_home.clear();
                        list_Vertical.clear();
//                        adapterVertical.notifyDataSetChanged();
//                        list_home.addAll(brands);
                        swp=true;
                        getlistpars(brands,NnbDaste+1);




                        refreshLayout.setRefreshing(false);

                    }

                    @Override
                    public void faild(String Error) {
                        Toast.makeText(Activity, "اتصال به سرور ممکن نیست...", Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);

                    }
                });

            }
        });





        ViewMain=view;





    }

    private void getlistpars(ArrayList<M_Home> list, int daste){


        if (list != null) {
            int i;
//            int find=extra.getInt("NumDaste");
            for (i = 1 ; i<daste ; i++) {
                ArrayList<M_Home> addList;
                addList=new ArrayList<>();
                int end_b=0;
                String Titr_b="";
                for (M_Home brand : list) {
                    end_b++;
                    if (brand.getViewv() != null && brand.getViewv().contains("brand") && brand.getPartId().contains(""+i)) {
                        Log.i("Brand Name  "+i, brand.getTitle());
                        Titr_b=brand.getPartTitle();
                        addList.add(new M_Home(brand.getViewv(),
                                brand.getPartId(),
                                brand.getPartTitle(),
                                brand.getId(),
                                brand.getTitle(),
                                brand.getImage(),
                                brand.getRate(),
                                brand.getState(),
                                brand.getType()));
                        Log.i("Tab",brand.getTitle()+" // "+brand.getState());
                    }
                    if (end_b == list.size()){
                        end_b=0;
                        Log.i("sizelist brand",i+" = "+addList.size());
                        list_Vertical.add(new Model_RecyclerHorizemtal("Home","برند های محصولات "+Titr_b,addList));
                    }

                }

                addList=new ArrayList<>();
                int end_sh=0;
                String Titr_sh="";
                for (M_Home sherkat : list){
                    end_sh++;
                    if (sherkat.getViewv() != null && sherkat.getViewv().contains("company") && sherkat.getPartId().contains(""+i)){
                        Log.i("sherkat Name  "+i, sherkat.getTitle());
                        Titr_sh=sherkat.getPartTitle();
                        addList.add(new M_Home(sherkat.getViewv(),
                                sherkat.getPartId(),
                                sherkat.getPartTitle(),
                                sherkat.getId(),
                                sherkat.getTitle(),
                                sherkat.getImage(),
                                sherkat.getRate(),
                                sherkat.getState(),
                                sherkat.getType()));
                    }
                    if (end_sh == list.size() && addList.size() != 0 ){
                        end_sh=0;
                        Log.i("sizelist sherkat",i+" = "+addList.size());
                        list_Vertical.add(new Model_RecyclerHorizemtal("Home","تامین کنندگان محصولات "+Titr_sh,addList));
                    }
                    if (end_sh == list.size() && addList.size() == 0){
                        end_sh=0;
                        Log.i("sizelist sherkat",i+" = "+addList.size());
                        addList.add(new M_Home("company",
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null));
                        list_Vertical.add(new Model_RecyclerHorizemtal("Home","تامین کنندگان محصولات "+Titr_b,addList));
                    }
                }

            }

//            Toast.makeText(Activity, ""+list.size(), Toast.LENGTH_SHORT).show();
            if (swp){
                adapterVertical=new Main_Adapter.Adaprer_RecyclerVertical(list_Vertical,Activity,Activity);
                recyclerView_Vertical.setAdapter(adapterVertical);
                adapterVertical.notifyDataSetChanged();
            }

            setUp_Recycler_Vertical();
        }
    }


    private void setUp_Recycler_Vertical (){

//        adapterVertical.swap(list_Vertical);
//        recyclerView_Vertical.getAdapter().notifyDataSetChanged();
        adapterVertical.notifyDataSetChanged();
//        refreshLayout.setRefreshing(false);

    }


    @Override
    public void OnOpen() {
        Log.i("Life","Open");
        super.OnOpen();
    }

    @Override
    public void OnResume() {
//        setUp_Recycler_Vertical();
        Log.i("Life","OnResume");
        navbotton.settview(Tag);


        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                Log.i("Go sherkat","get Bundel"+id_sherkat);
                if (id_sherkat != null){
                    Log.i("Go sherkat","send Bundel"+id_sherkat);
                    Bundle bundle = new Bundle();
                    bundle.putString("ID", id_sherkat);
                    Sherkat_View sherkat_view = new Sherkat_View();
                    sherkat_view.setArguments(bundle);
                    Activity.GetManager().OpenView(sherkat_view, "Sherkat_View", true);
                    id_sherkat=null;               }

            }
        };

        handler.postDelayed(r, 1000);




        super.OnResume();
    }

    @Override
    public void OnPause() {
        super.OnPause();
        Log.i("Life","OnPause");


    }
}


