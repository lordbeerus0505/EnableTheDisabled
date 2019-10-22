package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, Login.class));
//        final EditText uid=(EditText)findViewById(R.id.uid);
//        final EditText pwd=(EditText)findViewById(R.id.pwd);
//        Button btn = (Button)findViewById(R.id.login);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//        if(uid.getText().toString().equals("chainreaction")  )
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });
//        Button btn2=(Button)findViewById(R.id.sign_up);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Pressing button");
//                startActivity(new Intent(MainActivity.this, Login.class));
//            }
//        });
    }


}
