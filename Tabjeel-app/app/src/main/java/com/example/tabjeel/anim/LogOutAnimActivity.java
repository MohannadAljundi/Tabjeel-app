package com.example.tabjeel.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tabjeel.CreateAccount.CreateAccountStartUpActivity;
import com.example.tabjeel.Login.LoginActivity;
import com.example.tabjeel.R;
import com.example.tabjeel.Utils.UserSessionManager;
import com.google.firebase.auth.FirebaseAuth;

public class LogOutAnimActivity extends AppCompatActivity {

    private final int LOADING_DISPLAY_LENGTH = 3000;
    UserSessionManager session ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out_anim);
        session = new UserSessionManager(getApplicationContext());

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(getApplicationContext(), LoginActivity.class);
                LogOutAnimActivity.this.startActivity(mainIntent);
                LogOutAnimActivity.this.finish();
                FirebaseAuth.getInstance().signOut();
                session.logoutUser();
            }
        }, LOADING_DISPLAY_LENGTH);
    }
}