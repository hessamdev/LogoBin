package com.example.dev.logobin.Network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dev.logobin.App;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetHome {

    private String Url_Brand="http://satrapp.ir/api/home/";

    private Context context;

    private int Daste;

    private DB_Brand db_brand;


    public GetHome(Context context) {
        this.context = context;
    }

    public interface Back_Brand{
        void ok(ArrayList<M_Home> brands,int NnbDaste);
        void faild(String Error);
    }

    public void getHome (String Id_Mahale,final Back_Brand back_brand){
        final ArrayList<M_Home> list_home = new ArrayList<>();

        final JsonObjectRequest js_Home=new JsonObjectRequest(Request.Method.GET, Url_Brand+Id_Mahale, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject Jsa_Home=response.getJSONObject("data");
                            JSONArray Brand=Jsa_Home.getJSONArray("brand");
                            Daste=Brand.length();
                            for (int i =0 ;i<Brand.length();i++){
                                int x=0;
                                JSONObject part=Brand.getJSONObject(i);
                                JSONObject Daste=part.getJSONObject("part");
                                String id_Part=Daste.getString("id");
                                String title_Part=Daste.getString("title");
                                JSONArray brand=part.getJSONArray("brands");
                                Log.i("Home_Brand",id_Part+"**"+title_Part);
                                if (brand.length() > 25){
                                    x=25;
                                }else {
                                    x=brand.length();
                                }
                                for (int j=0 ; j<x ; j++){
                                    Log.i("Numb Brand",j+"");
//                                    if (j==25){
//                                        j=brand.length()-1;
//                                        Log.i("Numb Brand if",j+"");
//                                    }
                                    JSONObject Brands=brand.getJSONObject(j);
                                    String id_Brand=Brands.getString("id");
                                    String title_Brand=Brands.getString("title");
                                    String image_Brand="http://satrapp.ir"+Brands.getString("image");
                                    String rate_Brand=Brands.getString("rate");
                                    String rate_Brand_f=rate_Brand;
                                    if (rate_Brand.length() > 3){
                                         rate_Brand_f=rate_Brand.substring(0,3);
                                    }
                                    String state_Brand=Brands.getString("state");
                                    Log.i("Home_Brands",id_Brand+"**"+title_Brand+"**"+"**"+rate_Brand+"**"+state_Brand);
                                    list_home.add(new M_Home("brand" ,id_Part,title_Part,id_Brand,title_Brand,image_Brand,rate_Brand_f,state_Brand,null));


                                }

                            }
                            JSONArray Sherkat=Jsa_Home.getJSONArray("company");
                            int y=0;

                            if (Sherkat.length() > 25){
                                y=25;
                            }else {
                                y=Sherkat.length();
                            }
                            for (int i =0 ; i<y ; i++){
//                                if (i==25){
//                                    i=Sherkat.length()-1;
//                                }
                                JSONObject part=Sherkat.getJSONObject(i);
                                JSONObject Daste=part.getJSONObject("part");
                                String id_Part=Daste.getString("id");
                                String title_Part=Daste.getString("title");
                                Log.i("Home_Sherkat",id_Part+"**"+title_Part);
                                JSONArray brand=part.getJSONArray("companies");
                                for (int j=0 ; j<brand.length() ; j++){
                                    JSONObject Brands=brand.getJSONObject(j);
                                    String id_Sherkat=Brands.getString("id");
                                    String title_Sherkat=Brands.getString("title");
                                    String image_Sherkat="http://satrapp.ir"+Brands.getString("group_image");
                                    String rate_Sherkat=Brands.getString("rate");
                                    String rate_Sherkat_f=rate_Sherkat;
                                    if (rate_Sherkat.length() > 3){
                                        rate_Sherkat_f=rate_Sherkat.substring(0,3);
                                    }
                                    String state_Sherkat=Brands.getString("state");
                                    String type_Sherkat=Brands.getString("type");
                                    Log.i("Home_Sherkat",id_Sherkat+"**"+title_Sherkat+"**"+"**"+rate_Sherkat+"**"+state_Sherkat+"**"+type_Sherkat);
                                    list_home.add(new M_Home("company" ,id_Part,title_Part,id_Sherkat,title_Sherkat,image_Sherkat,rate_Sherkat_f,state_Sherkat,type_Sherkat));

                                }

                            }
                            back_brand.ok(list_home,Daste);


                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, "API_Brand_Error", Toast.LENGTH_SHORT).show();
                back_brand.faild(error.toString());
//                progressDialog.dismiss();
//                Ok=false;
//                brand_ok=false;

            }
        });

        App.getInstance().addToRequestQueue(js_Home,"Home");

    }






}


