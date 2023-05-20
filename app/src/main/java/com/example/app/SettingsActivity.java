package com.example.app;

import static com.example.app.GlobalVariables.*;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    ImageButton homeButton, rewardButton, couponsButton, loyaltyButton;
    private LinearLayout helpSupportSetting, logOutSetting;
    private static final String SUPPORT_EMAIL = "supermarket.app.mt@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        helpSupportSetting = findViewById(R.id.helpSupportSetting);
        logOutSetting = findViewById(R.id.logOutSetting);

        helpSupportSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactEmail();
            }
        });

        logOutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear global variables or any user session data
                // For example, assuming you have a global variable called "currentUser"
                name = null;
                email = null;

                // Navigate to the login activity
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                // Clear the activity stack and create a new task for the login activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        homeButton = findViewById(R.id.homeButton3);
        rewardButton = findViewById(R.id.rewardButton4);
        couponsButton = findViewById(R.id.couponsButton3);
        loyaltyButton = findViewById(R.id.loyaltyButton3);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
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

        couponsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CouponActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
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
    }

    private void showContactEmail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_contact_support, null);
        builder.setView(dialogView);
        builder.setCancelable(true);

        TextView emailTextView = dialogView.findViewById(R.id.emailTextView);
        Button okButton = dialogView.findViewById(R.id.okButton);

        emailTextView.setText("To contact support,\n please email: \n\n" + SUPPORT_EMAIL + "\n");

        final AlertDialog dialog = builder.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}





