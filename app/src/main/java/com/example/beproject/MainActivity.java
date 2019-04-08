package com.example.beproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.beproject.models.StationApiResponse;
import com.example.beproject.models.remote.SOService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private AnswerAdapter answerAdapter;
    private SOService service;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = ApiUtils.getSOService();
        recyclerView = findViewById(R.id.recycler_view);
       /* answerAdapter = new AnswerAdapter(Collections.<Station>emptyList(),this,  new AnswerAdapter.PostItemListener() {
            @Override
            public void onPostClick(String id) {
                Toast.makeText(MainActivity.this,"eyah hfwsf" + id, Toast.LENGTH_LONG).show();
            }
        });
       */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(answerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // loadAnswer();
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
            publ/ic void onError(Throwable e) {
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
                        Toast.makeText(MainActivity.this, "Call completed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorMessage();
                        Log.d(getLocalClassName(), "error ", e);

                    }

                    @Override
                    public void onNext(StationApiResponse stationApiResponse) {
                        answerAdapter.updateAnswers(stationApiResponse.getStations());

                    }
                });


    }

    private void showErrorMessage() {
        Toast.makeText(this, "Error again, Bitch", Toast.LENGTH_LONG).show();
    }
}
