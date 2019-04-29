package com.example.dev.logobin.handel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.dev.logobin.model.M_Brand;
import com.example.dev.logobin.model.M_Sherkat;

import java.util.ArrayList;

public class DB_Sherkat {
        myDbHelper myhelper;

        public DB_Sherkat(Context context)
        {
            myhelper = new myDbHelper(context);

        }


        public long insertData(String ID,
                               String Title,
                               String Url_Image,
                               String state,
                               String Rate
                               )
        {

            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.ID,ID );
            contentValues.put(myDbHelper.Title, Title);
            contentValues.put(myDbHelper.State,state);
            contentValues.put(myDbHelper.Rate, Rate);
            contentValues.put(myDbHelper.URl_Image,Url_Image);
            long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
            Log.i("DatabaseSherkat","Insert");
            dbb.close();
            return id;
        }

        public ArrayList<M_Sherkat>  getData()
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {
                    myDbHelper.UID,
                    myDbHelper.ID,
                    myDbHelper.Title,
                    myDbHelper.State,
                    myDbHelper.Rate,
                    myDbHelper.URl_Image,
            };
            Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
            ArrayList<M_Sherkat> ListSherkat=new ArrayList<>();
            while (cursor.moveToNext())
            {
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String Sid =cursor.getString(cursor.getColumnIndex(myDbHelper.ID));
                int id= Integer.parseInt(Sid);
                String  title =cursor.getString(cursor.getColumnIndex(myDbHelper.Title));
                String  state =cursor.getString(cursor.getColumnIndex(myDbHelper.State));
                String  rate =cursor.getString(cursor.getColumnIndex(myDbHelper.Rate));
                String  url_image =cursor.getString(cursor.getColumnIndex(myDbHelper.URl_Image));
                ListSherkat.add(new M_Sherkat(Sid,title,url_image,state,rate));
            }
            cursor.close();
            db.close();
            Log.i("Database_Sherkat","Get");
            return ListSherkat;
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
            private static final String DATABASE_NAME = "BD_Sherkat";    // Database Name
            private static final String TABLE_NAME = "Sherkat";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String UID="_id";     // Column I (Primary Key)
            private static final String ID = "id";
            private static final String Title = "title";
            private static final String State = "state";
            private static final String Rate = "mrate";
            private static final String URl_Image = "url_image";


            private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                    " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ID +" VARCHAR(255) ,"
                    + Title +" VARCHAR(255) ,"
                    + State +" VARCHAR(255) ,"
                    + Rate +" VARCHAR(255) ,"
                    + URl_Image +" VARCHAR(255));";
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



