package com.example.dev.logobin.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.Network.GetBrand;
import com.example.dev.logobin.Network.GetKala;
import com.example.dev.logobin.Network.GetSherkat;
import com.example.dev.logobin.R;
import com.example.dev.logobin.RecyclerApp.RecyclerKala;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.DB_BookMark;
import com.example.dev.logobin.handel.Main_Adapter;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.model.M_BookMark;
import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_Home;
import com.example.dev.logobin.model.M_Kala;
import com.example.dev.logobin.model.Model_All;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Show_All_Item extends FragmentView {

    private RecyclerView recycler_All;

    private ArrayList<M_Home> setlist;

    private TextView title;

    private ImageView im_search ,back;
    private EditText et_search;


    @Override
    public void OnCreate() {
        View view=View.inflate(Activity,R.layout.show_all_item,null);
        recycler_All = (RecyclerView) view.findViewById(R.id.All_Recyclerview);
        back=(ImageView) view.findViewById(R.id.ShowAll_Imageview_Back);
        im_search=(ImageView)view.findViewById(R.id.ShowAll_Imageview_search) ;
        et_search=(EditText) view.findViewById(R.id.ShowAll_EditText_Search) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
                showSoftwareKeyboard(false);
            }
        });

        title=(TextView) view.findViewById(R.id.ShowAll_Textview_NamePage);

        im_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getVisibility() == View.VISIBLE) {
                    title.setVisibility(View.GONE);
                    et_search.setVisibility(View.VISIBLE);
                    et_search.requestFocus();
                    showSoftwareKeyboard(true);
//                    Picasso.get().load(R.drawable.cancel).resize(50,50).into(back);
                }else {

                    if (setlist.size()>0){
                        showSoftwareKeyboard(false);
                        ArrayList<M_Home> filter=new ArrayList<>();
                        for (M_Home f :setlist){
                            if (f.getTitle().contains(et_search.getText().toString())){
                                filter.add(f);
                            }
                        }
                        setRecycler_All(filter);
                    }

                }
            }
        });

        et_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            if (setlist.size()>0){
                                showSoftwareKeyboard(false);
                                ArrayList<M_Home> filter=new ArrayList<>();
                                for (M_Home f :setlist){
                                    if (f.getTitle().contains(et_search.getText().toString())){
                                        filter.add(f);
                                    }
                                }
                                setRecycler_All(filter);
                            }


                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


        View_item(Tag);



        ViewMain = view;

    }

    protected void showSoftwareKeyboard(boolean showKeyboard){

        final InputMethodManager inputManager = (InputMethodManager)Activity.getSystemService(Context.INPUT_METHOD_SERVICE);



        if (inputManager != null) {
            if (showKeyboard) {
                inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }else {
                View view = Activity.getCurrentFocus();
                //If no view currently has focus, create a new one, just so we can grab a window token from it
                if (view == null) {
                    view = new View(Activity);
                }
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
    private void setRecycler_All(ArrayList<M_Home> list){
        Utility utility =new Utility();
        int n=utility.calculateNoOfColumns(Activity);
        recycler_All.setLayoutManager(new GridLayoutManager(Activity,n));
        recycler_All.setHasFixedSize(true);
        recycler_All.setAdapter(new Main_Adapter(list, Activity, new Main_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(M_Home model) {

            }
        }));
    }

    @Override
    public void OnResume() {
        if (Tag.equals("BookMark")){
            Boockmark();
        }
        super.OnResume();
    }


    public void View_item(String page){
        switch (page){
            case ("Show"):
                ShowAll();
                break;
            case ("Kala"):
                Showkala();
                break;
            case ("BookMark"):
                Boockmark();

                break;
                default: Toast.makeText(Activity, "nashod !!!", Toast.LENGTH_SHORT).show(); }
        }

    private void Showkala() {
        GetKala getKala=new GetKala(Activity);
        final Bundle bundle=this.getArguments();
        if (bundle != null) {
            String Title= "دسته بندی : "+bundle.getString("Daste_Title");
            title.setText(Title);
            String Id = bundle.getString("Daste_Id");
            Toast.makeText(Activity, Id, Toast.LENGTH_SHORT).show();
            getKala.getKala_Dastebandi(Id, new GetKala.vcallback() {
                @Override
                public void onSuccess(ArrayList<M_Kala> list) {
                    if (list.size()==0){
                        Toast.makeText(Activity, " این دسته بندی خالی میباشد. "+list.size(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Activity," تعداد کالا در  "+bundle.getString("Daste_Title")+" = "+list.size(), Toast.LENGTH_SHORT).show();
                        recycler_All.setLayoutManager(new GridLayoutManager(Activity, 2));
                        recycler_All.setHasFixedSize(true);
                        recycler_All.setAdapter(new RecyclerKala(Activity, list, Activity, new RecyclerKala.OnItemClickListener() {
                            @Override
                            public void onItemClick(M_Kala model) {
                                Kala_View kala_view = new Kala_View();
                                User_Data user_data = new User_Data(Activity);

                                Bundle bundle = new Bundle();
                                bundle.putString("ID_kala", model.getId());
                                bundle.putString("ID_mahale", user_data.getMahale());
                                bundle.putString("ImageKala", model.getImage());
                                bundle.putString("NameKala", model.getTitle());
                                kala_view.setArguments(bundle);

                                Activity.GetManager().OpenView(kala_view, "Kala_View", true);
                            }
                        }));
                    }
                }

                @Override
                public void faild(String Eror) {
                    Toast.makeText(Activity, "" + Eror, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void ShowAll(){

            setlist=new ArrayList<>();

            Bundle bundle=this.getArguments();
            if (bundle != null){
                title.setText(bundle.getString("Title"));
//                setlist=bundle.<M_Home>getParcelableArrayList("List");
                String part=bundle.getString("Part");
                String viewv=bundle.getString("Viewv");
//                Toast.makeText(Activity, viewv, Toast.LENGTH_SHORT).show();

                assert viewv != null;
                switch (viewv) {
                    case "brand" :
                        GetBrand getBrand = new GetBrand(Activity);
                        getBrand.getBrand(part, new GetBrand.Back_Brand() {
                            @Override
                            public void ok(ArrayList<M_Home> brands) {
                                setlist=brands;
                                setRecycler_All(setlist);
//                                Toast.makeText(Activity, "add", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void faild(String Error) {
                                Toast.makeText(Activity, "eror Api brand", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case "company"  :
                        GetSherkat getSherkat=new GetSherkat(Activity);
                        getSherkat.GetSHerkat(part, new GetSherkat.Back_Sherkat() {
                            @Override
                            public void onSuccess(ArrayList<M_Home> list) {
                                setlist=list;
                                setRecycler_All(setlist);
//                                Toast.makeText(Activity, "add", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public Void onfailor(String Error) {
                                Toast.makeText(Activity, "Eror Api sherkat", Toast.LENGTH_SHORT).show();
                                return null;
                            }

                        });
                        break;

                    case "Sherkat_gheymat" :

                        setlist=bundle.getParcelableArrayList("List");
                        setRecycler_All(setlist);
//                        Toast.makeText(Activity, "Gheymat", Toast.LENGTH_SHORT).show();
                        break;

                }


            }else {

                Toast.makeText(Activity, "no", Toast.LENGTH_SHORT).show();
            }



        }

       private void Boockmark(){

        setlist=new ArrayList<>();
           DB_BookMark db_bookMark=new DB_BookMark(Activity);
           ArrayList<M_BookMark> book=new ArrayList<>(db_bookMark.getData());

           if (book.size()>0) {
               for (int i = 0; i < book.size(); i++) {
                   Toast.makeText(Activity, ""+book.get(i).getTitel(), Toast.LENGTH_SHORT).show();

                   setlist.add(new M_Home("company",
                           "1",
                           null,
                           book.get(i).getId(),
                           book.get(i).getTitel(),
                           book.get(i).getImage(),
                           book.get(i).getRate(),
                           "off",
                           "-"
                   ));
               }
           }
        Bundle bundle=this.getArguments();
        if (bundle != null){
            title.setText(bundle.getString("Title"));
            Toast.makeText(Activity, "add", Toast.LENGTH_SHORT).show();
        }else {

            Toast.makeText(Activity, "no", Toast.LENGTH_SHORT).show();
        }


       setRecycler_All(setlist);
    }

        public class Utility {
        public  int calculateNoOfColumns(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            return (int) (dpWidth / 110);
        }
    }


}



