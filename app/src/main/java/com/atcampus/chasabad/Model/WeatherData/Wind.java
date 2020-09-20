package com.atcampus.chasabad.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    public int speed;

    public Wind(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
