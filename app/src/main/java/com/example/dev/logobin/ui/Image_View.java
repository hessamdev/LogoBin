package com.example.dev.logobin.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.squareup.picasso.Picasso;

public class Image_View extends FragmentView {

    ImageView imageView;

    @Override
    public void OnCreate() {

        View view = View.inflate(Activity, R.layout.fragment_image_view,null);
        imageView=(ImageView) view.findViewById(R.id.Imageview_Imageview_Imageview);
//        imageView.setBackground(Activity.getResources().getDrawable(R.drawable.logo_png));
        Animation animation= AnimationUtils.loadAnimation(Activity,R.anim.fadein);
        imageView.setAnimation(animation);

        final HorizontalScrollView scrollView=(HorizontalScrollView)view.findViewById(R.id.ImageView_scroll_title) ;

        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);

        ImageView back=(ImageView)view.findViewById(R.id.Imageview_Imageview_Back) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });

        TextView titel=(TextView)view.findViewById(R.id.Imageview_Textview_NameKala) ;
        titel.setText(getArguments().getString("Title"));

        Picasso.get().load(getArguments().getString("Image")).resize(1024,1024).into(imageView);



//        imageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Picasso.get().load(getArguments().getString("Image")).resize(2048,2048).centerCrop().into(imageView);
//                return false;
//            }
//        });


        ViewMain=view;

    }
}
