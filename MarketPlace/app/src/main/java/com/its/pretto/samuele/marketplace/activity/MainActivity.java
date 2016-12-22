package com.its.pretto.samuele.marketplace.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        final MenuItem accountItem = menu.findItem(R.id.action_user);
        accountItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MainActivity.this,AccountActivity.class));
                return true;
            }
        });
        return true;
    }
}
