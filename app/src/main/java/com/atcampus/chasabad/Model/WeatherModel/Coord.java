package com.atcampus.chasabad.Model.WeatherModel;

import com.google.gson.annotations.SerializedName;

class Coord {
    @SerializedName("lon")
    public float lon;
    @SerializedName("lat")
    public float lat;
}
