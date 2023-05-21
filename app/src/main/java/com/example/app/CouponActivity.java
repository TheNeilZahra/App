package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CouponActivity extends AppCompatActivity {

    DBHelper DB;

    ImageButton homeButton, loyaltyButton, rewardButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        DB = new DBHelper(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");

        String reward = email;

        homeButton = findViewById(R.id.homeButton);
        loyaltyButton = findViewById(R.id.loyaltyButton);
        rewardButton = findViewById(R.id.rewardButton);

        //On set Listeners

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }

        });

        loyaltyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QrActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                startActivity(intent);
            }

        });

        rewardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RewardActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                startActivity(intent);
            }

        });
    }

}
