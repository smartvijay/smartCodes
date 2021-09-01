package com.example.savelife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saveAN.SettingsActivity;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
public class Login extends AppCompatActivity implements Validator.ValidationListener{

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private EditText etEmail;
    private EditText etPassword;

    EditText et_user_email;
    EditText et_user_pass;

    String str_email;
    String str_pass;

    @NotEmpty
    @Email
    private EditText editTextEmail;

    private Button buttonSave,modulesButton;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageButton imgPreventionButton,imgContactButton,imgMapButton,imgMediaButton,imgTrackerButton;
//        Button volunteer,donor,pass;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initView();
        validator = new Validator(this);
        validator.setValidationListener(this);

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_pass);

        TextView volunteer= (TextView) findViewById(R.id.volunteer);
        TextView donor= (TextView) findViewById(R.id.donor);
        TextView pass= (TextView) findViewById(R.id.pass);

        //login top icon to next page

        TextView login_prevent= (TextView) findViewById(R.id.prevent);
        TextView login_contact= (TextView) findViewById(R.id.contact);
        TextView login_map= (TextView) findViewById(R.id.map);
        TextView login_media= (TextView) findViewById(R.id.media);
        TextView login_track= (TextView) findViewById(R.id.track);

        imgPreventionButton =(ImageButton)findViewById(R.id.prevention_image);
        imgPreventionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_one();
            }
        });
        imgContactButton =(ImageButton)findViewById(R.id.contact_image);
        imgContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_two();
            }
        });
        imgMapButton =(ImageButton)findViewById(R.id.map_image);
        imgMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_three();
            }
        });
        imgMediaButton =(ImageButton)findViewById(R.id.media_image);
        imgMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_four();
            }
        });
        imgTrackerButton =(ImageButton)findViewById(R.id.tracker_image);
        imgTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_five();
            }
        });

        // Modules Button
//        modulesButton = (Button) findViewById(R.id.modules);
//        modulesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openNewActivity();
//            }
//        });

        //Text to nextPage
        login_prevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_one();
            }
        });
        login_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_two();
            }
        });
        login_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_three();
            }
        });
        login_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_four();
            }
        });
        login_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity_five();
            }
        });

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityVolunteer();
            }
        });
        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityDonor();
            }
        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityPass();
            }
        });

        TextView appoinment= (TextView) findViewById(R.id.appoinment);
        TextView course= (TextView) findViewById(R.id.course);
        TextView counselling= (TextView) findViewById(R.id.counselling);
        appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityAppoinment();
            }
        });
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityCourse();
            }
        });
        counselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityCounselling();
            }
        });
        TextView tv= (TextView) findViewById(R.id.signup);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupActivity();
            }
        });
        TextView forget= (TextView) findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetActivity();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.website_view,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.translate:
                Intent intent = new Intent(this, WebsiteView.class);
                startActivity(intent);
                return true;
            case R.id.more:
                Intent intent1 = new Intent(this, TermsAndConditions.class);
                startActivity(intent1);
                return true;
            case R.id.settings:
                Intent intent2 = new Intent(this, SettingsActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, ModulesList.class);
        startActivity(intent);
    }

    private void initView() {
        editTextEmail = findViewById(R.id.et_email);
        buttonSave = findViewById(R.id.bt_login);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });
    }

    private void buttonSave_onClick(View view) {
        validator.validate();
        String username = editTextEmail.getText().toString();
        if (username.equalsIgnoreCase("pmk")) {
            editTextEmail.setError(getText(R.string.username_already_exists));
        }
    }

    public void openNewActivity_one(){
        Intent intent = new Intent(this, HomePreventive.class);
        startActivity(intent);
    }
    public void openNewActivity_two(){
        Intent intent = new Intent(this, HomeContact.class);
        startActivity(intent);
    }
    public void openNewActivity_three(){
        Intent intent = new Intent(this, HomeMap.class);
        startActivity(intent);
    }
    public void openNewActivity_four(){
        Intent intent = new Intent(this, HomeMedia.class);
        startActivity(intent);
    }
    public void openNewActivity_five(){
        Intent intent = new Intent(this, HomeSearch.class);
        startActivity(intent);
    }
    public void signupActivity(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void forgetActivity()
    {
        Intent intent = new Intent(this, ForgetPassword.class);
        startActivity(intent);
    }
    public void openNewActivityVolunteer(){
        Intent intent = new Intent(this, RegisterVolunteer.class);
        startActivity(intent);
    }
    public void openNewActivityDonor(){
        Intent intent = new Intent(this, RegisterDonors.class);
        startActivity(intent);
    }
    public void openNewActivityPass(){
        Intent intent = new Intent(this, RegisterPass.class);
        startActivity(intent);
    }
    public void openNewActivityAppoinment(){
        Intent intent = new Intent(this, RegisterAppoinment.class);
        startActivity(intent);
    }
    public void openNewActivityCourse(){
        Intent intent = new Intent(this, RegisterCourses.class);
        startActivity(intent);
    }
    public void openNewActivityCounselling(){
        Intent intent = new Intent(this, RegisterCounselling.class);
        startActivity(intent);
    }


    public void checkLogin(View arg0) {

        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        new Login.AsyncLogin().execute(email,password);

    }

    @Override
    public void onValidationSucceeded() {

        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        new Login.AsyncLogin().execute(email,password);
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

    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(Login.this);
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
            try {

                // Enter URL address where your php file resides
                url = new URL("https://savemylifefromcovid.000webhostapp.com/save_life_app/login.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }

            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }
            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true"))
            {
                Intent intent = new Intent(Login.this,ModulesList.class);
                startActivity(intent);
                Login.this.finish();

            }else if (result.equalsIgnoreCase("false")){

                Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(Login.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            }
        }

    }


}
