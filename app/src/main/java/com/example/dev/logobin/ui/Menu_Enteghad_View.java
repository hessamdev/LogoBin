package com.example.dev.logobin.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;

public class Menu_Enteghad_View extends FragmentView {
    @Override
    public void OnCreate() {
        View view= LayoutInflater.from(Activity).inflate(R.layout.menu_entegad,null);

        ImageView back=(ImageView) view.findViewById(R.id.enteghad_Imageview_Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });

        final EditText Email = (EditText)view.findViewById(R.id.enteghad_EditText_Email) ;


        TextView send=(TextView) view.findViewById(R.id.enteghad_textview_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok= isValidEmail(Email.getText().toString());
                Toast.makeText(Activity, ""+ok, Toast.LENGTH_SHORT).show();
            }
        });

        ViewMain=view;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
