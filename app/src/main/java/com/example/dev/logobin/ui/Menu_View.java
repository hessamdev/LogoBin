package com.example.dev.logobin.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;

public class Menu_View extends FragmentView implements View.OnClickListener {

    private RelativeLayout register,bookmark,ghavanin,enteghad,poshtibani,pishnahaddostan,noskhe,logout;

    @Override
    public void OnCreate() {
        View view= LayoutInflater.from(Activity).inflate(R.layout.home_menu_ui,null);
        register=(RelativeLayout)view.findViewById(R.id.menu_Relativ_register_page);
        bookmark=(RelativeLayout)view.findViewById(R.id.menu_Relativ_bookmark);
        ghavanin=(RelativeLayout)view.findViewById(R.id.menu_Relativ_ghavanin);
        enteghad=(RelativeLayout)view.findViewById(R.id.menu_Relativ_enteghad);
        poshtibani=(RelativeLayout)view.findViewById(R.id.menu_Relativ_poshtibani);
        pishnahaddostan=(RelativeLayout)view.findViewById(R.id.menu_Relativ_pishnahaddostan);
        noskhe=(RelativeLayout)view.findViewById(R.id.menu_Relativ_noskhe);
        logout=(RelativeLayout)view.findViewById(R.id.menu_Relativ_logout);

        register.setOnClickListener(this);
        bookmark.setOnClickListener(this);
        ghavanin.setOnClickListener(this);
        enteghad.setOnClickListener(this);
        poshtibani.setOnClickListener(this);
        pishnahaddostan.setOnClickListener(this);
        noskhe.setOnClickListener(this);
        logout.setOnClickListener(this);

        ViewMain=view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_Relativ_register_page:
                Activity.GetManager().OpenView(new Menu_Register_view(),"Virayesh",true);
                break;
            case R.id.menu_Relativ_bookmark:
                Activity.GetManager().OpenView(new Show_All_Item(),"Bookmark",true);
                Toast.makeText(Activity, "NEED DATA-BASE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_Relativ_ghavanin:
                Activity.GetManager().OpenView(new Menu_Ghavanin_View(),"Menu_Ghavanin_View",true);
                Toast.makeText(Activity, "NEED TEXT", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_Relativ_enteghad:
                Activity.GetManager().OpenView(new Menu_Enteghad_View(),"enteghad",true);
                Toast.makeText(Activity, "NEED UI&UX", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_Relativ_poshtibani:
                Activity.GetManager().OpenView(new Menu_Poshtibani_View(),"poshtibani",true);
                Toast.makeText(Activity, "NEED UI&UX", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_Relativ_pishnahaddostan:
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                String name="name";
                String appname="appname";
                sendIntent.putExtra(Intent.EXTRA_SUBJECT,appname);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                Activity.startActivity(Intent.createChooser(sendIntent,"Share App"));
                break;
            case R.id.menu_Relativ_noskhe:
                Toast.makeText(Activity, "NEED SnackBar View Version", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_Relativ_logout:
                Activity.finish();
                break;

        }
    }
}
