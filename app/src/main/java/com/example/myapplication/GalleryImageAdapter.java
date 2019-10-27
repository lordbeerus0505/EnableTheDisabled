package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by anupamchugh on 24/10/15.
 */
public class GalleryImageAdapter extends BaseAdapter
{
    private Context mContext;
    String s="pokemon";
    Integer [] arr=new Integer[100];
    Object[] objects;

    public GalleryImageAdapter(Context context,String s)
    {
        this.s=s;
        System.out.println("The new string is "+s);
        mContext = context;
        process(s);
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


    // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        // TODO Auto-generated method stub
        ImageView i = new ImageView(mContext);
        i.setImageResource(mImageIds[arr[index]]);
        System.out.println(arr[index]+"   hello");
        i.setLayoutParams(new Gallery.LayoutParams(200, 200));

        i.setScaleType(ImageView.ScaleType.FIT_XY);


        return i;
    }
    public void process(String s)
    {
        s=s.toLowerCase();
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            int pos=c-'a';
            arr[i]=pos;

        }
        for(int i=s.length();i<27;i++)
            arr[i]=26;
    }

    /*R.drawable.alarm,
    R.drawable.explore,
    R.drawable.language,
    R.drawable.lock,
    R.drawable.print,
    R.drawable.rotation_3d,
    R.drawable.spellcheck,
    R.drawable.redeem,
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
    */
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
            R.drawable.z,
            R.drawable.black,
            R.drawable.sp
    };

}

