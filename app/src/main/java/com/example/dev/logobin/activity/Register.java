package com.example.dev.logobin.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.dev.logobin.R;

import com.example.dev.logobin.Register.RegisterCode;
import com.example.dev.logobin.Register.RegisterPage;

import com.example.dev.logobin.fragment.FragmentActivity;


public class Register extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);


        changepage(1);

    }

    public void changepage(int page){


        Fragment fragment = null;
        switch (page){

            case 1:
                fragment=new RegisterPage();
                break;
            case 2:
               fragment= new RegisterCode();
                break;

        }

        getFragmentManager().beginTransaction().replace(R.id.framefull,fragment).commit();


    }


}
