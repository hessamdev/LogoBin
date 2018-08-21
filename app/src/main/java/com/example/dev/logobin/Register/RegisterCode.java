package com.example.dev.logobin.Register;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dev.logobin.R;


public class RegisterCode extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view =LayoutInflater.from(getActivity()).inflate(R.layout.registercod_ui,null);


        return view;
    }
}
