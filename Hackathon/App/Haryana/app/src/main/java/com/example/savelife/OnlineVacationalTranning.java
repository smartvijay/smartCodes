package com.example.savelife;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class OnlineVacationalTranning extends AppCompatActivity {


    CardView cardOne,cardTwo,cardThree,cardFour,cardFive,cardSix,cardSeven,cardEight,cardNine,cardTen,
            cardEleven,cardTwelve;

    // Sample test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_vacational_tranning);

        // card to Next Activity

        cardOne = findViewById(R.id.module_card_one);
        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, RegisterCourses.class);
                startActivity(intent);
            }
        });
        cardTwo = findViewById(R.id.module_card_two);
        cardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseC.class);
                startActivity(intent);
            }
        });
        cardThree = findViewById(R.id.module_card_three);
        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseCPlusPlus.class);
                startActivity(intent);
            }
        });
        cardFour = findViewById(R.id.module_card_four);
        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseDesign.class);
                startActivity(intent);
            }
        });
        cardFive = findViewById(R.id.module_card_five);
        cardFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseJava.class);
                startActivity(intent);
            }
        });
        cardSix = findViewById(R.id.module_card_six);
        cardSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseHtml.class);
                startActivity(intent);
            }
        });
        cardSeven = findViewById(R.id.module_card_seven);
        cardSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseCSharp.class);
                startActivity(intent);
            }
        });
        cardEight = findViewById(R.id.module_card_eight);
        cardEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CoursePython.class);
                startActivity(intent);
            }
        });
        cardNine = findViewById(R.id.module_card_nine);
        cardNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseRedhat.class);
                startActivity(intent);
            }
        });
        cardTen = findViewById(R.id.module_card_ten);
        cardTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseUpse.class);
                startActivity(intent);
            }
        });
        cardEleven = findViewById(R.id.module_card_eleven);
        cardEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseAndroid.class);
                startActivity(intent);
            }
        });
        cardTwelve = findViewById(R.id.module_card_twelve);
        cardTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineVacationalTranning.this, CourseWebsite.class);
                startActivity(intent);
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

}
