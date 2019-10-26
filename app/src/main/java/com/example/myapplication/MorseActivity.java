package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MorseActivity extends AppCompatActivity {

    Vibrator vibrator;
    Button longBtn,shortBtn,spaceBtn,cheatSheetBtn,Send;
    TextView textView;
    String letter=" ",convertedTxt="";
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse);
        longBtn=(Button)findViewById(R.id.longBut);
        shortBtn=(Button)findViewById(R.id.shortBut);
        spaceBtn=(Button)findViewById(R.id.spaceBut);
        cheatSheetBtn=(Button)findViewById(R.id.cheatSheet);

        Send=(Button)findViewById(R.id.Send);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        textView=(TextView)findViewById(R.id.textView);
        longBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Vibration long");
                vibrator.vibrate(400);
                letter+='-';
            }
        });
        shortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Vibration short");
                vibrator.vibrate(100);
                letter+='.';
            }
        });
        spaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Letter is "+letter);
                vibrator.vibrate(200);
                String txtToConvert = letter;
                convertedTxt += MorseCode.morseToAlpha(txtToConvert);
                convertedTxt.trim();
                textView.setText(convertedTxt);
                letter="";
            }
        });



        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Sending back the message");
                vibrator.vibrate(100);
                String s="";
                for(int i=0;i<convertedTxt.length();i++)
                {
                    char c=convertedTxt.charAt(i);
                    if(c!=' ')
                        s+=c;
                } 
                System.out.println(s);
                System.out.println("sending data now....");
                Intent intent = new Intent(MorseActivity.this, Chat.class);
                intent.putExtra("message", s);
                startActivity(intent);
            }
        });

        myDialog = new Dialog(this);



//
//        if (vibrator != null && vibrator.hasVibrator()) {
//
//            vibrateFor500ms();
//
//            customVibratePatternNoRepeat();
//
//            customVibratePatternRepeatFromSpecificIndex();
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                createOneShotVibrationUsingVibrationEffect();
//                createWaveFormVibrationUsingVibrationEffect();
//                createWaveFormVibrationUsingVibrationEffectAndAmplitude();
//            }
//
//        } else {
//            Toast.makeText(this, "Device does not support vibration", Toast.LENGTH_SHORT).show();
//        }
    }


    private void vibrateFor500ms() {
        vibrator.vibrate(500);
    }

    private void customVibratePatternNoRepeat() {

        // 0 : Start without a delay
        // 400 : Vibrate for 400 milliseconds
        // 200 : Pause for 200 milliseconds
        // 400 : Vibrate for 400 milliseconds
        long[] mVibratePattern = new long[]{0, 400, 200, 400};

        // -1 : Do not repeat this pattern
        // pass 0 if you want to repeat this pattern from 0th index
        vibrator.vibrate(mVibratePattern, -1);

    }

    private void customVibratePatternRepeatFromSpecificIndex() {
        long[] mVibratePattern = new long[]{0, 400, 800, 600, 800, 800, 800, 1000};

        // 3 : Repeat this pattern from 3rd element of an array
        vibrator.vibrate(mVibratePattern, 3);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createOneShotVibrationUsingVibrationEffect() {
        // 1000 : Vibrate for 1 sec
        // VibrationEffect.DEFAULT_AMPLITUDE - would perform vibration at full strength
        VibrationEffect effect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);
        vibrator.vibrate(effect);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createWaveFormVibrationUsingVibrationEffect() {
        long[] mVibratePattern = new long[]{0, 400, 1000, 600, 1000, 800, 1000, 1000};
        // -1 : Play exactly once
        VibrationEffect effect = VibrationEffect.createWaveform(mVibratePattern, -1);
        vibrator.vibrate(effect);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createWaveFormVibrationUsingVibrationEffectAndAmplitude() {

        long[] mVibratePattern = new long[]{0, 400, 800, 600, 800, 800, 800, 1000};
        int[] mAmplitudes = new int[]{0, 255, 0, 255, 0, 255, 0, 255};
        // -1 : Play exactly once

        if (vibrator.hasAmplitudeControl()) {
            VibrationEffect effect = VibrationEffect.createWaveform(mVibratePattern, mAmplitudes, -1);
            vibrator.vibrate(effect);
        }
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
