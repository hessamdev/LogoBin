package com.example.dev.logobin.register;

import android.view.View;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;

public class Siginin extends FragmentView {
    @Override
    public void OnCreate() {
        View view = View.inflate(Activity, R.layout.register_signin_ui,null);

        ViewMain=view;
    }
}
