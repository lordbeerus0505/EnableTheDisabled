package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoiceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        Button btn = (Button)findViewById(R.id.back_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoiceActivity.this, LoginActivity.class));
            }
        });
        Button btn2 = (Button)findViewById(R.id.voice_button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoiceActivity.this, VoiceActivity.class));
            }
        });
        Button btn3 = (Button)findViewById(R.id.camera_button);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoiceActivity.this, CameraActivity.class));
            }
        });
        Button btn4=(Button)findViewById((R.id.record_button));
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoiceActivity.this, VoiceRecord.class));
            }
        });
    }
}
