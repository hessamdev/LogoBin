package com.example.dev.logobin.handler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.model.Model_ImageSlider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageSlider extends PagerAdapter {

    private Context context;
    private ArrayList<Model_ImageSlider> list;
    private LayoutInflater inflater;

    public ImageSlider(Context context, ArrayList<Model_ImageSlider> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View Slider=inflater.inflate(R.layout.z_m_imageslider,null);
        final Model_ImageSlider Model=list.get(position);
        ImageView imageView=(ImageView)Slider.findViewById(R.id.z_m_Imageslider_Imageview);
        Picasso.get().load(Model.getImage()).fit().into(imageView);

        Slider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = Model.getTag();
                Toast.makeText(context, ""+test, Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(Slider,0);
        return Slider;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

}

