package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("chatParticipants", UserDetails.username+'_'+UserDetails.chatWith)
                .build();

        final Request request = new Request.Builder()
                .url("http://10.16.160.162:5000")
                .post(formBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();

                    if(response.code() == 200)
                        response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.pas.webcam");
        if (launchIntent != null) {
            startActivity(launchIntent);//null pointer check in case package name was not found
        }
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, 1);
//        }
//        Button btn = (Button)findViewById(R.id.back_button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CameraActivity.this, ChatActivity.class));
//            }
//        });
//        Button btn2 = (Button)findViewById(R.id.camera_button);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CameraActivity.this, CameraShoot.class));
//            }
//        });
//        Button btn3 = (Button)findViewById(R.id.voice_button);
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CameraActivity.this, VoiceActivity.class));
//            }
//        });
    }
}
