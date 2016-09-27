package com.its.samuele.farmalistav2.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.its.samuele.farmalistav2.R;
import com.its.samuele.farmalistav2.presenter.LoginPresenter;
import com.its.samuele.farmalistav2.view.LoginView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by samuele.pretto on 31/08/2016.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends RoboActivity implements LoginView {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 1;

    String email, password;

    @InjectView(R.id.input_email)
    private EditText emailEditText;

    @InjectView(R.id.input_password)
    private EditText passwordEditText;

    @InjectView(R.id.btn_login)
    private Button btnLogin;

    @InjectView(R.id.link_registrazione)
    private TextView linkRegistrazione;

    @InjectView(R.id.wrapper)
    private ScrollView wrapper;

    @Inject
    private LoginPresenter loginPresenter;

    ExtLogin extLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenterView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extLogin = new ExtLogin();
                validate();
                extLogin.login(email, password);
                Intent intent = new Intent(getApplicationContext(), AppActivity.class);
                startActivity(intent);
            }
        });

        linkRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PresentationActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    private void setPresenterView() {
        loginPresenter.setView(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                //TODO: implementare logica di registrazione qui
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Inserisci un indirizzo email valido");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordEditText.setError("La password deve essere di una lunghezza compresa fra i 4 e i 10 caratteri");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }
        return valid;
    }

}
