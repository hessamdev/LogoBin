package com.example.dev.logobin.handel;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.example.dev.logobin.Network.PostSherkat;
import com.example.dev.logobin.R;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class Create_Alert {

    private Context context;
    private CountDownTimer countDownTimer;
    private static int num ;
    private static boolean btime;
    private SharedPreferences Numtime;
    private TextView time;

    public Create_Alert(Context context) {
        this.context = context;
    }

    public interface calltime{
        void ttime(String tt,boolean finish);
    }
    public void Timer(final calltime calltime){
        num=180;
        countDownTimer=new CountDownTimer(180000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Timer",""+millisUntilFinished);
                calltime.ttime(num+"",false);

                num--;
                time.setText(String.valueOf(num));
            }


            @Override
            public void onFinish() {
                num=180;
                calltime.ttime(num+"",true);
                Toast.makeText(context, "Finish", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public CountDownTimer leftt(){
        countDownTimer=new CountDownTimer(180000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Timer",""+millisUntilFinished);
//                calltime.ttime(num+"",false);

                num--;
                time.setText(String.valueOf(num));
            }


            @Override
            public void onFinish() {
                num=180;
//                calltime.ttime(num+"",true);
                Toast.makeText(context, "Finish", Toast.LENGTH_SHORT).show();
            }
        };
        return countDownTimer;

    }

    public void createAlert_Darkhast(){


        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.1f);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.z_m_alertdialog_darkhast);
        time = (TextView) dialog.findViewById(R.id.z_m_rating_textview_timer);
        ImageView gifImageView=(ImageView) dialog.findViewById(R.id.gifview);

        DrawableImageViewTarget target = new DrawableImageViewTarget(gifImageView);
        Glide.with(context)
                .load(R.drawable.loading)
                .into(target);

//        time.setText(String.valueOf(num));

//        time.setText(num);
        dialog.show();
    }

    public void createAlert_Rate(final String User_Id, final String Grup_Id){

        final Dialog dialog=new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.1f);
        dialog.setContentView(R.layout.z_m_alertdialog_rating);
        TextView sabt = (TextView) dialog.findViewById(R.id.AlertRate_Button_Sabt);
        final TextView rate=(TextView)dialog.findViewById(R.id.z_m_rating_textview) ;
        final String[] s = {null};
        final MaterialRatingBar materialRatingBar=(MaterialRatingBar) dialog.findViewById(R.id.z_m_rating_ratingbar);

        rate.setText(String.valueOf(materialRatingBar.getRating()));
        materialRatingBar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                if (rating >=1f) {
                    rate.setText(String.valueOf(rating));
                }
                if (rating < 1.0f){
//                    materialRatingBar.setRating(1f);
                    ratingBar.setRating(1.0f);
                    Log.i("Rating",rating+"");
                }

            }
        });
        sabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "امتیاز شما "+ rate.getText().toString() +" ثبت شد.", Toast.LENGTH_SHORT).show();
                PostSherkat postSherkat=new PostSherkat(context);
                postSherkat.Post_Rate_Sherkat(User_Id,Grup_Id,rate.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void createAlert_Seen(String today,String yesterday){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.1f);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.z_m_alertdialog_seen);
        TextView Today=(TextView) dialog.findViewById(R.id.z_m_alertdialog_seen_Textview_today);
        Today.setText(today);
        TextView Yesterday=(TextView) dialog.findViewById(R.id.z_m_alertdialog_seen_Textview_yesterday);
        Yesterday.setText(yesterday);


        dialog.show();
    }

    public void createAlert_NoRate(String Rate){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.1f);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.z_m_alertdialog_norate);
        TextView Today=(TextView) dialog.findViewById(R.id.z_m_Textview_rate);
        Today.setText(Rate);
//        TextView Yesterday=(TextView) dialog.findViewById(R.id.z_m_alertdialog_seen_Textview_yesterday);
//        Yesterday.setText(yesterday);


        dialog.show();
    }

    public void createAlert_Deleteuser(final DeleteAcc deleteAcc){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.z_m_alertdialog_deleteacc);
        TextView no=(TextView)dialog.findViewById(R.id.AlertDeleteAcc_Textview_No) ;
        TextView yes=(TextView)dialog.findViewById(R.id.AlertDeleteAcc_Textview_Yes) ;

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteAcc.Delete(true);

            }
        });

        dialog.show();
    }

    public Dialog createAlert_Load(){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.1f);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCancelable(false);
        ImageView loading = new ImageView(context);
        loading.setAlpha(0.2f);
        dialog.setContentView(loading, new ViewGroup.LayoutParams(400, 90));
        DrawableImageViewTarget target = new DrawableImageViewTarget(loading);
        Glide.with(context)
                .load(R.drawable.loading)
                .into(target);

        return dialog;
    }


    public interface DeleteAcc{
        void Delete(Boolean yes);
    }





}




