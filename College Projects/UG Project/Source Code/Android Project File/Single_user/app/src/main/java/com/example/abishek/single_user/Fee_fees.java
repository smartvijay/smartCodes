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

public class Fee_fees extends AppCompatActivity {

    RequestQueue requestQueue;

    final String url = "http://192.168.225.45/project/single_user_mysql/fees.php";

    TextView Tfees_title_1, Tyear_1, Tsemester, Tdue_date_1, Tfees_amount_1, Tconcession_amount_1, Tnet_amount_1,
            Tfees_title_2, Tyear_2, Tsemester_2, Tdue_date_2, Tfees_amount_2, Tconcession_amount_2, Tnet_amount_2,
            Tfees_title_3, Tyear_3, Tsemester_3, Tdue_date_3, Tfees_amount_3, Tconcession_amount_3, Tnet_amount_3, Ttotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fee_fees_activity);

        setTitle("Fees");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        requestQueue = Volley.newRequestQueue(this);

        Tfees_title_1= findViewById(R.id.head_1);
        Tyear_1= findViewById(R.id.fv_recp_no);
        Tsemester= findViewById(R.id.fv_date);
        Tdue_date_1= findViewById(R.id.fv_year);
        Tfees_amount_1= findViewById(R.id.fv_feesamount);
        Tconcession_amount_1= findViewById(R.id.fv_cons_amount);
        Tnet_amount_1= findViewById(R.id.fv_pending_amount);

        Tfees_title_2= findViewById(R.id.head_2);
        Tyear_2= findViewById(R.id.fv_yea);
        Tsemester_2= findViewById(R.id.fv_semeste);
        Tdue_date_2= findViewById(R.id.fv_dueDat);
        Tfees_amount_2= findViewById(R.id.fv_feesamoun);
        Tconcession_amount_2= findViewById(R.id.fv_cons_amoun);
        Tnet_amount_2= findViewById(R.id.fv_net_amoun);

        Tfees_title_3= findViewById(R.id.head_3);
        Tyear_3= findViewById(R.id.ov_yea);
        Tsemester_3= findViewById(R.id.ov_semeste);
        Tdue_date_3= findViewById(R.id.ov_dueDat);
        Tfees_amount_3= findViewById(R.id.ov_feesamoun);
        Tconcession_amount_3= findViewById(R.id.ov_cons_amoun);
        Tnet_amount_3= findViewById(R.id.ov_pending_amoun);
        Ttotal= findViewById(R.id.tv_year);


        fees();

    }

    private void fees() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("fees");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject summary = jsonArray.getJSONObject(i);


                        String f_title_1 = summary.getString("fees_title_1");

                        String f_year_1 = summary.getString("year_1");
                        String f_semester_1 = summary.getString("semester");
                        String f_dueDate_1 = summary.getString("due_date_1");
                        int f_fees_amount_1 = summary.getInt("fees_amount_1");
                        int f_concession_amount_1 = summary.getInt("concession_amount_1");
                        int f_net_amount_1 = summary.getInt("net_amount_1");

                        String f_title_2 = summary.getString("fees_title_2");

                        String f_year_2 = summary.getString("year_2");
                        String f_semester_2 = summary.getString("semester_2");
                        String f_dueDate_2 = summary.getString("due_date_2");
                        int f_fees_amount_2 = summary.getInt("fees_amount_2");
                        int f_concession_amount_2 = summary.getInt("concession_amount_2");
                        int f_net_amount_2 = summary.getInt("net_amount_2");

                        String f_title_3 = summary.getString("fees_title_3");

                        String f_year_3 = summary.getString("year_3");
                        String f_semester_3 = summary.getString("semester_3");
                        String f_dueDate_3 = summary.getString("due_date_3");
                        int f_fees_amount_3 = summary.getInt("fees_amount_3");
                        int f_concession_amount_3 = summary.getInt("concession_amount_3");
                        int f_net_amount_3 = summary.getInt("net_amount_3");

                        int f_total_amount_3 = summary.getInt("total");





                        Tfees_title_1.setText(f_title_1);

                        Tyear_1.setText(String.valueOf(f_year_1));
                        Tsemester.setText(String.valueOf(f_semester_1));
                        Tdue_date_1.setText(String.valueOf(f_dueDate_1));
                        Tfees_amount_1.setText(String.valueOf(f_fees_amount_1));
                        Tconcession_amount_1.setText(String.valueOf(f_concession_amount_1));
                        Tnet_amount_1.setText(String.valueOf(f_net_amount_1));

                        Tfees_title_2.setText(f_title_2);
                        Tyear_2.setText(String.valueOf(f_year_2));
                        Tsemester.setText(String.valueOf(f_semester_2));
                        Tdue_date_2.setText(String.valueOf(f_dueDate_2));
                        Tfees_amount_2.setText(String.valueOf(f_fees_amount_2));
                        Tconcession_amount_2.setText(String.valueOf(f_concession_amount_2));
                        Tnet_amount_2.setText(String.valueOf(f_net_amount_2));

                        Tfees_title_3.setText(f_title_3);
                        Tyear_3.setText(String.valueOf(f_year_3));
                        Tsemester.setText(String.valueOf(f_semester_3));
                        Tdue_date_3.setText(String.valueOf(f_dueDate_3));
                        Tfees_amount_3.setText(String.valueOf(f_fees_amount_3));
                        Tconcession_amount_3.setText(String.valueOf(f_concession_amount_3));
                        Tnet_amount_3.setText(String.valueOf(f_net_amount_3));

                        Ttotal.setText(String.valueOf(f_total_amount_3));



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
