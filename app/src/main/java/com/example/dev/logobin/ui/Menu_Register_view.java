package com.example.dev.logobin.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dev.logobin.R;
import com.example.dev.logobin.RecyclerApp.RecyclerKala;
import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.DB_Helper;
import com.example.dev.logobin.model.M_Kala;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu_Register_view extends FragmentView {

    private TextView ostan, shahr, mahale, remember, done;
    private Button remove;
    private EditText phonenumber, username, family, adres;
    private DB_Helper db_helper;

    private String phone;
    private String user;

    String data="";
    RequestQueue location;


    List<String> AOstan;
    List<String> AShahr;
    List<String> AMahale;

    ArrayList<MDL_Register> Lst_Mahale;
    ArrayList<MDL_Register> Lst_shahr;
    ArrayList<MDL_Register> Lst_Ostan;

    ArrayList<Location> list_Ostan;
    ArrayList<Location> list_Shahr;
    ArrayList<Location> list_Mahale;

    String id_OStan;
    String id_Shahr;
    String id_Mahale;

    int intostan=0,intshahr=1,intmahale=2;


    private static String url = "http://satrapp.ir/api/register";


    @Override
    public void OnCreate() {
        View view = LayoutInflater.from(Activity).inflate(R.layout.register_ui2, null);
        location=Volley.newRequestQueue(Activity);

        list_Ostan=new ArrayList<>();
        list_Shahr=new ArrayList<>();
        list_Mahale=new ArrayList<>();


        ImageView sinup = (ImageView) view.findViewById(R.id.Imageview_Register_sinup);
        phonenumber = (EditText) view.findViewById(R.id.Register_Edittext_Phone);
        username = (EditText) view.findViewById(R.id.Register_Edittext_username);
        ostan=(TextView) view.findViewById(R.id.Register_Textview_Ostan);
        shahr=(TextView)view.findViewById(R.id.Register_Textview_shahr);
        mahale=(TextView)view.findViewById(R.id.Register_Textview_Mahale);
        TextView ghavanin=(TextView)view.findViewById(R.id.Register_Textview_Ghavanin) ;


        ghavanin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity.GetManager().OpenView(new Menu_Ghavanin_View(),"Menu_Ghavanin_View",true);
            }
        });

        location();


        sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = phonenumber.getText().toString();
//                int number = Integer.parseInt(phone);
//                String num=Integer.toString(number);
                user = username.getText().toString();

                if (
                        phone.length()==11 &&
                        phone.substring(0,2).equals("09") &&
                        user.length()>2 && user.length()<20  &&
                                !ostan.getText().toString().equals("استان")&&
                                !shahr.getText().toString().equals("شهر")
                        ){
                    Toast.makeText(Activity,shahr.getText().toString() , Toast.LENGTH_SHORT).show();
                    Toast.makeText(Activity, "True", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(Activity, "False", Toast.LENGTH_SHORT).show();
                }


//                Register(url, phone, user);


//                Intent intent=Activity.getIntent();
//                   Activity.finish();
//                  Activity.startActivity(intent);

            }
        });

//        new GetContacts().execute();
//
//        db_helper=new DB_Helper(Activity);
//        init(view);
//        if (Tag.equals("Register")){
//            Register();
//        }if (Tag.equals("Virayesh")){
//            Virayesh();
//        }

        ViewMain = view;
    }

    private void Register(String url, final String phone, final String user) {
        RequestQueue contact = Volley.newRequestQueue(Activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Log respons",response);

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("EROOOR",error.toString());

            }
        }){
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile", phone);
                params.put("name", user);
                params.put("state_id", id_OStan);
                params.put("city_id", id_Shahr);
//                params.put("area_id", id_Mahale);
                return params;
            };
        };
//        stringRequest.setTag(REQ_TAG);
        Log.i("request",stringRequest.toString());
        contact.add(stringRequest);




    }

        private void location(){
        ostan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_AlertDialog(list_Ostan,intostan);
            }
        });
        shahr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setShahr();
            }
        });
        mahale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMahale();
            }
        });


        String Url_location="http://satrapp.ir/api/location";
        JsonObjectRequest json_Location=new JsonObjectRequest(Request.Method.GET, Url_location, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            JSONArray All = response.getJSONArray("data");

                            Log.i("Info data ",All.toString());

                            Lst_Ostan=new ArrayList<>();
                            Lst_shahr=new ArrayList<>();
                            Lst_Mahale=new ArrayList<>();

                            AOstan=new ArrayList<>();
                            AShahr=new ArrayList<>();
                            AMahale=new ArrayList<>();
                            for (int i = 0; i < All.length(); i++) {
                            JSONObject ostan=All.getJSONObject(i);
                                String id = ostan.getString("id");
                                String title = ostan.getString("title");

                                Lst_Ostan.add(new MDL_Register(id,title));
                                list_Ostan.add(new Location(id,null,title,null,null));

                                JSONArray city =ostan.getJSONArray("cities");
                                for (int j = 0;j<city.length();j++){
                                    JSONObject ccity=city.getJSONObject(j);
                                    String idcity=ccity.getString("id");
                                    String idostan=ccity.getString("state_id");
                                    String namecity=ccity.getString("title");
                                    Lst_shahr.add(new MDL_Register(idcity,idostan,namecity));
                                    list_Shahr.add(new Location(idcity,idostan,null,namecity,null));
                                    JSONArray M=ccity.getJSONArray("areas");
                                    for (int m=0;m < M.length();m++){
                                        JSONObject Mahale=M.getJSONObject(m);
//
                                        String idmahale=Mahale.getString("id");
                                        String idShahr=Mahale.getString("city_id");
                                        String nameMahale=Mahale.getString("title");
                                        list_Mahale.add(new Location(idmahale,idShahr,null,null,nameMahale));
                                        Lst_Mahale.add(new MDL_Register(idmahale,idShahr,nameMahale));
                                    }


                                }

                            }

//
//                                JSONObject c = All.getJSONObject(i)
//
//                                String id = c.getString("id");
//                                String title = c.getString("title");
//
//                                Lst_Ostan.add(new MDL_Register(id,title));
//
//                                JSONArray city =c.getJSONArray("cities");
//                                for (int j = 0; j < city.length(); j++) {
//                                    JSONObject  ccity = city.getJSONObject(j);
//
//                                    String idcity=ccity.getString("id");
//                                    String idostan=ccity.getString("state_id");
//                                    String namecity=ccity.getString("title");
//
//                                    Lst_shahr.add(new MDL_Register(idcity,idostan,namecity));
//
//                                    JSONArray M=ccity.getJSONArray("areas");
//                                    for (int m=0;m < M.length();m++){
//                                        JSONObject Mahale=M.getJSONObject(m);
//
//                                        String idmahale=Mahale.getString("id");
//                                        String idShahr=Mahale.getString("city_id");
//                                        String nameMahale=Mahale.getString("title");
//
//                                        Lst_Mahale.add(new MDL_Register(idmahale,idShahr,nameMahale));
//                                    }
//                                }





//                            for(int i=0; i < ja.length(); i++){
//
//                                JSONObject jsonObject = ja.getJSONObject(i);
//
//                                // int id = Integer.parseInt(jsonObject.optString("id").toString());
//                                String title = jsonObject.getString("title");
//                                String url = jsonObject.getString("URL");
//
//                                data += "Blog Number "+(i+1)+" \n Blog Name= "+title  +" \n URL= "+ url +" \n\n\n\n ";
//                            }
//                            if (jsonStr != null) {
//                        try {
//                            JSONObject jsonObj = new JSONObject(jsonStr);
//
//                            Lst_Ostan=new ArrayList<>();
//                            Lst_shahr=new ArrayList<>();
//                            Lst_Mahale=new ArrayList<>();
//
//
//                            // Getting JSON Array node
//                            JSONArray data = jsonObj.getJSONArray("data");
//                            Log.i( "Response jsonobject: " , data.toString());
//
//
//                            AOstan=new ArrayList<>();
//                            AShahr=new ArrayList<>();
//                            AMahale=new ArrayList<>();
//                            // looping through All Contacts
//                            for (int i = 0; i < data.length(); i++) {
//
//
//                                JSONObject c = data.getJSONObject(i);
//
//                                String id = c.getString("id");
//                                String title = c.getString("title");
//
//                                Lst_Ostan.add(new MDL_Register(id,title));
//
//                                JSONArray city =c.getJSONArray("cities");
//                                for (int j = 0; j < city.length(); j++) {
//                                    JSONObject  ccity = city.getJSONObject(j);
//
//                                    String idcity=ccity.getString("id");
//                                    String idostan=ccity.getString("state_id");
//                                    String namecity=ccity.getString("title");
//
//                                    Lst_shahr.add(new MDL_Register(idcity,idostan,namecity));
//
//                                    JSONArray M=ccity.getJSONArray("areas");
//                                    for (int m=0;m < M.length();m++){
//                                        JSONObject Mahale=M.getJSONObject(m);
//
//                                        String idmahale=Mahale.getString("id");
//                                        String idShahr=Mahale.getString("city_id");
//                                        String nameMahale=Mahale.getString("title");
//
//                                        Lst_Mahale.add(new MDL_Register(idmahale,idShahr,nameMahale));
//                                    }
//                                }
//
//
//
//                                // Phone node is JSON Object
////                                JSONObject phone = c.getJSONObject("phone");
////                                String mobile = phone.getString("mobile");
////                                String home = phone.getString("home");
////                                String office = phone.getString("office");
//
//                            }


                        }catch(JSONException e){e.printStackTrace();}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );

        location.add(json_Location);

    }

    public void create_AlertDialog(final ArrayList<Location> list, final int type){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity);
        final AlertDialog dialog =alertDialog.create();
        View convertview=LayoutInflater.from(Activity).inflate(R.layout.z_m_alert_adress,null);
        dialog.setView(convertview);
        dialog.setTitle("استان");
        RecyclerView recyclerView=(RecyclerView)convertview.findViewById(R.id.z_m_Recycler_alert) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(Activity,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new Adapter(list,type, new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Location model) {

                switch (type){
                    case 0 :
                        Toast.makeText(Activity, "" + model.getId()+"listshahr = "+(list(list_Shahr,model.getId(),intshahr)).size(), Toast.LENGTH_SHORT).show();
                        Log.i("id  -shahr size - shahr",model.getId()+" - "+(list(list_Shahr,model.getId(),intshahr)).size()+" - "+model.getShahr());
                        create_AlertDialog(list(list_Shahr,model.getId(),intshahr),intshahr);
                        dialog.dismiss();
                        break;
                    case 1 :
                        Toast.makeText(Activity, model.getShahr()+"  Salam"+model.getId(), Toast.LENGTH_SHORT).show();
                        create_AlertDialog(list(list_Mahale,model.getId(),intmahale),intmahale);
                        dialog.dismiss();
                        break;
                    case 2 :
                        Toast.makeText(Activity, "Mahale id ="+model.getId(), Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
                        break;
                }

        }
        }));

        dialog.show();
    }

    private ArrayList<Location> list(ArrayList<Location> fulllist,String id,int type){
        ArrayList<Location> lists=new ArrayList<>();
            if (type == intshahr) {
                for (int i = 0 ;i<fulllist.size();i++){

                if (fulllist.get(i).getMainid().equals(id)) {
                    Log.i("i ",i+" - titel ="+fulllist.get(i).getShahr()+"id"+ fulllist.get(i).id+"main = "+fulllist.get(i).mainid);
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
            for (int i = 0 ;i<fulllist.size();i++){

                if (fulllist.get(i).getMainid().equals(id)) {
                    Log.i("i ",i+" - titel ="+fulllist.get(i).getShahr()+"id"+ fulllist.get(i).id+"main = "+fulllist.get(i).mainid);
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

    public void setOstan(){

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity);
        final AlertDialog dialog =alertDialog.create();
        View convertview=LayoutInflater.from(Activity).inflate(R.layout.z_m_alert_adress,null);
        dialog.setView(convertview);
        dialog.setTitle("استان");
        RecyclerView recyclerView=(RecyclerView)convertview.findViewById(R.id.z_m_Recycler_alert) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(Activity,DividerItemDecoration.VERTICAL));
//        recyclerView.setAdapter(new Adapter(list_Ostan, new Adapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Location model) {
//                Toast.makeText(Activity, ""+model.getId(), Toast.LENGTH_SHORT).show();
//            }
//        }));

        dialog.show();

    }
    public void getOstan(){
        int a ;
        for (a=0 ; a <Lst_Ostan.size() ; a++ ) {
            Log.i("a - id ostan",a+" / "+id_OStan);
            if (Lst_Ostan.get(a).ostan.equals(ostan.getText().toString())){
                id_OStan = Lst_Ostan.get(a).id;
                Toast.makeText(Activity, ""+id_OStan, Toast.LENGTH_SHORT).show();
                a=Lst_Ostan.size();
            }

//            int ggg = Lst_Ostan.get(a).getOstan().indexOf(ostan.getText().toString());
//
//            if (ggg>-1){
//                Log.i("GGG=0",""+a);
//                Log.i("GGG=0",""+Lst_Ostan.get(a).id);
//                id_OStan =Lst_Ostan.get(a).id;
//            }else {
//
//                Log.i("GGG=-1",""+a);
//            }

            // id_OStan=Lst_Ostan.get(ggg).id;

        }
    }
    public void setShahr(){
        AShahr.clear();

            getshahr();
        if (AShahr != null){

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity);
            final AlertDialog dialog =alertDialog.create();
            View convertview=LayoutInflater.from(Activity).inflate(R.layout.z_m_alert_adress,null);
            dialog.setView(convertview);
            dialog.setTitle("شهر");
//            ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity,android.R.layout.simple_list_item_1,AShahr);
//            lv.setAdapter(adapter);
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    String values=((TextView)view).getText().toString();
//                    shahr.setText(values);
//                    getshahr();
//                    getMahale();
//                    mahale.setVisibility(View.GONE);
//                    if (AMahale.size() >0){
//
//                        mahale.setVisibility(View.VISIBLE);
//                        setMahale();
//
//                    }
//
//                    dialog.dismiss();
//
//                }
//            });
            dialog.show();
        }else {
            Toast.makeText(Activity, "Ostan", Toast.LENGTH_SHORT).show();
        }


    }
    public void getshahr() {
        if (id_OStan != null) {
            for (int a = 0; a < Lst_shahr.size(); a++) {
                if ( Lst_shahr.get(a).getMain_id().equals(id_OStan)){
                    AShahr.add(Lst_shahr.get(a).getShahrVostan());
                    id_Shahr = Lst_shahr.get(a).id;
                }
//                int b = Lst_shahr.get(a).shahrVostan.indexOf(shahr.getText().toString());
//
//                if (ggg > -1) {
//                    Log.i("GGG=0", "" + a);
//                    Log.i("GGG=0", "" + Lst_shahr.get(a).getShahrVostan());
//
//                } else {
//                    Log.i("GGG=-1", "" + a);
//                }
//                if (b > -1) {
//                    id_Shahr = Lst_shahr.get(a).id;
//                    Log.i("id_shahr", id_Shahr);
//                }
            }


        }
    }
    public void setMahale(){
        AMahale.clear();
        getMahale();
        String names[] =Activity.getResources().getStringArray(R.array.Mahale);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity);
        final AlertDialog dialog =alertDialog.create();
        View convertview=LayoutInflater.from(Activity).inflate(R.layout.z_m_alert_adress,null);
        dialog.setView(convertview);
        dialog.setTitle("شهر");
//        ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity,android.R.layout.simple_list_item_1,AMahale);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String values=((TextView)view).getText().toString();
//                mahale.setText(values);
//                dialog.dismiss();
//            }
//        });
        dialog.show();

    }
    public void getMahale(){

        if (id_Shahr != null) {
            AMahale.clear();
            for (int a = 0; a < Lst_Mahale.size(); a++) {
                Log.i("Int ggg ","mahale id = "+" Id Shahr = "+id_Shahr);
                if (Lst_Mahale.get(a).getMain_id().equals(id_Shahr)){
                    AMahale.add(Lst_Mahale.get(a).getShahrVostan());
                    id_Mahale =Lst_Mahale.get(a).id;
                }

            }
        }
    }

}

 class MDL_Register{

    String id;
    String main_id;
    String ostan;
    String shahrVostan;


    public MDL_Register(String id, String ostan) {
        this.id = id;
        this.ostan = ostan;
    }

    public MDL_Register(String id, String main_id, String shahrVostan) {
        this.id = id;
        this.main_id = main_id;
        this.shahrVostan = shahrVostan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain_id() {
        return main_id;
    }

    public void setMain_id(String main_id) {
        this.main_id = main_id;
    }

    public String getOstan() {
        return ostan;
    }

    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    public String getShahrVostan() {
        return shahrVostan;
    }

    public void setShahrVostan(String shahrVostan) {
        this.shahrVostan = shahrVostan;
    }
}

class Location{

    String id,mainid,ostan,shahr,mahale;

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

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private ArrayList<Location> list;
    private int type;
    private OnItemClickListener clickListener;

    public Adapter(ArrayList<Location> list, int type, OnItemClickListener clickListener) {
        this.list = list;
        this.type = type;
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Location model);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_recycler_location,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Location location=list.get(position);

        switch (type){
            case 0:
                holder.titel.setText(location.getOstan());
                break;
            case 1 :
                holder.titel.setText(location.getShahr());
                 break;
            case 2:
                holder.titel.setText(location.getMahale());
                break;
        }

//        if (!(location.getOstan().equals("null"))) {
//
//        }else if (!location.getShahr().equals("null")){
//            holder.titel.setText(location.getShahr());
//        }else if (!location.getMahale().equals("null")){
//
//        }

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
            titel=(TextView)itemView.findViewById(R.id.z_m_textview_location);
        }
    }
}



//    public void init(View view){
//        phone=(EditText)view.findViewById(R.id.Register_Edittext_Phone);
//        name=(EditText)view.findViewById(R.id.Register_Edittext_Name);
//        family=(EditText)view.findViewById(R.id.Register_Edittext_Family);
//        adres=(EditText)view.findViewById(R.id.Register_Edittext_Adress);
//        ostan=(TextView) view.findViewById(R.id.Register_Textview_Ostan);
//        shahr=(TextView)view.findViewById(R.id.Register_Textview_shahr);
//        mahale=(TextView)view.findViewById(R.id.Register_Textview_Mahale);
//        remember=(TextView)view.findViewById(R.id.Register_Textview_Remember);
//        remove=(Button)view.findViewById(R.id.Register_Button_Remove);
//        done=(TextView)view.findViewById(R.id.Register_Textview_Register);
//
//    }
//
//    public void Register(){
//        remove.setVisibility(View.GONE);
//        shahr.setVisibility(View.GONE);
//        mahale.setVisibility(View.GONE);
//
//        remember.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Rememberalert();
//            }
//        });
//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                InsertDataBase();
//
//
//
//            }
//        });
//
//    }
//    public void Virayesh(){
//        ArrayList<Model_User> User=new ArrayList<>(db_helper.getData());
//
//        phone.setText(User.get(0).getPhone());
//      //  phone.setGravity(View.TEXT_ALIGNMENT_CENTER);
//        phone.setTextColor(Activity.getResources().getColor(android.R.color.holo_red_light));
//        phone.setFocusable(false);
//        phone.setEnabled(false);
//        phone.setInputType(0);
//        name.setText(User.get(0).getName());
//        family.setText(User.get(0).getFamily());
//        ostan.setText(User.get(0).getOstan());
//
//        ostan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setOstan();
//            }
//        });
//        shahr.setText(User.get(0).getShahr());
//        shahr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (id_OStan != null){
//                    setShahr();
//                }else {
//                    Toast.makeText(Activity, "Ostan pa chi", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        mahale.setText(User.get(0).getMahale());
//        mahale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setMahale();
//            }
//        });
//        adres.setText(User.get(0).getAdres());
//
//        done.setText("ویرایش");
//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (phone.getText()==null ||
//                        name.getText() ==null||
//                        family.getText() ==null||
//                        adres.getText() ==null||
//                        ostan.getText().equals("استان")||
//                        shahr.getText().equals("شهر")||
//                        mahale.getText().equals("محله")
//                        ){
//                    Toast.makeText(Activity, "Empty", Toast.LENGTH_SHORT).show();
//                }else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(Activity);
//                    builder.setTitle("ویرایش");
//                    builder.setMessage("از اطلاعات ثبتی خود مطمعا هستید !");
//
//                    // add a button
//                    builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            db_helper.updateName(phone.getText().toString(),
//                                    name.getText().toString(),
//                                    family.getText().toString(),
//                                    ostan.getText().toString(),
//                                    shahr.getText().toString(),
//                                    mahale.getText().toString(),
//                                    adres.getText().toString());
//
//                            dialog.dismiss();
//                            Activity.onBackPressed();
//
//
//                        }
//                    });
//                    builder.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    // create and show the alert dialog
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//
//                }
//            }
//        });
//
//        remember.setVisibility(View.INVISIBLE);
//
//        remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Activity);
//                builder.setTitle("ویرایش");
//                builder.setMessage("از اطلاعات ثبتی خود مطمعا هستید !");
//
//                // add a button
//                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        db_helper.delete(phone.getText().toString());
//                        Intent intent=Activity.getIntent();
//                        Activity.finish();
//                        Activity.startActivity(intent);
//                    }
//                });
//                builder.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                // create and show the alert dialog
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//            }
//        });
//
//        }
//
//
//            private class GetContacts extends AsyncTask<Void, Void, Void> {
//
//                @Override
//                protected void onPreExecute() {
//                    super.onPreExecute();
//                    // Showing progress dialog
//                    pDialog = new ProgressDialog(Activity);
//                    pDialog.setMessage("Please wait...");
//                    pDialog.setCancelable(false);
//                    pDialog.show();
//
//                }
//
//                @Override
//                protected Void doInBackground(Void... arg0) {
//                    HttpHandler sh = new HttpHandler();
//
//                    // Making a request to url and getting response
//                    String jsonStr = sh.makeServiceCall(url);
//
//
//
//
//                    Log.i( "Response from url: " , jsonStr);
//
//                    if (jsonStr != null) {
//                        try {
//                            JSONObject jsonObj = new JSONObject(jsonStr);
//
//                            Lst_Ostan=new ArrayList<>();
//                            Lst_shahr=new ArrayList<>();
//                            Lst_Mahale=new ArrayList<>();
//
//
//                            // Getting JSON Array node
//                            JSONArray data = jsonObj.getJSONArray("data");
//                            Log.i( "Response jsonobject: " , data.toString());
//
//
//                            AOstan=new ArrayList<>();
//                            AShahr=new ArrayList<>();
//                            AMahale=new ArrayList<>();
//                            // looping through All Contacts
//                            for (int i = 0; i < data.length(); i++) {
//
//
//                                JSONObject c = data.getJSONObject(i);
//
//                                String id = c.getString("id");
//                                String title = c.getString("title");
//
//                                Lst_Ostan.add(new MDL_Register(id,title));
//
//                                JSONArray city =c.getJSONArray("cities");
//                                for (int j = 0; j < city.length(); j++) {
//                                    JSONObject  ccity = city.getJSONObject(j);
//
//                                    String idcity=ccity.getString("id");
//                                    String idostan=ccity.getString("state_id");
//                                    String namecity=ccity.getString("title");
//
//                                    Lst_shahr.add(new MDL_Register(idcity,idostan,namecity));
//
//                                    JSONArray M=ccity.getJSONArray("areas");
//                                    for (int m=0;m < M.length();m++){
//                                        JSONObject Mahale=M.getJSONObject(m);
//
//                                        String idmahale=Mahale.getString("id");
//                                        String idShahr=Mahale.getString("city_id");
//                                        String nameMahale=Mahale.getString("title");
//
//                                        Lst_Mahale.add(new MDL_Register(idmahale,idShahr,nameMahale));
//                                    }
//                                }
//
//
//
//                                // Phone node is JSON Object
////                                JSONObject phone = c.getJSONObject("phone");
////                                String mobile = phone.getString("mobile");
////                                String home = phone.getString("home");
////                                String office = phone.getString("office");
//
//                            }
//                        } catch (final JSONException e) {
//                            Log.i( "Json parsing error: " , e.getMessage());
//
////                            runOnUiThread(new Runnable() {
////                                @Override
////                                public void run() {
////                                    Toast.makeText(getApplicationContext(),
////                                            "Json parsing error: " + e.getMessage(),
////                                            Toast.LENGTH_LONG)
////                                            .show();
////                                }
////                            });
//
//                        }
//                    } else {
//                        Log.i( "Couldn't server.","");
//                        Activity.onBackPressed();
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                Toast.makeText(getApplicationContext(),
////                                        "Couldn't get json from server. Check LogCat for possible errors!",
////                                        Toast.LENGTH_LONG)
////                                        .show();
////                            }
////                        });
//
//                    }
//
//                    return null;
//                }
//
//                @Override
//                protected void onPostExecute(Void result) {
//                    super.onPostExecute(result);
//                    // Dismiss the progress dialog
//                    if (pDialog.isShowing())
//                        pDialog.dismiss();
//                    /**
//                     * Updating parsed JSON data into ListView
//                     * */
//                    getOstan();
//                    getshahr();
//                  //  getMahale();
//
//
//                }
//
//            }
//
//
//
//    public void setOstan(){
//        AOstan.clear();
//        for (int a = 0 ; a <Lst_Ostan.size() ; a++ ){
//            MDL_Register MDL_Ostan=Lst_Ostan.get(a);
//
//            AOstan.add(MDL_Ostan.getOstan());
//        }
//
//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity);
//        final AlertDialog dialog =alertDialog.create();
//        View convertview=LayoutInflater.from(Activity).inflate(R.layout.z_m_alert_adress,null);
//        dialog.setView(convertview);
//        dialog.setTitle("استان");
//        ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity,android.R.layout.simple_list_item_1,AOstan);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String values=((TextView)view).getText().toString();
//                ostan.setText(values);
//                shahr.setVisibility(View.VISIBLE);
//                getOstan();
//                setShahr();
//
//                dialog.dismiss();
//
//            }
//        });
//        dialog.show();
//
//    }
//    public void getOstan(){
////        for (int a = 0 ; a <Lst_Ostan.size() ; a++ ) {
////            int ggg = Lst_Ostan.get(a).getOstan().indexOf(ostan.getText().toString());
////
////            if (ggg>-1){
////                Log.i("GGG=0",""+a);
////                Log.i("GGG=0",""+Lst_Ostan.get(a).id);
////                id_OStan =Lst_Ostan.get(a).id;
////            }else {
////
////                Log.i("GGG=-1",""+a);
////            }
////
////            // id_OStan=Lst_Ostan.get(ggg).id;
////
////        }
//    }
//
//    public void setShahr(){
////        AShahr.clear();
////
////            getshahr();
////        if (AShahr != null){
////
////            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity);
////            final AlertDialog dialog =alertDialog.create();
////            View convertview=LayoutInflater.from(Activity).inflate(R.layout.z_m_alert_adress,null);
////            dialog.setView(convertview);
////            dialog.setTitle("شهر");
////            ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
////            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity,android.R.layout.simple_list_item_1,AShahr);
////            lv.setAdapter(adapter);
////            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                @Override
////                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                    String values=((TextView)view).getText().toString();
////                    shahr.setText(values);
////                    getshahr();
////                    getMahale();
////                    mahale.setVisibility(View.GONE);
////                    if (AMahale.size() >0){
////
////                        mahale.setVisibility(View.VISIBLE);
////                        setMahale();
////
////                    }
////
////                    dialog.dismiss();
////
////                }
////            });
////            dialog.show();
////        }else {
////            Toast.makeText(Activity, "Ostan", Toast.LENGTH_SHORT).show();
////        }
//
//
//    }
//    public void getshahr() {
////        if (id_OStan != null) {
////            for (int a = 0; a < Lst_shahr.size(); a++) {
////                int ggg = Lst_shahr.get(a).getMain_id().indexOf(id_OStan);
////                int b = Lst_shahr.get(a).shahrVostan.indexOf(shahr.getText().toString());
////
////                if (ggg > -1) {
////                    Log.i("GGG=0", "" + a);
////                    Log.i("GGG=0", "" + Lst_shahr.get(a).getShahrVostan());
////                    AShahr.add(Lst_shahr.get(a).getShahrVostan());
////                } else {
////
////                    Log.i("GGG=-1", "" + a);
////                }
////                if (b > -1) {
////                    id_Shahr = Lst_shahr.get(a).id;
////                    Log.i("id_shahr", id_Shahr);
////                }
////            }
////
////
////        }
//    }
//
//    public void setMahale(){
////        AMahale.clear();
////        getMahale();
////        String names[] =Activity.getResources().getStringArray(R.array.Mahale);
////        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity);
////        final AlertDialog dialog =alertDialog.create();
////        View convertview=LayoutInflater.from(Activity).inflate(R.layout.z_m_alert_adress,null);
////        dialog.setView(convertview);
////        dialog.setTitle("شهر");
////        ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity,android.R.layout.simple_list_item_1,AMahale);
////        lv.setAdapter(adapter);
////        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                String values=((TextView)view).getText().toString();
////                mahale.setText(values);
////                dialog.dismiss();
////            }
////        });
////        dialog.show();
////
////    }
////    public void getMahale(){
////
////        if (id_Shahr != null) {
////            AMahale.clear();
////            for (int a = 0; a < Lst_Mahale.size(); a++) {
////                int ggg = Lst_Mahale.get(a).getMain_id().indexOf(id_Shahr);
////
////
////                if (ggg > -1) {
////
////                    AMahale.add(Lst_Mahale.get(a).getShahrVostan());
////                } else {
////
////                    Log.i("GGG=-1", "" + a);
////                }
////            }
////        }
//    }
//
//    public void Rememberalert(){
//
////        final   AlertDialog.Builder  alertDialog =new AlertDialog.Builder(Activity);
////        final   AlertDialog dialog=alertDialog.create();
////
////        View view=LayoutInflater.from(Activity).inflate(R.layout.z_m_alert_rememberuser,null);
////        dialog.setView(view);
////        dialog.setTitle("بازگردانی پروفایل");
////        final EditText phonenumber=(EditText)view.findViewById(R.id.z_m_rememberuser_Editetext);
////        Button send = (Button)view.findViewById(R.id.z_m_rememberuser_Button);
////        send.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (phonenumber.getText()==null||
////                    phonenumber.getText().length() !=11
////                        ){
////                    Toast.makeText(Activity, "لطفا شماره خود را به درستی وارد نماییید !", Toast.LENGTH_SHORT).show();
////                }else {
////                    dialog.dismiss();
////                }
////
////
////            }
////        });
////        dialog.show();
//
//    }
//
//    public void InsertDataBase(){
//
//        if (phone.getText()==null ||
//                name.getText() ==null||
//                family.getText() ==null||
//                adres.getText() ==null
////                ostan.getText().equals("استان")||
////                shahr.getText().equals("شهر")||
////                mahale.getText().equals("محله")
//                ){
//            Toast.makeText(Activity, "Empty", Toast.LENGTH_SHORT).show();
//        }else {
//            db_helper.insertData(
//                    phone.getText().toString(),
//                    name.getText().toString(),
//                    family.getText().toString(),
//                    "jivhk",
//                    "tehran",
//                    "tehran",
//                    adres.getText().toString()
//            );
//            Intent intent=Activity.getIntent();
//            Activity.finish();
//            Activity.startActivity(intent);
//        }
//
//
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
