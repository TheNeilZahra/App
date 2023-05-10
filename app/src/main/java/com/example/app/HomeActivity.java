package com.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView userName;
    DBHelper DB;
    LinearLayout layout;

    String name, email;

    public static final String SHARED_PREFS ="sharedPrefs";
    //public static final String TEXT = "text";

    //private String text;

    //int counter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // counter++;

        DB = new DBHelper(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");

        if(name != null) {

            // save the name and email in Shared Preferences
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.apply();

        }
        userName = (TextView)findViewById(R.id.userName);

        if(name == null) {
            // retrieve the name and email from Shared Preferences
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            String savedName = sharedPreferences.getString("name", "");
            String savedEmail = sharedPreferences.getString("email", "");

            name = savedName;
            email = savedEmail;
        }


        userName.setText(name);

        layout = (LinearLayout)findViewById(R.id.linearLayout6);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveData();
                Intent intent = new Intent(getApplicationContext(), QrActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                startActivity(intent);
            }

        });

        /*
        if (counter>1) {
            loadData();
            updateViews();
        }
         */
    }


    /*
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, userName.getText().toString());

        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT);
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews() {
        userName.setText(text);
    }
    */



}