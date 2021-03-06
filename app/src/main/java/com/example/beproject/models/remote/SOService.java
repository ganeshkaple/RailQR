package com.example.beproject.models.remote;

import com.example.beproject.models.SOAnswerResponse;
import com.example.beproject.models.StationApiResponse;
import com.example.beproject.models.TrainWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;



public interface SOService {
    String API_KEY = "3x6x2swia2";

    @GET("suggest-station/name/{stn_name}/apikey/{api_key}/")
    Observable<StationApiResponse> getStationNamesRx(@Path("stn_name") String stationName,
                                                     @Path("api_key") String apiKey);


    @GET("suggest-station/name/{stn_name}/apikey/{api_key}/")
    Call<StationApiResponse> getStationNames(@Path("stn_name") String stationName,
                                             @Path("api_key") String apiKey);

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswerResponse> getAnswers(@Query("tagged") String tags);

    //DateFormat should be dd-mm-yyyy
    @GET("between/source/{stn_code_source}/dest/{stn_code_dest}/date/{date}/apikey/{apikey}/")
    Observable<TrainWrapper> getTrainsBetweenStations(@Path("stn_code_source") String sourceStationCode, @Path("stn_code_dest") String destStationCode, @Path("date") String dateOfJourney, @Path("apikey") String apiKey);


}
