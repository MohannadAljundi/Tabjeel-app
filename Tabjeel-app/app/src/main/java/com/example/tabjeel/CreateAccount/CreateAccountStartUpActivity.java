package com.example.tabjeel.CreateAccount;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.tabjeel.Dialogs.Terms;
import com.example.tabjeel.Dialogs.TermsAndCondtionsActivity;
import com.example.tabjeel.R;


public class CreateAccountStartUpActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isCheckedDone_ConditionalAndTerms = false;
    private String terms_str;
    private Terms terms = new Terms();
    private TextView terms_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_start_up);

        terms_str = "1.\n" +
                "Why Mobile Apps Need Terms & Conditions\n" +
                "1.1.\n" +
                "Business Benefits\n" +
                "1.2.\n" +
                "User Benefits\n" +
                "1.3.\n" +
                "Terms and Conditions For Simple Apps\n" +
                "2.\n" +
                "Is a Terms and Conditions Agreement Required by Laws or App Stores?\n" +
                "3.\n" +
                "Clauses for Terms & Conditions for Apps\n" +
                "3.1.\n" +
                "Intellectual Property Clause\n" +
                "3.2.\n" +
                "Ownership and Use of Content Clauses\n" +
                "3.3.\n" +
                "Copyright Violations (Digital Millennium Copyright Act (\"DMCA\") Section)\n" +
                "3.4.\n" +
                "Retain Right to Filter Content Clause\n" +
                "3.5.\n" +
                "Prohibited Activities Clause\n" +
                "3.6.\n" +
                "Who Can Use Your Service Clause\n" +
                "3.7.\n" +
                "Warranty/Limitation of Liability Clause\n" +
                "4.\n" +
                "Getting Agreement to Your App's Terms and Conditions\n" +
                "5.\n" +
                "How to Create a Terms and Conditions for Your Mobile App";

        terms.setTerms_str(terms_str);
        findViewById(R.id.continue_to_phone_verification_btn).setOnClickListener(this);
        terms_txt = findViewById(R.id.terms_and_conditions_text);

        terms_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(CreateAccountStartUpActivity.this);
//                boolean agreed = sharedPreferences.getBoolean("agreed",false);
//                if (!agreed) {
                    new AlertDialog.Builder(CreateAccountStartUpActivity.this)
                            .setTitle("الاحكام و الشروط")
                            .setPositiveButton("قرأته بتمعن", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                                    editor.putBoolean("agreed", true);
//                                    editor.apply();
                                }
                            })
                            .setMessage(terms_str)
                            .show();
                }
            //}
        });




        final LottieAnimationView ConditionalAndTerms_CheckBox = findViewById(R.id.terms_and_conditions_anim);
        ConditionalAndTerms_CheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheckedDone_ConditionalAndTerms){
                    ConditionalAndTerms_CheckBox.setSpeed(-1);
                    ConditionalAndTerms_CheckBox.playAnimation();
                    isCheckedDone_ConditionalAndTerms = false;
                }else {
                    ConditionalAndTerms_CheckBox.setSpeed(1);
                    ConditionalAndTerms_CheckBox.playAnimation();
                    isCheckedDone_ConditionalAndTerms = true;
                }
            }
        });



    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_to_phone_verification_btn:{
                Intent i = new Intent(getApplicationContext(), CreateAccountPhoneVerification.class);
                startActivity(i);
            }break;


        }
    }
}