package com.example.beproject;

import com.example.beproject.models.remote.RetrofitClient;
import com.example.beproject.models.remote.SOService;

/**
 * Created by ganesh on 2/1/18.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.railwayapi.com/v2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }


}
