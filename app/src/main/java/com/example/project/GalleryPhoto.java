package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;

public class GalleryPhoto extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    ImageAdapter myImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_photo);

        GridView gridView=(GridView)findViewById(R.id.gridview);
        myImageAdapter=new ImageAdapter(this);
        gridView.setAdapter(myImageAdapter);
        gridView.setOnItemClickListener(this);

        String ExternalStorageDirectoryPath= Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        String targetPath=ExternalStorageDirectoryPath+"/GAMBAR/";
        Toast.makeText(getApplicationContext(),targetPath,Toast.LENGTH_SHORT).show();
        File targetDirector=new File(targetPath);
        File[] files=targetDirector.listFiles();
        for (File file:files){
            myImageAdapter.add(file.getAbsolutePath());
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int positions, long id) {
        Toast.makeText(GalleryPhoto.this,"Gambar no"+positions+"Dipilih",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),SinglePhoto.class);
        i.putExtra("path",myImageAdapter.itemList.get(positions));
        startActivity(i);
    }
}
