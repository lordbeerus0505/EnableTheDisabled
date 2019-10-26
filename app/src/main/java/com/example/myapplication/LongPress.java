package com.example.myapplication;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class LongPress extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_press);
        TextView txtView = (TextView) findViewById(R.id.txtView);
        txtView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),"You have pressed it long :)", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        txtView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stu
                Toast.makeText(getApplicationContext(), "Not Long Enough :(",Toast.LENGTH_SHORT).show();
            }
        });

    }
}