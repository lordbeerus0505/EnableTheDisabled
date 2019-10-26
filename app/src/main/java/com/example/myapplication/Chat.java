package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class Chat extends AppCompatActivity {
    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    Button text2speech,text2morse;
    Button camera_button,voice_btn,morseBut;
    SettingsContentObserver volObserver;
    Dialog myDialog;

    int count=0;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_firebase);
        TextView textView=(TextView)findViewById(R.id.text);
//        textView.setText("hellooooo");
        layout = (LinearLayout) findViewById(R.id.layout1);
        layout_2 = (RelativeLayout)findViewById(R.id.layout2);
        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        camera_button=(Button)findViewById(R.id.camera_button);
        text2morse=(Button) findViewById(R.id.text2morse);
        voice_btn=(Button)findViewById(R.id.voiceBut);
        morseBut=(Button)findViewById(R.id.morseBut);
        text2speech=(Button) findViewById(R.id.text2speech);

        myDialog = new Dialog(this);

        volObserver = new SettingsContentObserver(this, new Handler(), Chat.this);
        getApplicationContext().getContentResolver().registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, volObserver);

        morseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chat.this, MorseActivity.class));
            }
        });
        Firebase.setAndroidContext(this);

        reference1 = new Firebase("https://myapplication-fc320.firebaseio.com/messages/" + UserDetails.username + "_" + UserDetails.chatWith);
        reference2 = new Firebase("https://myapplication-fc320.firebaseio.com/messages/" + UserDetails.chatWith + "_" + UserDetails.username);

        camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chat.this, CameraActivity.class));
            }
        });
        camera_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ShowPopup(v);

                return true;
            }
        });

        voice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chat.this, VoiceRecord.class));
            }
        });

        text2morse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, Text2Morse.class);
                intent.putExtra("message", UserDetails.lastmessage);
                startActivity(intent);
//                startActivity(new Intent(Chat.this, TextToSpeech.class));
            }
        });
        text2speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, TextToSpeech.class);
                intent.putExtra("message", UserDetails.lastmessage);
                startActivity(intent);
//                startActivity(new Intent(Chat.this, TextToSpeech.class));
            }
        });
        Intent intent = getIntent();
        message = intent.getStringExtra("message");
        if(message!=null)
        {
            count=1;
        }
        System.out.println("Message is "+message);
//        textView.setText(message);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();

                if(count==1)
                {messageText=message;
                    count=0;}//Check if this works else remove the count.
                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", UserDetails.username);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");
                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();
                System.out.println(map.size());
                UserDetails.lastmessage=message;

                if(userName.equals(UserDetails.username)){
                    addMessageBox(  "\n  "+message+"  ", 1);
                }
                else{
                    addMessageBox(   "\n  " + message+ "  ", 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addMessageBox(String message, int type){
        TextView textView = new TextView(Chat.this);
        textView.setText(message);
        System.out.println("Message is "+message);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;
        lp2.setMargins(20,3,10,3);
        if(type == 1) {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.sender);
        }
        else{
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.receiver);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    public void ShowPopup(View v) {
        myDialog.setContentView(R.layout.cheat_sheet);

        Button closeSheet = myDialog.findViewById(R.id.closeSheet);

        closeSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }
}