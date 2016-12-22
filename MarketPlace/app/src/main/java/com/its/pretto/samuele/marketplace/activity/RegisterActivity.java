package com.its.pretto.samuele.marketplace.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.its.pretto.samuele.marketplace.R;
import com.its.pretto.samuele.marketplace.support.CipherClass;
import com.its.pretto.samuele.marketplace.support.Utente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmail, edtPass, edtName,edtSurname,edtAddress;
    Button btnRegister;
    ProgressDialog dialog;
    CipherClass cipherClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (savedInstanceState==null){
            try {
                cipherClass = CipherClass.getInstance(getApplicationContext());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }

        edtName = (EditText) findViewById(R.id.editTextName);

        edtSurname = (EditText) findViewById(R.id.editTextSurname);

        edtAddress = (EditText) findViewById(R.id.editTextAddress);

        edtEmail = (EditText) findViewById(R.id.editTextRegEmail);

        edtPass = (EditText) findViewById(R.id.editTextRegPassword);

        btnRegister = (Button) findViewById(R.id.buttonReg);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtEmail.length()>0&&edtPass.length()>0&&edtAddress.length()>0&&edtSurname.length()>0&&edtName.length()>0){
                    RegAsyncTask regAsyncTask = new RegAsyncTask();
                    regAsyncTask.execute(new Utente(edtEmail.getText().toString(),edtPass.getText().toString(),edtName.getText().toString(),edtSurname.getText().toString(),edtAddress.getText().toString()));
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Devi compilare tutti i campi", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    class RegAsyncTask extends AsyncTask<Utente, Void,String>{


        @Override
        protected String doInBackground(Utente... utentes) {
            Utente utente = utentes[0];
            Utente criptoUtente = new Utente();

            try {
                criptoUtente.setName(cipherClass.encrypt(utente.getName(),"AES","symmetric"));
                criptoUtente.setSurn(cipherClass.encrypt(utente.getSurn(),"AES","symmetric"));
                criptoUtente.setMail(cipherClass.encrypt(utente.getMail(),"AES","symmetric"));
                criptoUtente.setMail(utente.getMail());
                criptoUtente.setPass(utente.getPass());
                criptoUtente.setAddress(cipherClass.encrypt(utente.getAddress(),"AES","symmetric"));
                criptoUtente.setC_pubblica(cipherClass.encrypt(Base64.encodeToString(cipherClass.getPublicKey().getEncoded(),Base64.NO_WRAP),"AES","symmetric"));
                criptoUtente.setC_simmetrica(cipherClass.encrypt(Base64.encodeToString(new SecretKeySpec(cipherClass.getSymmetricKey().getEncoded(),"AES").getEncoded(),Base64.NO_WRAP),"RSA","public"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("mail",criptoUtente.getMail())
                    .add("password",criptoUtente.getPass())
                    .add("c_pubblica",criptoUtente.getC_pubblica())
                    .add("c_simmetrica",criptoUtente.getC_simmetrica())
                    .add("nome",criptoUtente.getName())
                    .add("cognome",criptoUtente.getSurn())
                    .add("indirizzo",criptoUtente.getAddress())
                    .build();

            Request request = new Request.Builder()
                    .url("http://tommo.altervista.org/ITS/annunci/register.php")
                    .post(formBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String result = response.body().string();
                Log.d("aaa",result);
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.getBoolean("success")){
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Registrazione fallita!", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            utente.setC_pubblica(criptoUtente.getC_pubblica());
            utente.setC_simmetrica(criptoUtente.getC_simmetrica());
            editor.putString("user",utente.toString());
            editor.commit();
            return criptoUtente.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("aaa",s);
            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(RegisterActivity.this);
            dialog.setMessage("Please wait");
            dialog.show();

        }
    }

}
