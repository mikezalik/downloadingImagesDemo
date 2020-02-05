package com.mikezalik.downloadingimagesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public void downloadImage (View view) {
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try {
            myImage = task.execute("https://www.google.com/url?sa=i&url=https%3A%2F%2Fgenerations-starwars.com%2F2019%2F11%2F15%2Fdisney-releases-baby-yoda-plush-from-mandalorian-or-baby-yoda-toy-buy-now-available-for-pre-order%2F&psig=AOvVaw13V34N9sfccnP2xRTQEirn&ust=1580952631442000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNjC0tOhuecCFQAAAAAdAAAAABAD").get();
            imageView.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
              URL url = new URL(urls[0]);
              HttpURLConnection connection = (HttpURLConnection) url.openConnection();
              connection.connect();

              InputStream in = connection.getInputStream();

                return BitmapFactory.decodeStream(in);

            } catch (Exception e) {
              e.printStackTrace();
              return null;
            }
        }
    }
}
