package com.example.tabjeel.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tabjeel.Home.HomeActivity;
import com.example.tabjeel.Home.HomeTestActivity;
import com.example.tabjeel.R;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 8000;
    ImageView tabjeelWord;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tabjeelWord = findViewById(R.id.tabjeel_word_splash);

        animation = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        animation.setDuration(1000);
        tabjeelWord.setAnimation(animation);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}