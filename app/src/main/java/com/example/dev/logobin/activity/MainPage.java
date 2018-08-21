package com.example.dev.logobin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.home.DarkhastUI;
import com.example.dev.logobin.home.DasteUI;
import com.example.dev.logobin.home.Safheasli;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainPage extends FragmentActivity {

    @BindView(R.id.Main_Linear_Darkhastha)
    LinearLayout click_Darkhast;
    @BindView(R.id.Main_Imageview_Darkhastha)
    ImageView Imageview_Darkhastha;
    @BindView(R.id.Main_Linear_Dasteha)
    LinearLayout click_Dasteha;
    @BindView(R.id.Main_Imageview_Dasteha)
    ImageView Imageview_Dasteha;
    @BindView(R.id.Main_Linear_Safheasli)
    LinearLayout click_Safheasli;
    @BindView(R.id.Main_Imageview_Safheasli)
    ImageView Imageview_Safheasli;
    @BindView(R.id.Main_Imageview_Search)
    ImageView search;
    @BindView(R.id.Main_Imageview_Menu)
    ImageView menu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_activity);
        ButterKnife.bind(this);


        click_Safheasli.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Changepage(3); }});
        click_Dasteha.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Changepage(2); }});
        click_Darkhast.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Changepage(1); }});

        Picasso.get().load(R.drawable.search).resize(100,100).into(search);
        Picasso.get().load(R.drawable.menu).resize(100,100).into(menu);



        Changepage(3);

    }

    public void Changepage(int page){

        Picasso.get().load(R.drawable.call).resize(100,100).into(Imageview_Darkhastha);
        Picasso.get().load(R.drawable.categury).resize(50,50).into(Imageview_Dasteha);
        Picasso.get().load(R.drawable.home).resize(50,50).into(Imageview_Safheasli);
        click_Darkhast.setBackgroundColor(getResources().getColor(R.color.c100));
        click_Dasteha.setBackgroundColor(getResources().getColor(R.color.c100));
        click_Safheasli.setBackgroundColor(getResources().getColor(R.color.c100));

        String tag = null;
        FragmentView fragment = null;

        switch (page)
        {
            case 1:
                tag="Darkhastha";
                fragment=new DarkhastUI();

                break;
            case 2:
                tag="Dasteha";
                fragment=new DasteUI();

                break;
            case 3:
                tag="Safheasli";
                fragment=new Safheasli();

                break;
        }
            GetManager().OpenView(fragment,tag,false);

    }

}
