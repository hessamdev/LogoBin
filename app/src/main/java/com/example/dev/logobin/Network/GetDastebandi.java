package com.example.dev.logobin.Network;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dev.logobin.App;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.handel.DB_DasteBandi;
import com.example.dev.logobin.handel.DB_ZirDaste;
import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_ZirDaste;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetDastebandi {

    String Url_Dastebandi="http://satrapp.ir/api/lists";

    Context context;
    ProgressDialog progressDialog;


    DB_DasteBandi db_dasteBandi;
    DB_ZirDaste db_zirDaste;


    public GetDastebandi(Context context) {
        this.context = context;
    }


    public void getDaste(){

        db_dasteBandi=new DB_DasteBandi(context);
        db_zirDaste=new DB_ZirDaste(context);

        JsonObjectRequest Dastebandi = new JsonObjectRequest(Request.Method.GET, Url_Dastebandi, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray Daste=response.getJSONArray("data");
                            Log.i("Daste lenth",Daste.length()+"");

                            for (int i =0 ;i<Daste.length();i++){
                                Log.i("Daste i",i+"");
                                JSONObject daste=Daste.getJSONObject(i);
                                String id = daste.getString("id");
                                String title = daste.getString("title");
                                Log.i("Id - titel",id+" - "+title);
                                db_dasteBandi.insertData(id,title);
                                JSONArray zirdaste = daste.getJSONArray("sublists");

                                for (int j = 0 ; j<zirdaste.length() ; j++){
                                    Log.i("ZirDaste i",j+"");
                                    JSONObject zirmenu=zirdaste.getJSONObject(j);
                                    String idzir=zirmenu.getString("id");
                                    String titlezir=zirmenu.getString("title");
                                    String list_idzir=zirmenu.getString("list_id");
                                    db_zirDaste.insertData(idzir,titlezir,list_idzir);
                                    Log.i("Id - titel - listid",idzir+" - "+titlezir+" - "+list_idzir);
                                }

                            }


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


        App.getInstance().addToRequestQueue(Dastebandi,"Dastebandi");

    }

    public ArrayList<M_ZirDaste> zirdastebrand(String id_Brand, final callback callback){
        String Url_ZirDasteBrand="http://satrapp.ir/api/brand/"+id_Brand;
        final ArrayList<M_ZirDaste> list=new ArrayList<>();

        JsonObjectRequest Dastebandi = new JsonObjectRequest(Request.Method.GET, Url_ZirDasteBrand, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray Daste=response.getJSONArray("data");

                            for (int i =0 ;i<Daste.length();i++) {
                                JSONObject Full=Daste.getJSONObject(i);
                                JSONObject Zirgroup=Full.getJSONObject("sublist");
                                String id = Zirgroup.getString("id");
                                String title = Zirgroup.getString("title");
                                String list_id = Zirgroup.getString("list_id");
                                list.add(new M_ZirDaste(id,title,list_id));



                            }

                            callback.onsuccess(list);

//                            for (int i =0 ;i<Daste.length();i++){
//                                Log.i("Daste i",i+"");
//                                JSONObject daste=Daste.getJSONObject(i);
//                                String id = daste.getString("id");
//                                String title = daste.getString("title");
//                                Log.i("Id - titel",id+" - "+title);
//                                db_dasteBandi.insertData(id,title);
//                                JSONArray zirdaste = daste.getJSONArray("sublists");
//
//                                for (int j = 0 ; j<zirdaste.length() ; j++){
//                                    Log.i("ZirDaste i",j+"");
//                                    JSONObject zirmenu=zirdaste.getJSONObject(j);
//                                    String idzir=zirmenu.getString("id");
//                                    String titlezir=zirmenu.getString("title");
//                                    String list_idzir=zirmenu.getString("list_id");
//                                    db_zirDaste.insertData(idzir,titlezir,list_idzir);
//                                    Log.i("Id - titel - listid",idzir+" - "+titlezir+" - "+list_idzir);
//                                }
//
//                            }


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


        App.getInstance().addToRequestQueue(Dastebandi,"Dastebandi");

        return list;
    }

    public interface callback{
        void onsuccess(ArrayList<M_ZirDaste> listss);
    }



}


