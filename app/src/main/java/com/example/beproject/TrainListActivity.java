package com.example.beproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.beproject.models.Train;
import com.example.beproject.models.TrainWrapper;
import com.example.beproject.models.remote.ApiUtils;
import com.example.beproject.models.remote.SOService;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.beproject.Booking_Form.DESTINATION_STATION_CODE;
import static com.example.beproject.Booking_Form.SOURCE_STATION_CODE;

public class TrainListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trainslist)
    RecyclerView trainslistRecyclerView;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        trainslistRecyclerView.setLayoutManager(layoutManager);

        trainAdapter = new TrainAdapter(TrainListActivity.this, Collections.emptyList(), new ItemClickListener() {
            @Override
            public void onItemClick(Train item) {
                TrainDetailsActivity.newIntent(item, TrainListActivity.this);
            }
        });
        trainslistRecyclerView.setAdapter(trainAdapter);


        Intent intent = getIntent();
        if (intent != null) {
            sourceStationCode = intent.getStringExtra(SOURCE_STATION_CODE);
            destinationStationCode = intent.getStringExtra(DESTINATION_STATION_CODE);
            //journeyDate = intent.getStringExtra(JOURNEY_DATE);
            journeyDate = "17-04-2019";
            populateTrainList(sourceStationCode, destinationStationCode, journeyDate);
        }
        /*else {*//*
        sourceStationCode = "BCT";
        destinationStationCode = "PUNE";
        journeyDate = "15-04-2019";
        populateTrainList(sourceStationCode, destinationStationCode, journeyDate);
        *//*  }
         */

    }

    private void populateTrainList(String sourceStationCode, String destinationStationCode, String journeyDate) {
        if (sourceStationCode != null && destinationStationCode != null && journeyDate != null) {
            service.getTrainsBetweenStations(sourceStationCode, destinationStationCode, journeyDate, service.API_KEY).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<TrainWrapper>() {
                        @Override
                        public void onCompleted() {
                            Log.d(getLocalClassName(), "Received Train lIst successfully");
                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(getLocalClassName(), "error While populating list", e);
                        }

                        @Override
                        public void onNext(TrainWrapper trainWrapper) {
                            Log.d(getLocalClassName(), "Updating the View now");

                            trainAdapter.setTrainList(trainWrapper.getTrains());

                        }
                    });
        } else {
            Toast.makeText(this, "Your values seems empty man,", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }


}
