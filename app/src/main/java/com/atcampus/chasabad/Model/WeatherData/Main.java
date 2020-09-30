package com.atcampus.chasabad.Model.WeatherData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {
//    @SerializedName("temp")
//    public Double temp;
//    @SerializedName("humidity")
//    public float humidity;
//
//    public Main(Double temp, float humidity) {
//        this.temp = temp;
//        this.humidity = humidity;
//    }
//
//    public Double getTemp() {
//        return temp;
//    }
//
//    public void setTemp(Double temp) {
//        this.temp = temp;
//    }
//
//    public float getHumidity() {
//        return humidity;
//    }
//
//    public void setHumidity(float humidity) {
//        this.humidity = humidity;
//    }

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("feels_like")
    @Expose
    private Double feelsLike;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("pressure")
    @Expose
    private Long pressure;
    @SerializedName("humidity")
    @Expose
    private Long humidity;
    @SerializedName("sea_level")
    @Expose
    private Long seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private Long grndLevel;

    public Main(Double temp, Double feelsLike, Double tempMin, Double tempMax, Long pressure, Long humidity, Long seaLevel, Long grndLevel) {
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
        this.seaLevel = seaLevel;
        this.grndLevel = grndLevel;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Long getPressure() {
        return pressure;
    }

    public void setPressure(Long pressure) {
        this.pressure = pressure;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public Long getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Long seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Long getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Long grndLevel) {
        this.grndLevel = grndLevel;
    }
}
