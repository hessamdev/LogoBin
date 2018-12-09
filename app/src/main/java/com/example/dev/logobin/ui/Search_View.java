package com.example.dev.logobin.ui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;

import java.util.Objects;

public class Search_View extends FragmentView {

    EditText search;

    @Override
    public void OnCreate() {
        View view= LayoutInflater.from(Activity).inflate(R.layout.home_search_ui,null);
        search=(EditText) view.findViewById(R.id.Search_EditText_search);
        search.requestFocus();

        showSoftwareKeyboard(true);
        ViewMain=view;

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

    @Override
    public void OnOpen() {
        super.OnOpen();

    }

    @Override
    public void OnResume() {
        super.OnResume();

    }

    @Override
    public void OnPause() {
        super.OnPause();
        showSoftwareKeyboard(false);
        
    }
}
