package com.example.dev.logobin.ui;

import android.view.LayoutInflater;
import android.view.View;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;

public class Menu_Enteghad_View extends FragmentView {
    @Override
    public void OnCreate() {
        View view= LayoutInflater.from(Activity).inflate(R.layout.menu_entegad,null);

        ViewMain=view;
    }
}
