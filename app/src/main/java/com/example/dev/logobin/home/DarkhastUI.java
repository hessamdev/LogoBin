package com.example.dev.logobin.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.model.Model_Gozaresh;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DarkhastUI extends FragmentView {

  //  @BindView(R.id.Gozaresh_recyclerview) RecyclerView recyclerView;
    private AdapterGozaresh adapter;
    private ArrayList<Model_Gozaresh> listgozaresh;

    @Override
    public void OnCreate() {

        View view =View.inflate(Activity, R.layout.darkhast_ui,null);
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
        listgozaresh.add(new Model_Gozaresh(R.drawable.gooshtiran,"02:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.damdaran,"02:02","97/3/15"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.domino,"02:10","97/5/07"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.damdaran,"05:40","97/1/30"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.choopan,"02:20","97/2/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.chitoz,"21:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.haraz,"02:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.gooshtiran,"02:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.damdaran,"02:02","97/3/15"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.domino,"02:10","97/5/07"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.damdaran,"05:40","97/1/30"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.choopan,"02:20","97/2/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.chitoz,"21:02","97/1/20"));
        listgozaresh.add(new Model_Gozaresh(R.drawable.haraz,"02:02","97/1/20"));
    }
}
class AdapterGozaresh extends RecyclerView.Adapter<AdapterGozaresh.ViewHolder>{

    private ArrayList<Model_Gozaresh> list=new ArrayList<>();

    public AdapterGozaresh(ArrayList<Model_Gozaresh> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_gozaresh,parent,false);

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

        public ViewHolder(View itemView) {
            super(itemView);

            circleImageView=(CircleImageView)itemView.findViewById(R.id.z_model_gozaresh_circularimage);
            mtime=(TextView)itemView.findViewById(R.id.z_model_gozaresh_textview_time);
            mdata=(TextView)itemView.findViewById(R.id.z_model_gozaresh_textview_data);

        }
    }
}
