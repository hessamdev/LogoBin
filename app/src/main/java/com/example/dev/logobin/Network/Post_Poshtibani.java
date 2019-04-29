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

public class Post_Poshtibani {

    private Context context;

    public Post_Poshtibani(Context context) {
        this.context = context;
    }

    public void Post_Poshtibanimap(final Map<String , String> mapPoshtibani){
        String Url_call="http://satrapp.ir/api/support";

        StringRequest post_poshtibani =new StringRequest(Request.Method.POST, Url_call , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("poshtibani_response", response + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("poshtibani_error", error + "");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params=mapPoshtibani;
//                params.put("user_id", user_id);
//                params.put("group_id", Email);

                Log.i("poshtibani_parms", params + "");

                return params;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                User_Data user_data=new User_Data(context);
//                headers.put("Authorization", "Bearer " + user_data.getToken());
//                headers.put("Accept", "application/json");
//                Log.i("poshtibani_Headers", headers + "");
//                return headers;
//            }
        };

        App.getInstance().addToRequestQueue(post_poshtibani,"poshtibani_Darkhast");


    }

}
