package com.example.savelife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

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
public class SignUp extends AppCompatActivity  implements Validator.ValidationListener{

    @NotEmpty
    @Length(min = 3, max = 10)
    private EditText editTextUsername;

    @NotEmpty
    @Email
    private EditText editTextEmail;

    @NotEmpty
    @Pattern(regex =  "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")
    private EditText editTextPhone;

    @NotEmpty
    @Password
    private EditText editTextPassword;

    @ConfirmPassword
    private EditText editTextConfirmPassword;


    @Checked
    private CheckBox checkBoxAgree;



    private Button buttonSave;

    private Validator validator;


    private EditText etEmail;
    private EditText etPassword;

    String ServerURL = "https://savemylifefromcovid.000webhostapp.com/save_life_app/signup.php" ;
    EditText email, password ;
    Button button,button1;
    String TempEmail, TempPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();
        validator = new Validator(this);
        validator.setValidationListener(this);

        TextView SignUp= (TextView) findViewById(R.id.signup_login);
        SignUp.setOnClickListener(new View.OnClickListener() {
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

    private void initView() {
        editTextUsername = findViewById(R.id.signup_user);
        editTextEmail = findViewById(R.id.signup_mail);
        editTextPhone = findViewById(R.id.signup_mobile);
        editTextPassword = findViewById(R.id.signup_password);
        editTextConfirmPassword = findViewById(R.id.signup_confirm_password);
        checkBoxAgree = findViewById(R.id.checkBoxAgree);
        buttonSave = findViewById(R.id.signup_bt_login);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });
    }
    private void buttonSave_onClick(View view) {
        validator.validate();
        String username = editTextUsername.getText().toString();
        if (username.equalsIgnoreCase("pmk")) {
            editTextUsername.setError(getText(R.string.username_already_exists));
        }
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        SignUp.this.finish();
    }
    public void openNewActivityLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        SignUp.this.finish();
    }
    public void GetData(){

        TempEmail = email.getText().toString();

        TempPassword = password.getText().toString();
    }
    public void InsertData(final String email, final String password){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(SignUp.this);
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
                String PasswordHolder = password ;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("password", PasswordHolder));

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
                    Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this,Login.class);
                    startActivity(intent);
                    SignUp.this.finish();
                }else {
                    Toast.makeText(SignUp.this, "Try Again", Toast.LENGTH_LONG).show();
                }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(email, password);
    }

    @Override
    public void onValidationSucceeded() {

        email = (EditText)findViewById(R.id.signup_mail);
        password = (EditText)findViewById(R.id.signup_password);

        button = (Button)findViewById(R.id.signup_bt_login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();

                InsertData(TempEmail, TempPassword);

//                openNewActivity();

            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}