package com.example.dev.logobin.home;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handler.Main_Adapter;
import com.example.dev.logobin.handler.ImageSlider;
import com.example.dev.logobin.model.Model_All;
import com.example.dev.logobin.model.Model_ImageSlider;
import com.example.dev.logobin.ui.Show_All_Item;

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
    //OnclickItem
    RelativeLayout brand,sherkat,kala;



    @Override
    public void OnCreate() {

        View view=View.inflate(Activity, R.layout.safheasli_ui,null);
        init(view);

        getlistDATABASE();
        setUp_Imageslider();
        setUp_Recyclerview_Brand();
        setUp_Recyclerview_Sherkat();
        setUp_Recyclerview_Kala();

        ViewMain=view;

    }
    private void getlistDATABASE(){
        listimage =new ArrayList<>();

        listimage.add(new Model_ImageSlider(R.drawable.baner1,"GotoBaner1"));
        listimage.add(new Model_ImageSlider(R.drawable.baner2,"GotoBaner2"));
        listimage.add(new Model_ImageSlider(R.drawable.baner1,"GotoBaner1"));
        listimage.add(new Model_ImageSlider(R.drawable.baner3,"GotoBaner3"));

        list_Brand=new ArrayList<>();

        list_Brand.add(new Model_All("Brand_R",R.drawable.b_domino,"دومینو","3.7"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_haraz,"هراز","4.7"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_gooshtiran,"گوشتیران","3.9"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_chitoz,"چیتوز","4.5"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_behrooz,"بهروز","3.7"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_damdaran,"دامداران","4.1"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_choopan,"چوپان","2.5"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_domino,"دومینو","3.7"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_haraz,"هراز","4.7"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_gooshtiran,"گوشتیران","3.9"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_chitoz,"چیتوز","4.5"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_behrooz,"بهروز","3.7"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_damdaran,"دامداران","4.1"));
        list_Brand.add(new Model_All("Brand_R",R.drawable.b_choopan,"چوپان","2.5"));

        list_Sherkat=new ArrayList<>();
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_alborz,"البرز","2.5"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_banian,"بانیان","3"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_karen,"کارن","4.7"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_mazrae,"مزرعه","3.9"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_pardis,"پردیس","4"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_ronak,"روناک","4.1"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_alborz,"البرز","2.5"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_banian,"بانیان","3"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_karen,"کارن","4.7"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_mazrae,"مزرعه","3.9"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_pardis,"پردیس","4"));
        list_Sherkat.add(new Model_All("Brand_R",R.drawable.sh_ronak,"روناک","4.1"));

        list_Kala=new ArrayList<>();

        list_Kala.add(new Model_All("Brand_R",R.drawable.kala1,"پنیر کاله","3.4"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala2,"ماست دامداران","4.5"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala3,"ماست دامداران","2.9"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala4,"ماست دامداران","3.7"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala5,"شیر دامداران","4.1"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala6,"شیر دامداران","5"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala7,"پنیر خامه ای دامداران","4.8"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala1,"پنیر کاله","3.4"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala2,"ماست دامداران","4.5"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala3,"ماست دامداران","2.9"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala4,"ماست دامداران","3.7"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala5,"شیر دامداران","4.1"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala6,"شیر دامداران","5"));
        list_Kala.add(new Model_All("Brand_R",R.drawable.kala7,"پنیر خامه ای دامداران","4.8"));


    }

    private void init(View view){
        slidertop=(ViewPager)view.findViewById(R.id.Safheasli_Viewpager_SliderTop);
        recyclerView_Brand=(RecyclerView) view.findViewById(R.id.Safheasli_Recyclerview_Brand);
        recyclerView_Sherkat=(RecyclerView) view.findViewById(R.id.Safheasli_Recyclerview_Sherkat);
        recyclerView_Kala=(RecyclerView) view.findViewById(R.id.Safheasli_Recyclerview_Kala);
        brand=(RelativeLayout)view.findViewById(R.id.Safheasli_Relative_Brand);
        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.GetManager().OpenView(new Show_All_Item(),"ALL_Brand",true);
            }
        });
        sherkat=(RelativeLayout)view.findViewById(R.id.Safheasli_Relative_Sherkat);
        sherkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.GetManager().OpenView(new Show_All_Item(),"ALL_Sherkat",true);
            }
        });
        kala=(RelativeLayout)view.findViewById(R.id.Safheasli_Relative_Kala);
        kala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.GetManager().OpenView(new Show_All_Item(),"All_Kala",true);
            }
        });

    }

    private void setUp_Recyclerview_Brand(){
        Main_Adapter adapter_Brand=new Main_Adapter(list_Brand,new Main_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Model_All model) {
                Toast.makeText(Activity, ""+model.getName(), Toast.LENGTH_SHORT).show();
            }
        });
       // listBrand_DataBase();

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Activity,LinearLayoutManager.HORIZONTAL,true);
        recyclerView_Brand.setHasFixedSize(true);
        recyclerView_Brand.setLayoutManager(layoutManager);
        recyclerView_Brand.setAdapter(adapter_Brand);



    }

    private void setUp_Recyclerview_Sherkat(){
        Main_Adapter adapter_Sherkat=new Main_Adapter(list_Sherkat,new Main_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Model_All model) {
                Toast.makeText(Activity, ""+model.getName(), Toast.LENGTH_SHORT).show();
            }

        });
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Activity,LinearLayoutManager.HORIZONTAL,true);
        recyclerView_Sherkat.setHasFixedSize(true);
        recyclerView_Sherkat.setLayoutManager(layoutManager);
        recyclerView_Sherkat.setAdapter(adapter_Sherkat);

    }

    private void setUp_Recyclerview_Kala(){
        Main_Adapter adapter_Kala=new Main_Adapter(list_Kala ,new Main_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Model_All model) {
                Toast.makeText(Activity, ""+model.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Activity,LinearLayoutManager.HORIZONTAL,true);
        recyclerView_Kala.setHasFixedSize(true);
        recyclerView_Kala.setLayoutManager(layoutManager);
        recyclerView_Kala.setAdapter(adapter_Kala);

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


