package com.example.abishek.single_user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    //declaring cards
    private CardView cardSummary, cardDetails, cardAbsent, cardOD, cardTest, cardInternal, cardFees, cardReceipt, cardPending, cardLibrary, cardGeneral, cardLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Home");

        //defining cards

        cardSummary = findViewById(R.id.card_summary);
        cardDetails = findViewById(R.id.card_details);
        cardAbsent = findViewById(R.id.card_absent);
        cardOD = findViewById(R.id.card_onduty);
        cardTest = findViewById(R.id.card_test);
        cardInternal = findViewById(R.id.card_internal);
        cardFees = findViewById(R.id.card_fees);
        cardReceipt = findViewById(R.id.card_receipt);
        cardPending = findViewById(R.id.card_pending);
        cardLibrary = findViewById(R.id.card_library);
        cardGeneral = findViewById(R.id.card_general);
        cardLogout = findViewById(R.id.card_logout);

//Card onclick listener

        cardSummary.setOnClickListener(this);
        cardDetails.setOnClickListener(this);
        cardAbsent.setOnClickListener(this);
        cardOD.setOnClickListener(this);
        cardTest.setOnClickListener(this);
        cardInternal.setOnClickListener(this);
        cardFees.setOnClickListener(this);
        cardReceipt.setOnClickListener(this);
        cardPending.setOnClickListener(this);
        cardLibrary.setOnClickListener(this);
        cardGeneral.setOnClickListener(this);
        cardLogout.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_summary) {
            Intent intent = new Intent(this,attend_summary.class);
            startActivity(intent);

        }else if (id == R.id.nav_details) {
            Intent intent = new Intent(this,attend_details.class);
            startActivity(intent);

        } else if (id == R.id.nav_absent) {
            Intent intent = new Intent(this,attend_absent.class);
            startActivity(intent);

        } else if (id == R.id.nav_onduty) {
            Intent intent = new Intent(this,attend_od.class);
            startActivity(intent);

        } else if (id == R.id.nav_test) {
            Intent intent = new Intent(this,Exam_test.class);
            startActivity(intent);

        } else if (id == R.id.nav_internal) {
            Intent intent = new Intent(this,Exam_internal.class);
            startActivity(intent);

        } else if (id == R.id.nav_fees) {
            Intent intent = new Intent(this,Fee_fees.class);
            startActivity(intent);

        }else if (id == R.id.nav_pending) {
            Intent intent = new Intent(this,Fee_pending.class);
            startActivity(intent);

        }else if (id == R.id.nav_receipt) {
            Intent intent = new Intent(this, Fee_receipt.class);
            startActivity(intent);
        }else if (id == R.id.nav_personal) {
            Intent intent = new Intent(this, User_general.class);
            startActivity(intent);
        }else if (id == R.id.nav_library) {
            Intent intent = new Intent(this,Library_main.class);
            startActivity(intent);

        }else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        Toast toast;


        Intent i ;
        switch (view.getId()){
            case R.id.card_summary: i = new Intent(this,attend_summary.class);
                startActivity(i); break;
            case R.id.card_details: i = new Intent(this,attend_details.class);
                startActivity(i); break;
            case R.id.card_absent: i = new Intent(this,attend_absent.class);
                startActivity(i); break;
            case R.id.card_onduty: i = new Intent(this,attend_od.class);
                startActivity(i); break;
            case R.id.card_test:  i = new Intent(this,Exam_test.class);
                startActivity(i); break;
            case R.id.card_internal:  i = new Intent(this,Exam_internal.class);
                startActivity(i); break;
            case R.id.card_library: i = new Intent(this,Library_main.class);
                startActivity(i); break;
            case R.id.card_fees:  i = new Intent(this,Fee_fees.class);
                startActivity(i); break;
            case R.id.card_receipt: i = new Intent(this,Fee_receipt.class);
                startActivity(i); break;
            case R.id.card_pending: i = new Intent(this,Fee_pending.class);
                startActivity(i); break;
            case R.id.card_general: i = new Intent(this,User_general.class);
                startActivity(i); break;
            case R.id.card_logout:i = new Intent(this,Login.class);
                startActivity(i); break;

        }

    }
}
