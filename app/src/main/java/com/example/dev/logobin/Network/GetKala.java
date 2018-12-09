package com.example.dev.logobin.Network;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dev.logobin.App;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.handel.DB_Kala;
import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_Kala;
import com.example.dev.logobin.ui.Brand_View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetKala {

    String Url_Brand="http://satrapp.ir/api/brands";

    Context context;
    ProgressDialog progressDialog;

    private Boolean Ok;
    private boolean brand_ok;

    DB_Brand db_brand;
    DB_Kala db_kala;
    ArrayList<M_Brand> listBrand;


    public GetKala(Context context) {
        this.context = context;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){


    };
    public interface vcallback{
        void onSuccess(ArrayList<M_Kala> list);
    }





    public void getKala(String id,String sublistid, final vcallback vcallback){

//        db_kala= new DB_Kala(context);
//        db_kala.delete();

        final ArrayList<M_Kala> kalalist=new ArrayList<>();
        String Url_Kala="http://satrapp.ir/api/pro/"+id+"/"+sublistid;

        JsonObjectRequest js_kala = new JsonObjectRequest(Request.Method.GET, Url_Kala, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray Jsa_Kala=response.getJSONArray("data");

                            for (int i =0 ;i<Jsa_Kala.length();i++){
                                JSONObject Kala=Jsa_Kala.getJSONObject(i);
                                String id = Kala.getString("id");
                                String title = Kala.getString("title");
                                String cprice = Kala.getString("cprice");
                                String code = Kala.getString("code");
                                String details = Kala.getString("details");
                                String image = "http://satrapp.ir"+(Kala.getString("image"));
                                String active = Kala.getString("active");
                                String factory_id = Kala.getString("factory_id");
                                String list_id = Kala.getString("list_id");
                                String sublist_id = Kala.getString("sublist_id");
//                                db_kala.insertData(id,title,cprice,code,details,image,active,factory_id,list_id,sublist_id);
                                kalalist.add(new M_Kala(id,title,cprice ,code,details,image,active,factory_id,list_id,sublist_id));
                                Log.i("id kala = ",id);
                                Log.i("title kala = ",title);

                            }
                            vcallback.onSuccess(kalalist);



                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "API_kala_Error", Toast.LENGTH_SHORT).show();

            }
        });

        App.getInstance().addToRequestQueue(js_kala,"Brand");


    }






}


