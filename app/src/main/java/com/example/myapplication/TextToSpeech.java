package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class TextToSpeech extends AppCompatActivity {

    android.speech.tts.TextToSpeech t1;
    EditText ed1;
    Button b1;
    private String message;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        ed1=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.button);
        t1=new android.speech.tts.TextToSpeech(getApplicationContext(), new android.speech.tts.TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != android.speech.tts.TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        String toSpeak = ed1.getText().toString();
        if(count==1)
            toSpeak=message;
        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
        t1.speak(toSpeak, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);
        new CountDownTimer(5000, 1000) {
            public void onFinish() {
                // When timer is finished
                // Execute your code here
                startActivity(new Intent(TextToSpeech.this, Chat.class));
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = ed1.getText().toString();
                if(count==1)
                    toSpeak=message;
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);
                new CountDownTimer(1000, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        startActivity(new Intent(TextToSpeech.this, Chat.class));
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();

            }
        });
        Intent intent = getIntent();
        message = intent.getStringExtra("message");
        if(message!=null)
        {
            count=1;
        }
        System.out.println("Message is "+message);
    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
