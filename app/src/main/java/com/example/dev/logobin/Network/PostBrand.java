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

public class PostBrand {

    private Context context;

    public PostBrand(Context context) {
        this.context = context;
    }


    public void Post_Rate_Brand(final String user_id, final String brand_id, final String rate){
        String Url_Rate="http://satrapp.ir/api/brandrate";

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
                params.put("brand_id", brand_id);
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
                Log.i("Rate_Brand", headers + "");
                return headers;
            }
        };

        App.getInstance().addToRequestQueue(post_Rate,"Rate_Brand");


    }

}
