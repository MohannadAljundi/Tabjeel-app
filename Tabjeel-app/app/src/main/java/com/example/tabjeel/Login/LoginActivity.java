package com.example.tabjeel.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tabjeel.CreateAccount.CreateAccountHome;
import com.example.tabjeel.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.you_do_not_have_account_txt).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.you_do_not_have_account_txt:{
                Intent i = new Intent(getApplicationContext(), CreateAccountHome.class);
                startActivity(i);
            }break;
        }
    }
}