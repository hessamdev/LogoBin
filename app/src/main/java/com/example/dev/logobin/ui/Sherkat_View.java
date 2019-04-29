package com.example.dev.logobin.ui;

import android.app.Dialog;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.example.dev.logobin.App;
import com.example.dev.logobin.Network.GetDarkhastTamas;
import com.example.dev.logobin.Network.GetDastebandi;
import com.example.dev.logobin.Network.GetKala;
import com.example.dev.logobin.Network.PostSherkat;
import com.example.dev.logobin.R;
import com.example.dev.logobin.RecyclerApp.RecyclerKala;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Adapter_Daste_Brand;
import com.example.dev.logobin.handel.Create_Alert;
import com.example.dev.logobin.handel.DB_BookMark;
import com.example.dev.logobin.handel.PrefUtils;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.model.M_Kala;
import com.example.dev.logobin.model.M_ZirDaste;
import com.example.dev.logobin.model.Model_Gozaresh;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Sherkat_View extends FragmentView {

    String ttt="ttttt";

    private enum TimerState {
        STOPPED,
        RUNNING
    }

    private static final long TIMER_LENGHT = 10; // Ten seconds
    private long mTimeToGo;
    private CountDownTimer mCountDownTimer;
    private TimerState mState;
    private PrefUtils mPreferences;
    private TextView time;
    private boolean darkhastview=false;

    private TextView darkhasttime;
    private ImageView call;


    private ArrayList<M_Kala> list_Kala;
    private ArrayList<M_ZirDaste> list_Daste;
    private Create_Alert createAlert_;

    private String id_Sherkat,simage,srate,stitle,today,yesterday;

    private RecyclerView Recycler_Dastebandi;
    private GetDastebandi getDastebandi;
    private RecyclerView Recycler_Kala;
    private GetKala getKala;

    private DB_BookMark db_bookMark;

    private TextView textView_Seen,textView_Rate;
    private Animation fadin;
    private User_Data user_data;
    private PostSherkat postSherkat;

    private CircleImageView heder;

    private boolean share;

    @Override
    public void OnCreate() {
        View view = LayoutInflater.from(Activity).inflate(R.layout.fragment_sherkat2, null);
        final AppBarLayout appBarLayout=(AppBarLayout)view.findViewById(R.id.appbarsherkat) ;
        appBarLayout.setVisibility(View.INVISIBLE);



        mPreferences=new PrefUtils(Activity);
        user_data=new User_Data(Activity);
        App.getInstance().cancelAllRequests("Cancell_All");

        id_Sherkat=getArguments().getString("ID");
        share=getArguments().getBoolean("Share");
//        image=getArguments().getString("Image");
//        rate=getArguments().getString("Rate");
//        title=getArguments().getString("Title");

        postSherkat=new PostSherkat(Activity);
        postSherkat.Post_Seen_Sherkat(user_data.getUser_id(),id_Sherkat);

        createAlert_ =new Create_Alert(Activity);
        getDastebandi=new GetDastebandi(Activity);
        getKala=new GetKala(Activity);

        db_bookMark=new DB_BookMark(Activity);
        fadin= AnimationUtils.loadAnimation(Activity,R.anim.fadein);


        setUpToolbar(view);


        heder = (CircleImageView) view.findViewById(R.id.Sherkat_Imageview_Heder);

        Recycler_Kala= (RecyclerView) view.findViewById(R.id.Sherkat_Recyclerview);

        Recycler_Kala.setVisibility(View.INVISIBLE);

        Recycler_Dastebandi =(RecyclerView)view.findViewById(R.id.Sherkat_Recycerview_Daste);




        getDastebandi.zirdasteSherkat(id_Sherkat, new GetDastebandi.callback() {
            @Override
            public void onsuccess(ArrayList<M_ZirDaste> listss) {
                appBarLayout.setVisibility(View.VISIBLE);
                Recycler_Kala.setVisibility(View.VISIBLE);
                if (listss.size() > 0) {
                    list_Daste = new ArrayList<>(listss);
                    setUpRecyclerKala(listss.get(0).getId());
//                Toast.makeText(Activity, ""+listss.size(), Toast.LENGTH_SHORT).show();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Activity, LinearLayoutManager.HORIZONTAL, true);
                    Recycler_Dastebandi.setLayoutManager(linearLayoutManager);
                    Recycler_Dastebandi.setHasFixedSize(true);
                    Recycler_Dastebandi.setAdapter(new Adapter_Daste_Brand(list_Daste, new Adapter_Daste_Brand.OnItemClickListener() {
                        @Override
                        public void onItemClick(M_ZirDaste m_zirDaste) {
                            Toast.makeText(Activity, "" + m_zirDaste.getId(), Toast.LENGTH_SHORT).show();
                            setUpRecyclerKala(m_zirDaste.getId());
                        }
                    }));
                }
            }

            @Override
            public void faild(String eror) {
                Activity.onBackPressed();
            }
        }, new GetDastebandi.DataSherkat() {
            @Override
            public void Datasherkat(String seen_toDay, String Seen_YesterDay, String image, String title, String rate) {
                today = seen_toDay;
                yesterday = Seen_YesterDay;

                simage="http://satrapp.ir"+image;
                Picasso.get().load(simage).resize(1024,1024).into(heder);
                srate=rate;
                textView_Rate.setText(rate);
                textView_Rate.startAnimation(fadin);
                stitle=title;
            }
        });




        ViewMain=view;
    }



    @Override
    public void OnResume() {
        super.OnResume();
        initTimer();
    }

    @Override
    public void OnPause() {
        super.OnPause();
        if (mState == TimerState.RUNNING) {
            mCountDownTimer.cancel();
        }
    }

    public void setUpRecyclerKala(String list_id){
        getKala.getKala_Sherkat(id_Sherkat, list_id, new GetKala.vcallback() {
            @Override
            public void onSuccess(ArrayList<M_Kala> list) {
                list_Kala=new ArrayList<>(list);

                GridLayoutManager gridLayoutManager=new GridLayoutManager(Activity,2, LinearLayoutManager.VERTICAL,false);
                Recycler_Kala.setLayoutManager(gridLayoutManager);
                Recycler_Kala.setNestedScrollingEnabled(true);
                Recycler_Kala.setHasFixedSize(true);

                RecyclerKala adapterKala=new RecyclerKala(Activity, list_Kala, Activity, new RecyclerKala.OnItemClickListener() {
                    @Override
                    public void onItemClick(M_Kala model) {
                        Toast.makeText(Activity, ""+model.getId(), Toast.LENGTH_SHORT).show();
                        Kala_View kala_view=new Kala_View();

                        Bundle bundle =new Bundle();
                        bundle.putString("ID_kala",model.getId());
                        bundle.putString("ID_mahale",user_data.getMahale());
                        bundle.putString("ImageKala",model.getImage());
                        bundle.putString("NameKala",model.getTitle());
                        kala_view.setArguments(bundle);

                        Activity.GetManager().OpenView(kala_view,"Kala_View",true);
                    }
                });
                Recycler_Kala.setAdapter(adapterKala);
            }

            @Override
            public void faild(String Eror) {
                Toast.makeText(Activity, "Kala Eror Api", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void setUpToolbar(View view){
        LinearLayout darkhast = (LinearLayout) view.findViewById(R.id.Linear_Sherkat_darkhast);
        call=(ImageView) view.findViewById(R.id.Sherkat_Imageview_Call);
        darkhasttime=(TextView)view.findViewById(R.id.sherkat_textview_darkhasttime) ;

        Picasso.get().load(R.drawable.call).resize(50,50).into(call);
        darkhast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                createAlert_Darkhast();

            }



        });
        LinearLayout Seen=(LinearLayout) view.findViewById(R.id.sherkat_Linear_seen) ;
        ImageView imageseen=(ImageView) view.findViewById(R.id.Sherkat_Imageview_seen);
        Picasso.get().load(R.drawable.looknew2).resize(50,50).into(imageseen);
        Seen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlert_.createAlert_Seen(today,yesterday);
            }
        });

//        View viewseen=LayoutInflater.from(Activity).inflate(R.layout.z_n_look_g,null);
//        Seen.addView(viewseen);
//        textView_Seen=(TextView)viewseen.findViewById(R.id.z_n_Textview_seen) ;
//        Seen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createAlert_.createAlert_Seen(today,yesterday);
//            }
//        });


        LinearLayout Rate=(LinearLayout) view.findViewById(R.id.Sherkat_Linear_Rate) ;
//        View viewrate=LayoutInflater.from(Activity).inflate(R.layout.z_n_rating,null);
//        Rate.addView(viewrate);
        textView_Rate=(TextView)view.findViewById(R.id.z_n_Textview_rate) ;

        Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GetDarkhastTamas getDarkhastTamas= new GetDarkhastTamas(Activity);
                getDarkhastTamas.GetListDarkhast(user_data.getUser_id(), user_data.getToken(), new GetDarkhastTamas.Back_Darkhast() {
                    @Override
                    public void ok(ArrayList<Model_Gozaresh> lst_gozaresh) {

                        boolean alert=false;

                        for (int i = 0 ; i < lst_gozaresh.size() ; i++){
                            if (lst_gozaresh.get(i).getId_sherkat().equals(id_Sherkat)){
//                                createAlert_.createAlert_Rate(user_data.getUser_id(),id_Sherkat);
                                alert=true;
                            }
                        }

                        if (alert){
                            createAlert_.createAlert_Rate(user_data.getUser_id(),id_Sherkat);
                        }else {
                            createAlert_.createAlert_NoRate(srate);
                        }

                    }

                    @Override
                    public void faild(String Error) {

                    }
                });


            }
        });

        LinearLayout back=(LinearLayout)view.findViewById(R.id.Sherkat_Imageview_Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });

        LinearLayout Share=(LinearLayout)view.findViewById(R.id.sherkat_Linear_share);
        ImageView imageShare=(ImageView) view.findViewById(R.id.Sherkat_Imageview_Share);
        Picasso.get().load(R.drawable.share).resize(50,50).into(imageShare);
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onShareItem();

//                Intent sendIntent = new Intent(Intent.ACTION_SEND);
////                sendIntent.setType("image/*");
////                String name="name";
////                String appname="appname";
////                sendIntent.putExtra(Intent.EXTRA_SUBJECT,appname+stitle);
////                sendIntent.putExtra(Intent.EXTRA_STREAM, simage);
////                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "http://satrapp.ir/?sh="+id_Sherkat);
////                sendIntent.putExtra(Intent.EXTRA_STREAM, simage);
//                sendIntent.setType("image/*");
//                sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//                Activity.startActivity(Intent.createChooser(sendIntent,"Share Using"));


            }
        });

        LinearLayout bookmark=(LinearLayout)view.findViewById(R.id.sherkat_Linear_bookmark);
        final ImageView imagebookmark=(ImageView)view.findViewById(R.id.sherkat_imageview_bookmark) ;
        Boolean mark= db_bookMark.setMark(id_Sherkat);

        if (mark){
            imagebookmark.setImageResource(R.drawable.bookmark_full);
            imagebookmark.setTag(R.drawable.bookmark_full);
        }else {
            imagebookmark.setImageResource(R.drawable.bookmark_empty);
            imagebookmark.setTag(R.drawable.bookmark_empty);
        }

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( Integer.parseInt(imagebookmark.getTag().toString()) == R.drawable.bookmark_empty){
                    db_bookMark.insertData(id_Sherkat,simage,stitle,srate);
                    imagebookmark.setImageResource(R.drawable.bookmark_full);
                    imagebookmark.setTag(R.drawable.bookmark_full);
                }else {
                    db_bookMark.delete(id_Sherkat);
                    imagebookmark.setImageResource(R.drawable.bookmark_empty);
                    imagebookmark.setTag(R.drawable.bookmark_empty);
                }

            }
        });




    }

    public void onShareItem() {
        // Get access to bitmap image from view
//        ImageView ivImage = (ImageView) findViewById(R.id.ivResult);
        // Get access to the URI for the bitmap
        Uri bmpUri = getLocalBitmapUri(heder);
        if (bmpUri != null) {
            // Construct a ShareIntent with link to image
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,stitle);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "http://satrapp.ir/?sh="+id_Sherkat+ "  "+stitle);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.setType("image/*");
            // Launch sharing dialog for image
            Activity.startActivity(Intent.createChooser(shareIntent, "Share Image"));
        } else {
            // user did'n get bitmap uri, sharing failed
            Toast.makeText(Activity, "Nashod", Toast.LENGTH_SHORT).show();
        }
    }
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            // This way, you don't need to request external read/write permission.
            File file =  new File(Activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


    // Returns the URI path to the Bitmap displayed in specified ImageView
//    public Uri getLocalBitmapUri(ImageView imageView) {
//        // Extract Bitmap from ImageView drawable
//        Drawable drawable = imageView.getDrawable();
//        Bitmap bmp = null;
//        if (drawable instanceof BitmapDrawable){
//            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        } else {
//            return null;
//        }
//        // Store image to default external storage directory
//        Uri bmpUri = null;
//        try {
//            // Use methods on Context to access package-specific directories on external storage.
//            // This way, you don't need to request external read/write permission.
//            // See https://youtu.be/5xVh-7ywKpE?t=25m25s
//            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
//            FileOutputStream out = new FileOutputStream(file);
//            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
//            out.close();
//            // **Warning:** This will fail for API >= 24, use a FileProvider as shown below instead.
//            bmpUri = Uri.fromFile(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bmpUri;
//    }


    public void createAlert_Darkhast(){

        if  (mState == TimerState.STOPPED) {
            postSherkat.Post_Darkhast_Sherkat(user_data.getUser_id(),id_Sherkat);
            mPreferences.setStartedTime(getNow());
            Log.i(ttt,"start timer");
            startTimer();
            mState = TimerState.RUNNING;
        }

        final Dialog dialog = new Dialog(Activity);
        darkhastview=true;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.1f);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.z_m_alertdialog_darkhast);
        time = (TextView) dialog.findViewById(R.id.z_m_rating_textview_timer);
        ImageView gifImageView=(ImageView) dialog.findViewById(R.id.gifview);

        DrawableImageViewTarget target = new DrawableImageViewTarget(gifImageView);
        Glide.with(Activity)
                .load(R.drawable.loading)
                .into(target);

        dialog.show();
    }



    private long getNow() {
        Calendar rightNow = Calendar.getInstance();
        return rightNow.getTimeInMillis() / 1000;
    }

    private void initTimer() {
        long startTime = mPreferences.getStartedTime();
        if (startTime > 0) {
            mTimeToGo = (TIMER_LENGHT - (getNow() - startTime));
            if (mTimeToGo <= 0) { // TIMER EXPIRED
                mTimeToGo = TIMER_LENGHT;
                mState = TimerState.STOPPED;
                onTimerFinish();
            } else {
                startTimer();
                mState = TimerState.RUNNING;
            }
        } else {
            mTimeToGo = TIMER_LENGHT;
            mState = TimerState.STOPPED;
        }
        updateTimeUi();
    }

    private void onTimerFinish() {
        Toast.makeText(Activity, "zaman darkhast tamama shod", Toast.LENGTH_SHORT).show();
        mPreferences.setStartedTime(0);
        mTimeToGo = TIMER_LENGHT;
        updateTimeUi();
    }

    private void updateTimeUi() {
        if (mState == TimerState.RUNNING) {
//            mTimerButton.setEnabled(false);
            call.setVisibility(View.GONE);
            darkhasttime.setVisibility(View.VISIBLE);
            darkhasttime.setText(String.valueOf(mTimeToGo));

        } else {
            call.setVisibility(View.VISIBLE);
            darkhasttime.setVisibility(View.GONE);
//            mTimerButton.setEnabled(true);
        }

        if (darkhastview) {
            time.setText(String.valueOf(mTimeToGo));
        }
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeToGo * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTimeToGo -= 1;
                updateTimeUi();
                Log.i(ttt,mTimeToGo+"");
            }
            public void onFinish() {
                mState = TimerState.STOPPED;
                onTimerFinish();
                updateTimeUi();
            }
        }.start();
    }



}
