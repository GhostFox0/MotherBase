package inc.sam.cipherlogin;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements RegisterFragment.IRegisterFragment, LoginFragment.ILoginFragment {

    Button btnBeginLog, btnBeginReg;
    Cipher cipherAES, cipherRSA, decipherAES, decipherRSA;
    KeyGenerator generator;
    SecretKey symmetricKey;
    KeyPairGenerator kpg;
    KeyPair kp;
    PublicKey publicKey;
    PrivateKey privateKey;
    RegisterFragment registerFragment;
    LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState==null){

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            if(!preferences.getBoolean("firstTime",false)){
                try {
                    generator = KeyGenerator.getInstance("AES");

                    generator.init(128);
                    symmetricKey = generator.generateKey();
                    kpg = KeyPairGenerator.getInstance("RSA");

                    kpg.initialize(1024);
                    kp = kpg.genKeyPair();
                    publicKey = kp.getPublic();
                    privateKey = kp.getPrivate();

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("symmetricKey",Base64.encodeToString(symmetricKey.getEncoded(),Base64.DEFAULT));

                    editor.putString("privateKey",Base64.encodeToString(privateKey.getEncoded(),Base64.DEFAULT));
                    editor.putString("publicKey",Base64.encodeToString(publicKey.getEncoded(),Base64.DEFAULT));
                    editor.commit();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

            }
            else{

                    symmetricKey = new SecretKeySpec(preferences.getString("symmetricKey",null).getBytes(),"AES");

                try {
                    publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(preferences.getString("publicKey",null).getBytes("utf-8")));
                    privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(preferences.getString("privateKey",null).getBytes("utf-8")));
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("firstTime", true);
                editor.commit();



            registerFragment = new RegisterFragment();
            getFragmentManager().beginTransaction().add(R.id.container,registerFragment).commit();
            try {

                cipherAES = Cipher.getInstance("AES");
                cipherAES.init(Cipher.ENCRYPT_MODE,symmetricKey);
                cipherRSA = Cipher.getInstance("RSA");
                cipherRSA.init(Cipher.ENCRYPT_MODE,publicKey);
                decipherRSA =Cipher.getInstance("RSA");
                decipherRSA.init(Cipher.DECRYPT_MODE,privateKey);
                decipherAES = Cipher.getInstance("AES");

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }

        btnBeginLog = (Button) findViewById(R.id.buttonLoginFrag);

        btnBeginLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFragment = new LoginFragment();
                getFragmentManager().beginTransaction().replace(R.id.container,loginFragment).commit();
            }
        });

        btnBeginReg = (Button) findViewById(R.id.buttonRegFrag);

        btnBeginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container,registerFragment).commit();
            }
        });

    }

    @Override
    public void pushRegFragReg(Utente u) {
        new FetchRegTask().execute(u);
    }

    @Override
    public void pushLogFragLog(Utente u) {
        new FetchLogTask().execute(u);
    }

    public class FetchLogTask extends AsyncTask<Utente,Void,String>{

        @Override
        protected String doInBackground(Utente... utentes) {
            Utente utente = utentes[0];
            Utente decryptoUtente = new Utente();
            String email ="";
            String password ="";

            try {

                email =Base64.encodeToString(cipherAES.doFinal(utente.getMail().getBytes("UTF-8")),Base64.DEFAULT);
                password = Base64.encodeToString(cipherAES.doFinal(utente.getPass().getBytes("UTF-8")),Base64.DEFAULT);

            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
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
                String result = response.body().string();
                JSONObject jsonObject = new JSONObject(result);
                String cryptC_simmetrica = jsonObject.getJSONObject("user").getString("c_simmetrica");
                String cryptNome = jsonObject.getJSONObject("user").getString("nome");
                String cryptCognome = jsonObject.getJSONObject("user").getString("cognome");
                String cryptIndirizzo = jsonObject.getJSONObject("user").getString("indirizzo");
                byte[] encodeKey = decipherRSA.doFinal(Base64.decode(cryptC_simmetrica.getBytes(),Base64.DEFAULT));
                SecretKey decryptC_simmetrica = new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
                decipherAES.init(Cipher.DECRYPT_MODE,decryptC_simmetrica);
                decryptoUtente = new Utente(email,password,new String(decipherAES.doFinal(Base64.decode(cryptNome.getBytes(),Base64.DEFAULT))),
                        new String(decipherAES.doFinal(Base64.decode(cryptCognome.getBytes(),Base64.DEFAULT))),
                        new String(decipherAES.doFinal(Base64.decode(cryptIndirizzo.getBytes(),Base64.DEFAULT))));
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
            }

            return decryptoUtente.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loginFragment.setLogText(s);
        }
    }


    public class FetchRegTask extends AsyncTask<Utente,Void,String>{

        @Override
        protected String doInBackground(Utente... utentes) {
            Utente utente = utentes[0];
            Utente criptoUtente = new Utente();
            try {
                criptoUtente.setName(Base64.encodeToString(cipherAES.doFinal(utente.getName().getBytes("UTF-8")),Base64.DEFAULT));
                criptoUtente.setSurn(Base64.encodeToString(cipherAES.doFinal(utente.getSurn().getBytes("UTF-8")),Base64.DEFAULT));
                criptoUtente.setMail(Base64.encodeToString(cipherAES.doFinal(utente.getMail().getBytes("UTF-8")),Base64.DEFAULT));
                criptoUtente.setPass(Base64.encodeToString(cipherAES.doFinal(utente.getPass().getBytes("UTF-8")),Base64.DEFAULT));
                criptoUtente.setAddress(Base64.encodeToString(cipherAES.doFinal(utente.getAddress().getBytes("UTF-8")),Base64.DEFAULT));
                criptoUtente.setC_pubblica(Base64.encodeToString(cipherAES.doFinal(publicKey.toString().getBytes("UTF-8")),Base64.DEFAULT));
                criptoUtente.setC_simmetrica(Base64.encodeToString(cipherRSA.doFinal(symmetricKey.toString().getBytes("UTF-8")),Base64.DEFAULT));

            }  catch (BadPaddingException e) {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            return criptoUtente.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            registerFragment.setRegText(s);
        }
    }
}
