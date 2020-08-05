package com.example.tabjeel.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tabjeel.CreateAccount.CreateAccountStartUpActivity;
import com.example.tabjeel.R;

public class LoadingGreenActivity extends AppCompatActivity {

    private final int LOADING_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_green);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(getApplicationContext(), CreateAccountStartUpActivity.class);
                LoadingGreenActivity.this.startActivity(mainIntent);
                LoadingGreenActivity.this.finish();
            }
        }, LOADING_DISPLAY_LENGTH);

    }
}