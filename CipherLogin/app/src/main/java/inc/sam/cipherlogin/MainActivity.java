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
import android.widget.Toast;

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
    Cipher decipherAES, decipherRSA;
    PrivateKey privateKey;
    RegisterFragment registerFragment;
    LoginFragment loginFragment;
    CipherClass cipherClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState==null){
            try {
                cipherClass = CipherClass.getInstance();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            registerFragment = new RegisterFragment();
            getFragmentManager().beginTransaction().add(R.id.container,registerFragment).commit();

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
                String result = response.body().string();
                JSONObject jsonObject = new JSONObject(result);
                String cryptC_simmetrica = jsonObject.getJSONObject("user").getString("c_simmetrica");
                String cryptNome = jsonObject.getJSONObject("user").getString("nome");
                String cryptCognome = jsonObject.getJSONObject("user").getString("cognome");
                String cryptIndirizzo = jsonObject.getJSONObject("user").getString("indirizzo");

                SecretKey databaseSymKey = new SecretKeySpec(Base64.decode(cipherClass.decrypt(cryptC_simmetrica,"RSA","private"),Base64.NO_WRAP),"AES");

                //Controllo se la chiave simmetrica del database è uguale a quella della classe
                if (databaseSymKey.equals(cipherClass.getSymmetricKey())){
                    Log.d("aaa","Sono uguali");
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
            loginFragment.setLogText(s);
        }
    }


    public class FetchRegTask extends AsyncTask<Utente,Void,String>{

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
