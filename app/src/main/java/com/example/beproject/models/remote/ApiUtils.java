package com.example.beproject.models.remote;

public class ApiUtils {
    public static final String BASE_URL = "https://api.railwayapi.com/v2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }


}
