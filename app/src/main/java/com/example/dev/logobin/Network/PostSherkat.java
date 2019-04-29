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

import java.util.HashMap;
import java.util.Map;

public class PostSherkat {

    private Context context;


    public PostSherkat(Context context) {
        this.context = context;
    }


    public void Post_Rate_Sherkat(final String user_id, final String group_id, final String rate){
        String Url_Rate="http://satrapp.ir/api/companyrate";

        StringRequest post_Rate =new StringRequest(Request.Method.POST, Url_Rate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("rate_response", response + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("rate_error", error + "");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("user_id", user_id);
                params.put("group_id", group_id);
                params.put("rate", rate);

                Log.i("rate_parms", params + "");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                User_Data user_data=new User_Data(context);
                headers.put("Authorization", "Bearer " + user_data.getToken());
                headers.put("Accept", "application/json");
                Log.i("Rate_sherkat", headers + "");
                return headers;
            }
        };

        App.getInstance().addToRequestQueue(post_Rate,"Rate_sherkat");


    }

    public void Post_Seen_Sherkat(final String user_id, final String group_id){
        String Url_visit="http://satrapp.ir/api/visit";

        StringRequest post_Rate =new StringRequest(Request.Method.POST, Url_visit , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("visit_response", response + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("visit_error", error + "");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("user_id", user_id);
                params.put("group_id", group_id);

                Log.i("visit_parms", params + "");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                User_Data user_data=new User_Data(context);
                headers.put("Authorization", "Bearer " + user_data.getToken());
                headers.put("Accept", "application/json");
                Log.i("visit_Headers", headers + "");
                return headers;
            }
        };

        App.getInstance().addToRequestQueue(post_Rate,"visit_sherkat");


    }

    public void Post_Darkhast_Sherkat(final String user_id, final String group_id){
        String Url_call="http://satrapp.ir/api/call";

        StringRequest post_call =new StringRequest(Request.Method.POST, Url_call , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("call_response", response + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("call_error", error + "");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("user_id", user_id);
                params.put("group_id", group_id);

                Log.i("call_parms", params + "");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                User_Data user_data=new User_Data(context);
                headers.put("Authorization", "Bearer " + user_data.getToken());
                headers.put("Accept", "application/json");
                Log.i("call_Headers", headers + "");
                return headers;
            }
        };

        App.getInstance().addToRequestQueue(post_call,"call_sherkat");


    }
}
