package com.example.dev.logobin.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.model.Model_Gozaresh;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DarkhastUI extends FragmentView {

  //  @BindView(R.id.Gozaresh_recyclerview) RecyclerView recyclerView;
    private AdapterGozaresh adapter;
    private ArrayList<Model_Gozaresh> listgozaresh;

    @Override
    public void OnCreate() {

        View view =View.inflate(Activity, R.layout.home_darkhast_ui,null);
     //  ButterKnife.bind(Activity);

     //  Toast.makeText(Activity, "", Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.Gozaresh_recyclerview);

        setdatalist();

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Activity);
        adapter=new AdapterGozaresh(listgozaresh);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ViewMain =view;

    }



    public void setdatalist(){
        listgozaresh =new ArrayList<>();

        listgozaresh.add(new Model_Gozaresh(R.drawable.b_damdaran,"02:02","97/3/15"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_domino,"02:10","97/5/07"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_damdaran,"05:40","97/1/30"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_chitoz,"21:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_haraz,"02:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_damdaran,"02:02","97/3/15"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_domino,"02:10","97/5/07"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_damdaran,"05:40","97/1/30"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_chitoz,"21:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_haraz,"02:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_damdaran,"02:02","97/3/15"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_domino,"02:10","97/5/07"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_damdaran,"05:40","97/1/30"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_chitoz,"21:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_haraz,"02:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_damdaran,"02:02","97/3/15"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_domino,"02:10","97/5/07"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_damdaran,"05:40","97/1/30"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_chitoz,"21:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.b_haraz,"02:02","97/1/20"));
    }
}
class AdapterGozaresh extends RecyclerView.Adapter<AdapterGozaresh.ViewHolder>{

    private ArrayList<Model_Gozaresh> list=new ArrayList<>();
    public View rate;

    public AdapterGozaresh(ArrayList<Model_Gozaresh> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_gozaresh,parent,false);
        rate=LayoutInflater.from(parent.getContext()).inflate(R.layout.z_n_rating_g,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Model_Gozaresh modelGozaresh = list.get(position);
        Picasso.get().load(modelGozaresh.getImage()).resize(100,100).into(holder.circleImageView);
       // holder.circleImageView.setImageResource(modelGozaresh.getImage());
        holder.mdata.setText(modelGozaresh.getData());
        holder.mtime.setText(modelGozaresh.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView circleImageView;
        public TextView mtime,mdata;
        private RelativeLayout Rate;

        public ViewHolder(View itemView) {
            super(itemView);

            circleImageView=(CircleImageView)itemView.findViewById(R.id.z_model_gozaresh_circularimage);
            mtime=(TextView)itemView.findViewById(R.id.z_model_gozaresh_textview_time);
            mdata=(TextView)itemView.findViewById(R.id.z_model_gozaresh_textview_data);
            Rate=(RelativeLayout)itemView.findViewById(R.id.z_model_gozaresh_Relative_Rate);
            Rate.addView(rate);

        }
    }
}
