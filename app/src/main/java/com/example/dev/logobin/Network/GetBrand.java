package com.example.dev.logobin.Network;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import android.os.BaseBundle;
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
import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetBrand {

    private String Url_Brand="http://satrapp.ir/api/brands/";

    private Context context;

    public GetBrand(Context context) {
        this.context = context;
    }

    public interface Back_Brand{
        void ok(ArrayList<M_Home> brands);
        void faild(String Error);
    }

    public void getBrand (final String part, final Back_Brand back_brand){
        final ArrayList<M_Home> listBrand = new ArrayList<>();
        final Create_Alert create_alert= new Create_Alert(context);
        final Dialog dialog= create_alert.createAlert_Load();
        dialog.show();



        final JsonObjectRequest js_brand=new JsonObjectRequest(Request.Method.GET, Url_Brand+part, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray Jsa_Brand=response.getJSONArray("data");
                            for (int i =0 ;i<Jsa_Brand.length();i++){
                                JSONObject Brand=Jsa_Brand.getJSONObject(i);
                                String id = Brand.getString("id");
                                int iid=Integer.parseInt(id );
                                String title = Brand.getString("title");
                                String image = Brand.getString("image");
                                String rate = Brand.getString("rate");
                                String state = Brand.getString("state");
                                String url_Image="http://satrapp.ir"+image;
//                                db_brand.insertData(id,title,rate,url_Image);

                                listBrand.add(new M_Home("brand",part,null,id,title,url_Image,rate,state,null));
                                Log.i("title to insert",title);

                            }
                            back_brand.ok(listBrand);
                            dialog.dismiss();


                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "API_Brand_Error", Toast.LENGTH_SHORT).show();
                back_brand.faild(error.toString());
                dialog.dismiss();
//                progressDialog.dismiss();
//                Ok=false;
//                brand_ok=false;

            }
        });

        App.getInstance().addToRequestQueue(js_brand,"Brand");

    }






}


