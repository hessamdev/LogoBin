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
import com.example.dev.logobin.model.M_Brand;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetBrand {

    String Url_Brand="http://satrapp.ir/api/brands";

    Context context;
    ProgressDialog progressDialog;

    private Boolean Ok;
    private boolean brand_ok;

    DB_Brand db_brand;
    ArrayList<M_Brand> listBrand;


    public GetBrand(Context context) {
        this.context = context;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){


    };



    public void getdatafromserver(){



        String Url_Kala="http://satrapp.ir/api/brand/10";

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
                                String image = Kala.getString("image");
                                String active = Kala.getString("active");
                                String factory_id = Kala.getString("factory_id");
                                String list_id = Kala.getString("list_id");
                                String sublist_id = Kala.getString("sublist_id");

                            }
                            progressDialog.dismiss();
                            Ok=true;

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "API_kala_Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Ok=false;

            }
        });




    }

    public void getBrand (){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Fetching The File....");
        progressDialog.show();

        db_brand=new DB_Brand(context);
        final JsonObjectRequest js_brand=new JsonObjectRequest(Request.Method.GET, Url_Brand, null,
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
                                String url_Image="http://satrapp.ir"+image;
                                db_brand.insertData(id,title,rate,url_Image);
                                Log.i("Rate to insert",rate);

                            }
                            progressDialog.dismiss();
                            brand_ok=true;
                            usebrabrand();
                            Toast.makeText(context, "insert", Toast.LENGTH_SHORT).show();
                            listBrand=new ArrayList<>(db_brand.getData());

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "API_kala_Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Ok=false;
                brand_ok=false;

            }
        });

        App.getInstance().addToRequestQueue(js_brand,"Brand");

    }

    public boolean usebrabrand(){
        return brand_ok;
    }





}


