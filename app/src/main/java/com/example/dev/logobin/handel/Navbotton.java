package com.example.dev.logobin.handel;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.dev.logobin.R;
import com.squareup.picasso.Picasso;

public class Navbotton {

    private Context context;
    ImageView Imageview_Darkhastha;
    ImageView Imageview_Dasteha;
    ImageView Imageview_Safheasli;
    ImageView Imageview_search;
    ImageView Imageview_menu;


    public Navbotton(Context context) {
        this.context = context;
    }


    public void settview(String Tag){
        Imageview_Darkhastha = (ImageView)((Activity)context).findViewById(R.id.Main_Imageview_Darkhastha);
        Imageview_Safheasli = (ImageView)((Activity)context).findViewById(R.id.Main_Imageview_Safheasli);
        Imageview_search = (ImageView) ((Activity)context).findViewById(R.id.Main_Imageview_Search);
        Imageview_menu = (ImageView) ((Activity)context).findViewById(R.id.Main_Imageview_Menu);

        Picasso.get().load(R.drawable.call).resize(100, 100).into(Imageview_Darkhastha);
        Picasso.get().load(R.drawable.home2).resize(50, 50).into(Imageview_Safheasli);
        Picasso.get().load(R.drawable.search2).resize(100, 100).into(Imageview_search);
        Picasso.get().load(R.drawable.tanzimat).resize(100, 100).into(Imageview_menu);

        if (Tag != null) {
            switch (Tag) {
                case "Safheasli":
                    Imageview_Safheasli.setAlpha(255);
                    Imageview_Darkhastha.setAlpha(100);
                    Imageview_search.setAlpha(100);
                    Imageview_menu.setAlpha(100);
                    break;
                case "Darkhastha":
                    Imageview_Safheasli.setAlpha(100);
                    Imageview_Darkhastha.setAlpha(255);
                    Imageview_search.setAlpha(100);
                    Imageview_menu.setAlpha(100);
                    break;

                case "Search_Page":
                    Imageview_Safheasli.setAlpha(100);
                    Imageview_Darkhastha.setAlpha(100);
                    Imageview_search.setAlpha(255);
                    Imageview_menu.setAlpha(100);
                    break;

                case "Menu_Page":
                    Imageview_Safheasli.setAlpha(100);
                    Imageview_Darkhastha.setAlpha(100);
                    Imageview_search.setAlpha(100);
                    Imageview_menu.setAlpha(255);
                    break;
            }
        }

    }

    public void changeview(String tag){



    }

}
