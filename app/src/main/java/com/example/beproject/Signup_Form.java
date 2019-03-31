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

public class Signup_Form extends AppCompatActivity {

    EditText txtEmail, txtPassword, txtConfirmPassword, txtAadharNo;
    Button btnRegister;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Register");

        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtConfirmPassword = findViewById(R.id.txt_confirm_password);
        txtAadharNo = findViewById(R.id.txt_aadharno);
        btnRegister = findViewById(R.id.buttonRegister);
        progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmpassword = txtConfirmPassword.getText().toString().trim();
                String aadhar = txtAadharNo.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(Signup_Form.this, "Please Enter Email-id", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(aadhar)) {

                    Toast.makeText(Signup_Form.this, "Please Enter Aadhar number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmpassword)) {

                    Toast.makeText(Signup_Form.this, "Please Re-enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8) {

                    Toast.makeText(Signup_Form.this, "Password too short", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                if (password.equals(confirmpassword)) {

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), Signup_Form.class));
                                        Toast.makeText(Signup_Form.this, "Registration Successful", Toast.LENGTH_SHORT).show();


                                    } else {

                                        Toast.makeText(Signup_Form.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });

                }

            }
        });
    }


}
