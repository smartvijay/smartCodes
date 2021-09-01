package com.example.abishek.single_user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class attend_od extends AppCompatActivity {

    RequestQueue requestQueue;

    final String url = "http://192.168.225.45/project/single_user_mysql/onduty.php";

    TextView Tonduty_title_1, Tfrom_date, Tto_date, Ttotal_days, Tperiod, Tsubject, Treason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attend_od_activity);

        setTitle("OnDuty Details");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        requestQueue = Volley.newRequestQueue(this);

        Tonduty_title_1 = findViewById(R.id.semester_head);
        Tfrom_date = findViewById(R.id.leave_dt);
        Tto_date = findViewById(R.id.to_date);
        Ttotal_days = findViewById(R.id.total_date);
        Tperiod = findViewById(R.id.periods);
        Tsubject = findViewById(R.id.subjects);
        Treason = findViewById(R.id.reasons);


        onduty();
    }

    private void onduty() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("onduty");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject general = jsonArray.getJSONObject(i);


                        String abs_title = general.getString("onduty_title_1");

                        String from = general.getString("from_date");
                        String to = general.getString("to_date");
                        String total = general.getString("total_days");
                        String periods = general.getString("period");
                        String sub = general.getString("subject");
                        String res = general.getString("reason");




                        Tonduty_title_1.setText(abs_title);
                        Tfrom_date.setText(from);
                        Tto_date.setText(to);
                        Ttotal_days.setText(String.valueOf(total));
                        Tperiod.setText(periods);
                        Tsubject.setText(sub);
                        Treason.setText(res);




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
}
