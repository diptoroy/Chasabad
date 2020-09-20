package com.atcampus.chasabad.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    public float lon;
    @SerializedName("lat")
    public float lat;

    public Coord(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
