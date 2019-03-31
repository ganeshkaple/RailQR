package com.example.beproject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beproject.models.Station;
import com.example.beproject.models.StationApiResponse;
import com.example.beproject.models.remote.SOService;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Booking_Form extends AppCompatActivity {
    @BindView(R.id.autoCompleteSourceStation)
    DelayedAutoCompleteTextView autoCompleteSourceStation;
    @BindView(R.id.autoCompleteDestinationStation)
    DelayedAutoCompleteTextView autoCompleteDestinationStation;
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
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView6)
    TextView textView6;


    private AnswerAdapter answerAdapter;
    private SOService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking__form);
        ButterKnife.bind(this);
        service = ApiUtils.getSOService();
        answerAdapter = new AnswerAdapter(this, service, new AnswerAdapter.PostItemListener() {
            @Override
            public void onPostClick(String id) {
                Toast.makeText(Booking_Form.this, "fsdf", Toast.LENGTH_LONG).show();
            }
        });


        autoCompleteSourceStation.setAdapter(answerAdapter);
        autoCompleteSourceStation.setLoadingIndicator(
                findViewById(R.id.pb_loading_indicator));

        autoCompleteSourceStation.setThreshold(10);
        autoCompleteSourceStation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

               /* String stationName = s.toString();
                if (stationName.length() >3)
                    loadAnswer(stationName);
*/
            }

            @Override
            public void afterTextChanged(Editable s) {

              /*  String stationName = s.toString();
                if (stationName.length() >3)
                loadAnswer(stationName);
            */
            }
        });
    }

    private void loadAnswer(String stationName) {
       /* service.getStationNames("pun", service.API_KEY).enqueue(new Callback<SOAnswerResponse>() {


            @Override
            public void onResponse(Call<SOAnswerResponse> call, retrofit2.Response<SOAnswerResponse> response) {
                if (response.isSuccessful()){


                }else {
                    int statusCode = response.code();
                    //handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswerResponse> call, Throwable t) {
                showErrorMessage();
                Log.d(getLocalClassName(),"error Bitch");
            }
        });*/

        /*service.getStationNames("pun", service.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Station>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this,"Call completed",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                showErrorMessage();
                Log.d(getLocalClassName(),"error " , e);

            }

            @Override
            public void onNext(List<Station> stations) {
                answerAdapter.updateAnswers(stations);
            }
        });*/
        service.getStationNames(stationName, service.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<StationApiResponse>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(Booking_Form.this, "Call completed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorMessage();
                        Log.d(getLocalClassName(), "error ", e);

                    }

                    @Override
                    public void onNext(StationApiResponse stationApiResponse) {

                        List<Station> stations = stationApiResponse.getStations();
                        for (Station station : stations) {
                            Toast.makeText(Booking_Form.this, station.getName() + " " + station.getCode(), Toast.LENGTH_SHORT).show();
                        }
                        answerAdapter.updateAnswers(stations);


                    }
                });


    }

    private void showErrorMessage() {
        Toast.makeText(this, "Error again, Bitch", Toast.LENGTH_LONG).show();
    }
}
