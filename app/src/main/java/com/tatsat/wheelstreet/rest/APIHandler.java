package com.tatsat.wheelstreet.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Shivlabs on 8/12/2016.
 */
public class APIHandler
{
    private static final String BASE_URL = "https://api.wheelstreet.com/v1/test";

    private static final long HTTP_TIMEOUT = TimeUnit.SECONDS.toMillis(120);
    private static BazzarPrivee_APIServices apiService;


    /**
     * For Normal Application related API Calling done here
     *
     * @return
     */
    public static BazzarPrivee_APIServices getApiService()
    {
        if (apiService == null)
        {
            OkHttpClient okHttpClient = new OkHttpClient();

            okHttpClient.setConnectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            okHttpClient.setWriteTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            okHttpClient.setReadTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);

            Gson  gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

            //For create retrofit instance
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(BASE_URL)
                    .setClient(new OkClient(okHttpClient))
                    .setConverter(new GsonConverter(gson))
                    .build();

            apiService = restAdapter.create(BazzarPrivee_APIServices.class);
            return apiService;
        }
        else
        {
            return apiService;
        }

    }
}
