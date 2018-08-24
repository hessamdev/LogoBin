package com.example.dev.logobin.Register;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.R;



public class RegisterPage extends Fragment{


    TextView  ostan,shahr,mahale,remember ,done;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view= LayoutInflater.from(getActivity()).inflate(R.layout.register_ui,null);

        ostan=(TextView) view.findViewById(R.id.Register_Textview_Ostan);
        ostan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOstan();
            }
        });

        shahr=(TextView)view.findViewById(R.id.Register_Textview_shahr);
        shahr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setShahr();
            }
        });

        mahale=(TextView)view.findViewById(R.id.Register_Textview_Mahale);
        mahale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMahale();
            }
        });
        remember=(TextView)view.findViewById(R.id.Register_Textview_Remember);
        remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rememberalert();
            }
        });

        done=(TextView)view.findViewById(R.id.Register_Textview_Register);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.framefull,new RegisterCode(),"code").addToBackStack("Register").commit();
            }
        });



        return view;
    }

    public void setOstan(){
        String names[] =getResources().getStringArray(R.array.Ostan);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog =alertDialog.create();
        View convertview=LayoutInflater.from(getActivity()).inflate(R.layout.z_m_alert_adress,null);
        dialog.setView(convertview);
        dialog.setTitle("استان");
        ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String values=((TextView)view).getText().toString();
                ostan.setText(values);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void setShahr(){
        String names[] =getResources().getStringArray(R.array.Shahr);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog =alertDialog.create();
        View convertview=LayoutInflater.from(getActivity()).inflate(R.layout.z_m_alert_adress,null);
        dialog.setView(convertview);
        dialog.setTitle("شهر");
        ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String values=((TextView)view).getText().toString();
                shahr.setText(values);
                dialog.dismiss();



            }
        });
        dialog.show();

    }

    public void setMahale(){
        String names[] =getResources().getStringArray(R.array.Mahale);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog =alertDialog.create();
        View convertview=LayoutInflater.from(getActivity()).inflate(R.layout.z_m_alert_adress,null);
        dialog.setView(convertview);
        dialog.setTitle("شهر");
        ListView lv = (ListView) convertview.findViewById(R.id.z_m_listview_alert);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String values=((TextView)view).getText().toString();
                mahale.setText(values);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void Rememberalert(){

      final   AlertDialog.Builder  alertDialog =new AlertDialog.Builder(getActivity());
      final   AlertDialog dialog=alertDialog.create();

        View view=LayoutInflater.from(getActivity()).inflate(R.layout.z_m_alert_rememberuser,null);
        dialog.setView(view);
        dialog.setTitle("بازگردانی پروفایل");
        EditText phonenumber=(EditText)view.findViewById(R.id.z_m_rememberuser_Editetext);
        Button send = (Button)view.findViewById(R.id.z_m_rememberuser_Button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getFragmentManager().beginTransaction().replace(R.id.framefull,new RegisterCode(),"code").commit();
            }
        });
        dialog.show();

    }

}
