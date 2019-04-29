package com.example.dev.logobin.Network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dev.logobin.App;
import com.example.dev.logobin.handel.User_Data;
import com.example.dev.logobin.model.M_Home;
import com.example.dev.logobin.model.M_Kala;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostSearch {


    private Context context ;

    public PostSearch(Context context) {
        this.context = context;
    }


    public interface response_search{
        void result(ArrayList<M_Kala> m_kala,ArrayList<M_Home> m_brand);
        void Faild(String error);
    }


    public void Search (final String titel, final response_search response_search){
        final User_Data user_data= new User_Data(context);

        String Url_serch="http://satrapp.ir/api/search";
        final ArrayList<M_Kala> listkala=new ArrayList<>();
        final ArrayList<M_Home> listbrand=new ArrayList<>();
        StringRequest serch=new StringRequest(Request.Method.POST, Url_serch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null){
                    try {
                        JSONObject kala=new JSONObject(response);
                        Log.i("Search_response",kala.toString());
                        JSONObject data=kala.getJSONObject("data");
                        Log.i("Search",data.toString());
                        JSONArray Kala=data.getJSONArray("results");
                        for (int i = 0 ; i<Kala.length() ; i++){
                            JSONObject kalas=Kala.getJSONObject(i);

                            String id =kalas.getString("id");
                            String title =kalas.getString("title");
                            String image =kalas.getString("image");
                            String factory_id =kalas.getString("hascompany");
                            listkala.add(new M_Kala(id,title,null,null,null,"http://satrapp.ir"+image,factory_id,null,null,null));
                        }
                        JSONArray Brand=data.getJSONArray("brands");
                        for (int j=0 ; j<Brand.length() ;j++){
                            JSONObject Brands=Brand.getJSONObject(j);
                            String id =Brands.getString("id");
                            String title =Brands.getString("title");
                            String image =Brands.getString("image");
//                            String part_id =Brands.getString("part_id");
                            listbrand.add(new M_Home("brand","Search",null,id,title,"http://satrapp.ir"+image,"0",null,null));
                        }

                        response_search.result(listkala,listbrand);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Search_error",error.toString());
                response_search.Faild(error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> map=new HashMap<>();

                map.put("title",titel);
                map.put("area",user_data.getMahale());

                Log.i("Search_map",map.toString());

                return map;
            }
        };

        App.getInstance().addToRequestQueue(serch,"Search");


    }

}
