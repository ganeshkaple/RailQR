package com.example.beproject.models.remote;

import android.content.Context;

import com.example.beproject.otherApi.ServiceOther;

public class ApiUtils {
    private static final String BASE_URL = "https://api.railwayapi.com/v2/";


    private static final String BASE_URL_OTHER = "http://indianrailapi.com/api/v2/";

    public static SOService getSOService(Context context) {
        return RetrofitClient.getClient(BASE_URL, context).create(SOService.class);
    }

    public static ServiceOther getServiceNewApi(Context context) {

        return RetrofitClient.getClient2(BASE_URL_OTHER, context).create(ServiceOther.class);
    }


}
