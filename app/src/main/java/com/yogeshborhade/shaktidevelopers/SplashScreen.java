package com.yogeshborhade.shaktidevelopers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yogeshborhade.shaktidevelopers.LoginPage.LoginPage;

public class SplashScreen extends AppCompatActivity {

    ImageView imageView;
    Handler handler = new Handler();
   final int SPLASH_TIME_OUT=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView) findViewById(R.id.mimageDurga);
        Glide.with(this).load(R.drawable.durgapuja).into(imageView);



        new Handler().postDelayed(new Runnable() {





            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, LoginPage.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);



    }

}
