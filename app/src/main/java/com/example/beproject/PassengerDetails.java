package com.example.beproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PassengerDetails extends AppCompatActivity {

    DatabaseReference firebaseDatabase;
    String aadharno, name, age, gender, CurrentUserAadhar;
    TextView a1, n1, ag1, gen1;
    TextView a2, n2, ag2, gen2;
    TextView a3, n3, ag3, gen3;
    TextView a4, n4, ag4, gen4;
    TextView a5, n5, ag5, gen5;
    CardView c1, c2, c3, c4, c5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);

        a1 = findViewById(R.id.aadhar1);
        n1 = findViewById(R.id.name1);
        ag1 = findViewById(R.id.age1);
        gen1 = findViewById(R.id.Gender1);

        a2 = findViewById(R.id.aadhar2);
        n2 = findViewById(R.id.name2);
        ag2 = findViewById(R.id.age2);
        gen2 = findViewById(R.id.Gender2);

        a3 = findViewById(R.id.aadhar3);
        n3 = findViewById(R.id.name3);
        ag3 = findViewById(R.id.age3);
        gen3 = findViewById(R.id.Gender3);

        a4 = findViewById(R.id.aadhar4);
        n4 = findViewById(R.id.name4);
        ag4 = findViewById(R.id.age4);
        gen4 = findViewById(R.id.Gender4);

        a5 = findViewById(R.id.aadhar5);
        n5 = findViewById(R.id.name5);
        ag5 = findViewById(R.id.age5);
        gen5 = findViewById(R.id.Gender5);

        c1 = findViewById(R.id.card1);
        c2 = findViewById(R.id.card2);
        c3 = findViewById(R.id.card3);
        c4 = findViewById(R.id.card4);
        c5 = findViewById(R.id.card5);

        String currentUserUID = getIntent().getStringExtra("uid");
        String adharForPassenger2 = getIntent().getStringExtra("passenger2");
        String pass3 = getIntent().getStringExtra("passenger3");
        String pass4 = getIntent().getStringExtra("passenger4");
        String pass5 = getIntent().getStringExtra("passenger5");


        Log.e("Paaaaasssssss2 ", adharForPassenger2);
        Log.e("Paaaaasssssss3 ", pass3);
        Log.e("Paaaaasssssss4 ", pass4);
        Log.e("Paaaaasssssss5 ", pass5);

        Toast.makeText(PassengerDetails.this, "currentUserUID:" + currentUserUID, Toast.LENGTH_LONG).show();


        //for current passengers
        if (currentUserUID.matches("NA")) {
            //showPassenger(adharForPassenger2,a2,n2,ag2,gen2);
            c1.setVisibility(View.GONE);

        } else {

            getCurrentUserAadharNo(currentUserUID);

            //c1.setVisibility(View.GONE);
        }

        //for other 3 passengers
        if (adharForPassenger2.matches("NA")) {
            //showPassenger(adharForPassenger2,a2,n2,ag2,gen2);
            c2.setVisibility(View.GONE);

        } else {
            showPassenger(adharForPassenger2, a2, n2, ag2, gen2);
            //c1.setVisibility(View.GONE);
        }

        if (pass3.matches("NA")) {
            //showPassenger(adharForPassenger2,a2,n2,ag2,gen2);
            c3.setVisibility(View.GONE);

        } else {
            showPassenger(pass3, a3, n3, ag3, gen3);
            //c1.setVisibility(View.GONE);
        }

        if (pass4.matches("NA")) {
            //showPassenger(adharForPassenger2,a2,n2,ag2,gen2);
            c4.setVisibility(View.GONE);

        } else {
            showPassenger(pass4, a4, n4, ag4, gen4);
            //c1.setVisibility(View.GONE);
        }


        if (pass5.matches("NA")) {
            //showPassenger(adharForPassenger2,a2,n2,ag2,gen2);
            c5.setVisibility(View.GONE);

        } else {
            showPassenger(pass5, a5, n5, ag5, gen5);
            //c1.setVisibility(View.GONE);
        }


    }

    public String getCurrentUserAadharNo(String uid) {
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("registerData").child(uid);


        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                CurrentUserAadhar = dataSnapshot.child("AadharNo").getValue().toString();
                showPassenger(CurrentUserAadhar, a1, n1, ag1, gen1);
                //name = dataSnapshot.child("name").getValue().toString();
                //age = dataSnapshot.child("dob").getValue().toString();
                //gender = dataSnapshot.child("contact").getValue().toString();

                //Toast.makeText(PassengerDetails.this,aadharno+name,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        return CurrentUserAadhar;

    }

    public void showPassenger(String AadharNo, TextView t1, TextView t2, TextView t3, TextView t4) {
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("AadharData").child(AadharNo);


        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                aadharno = dataSnapshot.child("aadharNo").getValue().toString();
                name = dataSnapshot.child("name").getValue().toString();
                age = dataSnapshot.child("dob").getValue().toString();
                gender = dataSnapshot.child("contact").getValue().toString();

                t1.setText(aadharno);
                t2.setText(name);
                t3.setText(age);
                t4.setText(gender);


                //Toast.makeText(PassengerDetails.this,aadharno+name,Toast.LENGTH_LONG).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }
}
