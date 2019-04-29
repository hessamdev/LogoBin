package com.example.dev.logobin.handel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.dev.logobin.model.M_BookMark;
import com.example.dev.logobin.model.Model_User;

import java.util.ArrayList;

public class DB_BookMark {
        myDbHelper myhelper;



        public DB_BookMark(Context context)
        {
            myhelper = new myDbHelper(context);
        }


        public long insertData(String Id,
                               String Image,
                               String Titel,
                               String Rate
                               )
        {

            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.Id,Id );
            contentValues.put(myDbHelper.Image, Image);
            contentValues.put(myDbHelper.Titel, Titel);
            contentValues.put(myDbHelper.Rate, Rate);

            long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
            dbb.close();
            return id;
        }

        public ArrayList<M_BookMark>  getData()
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {
                    myDbHelper.UID,
                    myDbHelper.Id,
                    myDbHelper.Image,
                    myDbHelper.Titel,
                    myDbHelper.Rate
            };

            Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
            ArrayList<M_BookMark> Bookmark=new ArrayList<>();
            while (cursor.moveToNext())
            {
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String Id =cursor.getString(cursor.getColumnIndex(myDbHelper.Id));
                String  Image =cursor.getString(cursor.getColumnIndex(myDbHelper.Image));
                String  Titel =cursor.getString(cursor.getColumnIndex(myDbHelper.Titel));
                String  Rate =cursor.getString(cursor.getColumnIndex(myDbHelper.Rate));

                Bookmark.add(new M_BookMark(Id,Image,Titel,Rate));
            }
            db.close();
            cursor.close();
            return Bookmark;
        }

        public  int delete(String ID)
            {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] whereArgs ={ID};

            int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.Id +" = ?",whereArgs);
            db.close();
            return  count;
        }

        public boolean setMark(String id){
            SQLiteDatabase db=myhelper.getReadableDatabase();
            boolean Ok=false;
            String where=myDbHelper.Id+ " = ? ";
            String[] whereArg={id};


            try {
                Cursor cursor =db.query(myDbHelper.TABLE_NAME,null,where,whereArg,null,null,null);
                while (cursor.moveToNext()){
                    String Id=cursor.getString(cursor.getColumnIndex(myDbHelper.Id));
                    if (id == null){
                        Ok=false;
                        Log.i("Boolean","True");
                    }else {
                        Log.i("Boolean","False");
                        Ok=true;
                    }
                }
                db.close();
                cursor.close();
            }catch (Exception e){
                Ok=false;
            }

            return Ok;

        }


        static class myDbHelper extends SQLiteOpenHelper
        {
            private static final String DATABASE_NAME = "myDatabase_User";    // Database Name
            private static final String TABLE_NAME = "BookMark";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String UID="_id";     // Column I (Primary Key)
            private static final String Id = "Id";
            private static final String Image = "Image";
            private static final String Titel = "Titel";
            private static final String Rate = "Rate";

            private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                    " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Id +" VARCHAR(255) ,"
                    + Image +" VARCHAR(255) ,"
                    + Titel +" VARCHAR(255) ,"
                    + Rate +" VARCHAR(225));";
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

