package com.its.pretto.samuele.marketplace.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.its.pretto.samuele.marketplace.R;
import com.its.pretto.samuele.marketplace.fragment.FragmentAddAds;
import com.its.pretto.samuele.marketplace.fragment.FragmentAds;

public class MainActivity extends AppCompatActivity implements FragmentAds.IFragmentAds{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState==null){
            getFragmentManager().beginTransaction().add(R.id.container, new FragmentAds()).commit();
        }

    }

    @Override
    public void pushPlus() {
        getFragmentManager().beginTransaction().replace(R.id.container,new FragmentAddAds()).commit();
    }
}
