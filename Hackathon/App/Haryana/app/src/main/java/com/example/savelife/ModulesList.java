package com.example.savelife;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.saveAN.SettingsActivity;
import com.example.savelife.GeoFencing.MapsActivityGeoFence;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

public class ModulesList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button modulesButton;


    //Case Details

    private RequestQueue requestQueue;


    final String url = "https://savemylifefromcovid.000webhostapp.com/save_life_app/corona_case_count.php";

    TextView case_total,case_recovery,case_death;

    CardView cardOne,cardTwo,cardThree,cardFour,cardFive,cardSix,cardSeven,cardEight,cardNine,cardTen,
             cardEleven,cardTwelve,cardThirteen,cardFourteen,cardFifteen,cardSixteen,cardSeventeen,
             cardEighteen,cardNineteen,cardTwenty,cardTwentyOne,cardTwentyTwo,cardTwentyThree,cardTwentyFour;

    // Sample test

    String[] District = { "Select", "Ariyalur", "Chengalpattu", "Chennai", "Coimbatore", "Cuddalore", "Dharmapuri",
            "Dindigul", "Erode", "Kallakurichi", "Kancheepuram", "Kanyakumari", "Karur", "Krishnagiri", "Madurai", "Nagapattinam",
            "Namakkal", "Nilgiris", "Perambalur", "Pudukkottai", "Ramanathapuram", "Ranipet", "Salem", "Sivaganga", "Tenkasi",
            "Thanjavur", "Theni", "Thiruvallur", "Thiruvarur", "Thoothukkudi", "Tiruchirappalli", "Tirunelveli", "Tirupathur", "Tiruppur",
            "Tiruvannamalai", "Vellore", "Viluppuram", "Virudhunagar",};

//    TextView country,State,district;
//    TextView zone;

    TextView district;
    //    String zone;
    ProgressDialog pd;
    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList lineEntries;
    Button button1,button2;
    LocationManager locationManager;
    TextView tvCity, tvState, tvCountry, tvPin, tvLocality;

    Button b1,b2,b3,b4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;

    String spinner_3_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modules_list);


        //Status bar color change

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        //district module zone

        district = findViewById(R.id.district);
        district.setTextColor(Color.GREEN);


        ImageButton b1 = (ImageButton)findViewById(R.id.button);
//        b1 = (ImageButton) findViewById(R.id.button);
//        b2=(Button)findViewById(R.id.button2);
//        b3=(Button)findViewById(R.id.button3);
//        b4=(Button)findViewById(R.id.button4);

        BA = BluetoothAdapter.getDefaultAdapter();
//        lv = (ListView)findViewById(R.id.listView);


        //json Zone

        Spinner spin3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, District);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin3.setAdapter(adapter3);
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                spinner_3_data = item.toString();
                System.out.println("spinner 3 data "+spinner_3_data);
                new JsonTask().execute("https://api.covid19india.org/zones.json");
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // card to Next Activity

        cardOne = findViewById(R.id.module_card_one);
        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, HomePreventive.class);
                startActivity(intent);
            }
        });
        cardTwo = findViewById(R.id.module_card_two);
        cardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, RegisterAppoinment.class);
                startActivity(intent);
            }
        });
        cardThree = findViewById(R.id.module_card_three);
        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, ModuleLabMap.class);
                startActivity(intent);
            }
        });
        cardFour = findViewById(R.id.module_card_four);
        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, RegisterCounselling.class);
                startActivity(intent);
            }
        });
        cardFive = findViewById(R.id.module_card_five);
        cardFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, ModuleStoreMap.class);
                startActivity(intent);
            }
        });
        cardSix = findViewById(R.id.module_card_six);
        cardSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, ModuleSymptoms.class);
                startActivity(intent);
            }
        });
        cardSeven = findViewById(R.id.module_card_seven);
        cardSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, ModuleAdmission.class);
                startActivity(intent);
            }
        });
        cardEight = findViewById(R.id.module_card_eight);
        cardEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, ModuleShelterMap.class);
                startActivity(intent);
            }
        });
        cardNine = findViewById(R.id.module_card_nine);
        cardNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, HomeContact.class);
                startActivity(intent);
            }
        });

        cardTen = findViewById(R.id.module_card_ten);
        cardTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, MapsActivityGeoFence.class);
                startActivity(intent);
            }
        });
        cardEleven = findViewById(R.id.module_card_eleven);
        cardEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, RegisterVolunteer.class);
                startActivity(intent);
            }
        });
        cardTwelve = findViewById(R.id.module_card_twelve);
        cardTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, HomeSearch.class);
                startActivity(intent);
            }
        });
        cardThirteen = findViewById(R.id.module_card_thirteen);
        cardThirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, ModuleSupport.class);
                startActivity(intent);
            }
        });
        cardFourteen = findViewById(R.id.module_card_fourteen);
        cardFourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, ModuleMsmeProduct.class);
                startActivity(intent);
            }
        });
        cardFifteen = findViewById(R.id.module_card_fifteen);
        cardFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, RegisterDonors.class);
                startActivity(intent);
            }
        });
        cardSixteen = findViewById(R.id.module_card_sixteen);
        cardSixteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, RegisterPass.class);
                startActivity(intent);
            }
        });
        cardSeventeen = findViewById(R.id.module_card_seventeen);
        cardSeventeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, AlarmActivity.class);
                startActivity(intent);
            }
        });
        cardEighteen = findViewById(R.id.module_card_eighteen);
        cardEighteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, OnlineVacationalTranning.class);
                startActivity(intent);
            }
        });
        cardNineteen = findViewById(R.id.module_card_nineteen);
        cardNineteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, ModuleTransportPass.class);
                startActivity(intent);
            }
        });
        cardTwenty = findViewById(R.id.module_card_twenty);
        cardTwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, Assessment.class);
                startActivity(intent);
            }
        });
        cardTwentyOne = findViewById(R.id.module_card_twenty_one);
        cardTwentyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, DownloudReport.class);
                startActivity(intent);
            }
        });
        cardTwentyTwo = findViewById(R.id.module_card_twenty_two);
        cardTwentyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, ModuleWorkers.class);
                startActivity(intent);
            }
        });
        cardTwentyThree = findViewById(R.id.module_card_twenty_three);
        cardTwentyThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, HomeMap.class);
                startActivity(intent);
            }
        });
        cardTwentyFour = findViewById(R.id.module_card_twenty_four);
        cardTwentyFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesList.this, HomeMedia.class);
                startActivity(intent);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        //corona case status
        case_total = findViewById(R.id.total);
        case_recovery = findViewById(R.id.recovery);
        case_death = findViewById(R.id.death);
        pending();

    }


    private void pending() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("cases");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject summary = jsonArray.getJSONObject(i);

                        String get_total = summary.getString("total");
                        String get_recovery = summary.getString("recovery");
                        String get_death = summary.getString("death");

                        case_total.setText(get_total);
                        case_recovery.setText(get_recovery);
                        case_death.setText(get_death);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ModulesList.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contextual_action_bar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Intent intent = new Intent(ModulesList.this, CoronaSupport.class);
                startActivity(intent);
//                Toast.makeText(this, "Select Module", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.more:
                Intent intent1 = new Intent(ModulesList.this, TermsAndConditions.class);
                startActivity(intent1);
                return true;
            case R.id.ratio:
                Intent intent2 = new Intent(ModulesList.this, CoronaCaseDetails.class);
                startActivity(intent2);
                return true;
            case R.id.settings:
                Intent intent3 = new Intent(ModulesList.this, SettingsActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void on(View v){
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
        } else {
            BA.disable();
            Toast.makeText(getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
        }
    }

    private class JsonTask extends AsyncTask<String, String, String> {


        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(ModulesList.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
//                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            ArrayList<ZoneObject> zonelist = new ArrayList<>();

            super.onPostExecute(response);
            if (pd.isShowing()){
                pd.dismiss();
            }

            try {

                JSONObject jsonResponse = new JSONObject(response);

                JSONArray jsonArray = jsonResponse.getJSONArray("zones");
                for (int i = 0; i < jsonArray.length(); i++) {
                    ZoneObject zoneClass = new ZoneObject();
                    JSONObject zoneData = jsonArray.getJSONObject(i);

                    zoneClass.setState(zoneData.getString("state"));
                    zoneClass.setDistrict(zoneData.getString("district"));
                    zoneClass.setZone(zoneData.getString("zone"));

                    zonelist.add(zoneClass);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (zonelist.size() > 0){
                for (int i = 0; i < zonelist.size() ; i++) {
//                    System.out.println("getState "+zonelist.get(i).getState());
                    if (zonelist.get(i).getState().contains("Tamil Nadu")){
//                        if (spinner_3_data.length() > 0){
                        if (zonelist.get(i).getDistrict().contains(spinner_3_data)){
                            district.setText(zonelist.get(i).getZone());
                            String zone = district.getText().toString();
                            System.out.println("Zone  - " + zone);
                            if(zone.contains("Green")){
                                district.setTextColor(Color.parseColor("#008000"));
//                                district.setBackgroundColor(Color.GREEN);
                            }
                            if(zone.contains("Orange")){
                                district.setTextColor(Color.parseColor("#FF8C00"));
                            }
                            if(zone.contains("Red")){
                                district.setTextColor(Color.parseColor("#FF0000"));
                            }
//                            System.out.println("District  - " + zonelist.get(i).getDistrict());
//                            System.out.println("zone      - " + zonelist.get(i).getZone());
//                            }
                        }else {
//                            district.setTextColor(Color.MAGENTA);
                        }

                    }else{
//                        System.out.println("Different state ");
                    }
                }
//                System.out.println("List size "+zonelist.size());
            }
        }
    }

}
