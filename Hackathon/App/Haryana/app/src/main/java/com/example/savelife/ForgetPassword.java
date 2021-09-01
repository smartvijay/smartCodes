package com.example.savelife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ForgetPassword extends AppCompatActivity {

    String ServerURL = "https://savemylifefromcovid.000webhostapp.com/save_life_app/reset.php" ;
    EditText email;
    Button button,button1;
    String TempEmail, TempPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        email = (EditText)findViewById(R.id.forgot_email);

        button = (Button)findViewById(R.id.forgot_login);
        button1 = (Button)findViewById(R.id.forgot_login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();

                InsertData(TempEmail);

//                openNewActivity();

            }
        });
        TextView Forgot= (TextView) findViewById(R.id.forgot_signup);
        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityLogin();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.basic_topbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.website:
                Intent intent = new Intent(this, WebsiteView.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        ForgetPassword.this.finish();
    }

    public void openNewActivityLogin(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        ForgetPassword.this.finish();
    }

    public void GetData(){

        TempEmail = email.getText().toString();

    }

    public void InsertData(final String email){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(ForgetPassword.this);
            HttpURLConnection conn;
            URL url = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //this method will be running on UI thread
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
            }
            @Override
            protected String doInBackground(String... params) {

                String EmailHolder = email ;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                pdLoading.dismiss();
                super.onPostExecute(result);

                if(true)
                {
                    Toast.makeText(ForgetPassword.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgetPassword.this,Login.class);
                    startActivity(intent);
                    ForgetPassword.this.finish();
                }else {
                    Toast.makeText(ForgetPassword.this, "Try Again", Toast.LENGTH_LONG).show();
                }


            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(email);
    }
}