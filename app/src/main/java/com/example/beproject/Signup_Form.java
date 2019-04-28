package com.example.beproject;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Form extends AppCompatActivity {

    EditText txtEmail, txtPassword, txtConfirmPassword, txtAadharNo, txtContactNo;
    Button btnRegister;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;
    //FirebaseDatabase firebaseDatabase;

    String email, password, confirmpassword, aadhar, contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Register");


        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtConfirmPassword = findViewById(R.id.txt_confirm_password);
        txtAadharNo = findViewById(R.id.txt_aadharno);
        txtAadharNo.addTextChangedListener(new FourDigitTextWatcher());
        txtContactNo = findViewById(R.id.txt_contactno);
        txtContactNo.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        btnRegister = findViewById(R.id.buttonRegister);
        progressBar = findViewById(R.id.progressBar);

        databaseReference = FirebaseDatabase.getInstance().getReference("registerData");

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = txtEmail.getText().toString().trim();
                password = txtPassword.getText().toString().trim();
                confirmpassword = txtConfirmPassword.getText().toString().trim();
                aadhar = txtAadharNo.getText().toString().trim();
                contact = txtContactNo.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(Signup_Form.this, "Please Enter Email-id", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(aadhar)) {

                    Toast.makeText(Signup_Form.this, "Please Enter Aadhar number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (aadhar.length() != 15) {

                    Toast.makeText(Signup_Form.this, "Invalid Aadhar number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(contact)) {

                    Toast.makeText(Signup_Form.this, "Please Enter Contact number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (contact.length() != 10) {

                    Toast.makeText(Signup_Form.this, "Invalid Contact number", Toast.LENGTH_SHORT).show();
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

                                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(Signup_Form.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);

                                            }
                                        });

                                        RegisterData info = new RegisterData(
                                                aadhar,
                                                contact,
                                                email,
                                                password

                                        );

                                        //Toast.makeText(Signup_Form.this, "Hiiiiiiiiii", Toast.LENGTH_SHORT).show();

                                        FirebaseDatabase.getInstance().getReference("registerData")
                                                .child(firebaseAuth.getCurrentUser().getUid())
                                                .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(Signup_Form.this, "Check Your Email For Verification", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), Login_Form.class));
                                                progressBar.setVisibility(View.GONE);
                                                txtAadharNo.setText("");
                                                txtConfirmPassword.setText("");
                                                txtEmail.setText("");
                                                txtPassword.setText("");
                                                txtContactNo.setText("");

                                            }
                                        });




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
