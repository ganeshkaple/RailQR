package com.example.beproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.beproject.models.remote.ApiUtils;
import com.example.beproject.models.remote.SOService;
import com.example.beproject.otherApi.ItemClickListener;
import com.example.beproject.otherApi.ServiceOther;
import com.example.beproject.otherApi.Train;
import com.example.beproject.otherApi.TrainAdapter;
import com.example.beproject.otherApi.TrainDetailsActivity;
import com.example.beproject.otherApi.TrainWrapper;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.bloco.faker.Faker;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.beproject.Booking_Form.DESTINATION_STATION_CODE;
import static com.example.beproject.Booking_Form.JOURNEY_DATE;
import static com.example.beproject.Booking_Form.SOURCE_STATION_CODE;

public class TrainListActivity extends AppCompatActivity implements InternetConnectionListener {

    public static final String TAG = TrainListActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trainslist)
    RecyclerView trainslistRecyclerView;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mySwipeRefreshLayout;
    private TrainAdapter trainAdapter;
    private String sourceStationCode;
    private String destinationStationCode;
    private String journeyDate;

    private SOService service;

    private ServiceOther serviceOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getApplication().setInternetConnectionListener(this);
        service = ApiUtils.getSOService(this);
        serviceOther = ApiUtils.getServiceNewApi(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        trainslistRecyclerView.setLayoutManager(layoutManager);
        List<Train> trains = new ArrayList<>();
        trains.add(newObject());


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
            journeyDate = intent.getStringExtra(JOURNEY_DATE);
            //  journeyDate = "17-05-2019";
            populateTrainList(sourceStationCode, destinationStationCode, journeyDate);
        } else {
            sourceStationCode = "BCT";
            destinationStationCode = "PUNE";
            journeyDate = "2-05-2019";
            populateTrainList(sourceStationCode, destinationStationCode, journeyDate);
        }

        /*
         * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.
         */
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        // Signal SwipeRefreshLayout to start the progress indicator
                        //  mySwipeRefreshLayout.setRefreshing(true);

                        mShimmerViewContainer.startShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.VISIBLE);
                        populateTrainList(sourceStationCode, destinationStationCode, journeyDate);
                    }
                }
        );

    }

    private Train newObject() {
        Faker faker = new Faker();
        Train train = new Train();
        train.setTrainNo(faker.number.number(5));
        train.setArrivalTime(new SimpleDateFormat("hh:mm:ss").format(faker.time.forward(faker.number.positive())));
        train.setDepartureTime(new SimpleDateFormat("hh:mm:ss").format(faker.time.forward(faker.number.positive())));
//TODO add other params

        return train;
    }

    /*  private void populateTrainList(String sourceStationCode, String destinationStationCode, String journeyDate) {
          if (sourceStationCode != null && destinationStationCode != null && journeyDate != null) {
              service.getTrainsBetweenStations(sourceStationCode, destinationStationCode, journeyDate, service.API_KEY).observeOn(AndroidSchedulers.mainThread())
                      .subscribeOn(Schedulers.io())
                      .subscribe(new Observer<TrainWrapper>() {
                          @Override
                          public void onCompleted() {
                              Log.d(getLocalClassName(), "Received Train lIst successfully");
                              mShimmerViewContainer.stopShimmerAnimation();
                              mShimmerViewContainer.setVisibility(View.GONE);
                              // Signal SwipeRefreshLayout to start the progress indicator
                              mySwipeRefreshLayout.setRefreshing(false);

                          }

                          @Override
                          public void onError(Throwable e) {
                              mySwipeRefreshLayout.setRefreshing(false);
                              Log.e(getLocalClassName(), "error While populating list", e);
                              mShimmerViewContainer.stopShimmerAnimation();
                              mShimmerViewContainer.setVisibility(View.GONE);
                              Snackbar.make(findViewById(android.R.id.content), "There's a network error ", Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      mShimmerViewContainer.startShimmerAnimation();
                                      mShimmerViewContainer.setVisibility(View.VISIBLE);
                                      mySwipeRefreshLayout.setRefreshing(true);
                                      populateTrainList(sourceStationCode, destinationStationCode, journeyDate);
                                  }
                              }).show();
                              // Signal SwipeRefreshLayout to start the progress indicator


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
  */
    private void populateTrainList(String sourceStationCode, String destinationStationCode, String journeyDate) {
        if (sourceStationCode != null && destinationStationCode != null && journeyDate != null) {
            serviceOther.getTrainsBetweenStations(serviceOther.API_KEY, sourceStationCode, destinationStationCode).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<TrainWrapper>() {
                        @Override
                        public void onCompleted() {
                            Log.d(getLocalClassName(), "Received Train lIst successfully");
                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            // Signal SwipeRefreshLayout to start the progress indicator
                            mySwipeRefreshLayout.setRefreshing(false);

                        }

                        @Override
                        public void onError(Throwable e) {
                            mySwipeRefreshLayout.setRefreshing(false);
                            Log.e(getLocalClassName(), "error While populating list", e);
                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            showNetworkError(sourceStationCode, destinationStationCode, journeyDate);
                            // Signal SwipeRefreshLayout to start the progress indicator


                        }

                        @Override
                        public void onNext(TrainWrapper trainWrapper) {
                            Log.d(getLocalClassName(), "Updating the View now");
                            if (trainWrapper.getStatus().equals("FAILED")) {
                                showNetworkError(sourceStationCode, destinationStationCode, journeyDate);
                            } else
                                trainAdapter.setTrainList(trainWrapper.getTrains());

                        }
                    });
        } else {
            Toast.makeText(this, "Your values seems empty man,", Toast.LENGTH_LONG).show();
        }
    }

    private void showNetworkError(String sourceStationCode, String destinationStationCode, String journeyDate) {
        Snackbar.make(findViewById(android.R.id.content), "There's a network error here, SOmething is definitely wrong ", Snackbar.LENGTH_LONG).setAction("Retry", v -> {
            mShimmerViewContainer.startShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            mySwipeRefreshLayout.setRefreshing(true);
            populateTrainList(sourceStationCode, destinationStationCode, journeyDate);
        }).show();
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


    @Override
    public void onInternetUnavailable() {
        //TODO Handle network error here


    }
}
