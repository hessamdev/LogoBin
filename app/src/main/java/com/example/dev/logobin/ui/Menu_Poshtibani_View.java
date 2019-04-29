package com.example.dev.logobin.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.Network.Post_Poshtibani;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.User_Data;

import java.util.HashMap;
import java.util.Map;

public class Menu_Poshtibani_View extends FragmentView {


    @Override
    public void OnCreate() {
        View view = LayoutInflater.from(Activity).inflate(R.layout.menu_poshtibani,null);

        ImageView back=(ImageView) view.findViewById(R.id.Poshtibani_Imageview_Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });

        final EditText Email = (EditText)view.findViewById(R.id.postibani_EditText_Email) ;
        final EditText Subject = (EditText)view.findViewById(R.id.postibani_EditText_Subject) ;
        final EditText Message = (EditText)view.findViewById(R.id.postibani_EditText_Message) ;


        TextView send=(TextView) view.findViewById(R.id.postibani_textview_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean ok= isValidEmail(Email.getText().toString());

               if (ok){
                   Post_Poshtibani post_poshtibani=new Post_Poshtibani(Activity);
                   User_Data user_data = new User_Data(Activity);
                   Map <String ,String> map_poshtibani=new HashMap<>();
                   map_poshtibani.put("user_id",user_data.getUser_id());
                   map_poshtibani.put("mobile",user_data.getUser_phone());
                   map_poshtibani.put("email",Email.getText().toString());
                   map_poshtibani.put("subject","poshtibani_"+Subject.getText().toString());
                   map_poshtibani.put("message",Subject.getText().toString());
                   post_poshtibani.Post_Poshtibanimap(map_poshtibani);
               }


                Toast.makeText(Activity, ""+ok, Toast.LENGTH_SHORT).show();
            }
        });






        ViewMain=view;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
