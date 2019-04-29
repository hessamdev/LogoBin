package com.example.dev.logobin.handel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.dev.logobin.model.M_Brand;

import java.util.ArrayList;

public class DB_Brand {
        myDbHelper myhelper;
        ArrayList<M_Brand> Brand;
        public DB_Brand(Context context)
        {
            myhelper = new myDbHelper(context);

        }


        public long insertData(String ID,
                               String Title,
                               String Rate,
                               String Url_Image,
                               String Type
                               )
        {

            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.ID,ID );
            contentValues.put(myDbHelper.Title, Title);
            contentValues.put(myDbHelper.Rate, Rate);
            contentValues.put(myDbHelper.URl_Image,Url_Image);
            contentValues.put(myDbHelper.Type,Type);
            long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
//            Log.i("DatabaseBrand","Insert");
            dbb.close();
            return id;
        }

        public ArrayList<M_Brand>  getData(String Type)
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String where= myDbHelper.Type+ " = ? ";
            String[] whereArg={Type};
            String[] columns = {
                    myDbHelper.UID,
                    myDbHelper.ID,
                    myDbHelper.Title,
                    myDbHelper.Rate,
                    myDbHelper.URl_Image,
            };
            Cursor cursor =db.query(myDbHelper.TABLE_NAME,null,where,whereArg,null,null,null);
            ArrayList<M_Brand> ListBrand=new ArrayList<>();
            while (cursor.moveToNext())
            {
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String Sid =cursor.getString(cursor.getColumnIndex(myDbHelper.ID));
                String  title =cursor.getString(cursor.getColumnIndex(myDbHelper.Title));
                String  rate =cursor.getString(cursor.getColumnIndex(myDbHelper.Rate));
                String  url_image =cursor.getString(cursor.getColumnIndex(myDbHelper.URl_Image));
                ListBrand.add(new M_Brand(Sid,title,rate,url_image));
            }
            Log.i("DatabaseBrand","Get");
            cursor.close();
            db.close();
            return ListBrand;
        }
//Delete_Update

        public  int delete()
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            Log.i("DatabaseBrand","Dlete");

            int count =db.delete(myDbHelper.TABLE_NAME , null ,null);
            return  count;
        }
//
//        public int updateName(String ufhone ,
//                              String name,
//                              String famyly,
//                              String ostan,
//                              String shahr,
//                              String mahale,
//                              String adres
//                              )
//        {
//            SQLiteDatabase db = myhelper.getWritableDatabase();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(myDbHelper.NAME ,name);
//            contentValues.put(myDbHelper.FAMILY ,famyly);
//            contentValues.put(myDbHelper.OSTAN ,ostan);
//            contentValues.put(myDbHelper.SHAHR ,shahr);
//            contentValues.put(myDbHelper.MAHALE ,mahale);
//            contentValues.put(myDbHelper.ADRES ,adres);
//            String[] whereArgs= {ufhone};
//            int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.PHONE +" = ?",whereArgs );
//            return count;
//        }

        static class myDbHelper extends SQLiteOpenHelper
        {
            private static final String DATABASE_NAME = "BD_Brand";    // Database Name
            private static final String TABLE_NAME = "brand";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String UID="_id";     // Column I (Primary Key)
            private static final String ID = "id";
            private static final String Title = "title";
            private static final String Rate = "mrate";
            private static final String URl_Image = "url_image";
            private static final String Type = "Type";


            private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                    " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ID +" VARCHAR(255) ,"
                    + Title +" VARCHAR(255) ,"
                    + Rate +" VARCHAR(255) ,"
                    + URl_Image +" VARCHAR(255) ,"
                    + Type +" VARCHAR(255));";
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



