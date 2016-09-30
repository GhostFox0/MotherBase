package com.its.samuele.imagecaptor.activity;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.its.samuele.imagecaptor.R;
import com.its.samuele.imagecaptor.fragment.PhotoFragment;

public class MainActivity extends AppCompatActivity {

    PhotoFragment photoFragment;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoFragment = new PhotoFragment();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container,photoFragment,null);
        transaction.commit();
    }
}
