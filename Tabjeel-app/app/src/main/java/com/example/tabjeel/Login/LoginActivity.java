package com.example.tabjeel.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabjeel.CreateAccount.CreateAccountHome;
import com.example.tabjeel.Home.HomeActivity;
import com.example.tabjeel.R;
import com.example.tabjeel.Utils.UserSessionManager;
import com.example.tabjeel.Utils.UserUtilsInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    UserSessionManager session ;
    private FirebaseDatabase firebaseInstance;
    private DatabaseReference firebaseDatabase;
    private Button login;
    private FirebaseAuth mAuth;
    private EditText Email , Pass ;
    private String  Email_Str , Password_Str ;
    public String Name_Str;

    UserUtilsInfo userUtilsInfo = new UserUtilsInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new UserSessionManager(getApplicationContext());
        findViewById(R.id.you_do_not_have_account_txt).setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        Email = findViewById(R.id.login_email);
        Pass = findViewById(R.id.login_password);


        firebaseInstance = FirebaseDatabase.getInstance();
        firebaseDatabase = firebaseInstance.getReference("UserUtilsInfo");

        login = findViewById(R.id.login_btn);
        login.setOnClickListener(v -> {
            String email = Email.getText().toString();
            String pass = Pass.getText().toString();
            if(email.isEmpty()){
                Email.setError("Enter Your Email !!");
                Email.requestFocus();
                return;
            }
            else if(pass.isEmpty()){
                Pass.setError("Enter Your Password !!");
                Pass.requestFocus();
            }

            LoginSoGood(email,pass);


        });

    }

    private void LoginSoGood(final String email, String pass){

        //ReadNiceNameFromFirebase();
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Welcome " + userUtilsInfo.FullName,Toast.LENGTH_LONG).show();
                            session.createLoginSession(userUtilsInfo.FullName, email,"phone");
                            Intent i = new Intent(getBaseContext(), HomeActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    void ReadNiceNameFromFirebase(){

        Email_Str = Email.getText().toString();
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){  // row read
                    for(DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()){ // column read
                        if(Email_Str.equals(dataSnapshot2.child("Email").getValue(String.class))){
                            userUtilsInfo.FullName = dataSnapshot2.child("NiceName").getValue(String.class);
                        }
                        Log.d("Firebase State","Read Name Successful" +" >> " + userUtilsInfo.FullName);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
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