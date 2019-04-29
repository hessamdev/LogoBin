package com.example.dev.logobin.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dev.logobin.Network.GetDastebandi;
import com.example.dev.logobin.Network.PostSearch;
import com.example.dev.logobin.R;
import com.example.dev.logobin.RecyclerApp.RecyclerKala;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Adapter_Daste_Brand;
import com.example.dev.logobin.handel.Main_Adapter;
import com.example.dev.logobin.handel.Navbotton;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.home.DasteUI;
import com.example.dev.logobin.model.M_Home;
import com.example.dev.logobin.model.M_Kala;
import com.example.dev.logobin.model.M_ZirDaste;
import com.example.dev.logobin.model.Model_RecyclerHorizemtal;

import java.util.ArrayList;
import java.util.List;

public class Search_View extends FragmentView {

    private EditText search;
    private ImageView mic;
    private RecyclerView recyclerView_kala,recyclerView_brand;
    private Navbotton navbotton ;





    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    public void OnCreate() {
        View view= LayoutInflater.from(Activity).inflate(R.layout.home_search_ui2,null);

        navbotton=new Navbotton(Activity);

        search=(EditText) view.findViewById(R.id.Search_EditText_search2);
        search.requestFocus();
        mic=(ImageView)view.findViewById(R.id.Search_Imageview_Mic) ;
        recyclerView_kala=(RecyclerView) view.findViewById(R.id.search_recycler_Kala);
        recyclerView_brand=(RecyclerView) view.findViewById(R.id.search_recycler_Brand);






        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            showSoftwareKeyboard(false);
                            Searchresult();

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });



        ImageView Image_dastebandi=(ImageView) view.findViewById(R.id.search_imageview_dastebandi);

        Image_dastebandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.GetManager().OpenView(new DasteUI(),"Dastebandi",false);
            }
        });





        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });


        showSoftwareKeyboard(true);
        ViewMain=view;

    }

    private void Searchresult(){
        PostSearch postSearch=new PostSearch(Activity);
        postSearch.Search(search.getText().toString(), new PostSearch.response_search() {
            @Override
            public void result(ArrayList<M_Kala> m_kala, ArrayList<M_Home> m_brand) {
                Toast.makeText(Activity, "تعداد کالا برگشتی"+m_kala.size(), Toast.LENGTH_SHORT).show();
                RecyclerKala Adapter_kala=new RecyclerKala(Activity, m_kala, Activity, new RecyclerKala.OnItemClickListener() {
                    @Override
                    public void onItemClick(M_Kala model) {

                        Kala_View kala_view=new Kala_View();
                        User_Data user_data=new User_Data(Activity);

                        Bundle bundle =new Bundle();
                        bundle.putString("ID_kala",model.getId());
                        bundle.putString("ID_mahale",user_data.getMahale());
                        bundle.putString("ImageKala",model.getImage());
                        bundle.putString("NameKala",model.getTitle());
                        kala_view.setArguments(bundle);

                        Activity.GetManager().OpenView(kala_view,"Kala_View",true);
                    }
                });

                recyclerView_kala.setLayoutManager(new GridLayoutManager(Activity,2,LinearLayoutManager.VERTICAL,false));
                recyclerView_kala.setHasFixedSize(true);
                recyclerView_kala.setNestedScrollingEnabled(true);
                recyclerView_kala.setAdapter(Adapter_kala);

                if (m_brand.size()>0){
                    ArrayList<Model_RecyclerHorizemtal> listbrand=new ArrayList<>();
                    recyclerView_brand.setVisibility(View.VISIBLE);

                    listbrand.add(new Model_RecyclerHorizemtal("Home","لیست برند ها",m_brand));

                    recyclerView_brand.setLayoutManager(new LinearLayoutManager(Activity,LinearLayoutManager.VERTICAL,false));
                    recyclerView_brand.setHasFixedSize(true);
                    recyclerView_brand.setNestedScrollingEnabled(false);
                    recyclerView_brand.setAdapter(new Main_Adapter.Adaprer_RecyclerVertical(listbrand,Activity,Activity));
                }else {
                    recyclerView_brand.setVisibility(View.GONE);
                }

            }

            @Override
            public void Faild(String error) {

            }
            });

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


    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fa_IR");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
               Activity.getResources().getString(R.string.speech_prompt));
        try {
           Activity.startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(Activity,
                    "این امکان برای گوشی شما فعال نمی باشد.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */



    @Override
    public void OnActivityResult(int RequestCode, int ResultCode, Intent intent) {
        super.OnActivityResult(RequestCode, ResultCode, intent);

        Log.i("Speech_RequestCode",RequestCode+"");
        Log.i("Speech_ResultCode",ResultCode+"");
        Log.i("Speech_intent",intent+"");



        switch (RequestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (ResultCode == -1 && null != intent) {

                    ArrayList<String> result = intent
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    search.setText(result.get(0));
                    Searchresult();
                    Toast.makeText(Activity, ""+intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS), Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }




    @Override
    public void OnOpen() {
        super.OnOpen();

    }

    @Override
    public void OnResume() {
        super.OnResume();
        navbotton.settview(Tag);
    }

    @Override
    public void OnPause() {
        super.OnPause();
        showSoftwareKeyboard(false);
        
    }
}
