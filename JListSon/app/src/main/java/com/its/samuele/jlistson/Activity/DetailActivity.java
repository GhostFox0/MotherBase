package com.its.samuele.jlistson.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.its.samuele.jlistson.R;

/**
 * Created by Samuele.Pretto on 05/10/2016.
 */

public class DetailActivity extends AppCompatActivity{
    Bundle bundle;
    String id,name,surname;
    TextView txtId, txtName, txtSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        bundle = getIntent().getExtras();
        id= bundle.getString("ID");
        name = bundle.getString("NAME");
        surname =bundle.getString("SURNAME");

        txtId = (TextView) findViewById(R.id.textViewId);
        txtName = (TextView) findViewById(R.id.textViewName);
        txtSurname = (TextView) findViewById(R.id.textViewSurname);

        txtId.setText(id);
        txtName.setText(name);
        txtSurname.setText(surname);


    }
}
