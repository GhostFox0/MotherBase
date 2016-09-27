package com.its.samuele.farmalistav2.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.its.samuele.farmalistav2.R;

import java.util.HashMap;

/**
 * Created by Utente on 02/09/2016.
 */
public class SignupFragment extends Fragment {

    EditText textEmail, textPassword;
    Button btnRegistrati;
    TextView linkNormativa;
    FragmentManager manager;
    PrivacyFragment privacyFragment;
    SignInterface listener;
    private static final String REGISTER_URL = "http://farmalista.96.lt/login.php";


    public interface SignInterface {
        public void setTitleActivity(String title);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_signup,container,false);

        manager = getFragmentManager();

        textEmail = (EditText) view.findViewById(R.id.input_email);

        textPassword = (EditText) view.findViewById(R.id.input_password);

        btnRegistrati = (Button) view.findViewById(R.id.btn_register);

        linkNormativa = (TextView) view.findViewById(R.id.link_normativa);

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        linkNormativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setTitleActivity("Normativa");
            }
        });

        return view;
    }

    private void registerUser(){
        String email = textEmail.getText().toString().trim().toLowerCase();
        String password = textPassword.getText().toString().trim().toLowerCase();

        register(email,password);
    }

    private void register(String email, String password){
        class RegisterUser extends AsyncTask<String, Void,String>{
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Prego aspettare", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("email",params[0]);
                data.put("password",params[1]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);
                Log.d("out",result);

                return result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(email,password);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof SignInterface){
            listener = (SignInterface) activity;
        }
        else {
            listener = null;
        }
    }
}