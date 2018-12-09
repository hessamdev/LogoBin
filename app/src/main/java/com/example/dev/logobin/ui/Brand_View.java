package com.example.dev.logobin.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dev.logobin.App;
import com.example.dev.logobin.Network.GetDastebandi;
import com.example.dev.logobin.Network.GetKala;
import com.example.dev.logobin.R;
import com.example.dev.logobin.RecyclerApp.RecyclerKala;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Adapter_Daste_Brand;
import com.example.dev.logobin.handel.DB_Kala;
import com.example.dev.logobin.handel.DB_ZirDaste;
import com.example.dev.logobin.model.M_Kala;
import com.example.dev.logobin.model.M_ZirDaste;
import com.example.dev.logobin.model.Model_All;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class Brand_View extends FragmentView {

    String Factory_Id,Url_ImageBrand,SRate;
    RecyclerView recyclerView_Kala;
    RecyclerKala Adapter;



    RecyclerView.LayoutManager layoutManager;

    DB_Kala db_kala;
    DB_ZirDaste db_zirDaste;
    ProgressDialog progressDialog;


    ArrayList<M_Kala> fulllistkala;
    Handler handler;

    RecyclerView recyclerViewDaste;
    Adapter_Daste_Brand Adapter_Daste;

    GetKala getKala;

    GetDastebandi getDastebandi;


    ArrayList<M_Kala> list_Kala;
    ArrayList<M_ZirDaste> list_Daste;

    FrameLayout rate;
    ImageView smallLogo;

    int GetNumData =0 , i =0 ,currentItems,totalItem,scrollOutItem ;


    @Override
    public void OnCreate() {
        View view = LayoutInflater.from(Activity).inflate(R.layout.fragment_brand2,null);
        Factory_Id=getArguments().getString("Factory_id");
        Url_ImageBrand=getArguments().getString("Url_Image");
        SRate=getArguments().getString("Rate");

//        db_kala= new DB_Kala(Activity);
//        db_zirDaste=new DB_ZirDaste(Activity);
//
//        fulllistkala=new ArrayList<>();
//        list_Daste= new ArrayList<>();
//
//        handler=new Handler();

        getDastebandi=new GetDastebandi(Activity);

        getKala=new GetKala(Activity);

        recyclerViewDaste=(RecyclerView)view.findViewById(R.id.Brand_Recycerview_Daste);
        recyclerViewDaste.setLayoutManager(new LinearLayoutManager(Activity,LinearLayoutManager.HORIZONTAL,true));
        recyclerViewDaste.setHasFixedSize(true);

        recyclerView_Kala = (RecyclerView) view.findViewById(R.id.Brand_Recyclerview);
        recyclerView_Kala.setNestedScrollingEnabled(true);
        layoutManager = new GridLayoutManager(Activity,2, LinearLayoutManager.VERTICAL,false);
        recyclerView_Kala.setLayoutManager(layoutManager);
        recyclerView_Kala.setHasFixedSize(true);



        getDastebandi.zirdastebrand(Factory_Id, new GetDastebandi.callback() {
            @Override
            public void onsuccess(ArrayList<M_ZirDaste> listss) {
                String one= listss.get(0).getId();
                getKala.getKala(Factory_Id, one, new GetKala.vcallback() {
                    @Override
                    public void onSuccess(ArrayList<M_Kala> list) {
                        Adapter=new RecyclerKala(Activity, list, new RecyclerKala.OnItemClickListener() {
                            @Override
                            public void onItemClick(M_Kala model) {
                                Toast.makeText(Activity, ""+model.getId(), Toast.LENGTH_SHORT).show();
                                Kala_View kala_view=new Kala_View();
                                Bundle bundle =new Bundle();
                                bundle.putString("ImageKala",model.getImage());
                                bundle.putString("NameKala",model.getTitle());
                                kala_view.setArguments(bundle);

                                Activity.GetManager().OpenView(kala_view,"Kala_View",true);
                            }
                        });
                        recyclerView_Kala.setAdapter(Adapter);
                        Adapter.notifyDataSetChanged();
                    }
                });
                Adapter_Daste=new Adapter_Daste_Brand(listss, new Adapter_Daste_Brand.OnItemClickListener() {
                    @Override
                    public void onItemClick(M_ZirDaste m_zirDaste) {

                        Toast.makeText(Activity, ""+m_zirDaste.getId(), Toast.LENGTH_SHORT).show();
                        getKala.getKala(Factory_Id,m_zirDaste.getId(), new GetKala.vcallback() {
                            @Override
                            public void onSuccess(ArrayList<M_Kala> list) {
                                Adapter=new RecyclerKala(Activity, list, new RecyclerKala.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(M_Kala model) {
                                        Toast.makeText(Activity, ""+model.getId(), Toast.LENGTH_SHORT).show();
                                        Kala_View kala_view=new Kala_View();
                                        Bundle bundle =new Bundle();
                                        bundle.putString("ImageKala",model.getImage());
                                        bundle.putString("NameKala",model.getTitle());
                                        kala_view.setArguments(bundle);

                                        Activity.GetManager().OpenView(kala_view,"Kala_View",true);
                                    }
                                });
                                recyclerView_Kala.setAdapter(Adapter);
                                Adapter.notifyDataSetChanged();

                            }
                        });

                    }
                });
                recyclerViewDaste.setAdapter(Adapter_Daste);


            }
        });



//        handler.postDelayed(runnable,0);


//        progressDialog = new ProgressDialog(Activity);
//        progressDialog.setMessage("Fetching The File....");
//        progressDialog.show();





//        list_Kala=new ArrayList<>();



//
//        recyclerView_Kala.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                totalItem=layoutManager.getItemCount();
//                currentItems=layoutManager.getChildCount();
//                scrollOutItem=((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
//                Log.i("Scroll == ","Total = "+totalItem+"=="+currentItems+"-"+scrollOutItem);
//
//                if (totalItem-5 <= currentItems+scrollOutItem ){
//                    Log.i("I= kala",GetNumData+"="+(db_kala.getData().size()-1));
//                    if (GetNumData >= db_kala.getData().size()-1) {
//                        Toast.makeText(Activity, "Payane List", Toast.LENGTH_SHORT).show();
//
//
//                     }else if (
////                             GetNumData < db_kala.getData().size()-1
//                            totalItem <=scrollOutItem+currentItems+20
//                            ){
////                        getkalalist(GetNumData);
//                        Log.i("Num data ? ",GetNumData+"-"+db_kala.getData().size());
//                        Log.i("i == db kala? ",i+"-"+db_kala.getData().size());
//                    }
//                }
//
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });






//        setUpList();

        CollapsingToolbarLayout colaps=view.findViewById(R.id.collapsing_toolbar);
        colaps.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                Log.i("top",""+top);
//                Log.i("bottom",""+bottom);
//                Log.i("oldBottom",""+oldBottom);
            }
        });
        smallLogo=(ImageView)view.findViewById(R.id.Brand_Imageview_smallLogo);
        Picasso.get().load(Url_ImageBrand).into(smallLogo);
        final AppBarLayout appbar=(AppBarLayout) view.findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offsetAlpha = (appBarLayout.getY() / appbar.getTotalScrollRange());
//                rate.setAlpha( 0 + (offsetAlpha * -1));
                smallLogo.setAlpha( 0 + (offsetAlpha * -1));
//                Log.i("ofset",""+offsetAlpha);
            }
        });

        Toolbar toolbar=(Toolbar)view.findViewById(R.id.z_toolbar);
        LinearLayout back=(LinearLayout) view.findViewById(R.id.Brand_Imageview_Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });


        Transformation transformation=new RoundedTransformationBuilder().cornerRadiusDp(20).build();
        ImageView heder = (ImageView) view.findViewById(R.id.Brand_Imageview_Heder);
        heder.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load(Url_ImageBrand).into(heder);
        rate=(FrameLayout) view.findViewById(R.id.Brand_Frame_Rate);
        View Rate=LayoutInflater.from(Activity).inflate(R.layout.z_n_rating,null);
        TextView numrate=(TextView)Rate.findViewById(R.id.z_n_Textview_rate) ;
        numrate.setText(SRate);
        rate.addView(Rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(Activity);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.z_m_alertdialog_rating);
                Button sabt = (Button)dialog.findViewById(R.id.AlertRate_Button_Sabt);
                final TextView rate=(TextView)dialog.findViewById(R.id.z_m_rating_textview) ;
                final String[] s = {null};
                final MaterialRatingBar materialRatingBar=(MaterialRatingBar) dialog.findViewById(R.id.z_m_rating_ratingbar);
                rate.setText(String.valueOf(materialRatingBar.getRating()));
                materialRatingBar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
                    @Override
                    public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                        rate.setText(String.valueOf(rating));
                    }
                });
                sabt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Activity, "امتیاز شما "+ String.valueOf(materialRatingBar.getRating()) +" ثبت شد.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });



//        list_Daste.add(new M_ZirDaste(null,"همه موارد",null));
//        Adapter_Daste=new Adapter_Daste_Brand(list_Daste, new Adapter_Daste_Brand.OnItemClickListener() {
//            @Override
//            public void onItemClick(M_ZirDaste m_zirDaste) {
//                Toast.makeText(Activity, ""+m_zirDaste.getId(), Toast.LENGTH_SHORT).show();
//                list_Kala.clear();
//                for (int i = 0 ; i<fulllistkala.size();i++){
//                    if (fulllistkala.get(i).getSublist_id().equals(m_zirDaste.getId())){
//                        list_Kala.add(new M_Kala(fulllistkala.get(i).getId(),
//                                fulllistkala.get(i).getTitle(),
//                                fulllistkala.get(i).getCprice(),
//                                fulllistkala.get(i).getCode(),
//                                fulllistkala.get(i).getDetails(),
//                                fulllistkala.get(i).getImage(),
//                                fulllistkala.get(i).getActive(),
//                                fulllistkala.get(i).getFactory_id(),
//                                fulllistkala.get(i).getList_id(),
//                                fulllistkala.get(i).getSublist_id()));
//
//                    }
//                }
//                Adapter.notifyDataSetChanged();
//
//            }
//        });





        ViewMain=view;
    }

//    private void getkalalist(int get){
//
//
//        for (int i = get ; i<fulllistkala.size();i++){
//            if (i == get + 50) {
//                i++;
//                GetNumData = i;
//                Toast.makeText(Activity, "" + GetNumData, Toast.LENGTH_SHORT).show();
//                i = fulllistkala.size();
//
//
//
//            } else {
//                list_Kala.add(new M_Kala(fulllistkala.get(i).getId(),
//                        fulllistkala.get(i).getTitle(),
//                        fulllistkala.get(i).getCprice(),
//                        fulllistkala.get(i).getCode(),
//                        fulllistkala.get(i).getDetails(),
//                        fulllistkala.get(i).getImage(),
//                        fulllistkala.get(i).getActive(),
//                        fulllistkala.get(i).getFactory_id(),
//                        fulllistkala.get(i).getList_id(),
//                        fulllistkala.get(i).getSublist_id()));
//                GetNumData = i;
//                Adapter.notifyDataSetChanged();
//            }
//        }
//
//        if (progressDialog.isShowing()){
//            progressDialog.dismiss();
//        }
//
//    }
//
//
//    public void setUpList(){
//
//        for (int i = 0 ; i<fulllistkala.size();i++){
//           ArrayList<M_ZirDaste> gettitel=new ArrayList<>(db_zirDaste.getname(fulllistkala.get(i).getSublist_id()));
//
//           adddaste(gettitel.get(0).getId(),
//                   gettitel.get(0).getTitle(),
//                   gettitel.get(0).getList_id());
//
//        }
//
//    }
//
//    private void adddaste(String id,String matn,String sublist){
//        int bood=0;
//        int naboood=0;
//        for (int i = 0 ; i < list_Daste.size();i++){
//
//
//            if (list_Daste.get(i).getTitle().equals(matn)){
//                bood++;
//            }else {
//                naboood++;
//            }
//        }
//        if (bood+naboood==naboood-bood) {
//            Log.i("matndaste hast",i+" Hast "+list_Daste.size()+"  bood= "+bood+"  nabood = "+naboood );
//            list_Daste.add(new M_ZirDaste(id,matn,sublist));
//            Adapter_Daste.notifyDataSetChanged();
//        }
//    }
//
//    int sizedbkala ;
//    public Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            Log.i("Run", sizedbkala+"test" + db_kala.getData().size());
//
//
//
////            if (sizedbkala != db_kala.getData().size()) {
//                sizedbkala=db_kala.getData().size();
//                handler.postDelayed(runnable,100);
//
//            }
//            else if ( db_kala.getData().size() == sizedbkala && sizedbkala != 0){
//
//                fulllistkala.addAll(db_kala.getData());
//                Log.i("Run", fulllistkala.size()+"true data ==" + db_kala.getData().size());
//                getkalalist(GetNumData);
//                recyclerView_Kala.setVisibility(View.VISIBLE);
//                recyclerView_Kala.setAdapter(Adapter);
//                setUpList();
//                handler.removeCallbacks(runnable);
//            }
//            else {
//                Log.i("Run","false");
//                handler.postDelayed(runnable,100);
//
//            }
//        }
//    } ;



    @Override
    public void OnResume() {
        super.OnResume();


    }

}

