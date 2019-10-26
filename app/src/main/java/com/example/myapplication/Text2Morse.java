package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Text2Morse extends AppCompatActivity {
    Vibrator vibrator;
    String message="";
    int count=0;
    TextView mess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2_morse);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        mess=(TextView)findViewById(R.id.mess);
        Intent intent = getIntent();
        message = intent.getStringExtra("message");
        if(message!=null)
        {
            count=1;
            process(message);
        }
        System.out.println("Message is "+message);
    }
    private void process(String text){
        String letter="";
        text=MorseCode.alphaToMorse(text);
//        mess.setText(text);
        text=text.trim();
        text+=' ';
        long[] vib = new long[1000];
        int count=0;
//        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        for(int i=0;i<text.length();i++)
        {
            System.out.println(text+" "+text.length());
            if(text.charAt(i)!=' ')
                letter+=text.charAt(i);
            else {

                for(int j=0;j<letter.length();j++)
                {   System.out.println("getting out the letter");
                    final char l=letter.charAt(j);
                    if(l=='.')
                    {vib[2*count]=100;
                        vib[2*count+1]=100;}
                    else
                    {
                        vib[2*count]=400;
                        vib[2*count+1]=100;
                    }
                    count++;

//                    customVibratePatternNoRepeat(vib);
                    System.out.println("letter processed");

                }
                letter="";
                vib[2*count]=1000;
                vib[2*count+1]=100;
                count++;
//                vibrator.vibrate(space_vib,-1);
            }
            vib[2*count]=2000;
            vib[2*count+1]=100;
//            count++;
//            vibrator.vibrate(space_vib,-1);
//            vibrator.vibrate(space_vib,-1);
//
        }
        customVibratePatternNoRepeat(vib);
    }

    private void customVibratePatternNoRepeat(long [] vib) {

        // 0 : Start without a delay
        // 400 : Vibrate for 400 milliseconds
        // 200 : Pause for 200 milliseconds
        // 400 : Vibrate for 400 milliseconds
        long[] mVibratePattern = new long[]{};

        // -1 : Do not repeat this pattern
        // pass 0 if you want to repeat this pattern from 0th index
        vibrator.vibrate(vib, -1);

    }
}
