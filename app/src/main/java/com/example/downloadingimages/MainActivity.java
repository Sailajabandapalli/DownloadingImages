package com.example.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
    public void Download(View view){
        ImageDownloader task=new ImageDownloader();
        Bitmap myImage;
        try {
            myImage = task.execute("https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg").get();
            imageView.setImageBitmap(myImage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     imageView=findViewById(R.id.imageView2);
    }
    public class ImageDownloader extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                Bitmap mybitmap= BitmapFactory.decodeStream(inputStream);
                return mybitmap;
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }
}
