package com.example.dev.logobin.Network;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.logobin.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by majid on 8/22/2018.
 */

public class Post extends Activity implements View.OnClickListener {

    @Override
    public void onClick(View v) {

    }

    public class MyHttpPostProjectActivity extends Activity implements View.OnClickListener {

        private EditText Regester_Edittext_PhonNumber;
        private EditText Regester_Edittext_Name;
        private EditText Regester_Edittext_Famely;
        private EditText Regester_Edittext_Adress;

        private TextView  Regester_TextView_Submet;
        private TextView  Regester_TextView_SingUP;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register_ui);

            Regester_Edittext_PhonNumber = (EditText) findViewById(R.id.Register_Edittext_Phone);
            Regester_Edittext_Name = (EditText) findViewById(R.id.Register_Edittext_Name);
            Regester_Edittext_Famely = (EditText) findViewById(R.id.Register_Edittext_Family);
            Regester_Edittext_Adress = (EditText) findViewById(R.id.Register_Edittext_Adress);

            Regester_TextView_Submet = (TextView) findViewById(R.id.Register_Textview_Register);
            Regester_TextView_Submet.setOnClickListener(this);

            Regester_TextView_SingUP = (TextView) findViewById(R.id.Register_Textview_Remember);
            Regester_TextView_SingUP.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

//            if(v.getId() == R.id.login_clear_button){
//                usernameEditText.setText("");
//                passwordEditText.setText("");
//                passwordEditText.setCursorVisible(false);
//                passwordEditText.setFocusable(false);
//                usernameEditText.setCursorVisible(true);
//                passwordEditText.setFocusable(true);
//            }
            if(v.getId() == R.id.Register_Textview_Register){
                String givenPhoneNumer = Regester_Edittext_PhonNumber.getEditableText().toString();
                String givenname = Regester_Edittext_Name.getEditableText().toString();
                String givenfamily = Regester_Edittext_Famely.getEditableText().toString();
                String givenAdress = Regester_Edittext_Adress.getEditableText().toString();

                System.out.println("GivenPhone :" + givenPhoneNumer +"Given username :" + givenname + " Given family :" + givenfamily+ "Given Adress :" + givenAdress );

                sendPostRequest(givenPhoneNumer,givenname,givenfamily,givenAdress);
            }
        }

        private void sendPostRequest(String givenphoneNumber, String givenname,String givefamily,String givenAdress) {

            class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

                @Override
                protected String doInBackground(String... params) {

                    String paramphoneNumber = params[0];
                    String paramname = params[1];
                    String paramfamily = params[2];
                    String paramAdress = params[3];

                    System.out.println("*** doInBackground ** paramphoneNumber " + paramphoneNumber + " paramname :" + paramname+ " paramfamily :" + paramfamily+ " paramAdress :" + paramAdress);

                    HttpClient httpClient = new DefaultHttpClient();

                    // In a POST request, we don't pass the values in the URL.
                    //Therefore we use only the web page URL as the parameter of the HttpPost argument
                    HttpPost httpPost = new HttpPost("http://www.nirmana.lk/hec/android/postLogin.php");

                    // Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
                    //uniquely separate by the other end.
                    //To achieve that we use BasicNameValuePair
                    //Things we need to pass with the POST request
                    BasicNameValuePair phoneBasicNameValuePair = new BasicNameValuePair("paramphoneNumber", paramphoneNumber);
                    BasicNameValuePair nameBasicNameValuePAir = new BasicNameValuePair("paramname", paramname);
                    BasicNameValuePair familyBasicNameValuePAir = new BasicNameValuePair("paramfamily", paramfamily);
                    BasicNameValuePair AdressBasicNameValuePAir = new BasicNameValuePair("paramAdress", paramAdress);

                    // We add the content that we want to pass with the POST request to as name-value pairs
                    //Now we put those sending details to an ArrayList with type safe of NameValuePair
                    List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                    nameValuePairList.add(phoneBasicNameValuePair);
                    nameValuePairList.add(nameBasicNameValuePAir);
                    nameValuePairList.add(familyBasicNameValuePAir);
                    nameValuePairList.add(AdressBasicNameValuePAir);

                    try {
                        // UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs.
                        //This is typically useful while sending an HTTP POST request.
                        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

                        // setEntity() hands the entity (here it is urlEncodedFormEntity) to the request.
                        httpPost.setEntity(urlEncodedFormEntity);

                        try {
                            // HttpResponse is an interface just like HttpPost.
                            //Therefore we can't initialize them
                            HttpResponse httpResponse = httpClient.execute(httpPost);

                            // According to the JAVA API, InputStream constructor do nothing.
                            //So we can't initialize InputStream although it is not an interface
                            InputStream inputStream = httpResponse.getEntity().getContent();

                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                            StringBuilder stringBuilder = new StringBuilder();

                            String bufferedStrChunk = null;

                            while((bufferedStrChunk = bufferedReader.readLine()) != null){
                                stringBuilder.append(bufferedStrChunk);
                            }

                            return stringBuilder.toString();

                        } catch (ClientProtocolException cpe) {
                            System.out.println("First Exception caz of HttpResponese :" + cpe);
                            cpe.printStackTrace();
                        } catch (IOException ioe) {
                            System.out.println("Second Exception caz of HttpResponse :" + ioe);
                            ioe.printStackTrace();
                        }

                    } catch (UnsupportedEncodingException uee) {
                        System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
                        uee.printStackTrace();
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);

                    if(result.equals("working")){
                        Toast.makeText(getApplicationContext(), "HTTP POST is working...", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Invalid POST req...", Toast.LENGTH_LONG).show();
                    }
                }
            }

            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
            sendPostReqAsyncTask.execute(givenphoneNumber, givenname,givefamily,givenAdress);
        }
    }
}
