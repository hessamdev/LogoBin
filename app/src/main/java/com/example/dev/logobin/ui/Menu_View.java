package com.example.dev.logobin.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.DB_BookMark;
import com.example.dev.logobin.handel.Navbotton;
import com.example.dev.logobin.model.M_BookMark;
import com.example.dev.logobin.model.Model_All;

import java.util.ArrayList;

public class Menu_View extends FragmentView implements View.OnClickListener {

    private RelativeLayout register,bookmark,ghavanin,enteghad,poshtibani,pishnahaddostan,noskhe,logout;
    private ConstraintLayout constraintLayout;

    private Navbotton navbotton ;

    @Override
    public void OnCreate() {
        View view= LayoutInflater.from(Activity).inflate(R.layout.home_menu_ui,null);
        navbotton=new Navbotton(Activity);
        register=(RelativeLayout)view.findViewById(R.id.menu_Relativ_register_page);
        bookmark=(RelativeLayout)view.findViewById(R.id.menu_Relativ_bookmark);
        ghavanin=(RelativeLayout)view.findViewById(R.id.menu_Relativ_ghavanin);
        enteghad=(RelativeLayout)view.findViewById(R.id.menu_Relativ_enteghad);
        poshtibani=(RelativeLayout)view.findViewById(R.id.menu_Relativ_poshtibani);
        pishnahaddostan=(RelativeLayout)view.findViewById(R.id.menu_Relativ_pishnahaddostan);
        noskhe=(RelativeLayout)view.findViewById(R.id.menu_Relativ_noskhe);


        constraintLayout=(ConstraintLayout)view.findViewById(R.id.Menu_view_constraint) ;

        register.setOnClickListener(this);
        bookmark.setOnClickListener(this);
        ghavanin.setOnClickListener(this);
        enteghad.setOnClickListener(this);
        poshtibani.setOnClickListener(this);
        pishnahaddostan.setOnClickListener(this);
        noskhe.setOnClickListener(this);


        ViewMain=view;
    }

    @Override
    public void OnResume() {
        super.OnResume();
        navbotton.settview(Tag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_Relativ_register_page:
                Activity.GetManager().OpenView(new Menu_Register_view(),"Virayesh",true);
                break;
            case R.id.menu_Relativ_bookmark:
                DB_BookMark db_bookMark=new DB_BookMark(Activity);
                Show_All_Item show_all_item=new Show_All_Item();
                Bundle bundle=new Bundle();
                bundle.putString("Title","نشان شده ها");
                ArrayList<Model_All> mark=new ArrayList<>();
                ArrayList<M_BookMark> book=new ArrayList<>(db_bookMark.getData());
                if (book.size()>0) {
                    for (int i = 0; i < book.size(); i++) {
                        mark.add(new Model_All("Sherkat_R",
                                book.get(i).getId(),
                                book.get(i).getImage(),
                                book.get(i).getTitel(),
                                book.get(i).getRate()
                        ));
                    }
                    bundle.putParcelableArrayList("List",mark);
                    show_all_item.setArguments(bundle);
                    Activity.GetManager().OpenView(show_all_item,"BookMark",true);
                }else {
                    Toast.makeText(Activity, "شما هیچ شرکتی را نشانه گذاری نکرده اید !", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.menu_Relativ_ghavanin:
                Activity.GetManager().OpenView(new Menu_Ghavanin_View(),"Menu_Ghavanin_View",true);
                Toast.makeText(Activity, "NEED TEXT", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_Relativ_enteghad:
                Activity.GetManager().OpenView(new Menu_Enteghad_View(),"enteghad",true);
//                Toast.makeText(Activity, "NEED UI&UX", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_Relativ_poshtibani:
                Activity.GetManager().OpenView(new Menu_Poshtibani_View(),"poshtibani",true);
//                Toast.makeText(Activity, "NEED UI&UX", Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(Activity, "NEED SnackBar View Version", Toast.LENGTH_SHORT).show();

                Snackbar snackbar = Snackbar.make(constraintLayout, "", Snackbar.LENGTH_LONG);
// Get the Snackbar's layout view
                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
// Hide the text
                TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                textView.setVisibility(View.INVISIBLE);

// Inflate our custom view
                View snackView = LayoutInflater.from(Activity).inflate(R.layout.my_snackbar, null);
// Configure the view
//                ImageView imageView = (ImageView) snackView.findViewById(R.id.image);
//                imageView.setImageBitmap(image);
//                TextView textViewTop = (TextView) snackView.findViewById(R.id.text);
//                textViewTop.setText("Text");
//                textViewTop.setTextColor(Color.WHITE);

//If the view is not covering the whole snackbar layout, add this line
                layout.setPadding(0,0,0,0);

// Add the view to the Snackbar's layout
                layout.addView(snackView, 0);
// Show the Snackbar
                snackbar.show();
                break;


        }
    }
}
