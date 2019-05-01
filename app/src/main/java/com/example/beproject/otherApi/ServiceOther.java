package com.example.beproject.otherApi;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ServiceOther {
    String API_KEY = "c86d08058252f86584f286ac246797bd";

    @GET("TrainBetweenStation/apikey/{apikey}/From/{From}/To/{To}")
    Observable<TrainWrapper> getTrainsBetweenStations(
            @Path("apikey") String apiKey,
            @Path("From") String sourceStationCode, @Path("To") String destStationCode);


}
