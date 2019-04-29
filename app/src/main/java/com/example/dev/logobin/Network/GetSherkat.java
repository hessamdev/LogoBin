package com.example.dev.logobin.Network;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dev.logobin.App;
import com.example.dev.logobin.handel.Create_Alert;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.model.M_Home;
import com.example.dev.logobin.model.M_Sherkat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetSherkat {

    private Context context;


    public GetSherkat(Context context) {
        this.context = context;
    }

    public interface Back_Sherkat{
        void onSuccess(ArrayList<M_Home> list);
        Void onfailor(String Error);
    }



    public void GetSHerkat(final String part, final Back_Sherkat back_sherkat){
        User_Data user_data=new User_Data(context);
        Create_Alert create_alert=new Create_Alert(context);
        final Dialog dialog=create_alert.createAlert_Load();
        dialog.show();

        final ArrayList<M_Home> listSherkat=new ArrayList<>();

        String Url_Sherkat="http://satrapp.ir/api/company/"+user_data.getMahale()+"/"+part;

        JsonObjectRequest RQ_sherkat = new JsonObjectRequest(
                Request.Method.GET,
                Url_Sherkat,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("Response sherkat",response.toString());

                        try {
                            JSONArray arraySherkat=response.getJSONArray("data");

                            for (int i = 0 ; i<arraySherkat.length() ; i++){

                                JSONObject objectSherkat=arraySherkat.getJSONObject(i);
                                String Id =objectSherkat.getString("id");
                                String title =objectSherkat.getString("title");
                                String image =objectSherkat.getString("group_image");
                                String state =objectSherkat.getString("state");
                                String rate =objectSherkat.getString("rate");
                                String rate_f=rate.substring(0,3);
                                String type =objectSherkat.getString("type");

                                Log.i("Titel sherkat ", title);

                                listSherkat.add(new M_Home("company",part,null,Id,title,"http://satrapp.ir"+image,rate_f,state,type));
                            }



                        }catch (JSONException e){
                            e.printStackTrace();
                        }


                        back_sherkat.onSuccess(listSherkat);
                        dialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                back_sherkat.onfailor(error.toString());
                dialog.dismiss();
                Toast.makeText(context, "مشکل اتصال سرور", Toast.LENGTH_SHORT).show();
            }
        }
        );


        App.getInstance().addToRequestQueue(RQ_sherkat,"Sherkat");

    }

    public void Getsherkat_for_kala(String Id_kala, String Id_mahale, final Back_Sherkat back_sherkat_gheymat){

        String Url_sherkat="http://satrapp.ir/api/product/"+Id_kala+"/"+Id_mahale;

        final ArrayList<M_Home> list = new ArrayList<>();

        JsonObjectRequest RQ_sherkat = new JsonObjectRequest(
                Request.Method.GET,
                Url_sherkat,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("Response sherkat",response.toString());

                        try {
                            JSONObject arraySherkat=response.getJSONObject("data");
                            JSONObject kala=arraySherkat.getJSONObject("product");
                            String id_kala=kala.getString("id");
                            JSONArray arraycompany=arraySherkat.getJSONArray("companies");

                            for (int i = 0 ; i<arraycompany.length() ; i++){
                                JSONObject objectkala=arraycompany.getJSONObject(i);
                                JSONObject objectSherkat=objectkala.getJSONObject("group");
                                String Id =objectSherkat.getString("id");
                                String title =objectSherkat.getString("title");
                                String image =objectSherkat.getString("group_image");

                                String state =objectkala.getString("state");
                                String rate =objectkala.getString("rate");
                                String rate_f=rate.substring(0,3);
//                                String type =objectSherkat.getString("type");

                                String price=objectkala.getString("price");
                                String last=objectkala.getString("last");
                                String pack=objectkala.getString("pack");
                                String unit=objectkala.getString("unit");
                                String minimum=objectkala.getString("minimum");
                                String cost=objectSherkat.getString("cost");

                                String customer="";
//                                if (customers != null) {
//                                    JSONArray customers=objectSherkat.getJSONArray("customers");
//                                    StringBuilder sb = new StringBuilder();
//                                    for (int j = 0; j < customers.length(); j++) {
//                                        Log.i("ToolCustom", customers.length() + "-" + j);
//                                        sb.append(customers.getString(j));
//                                        sb.append(",");
//                                    }
//                                    sb.deleteCharAt(sb.length() - 1);
//
//                                    customer = sb.toString();
//                                }else {
//                                    customer="هیچ کدام";
//                                }

                                Log.i("Titel sherkat ", title);

                                list.add(new M_Home("Sherkat_gheymat",id_kala,null,Id,title,"http://satrapp.ir"+image,rate_f,state,price,last,pack,unit,minimum,customer,cost,false));
                            }



                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                        if (list.size()==0){
                            list.add(new M_Home(null,null,null,null,null,null,null,null,null));
                            back_sherkat_gheymat.onSuccess(list);
                        }else {
                            back_sherkat_gheymat.onSuccess(list);
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        back_sherkat_gheymat.onfailor(error.toString());
                    }
                }
        );


        App.getInstance().addToRequestQueue(RQ_sherkat,"Sherkat_gheymat");

    }





    }




