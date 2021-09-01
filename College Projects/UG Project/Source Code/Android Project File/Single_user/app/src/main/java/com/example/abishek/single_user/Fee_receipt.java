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

public class Fee_receipt extends AppCompatActivity {

    RequestQueue requestQueue;

    final String url = "http://192.168.225.45/project/single_user_mysql/receipt.php";

    TextView Tfees_title_1, Treceipt_no, Tdate_1, Tyear_1, Tamount_1, Tnet_amount_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fee_receipt_activity);

        setTitle("Receipt");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        requestQueue = Volley.newRequestQueue(this);

        Tfees_title_1= findViewById(R.id.head_1);
        Treceipt_no= findViewById(R.id.fv_recp_no);
        Tdate_1= findViewById(R.id.fv_date);
        Tyear_1= findViewById(R.id.fv_year);
        Tamount_1= findViewById(R.id.fv_feesamount);
        Tnet_amount_1= findViewById(R.id.fv_pending_amount);

        receipt();
    }

    private void receipt() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("receipt");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject summary = jsonArray.getJSONObject(i);


                        String f_title_1 = summary.getString("fees_title_1");

                        String f_receipt_no = summary.getString("receipt_no");
                        String f_Date_1 = summary.getString("date_1");
                        String f_year_1 = summary.getString("year_1");
                        int f_amount_1 = summary.getInt("amount_1");
                        int f_net_amount_1 = summary.getInt("net_amount_1");



                        Tfees_title_1.setText(f_title_1);

                        Treceipt_no.setText(f_receipt_no);
                        Tdate_1.setText(f_Date_1);
                        Tyear_1.setText(f_year_1);
                        Tamount_1.setText(String.valueOf(f_amount_1));
                        Tnet_amount_1.setText(String.valueOf(f_net_amount_1));



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
