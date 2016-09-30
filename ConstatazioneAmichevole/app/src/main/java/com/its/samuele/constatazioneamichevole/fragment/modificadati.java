package com.its.samuele.constatazioneamichevole.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.samuele.constatazioneamichevole.R;
import com.its.samuele.constatazioneamichevole.activity.MainActivity;

/**
 * Created by Utente on 27/09/2016.
 */

public class modificadati extends AppCompatActivity {
    EditText mData, mOra, mFeriti;
    Button mModifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);

        mData = (EditText) findViewById(R.id.data);
        mFeriti = (EditText) findViewById(R.id.feriti);
        mOra = (EditText) findViewById(R.id.ora);

        mModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vIntent = new Intent(modificadati.this, MainActivity.class);
                Bundle vBundle = new Bundle();
                vIntent.putExtras(vBundle);
                startActivity(vIntent);
                finish();
            }
        });
    }

}