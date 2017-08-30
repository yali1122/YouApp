package com.example.ahsan.youtube;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class splash extends AppCompatActivity {
    ImageView
            image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image=(ImageView)findViewById(R.id.imageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();

            }
        },6000);

        image.animate().rotation(180).scaleX(1).scaleY(1).rotation(180).scaleX(-1).scaleY(-1).setDuration(6000);
    }
}
