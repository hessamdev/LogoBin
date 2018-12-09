package com.example.dev.logobin.handel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_Kala;

import java.util.ArrayList;

public class DB_Kala {
        myDbHelper myhelper;
        ArrayList<M_Kala> Kala;
        public DB_Kala(Context context)
        {
            myhelper = new myDbHelper(context);

        }


        public long insertData(String ID,
                               String title,
                               String price,
                               String code,
                               String details,
                               String image,
                               String active,
                               String factory_id,
                               String list_id,
                               String sublist_id)
        {

            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.ID,ID);
            contentValues.put(myDbHelper.Title, title);
            contentValues.put(myDbHelper.Price, price);
            contentValues.put(myDbHelper.Code,code);
            contentValues.put(myDbHelper.Details,details);
            contentValues.put(myDbHelper.Image,image);
            contentValues.put(myDbHelper.Active,active);
            contentValues.put(myDbHelper.Factory_id,factory_id);
            contentValues.put(myDbHelper.List_id,list_id);
            contentValues.put(myDbHelper.Sublist_id,sublist_id);

            long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
            Log.i("DatabaseKala","InsertData");
            return id;
        }

        public ArrayList<M_Kala>  getData()
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {
                    myDbHelper.UID,
                    myDbHelper.ID,
                    myDbHelper.Title,
                    myDbHelper.Price,
                    myDbHelper.Code,
                    myDbHelper.Details,
                    myDbHelper.Image,
                    myDbHelper.Active,
                    myDbHelper.Factory_id,
                    myDbHelper.List_id,
                    myDbHelper.Sublist_id,
            };
            Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
            Kala=new ArrayList<>();
            while (cursor.moveToNext())
            {
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String id =cursor.getString(cursor.getColumnIndex(myDbHelper.ID));
//                int id= Integer.parseInt(Sid);
                String  title =cursor.getString(cursor.getColumnIndex(myDbHelper.Title));
                String  price =cursor.getString(cursor.getColumnIndex(myDbHelper.Price));
                String  code =cursor.getString(cursor.getColumnIndex(myDbHelper.Code));
                String  details =cursor.getString(cursor.getColumnIndex(myDbHelper.Details));
                String  image =cursor.getString(cursor.getColumnIndex(myDbHelper.Image));
                String  active =cursor.getString(cursor.getColumnIndex(myDbHelper.Active));
                String  factory_id =cursor.getString(cursor.getColumnIndex(myDbHelper.Factory_id));
                String  list_id =cursor.getString(cursor.getColumnIndex(myDbHelper.List_id));
                String  sublist_id =cursor.getString(cursor.getColumnIndex(myDbHelper.Sublist_id));
                Kala.add(new M_Kala(id,title,price,code,details,image,active,factory_id,list_id,sublist_id));
            }
//            cursor.close();
            Log.i("DatabaseKala","Getkala");
            return Kala;
        }
//Delete_Update

        public  int delete()
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            Log.i("DatabaseBrand","Delete");

            int count =db.delete(myDbHelper.TABLE_NAME , null ,null);
            return  count;
        }

        public ArrayList<M_Kala> where(String Factory_Id) {


            SQLiteDatabase db = myhelper.getWritableDatabase();
            ArrayList<M_Kala> Where = new ArrayList<>();

            Cursor cursor= db.rawQuery("SELECT * from " + myDbHelper.TABLE_NAME
                    + " WHERE Factory_id= "+Factory_Id , null);

            if (cursor.moveToFirst()) {

                while (!cursor.isAfterLast()){
                    String id =cursor.getString(cursor.getColumnIndex(myDbHelper.ID));
//                int id= Integer.parseInt(Sid);
                    String  title =cursor.getString(cursor.getColumnIndex(myDbHelper.Title));
                    String  price =cursor.getString(cursor.getColumnIndex(myDbHelper.Price));
                    String  code =cursor.getString(cursor.getColumnIndex(myDbHelper.Code));
                    String  details =cursor.getString(cursor.getColumnIndex(myDbHelper.Details));
                    String  image =cursor.getString(cursor.getColumnIndex(myDbHelper.Image));
                    String  active =cursor.getString(cursor.getColumnIndex(myDbHelper.Active));
                    String  factory_id =cursor.getString(cursor.getColumnIndex(myDbHelper.Factory_id));
                    String  list_id =cursor.getString(cursor.getColumnIndex(myDbHelper.List_id));
                    String  sublist_id =cursor.getString(cursor.getColumnIndex(myDbHelper.Sublist_id));

                    Where.add(new M_Kala(id,title,price,code,details,image,active,factory_id,list_id,sublist_id));

                    cursor.moveToNext();
                }

            }
            return Where;
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
            private static final String DATABASE_NAME = "BD_Kala";    // Database Name
            private static final String TABLE_NAME = "kala";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String UID="_id";     // Column I (Primary Key)
            private static final String ID = "id";
            private static final String Title = "title";
            private static final String Price = "price";
            private static final String Code = "code";
            private static final String Details = "details";
            private static final String Image = "image";
            private static final String Active = "active";
            private static final String Factory_id = "factory_id";
            private static final String List_id = "list_id";
            private static final String Sublist_id = "sublist_id";


            private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                    " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ID +" VARCHAR(255) ,"
                    + Title +" VARCHAR(255) ,"
                    + Price +" VARCHAR(255) ,"
                    + Code +" VARCHAR(255) ,"
                    + Details +" VARCHAR(255) ,"
                    + Image +" VARCHAR(255) ,"
                    + Active +" VARCHAR(255) ,"
                    + Factory_id +" VARCHAR(255) ,"
                    + List_id +" VARCHAR(255) ,"
                    + Sublist_id +" VARCHAR(255));";
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



