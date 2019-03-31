package com.example.beproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Form extends AppCompatActivity {

    Button registerbtn;
    EditText email, password;
    ProgressBar progressBar;
    Button login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login");


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        registerbtn = findViewById(R.id.registerbtn);

        firebaseAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Signup_Form.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = email.getText().toString().trim();
                String password1 = password.getText().toString().trim();

                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(Login_Form.this, "Please enter email-id", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(Login_Form.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);


                firebaseAuth.signInWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    Toast.makeText(Login_Form.this, "Login Successful", Toast.LENGTH_SHORT).show();


                                } else {

                                    Toast.makeText(Login_Form.this, "Login UnSuccessful", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }
        });

    }


}