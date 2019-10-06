package com.example.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mcontext;
    ArrayList<String> itemList=new ArrayList<String>();

    public ImageAdapter(Context c){
        mcontext =c;
    }
    void add(String path) {
        itemList.add(path);
    }

    @Override
    public int getCount() {

        return itemList.size();
    }

    @Override
    public Object getItem(int i) {

        return i;
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView==null){
            imageView=new ImageView(mcontext);
            imageView.setLayoutParams(new GridView.LayoutParams(350,350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        }else{
            imageView=(ImageView)convertView;
        }
        Bitmap bitmap = decodeSampledBitmapFromUri(itemList.get(i),350, 350);
        imageView.setImageBitmap(bitmap);
        return imageView;
    }
    public Bitmap decodeSampledBitmapFromUri(String path,int reqWidht,int reqHeight){
        Bitmap bm=null;

        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(path,options);

        options.inSampleSize=calculateInsmapleSize(options,reqWidht,reqHeight);

        options.inJustDecodeBounds=false;
        bm=BitmapFactory.decodeFile(path,options);
        return bm;
    }
    public int calculateInsmapleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        final int height =options.outHeight;
        final int width=options.outWidth;
        int inSampleSize=1;

        if (height>reqHeight|| width > reqWidth){
            if (width>height){
                inSampleSize=Math.round((float)  height/(float)reqHeight);
            }else {
                inSampleSize=Math.round((float)reqWidth/(float)reqWidth);
            }
        }
        return  inSampleSize;
    }
}
