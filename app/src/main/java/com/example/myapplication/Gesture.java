package com.example.myapplication;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Gesture extends AppCompatActivity {

    ImageView selectedImage;
    String s="pokemon";
    String message;
    int count=0;
    ArrayList<Integer> arr=new ArrayList<Integer>();

    public Integer[] mImageIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j,
            R.drawable.k,
            R.drawable.l,
            R.drawable.m,
            R.drawable.n,
            R.drawable.o,
            R.drawable.p,
            R.drawable.q,
            R.drawable.r,
            R.drawable.s,
            R.drawable.t,
            R.drawable.u,
            R.drawable.v,
            R.drawable.w,
            R.drawable.x,
            R.drawable.y,
            R.drawable.z
    };
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        selectedImage=(ImageView)findViewById(R.id.imageView);
        gallery.setSpacing(1);
        Intent intent = getIntent();
        message = intent.getStringExtra("message");
        if(message!=null)
        {
            count=1;
        }
        if(count==1) {
            final GalleryImageAdapter galleryImageAdapter = new GalleryImageAdapter(this, message);
            gallery.setAdapter(galleryImageAdapter);


            gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    // show the selected Image
                    selectedImage.setImageResource(galleryImageAdapter.mImageIds[position]);
                }
            });
        }


    }
    public void process(String s)
    {
        s=s.toLowerCase();
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            int pos=c-'a';
            arr.add(pos);

        }
    }
}