package com.example.savelife;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterAdmission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button1;
        Button button2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admissions);
        button1 = (Button) findViewById(R.id.bt_login);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
                Toast.makeText(RegisterAdmission.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.basic_topbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.website:
                Intent intent = new Intent(this, WebsiteView.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}

