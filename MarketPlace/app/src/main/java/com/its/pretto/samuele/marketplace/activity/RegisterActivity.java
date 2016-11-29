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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmail, edtPass;
    Button btnRegister;
    String txtEmail, txtPass;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = (EditText) findViewById(R.id.editTextRegEmail);

        edtPass = (EditText) findViewById(R.id.editTextRegPassword);

        btnRegister = (Button) findViewById(R.id.buttonReg);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtEmail.length()>0&&edtPass.length()>0){
                    txtEmail = edtEmail.getText().toString();
                    txtPass = edtPass.getText().toString();
                    RegAsyncTask regAsyncTask = new RegAsyncTask();
                    regAsyncTask.execute(new String[]{txtEmail,txtPass});
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Devi compilare tutti i campi", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    class RegAsyncTask extends AsyncTask<String[], Void,Boolean>{
        String email,password,result,error;
        int id;
        Boolean test;

        @Override
        protected Boolean doInBackground(String[]... strings) {

            String[] strArray = strings[0];
            email = strArray[0];
            password = strArray[1];

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("mail",email)
                    .add("password",password)
                    .build();
            Request request = new Request.Builder()
                    .url("http://tommo.altervista.org/ITS/annunci/register.php")
                    .post(formBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                result = response.body().string();
                    JSONObject jsonObject = new JSONObject(result.toString());
                   test= jsonObject.getBoolean("success");
                if (test){
                    id = jsonObject.getInt("id");
                }
                else{
                    error = jsonObject.getString("error");
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
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
            else{
                Toast.makeText(RegisterActivity.this, "Error:" + error, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }


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
