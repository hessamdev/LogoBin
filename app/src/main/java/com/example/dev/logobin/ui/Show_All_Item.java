package com.example.dev.logobin.ui;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handler.Main_Adapter;
import com.example.dev.logobin.model.Model_All;

import java.util.ArrayList;

public class Show_All_Item extends FragmentView {

    private ArrayList<Model_All> setlist;

    private ArrayList<Model_All> Kala;

    @Override
    public void OnCreate() {
        View view=View.inflate(Activity,R.layout.show_all_item,null);
        String tag = Tag;
        View_item(tag);
        RecyclerView recycler_All = (RecyclerView) view.findViewById(R.id.All_Recyclerview);

        Utility utility =new Utility();
        recycler_All.setHasFixedSize(true);
        int n=utility.calculateNoOfColumns(Activity);
        recycler_All.setLayoutManager(new GridLayoutManager(Activity,n, LinearLayoutManager.VERTICAL,false));
        Main_Adapter adapter_All = new Main_Adapter(setlist, new Main_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Model_All model) {

            }
        });
        recycler_All.setAdapter(adapter_All);

        ViewMain = view;

    }

        public void View_item(String page){
        switch (page){
            case ("ALL_Brand"):
                ListBrand_DataBase();
                break;
            case ("ALL_Sherkat"):
             ListSherkat_DataBase();
                break;
            case ("All_Kala"):
                ListKala_DataBase();
                break;
                default: Toast.makeText(Activity, "nashod !!!", Toast.LENGTH_SHORT).show(); }
        }

        public void ListBrand_DataBase(){
            ArrayList<Model_All> Brand = new ArrayList<>();
            Brand.add(new Model_All("Brand_G",R.drawable.b_behrooz,"بهروز","2.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_domino,"دومینو","3.7"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_chitoz,"چیتوز","4.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_gooshtiran,"گوشتیران","3.9"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_haraz,"هراز","3.7"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_damdaran,"دامداران","4.1"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_choopan,"بهروز","2.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_behrooz,"بهروز","2.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_domino,"دومینو","3.7"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_chitoz,"چیتوز","4.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_gooshtiran,"گوشتیران","3.9"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_haraz,"هراز","3.7"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_damdaran,"دامداران","4.1"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_choopan,"بهروز","2.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_behrooz,"بهروز","2.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_domino,"دومینو","3.7"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_chitoz,"چیتوز","4.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_gooshtiran,"گوشتیران","3.9"));
            Brand.add(new Model_All("Brand_R",R.drawable.b_haraz,"هراز","3.7"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_damdaran,"دامداران","4.1"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_choopan,"بهروز","2.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_behrooz,"بهروز","2.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_domino,"دومینو","3.7"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_chitoz,"چیتوز","4.5"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_gooshtiran,"گوشتیران","3.9"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_haraz,"هراز","3.7"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_damdaran,"دامداران","4.1"));
            Brand.add(new Model_All("Brand_G",R.drawable.b_choopan,"بهروز","2.5"));

            setlist=new ArrayList<>(Brand);
        }
        public void ListSherkat_DataBase(){
            ArrayList<Model_All> Sherkat=new ArrayList<>();

            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_alborz,"البرز","2.5"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_banian,"بانیان","3"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_karen,"کارن","4.7"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_mazrae,"مزرعه","3.9"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_pardis,"پردیس","4"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_ronak,"روناک","4.1"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_alborz,"البرز","2.5"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_banian,"بانیان","3"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_karen,"کارن","4.7"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_mazrae,"مزرعه","3.9"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_pardis,"پردیس","4"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_ronak,"روناک","4.1"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_alborz,"البرز","2.5"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_banian,"بانیان","3"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_karen,"کارن","4.7"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_mazrae,"مزرعه","3.9"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_pardis,"پردیس","4"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_ronak,"روناک","4.1"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_alborz,"البرز","2.5"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_banian,"بانیان","3"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_karen,"کارن","4.7"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_mazrae,"مزرعه","3.9"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_pardis,"پردیس","4"));
            Sherkat.add(new Model_All("Brand_G",R.drawable.sh_ronak,"روناک","4.1"));
            setlist=new ArrayList<>(Sherkat);

        }
        public void ListKala_DataBase(){
            Kala=new ArrayList<>();
            Kala.add(new Model_All("Brand_G",R.drawable.kala1,"پنیر کاله","3.4"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala2,"ماست دامداران","4.5"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala3,"ماست دامداران","2.9"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala4,"ماست دامداران","3.7"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala5,"شیر دامداران","4.1"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala6,"شیر دامداران","5"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala7,"پنیر خامه ای دامداران","4.8"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala1,"پنیر کاله","3.4"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala2,"ماست دامداران","4.5"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala3,"ماست دامداران","2.9"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala4,"ماست دامداران","3.7"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala5,"شیر دامداران","4.1"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala6,"شیر دامداران","5"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala7,"پنیر خامه ای دامداران","4.8"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala1,"پنیر کاله","3.4"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala2,"ماست دامداران","4.5"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala3,"ماست دامداران","2.9"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala4,"ماست دامداران","3.7"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala5,"شیر دامداران","4.1"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala6,"شیر دامداران","5"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala7,"پنیر خامه ای دامداران","4.8"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala1,"پنیر کاله","3.4"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala2,"ماست دامداران","4.5"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala3,"ماست دامداران","2.9"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala4,"ماست دامداران","3.7"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala5,"شیر دامداران","4.1"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala6,"شیر دامداران","5"));
            Kala.add(new Model_All("Brand_G",R.drawable.kala7,"پنیر خامه ای دامداران","4.8"));
            setlist=new ArrayList<>(Kala);

        }

        public class Utility {
        public  int calculateNoOfColumns(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            return (int) (dpWidth / 110);
        }
    }


}



