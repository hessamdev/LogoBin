package com.example.dev.logobin.handel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.dev.logobin.model.Model_User;

import java.util.ArrayList;

public class DB_Helper {
        myDbHelper myhelper;
        ArrayList<Model_User> login;


        public DB_Helper(Context context)
        {
            myhelper = new myDbHelper(context);
        }


        public long insertData(String Phone,
                               String Name,
                               String Family,
                               String Ostan,
                               String Shahr,
                               String Mahale,
                               String Adres)
        {

            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.PHONE,Phone );
            contentValues.put(myDbHelper.NAME, Name);
            contentValues.put(myDbHelper.FAMILY, Family);
            contentValues.put(myDbHelper.OSTAN, Ostan);
            contentValues.put(myDbHelper.SHAHR, Shahr);
            contentValues.put(myDbHelper.MAHALE, Mahale);
            contentValues.put(myDbHelper.ADRES, Adres);
            long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
            return id;
        }

        public ArrayList<Model_User>  getData()
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {
                    myDbHelper.UID,
                    myDbHelper.PHONE,
                    myDbHelper.NAME,
                    myDbHelper.FAMILY,
                    myDbHelper.OSTAN,
                    myDbHelper.SHAHR,
                    myDbHelper.MAHALE,
                    myDbHelper.ADRES
            };
            Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
            ArrayList<Model_User> register=new ArrayList<>();
            while (cursor.moveToNext())
            {
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String phone =cursor.getString(cursor.getColumnIndex(myDbHelper.PHONE));
                String  name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
                String  family =cursor.getString(cursor.getColumnIndex(myDbHelper.FAMILY));
                String  ostan =cursor.getString(cursor.getColumnIndex(myDbHelper.OSTAN));
                String  shahr =cursor.getString(cursor.getColumnIndex(myDbHelper.SHAHR));
                String  mahale =cursor.getString(cursor.getColumnIndex(myDbHelper.MAHALE));
                String  adres =cursor.getString(cursor.getColumnIndex(myDbHelper.ADRES));
                register.add(new Model_User(cid,phone,name,family,ostan,shahr,mahale,adres));
            }
            return register;
        }

        public  int delete(String uphone)
            {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] whereArgs ={uphone};

            int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.PHONE +" = ?",whereArgs);
            return  count;
        }

        public int updateName(String ufhone ,
                              String name,
                              String famyly,
                              String ostan,
                              String shahr,
                              String mahale,
                              String adres
                              )
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.NAME ,name);
            contentValues.put(myDbHelper.FAMILY ,famyly);
            contentValues.put(myDbHelper.OSTAN ,ostan);
            contentValues.put(myDbHelper.SHAHR ,shahr);
            contentValues.put(myDbHelper.MAHALE ,mahale);
            contentValues.put(myDbHelper.ADRES ,adres);
            String[] whereArgs= {ufhone};
            int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.PHONE +" = ?",whereArgs );
            return count;
        }

        static class myDbHelper extends SQLiteOpenHelper
        {
            private static final String DATABASE_NAME = "myDatabase_User";    // Database Name
            private static final String TABLE_NAME = "User";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String UID="_id";     // Column I (Primary Key)
            private static final String PHONE = "Phone";
            private static final String NAME = "name";
            private static final String FAMILY = "Family";
            private static final String OSTAN = "Ostan";
            private static final String SHAHR = "Shahr";
            private static final String MAHALE = "Mahale";
            private static final String ADRES = "Adres";
            private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                    " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PHONE +" VARCHAR(255) ,"
                    + NAME +" VARCHAR(255) ,"
                    + FAMILY +" VARCHAR(255) ,"
                    + OSTAN +" VARCHAR(255) ,"
                    + SHAHR +" VARCHAR(255) ,"
                    + MAHALE +" VARCHAR(255) ,"
                    + ADRES +" VARCHAR(225));";
            private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
            private Context context;

            public myDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_Version);
                this.context=context;
            }

            public void onCreate(SQLiteDatabase db) {

                try {
                    db.execSQL(CREATE_TABLE);
                } catch (Exception e) {
                    Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try {
                    Toast.makeText(context, "OnUpgrade", Toast.LENGTH_SHORT).show();;
                    db.execSQL(DROP_TABLE);
                    onCreate(db);
                }catch (Exception e) {
                    Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

