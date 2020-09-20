package com.atcampus.chasabad.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataModel {
    @SerializedName("coord")
    public Coord coord;
    @SerializedName("weather")
    public List<Weather> weathers = new ArrayList<>();
    @SerializedName("main")
    public Main main;
    @SerializedName("wind")
    public Wind wind;


    public WeatherDataModel(Coord coord, List<Weather> weathers, Main main, Wind wind) {
        this.coord = coord;
        this.weathers = weathers;
        this.main = main;
        this.wind = wind;
    }

    public WeatherDataModel() {

    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }


}

