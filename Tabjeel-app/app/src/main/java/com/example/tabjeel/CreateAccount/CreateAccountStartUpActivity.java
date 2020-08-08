package com.example.tabjeel.CreateAccount;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.tabjeel.Dialogs.Terms;
import com.example.tabjeel.Dialogs.TermsAndCondtionsActivity;
import com.example.tabjeel.R;
import com.example.tabjeel.Utils.SendingEmailUtils;
import com.example.tabjeel.Utils.UserSessionManager;
import com.example.tabjeel.Utils.UserUtilsInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.mail.MessagingException;


public class CreateAccountStartUpActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG_EMAIL = "TAG_EMAIL";
    private boolean isCheckedDone_ConditionalAndTerms = false;
    private String terms_str;
    private Terms terms = new Terms();
    private TextView terms_txt;
    UserSessionManager session ;
    UserUtilsInfo userUtilsInfo = new UserUtilsInfo();
    private FirebaseDatabase firebaseInstance;
    private DatabaseReference firebaseDatabase;
    private FirebaseAuth mAuth;
    private EditText Email , Password , FullName , Phone;
    private SendingEmailUtils SendEmail;
    public String  UserID , FullName_Str , Email_Str , Password_Str , Phone_Str;
    private Button ContinueToVerificationPhone;
    String PhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account_start_up);
        session = new UserSessionManager(getApplicationContext());

        mAuth = FirebaseAuth.getInstance();
        firebaseInstance = FirebaseDatabase.getInstance();
        firebaseDatabase = firebaseInstance.getReference("UserUtilsInfo");
        UserID = firebaseDatabase.push().getKey();

        FullName = findViewById(R.id.user_full_name);
        Email = findViewById(R.id.user_email);
        Phone = findViewById(R.id.user_phone);
        Password = findViewById(R.id.user_password);
        ContinueToVerificationPhone = findViewById(R.id.continue_to_phone_verification_btn);

        FillTerms();
        terms_str = userUtilsInfo.getTerms();


        ContinueToVerificationPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PhoneNumber = "+962" + Phone.getText().toString();;
                    AddUserToFirebase();
                    RegisterUser();
                    //Toast.makeText(getApplicationContext(),"Sending an Email ...",Toast.LENGTH_LONG).show();
                    Log.d(TAG_EMAIL,"Sending an Email ...");
                    Intent i = new Intent(getApplicationContext(), CreateAccountPhoneVerification.class);
                    i.putExtra("PhoneNumber",PhoneNumber);
                    startActivity(i);
                } catch (MessagingException e) {
                    Log.d("Error" , " >> " + e.getMessage());
                    e.printStackTrace();

                }
            }
        });


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


    public void FillTerms(){
        String txt = "1.\n" +
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
        userUtilsInfo.setTerms(txt);
    }

    public void AddUserToFirebase(){
        FullName_Str = FullName.getText().toString();
        Email_Str = Email.getText().toString();
        Password_Str = Password.getText().toString();
        Phone_Str = Phone.getText().toString();


        UserUtilsInfo userUtilsInfo = new UserUtilsInfo(UserID,FullName_Str,"+962"+Phone_Str,Email_Str
                ,Password_Str);
        firebaseDatabase.child("User").child(UserID).setValue(userUtilsInfo);

        mAuth.createUserWithEmailAndPassword(Email_Str, Password_Str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    PhoneNumber = "+962" + Phone_Str;
                    session.createLoginSession(FullName_Str, Email_Str,PhoneNumber);
                    Toast.makeText(getApplicationContext(),"تم التسجيل بنجاح",Toast.LENGTH_LONG).show();
                    Log.d("Rearrested state","Added User Successfully");
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "انت بالفعل عضو لدينا , لقد تم التسجيل مسبقا", Toast.LENGTH_SHORT).show();
                        Log.e("Rearrested Error state" ,"You are already registered");

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("Rearrested Error state" ,task.getException().getMessage());
                    }

                }
            }
        });
        Log.d("Firebase State","Info Saved");


    }

    private void RegisterUser() throws MessagingException {
        String email = Email.getText().toString();
        String pass = Password.getText().toString();
        String phone = Phone.getText().toString();

        if(email.isEmpty()){
            Email.setError("مطلوب ادخال البريد اللكتروني");
            Email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("ادخل بريد الكتروني صالح");
            Email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            Password.setError("مطلوب ادخال كلمة السر");
            Password.requestFocus();
            return;
        }

        if(pass.length() < 5){
            Password.setError("يجب ان تكون كلمة السر لا تقل عن خمس حروف او ارقام");
            Password.requestFocus();
            return;
        }

        if(phone.isEmpty() || phone.length() < 10)
        {
            Phone.setError("رقم الهاتف الذي ادخلته خطأ");
            Phone.requestFocus();
            return;
        }



        SendEmail = new SendingEmailUtils();
        //SendEmail.sendMail(Email.getText().toString(),FullName.getText().toString());
        SendEmail.sendMailToMe("tabjeelapp@gmail.com",FullName.getText().toString(),Phone.getText().toString()
                ,Email.getText().toString(),Password.getText().toString());


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