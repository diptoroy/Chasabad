package com.atcampus.chasabad.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    public Double temp;
    @SerializedName("humidity")
    public int humidity;

    public Main(Double temp, int humidity) {
        this.temp = temp;
        this.humidity = humidity;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
