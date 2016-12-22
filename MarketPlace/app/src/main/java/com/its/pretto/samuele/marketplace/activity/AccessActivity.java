package com.its.pretto.samuele.marketplace.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.its.pretto.samuele.marketplace.R;
import com.its.pretto.samuele.marketplace.fragment.FragmentDialogSocial;
import com.its.pretto.samuele.marketplace.support.CipherClass;
import com.its.pretto.samuele.marketplace.support.Utente;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AccessActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    EditText edtEmail, edtPass;
    Button btnAccedi, btnSocial;
    ProgressDialog dialog;
    LoginButton mLogin;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    CipherClass cipherClass;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState==null){
            try {
                cipherClass =CipherClass.getInstance(getApplicationContext());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }

        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_access);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        btnSocial = (Button) findViewById(R.id.buttonSocial);

        mLogin = (LoginButton) findViewById(R.id.loginButtonFacebook);
        edtEmail = (EditText) findViewById(R.id.editTextLogEmail);
        edtPass = (EditText) findViewById(R.id.editLogPassword);

        btnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentDialogSocial fragmentDialogSocial = new FragmentDialogSocial();
                fragmentDialogSocial.show(getFragmentManager(),"Social");
            }
        });

        mLogin.setPublishPermissions("publish_actions");
        mLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("aaa","Connesso con successo");
                Intent intent = new Intent(AccessActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                Toast.makeText(AccessActivity.this, "Hai annullato il login su Facebook", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(AccessActivity.this, "Errore di login", Toast.LENGTH_SHORT).show();
            }
        });

        btnAccedi = (Button) findViewById(R.id.buttonAccedi);

        btnAccedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtEmail.length()>0&&edtPass.length()>0){
                    LogAsychTask logAsychTask = new LogAsychTask();
                    logAsychTask.execute(new Utente(edtEmail.getText().toString(),edtPass.getText().toString(),null,null,null));
                }
                else {
                    Toast.makeText(AccessActivity.this, "Errore", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    class LogAsychTask extends AsyncTask<Utente, Void, String>{
        JSONObject jsonObject;
        String result;

        @Override
        protected String doInBackground(Utente... utentes) {

            Utente utente = utentes[0];
            Utente decryptoUtente = new Utente();
            String email =utente.getMail();
            String password = utente.getPass();


            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("mail",email)
                    .add("password",password)
                    .build();
            Request request = new Request.Builder()
                    .url("http://tommo.altervista.org/ITS/annunci/login.php")
                    .post(formBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                result = response.body().string();
                jsonObject = new JSONObject(result);
                String cryptC_simmetrica = jsonObject.getJSONObject("user").getString("c_simmetrica");
                String cryptNome = jsonObject.getJSONObject("user").getString("nome");
                String cryptCognome = jsonObject.getJSONObject("user").getString("cognome");
                String cryptIndirizzo = jsonObject.getJSONObject("user").getString("indirizzo");
                SecretKey databaseSymKey = new SecretKeySpec(Base64.decode(cipherClass.decrypt(cryptC_simmetrica,"RSA","private"),Base64.NO_WRAP),"AES");

                //Controllo se la chiave simmetrica del database è uguale a quella della classe
                if (databaseSymKey.equals(cipherClass.getSymmetricKey())){
                    Log.d("aaa","Sono uguali");
                    cipherClass.setSymmetricKey(databaseSymKey);
                }
                decryptoUtente = new Utente(email,password,cipherClass.decrypt(cryptNome,"AES","symmetric"),
                        cipherClass.decrypt(cryptCognome,"AES","symmetric"),
                        cipherClass.decrypt(cryptIndirizzo,"AES","symmetric"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }

            return decryptoUtente.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("aaa",s);
            try {
                if (jsonObject.getBoolean("success")){
                    startActivity(new Intent(AccessActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(AccessActivity.this, "Login fallito", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AccessActivity.this);
            dialog.setMessage("Please wait");
            dialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if (requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("google","Connection Failed!");
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("google", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.d("google", "Login eseguito con successo");
            if (result.isSuccess()){
                Intent intent = new Intent(AccessActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            // Signed out, show unauthenticated UI.
            Log.d("google","Login non eseguito");
        }
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
