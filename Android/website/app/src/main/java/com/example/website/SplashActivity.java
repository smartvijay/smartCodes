package com.example.website;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.purple_200));
        }


        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 1500);
        }
}