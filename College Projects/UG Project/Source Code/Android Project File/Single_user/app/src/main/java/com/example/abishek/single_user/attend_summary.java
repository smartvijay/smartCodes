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

public class attend_summary extends AppCompatActivity {

    RequestQueue requestQueue;

    final String url = "http://192.168.225.45/project/single_user_mysql/summary.php";

    TextView Tsummary_title_1, Tmonth_1, Tdays_1, Tabsent_1, Tpresent_1, Tmonth_2, Tdays_2, Tabsent_2, Tpresent_2,
            Tmonth_3, Tdays_3, Tabsent_3, Tpresent_3, Ttotal, Tdays_4, Tabsent_4, Tpresent_4, Tpresent, Tdays_5, Tabsent_5, Tpresent_5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attend_summary_activity);

        setTitle("Attendance Summary");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        requestQueue = Volley.newRequestQueue(this);

        Tsummary_title_1= findViewById(R.id.semester_head);

        Tmonth_1= findViewById(R.id.f_tdat);
        Tdays_1= findViewById(R.id.leave_dat);
        Tabsent_1= findViewById(R.id.to_date);
        Tpresent_1= findViewById(R.id.total_date);

        Tmonth_2= findViewById(R.id.f_tda);
        Tdays_2= findViewById(R.id.leave_da);
        Tabsent_2= findViewById(R.id.to_dat);
        Tpresent_2= findViewById(R.id.total_dat);

        Tmonth_3= findViewById(R.id.f_tda3);
        Tdays_3= findViewById(R.id.leave_da3);
        Tabsent_3= findViewById(R.id.to_dat3);
        Tpresent_3= findViewById(R.id.total_dat33);

        Ttotal= findViewById(R.id.f_tda34);
        Tdays_4= findViewById(R.id.leave_da34);
        Tabsent_4= findViewById(R.id.to_dat34);
        Tpresent_4= findViewById(R.id.total_dat34);

        Tpresent= findViewById(R.id.f_tda35);
        Tdays_5= findViewById(R.id.leave_da35);
        Tabsent_5= findViewById(R.id.to_dat35);
        Tpresent_5= findViewById(R.id.total_dat35);


        summary();
    }

    private void summary() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("summary");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject summary = jsonArray.getJSONObject(i);


                        String abs_title = summary.getString("summary_title_1");

                        String mon_1 = summary.getString("month_1");
                        int day1 = summary.getInt("days_1");
                        int abs_1 = summary.getInt("absent_1");
                        int pres_1 = summary.getInt("present_1");

                        String mon_2 = summary.getString("month_2");
                        int day2 = summary.getInt("days_2");
                        int abs_2 = summary.getInt("absent_2");
                        int pres_2 = summary.getInt("present_2");

                        String mon_3 = summary.getString("month_3");
                        int day3 = summary.getInt("days_3");
                        int abs_3 = summary.getInt("absent_3");
                        int pres_3 = summary.getInt("present_3");

                        String total = summary.getString("total");
                        int day4 = summary.getInt("days_4");
                        int abs_4 = summary.getInt("absent_4");
                        int pres_4 = summary.getInt("present_4");

                        String present = summary.getString("present");
                        int day5 = summary.getInt("days_5");
                        int abs_5 = summary.getInt("absent_5");
                        int pres_5 = summary.getInt("present_5");




                        Tsummary_title_1.setText(abs_title);

                        Tmonth_1.setText(mon_1);
                        Tdays_1.setText(String.valueOf(day1));
                        Tabsent_1.setText(String.valueOf(abs_1));
                        Tpresent_1.setText(String.valueOf(pres_1));

                        Tmonth_2.setText(mon_2);
                        Tdays_2.setText(String.valueOf(day2));
                        Tabsent_2.setText(String.valueOf(abs_2));
                        Tpresent_2.setText(String.valueOf(pres_2));

                        Tmonth_3.setText(mon_3);
                        Tdays_3.setText(String.valueOf(day3));
                        Tabsent_3.setText(String.valueOf(abs_3));
                        Tpresent_3.setText(String.valueOf(pres_3));

                        Ttotal.setText(total);
                        Tdays_4.setText(String.valueOf(day4));
                        Tabsent_4.setText(String.valueOf(abs_4));
                        Tpresent_4.setText(String.valueOf(pres_4));


                        Tpresent.setText(present);
                        Tdays_5.setText(String.valueOf(day5));
                        Tabsent_5.setText(String.valueOf(abs_5));
                        Tpresent_5.setText(String.valueOf(pres_5));




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
