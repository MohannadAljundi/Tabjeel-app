package com.example.tabjeel.CreateAccount;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import com.chaos.view.PinView;
import com.example.tabjeel.Home.HomeActivity;
import com.example.tabjeel.R;
import com.example.tabjeel.Utils.UserSessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class CreateAccountPhoneVerification extends AppCompatActivity {


    UserSessionManager session ;
    private String VerificationId;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private PinView pinView;
    private TextView NotVerified , Verified , Description;
    private String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account_phone_verification);
        session = new UserSessionManager(getApplicationContext());

        auth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.verification_pin_conform_progressbar);
        pinView = findViewById(R.id.pinView_verification);
        NotVerified = findViewById(R.id.phone_verification_message_not_verified);
        Verified = findViewById(R.id.phone_verification_message_verified);
        Description = findViewById(R.id.phone_verification_explanatory_message);

        String PhoneNumber = getIntent().getStringExtra("PhoneNumber");
        HashMap<String,String > UserUtils = session.getUserDetails();

        phone  = UserUtils.get(UserSessionManager.KEY_PHONE);
        SendVerificationCode(PhoneNumber);


        findViewById(R.id.verification_pin_conform_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = pinView.getText().toString().trim();
                if(code.isEmpty() || code.length() <6){
                    Description.setVisibility(View.INVISIBLE);
                    NotVerified.setVisibility(View.VISIBLE);
                    return;
                }
                VerifyCode(code);
                Description.setVisibility(View.INVISIBLE);
                Verified.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.create_account_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }



    private void VerifyCode(String Code){
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId,Code);
            SingInWithCredential(credential);
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            Log.e("VerifyCode","Verification Code is wrong");
        }
    }

    private void SingInWithCredential(PhoneAuthCredential credential) {
        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"تم تسجيل رقم الهاتف بنجاح",Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public void SendVerificationCode(String phone){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                pinView.setText(code);
                VerifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(getApplicationContext(),"حدث خطأ !",Toast.LENGTH_LONG).show();
        }
    };
}