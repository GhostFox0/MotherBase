package com.its.pretto.samuele.marketplace.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.its.pretto.samuele.marketplace.R;

public class LoginActivity extends AppCompatActivity {

    Button btnAccedi, btnRegistrati;
    Intent intent;
    String process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnAccedi = (Button) findViewById(R.id.buttonAccedi);
        btnRegistrati = (Button) findViewById(R.id.buttonRegister);


        btnAccedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this,AccessActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
