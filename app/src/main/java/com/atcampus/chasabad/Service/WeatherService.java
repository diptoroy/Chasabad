package com.atcampus.chasabad.Service;

import com.atcampus.chasabad.Model.WeatherData.WeatherDataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherDataModel> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);
}
