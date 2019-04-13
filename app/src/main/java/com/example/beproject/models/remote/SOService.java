package com.example.beproject.models.remote;

import com.example.beproject.models.SOAnswerResponse;
import com.example.beproject.models.StationApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ganesh on 2/1/18.
 */

public interface SOService {
    String API_KEY = "v7wvv0wabr";

    @GET("suggest-station/name/{stn_name}/apikey/{api_key}/")
    Observable<StationApiResponse> getStationNames(@Path("stn_name") String stationName,
                                                   @Path("api_key") String apiKey);

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswerResponse> getAnswers(@Query("tagged") String tags);


}
