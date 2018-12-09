package com.example.dev.logobin.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dev.logobin.R;
import com.example.dev.logobin.activity.MainPage;
import com.example.dev.logobin.fragment.FragmentActivity;


public class RegisterPage extends FragmentActivity{


    private TextView  ostan,shahr,mahale,remember ,done;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_ui);
        intent=new Intent(this,MainPage.class);


        ostan=(TextView) findViewById(R.id.Register_Textview_Ostan);
        ostan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOstan();
            }
        });

        shahr=(TextView)findViewById(R.id.Register_Textview_shahr);
        shahr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setShahr();
            }
        });

        mahale=(TextView)findViewById(R.id.Register_Textview_Mahale);
        mahale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMahale();
            }
        });
        remember=(TextView)findViewById(R.id.Register_Textview_Remember);
        remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rememberalert();
            }
        });

        done=(TextView)findViewById(R.id.Register_Textview_Register);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
                //getFragmentManager().beginTransaction().replace(R.id.framefull,new RegisterCode(),"code").addToBackStack("Register").commit();
            }
        });
    }

    public void setOstan(){
        String names[] =getResources().getStringArray(R.array.Ostan);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final AlertDialog dialog =alertDialog.create();
        View convertview=LayoutInflater.from(this).inflate(R.layout.z_m_alert_adress,null);
        dialog.setView(convertview);
        dialog.setTitle("استان");
//        ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String values=((TextView)view).getText().toString();
//                ostan.setText(values);
//                dialog.dismiss();
//            }
//        });
        dialog.show();
    }

    public void setShahr(){
        String names[] =getResources().getStringArray(R.array.Shahr);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final AlertDialog dialog =alertDialog.create();
        View convertview=LayoutInflater.from(this).inflate(R.layout.z_m_alert_adress,null);
        dialog.setView(convertview);
        dialog.setTitle("شهر");
//        ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String values=((TextView)view).getText().toString();
//                shahr.setText(values);
//                dialog.dismiss();
//
//
//
//            }
//        });
        dialog.show();

    }

    public void setMahale(){
        String names[] =getResources().getStringArray(R.array.Mahale);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final AlertDialog dialog =alertDialog.create();
        View convertview=LayoutInflater.from(this).inflate(R.layout.z_m_alert_adress,null);
        dialog.setView(convertview);
        dialog.setTitle("شهر");
//        ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
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

    public void Rememberalert(){

      final   AlertDialog.Builder  alertDialog =new AlertDialog.Builder(this);
      final   AlertDialog dialog=alertDialog.create();

        View view=LayoutInflater.from(this).inflate(R.layout.z_m_alert_rememberuser,null);
        dialog.setView(view);
        dialog.setTitle("بازگردانی پروفایل");
        EditText phonenumber=(EditText)view.findViewById(R.id.z_m_rememberuser_Editetext);
        Button send = (Button)view.findViewById(R.id.z_m_rememberuser_Button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //getFragmentManager().beginTransaction().replace(R.id.framefull,new RegisterCode(),"code").commit();
            }
        });
        dialog.show();

    }

}
