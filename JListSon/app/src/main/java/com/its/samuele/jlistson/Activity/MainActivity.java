package com.its.samuele.jlistson.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.its.samuele.jlistson.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    ListView mListView;
    ArrayList<HashMap<String, String>> listPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listJSON);
        listPeople = new ArrayList<>();

        new GetJSON().execute();

    }

    private class GetJSON extends AsyncTask{

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,listPeople,R.layout.list_layout, new String[]{"name","surname"}, new int[]{R.id.textViewName, R.id.textViewSurname});
            mListView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage("Attendere prego...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            String jsonStr ="";
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://www.json-generator.com/api/json/get/bKqyWffeGa?indent=2")
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                jsonStr = response.body().string().trim();
                JSONObject reader = new JSONObject(jsonStr);
                JSONArray users = reader.getJSONArray("users");

                for (int i =0; i<users.length(); i++){
                    JSONObject objTemp = users.getJSONObject(i);
                    JSONArray tmpArray = objTemp.getJSONArray("User");
                    for (int x=0; x<tmpArray.length();x++){
                        JSONObject tmp = tmpArray.getJSONObject(x);
                        String tSurname = tmp.optString("surname");
                        String tid = tmp.optString("id");
                        String tname = tmp.optString("name");
                        HashMap<String, String> person = new HashMap<>();
                        person.put("surname", tSurname);
                        person.put("id", tid);
                        person.put("name", tname);
                        listPeople.add(person);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
