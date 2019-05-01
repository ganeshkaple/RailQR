package com.example.beproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class Login_Form extends AppCompatActivity {

    Button registerbtn;
    EditText email, password;
    ProgressBar progressBar;
    Button login;
    private FirebaseAuth firebaseAuth;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
//        getSupportActionBar().setTitle("Login");


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        registerbtn = findViewById(R.id.registerbtn);

        firebaseAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);

        registerbtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Signup_Form.class)));

        login.setOnClickListener(v -> {

//                String email1 = email.getText().toString().trim();
//                String password1 = password.getText().toString().trim();


            String email1 = "piyugaikwad1997@gmail.com";
            if (TextUtils.isEmpty(email1)) {
                Toast.makeText(Login_Form.this, "Please enter email-id", Toast.LENGTH_SHORT).show();
                return;
            }

            String password1 = "qwertypiyu";
            if (TextUtils.isEmpty(password1)) {
                Toast.makeText(Login_Form.this, "Please enter password", Toast.LENGTH_SHORT).show();
                return;
            }


            progressBar.setVisibility(View.VISIBLE);

            final FirebaseUser currentUser = firebaseAuth.getCurrentUser();


            firebaseAuth.signInWithEmailAndPassword(email1, password1)
                    .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (currentUser != null) {
                                    UID = currentUser.getUid();

                                    if (currentUser.isEmailVerified()) {

                                        //startActivity(new Intent(getApplicationContext(), Booking_Form.class));

                                        Intent intent = new Intent(Login_Form.this, Booking_Form.class);
                                        //intent.putExtra("passenger1", uid);
                                        intent.putExtra("CurrentUserUID", UID);
                                        startActivity(intent);

                                        Toast.makeText(Login_Form.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(Login_Form.this, UID, Toast.LENGTH_SHORT).show();
                                        Log.e("UID", UID);
                                        progressBar.setVisibility(View.GONE);
                                        email.setText("");
                                        password.setText("");

                                    } else {
                                        Toast.makeText(Login_Form.this, "Verify Your email", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        email.setText("");
                                        password.setText("");
                                    }
                                }

                            } else {

                                Toast.makeText(Login_Form.this, "Login UnSuccessful", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                email.setText("");
                                password.setText("");

                            }


                        }
                    });


        });

    }


}
