package com.atcampus.chasabad.Model.WeatherModel;

import com.google.gson.annotations.SerializedName;

class Sys {
    @SerializedName("country")
    public String country;
    @SerializedName("sunrise")
    public long sunrise;
    @SerializedName("sunset")
    public long sunset;
}
