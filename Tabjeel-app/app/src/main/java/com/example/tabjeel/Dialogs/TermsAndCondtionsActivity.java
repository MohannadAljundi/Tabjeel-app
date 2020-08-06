package com.example.tabjeel.Dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.tabjeel.CreateAccount.CreateAccountPhoneVerification;
import com.example.tabjeel.R;

public class TermsAndCondtionsActivity extends AppCompatActivity {

    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condtions);

        text = "1.\n" +
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

                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                boolean agreed = sharedPreferences.getBoolean("agreed",false);
                if (!agreed) {
                    new AlertDialog.Builder(this)
                            .setTitle("License agreement")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("agreed", true);
                                    editor.apply();
                                    Intent i = new Intent(getApplicationContext(), CreateAccountPhoneVerification.class);
                                    startActivity(i);
                                }
                            })
                            .setMessage(text)
                            .show();
                }
            }
    }
