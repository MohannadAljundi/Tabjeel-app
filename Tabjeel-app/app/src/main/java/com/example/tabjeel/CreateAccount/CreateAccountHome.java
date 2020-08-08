package com.example.tabjeel.CreateAccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.example.tabjeel.Login.LoginActivity;
import com.example.tabjeel.R;
import com.example.tabjeel.Utils.UserSessionManager;
import com.example.tabjeel.anim.LoadingGreenActivity;

import java.util.HashMap;


public class CreateAccountHome extends AppCompatActivity implements View.OnClickListener {

    UserSessionManager session ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_home);

        session = new UserSessionManager(getApplicationContext());


        findViewById(R.id.start_create_account_btn).setOnClickListener(this);
        findViewById(R.id.already_have_account_txt).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_create_account_btn:{
                Intent i = new Intent(getApplicationContext(), LoadingGreenActivity.class);
                startActivity(i);
            }break;

            case  R.id.already_have_account_txt:{
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }break;

        }
    }
}