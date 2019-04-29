package com.example.dev.logobin.handel;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.dev.logobin.model.M_User;

import java.util.ArrayList;

public class User_Data {

    private Context context;

    public User_Data(Context context) {
        this.context = context;
    }

    public void setData(String Phone,
                        String Username,
                        String Ostan,
                        String ID_Ostan,
                        String Shahr,
                        String ID_Shahr,
                        String Mahale,
                        String ID_Mahale,
                        String User_id,
                        String Token
                        )
    {
        SharedPreferences User = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=User.edit();

        editor.putString("PhoneNumber",Phone);
        editor.putString("UserName",Username);
        editor.putString("Ostan",Ostan);
        editor.putString("ID_Ostan",ID_Ostan);
        editor.putString("Shahr",Shahr);
        editor.putString("ID_Shahr",ID_Shahr);
        editor.putString("Mahale",Mahale);
        editor.putString("ID_Mahale",ID_Mahale);
        editor.putString("User_id",User_id);
        editor.putString("Token",Token);

        editor.apply();

        Log.i("Add to share","True");

    }

    public ArrayList<M_User> getDatauser(){
        SharedPreferences User = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        ArrayList<M_User> userdata=new ArrayList<>();
        userdata.add(new M_User(
                User.getString("PhoneNumber",null),
                User.getString("UserName",null),
                User.getString("Ostan",null),
                User.getString("ID_Ostan",null),
                User.getString("Shahr",null),
                User.getString("ID_Shahr",null),
                User.getString("Mahale",null),
                User.getString("ID_Mahale",null),
                User.getString("User_id",null),
                User.getString("Token",null)
                ));
        return userdata;

    }

    public String getMahale(){
        SharedPreferences User = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String Id_Mahale=User.getString("ID_Mahale",null);

        return Id_Mahale;
    }

    public String getToken(){
        SharedPreferences User = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String Id_Mahale=User.getString("Token",null);

        return Id_Mahale;
    }

    public String getUser_id(){
        SharedPreferences User = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String User_id=User.getString("User_id",null);

        return User_id;
    }

    public String getUser_phone(){
        SharedPreferences User = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String _phone=User.getString("PhoneNumber",null);

        return _phone;
    }

    public void Delete() {
        SharedPreferences User = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=User.edit();
        editor.clear();
        editor.apply();
    }


}
