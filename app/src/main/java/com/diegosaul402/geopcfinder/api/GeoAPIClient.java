package com.diegosaul402.geopcfinder.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by diego on 01/12/2016.
 */

public class GeoAPIClient {
    Retrofit retrofit;

    private final static String BASE_URL = "http://api.geonames.org/";

    public GeoAPIClient(){
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public GeoAPIService getGeoAPIService(){
        return this.retrofit.create(GeoAPIService.class);
    }
}
