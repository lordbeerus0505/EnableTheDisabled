package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {
    Button twitter,facebook,signup,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        twitter=(Button)findViewById(R.id.twitter);
        facebook=(Button)findViewById(R.id.facebook);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this, Login.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this, Register.class));
            }
        });
    }
}
