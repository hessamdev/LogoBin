package com.example.dev.logobin.handler;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

class Viewpager_Runabel{

//
//    int page;
//
//    @SuppressLint("ClickableViewAccessibility")
//    public void setup(final ViewPager viewPager, final Runnable runnable){
//
//
//     viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//
//            page =position;
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    });
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            switch (event.getAction() & MotionEvent.ACTION_MASK) {
//
//                case MotionEvent.ACTION_DOWN:
//                case MotionEvent.ACTION_UP:
//                case MotionEvent.ACTION_OUTSIDE:
//                case MotionEvent.ACTION_CANCEL:
//                case MotionEvent.ACTION_POINTER_DOWN:
//                case MotionEvent.ACTION_POINTER_UP:
//                case MotionEvent.ACTION_MOVE:
//                    viewPager.removeCallbacks(runnable);
//                    break;
//            }
//            viewPager.postDelayed(runnable,2500);
//            return false;
//        }
//    });
//
//    //Change_Slide
//    runnable =new Runnable() {
//        @Override
//        public void run() {
//            if (adapter.getCount()-1== page){
//              //  Log.i("Page ;;;",adapter.getCount()+"__"+ page);
//                page=0;
//            }else {
//             //   Log.i("Page ;;;",adapter.getCount()+"__"+ page);
//                page++;
//            }
//            viewPager.setCurrentItem(page,true);
//            viewPager.postDelayed(this,2500);
//        }
//    };
//    }
}
