package com.example.beproject;

import com.example.beproject.models.remote.RetrofitClient;
import com.example.beproject.models.remote.SOService;

public class ApiUtils {
    public static final String BASE_URL = "https://api.railwayapi.com/v2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }


}
