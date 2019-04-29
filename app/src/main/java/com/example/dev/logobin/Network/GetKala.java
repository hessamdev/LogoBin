package com.example.dev.logobin.Network;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import com.example.dev.logobin.handel.Create_Alert;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.handel.DB_Kala;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_Kala;
import com.example.dev.logobin.ui.Brand_View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetKala {



    Context context;


    public GetKala(Context context) {
        this.context = context;
    }

    public interface vcallback{
        void onSuccess(ArrayList<M_Kala> list);
        void faild (String Eror);
    }





    public void getKala_Brand(String Factoori_id, String sublistid,String Id_Mahale, final int load, final vcallback vcallback){

//        db_kala= new DB_Kala(context);
//        db_kala.delete();

        final ArrayList<M_Kala> kalalist=new ArrayList<>();
        String Url_Kala2="http://satrapp.ir/api/pro/"+Factoori_id+"/"+sublistid;
        String Url_Kala="http://satrapp.ir/api/brand/"+Factoori_id+"/products/"+sublistid+"/"+Id_Mahale;
        Log.i("Brand_Getkala",Url_Kala);
        JsonObjectRequest js_kala = new JsonObjectRequest(Request.Method.GET, Url_Kala, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray Jsa_Kala=response.getJSONArray("data");

                            for (int i =0 ;i<Jsa_Kala.length();i++){
//                                if (i==load+50){
//                                    i=Jsa_Kala.length();
//                                    vcallback.onSuccess(kalalist);
//                                }
                                JSONObject Kala=Jsa_Kala.getJSONObject(i);
                                String id = Kala.getString("id");
                                String title = Kala.getString("title");
//                                String cprice = Kala.getString("cprice");
//                                String code = Kala.getString("code");
                                String details = Kala.getString("details");
                                String image = "http://satrapp.ir"+(Kala.getString("image"));
                                String hascompany = Kala.getString("hascompany");
//                                String factory_id = Kala.getString("factory_id");
//                                String list_id = Kala.getString("list_id");
//                                String sublist_id = Kala.getString("sublist_id");
//                                db_kala.insertData(id,title,cprice,code,details,image,active,factory_id,list_id,sublist_id);
                                kalalist.add(new M_Kala(id,title,null,null,details,image,hascompany,null,null,null));
                                Log.i("id kala = ",id);
                                Log.i("title kala = ",title);

                            }
                            vcallback.onSuccess(kalalist);



                        }catch (JSONException e){
                            vcallback.faild(e.toString());
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "API_kala_Error", Toast.LENGTH_SHORT).show();

            }
        });

        App.getInstance().addToRequestQueue(js_kala,"Kala_Brand");


    }

    public void getKala_Sherkat(String Sherkat_id, String sublistid, final vcallback vcallback){

        final ArrayList<M_Kala> kalalist=new ArrayList<>();
        String Url_Kala="http://satrapp.ir/api/company/product/"+Sherkat_id+"/"+sublistid;

        JsonObjectRequest js_kala = new JsonObjectRequest(Request.Method.GET, Url_Kala, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject DATA=response.getJSONObject("data");
                            JSONArray KALA=DATA.getJSONArray("products");
                            for (int i =0 ;i<KALA.length();i++){
//                                if (i==load+50){
//                                    i=Jsa_Kala.length();
//                                    vcallback.onSuccess(kalalist);
//                                }
                                JSONObject Kala=KALA.getJSONObject(i);
                                String id = Kala.getString("id");
                                String title = Kala.getString("title");
                                String cprice = Kala.getString("price");
//                                String code = Kala.getString("code");
//                                String details = Kala.getString("details");
                                String image = "http://satrapp.ir"+(Kala.getString("image"));
                                kalalist.add(new M_Kala(id,title,cprice ,null,null,image,"no" ,null,null,null));
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

        App.getInstance().addToRequestQueue(js_kala,"KALA Sherkat");


    }

    public void getKala_Dastebandi(String sublist_Id, final vcallback vcallback){

        User_Data user_data= new User_Data(context);

        String Url_kala_daste="http://satrapp.ir/api/search/sublist/"+sublist_Id+"/"+user_data.getMahale();
        final ArrayList<M_Kala> kalalist=new ArrayList<>();
        Create_Alert create_alert=new Create_Alert(context);
        final Dialog dialog_load= create_alert.createAlert_Load();
        dialog_load.show();

        JsonObjectRequest getkala_daste=new JsonObjectRequest(Request.Method.GET, Url_kala_daste, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject DATA = response.getJSONObject("data");
                            JSONArray KALA = DATA.getJSONArray("results");
                            for (int i = 0; i < KALA.length(); i++) {
                                JSONObject Kala = KALA.getJSONObject(i);
                                String id = Kala.getString("id");
                                String title = Kala.getString("title");
                                String hascompany = Kala.getString("hascompany");
//                                String code = Kala.getString("code");
//                                String details = Kala.getString("details");
                                String image = "http://satrapp.ir" + (Kala.getString("image"));
                                kalalist.add(new M_Kala(id, title, null, null, null, image, hascompany, null, null, null));
                            }
                            vcallback.onSuccess(kalalist);
                            dialog_load.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            vcallback.faild(e.toString());
                            dialog_load.dismiss();
                        }
                    }
                    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vcallback.faild(error.toString());
                dialog_load.dismiss();
                Toast.makeText(context, "Api Error", Toast.LENGTH_SHORT).show();

            }
        });


        App.getInstance().addToRequestQueue(getkala_daste,"Getkala_daste");

    }






}


