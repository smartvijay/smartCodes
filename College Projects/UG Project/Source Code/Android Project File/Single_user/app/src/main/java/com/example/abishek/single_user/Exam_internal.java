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

public class Exam_internal extends AppCompatActivity {
    RequestQueue requestQueue;

    final String url = "http://192.168.225.45/project/single_user_mysql/internal.php";


    TextView Tinternal_title, Tsub_code_1, Tsub_dis_1, Tsub_mark_1, Tsub_result_1, Tsub_code_2, Tsub_dis_2, Tsub_mark_2, Tsub_result_2,
            Tsub_code_3, Tsub_dis_3, Tsub_mark_3, Tsub_result_3, Tsub_code_4, Tsub_dis_4, Tsub_mark_4, Tsub_result_4,
            Tsub_code_5, Tsub_dis_5, Tsub_mark_5, Tsub_result_5, Tsub_code_6, Tsub_dis_6, Tsub_mark_6, Tsub_result_6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_internal_activity);

        setTitle("Internals");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        requestQueue = Volley.newRequestQueue(this);

        Tinternal_title = findViewById(R.id.internal_heading);
        Tsub_code_1 = findViewById(R.id.subcode);
        Tsub_dis_1 = findViewById(R.id.acc_no);
        Tsub_mark_1 = findViewById(R.id.mark);
        Tsub_result_1 = findViewById(R.id.res_type);
        Tsub_code_2 = findViewById(R.id.subcode1);
        Tsub_dis_2 = findViewById(R.id.subDis1);
        Tsub_mark_2 = findViewById(R.id.mark1);
        Tsub_result_2 = findViewById(R.id.textView4);
        Tsub_code_3 = findViewById(R.id.subcode2);
        Tsub_dis_3 = findViewById(R.id.subDis2);
        Tsub_mark_3 = findViewById(R.id.mark2);
        Tsub_result_3 = findViewById(R.id.textView5);
        Tsub_code_4 = findViewById(R.id.subcode3);
        Tsub_dis_4 = findViewById(R.id.subDis3);
        Tsub_mark_4 = findViewById(R.id.mark3);
        Tsub_result_4 = findViewById(R.id.textView6);
        Tsub_code_5 = findViewById(R.id.subcode4);
        Tsub_dis_5 = findViewById(R.id.subDis4);
        Tsub_mark_5 = findViewById(R.id.mark4);
        Tsub_result_5 = findViewById(R.id.textView7);
        Tsub_code_6 = findViewById(R.id.subcode5);
        Tsub_dis_6 = findViewById(R.id.subDis5);
        Tsub_mark_6 = findViewById(R.id.mark5);
        Tsub_result_6 = findViewById(R.id.textView8);

        internal();
    }

    private void internal() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("internal");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject internal = jsonArray.getJSONObject(i);


                        String abs_title = internal.getString("internal_title");

                        String subCode_1 = internal.getString("sub_code_1");
                        String subDes_1 = internal.getString("sub_dis_1");
                        int subMark_1 = internal.getInt("sub_mark_1");
                        String subResult_1 = internal.getString("sub_result_1");

                        String subCode_2 = internal.getString("sub_code_2");
                        String subDes_2 = internal.getString("sub_dis_2");
                        int subMark_2 = internal.getInt("sub_mark_2");
                        String subResult_2 = internal.getString("sub_result_2");

                        String subCode_3 = internal.getString("sub_code_3");
                        String subDes_3 = internal.getString("sub_dis_3");
                        int subMark_3 = internal.getInt("sub_mark_3");
                        String subResult_3 = internal.getString("sub_result_3");

                        String subCode_4 = internal.getString("sub_code_4");
                        String subDes_4 = internal.getString("sub_dis_4");
                        int subMark_4 = internal.getInt("sub_mark_4");
                        String subResult_4 = internal.getString("sub_result_4");

                        String subCode_5 = internal.getString("sub_code_5");
                        String subDes_5 = internal.getString("sub_dis_5");
                        int subMark_5 = internal.getInt("sub_mark_5");
                        String subResult_5 = internal.getString("sub_result_5");

                        String subCode_6 = internal.getString("sub_code_6");
                        String subDes_6 = internal.getString("sub_dis_6");
                        int subMark_6 = internal.getInt("sub_mark_6");
                        String subResult_6 = internal.getString("sub_result_6");


                        Tinternal_title.setText(abs_title);


                        Tsub_code_1.setText(subCode_1);
                        Tsub_dis_1.setText(subDes_1);
                        Tsub_mark_1.setText(String.valueOf(subMark_1));
                        Tsub_result_1.setText(subResult_1);

                        Tsub_code_2.setText(subCode_2);
                        Tsub_dis_2.setText(subDes_2);
                        Tsub_mark_2.setText(String.valueOf(subMark_2));
                        Tsub_result_2.setText(subResult_2);

                        Tsub_code_3.setText(subCode_3);
                        Tsub_dis_3.setText(subDes_3);
                        Tsub_mark_3.setText(String.valueOf(subMark_3));
                        Tsub_result_3.setText(subResult_3);

                        Tsub_code_4.setText(subCode_4);
                        Tsub_dis_4.setText(subDes_4);
                        Tsub_mark_4.setText(String.valueOf(subMark_4));
                        Tsub_result_4.setText(subResult_4);

                        Tsub_code_5.setText(subCode_5);
                        Tsub_dis_5.setText(subDes_5);
                        Tsub_mark_5.setText(String.valueOf(subMark_5));
                        Tsub_result_5.setText(subResult_5);

                        Tsub_code_6.setText(subCode_6);
                        Tsub_dis_6.setText(subDes_6);
                        Tsub_mark_6.setText(String.valueOf(subMark_6));
                        Tsub_result_6.setText(subResult_6);


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
