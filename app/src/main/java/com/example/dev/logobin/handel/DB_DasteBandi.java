package com.example.dev.logobin.handel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.dev.logobin.model.M_Daste;
import com.example.dev.logobin.model.M_Kala;

import java.util.ArrayList;

public class DB_DasteBandi {
        myDbHelper myhelper;
        ArrayList<M_Daste> Daste;
        public DB_DasteBandi(Context context)
        {
            myhelper = new myDbHelper(context);

        }


        public long insertData(String ID,
                               String title
                               )
        {

            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.ID,ID);
            contentValues.put(myDbHelper.Title, title);

            long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
            Log.i("DatabaseKala","InsertData");
            return id;
        }

        public ArrayList<M_Daste>  getData()
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {
                    myDbHelper.UID,
                    myDbHelper.ID,
                    myDbHelper.Title,
            };
            Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
            Daste =new ArrayList<>();
            while (cursor.moveToNext())
            {
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String id =cursor.getString(cursor.getColumnIndex(myDbHelper.ID));
//                int id= Integer.parseInt(Sid);
                String  title =cursor.getString(cursor.getColumnIndex(myDbHelper.Title));
                Daste.add(new M_Daste(id,title));
            }
//            cursor.close();
            Log.i("DatabaseKala","Getkala");
            return Daste;
        }
//Delete_Update

        public  int delete()
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            Log.i("DatabaseBrand","Delete");

            int count =db.delete(myDbHelper.TABLE_NAME , null ,null);
            return  count;
        }

        public ArrayList<M_Daste> where(String Factory_Id) {


            SQLiteDatabase db = myhelper.getWritableDatabase();
            ArrayList<M_Daste> Where = new ArrayList<>();

            Cursor cursor= db.rawQuery("SELECT * from " + myDbHelper.TABLE_NAME
                    + " WHERE Factory_id= "+Factory_Id , null);

            if (cursor.moveToFirst()) {

                while (!cursor.isAfterLast()){
                    String id =cursor.getString(cursor.getColumnIndex(myDbHelper.ID));
//                int id= Integer.parseInt(Sid);
                    String  title =cursor.getString(cursor.getColumnIndex(myDbHelper.Title));

                    Where.add(new M_Daste(id,title));

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
            private static final String DATABASE_NAME = "BD_Daste";    // Database Name
            private static final String TABLE_NAME = "Daste";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String UID="_id";     // Column I (Primary Key)
            private static final String ID = "id";
            private static final String Title = "title";



            private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                    " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ID +" VARCHAR(255) ,"
                    + Title +" VARCHAR(255));";
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



