package com.example.beproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beproject.models.Station;
import com.example.beproject.models.StationApiResponse;
import com.example.beproject.models.remote.ApiUtils;
import com.example.beproject.models.remote.SOService;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Booking_Form extends AppCompatActivity {

    public static final String SOURCE_STATION_CODE = "sourceStationCode";
    public static final String DESTINATION_STATION_CODE = "destinationStationCode";
    public static final String JOURNEY_DATE = "journeyDate";

    String[] fruits = {"Apple", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear"};
    @BindView(R.id.autoCompleteSourceStation)
    AutoCompleteTextView autoCompleteSourceStation;
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.checkBox2)
    CheckBox checkBox2;
    @BindView(R.id.passenger2)
    TextInputLayout passenger2;
    @BindView(R.id.passenger3)
    TextInputLayout passenger3;
    @BindView(R.id.passenger4)
    TextInputLayout passenger4;
    @BindView(R.id.passenger5)
    TextInputLayout passenger5;
    @BindView(R.id.button)
    Button button;


    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    int year;
    int month;
    int dayofmonth;
    EditText pas2, pas3, pas4, pas5;
    String UID;
    @BindView(R.id.autoCompleteDestinationStation)
    AutoCompleteTextView autoCompleteDestinationStation;
    private AutoCompleteStationsAdapter autoCompleteSourceStationsAdapter;
    private AutoCompleteStationsAdapter autoCompleteDestinationStationsAdapter;
    private SOService service;
    private DatePickerDialog datePickerDialog;
    private Date journeyDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking__form);

        UID = getIntent().getStringExtra("CurrentUserUID");

        ButterKnife.bind(this);
        service = ApiUtils.getSOService();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    passenger2.setVisibility(View.GONE);
                    passenger3.setVisibility(View.GONE);
                    passenger4.setVisibility(View.GONE);
                    passenger5.setVisibility(View.GONE);

                }
                if (isChecked && checkBox2.isChecked()) {
                    passenger2.setVisibility(View.VISIBLE);
                    passenger3.setVisibility(View.VISIBLE);
                    passenger4.setVisibility(View.VISIBLE);
                }
                if (isChecked && !checkBox2.isChecked()) {
                    passenger2.setVisibility(View.GONE);
                    passenger3.setVisibility(View.GONE);
                    passenger4.setVisibility(View.GONE);
                    passenger5.setVisibility(View.GONE);
                }
                if (!isChecked && !checkBox2.isChecked()) {
                    passenger2.setVisibility(View.VISIBLE);
                    passenger3.setVisibility(View.VISIBLE);
                    passenger4.setVisibility(View.VISIBLE);
                    passenger5.setVisibility(View.VISIBLE);
                }
                if (!isChecked && checkBox2.isChecked()) {
                    passenger2.setVisibility(View.VISIBLE);
                    passenger3.setVisibility(View.VISIBLE);
                    passenger4.setVisibility(View.VISIBLE);
                    passenger5.setVisibility(View.VISIBLE);
                }

            }
        });


        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked && checkBox.isChecked()) {
                    passenger2.setVisibility(View.VISIBLE);
                    passenger3.setVisibility(View.VISIBLE);
                    passenger4.setVisibility(View.VISIBLE);
                }

                /*if (isChecked)
                {
                    passenger2.setVisibility(View.VISIBLE);
                    passenger3.setVisibility(View.VISIBLE);
                    passenger4.setVisibility(View.VISIBLE);
                    passenger5.setVisibility(View.VISIBLE);

                }*/

                if (!isChecked && checkBox.isChecked()) {
                    passenger2.setVisibility(View.GONE);
                    passenger3.setVisibility(View.GONE);
                    passenger4.setVisibility(View.GONE);
                    passenger5.setVisibility(View.GONE);
                }


            }
        });

        imageButton = findViewById(R.id.imageButton);

        button = findViewById(R.id.button);

        pas2 = findViewById(R.id.pas2);

        pas3 = findViewById(R.id.pas3);

        pas4 = findViewById(R.id.pas4);

        pas5 = findViewById(R.id.pas5);


        //String pass1=firebaseAuth.getCurrentUser().getMetadata().toString();

       /* String a2=pas2.getText().toString();
        String a3=pas3.getText().toString().trim();
        String a4=pas4.getText().toString().trim();
        String a5=pas5.getText().toString().trim();*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Toast.makeText(Booking_Form.this, pas2.getText().toString() , Toast.LENGTH_LONG).show();

                //startActivity(new Intent(getApplicationContext(), PassengerDetails.class));
               /* if(a2.equals("123")){
                    Toast.makeText(Booking_Form.this, "hello" , Toast.LENGTH_LONG).show();
                }*/

                //String uid=firebaseAuth.getCurrentUser().getUid();
                //Toast.makeText(Booking_Form.this, "hello:"+uid , Toast.LENGTH_LONG).show();

                String a2 = pas2.getText().toString();
                String a3 = pas3.getText().toString();
                String a4 = pas4.getText().toString();
                String a5 = pas5.getText().toString();

                if (a2.matches(""))
                    a2 = "NA";
                if (a3.matches(""))
                    a3 = "NA";
                if (a4.matches(""))
                    a4 = "NA";
                if (a5.matches(""))
                    a5 = "NA";
                if (UID.matches(""))
                    UID = "NA";


                //Toast.makeText(Booking_Form.this,"Passenger2222:"+uid, Toast.LENGTH_LONG).show();

               /* Intent intent = new Intent(getBaseContext(), PassengerDetails.class);
                intent.putExtra("uid", UID);
                intent.putExtra("passenger2", a2);
                intent.putExtra("passenger3", a3);
                intent.putExtra("passenger4", a4);
                intent.putExtra("passenger5", a5);
               */
               String sourceStationCode = autoCompleteSourceStation.getText().toString();
                String destinationStationCode = autoCompleteDestinationStation.getText().toString();
                if (TextUtils.isEmpty(sourceStationCode) && TextUtils.isEmpty(destinationStationCode) && journeyDate != null)
                    return;
//TODO Add refined validations later
                Intent intent = new Intent(Booking_Form.this, TrainListActivity.class);

                intent.putExtra(SOURCE_STATION_CODE, sourceStationCode);
                intent.putExtra(DESTINATION_STATION_CODE, destinationStationCode);
               // intent.putExtra(JOURNEY_DATE, journeyDate.toString());

                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Booking_Form.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                textView.setText(day + "-" + (month + 1) + "-" + year);


                                journeyDate = new Date(day, month, year);
                               // Log.e("Date - ", String.valueOf(journeyDate));
                                 //String JDate = String.valueOf(journeyDate);
                                 //Log.e("Date - ", JDate);

                            }
                        }, year, month, dayofmonth);


                datePickerDialog.show();
            }
        });

        autoCompleteSourceStationsAdapter = new AutoCompleteStationsAdapter(this, service, new AutoCompleteStationsAdapter.PostItemListener() {
            @Override
            public void onPostClick(String id) {
                //Toast.makeText(Booking_Form.this, "fsdf", Toast.LENGTH_LONG).show();
            }

        });


        autoCompleteSourceStation.setAdapter(autoCompleteSourceStationsAdapter);
        autoCompleteSourceStation.setThreshold(3);
        autoCompleteSourceStation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String stationName = s.toString();
                //   if (stationName.length() > 3)
                //              loadAutoCompleteSuggestions(stationName, StationTypeEnum.SOURCE);
            }
        });

        autoCompleteSourceStation.setOnItemClickListener((parent, view, position, id) -> {
            Station station = (Station) parent.getItemAtPosition(position);
            autoCompleteSourceStation.setText(station.getCode());


        });

        autoCompleteDestinationStationsAdapter = new AutoCompleteStationsAdapter(this, service, new AutoCompleteStationsAdapter.PostItemListener() {
            @Override
            public void onPostClick(String id) {
                //Toast.makeText(Booking_Form.this, "fsdf", Toast.LENGTH_LONG).show();
            }

        });

        autoCompleteDestinationStation.setAdapter(autoCompleteDestinationStationsAdapter);
        autoCompleteDestinationStation.setThreshold(3);
        autoCompleteDestinationStation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String stationName = s.toString();
                //   if (stationName.length() > 3)
                //   loadAutoCompleteSuggestions(stationName, StationTypeEnum.DESTINATION);
            }
        });

        autoCompleteDestinationStation.setOnItemClickListener((parent, view, position, id) -> {
            Station station = (Station) parent.getItemAtPosition(position);
            autoCompleteDestinationStation.setText(station.getCode());


        });
        // Might be required in case of above code doesnt work   autoCompleteSourceStationsAdapter = new AutoCompleteStationsAdapter(this, service, id -> Toast.makeText(Booking_Form.this, "fsdf", Toast.LENGTH_LONG).show());


    }


    private void loadAutoCompleteSuggestions(String stationName, StationTypeEnum stationType) {
        service.getStationNamesRx(stationName, service.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<StationApiResponse>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(Booking_Form.this, "Call completed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //   showErrorMessage();
                        Log.e(getLocalClassName(), "error While Loading AutoComplete for source and destination", e);

                    }

                    @Override
                    public void onNext(StationApiResponse stationApiResponse) {

                        List<Station> stations = stationApiResponse.getStations();
                       /* for (Station station : stations) {
                            Toast.makeText(Booking_Form.this, station.getName() + " " + station.getCode(), Toast.LENGTH_SHORT).show();
                        }*/
                        if (stationType.equals(StationTypeEnum.SOURCE))
                            Booking_Form.this.autoCompleteSourceStationsAdapter.updateAnswers(stations);
                        else
                            Booking_Form.this.autoCompleteDestinationStationsAdapter.updateAnswers(stations);

                    }

                });


    }

    private void showErrorMessage() {
        Toast.makeText(this, "Error again", Toast.LENGTH_LONG).show();
    }
}
