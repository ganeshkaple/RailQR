package com.example.beproject.models.remote;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.beproject.InternetConnectionListener;
import com.example.beproject.NetworkConnectionInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final long REQUEST_TIMEOUT = 30;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;
    private static Retrofit retrofit2 = null;
    private static InternetConnectionListener mInternetConnectionListener;

    public static Retrofit getClient(String baseUrl, Context context) {
        if (retrofit == null) retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initOkHttp())
                .build();
        return retrofit;

    }

    public static Retrofit getClient2(String baseUrl, Context context) {

        if (retrofit2 == null) retrofit2 = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initOkHttp())
                .build();
        return retrofit2;

    }

    public static void setInternetConnectionListener(InternetConnectionListener listener) {
        mInternetConnectionListener = listener;
    }

    public static void removeInternetConnectionListener() {
        mInternetConnectionListener = null;
    }



    private static OkHttpClient initOkHttp() {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(loggingInterceptor());

        httpClient.addInterceptor(new NetworkConnectionInterceptor() {
            @Override
            public boolean isInternetAvailable() {
                return isInternetAvailable();
            }

            @Override
            public void onInternetUnavailable() {
                if (mInternetConnectionListener != null) {
                    mInternetConnectionListener.onInternetUnavailable();
                }
            }
        });
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Request-Type", "Android")
                    .addHeader("Content-Type", "application/json");

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        okHttpClient = httpClient.build();
        return okHttpClient;
    }


    static public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor =
                new HttpLoggingInterceptor(
                        message -> Log.d("OkHttp", "Retrofit Logging " + message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public static void resetApiClient() {
        retrofit = null;
        okHttpClient = null;
    }

    static Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

}
