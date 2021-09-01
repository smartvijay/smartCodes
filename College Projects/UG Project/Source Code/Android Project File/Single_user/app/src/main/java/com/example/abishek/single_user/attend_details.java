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

public class attend_details extends AppCompatActivity {

    RequestQueue requestQueue;

    final String url = "http://192.168.225.45/project/single_user_mysql/details.php";
    // final String url = "https://drive.google.com/open?id=1cnpBUIQL9WtXsp7guoTI_Zh9i7oOKzjU";


    TextView  Tdetaila_title_1 , Tdate_1, Tperiod_1, Tsubject_1, Tperiod_2, Tsubject_2, Tperiod_3, Tsubject_3, Tperiod_4, Tsubject_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attend_details_activity);

        setTitle("Attendance Details");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        requestQueue = Volley.newRequestQueue(this);

        Tdetaila_title_1 = findViewById( R.id.semester_head);
        Tdate_1 = findViewById( R.id.fv_yea);
        Tperiod_1 = findViewById( R.id.period1);
        Tsubject_1 = findViewById( R.id.subject1);

        Tperiod_2 = findViewById( R.id.period2);
        Tsubject_2 = findViewById( R.id.subject2);

        Tperiod_3 = findViewById( R.id.period3);
        Tsubject_3 = findViewById( R.id.subject3);

        Tperiod_4 = findViewById( R.id.period4);
        Tsubject_4 = findViewById( R.id.subject4);


        details_data();
    }

    private void details_data() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("details");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject general = jsonArray.getJSONObject(i);


                        String detaila_title_1 = general.getString("detaila_title_1");

                        String date_1 = general.getString("date_1");
                        int period_1 = general.getInt("period_1");
                        String ssubject_1 = general.getString("subject_1");

                        int period_2 = general.getInt("period_2");
                        String subject_2 = general.getString("subject_2");

                        int period_3 = general.getInt("period_3");
                        String subject_3= general.getString("subject_3");

                        int period_4= general.getInt("period_4");
                        String subject_4= general.getString("subject_4");




                        Tdetaila_title_1.setText(detaila_title_1);
                        Tdate_1.setText(date_1);
                        Tperiod_1.setText(String.valueOf(period_1));
                        Tsubject_1.setText(ssubject_1);

                        Tperiod_2.setText(String.valueOf(period_2));
                        Tsubject_2.setText(subject_2);

                        Tperiod_3.setText(String.valueOf(period_3));
                        Tsubject_3.setText(subject_3);

                        Tperiod_4.setText(String.valueOf(period_4));
                        Tsubject_4.setText(subject_4);





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
