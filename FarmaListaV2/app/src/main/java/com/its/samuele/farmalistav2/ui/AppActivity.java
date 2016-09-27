package com.its.samuele.farmalistav2.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.its.samuele.farmalistav2.R;
import com.its.samuele.farmalistav2.view.LoginView;

import roboguice.activity.RoboActivity;

/**
 * Created by Utente on 09/09/2016.
 */
public class AppActivity extends AppCompatActivity {

    private static final String TAG = "gat";
    ImageView profileButton;
    Toolbar toolbar;
    ListaMedicinaliFragment listaMedicinaliFragment;
    FragmentManager manager;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        toolbar = (Toolbar) findViewById(R.id.toolbarApp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lista medicinali");
        getSupportActionBar().setSubtitle("FarmaLista");

        profileButton = (ImageView) findViewById(R.id.profile_button);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileFragment = new ProfileFragment();
                manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.container, profileFragment, "gat");
                fragmentTransaction.commit();
            }
        });

        if (savedInstanceState == null){
            listaMedicinaliFragment = new ListaMedicinaliFragment();
            manager = getFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.container, listaMedicinaliFragment,"gat");
            fragmentTransaction.commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(TAG, true);
    }
}
