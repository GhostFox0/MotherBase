package com.its.samuele.farmalistav2.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.its.samuele.farmalistav2.R;
import com.its.samuele.farmalistav2.view.LoginView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

/**
 * Created by Utente on 07/09/2016.
 */
public class PresentationActivity extends AppCompatActivity implements SignupFragment.SignInterface {

    private static final String TAG = "tag";
    Toolbar toolbar;
    SignupFragment signupFragment;
    FragmentManager fragmentManager;
    String localTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        toolbar = (Toolbar) findViewById(R.id.toolbarPresentation);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Registrazione");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (savedInstanceState == null){

            signupFragment = new SignupFragment();
            fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,signupFragment,"tag");
            fragmentTransaction.commit();
        }
    }

    public void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbarPresentation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrazione");

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(TAG, true);
    }

    @Override
    public void setTitleActivity(String title) {
        localTitle = title;
        toolbar.setTitle(title);
        PrivacyFragment privacyFragment = new PrivacyFragment();
        FragmentTransaction vTransaction = fragmentManager.beginTransaction();
        vTransaction.replace(R.id.container, privacyFragment,"TAG");
        vTransaction.commit();
    }
}
