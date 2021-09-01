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

public class Fee_pending extends AppCompatActivity {

    RequestQueue requestQueue;

    final String url = "http://192.168.225.45/project/single_user_mysql/pending.php";

    TextView Ttfees_title_1, Ttyear_1, Ttsemester_1, Ttdue_date_1, Ttpending_amount_1,
            Ttfees_title_2, Ttyear_2, Ttsemester_2, Ttdue_date_2, Ttpending_amount_2, Tttotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fee_pending_activity);

        setTitle("Pending");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        requestQueue = Volley.newRequestQueue(this);

        Ttfees_title_1 = findViewById(R.id.head_1);
        Ttyear_1 = findViewById(R.id.fv_year);
        Ttsemester_1 = findViewById(R.id.fv_semester);
        Ttdue_date_1 = findViewById(R.id.fv_dueDate);
        Ttpending_amount_1 = findViewById(R.id.fv_pending_amount);
        Ttfees_title_2 = findViewById(R.id.head_2);
        Ttyear_2 = findViewById(R.id.ov_yea);
        Ttsemester_2 = findViewById(R.id.ov_semeste);
        Ttdue_date_2 = findViewById(R.id.ov_dueDat);
        Ttpending_amount_2 = findViewById(R.id.ov_pending_amoun);
        Tttotal = findViewById(R.id.fv_total);

        pending();

    }

    private void pending() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("pending");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject summary = jsonArray.getJSONObject(i);


                        String Tfees_title_1 = summary.getString("fees_title_1");
                        String Tyear_1 = summary.getString("year_1");
                        String Tsemester_1 = summary.getString("semester_1");

                        String Tdue_date_1 = summary.getString("due_date_1");
                        int Tpending_amount_1 = summary.getInt("pending_amount_1");

                        String Tfees_title_2 = summary.getString("fees_title_2");
                        String Tyear_2 = summary.getString("year_2");
                        String Tsemester_2 = summary.getString("semester_2");

                        String Tdue_date_2 = summary.getString("due_date_2");
                        int Tpending_amount_2 = summary.getInt("pending_amount_2");
                        int Ttotal = summary.getInt("total");


                        Ttfees_title_1.setText(Tfees_title_1);
                        Ttyear_1.setText(Tyear_1);
                        Ttsemester_1.setText(Tsemester_1);

                        Ttdue_date_1.setText(String.valueOf(Tdue_date_1));
                        Ttpending_amount_1.setText(String.valueOf(Tpending_amount_1));

                        Ttfees_title_2.setText(Tfees_title_2);
                        Ttyear_2.setText(Tyear_2);
                        Ttsemester_2.setText(Tsemester_2);

                        Ttdue_date_2.setText(String.valueOf(Tdue_date_2));
                        Ttpending_amount_2.setText(String.valueOf(Tpending_amount_2));
                        Tttotal.setText(String.valueOf(Ttotal));


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
