package com.example.dev.logobin.home;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.example.dev.logobin.App;
import com.example.dev.logobin.Network.GetDarkhastTamas;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Create_Alert;
import com.example.dev.logobin.handel.Navbotton;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.model.Model_Gozaresh;
import com.example.dev.logobin.ui.Sherkat_View;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class DarkhastUI extends FragmentView {

    private AdapterGozaresh adapter;

    private RecyclerView recyclerView;
    private Dialog dialog;
    private Navbotton navbotton;

    @Override
    public void OnCreate() {
        View view = View.inflate(Activity, R.layout.home_darkhast_ui, null);

        navbotton =new Navbotton(Activity);

        Create_Alert create_alert = new Create_Alert(Activity);
        dialog = create_alert.createAlert_Load();
        User_Data user_data = new User_Data(Activity);

        dialog.show();
        GetDarkhastTamas getDarkhastTamas = new GetDarkhastTamas(Activity);

        getDarkhastTamas.GetListDarkhast(user_data.getUser_id(), user_data.getToken(), new GetDarkhastTamas.Back_Darkhast() {
            @Override
            public void ok(ArrayList<Model_Gozaresh> lst_gozaresh) {

                if (lst_gozaresh.size() == 0) {
                    Toast.makeText(Activity, "هنوز در خواست تماسی ثبت نکرده اید", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    adapter = new AdapterGozaresh(lst_gozaresh, Activity);
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();
                }

            }

            @Override
            public void faild(String Error) {
                dialog.dismiss();
            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.Gozaresh_recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Activity);
        recyclerView.setLayoutManager(layoutManager);


        ViewMain = view;

    }

    @Override
    public void OnResume() {
        super.OnResume();
        navbotton.settview(Tag);
    }
}




class AdapterGozaresh extends RecyclerView.Adapter<AdapterGozaresh.ViewHolder> {

    private ArrayList<Model_Gozaresh> list;
    private FragmentActivity activity;


    public AdapterGozaresh(ArrayList<Model_Gozaresh> list, FragmentActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_gozaresh, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Model_Gozaresh modelGozaresh = list.get(position);
        Picasso.get().load(modelGozaresh.getMimage()).resize(200, 200).into(holder.circleImageView);
        // holder.circleImageView.setImageResource(modelGozaresh.getImage());
        holder.mdata.setText(modelGozaresh.getmData());
        holder.mtime.setText(modelGozaresh.getMtime());
        holder.title.setText(modelGozaresh.getTitle());
        holder.rate.setText(modelGozaresh.getRate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Image", modelGozaresh.getMimage());
                bundle.putString("ID", modelGozaresh.getId_sherkat());
                bundle.putString("Rate", modelGozaresh.getRate());
                bundle.putString("Title", modelGozaresh.getTitle());
                Sherkat_View sherkat_view = new Sherkat_View();
                sherkat_view.setArguments(bundle);
                activity.GetManager().OpenView(sherkat_view, "Sherkat_View", true);
            }
        });

        holder.Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_Alert create_alert = new Create_Alert(activity);
                User_Data user_data = new User_Data(activity);

                create_alert.createAlert_Rate(user_data.getUser_id(), modelGozaresh.getId_sherkat());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView mtime, mdata, title, rate;
        private LinearLayout Rate;

        public ViewHolder(View itemView) {
            super(itemView);

            circleImageView = (CircleImageView) itemView.findViewById(R.id.z_model_gozaresh_circularimage);
            mtime = (TextView) itemView.findViewById(R.id.z_model_gozaresh_textview_time);
            mdata = (TextView) itemView.findViewById(R.id.z_model_gozaresh_textview_data);
            Rate = (LinearLayout) itemView.findViewById(R.id.z_m_gozaresh_Linear_Rate);
            title = (TextView) itemView.findViewById(R.id.z_m_gozaresh_textview_title);
            rate = (TextView) itemView.findViewById(R.id.z_n_Textview_rate);

        }
    }
}
