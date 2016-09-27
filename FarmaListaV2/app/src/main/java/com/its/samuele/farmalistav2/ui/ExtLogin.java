package com.its.samuele.farmalistav2.ui;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

public class ExtLogin {

    String smail, spassword, pass;

    public static String getSalt(){
        String uuid = "margherita";
        return uuid;
    }
    public static String getEncryptedPassword(String salt, String password){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            md.update(salt.getBytes());

            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public void login(String email, String keyPass){
        smail = email;
        spassword = keyPass;
        pass = getEncryptedPassword(getSalt(), spassword);

        if (spassword.length() != 0) {


            pass = getEncryptedPassword(getSalt(), spassword);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("mail", smail));
            params.add(new BasicNameValuePair("password", pass));

            HttpEntity httpEntity = null;

            String url = "http://serverdatizancan.esy.es/getUser.php?mail=" + smail + "&password=" + spassword;

            try
            {

                DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient

                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);

                httpEntity = httpResponse.getEntity();



            } catch (ClientProtocolException e) {

                // Signals error in http protocol
                e.printStackTrace();

                //Log Errors Here



            } catch (IOException e) {
                e.printStackTrace();
            }


            // Convert HttpEntity into JSON Array
            JSONArray jsonArray = null;

            if (httpEntity != null) {
                try {
                    String entityResponse = EntityUtils.toString(httpEntity);

                    Log.e("Entity Response  : ", entityResponse);

                    jsonArray = new JSONArray(entityResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}