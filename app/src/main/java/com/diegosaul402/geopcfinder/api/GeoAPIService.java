package com.diegosaul402.geopcfinder.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by diego on 01/12/2016.
 */

public interface GeoAPIService {
    @GET("{endpoint}")
    Call<PostalCodes> ListPostalCodes(@Path("endpoint")String endPoint,
                                      @Query("postalcode") String postalCode,
                                      @Query("country") String country,
                                      @Query("maxRows") String maxRows,
                                      @Query("username") String username);
    @GET("postalCodeSearchJSON?postalcode=36680&country=MX&maxRows=10&username=diego_402")
    Call<PostalCodes> ListPostalTest();
}
