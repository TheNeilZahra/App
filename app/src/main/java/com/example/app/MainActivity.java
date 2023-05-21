package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword, registerName, registerEmail, registerPassword;
    TextView redirectNewReg;
    ImageButton login;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = (EditText)findViewById(R.id.loginEmail);
        loginPassword = (EditText)findViewById(R.id.loginPassword);
        login = (ImageButton)findViewById(R.id.login);
        redirectNewReg = findViewById(R.id.redirectNewReg);
        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                //Validates that the Account exists
                if(email.equals("")||password.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuseremailpass = DB.checkemailpassword(email,password);
                    String name = DB.getRelativeName(email,password);
                    if(checkuseremailpass==true){
                        Toast.makeText(MainActivity.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                        GlobalVariables.name = name;
                        GlobalVariables.email = email;
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("email",email);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        redirectNewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }

        });

    }
}