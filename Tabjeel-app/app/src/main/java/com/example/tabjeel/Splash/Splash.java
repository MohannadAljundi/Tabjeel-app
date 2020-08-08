package com.example.tabjeel.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tabjeel.CreateAccount.CreateAccountHome;
import com.example.tabjeel.Home.HomeActivity;
import com.example.tabjeel.Home.HomeTestActivity;
import com.example.tabjeel.ImageSlider.AutoImageSliderActivity;
import com.example.tabjeel.MainActivity;
import com.example.tabjeel.R;
import com.example.tabjeel.Utils.UserSessionManager;

public class Splash extends AppCompatActivity {

    UserSessionManager session ;
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    ImageView tabjeelWord;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        session = new UserSessionManager(getApplicationContext());

        tabjeelWord = findViewById(R.id.tabjeel_word_splash);

        animation = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        animation.setDuration(1000);
        tabjeelWord.setAnimation(animation);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if(session.isLoggedIn()){
                    Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }else {
                    Intent mainIntent = new Intent(getApplicationContext(), CreateAccountHome.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}