package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class FileOpen extends AppCompatActivity {
    Button read;
    ImageView imageView;
    File directory,photoDirectory;
    @Override
    protected void onCreate(Bundle readdInstanceState) {
        super.onCreate(readdInstanceState);

        setContentView(R.layout.activity_file_open);
        imageView = findViewById(R.id.imageView);
        read = findViewById(R.id.read);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello");
                //if there is no SD card, create new directory objects to make directory on device
                if (Environment.getExternalStorageState() == null) {
                    //create new file directory object
                    directory = new File(Environment.getDataDirectory()
                            + "/ASL/");
                    photoDirectory = new File(Environment.getDataDirectory()
                            + "/ASL/");
                    System.out.println("Check if exists");
                    /*
                     * this checks to see if there are any previous test photo files
                     * if there are any photos, they are deleted for the sake of
                     * memory
                     */
                    if (photoDirectory.exists()) {
                        System.out.println("Check if exists");
//                        File[] dirFiles = photoDirectory.listFiles();
//                        if (dirFiles.length != 0) {
//                            for (int ii = 0; ii <= dirFiles.length; ii++) {
//                                dirFiles[ii].delete();
//                            }
//                        }
                        String path=photoDirectory+"/a.png";
                        System.out.println(path);
                    }
                    // if no directory exists, create new directory
                    if (!directory.exists()) {
                        directory.mkdir();
                    }

                    // if phone DOES have sd card
                } else if (Environment.getExternalStorageState() != null) {
                    System.out.println("Byeee");
                    // search for directory on SD card
                    directory = new File(Environment.getExternalStorageDirectory()
                            + "/ASL/");
                    photoDirectory = new File(
                            Environment.getExternalStorageDirectory()
                                    + "/ASL/");
                    if (photoDirectory.exists()) {
                        System.out.println(photoDirectory);
                        File[] dirFiles = photoDirectory.listFiles();
                        String path=photoDirectory+"/a.png";
                        System.out.println(path);
                        if (dirFiles.length > 0) {
                            System.out.println("Exists");
                            ContextWrapper cw = new ContextWrapper(getApplicationContext());
//                            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                            File file = new File(directory, "a" + ".png");
                            imageView.setImageDrawable(Drawable.createFromPath(file.toString()));


                        }
                    }
                    // if no directory exists, create new directory to store test
                    // results
                    if (!directory.exists()) {
                        System.out.println("Create directory");
                        directory.mkdir();
                    }
                }// end of SD card checking
            }
        });
    }
}