package com.example.dev.logobin.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dev.logobin.R;
import com.example.dev.logobin.activity.Getdata;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Create_Alert;
import com.example.dev.logobin.handel.User_Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu_Register_view extends FragmentView {

    private TextView ostan, shahr, mahale;
    private EditText phonenumber, username;

    private RequestQueue location;

    private ArrayList<Location> list_Ostan, list_Shahr, list_Mahale;

    private String id_OStan = "", id_Shahr = "", id_Mahale = "", phone, user;


    int intostan = 0, intshahr = 1, intmahale = 2;

    private User_Data user_data;
    private boolean acc = false,refresh = false;
    private View view;
    private LinearLayout layout_ghavanin;
    private  TextView delete,sinup,ghavanin;
    private ImageView Back,accept;

    private LinearLayout layoutview;

    private static String url = "http://satrapp.ir/api/register";
    private static String url_virayesh = "http://satrapp.ir/api/useredit";


    @Override
    public void OnCreate() {

        user_data = new User_Data(Activity);

        if (Tag.equals("Virayesh")) {
            view = LayoutInflater.from(Activity).inflate(R.layout.register_ui_virayesh, null);
            init(view);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Create_Alert create_alert = new Create_Alert(Activity);
                    create_alert.createAlert_Deleteuser(new Create_Alert.DeleteAcc() {
                        @Override
                        public void Delete(Boolean yes) {
                            if (yes) {
                                user_data.Delete();
                                Intent intent = new Intent(Activity, Getdata.class);
                                Activity.startActivity(intent);
                                Activity.finish();
                            }
                        }
                    });

                }
            });

//            layout_ghavanin.setVisibility(View.GONE);
        } else {
            view = LayoutInflater.from(Activity).inflate(R.layout.register_ui2, null);
//            delete.setVisibility(View.GONE);
            init(view);
            phonenumber.setFocusableInTouchMode(true);
            phonenumber.setFocusable(true);
            phonenumber.requestFocus();
            Back.setVisibility(View.GONE);
        }







        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.onBackPressed();
            }
        });

        accept.setImageResource(R.drawable.oval);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (acc) {
                    accept.setImageResource(R.drawable.oval);
                    acc = false;
                } else {
                    accept.setImageResource(R.drawable.verified_acc);
                    acc = true;
                }
            }
        });


        if (user_data.getDatauser().get(0).getPhone() == null) {
//            Toast.makeText(Activity, "Null hast", Toast.LENGTH_SHORT).show();
//            delete.setVisibility(View.GONE);
        } else {
//            Toast.makeText(Activity, "Null Nist = " + user_data.getDatauser().get(0).getPhone(), Toast.LENGTH_SHORT).show();
            phonenumber.setText(user_data.getDatauser().get(0).getPhone());
            phonenumber.setFocusable(false);
            phonenumber.setFocusableInTouchMode(false);
            phonenumber.setClickable(false);
            phonenumber.setTextColor(Activity.getResources().getColor(R.color.c300));
            username.setText(user_data.getDatauser().get(0).getUsername());
            username.setTextColor(Activity.getResources().getColor(R.color.c500));
            ostan.setText(user_data.getDatauser().get(0).getOstan());
            ostan.setTextColor(Activity.getResources().getColor(R.color.c500));
            id_OStan = user_data.getDatauser().get(0).getId_ostan();
            shahr.setText(user_data.getDatauser().get(0).getShahr());
            shahr.setTextColor(Activity.getResources().getColor(R.color.c500));
            id_Shahr = user_data.getDatauser().get(0).getId_shahr();
            mahale.setText(user_data.getDatauser().get(0).getMahale());
            mahale.setTextColor(Activity.getResources().getColor(R.color.c500));
            id_Mahale = user_data.getDatauser().get(0).getId_mahale();
        }

        location = Volley.newRequestQueue(Activity);


        list_Ostan = new ArrayList<>();
        list_Shahr = new ArrayList<>();
        list_Mahale = new ArrayList<>();
        location();


        ostan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_AlertDialog(list_Ostan, intostan);
            }
        });

        shahr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!id_OStan.equals("")) {
                    create_AlertDialog(list(list_Shahr, id_OStan, intshahr), intshahr);
                } else {
                    Toast.makeText(Activity, "Ostan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mahale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!id_Shahr.equals("")) {
                    create_AlertDialog(list(list_Mahale, id_Shahr, intmahale), intmahale);
                } else {
                    Toast.makeText(Activity, "shahr", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ghavanin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.GetManager().OpenView(new Menu_Ghavanin_View(), "Menu_Ghavanin_View", true);
            }
        });


        sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = phonenumber.getText().toString();
//                int number = Integer.parseInt(phone);
//                String num=Integer.toString(number);
                user = username.getText().toString();

                if (
                        phone.length() == 11 &&
                                phone.substring(0, 2).equals("09") &&
                                user.length() > 2 && user.length() < 30 &&
                                !ostan.getText().toString().equals("استان") &&
                                !mahale.getText().toString().equals("محله") &&
                                !shahr.getText().toString().equals("شهر")
                        ) {

                    if (Tag.equals("Virayesh")) {

                        if (!mahale.getText().toString().equals(user_data.getDatauser().get(0).getMahale())){
                            Virayesh(url_virayesh, phone, user);
                            refresh=true;
                        }else {
                            Virayesh(url_virayesh, phone, user);
                        }

                    } else if (!acc) {
                        Toast.makeText(Activity, "مقررات را تایید کنید", Toast.LENGTH_SHORT).show();
                    }else {
                        Register(url, phone, user);

                    }





                } else {
                    Toast.makeText(Activity, "اطلاعات ثبتی کامل و یا صحیح نیست...", Toast.LENGTH_SHORT).show();
                }
            }
        });




        ViewMain = view;
    }

    private void init(View view){
        layoutview=(LinearLayout) view.findViewById(R.id.Register_Linear_view);
        sinup = (TextView) view.findViewById(R.id.TextView_Register_sinup);
        delete = (TextView) view.findViewById(R.id.Register_Textview_Removeacc);
        accept = (ImageView) view.findViewById(R.id.register_imageview_verfired);
        Back = (ImageView) view.findViewById(R.id.Register_Imageview_Back);
        layout_ghavanin = (LinearLayout) view.findViewById(R.id.Register_Linear_Ghavanin);
        phonenumber = (EditText) view.findViewById(R.id.Register_Edittext_Phone);
        username = (EditText) view.findViewById(R.id.Register_Edittext_username);
        ostan = (TextView) view.findViewById(R.id.Register_Textview_Ostan);
        shahr = (TextView) view.findViewById(R.id.Register_Textview_shahr);
        mahale = (TextView) view.findViewById(R.id.Register_Textview_Mahale);
        ghavanin = (TextView) view.findViewById(R.id.Register_Textview_Ghavanin);

    }


    private void Register(String url, final String phone, final String user) {
        RequestQueue contact = Volley.newRequestQueue(Activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Register_Log respons", response);

                if (!response.isEmpty()) {

                    try {
                        JSONObject object = new JSONObject(response);
                        JSONObject Id = object.getJSONObject("success");
                        String User_Id = Id.getString("id");
                        String token = Id.getString("token");
                        user_data.setData(phone, user, ostan.getText().toString(), id_OStan, shahr.getText().toString(), id_Shahr, mahale.getText().toString(), id_Mahale, User_Id, token);

                        if (Tag.equals("Virayesh")) {
                            Activity.onBackPressed();
                            Toast.makeText(Activity, "اطلاعات شما ویرایش شد.", Toast.LENGTH_SHORT).show();
                        } else {
                            Activity.finish();
                            Intent intent = new Intent(Activity, Getdata.class);
                            Activity.startActivity(intent);
                            Toast.makeText(Activity, "اطلاعات شما ثبت شد.", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Activity, "مشکل در اتصال به سرور !", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Register_EROOOR", error.toString());
                Toast.makeText(Activity, "مشکل در اتصال به سرور !", Toast.LENGTH_SHORT).show();

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile", phone);
                params.put("name", user);
                params.put("state_id", id_OStan);
                params.put("city_id", id_Shahr);
                params.put("area_id", id_Mahale);

                Log.i("Register", params + "");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
//        stringRequest.setTag(REQ_TAG);
        Log.i("Register_request", stringRequest.toString());
        contact.add(stringRequest);


    }

    private void Virayesh(String url, final String phone, final String user) {
        RequestQueue contact = Volley.newRequestQueue(Activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Register_Log respons", response);

                if (!response.isEmpty()) {

                    try {
                        JSONObject object = new JSONObject(response);
                        JSONObject Id = object.getJSONObject("success");
                        String User_Id = Id.getString("id");
//
                        user_data.setData(phone, user, ostan.getText().toString(), id_OStan, shahr.getText().toString(), id_Shahr, mahale.getText().toString(), id_Mahale, User_Id, user_data.getToken());

                        if (Tag.equals("Virayesh")) {
                            if (refresh){
                                Activity.finish();
                                Intent intent = new Intent(Activity, Getdata.class);
                                Activity.startActivity(intent);
                            }else {
                                Activity.onBackPressed();
                            }
                        } else {
                            Activity.finish();
                            Intent intent = new Intent(Activity, Getdata.class);
                            Activity.startActivity(intent);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Register_EROOOR", error.toString());

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("mobile", phone);
                params.put("name", user);
                params.put("state_id", id_OStan);
                params.put("city_id", id_Shahr);
                params.put("area_id", id_Mahale);

                Log.i("Register", params + "");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + user_data.getToken());
                headers.put("Accept", "application/json");
                Log.i("Register_Edite", headers + "");
                return headers;

            }

        };
//        stringRequest.setTag(REQ_TAG);
        Log.i("Register_request", stringRequest.toString());
        contact.add(stringRequest);


    }

    private void location() {
        layoutview.setVisibility(View.GONE);
        Create_Alert create_alert= new Create_Alert(Activity);

        final Dialog dialog=create_alert.createAlert_Load();
        dialog.show();
        String Url_location = "http://satrapp.ir/api/location";
        JsonObjectRequest json_Location = new JsonObjectRequest(Request.Method.GET, Url_location, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray All = response.getJSONArray("data");

                            Log.i("Info data ", All.toString());


                            for (int i = 0; i < All.length(); i++) {
                                JSONObject ostan = All.getJSONObject(i);
                                String id = ostan.getString("id");
                                String title = ostan.getString("title");

                                list_Ostan.add(new Location(id, null, title, null, null));

                                JSONArray city = ostan.getJSONArray("cities");
                                for (int j = 0; j < city.length(); j++) {
                                    JSONObject ccity = city.getJSONObject(j);
                                    String idcity = ccity.getString("id");
                                    String idostan = ccity.getString("state_id");
                                    String namecity = ccity.getString("title");
                                    list_Shahr.add(new Location(idcity, idostan, null, namecity, null));
                                    JSONArray M = ccity.getJSONArray("areas");
                                    for (int m = 0; m < M.length(); m++) {
                                        JSONObject Mahale = M.getJSONObject(m);
                                        String idmahale = Mahale.getString("id");
                                        String idShahr = Mahale.getString("city_id");
                                        String nameMahale = Mahale.getString("title");
                                        list_Mahale.add(new Location(idmahale, idShahr, null, null, nameMahale));

                                    }


                                }

                            }
                            layoutview.setVisibility(View.VISIBLE);
                            dialog.dismiss();



                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialog.dismiss();
                            Toast.makeText(Activity, "مشکل اتصال به سرور ...", Toast.LENGTH_SHORT).show();
                            Activity.onBackPressed();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(Activity, "مشکل اتصال به سرور ...", Toast.LENGTH_SHORT).show();
                Activity.onBackPressed();
            }
        }

        );

        location.add(json_Location);

    }

    public void create_AlertDialog(final ArrayList<Location> list, final int type) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity);
        final AlertDialog dialog = alertDialog.create();
        View convertview = LayoutInflater.from(Activity).inflate(R.layout.z_m_alert_adress, null);
        dialog.setView(convertview);
        final RecyclerView recyclerView = (RecyclerView) convertview.findViewById(R.id.z_m_Recycler_alert);
        EditText search = (EditText) convertview.findViewById(R.id.z_m_alertAdres_Edittext_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(Activity, DividerItemDecoration.VERTICAL));
        final Adapter_Location adapter_location = new Adapter_Location(list, type, new Adapter_Location.OnItemClickListener() {
            @Override
            public void onItemClick(Location model) {

                switch (type) {
                    case 0:
                        dialog.setTitle("استان");
//                        Toast.makeText(Activity, "" + model.getId() + "listshahr = " + (list(list_Shahr, model.getId(), intshahr)).size(), Toast.LENGTH_SHORT).show();
                        Log.i("id  -shahr size - shahr", model.getId() + " - " + (list(list_Shahr, model.getId(), intshahr)).size() + " - " + model.getShahr());
                        ostan.setText(model.getOstan());
                        shahr.setText("شهر");
                        mahale.setText("محله");
                        id_OStan = model.getId();
                        create_AlertDialog(list(list_Shahr, model.getId(), intshahr), intshahr);
                        dialog.dismiss();
                        break;
                    case 1:
                        dialog.setTitle("شهر");
//                        Toast.makeText(Activity, model.getShahr() + "  Salam" + model.getId(), Toast.LENGTH_SHORT).show();
                        shahr.setText(model.getShahr());
                        mahale.setText("محله");
                        id_Shahr = model.getId();
                        create_AlertDialog(list(list_Mahale, model.getId(), intmahale), intmahale);
                        dialog.dismiss();
                        break;
                    case 2:
                        dialog.setTitle("محله");
                        id_Mahale = model.getId();
//                        Toast.makeText(Activity, "Mahale id =" + model.getId(), Toast.LENGTH_SHORT).show();
                        mahale.setText(model.getMahale());
                        dialog.dismiss();
                        break;
                }

            }
        });
        recyclerView.setAdapter(adapter_location);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter_location.getFilter().filter(s);
            }


            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<Location> filter = new ArrayList<>();

                Adapter_Location adapterfilter = new Adapter_Location(filter, type, new Adapter_Location.OnItemClickListener() {
                    @Override
                    public void onItemClick(Location model) {

                        switch (type) {
                            case 0:
                                dialog.setTitle("استان");
                                Toast.makeText(Activity, "" + model.getId() + "listshahr = " + (list(list_Shahr, model.getId(), intshahr)).size(), Toast.LENGTH_SHORT).show();
                                Log.i("id  -shahr size - shahr", model.getId() + " - " + (list(list_Shahr, model.getId(), intshahr)).size() + " - " + model.getShahr());
                                ostan.setText(model.getOstan());
                                shahr.setText("شهر");
                                mahale.setText("محله");
                                id_OStan = model.getId();
                                create_AlertDialog(list(list_Shahr, model.getId(), intshahr), intshahr);
                                dialog.dismiss();
                                break;
                            case 1:
                                dialog.setTitle("شهر");
                                Toast.makeText(Activity, model.getShahr() + "  Salam" + model.getId(), Toast.LENGTH_SHORT).show();
                                shahr.setText(model.getShahr());
                                mahale.setText("محله");
                                id_Shahr = model.getId();
                                create_AlertDialog(list(list_Mahale, model.getId(), intmahale), intmahale);
                                dialog.dismiss();
                                break;
                            case 2:
                                dialog.setTitle("محله");
                                id_Mahale = model.getId();
                                Toast.makeText(Activity, "Mahale id =" + model.getId(), Toast.LENGTH_SHORT).show();
                                mahale.setText(model.getMahale());
                                dialog.dismiss();
                                break;
                        }
                    }
                });

                recyclerView.setAdapter(adapterfilter);

                if (type == 0) {
                    for (Location location : list) {

                        if (location.getOstan().contains(s)) {
                            filter.add(location);
                        }
                    }
                    adapterfilter.notifyDataSetChanged();
                }
                if (type == 1) {
                    for (Location location : list) {
                        if (location.getShahr().contains(s)
                                ) {
                            filter.add(location);
                        }
                    }
                    adapterfilter.notifyDataSetChanged();
                }
                if (type == 2) {
                    for (Location location : list) {
                        if (location.getMahale().contains(s)
                                ) {
                            filter.add(location);
                        }
                    }
                    adapterfilter.notifyDataSetChanged();
                }
            }
        });

        dialog.show();
    }


    private ArrayList<Location> list(ArrayList<Location> fulllist, String id, int type) {
        ArrayList<Location> lists = new ArrayList<>();
        if (type == intshahr) {
            for (int i = 0; i < fulllist.size(); i++) {

                if (fulllist.get(i).getMainid().equals(id)) {
                    Log.i("i ", i + " - titel =" + fulllist.get(i).getShahr() + "id" + fulllist.get(i).id + "main = " + fulllist.get(i).mainid);
                    lists.add(new Location(
                            fulllist.get(i).id,
                            fulllist.get(i).mainid,
                            null,
                            fulllist.get(i).shahr,
                            null
                    ));
                }
            }
        }
        if (type == intmahale) {
            for (int i = 0; i < fulllist.size(); i++) {

                if (fulllist.get(i).getMainid().equals(id)) {
                    Log.i("i ", i + " - titel =" + fulllist.get(i).getShahr() + "id" + fulllist.get(i).id + "main = " + fulllist.get(i).mainid);
                    lists.add(new Location(
                            fulllist.get(i).id,
                            fulllist.get(i).mainid,
                            null,
                            null,
                            fulllist.get(i).getMahale()
                    ));
                }
            }
        }

        return lists;
    }

}


class Location {

    String id, mainid, ostan, shahr, mahale;

    public Location(String id, String mainid, String ostan, String shahr, String mahale) {
        this.id = id;
        this.mainid = mainid;
        this.ostan = ostan;
        this.shahr = shahr;
        this.mahale = mahale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    public String getOstan() {
        return ostan;
    }

    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    public String getShahr() {
        return shahr;
    }

    public void setShahr(String shahr) {
        this.shahr = shahr;
    }

    public String getMahale() {
        return mahale;
    }

    public void setMahale(String mahale) {
        this.mahale = mahale;
    }
}

class Adapter_Location extends RecyclerView.Adapter<Adapter_Location.ViewHolder> implements Filterable {

    private ArrayList<Location> list;
    private ArrayList<Location> filter_list;
    private int type;
    private OnItemClickListener clickListener;


    public Adapter_Location(ArrayList<Location> list, int type, OnItemClickListener clickListener) {
        this.list = list;
        this.filter_list = list;
        this.type = type;
        this.clickListener = clickListener;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charstring = constraint.toString();

                if (charstring.isEmpty()) {
                    filter_list = list;
                } else {
                    ArrayList<Location> filteredlist = new ArrayList<>();
                    for (Location row : list) {
                        Log.i("Search", charstring);
                        if (!row.getOstan().equals("")) {
                            Log.i("Ostan", "yes");
                            if (row.getOstan().contains(charstring)) {
                                filteredlist.add(row);
                            }
                        }
                        if (row.getShahr() != null) {
                            if (row.getShahr().contains(charstring)) {
                                filteredlist.add(row);
                            }
                        }
                        if (row.getMahale() != null) {
                            if (row.getMahale().contains(charstring)) {
                                filteredlist.add(row);
                            }
                        }

                    }
                    filter_list = filteredlist;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filter_list;


                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filter_list = (ArrayList<Location>) results.values;
                Log.i("notify", "yes");
                notifyDataSetChanged();
            }
        };
    }

    public interface OnItemClickListener {
        void onItemClick(Location model);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_recycler_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Location location = list.get(position);

        switch (type) {
            case 0:
                holder.titel.setText(location.getOstan());
                break;
            case 1:
                holder.titel.setText(location.getShahr());
                break;
            case 2:
                holder.titel.setText(location.getMahale());
                break;
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(location);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titel;

        public ViewHolder(View itemView) {
            super(itemView);
            titel = (TextView) itemView.findViewById(R.id.z_m_textview_location);
        }
    }

}


