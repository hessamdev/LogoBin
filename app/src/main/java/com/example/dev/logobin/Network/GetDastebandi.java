package com.example.dev.logobin.Network;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import com.example.dev.logobin.handel.Create_Alert;
import com.example.dev.logobin.handel.DB_Brand;
import com.example.dev.logobin.handel.DB_DasteBandi;
import com.example.dev.logobin.handel.DB_ZirDaste;
import com.example.dev.logobin.model.Dastebandi;
import com.example.dev.logobin.model.Dastebandi_zirdaste;
import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_ZirDaste;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetDastebandi {

    private String Url_Dastebandi="http://satrapp.ir/api/lists";

    private Context context;





    public GetDastebandi(Context context) {
        this.context = context;
    }



    public ArrayList<M_ZirDaste> zirdastebrand(String id_Brand, final callback callback){
        String Url_ZirDasteBrand="http://satrapp.ir/api/brand/"+id_Brand;
        final ArrayList<M_ZirDaste> list=new ArrayList<>();

        JsonObjectRequest Dastebandi = new JsonObjectRequest(Request.Method.GET, Url_ZirDasteBrand, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray Daste=response.getJSONArray("data");

                            for (int i =0 ;i<Daste.length();i++) {
                                JSONObject Full=Daste.getJSONObject(i);
                                JSONObject Zirgroup=Full.getJSONObject("sublist");
                                String id = Zirgroup.getString("id");
                                String title = Zirgroup.getString("title");
                                String list_id = Zirgroup.getString("list_id");
                                list.add(new M_ZirDaste(id,title,list_id));



                            }

                            callback.onsuccess(list);


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


        App.getInstance().addToRequestQueue(Dastebandi,"Dastebandi");

        return list;
    }

    public ArrayList<M_ZirDaste> zirdasteSherkat(String Id_Sherkat, final callback callback, final DataSherkat dataSherkat){
        String Url_ZirDasteSherkat="http://satrapp.ir/api/company/product/"+Id_Sherkat;
        final ArrayList<M_ZirDaste> list=new ArrayList<>();
        final Create_Alert create_alert= new Create_Alert(context);
        final Dialog dialog= create_alert.createAlert_Load();
        dialog.show();


        JsonObjectRequest Dastebandi = new JsonObjectRequest(Request.Method.GET, Url_ZirDasteSherkat, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONObject DATA=response.getJSONObject("data");
                            JSONObject Sherkat=DATA.getJSONObject("company");
                            dataSherkat.Datasherkat(Sherkat.getString("today"),Sherkat.getString("yesterday"),Sherkat.getString("group_image"),Sherkat.getString("title"),Sherkat.getString("rate"));

                            JSONArray Zirgroup=DATA.getJSONArray("sublist");

                            for (int i =0 ;i<Zirgroup.length();i++) {

                                JSONObject Daste=Zirgroup.getJSONObject(i);
                                String id = Daste.getString("id");
                                String title = Daste.getString("title");
                                String list_id = Daste.getString("list_id");
                                list.add(new M_ZirDaste(id,title,list_id));

                            }

                            callback.onsuccess(list);
                            dialog.dismiss();


                        }catch (JSONException e){
                            e.printStackTrace();
                            dialog.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                callback.faild(error.toString());
                Toast.makeText(context, "API_Zir Daste sherkat erorr", Toast.LENGTH_SHORT).show();

            }
        });


        App.getInstance().addToRequestQueue(Dastebandi,"Dastebandi");

        return list;
    }


    public void DasteBandiFull(final String Id_part, final back_daste_full back_daste_full){
        String Url_Daste_Full="http://satrapp.ir/api/parts";

        final ArrayList<M_ZirDaste> FullList=new ArrayList<>();
        final ArrayList<M_ZirDaste> expend_list=new ArrayList<>();

        JsonObjectRequest Dastebandi_full = new JsonObjectRequest(Request.Method.GET, Url_Daste_Full, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray data=response.getJSONArray("data");

                            for (int i =0 ;i<data.length();i++) {

                                JSONObject part=data.getJSONObject(i);
                                String id = part.getString("id");
                                String title = part.getString("title");

                                if (Id_part.equals(id)) {

                                    JSONArray list = part.getJSONArray("lists");
                                    for (int j = 0; j < list.length(); j++) {
                                        JSONObject lists = list.getJSONObject(j);

                                        String id_lists = lists.getString("id");
                                        String title_lists = lists.getString("title");

                                        JSONArray sublist = lists.getJSONArray("sublists");
                                        ArrayList<M_ZirDaste> sub = new ArrayList<>();
                                        for (int k = 0; k < sublist.length(); k++) {
                                            JSONObject sublists = sublist.getJSONObject(k);

                                            String id_sublists = sublists.getString("id");
                                            String title_sublists = sublists.getString("title");
                                            sub.add(new M_ZirDaste(id_sublists,title_sublists,null));
                                            FullList.add(new M_ZirDaste(id_sublists,title_sublists,null));

                                        }
                                        expend_list.add(new M_ZirDaste(title_lists,false, sub));


                                    }
                                    back_daste_full.onsuccess(expend_list,FullList);
                                }

                            }




                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "API_Zir Daste sherkat erorr", Toast.LENGTH_SHORT).show();

            }
        });


        App.getInstance().addToRequestQueue(Dastebandi_full,"Dastebandi_full");


    }

    public void Daste_part(final callback api_back){
        String Url_Daste_Full="http://satrapp.ir/api/parts";

        final ArrayList<M_ZirDaste> partList=new ArrayList<>();


        JsonObjectRequest Dastebandi_full = new JsonObjectRequest(Request.Method.GET, Url_Daste_Full, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray data=response.getJSONArray("data");

                            for (int i =0 ;i<data.length();i++) {

                                JSONObject part=data.getJSONObject(i);
                                String id = part.getString("id");
                                String title = part.getString("title");
                                partList.add(new M_ZirDaste(id,"دسته "+title,null));
                            }

                            api_back.onsuccess(partList);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "API_Zir Daste  erorr", Toast.LENGTH_SHORT).show();
                api_back.faild(error.toString());
            }
        });


        App.getInstance().addToRequestQueue(Dastebandi_full,"part_daste");



    }

    public interface back_daste_full{
        void onsuccess(ArrayList<M_ZirDaste> ListAdapter,ArrayList<M_ZirDaste> full_list);
        void faild(String eror);
    }

    public interface callback{
        void onsuccess(ArrayList<M_ZirDaste> listss);
        void faild(String eror);
    }

    public interface DataSherkat{
        void Datasherkat(String seen_toDay,String Seen_YesterDay,String image,String title,String rate);
    }



}


