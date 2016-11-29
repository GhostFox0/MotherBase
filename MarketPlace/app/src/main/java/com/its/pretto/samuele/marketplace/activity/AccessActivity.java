package com.its.pretto.samuele.marketplace.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.its.pretto.samuele.marketplace.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AccessActivity extends AppCompatActivity {

    EditText edtEmail, edtPass;
    Button btnAccedi;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        edtEmail = (EditText) findViewById(R.id.editTextLogEmail);
        edtPass = (EditText) findViewById(R.id.editLogPassword);

        btnAccedi = (Button) findViewById(R.id.buttonAccedi);

        btnAccedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtEmail.length()>0&&edtPass.length()>0){
                    LogAsychTask logAsychTask = new LogAsychTask();
                    logAsychTask.execute(new String[]{edtEmail.getText().toString(),edtPass.getText().toString()});
                }
                else {
                    Toast.makeText(AccessActivity.this, "Errore", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    class LogAsychTask extends AsyncTask<String[], Void, Boolean>{
        String mMail, mPass, result,error;
        Boolean test=false;
        int id;

        @Override
        protected Boolean doInBackground(String[]... strings) {
            String[] credentials = strings[0];
            mMail = credentials[0];
            mPass = credentials[1];

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("mail",mMail)
                    .add("password",mPass)
                    .build();
            Request request = new Request.Builder()
                    .url("http://tommo.altervista.org/ITS/annunci/login.php")
                    .post(formBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                result = response.body().string();
                JSONObject jsonObject = new JSONObject(result.toString());
                test= jsonObject.getBoolean("success");
                if (test){
                    id = Integer.parseInt(jsonObject.getString("id"));
                }
                else{
                    error = jsonObject.getString("success");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return test;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                Intent intent = new Intent(AccessActivity.this,MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
            else{
                Toast.makeText(AccessActivity.this, "Error:" + error, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AccessActivity.this);
            dialog.setMessage("Please wait");
            dialog.show();
        }
    }
}
