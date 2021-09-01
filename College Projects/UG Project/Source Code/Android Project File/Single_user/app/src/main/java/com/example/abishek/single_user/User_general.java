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

public class User_general extends AppCompatActivity {

    RequestQueue requestQueue;

    final String url = "http://192.168.225.45/project/single_user_mysql/general.php";
    // final String url = "https://drive.google.com/open?id=1cnpBUIQL9WtXsp7guoTI_Zh9i7oOKzjU";


    TextView Tname, Troll, Tadmission_no, TBatch, Tcourse, Tsemester, Tdepartment,Tage, Tdob, Tblood_group, Theight, Tweight, Thealth_status, Tgender,
            Tmother_tongue, Tnationality, Treligion, Tcommunity, Tcaste, Tcategory, Tmedium_of_study, Taddress, Tcity, Tstate, Tcountry, Tpincode, Tmobile_no,
            Tfather_name,Tf_occupation,Tf_monthly_income,Tf_contact_no,Tmother_name,Tm_occupation,Tm_monthly_income,Tm_contact_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_general_activity);

        setTitle("General");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        requestQueue = Volley.newRequestQueue(this);

        Tname = findViewById(R.id.Pv_name);
        Troll = findViewById(R.id.Pv_roll_no);
        Tadmission_no = findViewById(R.id.Pv_admission);
        TBatch = findViewById(R.id.pv_batch);
        Tcourse = findViewById(R.id.pv_course);
        Tsemester = findViewById(R.id.pv_semester);
        Tdepartment = findViewById(R.id.pv_depertment);
        Tage= findViewById(R.id.pv_age);
        Tdob=findViewById(R.id.pv_DOB);
        Tblood_group=findViewById(R.id.pv_bloodGroup);
        Theight=findViewById(R.id.pv_height);
        Tweight=findViewById(R.id.pv_weight);
        Thealth_status=findViewById(R.id.pv_health);
        Tgender=findViewById(R.id.pv_gender);
        Tmother_tongue=findViewById(R.id.pv_motherTongue);
        Tnationality=findViewById(R.id.pv_nationality);
        Treligion= findViewById(R.id.pv_religion);
        Tcommunity=findViewById(R.id.pv_community);
        Tcaste=findViewById(R.id.pv_cast);
        Tcategory=findViewById(R.id.pv_category);
        Tmedium_of_study= findViewById(R.id.Pv_medium);
        Taddress=findViewById(R.id.pv_addressData);
        Tcity= findViewById(R.id.Pv_city);
        Tstate= findViewById(R.id.pv_state);
        Tcountry= findViewById(R.id.pv_country);
        Tpincode=findViewById(R.id.pv_pincode);
        Tmobile_no=findViewById(R.id.pv_mobile);
        Tfather_name=findViewById(R.id.pv_father);
        Tf_occupation=findViewById(R.id.pv_f_Occupation);
        Tf_monthly_income=findViewById(R.id.pv_f_Income);
        Tf_contact_no=findViewById(R.id.pv_f_contact);
        Tmother_name=findViewById(R.id.pv_motherName);
        Tm_occupation=findViewById(R.id.pv_m_Occupation);
        Tm_monthly_income=findViewById(R.id.pv_m_Income);
        Tm_contact_no=findViewById(R.id.pv_m_contact);

        jsonParsing();
    }

    private void jsonParsing() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("general");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject general = jsonArray.getJSONObject(i);


                        String name = general.getString("name ");
                        String roll_no = general.getString("roll_no");
                        String admission_date = general.getString("admission_date");
                        String batch = general.getString("batch");
                        String course = general.getString("course");
                        String semester = general.getString("semester");
                        String department = general.getString("department");
                        String age= general.getString("age");
                        String dob= general.getString("dob");
                        String blood_group= general.getString("blood_group");
                        String height= general.getString("height");
                        String weight= general.getString("weight");
                        String health_status= general.getString("health_status");
                        String gender= general.getString("gender");
                        String mother_tongue= general.getString("mother_tongue");
                        String nationality= general.getString("nationality");
                        String religion= general.getString("religion");
                        String community= general.getString("community");
                        String caste= general.getString("caste");
                        String category= general.getString("category");
                        String medium_of_study= general.getString("medium_of_study");
                        String address= general.getString("address");
                        String city= general.getString("city");
                        String state= general.getString("state");
                        String country= general.getString("country");
                        String pincode= general.getString("pincode");
                        String mobile_no= general.getString("mobile_no");
                        String father_name= general.getString("father_name");
                        String f_occupation= general.getString("f_occupation");
                        String f_monthly_income= general.getString("f_monthly_income");
                        String f_contact_no= general.getString("f_contact_no");
                        String mother_name= general.getString("mother_name");
                        String m_occupation= general.getString("m_occupation");
                        String m_monthly_income= general.getString("m_monthly_income");
                        String m_contact_no= general.getString("m_contact_no");



                        Tname.setText(name);
                        Troll.setText(roll_no);
                        Tadmission_no.setText(admission_date);
                        TBatch.setText(batch);
                        Tcourse.setText(course);
                        Tsemester.setText(semester);
                        Tdepartment.setText(department);
                        Tage.setText(age);
                        Tdob.setText(dob);
                        Tblood_group.setText(blood_group);
                        Theight.setText(height);
                        Tweight.setText(weight);
                        Thealth_status.setText(health_status);
                        Tgender.setText(gender);
                        Tmother_tongue.setText(mother_tongue);
                        Tnationality.setText(nationality);
                        Treligion.setText(religion);
                        Tcommunity.setText(community);
                        Tcaste.setText(caste);
                        Tcategory.setText(category);
                        Tmedium_of_study.setText(medium_of_study);
                        Taddress.setText(address);
                        Tcity.setText(city);
                        Tstate.setText(state);
                        Tcountry.setText(country);
                        Tpincode.setText(pincode);
                        Tmobile_no.setText(mobile_no);
                        Tfather_name.setText(father_name);
                        Tf_occupation.setText(f_occupation);
                        Tf_monthly_income.setText(f_monthly_income);
                        Tf_contact_no.setText(f_contact_no);
                        Tmother_name.setText(mother_name);
                        Tm_occupation.setText(m_occupation);
                        Tm_monthly_income.setText(m_monthly_income);
                        Tm_contact_no.setText(m_contact_no);



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
