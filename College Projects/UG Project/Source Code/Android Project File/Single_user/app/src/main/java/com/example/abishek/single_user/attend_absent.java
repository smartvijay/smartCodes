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

public class attend_absent extends AppCompatActivity {

    RequestQueue requestQueue;

    TextView Tsemester_head, Tleave_dt,Tleave_too,Treasons;

    final String url = "http://192.168.225.45/project/single_user_mysql/absent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attend_absent_activity);

        setTitle("Absent Details");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        requestQueue = Volley.newRequestQueue(this);

        Tsemester_head = findViewById(R.id.semester_head);
        Tleave_dt = findViewById(R.id.leave_dt);
        Tleave_too = findViewById(R.id.leave_too);
        Treasons = findViewById(R.id.reasons);

        absent();
    }

    private void absent() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("absent");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject general = jsonArray.getJSONObject(i);


                        String abs_title = general.getString("absent_title_1");

                        String leave_frm = general.getString("leave_from");
                        String leave_t = general.getString("leave_to");
                        String reson = general.getString("reason");




                        Tsemester_head.setText(abs_title);
                        Tleave_dt.setText(leave_frm);
                        Tleave_too.setText(String.valueOf(leave_t));
                        Treasons.setText(reson);




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
