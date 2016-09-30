package com.its.samuele.constatazioneamichevole.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.its.samuele.constatazioneamichevole.R;
import com.its.samuele.constatazioneamichevole.database.SinistroDB;
import com.its.samuele.constatazioneamichevole.fragment.BottoniFragment;
import com.its.samuele.constatazioneamichevole.fragment.DanniFragment;
import com.its.samuele.constatazioneamichevole.fragment.FeritiFragment;
import com.its.samuele.constatazioneamichevole.fragment.LuogoFragment;
import com.its.samuele.constatazioneamichevole.fragment.TestimoniFragment;
import com.its.samuele.constatazioneamichevole.utility.Comune;
import com.its.samuele.constatazioneamichevole.utility.Provincia;
import com.its.samuele.constatazioneamichevole.utility.Regione;
import com.its.samuele.constatazioneamichevole.utility.Testimone;

import junit.framework.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Utente on 23/09/2016.
 */

public class NuovaConstatazione extends AppCompatActivity
        implements
        DanniFragment.IDanni,
        FeritiFragment.IFeriti,
        TestimoniFragment.ITestimoni,
        LuogoFragment.ILuogo,
        BottoniFragment.IBottono
        {

    ContentValues values =new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        BottoniFragment bottoni =new BottoniFragment();



        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,bottoni,"Tag");
        fragmentTransaction.commit();


    }

    public void aggiornaSinistro () {

        SQLiteDatabase database = new SinistroDB(NuovaConstatazione.this).getReadableDatabase();
        //database.update("dettagliosinistri",values,,);

    }

    @Override
    public boolean changeDanni(boolean a, boolean b) {
        values.put("VEICOLI",a);
        values.put("OGGETTI",b);
        return false;
    }

    @Override
    public boolean changeFeriti(boolean b) {
        values.put("FERITI",b);
        return false;
    }

    @Override
    public void changeTestimoni(Testimone testimone) {


    }

    @Override
    public void changeLuogo() {

    }
            @Override
            public void onButtonPress(Fragment f) {


            }
        }
