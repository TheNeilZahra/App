package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText registerName, registerEmail, registerPassword, registerPassword2;
    ImageButton signup;

    DBHelper DB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerName = (EditText)findViewById(R.id.registerName);
        registerEmail = (EditText)findViewById(R.id.registerEmail);
        registerPassword = (EditText)findViewById(R.id.registerPassword);
        registerPassword2 = (EditText)findViewById(R.id.registerPassword2);
        signup = (ImageButton)findViewById(R.id.signup);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = registerName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String repassword = registerPassword2.getText().toString();

                if (name.equals("")||email.equals("")||password.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if(password.equals(repassword)){
                        Boolean checkuser = DB.checkEmail(email);
                                if(checkuser==false){
                                    Boolean insert = DB.insertData(name,email,password);
                                    if(insert==true){
                                        Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                        intent.putExtra("name",name);
                                        intent.putExtra("email",email);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "User Already Exists!, Please sign in.", Toast.LENGTH_SHORT).show();
                                }
                    }else {
                        Toast.makeText(RegisterActivity.this, "Passwords Do Not Match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
