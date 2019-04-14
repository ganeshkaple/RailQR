package com.example.beproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beproject.models.AadharData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class AdminAadharInsert extends AppCompatActivity {

    EditText aadharNo, name, contact, address, DOB, email;
    Button btn;

    DatabaseReference reff;
    AadharData aadharData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_aadhar_insert);

        aadharNo = findViewById(R.id.txt_aadharno);
        name = findViewById(R.id.txt_name);
        contact = findViewById(R.id.txt_contactno);
        email = findViewById(R.id.txt_email);
        DOB = findViewById(R.id.txt_dob);
        address = findViewById(R.id.txt_address);
        btn = findViewById(R.id.buttonSubmit);

        aadharData = new AadharData();
        reff = FirebaseDatabase.getInstance().getReference().child("AadharData");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String AadharNo = aadharNo.getText().toString().trim();
                String Name = name.getText().toString().trim();
                String Contact = contact.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String dateofBirth = DOB.getText().toString().trim();
                String Address = address.getText().toString().trim();


                aadharData.setAadharNo(AadharNo);
                aadharData.setName(Name);
                aadharData.setContact(Contact);
                aadharData.setAddress(Address);
                aadharData.setDOB(dateofBirth);
                aadharData.setEmail(Email);

                reff.child(AadharNo).setValue(aadharData);

                Toast.makeText(AdminAadharInsert.this, "Data Added Succesfully", Toast.LENGTH_SHORT).show();

                aadharNo.setText("");
                name.setText("");
                contact.setText("");
                email.setText("");
                DOB.setText("");
                address.setText("");
            }
        });


    }
}
