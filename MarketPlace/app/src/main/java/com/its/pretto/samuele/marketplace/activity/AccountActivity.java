package com.its.pretto.samuele.marketplace.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.its.pretto.samuele.marketplace.R;

public class AccountActivity extends AppCompatActivity {

    TextView textUser;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        textUser = (TextView) findViewById(R.id.textViewUser);

        textUser.setText(preferences.getString("user",null));

    }
}
