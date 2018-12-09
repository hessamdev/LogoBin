package com.example.dev.logobin.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.handel.Main_Adapter;
import com.example.dev.logobin.handel.ImageSlider;
import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.Model_All;
import com.example.dev.logobin.model.Model_Brand;
import com.example.dev.logobin.model.Model_ImageSlider;
import com.example.dev.logobin.model.Model_RecyclerHorizemtal;

import java.util.ArrayList;

public class Safheasli extends FragmentView {


    //Imageslider
     private ViewPager slidertop;
     private ImageSlider adapter_Imageslider;
     private Runnable runnable;
     private int page;
     private ArrayList<Model_ImageSlider> listimage;
     //RecyclerBrand
    private ArrayList<Model_All> list_Brand;
    private RecyclerView recyclerView_Brand;
    //Recycler_Sherkat
    private ArrayList<Model_All> list_Sherkat;
    private RecyclerView recyclerView_Sherkat;
    //Recycler_Kala
    private ArrayList<Model_All> list_Kala;
    private RecyclerView recyclerView_Kala;
    //RecyclerAsli
    private RecyclerView recyclerView_Vertical;
    private ArrayList<Model_RecyclerHorizemtal> list_Vertical;
    //OnclickItem
    private ArrayList<Model_All> list_tablighat;


    private DB_Brand db_brand;
    private M_Brand m_brand;
    private Main_Adapter.Adaprer_RecyclerVertical adapterVertical;

    @Override
    public void OnCreate() {

        View view=View.inflate(Activity, R.layout.home_safheasli_ui,null);
        init(view);

        db_brand=new DB_Brand(Activity);
        ArrayList<M_Brand> databrand=new ArrayList<>(db_brand.getData());
        list_Brand=new ArrayList<>();
        for (int i = 0 ; databrand.size()>i ;i++){
            Log.i("titel",databrand.get(i).getTitle());
            list_Brand.add(new Model_All("Brand_R",
                    String.valueOf(databrand.get(i).getId()),
                    databrand.get(i).getImage(),
                    databrand.get(i).getTitle(),
                    databrand.get(i).getRate()
                    ));
        }




//        getdatafromserver();

        getlistDATABASE();
        setUp_Imageslider();
        setUp_Recycler_Vertical();

        ViewMain=view;

    }
    private void getlistDATABASE(){
        listimage =new ArrayList<>();

        listimage.add(new Model_ImageSlider(R.drawable.baner1,"GotoBaner1"));
        listimage.add(new Model_ImageSlider(R.drawable.baner2,"GotoBaner2"));
        listimage.add(new Model_ImageSlider(R.drawable.baner1,"GotoBaner1"));
        listimage.add(new Model_ImageSlider(R.drawable.baner3,"GotoBaner3"));

//        list_Brand=new ArrayList<>();



//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_domino,"دومینو","3.7"));
//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_haraz,"هراز","4.7"));
//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_chitoz,"چیتوز","4.5"));
//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_behrooz,"بهروز","3.7"));
//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_damdaran,"دامداران","4.1"));
//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_domino,"دومینو","3.7"));
//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_haraz,"هراز","4.7"));
//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_chitoz,"چیتوز","4.5"));
//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_behrooz,"بهروز","3.7"));
//        list_Brand.add(new Model_All("Brand_R",R.drawable.b_damdaran,"دامداران","4.1"));


        list_Sherkat=new ArrayList<>();
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_alborz,"البرز","2.5"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_banian,"بانیان","3"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_karen,"کارن","4.7"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_mazrae,"مزرعه","3.9"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_pardis,"پردیس","4"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_ronak,"روناک","4.1"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_alborz,"البرز","2.5"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_banian,"بانیان","3"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_karen,"کارن","4.7"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_mazrae,"مزرعه","3.9"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_pardis,"پردیس","4"));
        list_Sherkat.add(new Model_All("Sherkat_R",R.drawable.sh_ronak,"روناک","4.1"));

        list_Kala=new ArrayList<>();

//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala1,"پنیر کاله"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala2,"ماست دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala3,"ماست دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala4,"ماست دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala5,"شیر دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala6,"شیر دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala7,"پنیر خامه ای دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala1,"پنیر کاله"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala2,"ماست دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala3,"ماست دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala4,"ماست دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala5,"شیر دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala6,"شیر دامداران"));
//        list_Kala.add(new Model_All("Kala_G",R.drawable.kala7,"پنیر خامه ای دامداران"));

        list_tablighat=new ArrayList<>();
        list_tablighat.add(new Model_All("Recycler_Tabligh",R.drawable.baner4));

        list_Vertical=new ArrayList<>();
        list_Vertical.add(new Model_RecyclerHorizemtal("Home","برترین برند های غذایی",list_Brand));
        list_Vertical.add(new Model_RecyclerHorizemtal("Home","برترین تامین کنندگان",list_Sherkat));
        list_Vertical.add(new Model_RecyclerHorizemtal("Tabligh",list_tablighat));
        list_Vertical.add(new Model_RecyclerHorizemtal("Home","برترین برند های غذایی",list_Brand));
        list_Vertical.add(new Model_RecyclerHorizemtal("Home","برترین شرکت های پخش",list_Sherkat));
        list_Vertical.add(new Model_RecyclerHorizemtal("Tabligh",list_tablighat));



    }

    private void init(View view){
        slidertop=(ViewPager)view.findViewById(R.id.Safheasli_Viewpager_SliderTop);
        recyclerView_Vertical=(RecyclerView) view.findViewById(R.id.safheasli_Recyclerview_Asli);

    }

    private void setUp_Recycler_Vertical (){
        adapterVertical=new Main_Adapter.Adaprer_RecyclerVertical(list_Vertical,Activity,Activity);
        recyclerView_Vertical.setLayoutManager(new LinearLayoutManager(Activity,LinearLayoutManager.VERTICAL,false));
        recyclerView_Vertical.setNestedScrollingEnabled(false);
        recyclerView_Vertical.setAdapter(adapterVertical);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUp_Imageslider(){
        //Set_Adapter
        adapter_Imageslider=new ImageSlider(Activity,listimage);
        slidertop.setAdapter(adapter_Imageslider);
        slidertop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                page=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        slidertop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_MOVE:
                        slidertop.removeCallbacks(runnable);
                        break;
                }
                slidertop.postDelayed(runnable,2500);
                return false;
            }
        });

            //Change_Slide
            runnable =new Runnable() {
                @Override
                public void run() {
                    if (adapter_Imageslider.getCount()-1==page){
                        page=0;
                    }else {
                        page++;
                    }slidertop.setCurrentItem(page,true);
                    slidertop.postDelayed(this,2500);
                }
            };

    }

//    public void getdatafromserver(){
//
//        String Url_Kala="http://satrapp.ir/api/brand/10";
//        String Url_Brand="http://satrapp.ir/api/brands";
//        JsonObjectRequest js_kala = new JsonObjectRequest(Request.Method.GET, Url_Kala, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray Jsa_Kala=response.getJSONArray("data");
////                            Log.i("Brand : ",brand.toString());
//                            for (int i =0 ;i<Jsa_Kala.length();i++){
//                                JSONObject Kala=Jsa_Kala.getJSONObject(i);
//                                String id = Kala.getString("id");
//                                String title = Kala.getString("title");
//                                String cprice = Kala.getString("cprice");
//                                String code = Kala.getString("code");
//                                String details = Kala.getString("details");
//                                String image = Kala.getString("image");
//                                String active = Kala.getString("active");
//                                String factory_id = Kala.getString("factory_id");
//                                String list_id = Kala.getString("list_id");
//                                String sublist_id = Kala.getString("sublist_id");
//
//                            }
//
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        final JsonObjectRequest js_brand=new JsonObjectRequest(Request.Method.GET, Url_Brand, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray Jsa_Brand=response.getJSONArray("data");
//                            Log.i("Brand : ",Jsa_Brand.toString());
//
//                            for (int i =0 ;i<Jsa_Brand.length();i++){
//                                JSONObject Brand=Jsa_Brand.getJSONObject(i);
//                                String id = Brand.getString("id");
//                                String title = Brand.getString("title");
//                                String image = Brand.getString("image");
//                                String rate = Brand.getString("rate");
//                                String url_Image="http://satrapp.ir"+image;
//
//                                list_Brand.add(new Model_All("Brand_R",id,url_Image,title,rate));
//
//
//
//
//                            }
//
//
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        getdata.add(js_kala);
//        getdata.add(js_brand);
//
//    }

    @Override
    public void OnResume() {
        super.OnResume();
        slidertop.postDelayed(runnable,2000);
    }

    @Override
    public void OnPause() {
        super.OnPause();
        slidertop.removeCallbacks(runnable);
    }
}


