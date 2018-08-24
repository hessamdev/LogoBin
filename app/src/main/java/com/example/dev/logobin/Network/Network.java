//package com.example.dev.logobin.Network;
//
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * Created by majid on 8/22/2018.
// */
//
//public class Network {
//
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//
//    public class Codes extends AppCompatActivity {
//
//
//
//        RequestQueue req;
//        Context context;
//
//
//        String url = "http://192.168.43.163/music.php?key=";
//        String url2 = "http://192.168.43.163/music.php?key=";
//        public static MediaPlayer mediaPlayer;
//
//        public void getData(final Context con, final List<Music> list1,final RecyclerView recyclerView, String cat, RequestQueue req
//        ){
//            this.req = req;
//            req = Volley.newRequestQueue(con);
//            String link = url+cat;
//            Log.i("1234", link);
//
//            JsonObjectRequest job = new JsonObjectRequest(Request.Method.POST, link, null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    Log.i("1234", String.valueOf(response));
//
//
//
//                    } catch (Exception e){
//                        StringWriter sw = new StringWriter();
//                        PrintWriter pw = new PrintWriter(sw);
//                        e.printStackTrace(pw);
//                        String sStackTrace = sw.toString();
//                        Toast.makeText(con, sStackTrace, Toast.LENGTH_SHORT).show();
//                        //Log.i("error", String.valueOf(e));
//
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    StringWriter sw = new StringWriter();
//                    PrintWriter pw = new PrintWriter(sw);
//                    error.printStackTrace(pw);
//                    String sStackTrace = sw.toString();
//                    Toast.makeText(con,sStackTrace, Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//
//        }
//
//
//
//            req.add(job);
//
//        }
//
//    }
//
//
//}
