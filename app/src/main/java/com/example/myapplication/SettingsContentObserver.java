package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;

import android.os.Handler;

public class SettingsContentObserver extends ContentObserver {
    int previousVolume;
    Context context, chat;

    public SettingsContentObserver(Context c, Handler handler, Context chat) {

        super(handler);
        context=c;

        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        previousVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        this.chat = chat;
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        System.out.println(3);
        super.onChange(selfChange);

        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        int delta=previousVolume-currentVolume;

        if(delta != 0)
        {
            Intent intent = new Intent(chat, Text2Morse.class);
            intent.putExtra("message", UserDetails.lastmessage);

            context.startActivity(intent);
            previousVolume=currentVolume;
        }
    }
}

