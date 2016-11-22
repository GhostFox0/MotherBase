package com.its.pretto.samuele.marketplace.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.its.pretto.samuele.marketplace.R;
import com.its.pretto.samuele.marketplace.database.Ad;
import com.its.pretto.samuele.marketplace.database.AdAdapter;
import com.its.pretto.samuele.marketplace.database.AdDatabase;
import com.its.pretto.samuele.marketplace.database.AdTableHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Samuele.Pretto on 15/11/2016.
 */

public class FragmentAds extends Fragment {

    ProgressDialog mDialog;
    SQLiteDatabase vData;
    ListView mListView;
    FloatingActionButton floatingActionButton;


    public interface IFragmentAds{
        public void pushPlus();
    }

    IFragmentAds listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

        mListView = (ListView) rootView.findViewById(R.id.listview_ads);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.FAB);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.pushPlus();
            }
        });

        vData = new AdDatabase(getActivity()).getReadableDatabase();
        new FetchAdsTask().execute();
        return rootView;
    }

    public class FetchAdsTask extends AsyncTask<Void,Void,String>{

        String result;

        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://tommo.altervista.org/ITS/annunci/getall.php")
                    .get()
                    .build();

            try {
                Response response = client.newCall(request).execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog = new ProgressDialog(getContext());
            mDialog.setMessage("Please wait");
            mDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            JSONArray jsonArray = null;
            ContentValues values;
            try {
                jsonArray = new JSONArray(s);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i=0; i< jsonArray.length();i++){
                try {

                    values = new ContentValues();
                    JSONObject object = jsonArray.getJSONObject(i);
                    Cursor vCursor = vData.query(AdTableHelper.TABLE_NAME,new String[]{AdTableHelper._ID},AdTableHelper._ID+" = "+object.getInt("id"),null,null,null,null);
                    if (vCursor.getCount()<=0){
                        values.put(AdTableHelper._ID,object.getString("id"));
                        values.put(AdTableHelper.TITLE,object.getString("titolo"));
                        values.put(AdTableHelper.DESCRIPTION,object.getString("descrizione"));
                        values.put(AdTableHelper.IMAGE,object.getString("immagine"));
                        values.put(AdTableHelper.PRICE,object.getString("prezzo"));
                        values.put(AdTableHelper.DATE,object.getString("data"));
                        values.put(AdTableHelper.COMUNE,object.getString("comune"));
                        values.put(AdTableHelper.PROVINCIA,object.getString("provincia"));
                        values.put(AdTableHelper.REGIONE,object.getString("regione"));
                        values.put(AdTableHelper.MAIL,object.getString("mail"));
                        values.put(AdTableHelper.CATEGORIE_ID,object.getInt("categorie_id"));
                        vData.insert(AdTableHelper.TABLE_NAME,null,values);
                        vCursor.close();
                    }
                    else {
                        vCursor.close();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            Cursor vCursor = vData.query(AdTableHelper.TABLE_NAME,null,null,null,null,null,null);
            int vIndexId = vCursor.getColumnIndex(AdTableHelper._ID);
            int vIndexTitle = vCursor.getColumnIndex(AdTableHelper.TITLE);
            int vIndexImage = vCursor.getColumnIndex(AdTableHelper.IMAGE);
            int vIndexPrice = vCursor.getColumnIndex(AdTableHelper.PRICE);
            int vIndexDate = vCursor.getColumnIndex(AdTableHelper.DATE);
            int vIndexComune = vCursor.getColumnIndex(AdTableHelper.COMUNE);
            int vIndexProvincia = vCursor.getColumnIndex(AdTableHelper.PROVINCIA);
            int vIndexRegione = vCursor.getColumnIndex(AdTableHelper.REGIONE);
            int vIndexMail = vCursor.getColumnIndex(AdTableHelper.MAIL);
            int vIndexCategorie_Id = vCursor.getColumnIndex(AdTableHelper.CATEGORIE_ID);

            ArrayList<Ad> ads = new ArrayList<>();
            while (vCursor.moveToNext()){
                Ad vAd = new Ad();
                vAd.set_id(vCursor.getInt(vIndexId));
                vAd.setTitle(vCursor.getString(vIndexTitle));
                vAd.setImage(vCursor.getString(vIndexImage));
                vAd.setPrice(vCursor.getString(vIndexPrice));
                vAd.setDate(vCursor.getString(vIndexDate));
                vAd.setComune(vCursor.getString(vIndexComune));
                vAd.setProvincia(vCursor.getString(vIndexProvincia));
                vAd.setRegione(vCursor.getString(vIndexRegione));
                vAd.setMail(vCursor.getString(vIndexMail));
                vAd.setCategorie_id(vCursor.getInt(vIndexCategorie_Id));
                ads.add(vAd);
            }
            vCursor.close();
            vData.close();
            AdAdapter vAdAdapter = new AdAdapter(getActivity(),ads);
            mListView.setAdapter(vAdAdapter);
            mDialog.dismiss();
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IFragmentAds){
            listener = (IFragmentAds) activity;
        }
        else {
            listener = null;
        }
    }
}
