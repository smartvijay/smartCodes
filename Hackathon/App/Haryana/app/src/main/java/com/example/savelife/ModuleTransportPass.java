package com.example.savelife;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ModuleTransportPass extends AppCompatActivity implements LocationListener {
    DatePickerDialog picker;
    EditText eText;
    LocationManager locationManager;
    TextView tvCity, tvState, tvCountry, tvPin, tvLocality;
    String ServerURL = "https://savemylifefromcovid.000webhostapp.com/save_life_app/epass_registration.php" ;
    EditText name, mobile, email, role, gender, purpose, date, country, state, city, pin, address;
    Button button,button1;
    String TempName, TempMobile, TempEmail, TempRole, TempGender, TempPurpose, TempDate, TempCountry, TempState, TempCity, TempPin, TempAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass);
        name = (EditText)findViewById(R.id.tvName);
        mobile = (EditText)findViewById(R.id.tvMobile);
        email = (EditText)findViewById(R.id.tvEmail);
        role = (EditText)findViewById(R.id.tvRole);
        gender = (EditText)findViewById(R.id.tvGender);
        purpose = (EditText) findViewById(R.id.tvPurpose);
        date = (EditText)findViewById(R.id.tvDate);
        country = (EditText)findViewById(R.id.tvCountry);
        state = (EditText)findViewById(R.id.tvState);
        city = (EditText)findViewById(R.id.tvCity);
        pin = (EditText)findViewById(R.id.tvPin);
        address = (EditText)findViewById(R.id.tvLocality);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();

                InsertData(TempName, TempMobile, TempEmail, TempRole, TempGender, TempPurpose, TempDate, TempCountry, TempState, TempCity, TempPin, TempAddress);

//                openNewActivity();
            }
        });
        TextView forget= (TextView) findViewById(R.id.pass_url);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityUrl();
            }
        });


        eText=(EditText) findViewById(R.id.tvDate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ModuleTransportPass.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

        tvCity = findViewById(R.id.tvCity);
        tvState = findViewById(R.id.tvState);
        tvCountry = findViewById(R.id.tvCountry);
        tvPin = findViewById(R.id.tvPin);
        tvLocality = findViewById(R.id.tvLocality);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationEnabled();
        getLocation();
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

    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(ModuleTransportPass.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            tvCity.setText(addresses.get(0).getLocality());
            tvState.setText(addresses.get(0).getAdminArea());
            tvCountry.setText(addresses.get(0).getCountryName());
            tvPin.setText(addresses.get(0).getPostalCode());
            tvLocality.setText(addresses.get(0).getAddressLine(0));

        } catch (Exception e) {
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        ModuleTransportPass.this.finish();
    }
    public void GetData(){

        TempName = name.getText().toString();

        TempMobile = mobile.getText().toString();

        TempEmail = email.getText().toString();

        TempRole = role.getText().toString();

        TempGender = gender.getText().toString();

        TempPurpose = purpose.getText().toString();

        TempDate = date.getText().toString();

        TempCountry = country.getText().toString();

        TempState = state.getText().toString();

        TempCity = city.getText().toString();

        TempPin = pin.getText().toString();

        TempAddress = address.getText().toString();

    }

    public void InsertData(final String name, final String mobile,final String email, final String role,final String gender, final String purpose
            , final String date,final String country, final String state, final String city,final String pin, final String address){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(ModuleTransportPass.this);
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

                String NameHolder = name ;
                String MobileHolder = mobile ;
                String EmailHolder = email ;
                String RoleHolder = role ;
                String GenderHolder = gender ;
                String PurposeHolder = purpose ;
                String DateHolder = date ;
                String CountryHolder = country ;
                String StateHolder = state ;
                String CityHolder = city ;
                String PinHolder = pin ;
                String AddressHolder = address ;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("mobile", MobileHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("role", RoleHolder));
                nameValuePairs.add(new BasicNameValuePair("gender", GenderHolder));
                nameValuePairs.add(new BasicNameValuePair("purpose", PurposeHolder));
                nameValuePairs.add(new BasicNameValuePair("date", DateHolder));
                nameValuePairs.add(new BasicNameValuePair("country", CountryHolder));
                nameValuePairs.add(new BasicNameValuePair("state", StateHolder));
                nameValuePairs.add(new BasicNameValuePair("city", CityHolder));
                nameValuePairs.add(new BasicNameValuePair("pin", PinHolder));
                nameValuePairs.add(new BasicNameValuePair("address", AddressHolder));

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
                    Toast.makeText(ModuleTransportPass.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ModuleTransportPass.this,Login.class);
                    startActivity(intent);
                    ModuleTransportPass.this.finish();
                }else {
                    Toast.makeText(ModuleTransportPass.this, "Try Again", Toast.LENGTH_LONG).show();
                }
            }


        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(name, mobile, email, role, gender, purpose, date, country, state, city, pin, address);
    }
    public void openNewActivityUrl(){
        Intent Getintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vellore.nic.in/"));
        startActivity(Getintent);
    }
}