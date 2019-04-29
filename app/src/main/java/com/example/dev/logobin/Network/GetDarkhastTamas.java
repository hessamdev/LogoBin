package com.example.dev.logobin.Network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dev.logobin.App;
import com.example.dev.logobin.model.Model_Gozaresh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetDarkhastTamas {

    private Context context ;

    private ArrayList<Model_Gozaresh> listgozaresh;


    public GetDarkhastTamas(Context context) {
        this.context = context;
    }

    public interface Back_Darkhast{
        void ok(ArrayList<Model_Gozaresh> lst_gozaresh);
        void faild(String Error);
    }


    public void GetListDarkhast(String userId, final String Token, final Back_Darkhast back_darkhast){
        String Url_call="http://satrapp.ir/api/calls/"+userId;
        listgozaresh=new ArrayList<>();

        JsonObjectRequest getlist =new JsonObjectRequest(Request.Method.GET, Url_call, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("List darkhast", response.toString());
                        try {
                            JSONArray Data=response.getJSONArray("data");
                            for (int i = 0 ; i<Data.length(); i++){
                                JSONObject Tamin=Data.getJSONObject(i);
                                String created_at=Tamin.getString("time");
                                String updated_at=Tamin.getString("time");
                                String rate=Tamin.getString("rate");
                                String group_id=Tamin.getString("group_id");
                                JSONObject tamin=Tamin.getJSONObject("group");
                                String id=tamin.getString("id");
                                String title=tamin.getString("title");
                                String group_image="http://satrapp.ir"+tamin.getString("group_image");
                                listgozaresh.add(new Model_Gozaresh(group_image,created_at,updated_at,title,rate,group_id));
                            }
                            back_darkhast.ok(listgozaresh);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            back_darkhast.faild(e.toString());
//                            dialog.dismiss();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("List darkhast", error.toString());
                back_darkhast.faild(error.toString());
//                dialog.dismiss();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("Authorization", "Bearer " + Token);
                headers.put("Accept", "application/json");
                Log.i("call_Headers", headers + "");
                return headers;
            }
        };
        App.getInstance().addToRequestQueue(getlist,"List_Darkhast_Tamas");

    }

}
