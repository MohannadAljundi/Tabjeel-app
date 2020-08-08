package com.example.tabjeel.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tabjeel.Home.HomeActivity;
import com.example.tabjeel.Login.LoginActivity;
import com.example.tabjeel.R;
import com.example.tabjeel.Utils.UserSessionManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class CreateAccountSuccessfullyAnimActivity extends AppCompatActivity {

    private final int LOADING_DISPLAY_LENGTH = 3000;
    UserSessionManager session ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_successfully_anim);
        session = new UserSessionManager(getApplicationContext());



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
                CreateAccountSuccessfullyAnimActivity.this.startActivity(mainIntent);
                CreateAccountSuccessfullyAnimActivity.this.finish();

            }
        }, LOADING_DISPLAY_LENGTH);
    }
}