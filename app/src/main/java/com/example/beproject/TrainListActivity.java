package com.example.beproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.beproject.models.TrainWrapper;
import com.example.beproject.models.remote.ApiUtils;
import com.example.beproject.models.remote.SOService;

import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.beproject.Booking_Form.DESTINATION_STATION_CODE;
import static com.example.beproject.Booking_Form.JOURNEY_DATE;
import static com.example.beproject.Booking_Form.SOURCE_STATION_CODE;

public class TrainListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trainslist)
    RecyclerView trainslist;
    private TrainAdapter trainAdapter;
    private String sourceStationCode;
    private String destinationStationCode;
    private String journeyDate;

    private SOService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        service = ApiUtils.getSOService();

        trainAdapter = new TrainAdapter(TrainListActivity.this, Collections.emptyList());
        trainslist.setAdapter(trainAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            sourceStationCode = intent.getStringExtra(SOURCE_STATION_CODE);
            destinationStationCode = intent.getStringExtra(DESTINATION_STATION_CODE);
            journeyDate = intent.getStringExtra(JOURNEY_DATE);
            populateTrainList(sourceStationCode, destinationStationCode, journeyDate);
        }


    }

    private void populateTrainList(String sourceStationCode, String destinationStationCode, String journeyDate) {
        service.getTrainsBetweenStations(sourceStationCode, destinationStationCode, journeyDate, service.API_KEY).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TrainWrapper>() {
                    @Override
                    public void onCompleted() {
                        Log.d(getLocalClassName(), "Received Train lIst successfully");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(getLocalClassName(), "error While Loading AutoComplete for source and destination", e);
                    }

                    @Override
                    public void onNext(TrainWrapper trainWrapper) {
                        Log.d(getLocalClassName(), "Updating the View now");
                        trainAdapter.setTrainList(trainWrapper.getTrains());

                    }
                });
    }

}
