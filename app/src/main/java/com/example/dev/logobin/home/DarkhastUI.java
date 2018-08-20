package com.example.dev.logobin.home;

import android.view.View;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;

public class DarkhastUI extends FragmentView {
    @Override
    public void OnCreate() {

        View view =View.inflate(Activity, R.layout.darkhast_ui,null);

        Toast.makeText(Activity, "Add_Adapter_For_Recyclerview", Toast.LENGTH_SHORT).show();

        ViewMain =view;

    }
}
